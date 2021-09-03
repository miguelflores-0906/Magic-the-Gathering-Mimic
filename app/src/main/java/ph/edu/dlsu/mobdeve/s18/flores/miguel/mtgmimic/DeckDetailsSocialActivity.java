package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DeckDetailsSocialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_details_social);

        TextView deckName = findViewById(R.id.tv_decksocialname);

        String name = "temp_name";

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            name = extras.getString("deckName");
        }

        deckName.setText(name);
    }
}