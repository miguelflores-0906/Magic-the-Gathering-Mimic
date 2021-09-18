package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;

public class DeckDBDAOImpl implements DeckDBDAO{
    private SQLiteDatabase database;
    private DecksDB decksdb;

    public DeckDBDAOImpl(Context context)
    {
        decksdb = new DecksDB(context);
    }

    @Override
    public long addDeck(Deck deck) {
        ContentValues values = new ContentValues();

        values.put(DecksDB.DECKS_USERNAME, deck.getUsername());
        values.put(DecksDB.DECKS_DECKNAME, deck.getDeckname());
        values.put(DecksDB.DECKS_CARDS, deck.getCards());

        database = decksdb.getWritableDatabase();

        long id = database.insert(DecksDB.TABLE_DECKS, null, values);

        if(database != null)
        {
            decksdb.close();
        }

        return id;
    }

    @Override
    public ArrayList<Deck> getDecks() {
        ArrayList<Deck> result = new ArrayList<>();
        String[] columns = {DecksDB.DECKS_USERNAME, DecksDB.DECKS_DECKNAME, DecksDB.DECKS_CARDS};

        database = decksdb.getReadableDatabase();

        Cursor cursor = database.query(DecksDB.TABLE_DECKS, columns,
                null, null, null, null,null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast())
        {
            Deck temp = new Deck();
            temp.setUsername(cursor.getString(0));
            temp.setDeckname(cursor.getString(1));
//            temp.setEmail(cursor.getString(2));
            //placeholder for deck string
            result.add(temp);
            cursor.moveToNext();
        }

        if(cursor != null)
        {
            cursor.close();
        }

        if(database != null)
        {
            decksdb.close();
        }

        return result;
    }

    @Override
    public ArrayList<Deck> getUserDecks(String username) {
        Deck deck = null;
        ArrayList<Deck> decks = null;

        String query = "SELECT * from " + DecksDB.TABLE_DECKS +
                " where " + DecksDB.DECKS_USERNAME + " is " + username;

        Cursor cursor = null;

        database = decksdb.getReadableDatabase();

        try
        {
            cursor = database.rawQuery(query, null);
            cursor.moveToFirst();


            while(!cursor.isAfterLast())
            {
                deck = new Deck();
                deck.setUsername(cursor.getString(cursor.getColumnIndex(DecksDB.DECKS_USERNAME)));
                deck.setDeckname(cursor.getString(cursor.getColumnIndex(DecksDB.DECKS_DECKNAME)));
                //placeholder for card string
                decks.add(deck);
                cursor.moveToNext();
            }
        }catch (SQLiteException e)
        {
            return null;
        }

        if(cursor != null)
        {
            cursor.close();
        }

        if(database != null)
        {
            decksdb.close();
        }

        return decks;
    }
}
