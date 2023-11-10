package com.example.formation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        /*goToSignIn = findViewById(R.id.signUp);

        goToSignIn.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
                });*/
    }
}