package com.example.android.rotcare1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class Nav_ContactsActivity extends AppCompatActivity {

    TextView n1,p1,e1,n2,p2,e2;
    ImageView i1,i2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav__contacts);

        Toolbar toolbar = findViewById(R.id.toolbar7);
        setSupportActionBar(toolbar);

        n1 = findViewById(R.id.aname);
        p1 = findViewById(R.id.apost);
        e1 = findViewById(R.id.amail);
        n2 = findViewById(R.id.aname2);
        p2 = findViewById(R.id.apost2);
        e2 = findViewById(R.id.amail2);
        i1 = findViewById(R.id.achal_pic);
        i2 = findViewById(R.id.pic);


        


    }
}
