package com.example.android.rotcare1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class Nav_ContactsActivity extends AppCompatActivity {

    TextView n1,p1,e1,n2,p2,e2;
    ImageView i1,i2;
    DatabaseReference mDatabase;

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


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Contact_info").child("1");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                n1.setText(dataSnapshot.child("Name").getValue().toString());
                p1.setText(dataSnapshot.child("Post").getValue().toString());
                e1.setText(dataSnapshot.child("mail").getValue().toString());
                Picasso.get().load(dataSnapshot.child("image").getValue().toString()).into(i1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Contact_info").child("2");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                n2.setText(dataSnapshot.child("Name").getValue().toString());
                p2.setText(dataSnapshot.child("Post").getValue().toString());
                e2.setText(dataSnapshot.child("mail").getValue().toString());
                Picasso.get().load(dataSnapshot.child("image").getValue().toString()).into(i2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
