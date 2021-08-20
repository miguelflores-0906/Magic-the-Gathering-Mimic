package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Button logoutBtn;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Button button = findViewById(R.id.btn_test);

//        this is just a test for when I figure out how to use the json file later on kek
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button


            }
        });

        logoutBtn = findViewById(R.id.btn_logout);

        logoutBtn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent logout = new Intent(getApplicationContext(), LoginPageActivity.class);
            startActivity(logout);
        });
    }

    @Override
    public void onBackPressed()
    {

    }
}