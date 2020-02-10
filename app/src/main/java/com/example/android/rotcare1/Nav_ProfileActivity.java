package com.example.android.rotcare1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Nav_ProfileActivity extends AppCompatActivity {

    TextView pmail,pphone,poccu,padd;
    FirebaseAuth fAuth;
    DatabaseReference mDatabase;
    String Current_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav__profile);
        Toolbar toolbar = findViewById(R.id.toolbar10);
        setSupportActionBar(toolbar);

        pmail = findViewById(R.id.mail);
        pphone = findViewById(R.id.phone_no);
        poccu = findViewById(R.id.occupation);
        padd = findViewById(R.id.address);
        fAuth = FirebaseAuth.getInstance();
        Current_user = fAuth.getCurrentUser().getUid();


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(Current_user);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pmail.setText(dataSnapshot.getValue(User.class).getEmail());
                pphone.setText(dataSnapshot.getValue(User.class).getPhone());
                poccu.setText(dataSnapshot.getValue(User.class).getOccupation());
                padd.setText(dataSnapshot.getValue(User.class).getAddress());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
