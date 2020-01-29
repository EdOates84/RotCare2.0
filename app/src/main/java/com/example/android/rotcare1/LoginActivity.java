package com.example.android.rotcare1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.net.PasswordAuthentication;

public class LoginActivity extends AppCompatActivity {

    EditText mail,password;
    Spinner user_type;
    Button loginbtn;
    TextView Create_user,forgot_pass;
    String Usertype;
    private FirebaseAuth fAuth;
    FirebaseUser Current_User;
    private FirebaseAuth.AuthStateListener fAuthListener;
    DatabaseReference mDatabase;
    String USER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultiDex.install(this);
        setContentView(R.layout.activity_login);

        fAuth = FirebaseAuth.getInstance();
        Current_User = fAuth.getCurrentUser();
        mail = findViewById(R.id.loginmail);
        password = findViewById(R.id.loginpass);
//        user_type = findViewById(R.id.usertype_spin);
        loginbtn = findViewById(R.id.sign_btn);
        Create_user = findViewById(R.id.create_user);
        forgot_pass = findViewById(R.id.forgotpass);
        final ProgressDialog dialog = new ProgressDialog(this);



        fAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            }
        };


//        ArrayAdapter<CharSequence> usertype_adapter = ArrayAdapter.createFromResource(this, R.array.Login_User_type, android.R.layout.simple_spinner_item);
//        usertype_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        user_type.setAdapter(usertype_adapter);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Mail = mail.getText().toString().trim();
                String Password = password.getText().toString().trim();
//                Usertype = user_type.getSelectedItem().toString();


                if (TextUtils.isEmpty(Mail)) {
                    mail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(Password)) {
                    password.setError("Password is required");
                    return;
                }
                if (password.length() < 6) {
                    password.setError("Password must be >= 6 characters");
                    return;
                }
//                dialog.setMessage("Signing you in...");
//                dialog.show();
//                if (Usertype.equals("User Type")) {
//                    Toast.makeText(LoginActivity.this, "Select User", Toast.LENGTH_SHORT).show();
//                } else {
                    dialog.setMessage("Signing you in...");
                    dialog.show();

                    fAuth.signInWithEmailAndPassword(Mail, Password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }

                    });
                }
//            }
        });
        Create_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }

    @Override
    protected void onStart () {
        super.onStart();
        fAuth.addAuthStateListener(fAuthListener);
    }

        @Override
        public void onBackPressed() {
            Intent setIntent = new Intent(Intent.ACTION_MAIN);
            setIntent.addCategory(Intent.CATEGORY_HOME);
            setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(setIntent);
            finish();
        }


}
