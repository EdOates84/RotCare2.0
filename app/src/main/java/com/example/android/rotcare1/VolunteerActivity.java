package com.example.android.rotcare1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VolunteerActivity extends AppCompatActivity {

    Button pending_req,alloted_req;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);

        pending_req = findViewById(R.id.v_pending_req);
        alloted_req = findViewById(R.id.v_alloted_req);

        pending_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Pending_req_Activity.class));
            }
        });

        alloted_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Alloted_req_Activity.class));
            }
        });
    }
}
