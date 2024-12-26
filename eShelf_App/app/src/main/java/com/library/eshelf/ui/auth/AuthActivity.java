package com.library.eshelf.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.library.eshelf.MainActivity;
import com.library.eshelf.R;

public class AuthActivity extends AppCompatActivity {
    private static final String TAG = "AuthActivity";
    private AuthViewModel viewModel;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signInButton;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signInButton = findViewById(R.id.signInButton);
        createAccountButton = findViewById(R.id.createAccountButton);

        signInButton.setOnClickListener(v -> handleSignIn());
        createAccountButton.setOnClickListener(v -> handleCreateAccount());
    }

    private void handleSignIn() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        
        if (validateInput(email, password)) {
            Log.d(TAG, "Starting sign in process");
            viewModel.signIn(email, password, new AuthViewModel.AuthCallback() {
                @Override
                public void onSuccess() {
                    Log.d(TAG, "Sign in successful");
                    startActivity(new Intent(AuthActivity.this, MainActivity.class));
                    finish();
                }

                @Override
                public void onError(String error) {
                    Log.e(TAG, "Sign in failed: " + error);
                    Toast.makeText(AuthActivity.this, "Sign in failed: " + error, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void handleCreateAccount() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        
        if (validateInput(email, password)) {
            Log.d(TAG, "Starting account creation");
            viewModel.createAccount(email, password, new AuthViewModel.AuthCallback() {
                @Override
                public void onSuccess() {
                    Log.d(TAG, "Account creation successful");
                    startActivity(new Intent(AuthActivity.this, MainActivity.class));
                    finish();
                }

                @Override
                public void onError(String error) {
                    Log.e(TAG, "Account creation failed: " + error);
                    Toast.makeText(AuthActivity.this, "Account creation failed: " + error, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private boolean validateInput(String email, String password) {
        if (email.isEmpty()) {
            emailEditText.setError("Email is required");
            return false;
        }
        if (password.isEmpty()) {
            passwordEditText.setError("Password is required");
            return false;
        }
        if (password.length() < 6) {
            passwordEditText.setError("Password must be at least 6 characters");
            return false;
        }
        return true;
    }
}
