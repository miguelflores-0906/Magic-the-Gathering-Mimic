package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class CardDetailsActivity extends AppCompatActivity {

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

        String name = "temp_name";
        String expansion = "temp_exp";
        String type = "temp_type";
        String text = "temp_text";
        String image = "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=527314&type=card";
        String mana = "temp_mana";

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            name = extras.getString("cardName");
            expansion = extras.getString("expansion");
            type = extras.getString("type");
            text = extras.getString("text");
            image = extras.getString("imageUrl");
            mana = extras.getString("manaCost");
        }

        cardName.setText(name);
        cardExp.setText(expansion);
        cardType.setText(type);
        cardText.setText(text);
        cardMana.setText(mana);

        Picasso.get().load(image).into(iv);

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