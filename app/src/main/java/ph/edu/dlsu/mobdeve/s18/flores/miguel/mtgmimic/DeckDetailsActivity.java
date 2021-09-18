package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
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
    private ActivityDeckDetailsBinding binding;
    private ArrayList<Card> cardArrayList;
    private ArrayList<Card> finalCardArrayList;
    private ArrayList<Integer> qtyList;
    private MasterCardlistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String name = "temp_name";
        String dname = "temp_dname";
        String cards = "temp_cards";

        Bundle extras = getIntent().getExtras();


        if (extras != null) {
            name = extras.getString("username");
            dname = extras.getString("deckname");
            cards = extras.getString("decklist");
        }

        String finalCards = cards;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                cardArrayList = destring(finalCards);
                qtyList = getQtyList(finalCards);

                for(int i = 0; i < cardArrayList.size(); i++)
                {
                    int j = qtyList.get(i);

                    for(int k = 0; k < j; k++)
                    {
                        finalCardArrayList.add(cardArrayList.get(i));
                    }
                }
            }
        });


        setContentView(R.layout.activity_deck_details);

        TextView username = findViewById(R.id.tv_deckdetails_author);
        TextView deckname = findViewById(R.id.tv_deckdetails_dname);


        username.setText(name);
        deckname.setText(dname);


        // Recycler View

  //      cardArrayList = CustomDataHelper.loadCards();

        // call API to get all standard 2022 cards

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                cardArrayList = destring(finalCards);
//                qtyList = getQtyList(finalCards);
//
//                for(int i = 0; i < cardArrayList.size(); i++)
//                {
//                    int j = qtyList.get(i);
//
//                    for(int k = 0; k < j; k++)
//                    {
//                        finalCardArrayList.add(cardArrayList.get(i));
//                    }
//                }
//            }
//        }).start();

        adapter = new MasterCardlistAdapter(finalCardArrayList, this);

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

    public ArrayList<Card> destring(String dbData) {
        ArrayList<Integer> multiverseList = new ArrayList<>();
        ArrayList<Card> allCards = new ArrayList<>();

        // split the db data into each card id and qty
        // array would look like this: {id-qty, id-qty, id-qty}
        String[] cardAndQty = dbData.split(",");

        // get each card id from cardAndQty
        /**
         * temp will hold two values only, the result when you split the String
         * "id-qty" at "-":
         * temp[0] = "id"
         * parse temp[0] into an int then add to the ArrayList
         * */
        String[] temp = new String[2];

        for (String s : cardAndQty) {
            temp = s.split("-");
            multiverseList.add(Integer.parseInt(temp[0]));
        }

        /**
         * This next bit will run through the list and get each card and add
         * them to the arraylist
         * */
        for (int i : multiverseList) {
            Card c = CardAPI.getCard(i);
            allCards.add(c);
        }

        return allCards;
    }

    public ArrayList<Integer> getQtyList(String dbData) {
        ArrayList<Integer> allQty = new ArrayList<>();

        // split the db data into each card id and qty
        // array would look like this: {id-qty, id-qty, id-qty}
        String[] cardAndQty = dbData.split(",");

        // get each card qty from cardAndQty
        /**
         * temp will hold two values only, the result when you split the String
         * "id-qty" at "-":
         * temp[1] = "qty"
         * parse temp[1] into an int then add to the ArrayList
         * */
        String[] temp = new String[2];

        for (String s : cardAndQty) {
            temp = s.split("-");
            allQty.add(Integer.parseInt(temp[1]));
        }

        return allQty;
    }
}