package com.example.android.rotcare1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Nav_ProfileActivity extends AppCompatActivity {

    ImageView iprofile,imail,iphone,ioccu,iadd,iimage;
    TextView pname,pmail,pphone,poccu,padd;
    FirebaseAuth fAuth;
    DatabaseReference mDatabase;
    String Current_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav__profile);
        Toolbar toolbar = findViewById(R.id.toolbar10);
        setSupportActionBar(toolbar);

        final ProgressDialog dialog = new ProgressDialog(this);
        iprofile = findViewById(R.id.profile);
        imail = findViewById(R.id.m);
        iphone = findViewById(R.id.p);
        ioccu = findViewById(R.id.o);
        iadd = findViewById(R.id.a);
        pname = findViewById(R.id.Name);
        pmail = findViewById(R.id.mail);
        pphone = findViewById(R.id.phone_no);
        poccu = findViewById(R.id.occupation);
        padd = findViewById(R.id.address);
        fAuth = FirebaseAuth.getInstance();
        Current_user = fAuth.getCurrentUser().getUid();


        dialog.setMessage("Please Wait...");
        dialog.show();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(Current_user);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pname.setText(dataSnapshot.getValue(User.class).getName());
                pmail.setText(dataSnapshot.getValue(User.class).getEmail());
                pphone.setText(dataSnapshot.getValue(User.class).getPhone());
                poccu.setText(dataSnapshot.getValue(User.class).getOccupation());
                padd.setText(dataSnapshot.getValue(User.class).getAddress());

                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
