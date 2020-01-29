package com.example.android.rotcare1;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private ViewPageAdapter adapter;
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    Button m_home, v_home;

    private DrawerLayout drawer;
    private FirebaseAuth fAuth;
    private TextView email;
    FirebaseUser CurrentUser;
    FirebaseAuth.AuthStateListener fAuthlistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        m_home = findViewById(R.id.Main_Member);
//        v_home = findViewById(R.id.Main_Volunteer);

//        final Toolbar toolbar = findViewById(R.id.main_toolbar);
//        setSupportActionBar(toolbar);
//        Member = findViewById(R.id.tab_member);
//        Volunteer = findViewById(R.id.tab_volunteer);
//        tabLayout = findViewById(R.id.tabs);
        tabLayout = findViewById(R.id.tablayout_id);
        viewPager = findViewById(R.id.viewpager_id);
        toolbar = findViewById(R.id.toolbar_id);
        adapter = new ViewPageAdapter(getSupportFragmentManager());
        adapter.AddFragment(new MemberFragment(), "Member");
        adapter.AddFragment(new DemoFragment(), "Volunteer");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        setSupportActionBar(toolbar);
        fAuth = FirebaseAuth.getInstance();
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
                startActivity(new Intent(MainActivity.this,Nav_HomeActivity.class));
                break;

            case R.id.nav_profile:
                startActivity(new Intent(MainActivity.this,Nav_ProfileActivity.class));
                break;

            case R.id.nav_reward:
                startActivity(new Intent(MainActivity.this,Nav_RewardActivity.class));
                break;

            case R.id.nav_message:
                startActivity(new Intent(MainActivity.this,Nav_MessageActivity.class));
                break;

            case R.id.nav_contacts:
                startActivity(new Intent(MainActivity.this,Nav_ContactsActivity.class));
                break;


            case R.id.nav_logout:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
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
