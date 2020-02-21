package com.example.android.rotcare1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class V_req_History extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private RecyclerView recyclerView;
    private ArrayList<Request> list;
    private V_req_history_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_req__history);

        Toolbar toolbar = findViewById(R.id.toolbar13);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Request>();
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please Wait...");
        dialog.show();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Requests");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Request u = dataSnapshot1.getValue(Request.class);
                    if(dataSnapshot1.getValue(Request.class).getStatus()==2 || dataSnapshot1.getValue(Request.class).getStatus()==3 ) {
                        list.add(u);
                    }
//                    else{
//                        Toast.makeText(Req_history_Activity.this, "No Requests Is Available", Toast.LENGTH_SHORT).show();
//                    }
                }
                adapter = new V_req_history_adapter(V_req_History.this, list);
                recyclerView.setAdapter(adapter);
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(V_req_History.this, "opss.. Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

