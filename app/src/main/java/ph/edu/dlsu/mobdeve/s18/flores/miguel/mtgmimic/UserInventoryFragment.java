package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Objects;

import io.magicthegathering.javasdk.resource.Card;

import static android.app.Activity.RESULT_OK;

public class UserInventoryFragment extends Fragment implements UserInvAdapter.ItemClickListener {

    private ArrayList<io.magicthegathering.javasdk.resource.Card> cardArrayList;
    private ArrayList<Card> allMagicCards = new ArrayList<>();
    private UserInvAdapter adapter;
    private FloatingActionButton fab_add;
    private ArrayList<BuilderCard> builderCards;
    private UserInvDBDAO userInvDBDAO;
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_user_inv, container, false);

        userInvDBDAO = new UserInvDBDAOImpl(getActivity().getApplicationContext());

        cardArrayList = new ArrayList<>();

        builderCards = new ArrayList<>();

        adapter = new UserInvAdapter(builderCards, this);

        if (userInvDBDAO.getUserInv(fAuth.getCurrentUser().getDisplayName()) != null) {
            builderCards = destringifyCards(userInvDBDAO.getUserInv(fAuth.getCurrentUser().getDisplayName()));
            adapter.notifyDataSetChanged();
        }



        EditText et = view.findViewById(R.id.et_user_inv);

        RecyclerView recyclerView = view.findViewById(R.id.rv_userlist);
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

        fab_add = view.findViewById(R.id.fab_inv_add);

        fab_add.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), DeckAddCardActivity.class);
            startActivityForResult(intent, 1);
        });


        return view;
    }

    private void filter(String text) {
        ArrayList<BuilderCard> filteredList = new ArrayList<>();

        for (BuilderCard bCard : builderCards) {
            if (bCard.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(bCard);
            }
        }

        adapter.filterList(filteredList);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                System.out.println("Got a response!");
                System.out.println(data.getStringExtra("name") + "\n"
                        + data.getIntExtra("multiverseId", -1) + "\n"
                        + data.getIntExtra("qty", 0) + "\n");
                String cardName = data.getStringExtra("name");
                int multiverseId = data.getIntExtra("multiverseId", -1);
                int qty = data.getIntExtra("qty", 0);

                boolean alreadyHere = false;
                BuilderCard existing = new BuilderCard();

                for (BuilderCard card : builderCards) {
                    if (card.getName().equalsIgnoreCase(cardName) ||
                        card.getMultiverseId() == multiverseId) {
                        alreadyHere = true;
                        existing = card;
                    }
                }

                if (alreadyHere) {
                    existing.addQty(qty);
                    adapter.notifyItemChanged(builderCards.indexOf(existing));
                }
                else {
                    builderCards.add(0, new BuilderCard(multiverseId, cardName, qty));
                    adapter.notifyItemInserted(0);
                }

                // update in database

                // there is inv already
                if (!(userInvDBDAO.getUserInv(fAuth.getCurrentUser().getDisplayName()) == null)) {
                    userInvDBDAO.updateUserInv(fAuth.getCurrentUser().getDisplayName(), stringifyCards(builderCards));
                    System.out.println("Updated user inventory");
                }
                else {
                    userInvDBDAO.addUserInv(fAuth.getCurrentUser().getDisplayName(), stringifyCards(builderCards));
                    System.out.println("Added user inventory");
                }
            }
        }
    }

    @Override
    public void onItemClick(BuilderCard card) {
        Intent intent = new Intent(getActivity().getApplicationContext(), UserInvCardActivity.class);
        intent.putExtra("multiverseId", card.getMultiverseId());
        startActivity(intent);
        System.out.println(card.toString());
    }

    private String stringifyCards(ArrayList<BuilderCard> cardList) {
        StringBuilder sb = new StringBuilder();

        for (BuilderCard card : cardList) {
            sb.append(card.getMultiverseId());
            sb.append("-");
            sb.append(card.getQty());
            sb.append(",");
        }

        return sb.toString();
    }

    private ArrayList<BuilderCard> destringifyCards(String s) {
        ArrayList<BuilderCard> allCards = new ArrayList<>();

        String[] cardAndQty = s.split(",");

        String[] temp = new String[2];

        for (String string : cardAndQty) {
            temp = string.split("-");
            allCards.add(new BuilderCard(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
        }

        return allCards;
    }
}
