package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.ActivityDeckDetailsBinding;

public class DeckDetailsActivity extends AppCompatActivity implements MasterCardlistAdapter.ItemClickListener {
    private ActivityDeckDetailsBinding binding;
    private ArrayList<Card> cardArrayList;
    private MasterCardlistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_details);

        TextView username = findViewById(R.id.tv_deckdetails_author);
        TextView deckname = findViewById(R.id.tv_deckdetails_dname);

        String name = "temp_name";
        String dname = "temp_dname";

        Bundle extras = getIntent().getExtras();


        if (extras != null) {
            name = extras.getString("username");
            dname = extras.getString("deckname");
//            cardArrayList = extras.get("decklist");
        }

        username.setText(name);
        deckname.setText(dname);

        // Recycler View

        cardArrayList = CustomDataHelper.loadCards();
        adapter = new MasterCardlistAdapter(cardArrayList, this);

        RecyclerView recyclerView = findViewById(R.id.rv_deckdetailscards);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);


        FloatingActionButton fab = findViewById(R.id.fab_deckstats);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), DeckStatActivity.class);
            startActivity(intent);
        });

        // TODO: Change to Add cards Activity
        FloatingActionButton fab_add = findViewById(R.id.fab_add_cards);

        fab_add.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AddCardsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onItemClick(Card card) {
        Intent intent = new Intent(getApplicationContext(), CardDetailsActivity.class);
        intent.putExtra("cardName", card.getCardName());
        intent.putExtra("cardExp", card.getSet());
        intent.putExtra("cardType", card.getType());
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}