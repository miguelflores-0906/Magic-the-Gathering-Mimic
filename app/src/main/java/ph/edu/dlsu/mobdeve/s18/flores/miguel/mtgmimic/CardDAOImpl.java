package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CardDAOImpl implements CardDAO{

    private final String PATH = "cards";
    private FirebaseDatabase db = FirebaseDatabase.getInstance("https://mobdeve-s17-flores-singson-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference myRef = db.getReference(PATH);

    public CardDAOImpl()
    {

    }

    public CardDAOImpl(Context context)
    {

    }
    @Override
    public long addCard(Card card) {
        final long[] result = {-1};
        final String[] key = new String[1];
        key[0] = myRef.push().getKey();
        card.setKey(key[0]);
        myRef.push().setValue(card, new DatabaseReference.CompletionListener() {

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


    @Override
    public ArrayList<Card> getCards() {
        ArrayList<Card> result = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot)
            {
                for(DataSnapshot data: snapshot.getChildren())
                {
                    Card card = new Card();
                    card.setCardName(data.child("cardName").getValue(String.class));
                    card.setSet(data.child("set").getValue(String.class));
                    card.setType(data.child("type").getValue(String.class));

                    result.add(card);
                    Log.d("Tag", "yes load work");
                    Log.d("Cards", "Card: " + result.get(0).getCardName());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            Log.e("Tag", "no card load doesnt work");
            }


        });

        return result;
    }

}

