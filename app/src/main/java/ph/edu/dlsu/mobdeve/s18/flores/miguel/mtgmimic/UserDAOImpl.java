package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class UserDAOImpl implements UserDAO{
    private final String PATH = "users";
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://mobdeve-s17-flores-singson-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference myRef = database.getReference(PATH);
    public Map<String, Object> users = new HashMap<>();

    @Override
    public long addUser(User user) {
        final long[] result = {-1};
        final String[] key = new String[1];
        key[0] = myRef.push().getKey();
        user.setKey(key[0]);
        myRef.child(key[0]).setValue(user, new DatabaseReference.CompletionListener() {

            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref)
            {
                if(error != null)
                {
                    Log.e("Error", "ERROR : " + error.getMessage());
                }
                else
                {
                    Log.d("SUCCESS", "KEY = " + key[0]);
                    result[0] = 1L;
                }
            }
        });
        return result[0];
    }

//    @Override
//    public User getUser(User user) {
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot)
//            {
//                for(DataSnapshot data: snapshot.getChildren())
//                {
//                   if(data.child("email").getValue(String.class).equals(email))
//                   {
//                       user = data.getValue(User.class);
//                   }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//
//            }
//        });
//        return user;
//    }
}
