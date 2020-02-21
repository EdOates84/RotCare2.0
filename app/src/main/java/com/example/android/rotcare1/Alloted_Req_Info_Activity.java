package com.example.android.rotcare1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Alloted_Req_Info_Activity extends AppCompatActivity {

    TextView name,token,subject,alloted,mobile,status,discription;
    String sname,stoken,ssubject,salloted,smobile,sstatus,uiid,sdis;
    ImageView imageView;
    DatabaseReference mDatabase;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alloted__req__info_);

        name = findViewById(R.id.name);
        token = findViewById(R.id.tok);
        subject = findViewById(R.id.sub);
        alloted = findViewById(R.id.alloted_name);
        mobile = findViewById(R.id.mobile_no);
        status = findViewById(R.id.status);
        discription = findViewById(R.id.discription);
        fAuth = FirebaseAuth.getInstance();


        uiid = getIntent().getStringExtra("Uiid");
        sname = getIntent().getStringExtra("name");
        stoken = getIntent().getStringExtra("token");
        ssubject = getIntent().getStringExtra("subject");
        salloted = getIntent().getStringExtra("status");
        smobile = getIntent().getStringExtra("mobile");
        sstatus = getIntent().getStringExtra("alloted");
        sdis=getIntent().getStringExtra("dis");
        name.setText(sname);
        subject.setText(ssubject);
        token.setText(stoken);
        discription.setText(sdis);
        status.setText("Process");

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        String dateTime = simpleDateFormat.format(calendar.getTime());
        Log.e("a","damp"+dateTime);







        mDatabase = FirebaseDatabase.getInstance().getReference().child("Requests");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for ( DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    if (stoken.equals(dataSnapshot1.getValue(Request.class).getToken())){


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
