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

import io.magicthegathering.javasdk.resource.Card;
import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.ActivityCardStatsBinding;
import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.ActivityDeckStatBinding;

public class DeckStatActivity extends AppCompatActivity {

    private ArrayList<Card> cardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_stat);


        BarChart typeChart = findViewById(R.id.barchart_typedist);
        BarChart colorChart = findViewById(R.id.barchart_colordist);
        BarChart cmcChart = findViewById(R.id.barchart_cmcdist);
        ArrayList<BarEntry> typeEntries = new ArrayList<>();
        ArrayList<BarEntry> cmcEntries = new ArrayList<>();
        ArrayList<BarEntry> colorEntries = new ArrayList<>();

        //pass the string in db
        //translate string back to arraylist of cards

        //graph of type

//        int artifactNum = 0;
//        int conspiracyNum = 0;
//        int creatureNum = 0;
//        int enchantmentNum = 0;
//        int instantNum = 0;
//        int landNum = 0;
//        int phenomenonNum = 0;
//        int planeNum = 0;
//        int planeswalkerNum = 0;
//        int schemeNum = 0;
//        int sorceryNum = 0;
//        int tribalNum = 0;
//        int vanguardNum = 0;
//
//        for(int i = 0; i < cardList.size(); i++)
//        {
//            if(cardList.get(i).getType().contains("Artifact"))
//            {
//                artifactNum++;
//            }
//            if(cardList.get(i).getType().contains("Conspiracy"))
//            {
//                conspiracyNum++;
//            }
//            if(cardList.get(i).getType().contains("Creature"))
//            {
//                creatureNum++;
//            }
//            if(cardList.get(i).getType().contains("Enchantment"))
//            {
//                enchantmentNum++;
//            }
//            if(cardList.get(i).getType().contains("Land"))
//            {
//                landNum++;
//            }
//            if(cardList.get(i).getType().contains("Phenomenon"))
//            {
//                phenomenonNum++;
//            }
//            if(cardList.get(i).getType().equals("Plane"))
//            {
//                planeNum++;
//            }
//            if(cardList.get(i).getType().contains("Planeswalker"))
//            {
//                planeswalkerNum++;
//            }
//            if(cardList.get(i).getType().contains("Scheme"))
//            {
//                schemeNum++;
//            }
//            if(cardList.get(i).getType().contains("Sorcery"))
//            {
//                sorceryNum++;
//            }
//            if(cardList.get(i).getType().contains("Tribal"))
//            {
//                tribalNum++;
//            }
//            if(cardList.get(i).getType().contains("Vanguard"))
//            {
//                vanguardNum++;
//            }
//        }
//
//
//
//        typeEntries.add(new BarEntry(1, artifactNum));
//        typeEntries.add(new BarEntry(2, conspiracyNum));
//        typeEntries.add(new BarEntry(3, creatureNum));
//        typeEntries.add(new BarEntry(4, enchantmentNum));
//        typeEntries.add(new BarEntry(5, instantNum));
//        typeEntries.add(new BarEntry(6, landNum));
//        typeEntries.add(new BarEntry(7, phenomenonNum));
//        typeEntries.add(new BarEntry(8, planeNum));
//        typeEntries.add(new BarEntry(9, planeswalkerNum));
//        typeEntries.add(new BarEntry(10, schemeNum));
//        typeEntries.add(new BarEntry(11, sorceryNum));
//        typeEntries.add(new BarEntry(12, tribalNum));
//        typeEntries.add(new BarEntry(13, vanguardNum));
//
//        String[] xAxisLables = new String[]{"Artifact", "Conspiracy", "Creature", "Enchantment",
//                "Instant", "Land", "Phenomenon", "Plane", "Planeswalker", "Scheme", "Sorcery", "Tribal", "Vanguard"};
//
//        typeChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLables));
//        typeChart.getXAxis().isAvoidFirstLastClippingEnabled();
//
//        BarDataSet typeDataSet = new BarDataSet(typeEntries, "Types");
//        typeDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
//        typeDataSet.setValueTextColor(Color.BLACK);
//        typeDataSet.setValueTextSize(10f);
//
//        BarData typeData = new BarData(typeDataSet);
//
//        typeChart.setData(typeData);
//        typeChart.getDescription().setText("Type Distribution");
//        typeChart.animateXY(2000, 2000);

        // TODO: Pie Graph of Color
        //graph of color
//            int redCount = 0;
//            int greenCount = 0;
//            int blackCount = 0;
//            int whiteCount = 0;
//            int blueCount = 0;
//            int uncoloredCount = 0;
//
//        for(int i = 0; i < cardList.size(); i++)
//        {
//            for(int j = 0; j < cardList.get(i).getColorIdentity().length; j++)
//            {
//                cardList.get(i).getColorIdentity();
//            }
//        }

        //graph of cmc
//        int count1 = 0;
//        int count2 = 0;
//        int count3 = 0;
//        int count4 = 0;
//        int count5 = 0;
//        int count6 = 0;
//        int count7 = 0;
//        int count8 = 0;
//        int count9 = 0;
//        int count10 = 0;
//
//        for(int i = 0; i < cardList.size();i++ )
//        {
//            switch((int) cardList.get(i).getCmc())
//            {
//                case 1: count1++; break;
//                case 2: count2++; break;
//                case 3: count3++; break;
//                case 4: count4++; break;
//                case 5: count5++; break;
//                case 6: count6++; break;
//                case 7: count7++; break;
//                case 8: count8++; break;
//                case 9: count9++; break;
//                case 10: count10++; break;
//                default: break;
//            }
//        }
//
//        cmcEntries.add(new BarEntry(1, count1));
//        cmcEntries.add(new BarEntry(2, count2));
//        cmcEntries.add(new BarEntry(3, count3));
//        cmcEntries.add(new BarEntry(4, count4));
//        cmcEntries.add(new BarEntry(5, count5));
//        cmcEntries.add(new BarEntry(6, count6));
//        cmcEntries.add(new BarEntry(7, count7));
//        cmcEntries.add(new BarEntry(8, count8));
//        cmcEntries.add(new BarEntry(9, count9));
//        cmcEntries.add(new BarEntry(10, count10));
//
//        BarDataSet cmcDataSet = new BarDataSet(typeEntries, "Types");
//        cmcDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
//        cmcDataSet.setValueTextColor(Color.BLACK);
//        cmcDataSet.setValueTextSize(10f);
//
//        BarData cmcData = new BarData(cmcDataSet);
//
//        typeChart.setData(cmcData);
//        typeChart.getDescription().setText("CMC Distribution");
//        typeChart.animateXY(2000, 2000);
    }
}