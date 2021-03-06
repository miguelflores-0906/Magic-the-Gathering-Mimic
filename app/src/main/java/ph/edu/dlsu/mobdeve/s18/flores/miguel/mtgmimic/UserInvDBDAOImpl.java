package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class UserInvDBDAOImpl implements UserInvDBDAO{
    private SQLiteDatabase database;
    private UserInvDB userInvDB;

    public UserInvDBDAOImpl(Context context) {
        userInvDB = new UserInvDB(context);
    }

    @Override
    public long addUserInv(String user, String cards) {
        ContentValues values = new ContentValues();

        values.put(UserInvDB.UINV_NAME, user);
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

        Cursor cursor = null;

        database = userInvDB.getReadableDatabase();

        try
        {
            cursor = database.rawQuery("SELECT cards FROM userinv WHERE username = '" + user + "'", null);
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

    @Override
    public long updateUserInv(String user, String updatedInv) {
        ContentValues values = new ContentValues();

        values.put(UserInvDB.UINV_CARDS, updatedInv);


        database = userInvDB.getWritableDatabase();

        int records = database.update(UserInvDB.TABLE_UINV, values, UserInvDB.UINV_NAME + " = '" + user + "'", null);

        if(database != null)
        {
            userInvDB.close();
        }

        return records;
    }
}
