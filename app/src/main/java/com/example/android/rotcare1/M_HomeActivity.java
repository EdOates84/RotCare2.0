package com.example.android.rotcare1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.TextValueSanitizer;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class M_HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private FirebaseAuth fAuth;
    private TextView email;
    FirebaseUser CurrentUser;
    FirebaseAuth.AuthStateListener fAuthlistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m__home);

        fAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        CurrentUser = fAuth.getCurrentUser();
        fAuthlistener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        };

        NavigationView navigationView = findViewById(R.id.nev_view);
        navigationView.setNavigationItemSelectedListener(this);
        updateNavHeader();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){

            case R.id.nav_home:
                startActivity(new Intent(M_HomeActivity.this,Nav_HomeActivity.class));
                break;

            case R.id.nav_profile:
                startActivity(new Intent(M_HomeActivity.this,Nav_ProfileActivity.class));
                break;

            case R.id.nav_reward:
                startActivity(new Intent(M_HomeActivity.this,Nav_RewardActivity.class));
                break;

            case R.id.nav_message:
                startActivity(new Intent(M_HomeActivity.this,Nav_MessageActivity.class));
                break;

            case R.id.nav_contacts:
                startActivity(new Intent(M_HomeActivity.this,Nav_ContactsActivity.class));
                break;


            case R.id.nav_logout:
                startActivity(new Intent(M_HomeActivity.this, LoginActivity.class));
                fAuth.signOut();
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    public void updateNavHeader() {

        NavigationView navigationView = findViewById(R.id.nev_view);
        View headerView = navigationView.getHeaderView(0);
//        name =  headerView.findViewById(R.id.profile_name);
        if (CurrentUser != null) {
            email = headerView.findViewById(R.id.profile_email);
//        name.setText(CurrentUser.getDisplayName());
            email.setText(CurrentUser.getEmail());
        }

    }
    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
        finish();
    }
    @Override
    protected void onStart() {
        super.onStart();
        fAuth.addAuthStateListener(fAuthlistener);
    }

}