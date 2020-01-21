package com.example.android.rotcare1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.net.PasswordAuthentication;

public class LoginActivity extends AppCompatActivity {

    EditText mail,password;
    Spinner user_type;
    Button loginbtn;
    TextView Create_user,forgot_pass;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultiDex.install(this);
        setContentView(R.layout.activity_login);

        mail = findViewById(R.id.loginmail);
        password = findViewById(R.id.loginpass);
        user_type = findViewById(R.id.usertype_spin);
        loginbtn = findViewById(R.id.sign_btn);
        Create_user = findViewById(R.id.create_user);
        forgot_pass = findViewById(R.id.forgotpass);
        fAuth = FirebaseAuth.getInstance();


        ArrayAdapter<CharSequence> usertype_adapter = ArrayAdapter.createFromResource(this,R.array.User_type,android.R.layout.simple_spinner_item);
        usertype_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        user_type.setAdapter(usertype_adapter);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Mail = mail.getText().toString().trim();
                String Password = password.getText().toString().trim();
                final String Usertype = user_type.getSelectedItem().toString();


                if(TextUtils.isEmpty(Mail)){
                    mail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(Password)){
                    password.setError("Password is required");
                    return;
                }
                if(password.length() <6) {
                    password.setError("Password must be >= 6 characters");
                    return;
                }


                fAuth.signInWithEmailAndPassword(Mail,Password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            if (Usertype.equals("Member")){
                                startActivity(new Intent(getApplicationContext(),M_HomeActivity.class));
                            }
                            else if (Usertype.equals("Volunteer")){
                                startActivity(new Intent(getApplicationContext(),V_HomeActivity.class));
                            }
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        Create_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });
    }
}
