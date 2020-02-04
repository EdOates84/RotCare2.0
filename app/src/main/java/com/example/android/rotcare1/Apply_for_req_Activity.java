package com.example.android.rotcare1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Apply_for_req_Activity extends AppCompatActivity {

    EditText subject,discription;
    Button submitbtn;
    DatabaseReference mDatabase;
    FirebaseAuth fAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_for_req_);

        subject = findViewById(R.id.suject_et);
        discription = findViewById(R.id.discription_et);
        submitbtn = findViewById(R.id.submit_btn);
        fAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Requests");

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String Subject = subject.getText().toString().trim();
                final String Dis = discription.getText().toString().trim();
                final String Name = fAuth.getCurrentUser().getDisplayName();
                int Count = 0;
                int Status = 0;


                if (TextUtils.isEmpty(Subject)) {
                    Toast.makeText(getApplicationContext(), "Enter Subject!", Toast.LENGTH_SHORT).show();
                    subject.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Dis)) {
                    Toast.makeText(getApplicationContext(), "Enter Discription!", Toast.LENGTH_SHORT).show();
                    discription.requestFocus();
                    return;
                }

                Request request = new Request(
                        Subject,
                        Dis,
                        Name,
                        Count,
                        Status
                );
                FirebaseDatabase.getInstance().getReference().child("Requests")
                        .child(fAuth.getCurrentUser().getUid()).setValue(request).addOnCompleteListener(Apply_for_req_Activity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){


                            Toast.makeText(Apply_for_req_Activity.this, "Request Submited", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),My_req_Activity.class));
                        }else {
                            Toast.makeText(Apply_for_req_Activity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                
            }
        });



    }
}
