package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

// going to make this the game tools fragment instead, not going to rename it though

public class LifeCounterFragment extends Fragment {

    private Button add_life;
    private Button sub_life;
    private Button add_pois;
    private Button sub_pois;
    private FloatingActionButton fab_coin;
    private FloatingActionButton fab_dice;
    private TextView life;
    private TextView poison;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_life_counter, container, false);

        add_life = view.findViewById(R.id.btn_add_life);
        sub_life = view.findViewById(R.id.btn_sub_life);
        add_pois = view.findViewById(R.id.btn_add_pois);
        sub_pois = view.findViewById(R.id.btn_sub_pois);
        life = view.findViewById(R.id.tv_ctrL);
        poison = view.findViewById(R.id.tv_ctrP);
        fab_coin = view.findViewById(R.id.fab_coin);
        fab_dice = view.findViewById(R.id.fab_dice);

        add_life.setOnClickListener(v -> {
            addLife();
        });

        sub_life.setOnClickListener(v -> {
            subLife();
        });

        add_pois.setOnClickListener(v -> {
            addPois();
        });

        sub_pois.setOnClickListener(v -> {
            subPois();
        });

        fab_coin.setOnClickListener(v -> {
            String result = coinToss();

            Toast.makeText(getContext(),
                    "You toss a coin and get " + result,
                    Toast.LENGTH_LONG).show();
        });

        fab_dice.setOnClickListener(v -> {
            String result = dieRoll();

            Toast.makeText(getContext(),
                    "You roll a 6-sided die and get " + result,
                    Toast.LENGTH_LONG).show();
        });

        return view;
    }

    private void addLife() {
        int curr = Integer.parseInt(life.getText().toString());
        curr++;
        life.setText(Integer.toString(curr));
    }

    private void subLife() {
        int curr = Integer.parseInt(life.getText().toString());
        curr--;
        life.setText(Integer.toString(curr));
    }

    private void addPois() {
        int curr = Integer.parseInt(poison.getText().toString());
        curr++;
        poison.setText(Integer.toString(curr));
    }

    private void subPois() {
        int curr = Integer.parseInt(poison.getText().toString());
        curr--;
        poison.setText(Integer.toString(curr));
    }

    private String coinToss() {
        String[] bank = {"HEADS", "TAILS"};

        return bank[new Random().nextInt(2)];
    }

    private String dieRoll() {
        return Integer.toString(new Random().nextInt(6) + 1);
    }
}
