package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Random;

public class GameToolsFragment extends Fragment {

    private Button life_counter;
    private Button coin_toss;
    private Button dice_roll;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_game_tools, container, false);

        // TODO: Life counter, switch to life counter fragment
        life_counter = view.findViewById(R.id.btn_counter);

        life_counter.setOnClickListener(v -> {
            FragmentTransaction ft = getParentFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, new LifeCounterFragment());
            ft.commit();
        });

        // coin toss, toast should be just fine
        coin_toss = view.findViewById(R.id.btn_coin);

        coin_toss.setOnClickListener(v -> {
            String result = coinToss();

            Toast.makeText(getContext(),
                    "You toss a coin and get " + result,
                    Toast.LENGTH_LONG).show();
        });

        // dice roll, toast for now, we could make a new fragment for final app
        dice_roll = view.findViewById(R.id.btn_dice);

        dice_roll.setOnClickListener(v -> {
            String result = dieRoll();

            Toast.makeText(getContext(),
                    "You roll a 6-sided die and get " + result,
                    Toast.LENGTH_LONG).show();
        });

        return view;
    }

    private String coinToss() {
        String[] bank = {"HEADS", "TAILS"};

        return bank[new Random().nextInt(2)];
    }

    private String dieRoll() {
        return Integer.toString(new Random().nextInt(6) + 1);
    }
}
