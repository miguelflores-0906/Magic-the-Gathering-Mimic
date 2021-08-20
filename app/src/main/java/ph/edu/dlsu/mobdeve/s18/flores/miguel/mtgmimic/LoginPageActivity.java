package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.ActivityLoginPageBinding;

public class LoginPageActivity extends AppCompatActivity {

    ActivityLoginPageBinding binding;
    Button login;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginPageBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_login_page);

        login = findViewById(R.id.button_login);

        login.setOnClickListener(v ->
        {
            Intent intent = new Intent(this, MasterCardlistActivity.class);
            startActivity(intent);
        });
    }

}