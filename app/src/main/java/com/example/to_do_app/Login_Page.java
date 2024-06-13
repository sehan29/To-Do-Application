package com.example.to_do_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login_Page extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    TextView text_login;
    private MydatabaseHelper mydatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page); // Assuming your login XML is named activity_login

        emailEditText = findViewById(R.id.editTextText2);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        loginButton = findViewById(R.id.login_btn);
        text_login = findViewById(R.id.registration_id);

        mydatabaseHelper = new MydatabaseHelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (mydatabaseHelper.checkUser(email, password)) {
                    Toast.makeText(Login_Page.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    // Redirect to another activity or main page
                    Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login_Page.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        text_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registration = new Intent(getApplicationContext(), Registration.class);
                startActivity(registration);
            }
        });
    }
}
