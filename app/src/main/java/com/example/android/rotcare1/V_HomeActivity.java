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

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class V_HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v__home);

        fAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nev_view);
        navigationView.setNavigationItemSelectedListener(this);
//        updateNavHeader();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){

            case R.id.nav_home:
                startActivity(new Intent(V_HomeActivity.this,Nav_HomeActivity.class));
                break;

            case R.id.nav_profile:
                startActivity(new Intent(V_HomeActivity.this,Nav_ProfileActivity.class));
                break;

            case R.id.nav_reward:
                startActivity(new Intent(V_HomeActivity.this,Nav_RewardActivity.class));
                break;

            case R.id.nav_message:
                startActivity(new Intent(V_HomeActivity.this,Nav_MessageActivity.class));
                break;

            case R.id.nav_contacts:
                startActivity(new Intent(V_HomeActivity.this,Nav_ContactsActivity.class));
                break;


            case R.id.nav_logout:
                startActivity(new Intent(V_HomeActivity.this, LoginActivity.class));
                fAuth.signOut();
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
        finish();
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
