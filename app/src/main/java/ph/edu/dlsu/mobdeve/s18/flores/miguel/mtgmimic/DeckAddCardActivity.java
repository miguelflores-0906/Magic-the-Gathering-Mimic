package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.magicthegathering.javasdk.api.SetAPI;
import io.magicthegathering.javasdk.resource.Card;
import io.magicthegathering.javasdk.resource.MtgSet;
import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.ActivityDeckAddCardBinding;

public class DeckAddCardActivity extends AppCompatActivity {

    private Button cancelBtn, addBtn;
    private EditText qtyEt, nameEt;
    private ActivityDeckAddCardBinding binding;
    private String name;
    private int multiverseId, qty;
    private ArrayList<Card> cardArrayList = new ArrayList<>();
    private DeckAddAdapter.RecyclerViewClickListener listener;
    private DeckAddAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeckAddCardBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_deck_add_card);

        cancelBtn = findViewById(R.id.btn_cancel);
        addBtn = findViewById(R.id.btn_add_this_card);
        qtyEt = findViewById(R.id.et_add_this_card_qty);
        nameEt = findViewById(R.id.et_add_this_card);

        cancelBtn.setOnClickListener(v -> {
            finish();
        });

        addBtn.setOnClickListener(v -> {
            if (TextUtils.isEmpty(nameEt.getText().toString())
                    || TextUtils.isEmpty(qtyEt.getText().toString())) {
                Toast.makeText(this, "Please type something-nyan", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent();
                intent.putExtra("name", name);
                intent.putExtra("multiverseId", multiverseId);
                intent.putExtra("qty", qtyEt.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        // load cards
        getStandardCards();

        // listener
        listener = new DeckAddAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                nameEt.setText(cardArrayList.get(position).getName());
                multiverseId = cardArrayList.get(position).getMultiverseid();
            }
        };

        // adapter
        adapter = new DeckAddAdapter(this.getApplicationContext(), cardArrayList, listener);

        // Layout Manager
        RecyclerView recyclerView = findViewById(R.id.rv_add_this_card);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        nameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    public void getStandardCards() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Adventures in the Forgotten Realms
                MtgSet afr = SetAPI.getSet("AFR");
                System.out.println("AFR loaded");
                List<Card> afrCards = afr.getCards();

                cardArrayList.addAll(afrCards);
                System.out.println("all AFR cards loaded");
            }
        }).start();
    }

    private void filter(String text) {
        ArrayList<io.magicthegathering.javasdk.resource.Card> filteredList = new ArrayList<>();

        for (io.magicthegathering.javasdk.resource.Card card : cardArrayList) {
            if (card.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(card);
            }
        }

        adapter.filterList(filteredList);
    }
}