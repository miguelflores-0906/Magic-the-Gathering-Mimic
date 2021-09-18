package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserInvDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "userinv.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_UINV = "userinv";
    public static final String UINV_NAME ="username";
    public static final String UINV_CARDS = "cards";

    private static final String CREATE_DECKS_TABLE = "create table " + TABLE_UINV +
            " ( "+ UINV_NAME + " text, " + UINV_CARDS + " text );";


    public UserInvDB(Context context){ super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DECKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UINV);
        onCreate(db);
    }
}
