package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import io.magicthegathering.javasdk.api.CardAPI;


public class DeckSocialFragment extends Fragment implements DeckSocialAdapter.ItemClickListener {

    private ArrayList<Deck> deckArrayList;
    private DeckSocialAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_deck_social, container, false);

        adapter = new DeckSocialAdapter(deckArrayList, this::onItemClick);

        EditText et = view.findViewById(R.id.et_searchbar_social);

        RecyclerView recyclerView = view.findViewById(R.id.rv_dsociallist);
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

        return view;
    }

    private void filter(String text) {
        ArrayList<Deck> filteredList = new ArrayList<>();

        for (Deck deck : deckArrayList) {
            if (deck.getDeckname().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(deck);
            }
        }

        adapter.filterList(filteredList);
    }

    @Override
    public void onItemClick(Deck deck) {
        Intent intent = new Intent(getActivity().getApplicationContext(), DeckDetailsSocialActivity.class);
        intent.putExtra("deckName", deck.getDeckname());
        intent.putExtra("username", deck.getUsername());
        startActivity(intent);
    }
}