package com.example.android.rotcare1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MemberActivity extends AppCompatActivity {

    Button apy_for_req,req_status,req_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        apy_for_req = findViewById(R.id.applyreq);
        req_history = findViewById(R.id.req_history);
        req_status = findViewById(R.id.my_req);

        apy_for_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Apply_for_req_Activity.class));
            }
        });
        req_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Req_history_Activity.class));
            }
        });
        req_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),My_req_Activity.class));
            }
        });
    }
}
