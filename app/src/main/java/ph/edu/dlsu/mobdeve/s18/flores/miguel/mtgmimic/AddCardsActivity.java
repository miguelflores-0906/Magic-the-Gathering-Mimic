package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import io.magicthegathering.javasdk.api.SetAPI;
import io.magicthegathering.javasdk.resource.Card;
import io.magicthegathering.javasdk.resource.MtgSet;
import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.ActivityAddCardsBinding;

public class AddCardsActivity extends AppCompatActivity implements MasterCardlistAdapter.ItemClickListener {
    private ActivityAddCardsBinding binding;
    private ArrayList<Card> cardArrayList;
    private MasterCardlistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cards);
        binding = ActivityAddCardsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

                cardArrayList.addAll(afrCards);
                cardArrayList.addAll(stxCards);
                cardArrayList.addAll(khmCards);
                cardArrayList.addAll(znrCards);
                cardArrayList.addAll(anbCards);
            }
        }).start();

        adapter = new MasterCardlistAdapter(cardArrayList, this);

        binding.rvAddCardsList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvAddCardsList.setAdapter(adapter);


        // TODO: Change back to deck list
        Button btn = findViewById(R.id.btn_done_add);

        btn.setOnClickListener(v -> {
           Intent intent = new Intent(getApplicationContext(), DeckDetailsActivity.class);
           startActivity(intent);
        });

    }

    @Override
    public void onItemClick(io.magicthegathering.javasdk.resource.Card card) {
        Toast.makeText(getApplicationContext(),
                "You have added " + card.getName() + " to your deck",
                Toast.LENGTH_LONG).show();
    }
}
