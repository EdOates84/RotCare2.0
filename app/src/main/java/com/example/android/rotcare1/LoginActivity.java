package com.example.android.rotcare1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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
        setContentView(R.layout.activity_login);

        mail = findViewById(R.id.loginmail);
        password = findViewById(R.id.loginpass);
        user_type = findViewById(R.id.usertype_spin);
        loginbtn = findViewById(R.id.sign_btn);
        Create_user = findViewById(R.id.create_user);
        forgot_pass = findViewById(R.id.forgotpass);
        fAuth = FirebaseAuth.getInstance();



    }
}
