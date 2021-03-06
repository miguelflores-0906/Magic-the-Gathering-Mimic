package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
    private ArrayList<io.magicthegathering.javasdk.resource.Card> cardArrayList;
    private MasterCardlistAdapter adapter;
    private List<io.magicthegathering.javasdk.resource.Card> cardList;
    private Handler mHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_master_cardlist, container, false);

        cardArrayList = new ArrayList<>();

        Toast.makeText(getActivity().getApplicationContext(),
                "Please wait for cards to load",
                Toast.LENGTH_SHORT).show();
        // call API to get all standard 2022 cards
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Adventures in the Forgotten Realms

                MtgSet afr = new MtgSet();
                afr = SetAPI.getSet("AFR");
                System.out.println("AFR loaded");

                cardArrayList = new ArrayList<>(afr.getCards());
                System.out.println("all AFR cards loaded");

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateList(cardArrayList);
                    }
                });
            }
        }).start();

        System.out.println("this is outside");

        adapter = new MasterCardlistAdapter(cardArrayList, this::onItemClick);

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
        Intent intent = new Intent(getContext(), CardDetailsActivity.class);
        intent.putExtra("cardName", card.getName());
        intent.putExtra("expansion", card.getSetName());
        intent.putExtra("type", card.getType());
        intent.putExtra("text", card.getText());
        intent.putExtra("imageUrl", card.getImageUrl());
        intent.putExtra("manaCost", card.getManaCost());
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

                cardArrayList.addAll(afrCards);
                System.out.println("all AFR cards loaded");

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateList(cardArrayList);
                    }
                });
            }
        }).start();
    }
}
