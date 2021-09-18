package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;

public class DeckDBDAOImpl implements DeckDBDAO {
    private SQLiteDatabase database;
    private DecksDB decksdb;

    public DeckDBDAOImpl(Context context) {
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

        if (database != null) {
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
                null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Deck temp = new Deck();
            temp.setUsername(cursor.getString(0));
            temp.setDeckname(cursor.getString(1));
            temp.setCards(cursor.getString(2));
            result.add(temp);
            cursor.moveToNext();
        }

        if (cursor != null) {
            cursor.close();
        }

        if (database != null) {
            decksdb.close();
        }

        return result;
    }

    @Override
    public ArrayList<Deck> getUserDecks(String username) {
        ArrayList<Deck> result = new ArrayList<>();

        database = decksdb.getReadableDatabase();


        String queryString = "SELECT * FROM " + DecksDB.TABLE_DECKS + " WHERE " + DecksDB.DECKS_USERNAME + " = '\"+username+\"'";

        Cursor cursor = database.rawQuery(queryString, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Deck temp = new Deck();
            temp.setUsername(cursor.getString(0));
            temp.setDeckname(cursor.getString(1));
            temp.setCards(cursor.getString(2));
            result.add(temp);
            cursor.moveToNext();
        }

        if (cursor != null) {
            cursor.close();
        }

        if (database != null) {
            decksdb.close();
        }

        return result;
    }
}
