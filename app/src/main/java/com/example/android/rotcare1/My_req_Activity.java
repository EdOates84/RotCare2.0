package com.example.android.rotcare1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class My_req_Activity extends AppCompatActivity {

    private DatabaseReference mDatabase,fDatabase;
    private RecyclerView recyclerView;
    private ArrayList<Request> list;
    private Req_Status_Adapter adapter;
    FirebaseAuth fAuth;
    String Current_user,mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_req_);

        Toolbar toolbar = findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fAuth = FirebaseAuth.getInstance();
        Current_user = fAuth.getCurrentUser().getUid();

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Request>();

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please Wait...");
        dialog.show();

        fDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(Current_user);
        fDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getKey();
                mobile = dataSnapshot.getValue(User.class).getPhone();
                Log.e("aqwes","calll"+mobile);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Requests");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Request u = dataSnapshot1.getValue(Request.class);
                    Log.e("aqwes","True"+dataSnapshot1.getValue(Request.class).getMobile());

                    if (dataSnapshot1.getValue(Request.class).getMobile().equals(mobile)) {
                        Log.e("aqwes","True");

                        list.add(u);
                    }
//                    if (Current_user == current) {
                        Log.e("aqwes","Calll");
//                        list.add(u);
//                    }
                }
                adapter = new Req_Status_Adapter(My_req_Activity.this, list);
                recyclerView.setAdapter(adapter);
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(My_req_Activity.this, "opss.. Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        super.onBackPressed();
    }
}
