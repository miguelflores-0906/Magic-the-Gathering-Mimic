package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class UserInvDBDAOImpl implements UserInvDBDAO{
    private SQLiteDatabase database;
    private UserInvDB userInvDB;

    @Override
    public long addUserInv(String username,String cards) {
        ContentValues values = new ContentValues();

        values.put(UserInvDB.UINV_NAME, username);
        values.put(UserInvDB.UINV_CARDS, cards);

        database = userInvDB.getWritableDatabase();

        long id = database.insert(UserInvDB.TABLE_UINV, null, values);

        if(database != null)
        {
            userInvDB.close();
        }

        return id;
    }

    @Override
    public String getUserInv(String user) {
        String cards = "";

        String query = "SELECT * from " + UserInvDB.TABLE_UINV +
                " where " + UserInvDB.UINV_NAME + " = " + user;

        Cursor cursor = null;

        database = userInvDB.getReadableDatabase();

        try
        {
            cursor = database.rawQuery(query, null);
            cursor.moveToFirst();


            while(!cursor.isAfterLast())
            {
                cards = cursor.getString(cursor.getColumnIndex(UserInvDB.UINV_CARDS));
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
            userInvDB.close();
        }

        return cards;
    }
}
