package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.magicthegathering.javasdk.api.CardAPI;
import io.magicthegathering.javasdk.api.SetAPI;
import io.magicthegathering.javasdk.resource.Card;
import io.magicthegathering.javasdk.resource.MtgSet;
import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.FragmentMasterCardlistBinding;


public class MasterCardlistFragment extends Fragment implements MasterCardlistAdapter.ItemClickListener {

    private FragmentMasterCardlistBinding binding;
    private ArrayList<io.magicthegathering.javasdk.resource.Card> cardArrayList = new ArrayList<>();
    private MasterCardlistAdapter adapter;
    private List<io.magicthegathering.javasdk.resource.Card> cardList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_master_cardlist, container, false);

        // call API to get all standard 2022 cards
        if (cardArrayList.isEmpty()) {
            getStandardCards();
        }
        
        if (cardArrayList.isEmpty()) {
            System.out.println("it no work");
        }

        adapter = new MasterCardlistAdapter(getContext(), cardArrayList);

        if (!cardArrayList.isEmpty()) {
            adapter.initialListUpdate();
        }

        EditText et = view.findViewById(R.id.et_master);

        RecyclerView recyclerView = view.findViewById(R.id.rv_cardlist_frag);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);



        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

//        try {
//            magicCards = CardAPI.getAllCards();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            for (io.magicthegathering.javasdk.resource.Card card : magicCards) {
//                System.out.println(card.getName());
//            }
//        }

        return view;
    }

    private void filter(String text) {
        ArrayList<io.magicthegathering.javasdk.resource.Card> filteredList = new ArrayList<>();

        for (io.magicthegathering.javasdk.resource.Card card : cardArrayList) {
            if (card.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(card);
            }
        }

        adapter.filterList(filteredList);
    }

    @Override
    public void onItemClick(Card card) {
        Intent intent = new Intent(getActivity().getApplicationContext(), CardDetailsActivity.class);
        intent.putExtra("cardName", card.getName());
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void getStandardCards() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                // Adventures in the Forgotten Realms
                MtgSet afr = SetAPI.getSet("AFR");
                System.out.println("AFR loaded");
                List<Card> afrCards = afr.getCards();

                System.out.println("all AFR cards loaded");
                cardArrayList.addAll(afrCards);

                System.out.println("Loaded all cards to Activity");
            }
        }).start();

        if (!cardArrayList.isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(),
                    "All cards from AFR have loaded",
                    Toast.LENGTH_LONG).show();
        }
    }
}
