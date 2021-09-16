package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class CardDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_card_details);


        // set the card details here
        // Text
        TextView cardName = findViewById(R.id.tv_details_name);
        TextView cardExp = findViewById(R.id.tv_details_expansion);
        TextView cardType = findViewById(R.id.tv_details_type);

        String name = "temp_name";
        String expansion = "temp_exp";
        String type = "temp_type";

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            name = extras.getString("cardName");
        }

        cardName.setText(name);
        cardExp.setText(expansion);
        cardType.setText(type);

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