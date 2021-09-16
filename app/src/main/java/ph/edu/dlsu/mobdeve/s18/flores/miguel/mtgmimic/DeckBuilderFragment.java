package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class DeckBuilderFragment extends Fragment {

    private Button btn_add;
    private ArrayList<BuilderCard> cardArrayList;
    private DeckBuilderAdapter adapter;
    private EditText et_deck_name;
    private FloatingActionButton fab_save;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_deck_builder, container, false);

        btn_add = view.findViewById(R.id.btn_deck_builder);

        btn_add.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), DeckAddCardActivity.class);
            startActivityForResult(intent, 1);
        });

        // TODO: FloatingActionButton on click (save)

        // populate list
        cardArrayList = new ArrayList<>();

        // adapter
        adapter = new DeckBuilderAdapter(cardArrayList, getContext());

        // Layout Manager
        RecyclerView rv = view.findViewById(R.id.rv_deck_builder);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String cardName = data.getStringExtra("name");
                int multiverseId = data.getIntExtra("multiverseId", -1);
                int qty = data.getIntExtra("qty", 0);
                cardArrayList.add(0, new BuilderCard(multiverseId, cardName, qty));
            }
        }
    }
}
