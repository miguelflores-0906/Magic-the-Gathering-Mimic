package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.FragmentUsersDecksBinding;


public class UsersDecksFragment extends Fragment {

    private ArrayList<Deck> deckArrayList;
    private ArrayList<Deck> sample;
    private UserDecklistAdapter adapter;
    private FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_users_decks, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        String temp = firebaseAuth.getCurrentUser().getDisplayName();
        DeckDBDAO deckDBDAO = new DeckDBDAOImpl(getContext());

        sample = deckDBDAO.getUserDecks(temp);

        for (Deck d : sample) {
            System.out.println(d.getDeckname());
        }

        adapter = new UserDecklistAdapter(sample, this::onItemClick);

        RecyclerView recyclerView = view.findViewById(R.id.rv_userdecks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);
        return view;

    }

    public void onItemClick(Deck deck) {
        Intent intent = new Intent(getContext(), DeckDetailsActivity.class);
        intent.putExtra("username", deck.getUsername());
        intent.putExtra("deckname", deck.getDeckname());
        intent.putExtra("decklist", deck.getCards());
        startActivity(intent);
    }

}