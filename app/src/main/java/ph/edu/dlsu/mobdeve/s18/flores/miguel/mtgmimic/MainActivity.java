package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.tb_master);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_master_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new MasterCardlistFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_master);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_master:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MasterCardlistFragment()).commit();
                break;
            case R.id.nav_inv:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new UserInventoryFragment()).commit();
                break;
            case R.id.nav_user_deck:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TestFragment()).commit();
                break;
            case R.id.nav_deck_social:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TestFragment2()).commit();
                break;
            case R.id.nav_tools:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LifeCounterFragment()).commit();
                break;

//                TODO: IMPLEMENT LOGOUT HERE (just put it inside the case, you can remove the toast if you want)
            case R.id.menu_logout:
                firebaseAuth.signOut();
                Toast.makeText(getApplicationContext(),
                        "You have successfully logged out",
                        Toast.LENGTH_SHORT).show();
                Intent logout = new Intent(this, LoginPageActivity.class);
                startActivity(logout);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }
}