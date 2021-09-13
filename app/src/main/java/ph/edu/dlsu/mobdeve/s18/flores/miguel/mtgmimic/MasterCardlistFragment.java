package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.FragmentMasterCardlistBinding;

public class MasterCardlistFragment extends Fragment implements MasterCardlistAdapter.ItemClickListener {

    private FragmentMasterCardlistBinding binding;
    private ArrayList<Card> cardArrayList;
    private MasterCardlistAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_master_cardlist, container, false);

        CardDAO cardDAO = new CardDAOImpl(getContext());
        adapter = new MasterCardlistAdapter(getContext(), cardDAO.getCards());

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

        return view;
    }

    private void filter(String text) {
        ArrayList<Card> filteredList = new ArrayList<>();

        for (Card card : cardArrayList) {
            if (card.getCardName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(card);
            }
        }

        adapter.filterList(filteredList);
    }

    @Override
    public void onItemClick(Card card) {
        Intent intent = new Intent(getActivity().getApplicationContext(), CardDetailsActivity.class);
        intent.putExtra("cardName", card.getCardName());
        intent.putExtra("cardExp", card.getSet());
        intent.putExtra("cardType", card.getType());
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
