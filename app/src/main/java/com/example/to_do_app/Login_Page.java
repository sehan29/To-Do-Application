package com.example.to_do_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login_Page extends AppCompatActivity {

    Button login;
    TextView text_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login = findViewById(R.id.login_btn);
        text_login = findViewById(R.id.registration_id);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent login_intent = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(login_intent);
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