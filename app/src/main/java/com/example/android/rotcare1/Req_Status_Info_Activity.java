package com.example.android.rotcare1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Req_Status_Info_Activity extends AppCompatActivity {

    TextView name,token,subject,alloted,mobile,status,discription;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req__status__info_);

        name = findViewById(R.id.iname);
        token = findViewById(R.id.itoken);
        subject = findViewById(R.id.isubject);
        alloted = findViewById(R.id.ialloted_name);
        mobile = findViewById(R.id.mobile_no);
        status = findViewById(R.id.status);
        discription = findViewById(R.id.idiscription);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Requests");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for ( DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
