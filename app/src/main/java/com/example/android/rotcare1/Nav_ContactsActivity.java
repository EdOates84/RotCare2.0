package com.example.android.rotcare1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
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

    TextView n1, p1, e1, n2, p2, e2, n3, p3, e3;
    ImageView i1, i2, i3;
    DatabaseReference mDatabase;
    String E1, E2, E3;

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
        n3 = findViewById(R.id.aname3);
        p3 = findViewById(R.id.apost3);
        e3 = findViewById(R.id.amail3);
        i3 = findViewById(R.id.pic2);

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please Wait...");
        dialog.show();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Contact_info").child("1");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                n1.setText(dataSnapshot.child("Name").getValue().toString());
                p1.setText(dataSnapshot.child("Post").getValue().toString());
                E1 = dataSnapshot.child("mail").getValue().toString();
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
                E2 = dataSnapshot.child("mail").getValue().toString();
                e2.setText(dataSnapshot.child("mail").getValue().toString());
                Picasso.get().load(dataSnapshot.child("image").getValue().toString()).into(i2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Contact_info").child("3");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                n3.setText(dataSnapshot.child("Name").getValue().toString());
                p3.setText(dataSnapshot.child("Post").getValue().toString());
                E3 = dataSnapshot.child("mail").getValue().toString();
                e3.setText(dataSnapshot.child("mail").getValue().toString());
                Picasso.get().load(dataSnapshot.child("image").getValue().toString()).into(i3);

                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", E1, null));
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });

        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", E2, null));
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });

        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", E3, null));
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });


    }
}
