package com.example.android.rotcare1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Req_Status_Info_Activity extends AppCompatActivity {

    TextView name,token,subject,alloted,mobile,status,discription;
    String sname,stoken,ssubject,salloted,smobile,sstatus;
    ImageView imageView;
    DatabaseReference mDatabase;
    FirebaseAuth fAuth;

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
        fAuth = FirebaseAuth.getInstance();
        final String Current_User = fAuth.getCurrentUser().getUid();
        Log.e("asdfgh", "onCreate: "+Current_User );

        sname = getIntent().getStringExtra("name");
        stoken = getIntent().getStringExtra("token");
        ssubject = getIntent().getStringExtra("subject");
        salloted = getIntent().getStringExtra("status");
        smobile = getIntent().getStringExtra("mobile");
        sstatus = getIntent().getStringExtra("alloted");
        name.setText(sname);
        token.setText(stoken);
        subject.setText(ssubject);
        status.setText(sstatus);






        mDatabase = FirebaseDatabase.getInstance().getReference().child("Requests");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for ( DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    if (Current_User.equals(dataSnapshot1.getValue(Request.class).getUid())){
                        Log.e("asdfgh", "compare: "+dataSnapshot1.getValue(Request.class).getUid() );

//                        name.setText(dataSnapshot1.getValue(Request.class).getName());
//                        token.setText(dataSnapshot1.getValue(Request.class).getToken());
//                        subject.setText(dataSnapshot1.getValue(Request.class).getSub());
//                        if (dataSnapshot1.getValue(Request.class).getStatus() == 0){
//                            status.setText("Pending");
//                        }
//                        if (dataSnapshot1.getValue(Request.class).getStatus() == 1){
//                            status.setText("Process");
//                        }
//                        if (dataSnapshot1.getValue(Request.class).getStatus() == 2){
//                            status.setText("Complete");
//                        }
//                        status.setText(dataSnapshot1.getValue(Request.class).getStatus());
                        discription.setText(dataSnapshot1.getValue(Request.class).getDis());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
