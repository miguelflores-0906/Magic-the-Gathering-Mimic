package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.ActivityCardStatsBinding;
import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.ActivityDeckStatBinding;

public class DeckStatActivity extends AppCompatActivity {

    private ArrayList<Card> cardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_stat);

        TextView manacost = findViewById(R.id.tv_manacost);

//        String name = "temp_name";
//        String dname = "temp_dname";
//        Bundle extras = getIntent().getExtras();
//
//        if (extras != null) {
//            name = extras.getString("username");
//            dname = extras.getString("deckname");
////            cardList = (ArrayList<Card>) extras.get("decklist");
//        }

//        deck.setUsername(name);
//        deck.setDeckname(dname);
        cardList = CustomDataHelper.loadCards();

        //for bar graph of types
        int artifactNum = 0;
        int conspiracyNum = 0;
        int creatureNum = 0;
        int enchantmentNum = 0;
        int instantNum = 0;
        int landNum = 0;
        int phenomenonNum = 0;
        int planeNum = 0;
        int planeswalkerNum = 0;
        int schemeNum = 0;
        int sorceryNum = 0;
        int tribalNum = 0;
        int vanguardNum = 0;

        BarChart typeChart = findViewById(R.id.barchart_typedist);
        ArrayList<BarEntry> typeEntries = new ArrayList<>();

        for(int i = 0; i < cardList.size(); i++)
        {
            if(cardList.get(i).getType().equals("Artifact"))
            {
                artifactNum++;
            }
            if(cardList.get(i).getType().equals("Conspiracy"))
            {
                conspiracyNum++;
            }
            if(cardList.get(i).getType().equals("Creature"))
            {
                creatureNum++;
            }
            if(cardList.get(i).getType().equals("Enchantment"))
            {
                enchantmentNum++;
            }
            if(cardList.get(i).getType().equals("Land"))
            {
                landNum++;
            }
            if(cardList.get(i).getType().equals("Phenomenon"))
            {
                phenomenonNum++;
            }
            if(cardList.get(i).getType().equals("Planeswalker"))
            {
                planeswalkerNum++;
            }
            if(cardList.get(i).getType().equals("Scheme"))
            {
                schemeNum++;
            }
            if(cardList.get(i).getType().equals("Sorcery"))
            {
                sorceryNum++;
            }
            if(cardList.get(i).getType().equals("Tribal"))
            {
                tribalNum++;
            }
            if(cardList.get(i).getType().equals("Vanguard"))
            {
                vanguardNum++;
            }
        }



        typeEntries.add(new BarEntry(1, artifactNum));
        typeEntries.add(new BarEntry(2, conspiracyNum));
        typeEntries.add(new BarEntry(3, creatureNum));
        typeEntries.add(new BarEntry(4, enchantmentNum));
        typeEntries.add(new BarEntry(5, instantNum));
        typeEntries.add(new BarEntry(6, landNum));
        typeEntries.add(new BarEntry(7, phenomenonNum));
        typeEntries.add(new BarEntry(8, planeNum));
        typeEntries.add(new BarEntry(9, planeswalkerNum));
        typeEntries.add(new BarEntry(10, schemeNum));
        typeEntries.add(new BarEntry(11, sorceryNum));
        typeEntries.add(new BarEntry(12, tribalNum));
        typeEntries.add(new BarEntry(13, vanguardNum));

        String[] xAxisLables = new String[]{"Artifact", "Conspiracy", "Creature", "Enchantment",
        "Instant", "Land", "Phenomenon", "Plane", "Planeswalker", "Scheme", "Sorcery", "Tribal", "Vanguard"};

        typeChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLables));
        typeChart.getXAxis().isAvoidFirstLastClippingEnabled();

        BarDataSet typeDataSet = new BarDataSet(typeEntries, "Types");
        typeDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        typeDataSet.setValueTextColor(Color.BLACK);
        typeDataSet.setValueTextSize(10f);

        BarData typeData = new BarData(typeDataSet);

        typeChart.setData(typeData);
        typeChart.getDescription().setText("Type Distribution");
        typeChart.animateXY(2000, 2000);

    }
}