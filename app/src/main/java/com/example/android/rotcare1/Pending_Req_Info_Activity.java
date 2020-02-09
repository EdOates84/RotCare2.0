package com.example.android.rotcare1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Pending_Req_Info_Activity extends AppCompatActivity {

    TextView name,token,subject,alloted,mobile,status,discription;
    String sname,stoken,ssubject,salloted,smobile,sstatus,uiid;
    Button Accept_btn;
    ImageView imageView;
    DatabaseReference mDatabase,uDatabase;
    FirebaseAuth fAuth;
    String current_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending__req__info_);


        Accept_btn = findViewById(R.id.accept_btn);
        name = findViewById(R.id.name);
        token = findViewById(R.id.tok);
        subject = findViewById(R.id.sub);
        alloted = findViewById(R.id.alloted_name);
        mobile = findViewById(R.id.mobile_no);
        status = findViewById(R.id.status);
        discription = findViewById(R.id.discription);
        fAuth = FirebaseAuth.getInstance();
        current_user = fAuth.getCurrentUser().getUid();
        final String Current_User = fAuth.getCurrentUser().getUid();
        Log.e("asdfgh", "onCreate: "+Current_User );

        uiid = getIntent().getStringExtra("Uiid");
        sname = getIntent().getStringExtra("name");
        stoken = getIntent().getStringExtra("token");
        ssubject = getIntent().getStringExtra("subject");
        salloted = getIntent().getStringExtra("status");
        smobile = getIntent().getStringExtra("mobile");
        sstatus = getIntent().getStringExtra("alloted");
        name.setText(sname);
        subject.setText(ssubject);





        uDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        uDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Requests");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for ( DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    if (uiid.equals(dataSnapshot1.getValue(Request.class).getUid())){

//                        name.setText(dataSnapshot1.getValue(Request.class).getName());
                        token.setText(String.valueOf(dataSnapshot1.getValue(Request.class).getToken()));
                        subject.setText(dataSnapshot1.getValue(Request.class).getSub());
                        if (dataSnapshot1.getValue(Request.class).getStatus() == 0){
                            status.setText("Pending");
                        }
                        if (dataSnapshot1.getValue(Request.class).getStatus() == 1){
                            status.setText("Process");
                        }
                        if (dataSnapshot1.getValue(Request.class).getStatus() == 2){
                            status.setText("Complete");
                        }
//                        status.setText(dataSnapshot1.getValue(Request.class).getStatus());
                        discription.setText(dataSnapshot1.getValue(Request.class).getDis());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Accept_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
