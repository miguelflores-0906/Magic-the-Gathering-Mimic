package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.ActivityCardStatsBinding;

public class CardStatsActivity extends AppCompatActivity {

    private ArrayList<Stats> statsList;
    private CardStatsAdapter adapter;
    private ActivityCardStatsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        statsList = StatsHelper.loadStats();
        adapter = new CardStatsAdapter(statsList);

        binding = ActivityCardStatsBinding.inflate(getLayoutInflater());

        binding.rvCardstats.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvCardstats.setAdapter(adapter);

        setContentView(R.layout.activity_card_stats);
    }
}