package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeckDAOImpl implements DeckDAO{

    private final String PATH = "decks";
    private FirebaseDatabase db = FirebaseDatabase.getInstance("https://mobdeve-s17-flores-singson-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference myRef = db.getReference(PATH);

    public DeckDAOImpl()
    {

    }

    public DeckDAOImpl(Context context)
    {

    }

    @Override
    public long addDeck(Deck deck) {
        final long[] result = {-1};
        myRef.push().setValue(deck, new DatabaseReference.CompletionListener() {

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
    public ArrayList<Deck> getDecks() {
        ArrayList<Deck> result = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot)
            {
                for(DataSnapshot data: snapshot.getChildren())
                {
                    Deck deck = new Deck();
                    deck.setDeckname(data.child("deckName").getValue(String.class));
                    deck.setUsername(data.child("username").getValue(String.class));
//                  deck.setDecklist(data.child("deckList").getValue(Deck.class));

                    result.add(deck);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        return result;
    }
}
