package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.ActivityDeckDetailsBinding;

public class DeckDetailsActivity extends AppCompatActivity {
    private ActivityDeckDetailsBinding binding;
    private ArrayList<Card> cardArrayList;
    private DeckDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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

//        binding = ActivityDeckDetailsBinding.inflate(getLayoutInflater());
//        adapter = new DeckDetailsAdapter(cardArrayList, this::onClick);
//
//
//        binding.rvDeckdetailscards.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        FloatingActionButton fab = findViewById(R.id.fab_deckstats);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), DeckStatActivity.class);
            startActivity(intent);
        });


    }

    private void onClick(Card card) {
        Intent intent = new Intent(getApplicationContext(), CardDetailsActivity.class);
        intent.putExtra("cardName", card.getCardName());
        intent.putExtra("cardExp", card.getSet());
        intent.putExtra("cardType", card.getType());
        startActivity(intent);
    }
}