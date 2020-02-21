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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Alloted_Req_Info_Activity extends AppCompatActivity {

    TextView name, token, subject, alloted, alloted_mobile, mobile, status, discription;
    String sname, stoken, ssubject, salloted, sallot_mob, smobile, sstatus, uiid, sdis;
    ImageView imageView;
    DatabaseReference mDatabase;
    FirebaseAuth fAuth;
    Button comp, withdraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alloted__req__info_);

        name = findViewById(R.id.name);
        token = findViewById(R.id.tok);
        subject = findViewById(R.id.sub);
        alloted = findViewById(R.id.alloted_name);
        alloted_mobile = findViewById(R.id.alloted_mob_no);
        mobile = findViewById(R.id.mobile_no);
        status = findViewById(R.id.status);
        discription = findViewById(R.id.discription);
        comp = findViewById(R.id.Complete_btn);
        withdraw = findViewById(R.id.withdraw_btn);
        fAuth = FirebaseAuth.getInstance();


        uiid = getIntent().getStringExtra("Uiid");
        sname = getIntent().getStringExtra("name");
        stoken = getIntent().getStringExtra("token");
        ssubject = getIntent().getStringExtra("subject");
        salloted = getIntent().getStringExtra("allot_name");
        sallot_mob = getIntent().getStringExtra("allot_no");
        smobile = getIntent().getStringExtra("mobile");
        sstatus = getIntent().getStringExtra("alloted");
        sdis = getIntent().getStringExtra("dis");
        name.setText(sname);
        subject.setText(ssubject);
        token.setText(stoken);
        discription.setText(sdis);
//        alloted.setText(salloted);
//        alloted_mobile.setTe xt(sallot_mob);
        status.setText("Process");

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        String dateTime = simpleDateFormat.format(calendar.getTime());
        Log.e("a", "damp" + dateTime);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Requests");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    if (stoken.equals(String.valueOf(dataSnapshot1.getValue(Request.class).getToken()))) {
                        alloted.setText(dataSnapshot1.getValue(Request.class).getAcceptname());
                        Log.e("a", "bcd" + dataSnapshot1.getValue(Request.class).getAcceptname());
                        alloted_mobile.setText(dataSnapshot1.getValue(Request.class).getAcceptno());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), V_req_History.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Alloted_Req_Info_Activity.this, "Request is withdraw", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
