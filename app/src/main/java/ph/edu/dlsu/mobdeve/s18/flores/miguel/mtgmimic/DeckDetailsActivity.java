package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import io.magicthegathering.javasdk.api.SetAPI;
import io.magicthegathering.javasdk.resource.Card;
import io.magicthegathering.javasdk.resource.MtgSet;
import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.ActivityDeckDetailsBinding;

public class DeckDetailsActivity extends AppCompatActivity implements MasterCardlistAdapter.ItemClickListener {
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
        }

        username.setText(name);
        deckname.setText(dname);

        // Recycler View

//        cardArrayList = CustomDataHelper.loadCards();

        // call API to get all standard 2022 cards
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Adventures in the Forgotten Realms
                MtgSet afr = SetAPI.getSet("AFR");
                List<Card> afrCards = afr.getCards();

                // Strixhaven
                MtgSet stx = SetAPI.getSet("STX");
                List<Card> stxCards = stx.getCards();

                // Kaldheim
                MtgSet khm = SetAPI.getSet("KHM");
                List<Card> khmCards = khm.getCards();

                // Zendikar Rising
                MtgSet znr = SetAPI.getSet("ZNR");
                List<Card> znrCards = znr.getCards();

                // Arena Base Set
                MtgSet anb = SetAPI.getSet("ANB");
                List<Card> anbCards = anb.getCards();

                // add each set to the arraylist
                cardArrayList.addAll(afrCards);
                cardArrayList.addAll(stxCards);
                cardArrayList.addAll(khmCards);
                cardArrayList.addAll(znrCards);
                cardArrayList.addAll(anbCards);
            }
        }).start();
        
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
        Button add_card_btn = findViewById(R.id.btn_add_card);

        add_card_btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddCardsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onItemClick(io.magicthegathering.javasdk.resource.Card card) {
        Intent intent = new Intent(getApplicationContext(), CardDetailsActivity.class);
        intent.putExtra("cardName", card.getName());
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}