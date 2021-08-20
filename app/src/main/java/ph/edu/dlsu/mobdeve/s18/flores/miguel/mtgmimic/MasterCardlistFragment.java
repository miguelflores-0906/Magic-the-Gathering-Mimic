package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.FragmentMasterCardlistBinding;

public class MasterCardlistFragment extends Fragment {

    private FragmentMasterCardlistBinding binding;
    private ArrayList<Card> cardArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_master_cardlist, container, false);

        cardArrayList = CustomDataHelper.loadCards();

        RecyclerView recyclerView = view.findViewById(R.id.rv_cardlist_frag);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new MasterCardlistAdapter(cardArrayList));

        return view;
    }

}
