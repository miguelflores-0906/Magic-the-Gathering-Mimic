package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.ActivityCardStatsBinding;
import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.ActivityDeckStatBinding;

public class DeckStatActivity extends AppCompatActivity {

    private ArrayList<Stats> statsList;
    private CardStatsAdapter adapter;
    private ActivityDeckStatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        statsList = StatsHelper.loadStats();
        adapter = new CardStatsAdapter(statsList);

        binding = ActivityDeckStatBinding.inflate(getLayoutInflater());
        binding.rvDeckstats.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        binding.rvDeckstats.setAdapter(adapter);

        setContentView(binding.getRoot());
    }
}