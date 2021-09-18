package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.ActivityCardStatsBinding;

public class CardStatsActivity extends AppCompatActivity {

    private CardStatsAdapter adapter;
    private ActivityCardStatsBinding binding;
    private PieChart percentInv;
    private PieChart percentDeck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_stats);

        percentInv = findViewById(R.id.pc_inventorypercent);
        percentDeck = findViewById(R.id.pc_percentalldecks);

        ArrayList<PieEntry> percentInvEntries = new ArrayList<>();
        percentInvEntries.add(new PieEntry(0.1f, "Selected Card"));
        percentInvEntries.add(new PieEntry(0.9f, "Rest of your Cards your Inventory"));

        ArrayList<PieEntry> percentDeckEntries = new ArrayList<>();
        percentDeckEntries.add(new PieEntry(0.1f, "Selected Card"));
        percentDeckEntries.add(new PieEntry(0.9f, "Rest of the Cards in all Decks"));

        ArrayList<Integer> colors = new ArrayList<>();
        for(int color: ColorTemplate.MATERIAL_COLORS)
        {
            colors.add(color);
        }

        PieDataSet ds1 = new PieDataSet(percentInvEntries, "Cards in your Decks");
        PieDataSet ds2 = new PieDataSet(percentDeckEntries, "Cards in all Decks");
        ds1.setColors(colors);
        ds2.setColors(colors);

        PieData d1 = new PieData(ds1);
        PieData d2 = new PieData(ds2);

        percentInv.setData(d1);
        percentDeck.setData(d2);

        percentInv.invalidate();
        percentDeck.invalidate();

    }

    private void setUpCharts()
    {
        percentInv.setDrawHoleEnabled(true);
        percentDeck.setDrawHoleEnabled(true);
        percentInv.setCenterText("Percentage of Card In Your Decks");
        percentDeck.setCenterText("Percentage of Cards in All Decks");
        percentInv.setCenterTextColor(Color.BLACK);
        percentDeck.setCenterTextColor(Color.BLACK);
        percentInv.getDescription().setEnabled(false);
        percentDeck.getDescription().setEnabled(false);
        percentInv.setUsePercentValues(true);
        percentDeck.setUsePercentValues(true);
        percentInv.setEntryLabelColor(Color.BLACK);
        percentDeck.setEntryLabelColor(Color.BLACK);

        Legend l1 = percentInv.getLegend();
        l1.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l1.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l1.setDrawInside(false);
        l1.setEnabled(true);

        Legend l2 = percentDeck.getLegend();
        l2.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l2.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l2.setDrawInside(false);
        l2.setEnabled(true);
    }

}