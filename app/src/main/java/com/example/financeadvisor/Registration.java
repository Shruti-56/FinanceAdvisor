package com.example.financeadvisor;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Registration extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Find the submit button by its ID
        Button submitButton = findViewById(R.id.loginButton);

        // Set an OnClickListener for the button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the submit button is clicked, start the MainActivity
                Intent intent = new Intent(Registration.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}