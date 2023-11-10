package com.example.formation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    private TextView goToSignIn;
    private EditText fullNameet, emailet, phonet, cinet, passet;
    private Button buttonSignUp;
    private String fullNameS, emailS, phoneS, cinS, passS;
    private static final String EMAIL_REGEX =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private FirebaseAuth firebaseAuth ;
    private ProgressDialog progressDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);

        goToSignIn = findViewById(R.id.signUp);
        fullNameet = findViewById(R.id.fullName);
        emailet = findViewById(R.id.email);
        phonet = findViewById(R.id.phone);
        cinet = findViewById(R.id.cin);
        passet = findViewById(R.id.pass);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        goToSignIn.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
        });
        buttonSignUp.setOnClickListener(v -> {
            if (validate()) {
                progressDialog.setMessage("Please wait...!!");
                progressDialog.show();
                String emailUser = emailet.getText().toString().trim();
                String passwordUser = passet.getText().toString().trim();
                firebaseAuth.createUserWithEmailAndPassword(emailUser,passwordUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, "Account Created Successfully !", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                        else {
                            Toast.makeText(SignUpActivity.this, "Register Failed !", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
            }

        });
    }

    private boolean validate() {
        boolean result = false;
        fullNameS = fullNameet.getText().toString();
        emailS = emailet.getText().toString();
        cinS = cinet.getText().toString();
        phoneS = phonet.getText().toString();
        passS = passet.getText().toString();
        if (fullNameS.isEmpty() || fullNameS.length()<7){
            fullNameet.setError("Full Name is invalid!");
        } else if (!isValidEmail(emailS)) {
            emailet.setError("Email is invalid!");

        } else if (cinS.isEmpty() || cinS.length() != 8 ) {
            cinet.setError("Cin is invalid!");

        } else if (phoneS.isEmpty() || phoneS.length() != 8 ) {
            phonet.setError("Phone is invalid!");
        } else if (passS.isEmpty() || passS.length()<7) {
            passet.setError("Password is invalid");
        } else {
            result = true ;
        }
        return result;
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}