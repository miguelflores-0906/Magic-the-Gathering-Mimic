package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.magicthegathering.javasdk.resource.Card;

public class UserInventoryFragment extends Fragment implements MasterCardlistAdapter.ItemClickListener {

    private ArrayList<io.magicthegathering.javasdk.resource.Card> cardArrayList;
    private MasterCardlistAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_user_inv, container, false);

//        cardArrayList = CustomDataHelper.loadCards();

        adapter = new MasterCardlistAdapter(cardArrayList, this);

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


        return view;
    }

    @Override
    public void onItemClick(io.magicthegathering.javasdk.resource.Card card) {
        Intent intent = new Intent(getActivity().getApplicationContext(), CardDetailsActivity.class);
        intent.putExtra("cardName", card.getName());
        startActivity(intent);
    }

    private void filter(String text) {
        ArrayList<Card> filteredList = new ArrayList<>();

        for (io.magicthegathering.javasdk.resource.Card card : cardArrayList) {
            if (card.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(card);
            }
        }

        adapter.filterList(filteredList);
    }
}
