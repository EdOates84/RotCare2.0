package com.example.android.rotcare1;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.annotation.NonNull;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class V_HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    private FirebaseAuth fAuth;
    private TextView email;
    FirebaseUser CurrentUser;
    FirebaseAuth.AuthStateListener fAuthlistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v__home);

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
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
                startActivity(new Intent(V_HomeActivity.this,Nav_HomeActivity.class));
                break;

            case R.id.nav_profile:
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
                startActivity(new Intent(V_HomeActivity.this,Nav_ProfileActivity.class));
                break;

            case R.id.nav_reward:
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
                startActivity(new Intent(V_HomeActivity.this,Nav_RewardActivity.class));
                break;

            case R.id.nav_message:
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
                startActivity(new Intent(V_HomeActivity.this,Nav_MessageActivity.class));
                break;

            case R.id.nav_contacts:
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
                startActivity(new Intent(V_HomeActivity.this,Nav_ContactsActivity.class));
                break;


            case R.id.nav_logout:

                startActivity(new Intent(V_HomeActivity.this, LoginActivity.class));
                fAuth.signOut();
                finish();
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
    @Override
    public void onBackPressed() {
        drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {

            Intent setIntent = new Intent(Intent.ACTION_MAIN);
            setIntent.addCategory(Intent.CATEGORY_HOME);
            setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(setIntent);
            finish();
        }
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
    protected void onStart() {
        super.onStart();
        fAuth.addAuthStateListener(fAuthlistener);
    }
}

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        switch (menuItem.getItemId()){
//
//            case R.id.nav_profile:
//                startActivity(new Intent(MainActivity.this, MyProfileActivity.class));
//                break;
//
//            case R.id.nav_help:
//                startActivity(new Intent(MainActivity.this, HelpActivity.class));
//                break;
//
////            case R.id.nav_settings:
////                Toast.makeText(MainActivity.this, "Settings Selected", Toast.LENGTH_SHORT).show();
////                break;
//
//            case R.id.nav_signout:
//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
//                auth.signOut();
//                finish();
//                break;
//        }
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//    }
