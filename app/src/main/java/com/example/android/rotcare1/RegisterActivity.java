package com.example.android.rotcare1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
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

public class RegisterActivity extends AppCompatActivity {

    private EditText name,mail,password,address,date,phone,emergency;
    private Spinner occupation , gender;
    private Button registerbtn;
    private FirebaseAuth fAuth;


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

        ArrayAdapter<CharSequence> gender_adapter = ArrayAdapter.createFromResource(this,R.array.Gender,android.R.layout.simple_spinner_item);
        gender_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(gender_adapter);

        ArrayAdapter<CharSequence> occupation_adapter = ArrayAdapter.createFromResource(this,R.array.Occupation, android.R.layout.simple_spinner_item);
        occupation_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        occupation.setAdapter(occupation_adapter);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Mail = mail.getText().toString().trim();
                String Password = password.getText().toString().trim();
                final String Address = address.getText().toString().trim();
                final String Phone = String.valueOf(phone.getText()).trim();
                final String Emergency = String.valueOf(emergency.getText()).trim();
                final String Date = String.valueOf(date.getText()).trim();
                final String Gender = gender.getSelectedItem().toString();
                final String Occupation = occupation.getSelectedItem().toString();


                fAuth.createUserWithEmailAndPassword(Mail,Password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "User Registered", Toast.LENGTH_SHORT).show();

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
