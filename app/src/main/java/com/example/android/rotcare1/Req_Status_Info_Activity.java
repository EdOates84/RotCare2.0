package com.example.android.rotcare1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Req_Status_Info_Activity extends AppCompatActivity {

    TextView name, token, subject, alloted,allotmob, mobile, status, discription;
    String sname, stoken, ssubject, salloted, sallomobile,smobile, sstatus, uiid, sdis;
    ImageView imageView;
    DatabaseReference mDatabase;
    FirebaseAuth fAuth;
    Button cancel_req;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req__status__info_);

        cancel_req = findViewById(R.id.cancel_btn);
        name = findViewById(R.id.name);
        token = findViewById(R.id.tok);
        subject = findViewById(R.id.sub);
        alloted = findViewById(R.id.alloted_name);
        allotmob = findViewById(R.id.alloted_mob_no);
        mobile = findViewById(R.id.mobile_no);
        status = findViewById(R.id.status);
        discription = findViewById(R.id.discription);
        fAuth = FirebaseAuth.getInstance();
        final String Current_User = fAuth.getCurrentUser().getUid();
        Log.e("asdfgh", "onCreate: " + Current_User);

        uiid = getIntent().getStringExtra("Uiid");
        sname = getIntent().getStringExtra("name");
        stoken = getIntent().getStringExtra("tok");
        ssubject = getIntent().getStringExtra("subject");
        salloted = getIntent().getStringExtra("acceptname");
        sallomobile = getIntent().getStringExtra("acceptno");
        smobile = getIntent().getStringExtra("mobile");
        sstatus = getIntent().getStringExtra("sta");
        sdis = getIntent().getStringExtra("dis");
        name.setText(sname);
        subject.setText(ssubject);
        token.setText(stoken);
        discription.setText(sdis);
        alloted.setText(salloted);
        allotmob.setText(sallomobile);
//        status.setText(sstatus);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Requests");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    if (stoken.equals(String.valueOf(dataSnapshot1.getValue(Request.class).getToken()))) {

                        if (dataSnapshot1.getValue(Request.class).getStatus() == 0) {
                            status.setText("Pending");
                        }
                        if (dataSnapshot1.getValue(Request.class).getStatus() == 1) {
                            status.setText("Process");
                        }
                        if (dataSnapshot1.getValue(Request.class).getStatus() == 2) {
                            status.setText("Complete");
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        cancel_req.setOnClickListener(new View.OnClickListener() {
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
                                        mDatabase.child(key).child("status").setValue(3);

                                        Intent intent = new Intent(getApplicationContext(),Alloted_req_Activity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);

                                    }
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });
            }

}
