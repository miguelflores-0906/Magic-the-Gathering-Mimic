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

import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.ActivityAddCardsBinding;

public class AddCardsActivity extends AppCompatActivity implements MasterCardlistAdapter.ItemClickListener {
    private ActivityAddCardsBinding binding;
    private ArrayList<Card> cardArrayList;
    private MasterCardlistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCardsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Recycler View

        cardArrayList = CustomDataHelper.loadCards();
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
    public void onItemClick(Card card) {
        Toast.makeText(getApplicationContext(),
                "You have added " + card.getCardName() + " to your deck",
                Toast.LENGTH_LONG).show();
    }
}
