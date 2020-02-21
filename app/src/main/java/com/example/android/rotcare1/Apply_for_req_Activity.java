package com.example.android.rotcare1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class Apply_for_req_Activity extends AppCompatActivity {

    EditText subject, discription;
    Spinner sub;
    Button submitbtn;
    DatabaseReference mDatabase, tDatabase, fDatabase, pDatabase, qDatabase;
    FirebaseAuth fAuth;
    FirebaseUser Current_User;
    int count, tok, sta;
    String name, mobile, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_for_req_);

        final ProgressDialog dialog = new ProgressDialog(this);
//        subject = findViewById(R.id.suject_et);
        sub = findViewById(R.id.subject_et);
        discription = findViewById(R.id.discription_et);
        submitbtn = findViewById(R.id.submit_btn);
        fAuth = FirebaseAuth.getInstance();
        Current_User = fAuth.getCurrentUser();
        Toolbar toolbar = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ArrayAdapter<CharSequence> usertype_adapter = ArrayAdapter.createFromResource(this, R.array.Subject_type, android.R.layout.simple_spinner_item);
        usertype_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sub.setAdapter(usertype_adapter);


        tDatabase = FirebaseDatabase.getInstance().getReference().child("Last_Token");
        tDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getKey();
                tok = (dataSnapshot.child("1").getValue(Last_Token.class).getLast_tok());
//                sta = (dataSnapshot.child("2").getValue(Last_Token.class).getNormal_status());

                Log.e("pappu", "" + tok);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        fDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(Current_User.getUid());
        fDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getKey();
                count = dataSnapshot.getValue(User.class).getCount();
                name = dataSnapshot.getValue(User.class).getName();
                mobile = dataSnapshot.getValue(User.class).getPhone();
                email = dataSnapshot.getValue(User.class).getEmail();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Requests");


        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Subject = sub.getSelectedItem().toString();
                final String Dis = discription.getText().toString().trim();
                final String Name = name;
                int Tok = tok + 1;
                int Count = count + 1;
                int Status = 0;
                String uniqueId = UUID.randomUUID().toString();
                final String Uid = fAuth.getCurrentUser().getUid();
                final String Mobile = mobile;
                String Acceptname = null;
                String Acceptno = null;
                String Mail = email;


                if (Subject.equals("Select Subject")) {
                    Toast.makeText(getApplicationContext(), "Select Subject!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(Dis)) {
                    Toast.makeText(getApplicationContext(), "Enter Discription!", Toast.LENGTH_SHORT).show();
                    discription.requestFocus();
                    return;
                }
                dialog.setMessage("Please wait...");
                dialog.show();

                Request request = new Request(
                        Subject,
                        Dis,
                        Name,
                        Uid,
                        Mobile,
                        Acceptname,
                        Acceptno,
                        Mail,
                        Status,
                        Count,
                        Tok
                );

                FirebaseDatabase.getInstance().getReference().child("Requests")
                        .child(uniqueId).setValue(request).addOnCompleteListener(Apply_for_req_Activity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {

                        } else {
                            Toast.makeText(Apply_for_req_Activity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                Intent intent = new Intent(getApplicationContext(), Pending_Req_Info_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("unique", uniqueId);

                tDatabase = FirebaseDatabase.getInstance().getReference().child("Last_Token").child("1");

                tDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        tDatabase.child("last_tok").setValue(tok + 1);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                qDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(Current_User.getUid());
                qDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String data = dataSnapshot.getKey();
                        qDatabase.child("count").setValue(count + 1);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                Toast.makeText(Apply_for_req_Activity.this, "Request Submited", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), My_req_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                dialog.dismiss();
            }
        });


    }
}
