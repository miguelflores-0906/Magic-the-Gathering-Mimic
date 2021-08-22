package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.util.Log;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDAOImpl implements UserDAO{
    private final String PATH = "users";
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://mobdeve-s17-flores-singson-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference myRef = database.getReference(PATH);

    @Override
    public long addUser(User user) {
        final long[] result = {-1};
        myRef.push().setValue(user, new DatabaseReference.CompletionListener() {

            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref)
            {
                if(error != null)
                {
                    Log.e("Error", "ERROR : " + error.getMessage());
                }
                else
                {
                    Log.d("SUCCESS", "DATA INSERTED");
                    result[0] = 1L;
                }
            }
        });
        return result[0];
    }
}
