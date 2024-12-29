package com.library.eshelf.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";
    private final FirebaseAuth mAuth;

    public AuthViewModel() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void signIn(String email, String password, AuthCallback callback) {
        Log.d(TAG, "Attempting to sign in with email: " + email);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    Log.d(TAG, "signIn:success");
                    callback.onSuccess();
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "signIn:failure", e);
                    callback.onError(e.getMessage());
                });
    }

    public void createAccount(String email, String password, AuthCallback callback) {
        Log.d(TAG, "Attempting to create account with email: " + email);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    Log.d(TAG, "createAccount:success");
                    callback.onSuccess();
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "createAccount:failure", e);
                    callback.onError(e.getMessage());
                });
    }

    public interface AuthCallback {
        void onSuccess();

        void onError(String error);
    }
}
