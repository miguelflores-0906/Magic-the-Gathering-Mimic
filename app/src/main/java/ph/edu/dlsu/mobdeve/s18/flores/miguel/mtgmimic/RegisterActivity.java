package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    Button register;
    EditText username;
    EditText email;
    EditText password;
    TextView loginReg;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        username = findViewById(R.id.et_username);
        email = findViewById(R.id.et_emailreg);
        password = findViewById(R.id.et_passwordreg);
        fAuth = FirebaseAuth.getInstance();

        register = findViewById(R.id.button_register);
        register.setOnClickListener(v -> {
            String usernameFB = username.getText().toString().trim();
            String emailFB = email.getText().toString().trim();
            String passwordFB = password.getText().toString().trim();

            if(TextUtils.isEmpty(usernameFB))
            {
                Toast.makeText(this, "Username must not be empty.", Toast.LENGTH_SHORT).show();
                return;
            }

            if(TextUtils.isEmpty(emailFB))
            {
                Toast.makeText(this, "Email must not be empty.", Toast.LENGTH_SHORT).show();
                return;
            }

            if(TextUtils.isEmpty(passwordFB))
            {
                Toast.makeText(this, "Password must not be empty.", Toast.LENGTH_SHORT).show();
                return;
            }

            //Register user in Firebase
            fAuth.createUserWithEmailAndPassword(emailFB,passwordFB).addOnCompleteListener(task -> {
                if(task.isSuccessful())
                {
                    Toast.makeText(RegisterActivity.this, "Account Successfully Created!", Toast.LENGTH_SHORT).show();
                    UserDAO userDAO = new UserDAOImpl();
                    User user = new User();
                    user.setUsername(usernameFB);
                    user.setEmail(emailFB);
                    userDAO.addUser(user);
                    Intent reg = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(reg);
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        });

        //redirects user to login page through the Text View
        loginReg = findViewById(R.id.tv_logintext);
        loginReg.setOnClickListener(v -> {
            Intent loginRed = new Intent(this, LoginPageActivity.class);
            startActivity(loginRed);
        });
    }


    @Override
    public void onBackPressed()
    {

    }
}