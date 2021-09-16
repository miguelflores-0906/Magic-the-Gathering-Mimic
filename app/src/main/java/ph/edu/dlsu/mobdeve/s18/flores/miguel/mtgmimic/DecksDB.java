package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DecksDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "decks.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_DECKS = "Decks";
    public static final String DECKS_USERNAME ="username";
    public static final String DECKS_DECKNAME = "deckname";
    public static final String DECKS_CARDS = "cards";

    private static final String CREATE_DECKS_TABLE = "create table " + TABLE_DECKS +
            " ( "+ DECKS_USERNAME + " text, "
            + DECKS_DECKNAME + " text, " + DECKS_CARDS + " text );";


    public DecksDB(Context context){ super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DECKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DECKS);
        onCreate(db);
    }
}
