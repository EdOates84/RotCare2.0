package com.example.android.rotcare1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Date;
import java.util.Calendar;


public class RegisterActivity extends AppCompatActivity {

    private TextView date;
    private EditText name,mail,password,address,phone,emergency;
    private Spinner occupation , gender , User_type;
    private Button registerbtn;
    private FirebaseAuth fAuth;
    private DatabaseReference mDatabase;
    private DatePickerDialog.OnDateSetListener dateset;
    String Date_of_Birth;


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
        User_type = findViewById(R.id.usertype_spin);
        registerbtn = findViewById(R.id.rregister);
        fAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegisterActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateset,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });


        dateset = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month + 1;
                Date_of_Birth = month + "/" + dayOfMonth + "/" + year;
                date.setText(Date_of_Birth);


            }
        };

        ArrayAdapter<CharSequence> user_adapter = ArrayAdapter.createFromResource(this,R.array.User_type,android.R.layout.simple_spinner_item);
        user_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        User_type.setAdapter(user_adapter);

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
                String Phone = phone.getText().toString().matches(MobilePattern)
                final String Emergency = emergency.getText().toString().trim();
                final String DATE = Date_of_Birth;
                final String USER = User_type.getSelectedItem().toString();
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
                                    DATE,
                                    Gender,
                                    Occupation,
                                    USER


                            );
                            FirebaseDatabase.getInstance().getReference().child("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {

                                        Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                    }
                                }
                            });
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


