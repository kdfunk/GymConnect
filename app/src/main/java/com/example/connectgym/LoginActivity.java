package com.example.connectgym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText, emailEditText;
    private Button loginButton, registerButton;
    private TextView toggleForms;
    private boolean isLoginMode = true;

    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        emailEditText = findViewById(R.id.email);
        loginButton = findViewById(R.id.login);
        registerButton = findViewById(R.id.register);
        toggleForms = findViewById(R.id.toggleForms);
        dbHelper = new DbHelper(this);

        // button listeners
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        toggleForms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleLoginRegister();
            }
        });

        // Initially hide the register button
        registerButton.setVisibility(View.GONE);
    }

    private void loginUser() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
        } else {
            if (dbHelper.checkUser(username, password)) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                // Start MainActivity here
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finish LoginActivity so user cannot go back to it using the back button
            } else {
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void registerUser() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        } else {
            if (dbHelper.addUser(username, email, password)) {
                Toast.makeText(this, "User registered", Toast.LENGTH_SHORT).show();
                loginUser(); // Automatically log the user in after registration
            } else {
                Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void toggleLoginRegister() {
        if (isLoginMode) {
            emailEditText.setVisibility(View.VISIBLE);
            registerButton.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.GONE);
            toggleForms.setText(R.string.login_instead);
            isLoginMode = false;
        } else {
            emailEditText.setVisibility(View.GONE);
            registerButton.setVisibility(View.GONE);
            loginButton.setVisibility(View.VISIBLE);
            toggleForms.setText(R.string.register_instead);
            isLoginMode = true;
        }
    }

}
