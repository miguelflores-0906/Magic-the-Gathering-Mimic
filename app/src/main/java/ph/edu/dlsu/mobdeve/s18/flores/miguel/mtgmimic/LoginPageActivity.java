package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic.databinding.ActivityLoginPageBinding;

public class LoginPageActivity extends AppCompatActivity {

    ActivityLoginPageBinding binding;
    Button login;
    EditText email;
    EditText password;
    TextView redirectReg;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        login = findViewById(R.id.button_login);
        email = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        fAuth = FirebaseAuth.getInstance();

        //goes to the main activity if user is still logged in
        if(fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(this, MainActivity.class));
        }

        login.setOnClickListener(v ->
        {
            String emailFB = email.getText().toString().trim();
            String passwordFB = password.getText().toString().trim();

            if(TextUtils.isEmpty(emailFB))
            {
                Toast.makeText(this, "Username must not be empty.", Toast.LENGTH_SHORT).show();
                return;
            }

            if(TextUtils.isEmpty(passwordFB))
            {
                Toast.makeText(this, "Password must not be empty.", Toast.LENGTH_SHORT).show();
                return;
            }

            fAuth.signInWithEmailAndPassword(emailFB, passwordFB).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(Task<AuthResult> task) { if(task.isSuccessful())
                {
                    Toast.makeText(LoginPageActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginPageActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(LoginPageActivity.this, "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
                }
            });


        });

        redirectReg = findViewById(R.id.tv_signuptext);

        redirectReg.setOnClickListener(v -> {
            Intent toReg = new Intent(this, RegisterActivity.class);
            startActivity(toReg);
        });
    }

    @Override
    public void onBackPressed()
    {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

}