package com.example.financeadvisor;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
EditText salary, investment, expenses;

TextView showsalary;
Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        salary = findViewById(R.id.salaryEditText);
        investment = findViewById(R.id.investmentsEditText);
        expenses = findViewById(R.id.expenses);
        showsalary = findViewById(R.id.totalSavingsTextView);
        submit = findViewById(R.id.submit1);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float a = Float.parseFloat(salary.getText().toString());
                float b = Float.parseFloat(investment.getText().toString());
                float c = Float.parseFloat(expenses.getText().toString());
                float d = (a + b) - c;
                showsalary.setText("Total Savings:" + d );
            }
        });
        // Find the "Chat with AI" button by its ID
        Button chatButton = findViewById(R.id.chatButton);

        // Set an OnClickListener for the button
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the button is clicked, start the ChatActivity
                Log.d("MainActivity", "Chat button clicked");
                Intent intent = new Intent(MainActivity.this, AIChat.class);
                startActivity(intent);
            }
        });
    }
}