package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.SyncFailedException;
import java.util.ArrayList;
import java.util.WeakHashMap;

import io.magicthegathering.javasdk.resource.Card;
import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.ActivityCardStatsBinding;
import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.ActivityDeckStatBinding;

public class DeckStatActivity extends AppCompatActivity {
    private ArrayList<Card> cardArrayList;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_stat);

        String cardList = "";

        Bundle extras = getIntent().getExtras();

        if(extras != null)
        {
            cardList = extras.getString("cards");
        }

        cardArrayList = new ArrayList<>();

        ArrayList<Integer> multiverseList = new ArrayList<>();
        ArrayList<Integer> qtyList = new ArrayList<>();
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
            qtyList.add(Integer.parseInt(temp[1]));
        }

        /**
         * This next bit will run through the list and get each card and add
         * them to the arraylist
         * */
        System.out.println("Started processing cards");

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < multiverseList.size(); i++) {
                    for(int j = 0; j < qtyList.get(i); j++)
                    {
                        Card c = CardAPI.getCard(multiverseList.get(i));
                        System.out.println(c.getName());
                        allCards.add(c);
                    }
                }
                System.out.println("Done processing");
                System.out.println("These are all the cards that I found:");
                System.out.println("CHECK HERE");
                for (Card c : allCards) {
                    System.out.println(c.getName());
                }

                System.out.println("Stop check here");
                cardArrayList = allCards;

                for (Card c : cardArrayList) {
                    System.out.println("The card " + c.getName() + " is here.");
                }

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        //CMC Graph
                        BarChart cmcChart = findViewById(R.id.barchart_cmcdist);
                        ArrayList<BarEntry> cmcEntries = new ArrayList<>();

                        int count1 = 0;
                        int count2 = 0;
                        int count3 = 0;
                        int count4 = 0;
                        int count5 = 0;
                        int count6 = 0;
                        int count7 = 0;
                        int count8 = 0;
                        int count9 = 0;
                        int count10 = 0;

                        for(int i = 0; i < cardArrayList.size();i++ )
                        {
                            switch((int) cardArrayList.get(i).getCmc())
                            {
                                case 1: count1++; break;
                                case 2: count2++; break;
                                case 3: count3++; break;
                                case 4: count4++; break;
                                case 5: count5++; break;
                                case 6: count6++; break;
                                case 7: count7++; break;
                                case 8: count8++; break;
                                case 9: count9++; break;
                                case 10: count10++; break;
                                default: break;
                            }
                        }

                        cmcEntries.add(new BarEntry(0, count1));
                        cmcEntries.add(new BarEntry(1, count2));
                        cmcEntries.add(new BarEntry(2, count3));
                        cmcEntries.add(new BarEntry(3, count4));
                        cmcEntries.add(new BarEntry(4, count5));
                        cmcEntries.add(new BarEntry(5, count6));
                        cmcEntries.add(new BarEntry(6, count7));
                        cmcEntries.add(new BarEntry(7, count8));
                        cmcEntries.add(new BarEntry(8, count9));
                        cmcEntries.add(new BarEntry(9, count10));

                        BarDataSet cmcDataSet = new BarDataSet(cmcEntries, "Converted Mana Cost");
                        cmcDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                        cmcDataSet.setValueTextColor(Color.BLACK);
                        cmcDataSet.setValueTextSize(10f);

                        BarData cmcData = new BarData(cmcDataSet);

                        cmcChart.setData(cmcData);
                        cmcChart.getDescription().setText("CMC Distribution");
                        cmcChart.animateX(2000);

                        //Type Chart
                        PieChart typeChart = findViewById(R.id.piechart_typedist);

                        double artifactNum = 0;
                        double conspiracyNum = 0;
                        double creatureNum = 0;
                        double enchantmentNum = 0;
                        double instantNum = 0;
                        double landNum = 0;
                        double phenomenonNum = 0;
                        double planeNum = 0;
                        double planeswalkerNum = 0;
                        double schemeNum = 0;
                        double sorceryNum = 0;
                        double tribalNum = 0;
                        double vanguardNum = 0;

                        for(int i = 0; i < cardArrayList.size(); i++)
                        {
                            if(cardArrayList.get(i).getType().contains("Artifact"))
                            {
                                artifactNum++;
                            }
                            if(cardArrayList.get(i).getType().contains("Conspiracy"))
                            {
                                conspiracyNum++;
                            }
                            if(cardArrayList.get(i).getType().contains("Creature"))
                            {
                                creatureNum++;
                            }
                            if(cardArrayList.get(i).getType().contains("Enchantment"))
                            {
                                enchantmentNum++;
                            }
                            if(cardArrayList.get(i).getType().contains("Instant"))
                            {
                                instantNum++;
                            }
                            if(cardArrayList.get(i).getType().contains("Land"))
                            {
                                landNum++;
                            }
                            if(cardArrayList.get(i).getType().contains("Phenomenon"))
                            {
                                phenomenonNum++;
                            }
                            if(cardArrayList.get(i).getType().equals("Plane"))
                            {
                                planeNum++;
                            }
                            if(cardArrayList.get(i).getType().contains("Planeswalker"))
                            {
                                planeswalkerNum++;
                            }
                            if(cardArrayList.get(i).getType().contains("Scheme"))
                            {
                                schemeNum++;
                            }
                            if(cardArrayList.get(i).getType().contains("Sorcery"))
                            {
                                sorceryNum++;
                            }
                            if(cardArrayList.get(i).getType().contains("Tribal"))
                            {
                                tribalNum++;
                            }
                            if(cardArrayList.get(i).getType().contains("Vanguard"))
                            {
                                vanguardNum++;
                            }
                        }

                        System.out.println("TYPE COUNTING");
                        System.out.println("Artifact Number: " + artifactNum);
                        System.out.println("Conspiracy Number: " + conspiracyNum);
                        System.out.println("Creature Number: " + creatureNum);
                        System.out.println("Enchantment Number: " + enchantmentNum);
                        System.out.println("Instant Number: " + instantNum);
                        System.out.println("Land Number: " + landNum);
                        System.out.println("Phenomenon Number: " + phenomenonNum);
                        System.out.println("Plane Number: " + planeNum);
                        System.out.println("Planeswalker Number: " + planeswalkerNum);
                        System.out.println("Scheme Number: " + schemeNum);
                        System.out.println("Sorcery Number: " + sorceryNum);
                        System.out.println("Tribal Number: " + tribalNum);
                        System.out.println("Vanguard Number: " + vanguardNum);

                        ArrayList<PieEntry> TypeEntries = new ArrayList<>();

                        if(artifactNum > 0)
                        {
                            TypeEntries.add(new PieEntry((float) (artifactNum/cardArrayList.size()+1), "Artifacts"));
                        }
                        if(conspiracyNum > 0)
                        {
                            TypeEntries.add(new PieEntry((float) (conspiracyNum/cardArrayList.size()+1), "Conspiracy"));
                        }
                        if(creatureNum > 0)
                        {
                            TypeEntries.add(new PieEntry((float) (creatureNum/cardArrayList.size()+1), "Creature"));
                        }
                        if(enchantmentNum > 0)
                        {
                            TypeEntries.add(new PieEntry((float) (enchantmentNum/cardArrayList.size()+1), "Enchantment"));
                        }
                        if(instantNum > 0)
                        {
                            TypeEntries.add(new PieEntry((float) (enchantmentNum/cardArrayList.size()+1), "Instant"));
                        }
                        if(landNum > 0)
                        {
                            TypeEntries.add(new PieEntry((float) (landNum/cardArrayList.size()+1), "Land"));
                        }
                        if(phenomenonNum > 0)
                        {
                            TypeEntries.add(new PieEntry((float) (phenomenonNum/cardArrayList.size()+1), "Phenomenon"));
                        }
                        if(planeNum > 0)
                        {
                            TypeEntries.add(new PieEntry((float) (planeNum/cardArrayList.size()+1), "Plane"));
                        }
                        if(planeswalkerNum > 0)
                        {
                            TypeEntries.add(new PieEntry((float) (planeswalkerNum/cardArrayList.size()+1), "Planeswalker"));
                        }
                        if(schemeNum > 0)
                        {
                            TypeEntries.add(new PieEntry((float) (schemeNum/cardArrayList.size()+1), "Scheme"));
                        }
                        if(sorceryNum > 0)
                        {
                            TypeEntries.add(new PieEntry((float) (sorceryNum/cardArrayList.size()+1), "Sorcery"));
                        }
                        if(tribalNum > 0)
                        {
                            TypeEntries.add(new PieEntry((float) (tribalNum/cardArrayList.size()+1), "Tribal"));
                        }
                        if(vanguardNum > 0)
                        {
                            TypeEntries.add(new PieEntry((float) (vanguardNum/cardArrayList.size()+1), "Vanguard"));
                        }

                        ArrayList<Integer> colors = new ArrayList<>();
                        for(int color: ColorTemplate.MATERIAL_COLORS)
                        {
                            colors.add(color);
                        }

                        PieDataSet ds1 = new PieDataSet(TypeEntries, "Type Distribution in Deck");
                        ds1.setColors(colors);

                        PieData d1 = new PieData(ds1);

                        typeChart.setData(d1);
                        typeChart.setUsePercentValues(true);
                        typeChart.setEntryLabelColor(Color.BLACK);
                        typeChart.setCenterTextColor(Color.BLACK);
                        typeChart.invalidate();

                        //Color Distribution
                        BarChart colorDist = findViewById(R.id.barchart_colordist);
                        System.out.println("Adding Colors");
                        ArrayList<String> colorsDistArrayList = new ArrayList<>();
                        String[] arr;
                        int redCount = 0;
                        int greenCount = 0;
                        int blackCount = 0;
                        int whiteCount = 0;
                        int blueCount = 0;
                        int uncoloredCount = 0;

                        for(int i = 0; i < cardArrayList.size(); i++)
                        {
                            arr = cardArrayList.get(i).getColorIdentity();
                            for(int j = 0; j < arr.length; j++)
                            {
                                System.out.println(arr[j]);
                                colorsDistArrayList.add(arr[j]);
                            }
                        }

                        for(int j = 0; j < colorsDistArrayList.size(); j++)
                        {
                            if(colorsDistArrayList.get(j).equals("R"))
                            {
                                redCount++;
                            }
                            if(colorsDistArrayList.get(j).equals("G"))
                            {
                                greenCount++;
                            }
                            if(colorsDistArrayList.get(j).equals("B"))
                            {
                                blackCount++;
                            }
                            if(colorsDistArrayList.get(j).equals("W"))
                            {
                                whiteCount++;
                            }
                            if(colorsDistArrayList.get(j).equals("U"))
                            {
                                blueCount++;
                            }
                            if(colorsDistArrayList.get(j).equals("C"))
                            {
                                uncoloredCount++;
                            }

                            ArrayList<BarEntry> colorDistEntries = new ArrayList<>();
                            colorDistEntries.add(new BarEntry(0, redCount));
                            colorDistEntries.add(new BarEntry(1, greenCount));
                            colorDistEntries.add(new BarEntry(2, blackCount));
                            colorDistEntries.add(new BarEntry(3, whiteCount));
                            colorDistEntries.add(new BarEntry(4, blueCount));
                            colorDistEntries.add(new BarEntry(5, uncoloredCount));

                            String[] xAxisLables = new String[]{"Red", "Green", "Black", "White", "Blue", "Uncolored"};

                            colorDist.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLables));

                            BarDataSet colorDistDS = new BarDataSet(colorDistEntries, "Converted Mana Cost");
                            colorDistDS.setColors(ColorTemplate.MATERIAL_COLORS);
                            colorDistDS.setValueTextColor(Color.BLACK);
                            colorDistDS.setValueTextSize(10f);

                            BarData colorDistData = new BarData(colorDistDS);

                            colorDist.setData(colorDistData);
                            colorDist.getDescription().setText("Color Distribution");
                            colorDist.animateX(2000);
                        }
                    }
                });
            }
        }).start();


//        BarChart colorChart = findViewById(R.id.barchart_colordist);
//        ArrayList<BarEntry> typeEntries = new ArrayList<>();
//        ArrayList<BarEntry> colorEntries = new ArrayList<>();

        //pass the string in db
        //translate string back to arraylist of cards

        //graph of type


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
//
//
//        for(int i = 0; i < cardList.size(); i++)
//        {
//            for(int j = 0; j < cardList.get(i).getColorIdentity().length; j++)
//            {
//                cardList.get(i).getColorIdentity();
//            }
//        }
    }
}