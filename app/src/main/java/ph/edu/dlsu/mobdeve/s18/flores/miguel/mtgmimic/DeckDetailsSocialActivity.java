package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import io.magicthegathering.javasdk.api.SetAPI;
import io.magicthegathering.javasdk.resource.Card;
import io.magicthegathering.javasdk.resource.MtgSet;
import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.ActivityDeckDetailsBinding;

public class DeckDetailsSocialActivity extends AppCompatActivity implements MasterCardlistAdapter.ItemClickListener {

    private DeckDetailsSocialActivity binding;
    private ArrayList<io.magicthegathering.javasdk.resource.Card> cardArrayList;
    private MasterCardlistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_details_social);

        TextView username = findViewById(R.id.tv_sdeckdetails_author);
        TextView deckname = findViewById(R.id.tv_sdeckdetails_dname);

        String name = "temp_name";
        String dname = "temp_dname";

        Bundle extras = getIntent().getExtras();


        if (extras != null) {
            name = extras.getString("username");
            dname = extras.getString("deckName");
//            cardArrayList = extras.get("decklist");
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
                List<io.magicthegathering.javasdk.resource.Card> afrCards = afr.getCards();

                // Strixhaven
                MtgSet stx = SetAPI.getSet("STX");
                List<io.magicthegathering.javasdk.resource.Card> stxCards = stx.getCards();

                // Kaldheim
                MtgSet khm = SetAPI.getSet("KHM");
                List<io.magicthegathering.javasdk.resource.Card> khmCards = khm.getCards();

                // Zendikar Rising
                MtgSet znr = SetAPI.getSet("ZNR");
                List<io.magicthegathering.javasdk.resource.Card> znrCards = znr.getCards();

                // Arena Base Set
                MtgSet anb = SetAPI.getSet("ANB");
                List<io.magicthegathering.javasdk.resource.Card> anbCards = anb.getCards();

                cardArrayList.addAll(afrCards);
                cardArrayList.addAll(stxCards);
                cardArrayList.addAll(khmCards);
                cardArrayList.addAll(znrCards);
                cardArrayList.addAll(anbCards);
            }
        }).start();

        adapter = new MasterCardlistAdapter(cardArrayList, this);

        RecyclerView recyclerView = findViewById(R.id.rv_sdeckdetailscards);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);


        FloatingActionButton fab = findViewById(R.id.fab_deckstats);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), DeckStatActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onItemClick(Card card) {
        Intent intent = new Intent(getApplicationContext(), CardDetailsActivity.class);
        intent.putExtra("cardName", card.getName());
        intent.putExtra("cardExp", card.getSet());
        intent.putExtra("cardType", card.getType());
        startActivity(intent);
    }
}