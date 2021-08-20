package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.ActivityMasterCardlistBinding;

public class MasterCardlistActivity extends AppCompatActivity {

    private ActivityMasterCardlistBinding binding;
    private ArrayList<Card> cardArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* try adding these lines
        binding = ActivityMasterCardlistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); */
        
        //delete this line
        setContentView(R.layout.activity_master_cardlist);

        cardArrayList = CustomDataHelper.loadCards();

        MasterCardlistAdapter mclAdapter = new MasterCardlistAdapter(getApplicationContext(), cardArrayList);
        binding.rvCardlist.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvCardlist.setAdapter(mclAdapter);
    }
}
