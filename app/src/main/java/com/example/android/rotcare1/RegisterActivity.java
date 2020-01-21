package com.example.android.rotcare1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {

    private EditText name,mail,password,address,date,phone,emergency;
    private Spinner occupation , gender;
    private Button registerbtn;
    private FirebaseAuth fAuth;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.rname);
        mail = findViewById(R.id.rmail);
        password = findViewById(R.id.rpassword);
        address = findViewById(R.id.raddress);
        phone = findViewById(R.id.rphone);
        emergency = findViewById(R.id.remergency);
        date = findViewById(R.id.rdate);
        occupation = findViewById(R.id.Occupation_spinner);
        gender = findViewById(R.id.gender_spinner);
        registerbtn = findViewById(R.id.rregister);
        fAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        ArrayAdapter<CharSequence> gender_adapter = ArrayAdapter.createFromResource(this,R.array.Gender,android.R.layout.simple_spinner_item);
        gender_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(gender_adapter);

        ArrayAdapter<CharSequence> occupation_adapter = ArrayAdapter.createFromResource(this,R.array.Occupation, android.R.layout.simple_spinner_item);
        occupation_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        occupation.setAdapter(occupation_adapter);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Name = name.getText().toString().trim();
                final String Mail = mail.getText().toString().trim();
                String Password = password.getText().toString().trim();
                final String Address = address.getText().toString().trim();
                final String Phone = phone.getText().toString().trim();
                final String Emergency = emergency.getText().toString().trim();
                final String Date = date.getText().toString().trim();
                final String Gender = gender.getSelectedItem().toString();
                final String Occupation = occupation.getSelectedItem().toString();


                fAuth.createUserWithEmailAndPassword(Mail,Password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            User user = new User(
                                    Name,
                                    Mail,
                                    Address,
                                    Phone,
                                    Emergency,
                                    Date,
                                    Gender,
                                    Occupation

                            );
                            FirebaseDatabase.getInstance().getReference().child("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {

                                        Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

//
//
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });

    }
}


//    Map<String, Object> user = new HashMap<>();
//                user.put("Name",Name);
//                        user.put("EMail",Mail);
//                        user.put("Phone",Phone);
//                        user.put("Emergency No.",Emergency);
//                        user.put("Date",Date);
//                        user.put("Gender",Gender);
//                        user.put("Occupation",Occupation);
//
//                        fFirestore.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//@Override
//public void onSuccess(DocumentReference documentReference) {
//        Toast.makeText(RegisterActivity.this, "DocumentSnapshot added with ID: "+ documentReference.getId(), Toast.LENGTH_SHORT).show();
//
//        }
//        }).addOnFailureListener(new OnFailureListener() {
//@Override
//public void onFailure(@NonNull Exception e) {
//
//        Toast.makeText(RegisterActivity.this, "Error!", Toast.LENGTH_SHORT).show();
//        }
//        });