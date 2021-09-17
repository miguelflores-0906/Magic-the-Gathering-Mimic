package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import io.magicthegathering.javasdk.resource.Card;

import static android.app.Activity.RESULT_OK;

public class DeckBuilderFragment extends Fragment {

    private Button btn_add;
    private ArrayList<BuilderCard> cardArrayList = new ArrayList<>();
    private DeckBuilderAdapter adapter;
    private EditText et_deck_name;
    private FloatingActionButton fab_save;
    private FirebaseAuth fAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_deck_builder, container, false);

        btn_add = view.findViewById(R.id.btn_deck_builder);
        fab_save = view.findViewById(R.id.fab_deck_builder);
        et_deck_name = view.findViewById(R.id.et_deck_name);
        fAuth = FirebaseAuth.getInstance();
//        DeckDBDAO deckDBDAO = new DeckDBDAOImpl(getActivity().getApplicationContext());


        btn_add.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), DeckAddCardActivity.class);
            startActivityForResult(intent, 1);
        });

        // TODO: FloatingActionButton on click (save)
        fab_save.setOnClickListener(v ->{
//            Deck deck = new Deck();
//            deck.setUsername(fAuth.getCurrentUser().getEmail());
//            deck.setDeckname(et_deck_name.getText().toString());
//            deck.setCards("Sample");
//            deckDBDAO.addDeck(deck);

//            if (cardArrayList.isEmpty()) {
//                Toast.makeText(getActivity().getApplicationContext(), "Deck is empty", Toast.LENGTH_SHORT).show();
//            }
//            else {
//                for (BuilderCard card : cardArrayList) {
//                    System.out.println(card.toString());
//                }
//            }

            /**
             * For every card in the list
             * take the id and add it to an sb
             * append a - then add the card qty
             * then append a , to separate cards
             * */

            StringBuilder sb = new StringBuilder();

            for (BuilderCard builderCard : cardArrayList) {
                sb.append(builderCard.getMultiverseId());
                sb.append("-");
                sb.append(builderCard.getQty());
                sb.append(",");
            }

            System.out.println(sb.toString());

            // TODO: db stuff
        });

        // adapter
        adapter = new DeckBuilderAdapter(cardArrayList, getContext());

        // Layout Manager
        RecyclerView rv = view.findViewById(R.id.rv_deck_builder);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                System.out.println("Got a response!");
                System.out.println(data.getStringExtra("name") + "\n"
                                    + data.getIntExtra("multiverseId", -1) + "\n"
                                    + data.getIntExtra("qty", 0) + "\n");
                String cardName = data.getStringExtra("name");
                int multiverseId = data.getIntExtra("multiverseId", -1);
                int qty = data.getIntExtra("qty", 0);
                cardArrayList.add(0, new BuilderCard(multiverseId, cardName, qty));
                adapter.notifyItemInserted(0);
            }
        }
    }
}
