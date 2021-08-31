package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class DeckSocialFragment extends Fragment {

    private ArrayList<Deck> deckArrayList;
    private DeckSocialAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_deck_social, container, false);

        deckArrayList = DeckHelper.loadDecks();

        adapter = new DeckSocialAdapter(deckArrayList);

        RecyclerView recyclerView = view.findViewById(R.id.rv_dsociallist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }
}