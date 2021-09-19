package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.magicthegathering.javasdk.api.SetAPI;
import io.magicthegathering.javasdk.resource.Card;
import io.magicthegathering.javasdk.resource.MtgSet;

public class UserInvCardActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_card_details);


        // set the card details here

        // Image
        ImageView iv = findViewById(R.id.iv_master_card_art);

        // Text
        TextView cardName = findViewById(R.id.tv_details_name);
        TextView cardExp = findViewById(R.id.tv_details_expansion);
        TextView cardType = findViewById(R.id.tv_details_type);
        TextView cardText = findViewById(R.id.tv_card_text);
        TextView cardMana = findViewById(R.id.tv_card_manacost);

        int multiId = -1;

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            multiId = extras.getInt("multiverseId", -1);
        }

        int finalMultiId = multiId;
        new Thread(new Runnable() {
            @Override
            public void run() {
                // grab card
                Card card = CardAPI.getCard(finalMultiId);

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Loading Card Data",
                                Toast.LENGTH_SHORT).show();

                        cardName.setText(card.getName());
                        cardExp.setText(card.getSetName());
                        cardType.setText(card.getType());
                        cardText.setText(card.getText());
                        cardMana.setText(card.getManaCost());

                        Picasso.get().load(card.getImageUrl()).into(iv);
                    }
                });
            }
        }).start();

//        cardName.setText(name);
//        cardExp.setText(expansion);
//        cardType.setText(type);
//        cardText.setText(text);
//        cardMana.setText(mana);
//
//        Picasso.get().load(image).into(iv);

        // floating action button
        FloatingActionButton fab = findViewById(R.id.fab_card_details);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CardStatsActivity.class);
            startActivity(intent);
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
