package com.example.android.rotcare1;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MemberFragment extends Fragment {

    Button apy_for_req,req_status,req_history;
    DatabaseReference mDatabase;
    FirebaseAuth fAuth;
    String Current_User;

    public MemberFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member, container, false);

        apy_for_req = (Button) view.findViewById(R.id.applyreq);
        req_history = view.findViewById(R.id.req_history);
        req_status =  view.findViewById(R.id.my_req);
        fAuth = FirebaseAuth.getInstance();
        Current_User = fAuth.getCurrentUser().getUid();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        String dateTime = simpleDateFormat.format(calendar.getTime());
        Log.e("a","damp"+dateTime);

        if (dateTime == "11:59:00") {

            mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(Current_User);
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String data = dataSnapshot.getKey();
                   mDatabase.child("count").setValue(0);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        apy_for_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),Apply_for_req_Activity.class));
            }
        });
        req_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),Req_history_Activity.class));
            }
        });
        req_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),My_req_Activity.class));
            }
        });

        return view;
    }
}
