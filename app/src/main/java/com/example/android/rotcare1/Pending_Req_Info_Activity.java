package com.example.android.rotcare1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
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
    String sname,stoken,ssubject,salloted,smobile,sstatus,uiid,sdis;
    Button Accept_btn;
    ImageView imageView;
    DatabaseReference mDatabase,uDatabase;
    FirebaseAuth fAuth;
    String current_user,selected_name,selected_mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending__req__info_);


        Accept_btn = findViewById(R.id.accept_btn);
        name = findViewById(R.id.name);
        token = findViewById(R.id.tok);
        subject = findViewById(R.id.sub);
        alloted = findViewById(R.id.alloted_name);
        mobile = findViewById(R.id.alloted_mob_no);
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
        smobile = getIntent().getStringExtra("mob");
        sstatus = getIntent().getStringExtra("alloted");
        sdis = getIntent().getStringExtra("dis");
        name.setText(sname);
        subject.setText(ssubject);
        token.setText(stoken);
        discription.setText(sdis);
        mobile.setText(smobile);





        uDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_user);
        uDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               selected_name = dataSnapshot.getValue(User.class).getName();
               Log.e("","aqwes"+selected_name);
               selected_mobile = dataSnapshot.getValue(User.class).getPhone();
                Log.e("","aqwes"+selected_mobile);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        mDatabase = FirebaseDatabase.getInstance().getReference().child("Requests");
//        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for ( DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
//                    if (stoken.equals(dataSnapshot1.getValue(Request.class).getToken())){
//
//                        if (dataSnapshot1.getValue(Request.class).getStatus() == 0){
//                            status.setText("Pending");
//                        }
//                        if (dataSnapshot1.getValue(Request.class).getStatus() == 1){
//                            status.setText("Process");
//                        }
//                        if (dataSnapshot1.getValue(Request.class).getStatus() == 2){
//                            status.setText("Complete");
//                        }
//
//
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        Accept_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Requests");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for ( DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                            if (stoken.equals(String.valueOf(dataSnapshot1.getValue(Request.class).getToken()))) {

                                String key = dataSnapshot1.getKey();
                                Log.e("","big"+key);
                                mDatabase.child(key).child("Alloted_no").setValue(selected_mobile);
                                mDatabase.child(key).child("Alloted_name").setValue(selected_name);
                                mDatabase.child(key).child("status").setValue(2);


                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
//                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

//        Accept_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("","working");
//                mDatabase = FirebaseDatabase.getInstance().getReference().child("Requests");
//                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        Log.e("","working");
//
//                        for ( DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
//                            if (stoken.equals(dataSnapshot1.getValue(Request.class).getToken())) {
//
//                                String key = dataSnapshot1.getKey();
//                                Log.e("",""+key);
//                                mDatabase.child(key).child("Alloted_name").setValue(selected_name);
//                                mDatabase.child(key).child("Alloted_no.").setValue(selected_mobile);
//
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//            }
//        });
    }
}
