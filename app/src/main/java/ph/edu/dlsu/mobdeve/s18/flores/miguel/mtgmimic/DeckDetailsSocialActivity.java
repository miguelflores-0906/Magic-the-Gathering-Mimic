package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

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
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_details_social);

        TextView username = findViewById(R.id.tv_sdeckdetails_author);
        TextView deckname = findViewById(R.id.tv_sdeckdetails_dname);

        String name = "temp_name";
        String dname = "temp_dname";
        String cardList = "";

        Bundle extras = getIntent().getExtras();


        if (extras != null) {
            name = extras.getString("username");
            dname = extras.getString("deckName");
            cardList = extras.getString("cards");
        }

        username.setText(name);
        deckname.setText(dname);

        // Recycler View

        // make arrayList
        cardArrayList = new ArrayList<>();

        ArrayList<Integer> multiverseList = new ArrayList<>();
        ArrayList<Card> allCards = new ArrayList<>();

        // split the db data into each card id and qty
        // array would look like this: {id-qty, id-qty, id-qty}
        String[] cardAndQty = cardList.split(",");

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
        System.out.println("Started processing cards");


        showToast("Loading cards");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i : multiverseList) {
                    Card c = CardAPI.getCard(i);
                    System.out.println(c.getName());
                    allCards.add(c);
                }
                System.out.println("Done processing");
                System.out.println("These are all the cards that I found:");
                for (Card c : allCards) {
                    System.out.println(c.getName());
                }

                cardArrayList = allCards;

                for (Card c : cardArrayList) {
                    System.out.println("The card " + c.getName() + " is here.");
                }


                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateList(cardArrayList);
                    }
                });
            }
        }).start();

        adapter = new MasterCardlistAdapter(cardArrayList, this);

        RecyclerView recyclerView = findViewById(R.id.rv_sdeckdetailscards);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);

        System.out.println("this is outside");

//        if (!cardList.equalsIgnoreCase("")) {
//            adapter.updateList(destring(cardList));
//        }

        FloatingActionButton fab = findViewById(R.id.fab_deckstats);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), DeckStatActivity.class);
            startActivity(intent);
        });

//        FloatingActionButton fab_reload = findViewById(R.id.fab_reload_deck_social_details);
//
//        fab_reload.setOnClickListener(v -> {
//            adapter.updateList(cardArrayList);
//        });
    }

    @Override
    public void onItemClick(Card card) {
        Intent intent = new Intent(getApplicationContext(), CardDetailsActivity.class);
        intent.putExtra("cardName", card.getName());
        intent.putExtra("expansion", card.getSetName());
        intent.putExtra("type", card.getType());
        intent.putExtra("text", card.getText());
        intent.putExtra("imageUrl", card.getImageUrl());
        intent.putExtra("manaCost", card.getManaCost());
        startActivity(intent);
    }

    private ArrayList<Card> destring(String dbData) {
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
        System.out.println("Started processing cards");

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i : multiverseList) {
                    Card c = CardAPI.getCard(i);
                    System.out.println(c.getName());
                    allCards.add(c);
                }
                System.out.println("Done processing");
                System.out.println("These are all the cards that I found:");
                for (Card c : allCards) {
                    System.out.println(c.getName());
                }
            }
        }).start();



        return allCards;
    }

    private void showToast(String toast) {
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show());
    }
}