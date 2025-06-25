package com.kime.goquest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthManager {

    private FirebaseAuth mAuth;
    private Context context;

    public AuthManager(Context context) {
        this.context = context;
        mAuth = FirebaseAuth.getInstance();
    }


    public boolean isUserLoggedIn() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        return currentUser != null;
    }


    public String getCurrentUserId() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            return currentUser.getUid();
        }
        return null;
    }


    public void register(String email, String password, AuthCallback callback) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((OnCompleteListener<AuthResult>) task -> {
                    if (task.isSuccessful()) {
                        Log.d("AuthManager", "Регистрация успешна");
                        callback.onSuccess(mAuth.getCurrentUser());
                    } else {
                        Log.w("AuthManager", "Ошибка регистрации", task.getException());
                        callback.onError(task.getException().getMessage());
                    }
                });
    }


    public void login(String email, String password, AuthCallback callback) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((OnCompleteListener<AuthResult>) task -> {
                    if (task.isSuccessful()) {
                        Log.d("AuthManager", "Вход выполнен");
                        callback.onSuccess(mAuth.getCurrentUser());
                    } else {
                        Log.w("AuthManager", "Ошибка входа", task.getException());
                        callback.onError(task.getException().getMessage());
                    }
                });
    }


    public void logout() {
        if (isUserLoggedIn()) {
            mAuth.signOut();
            Toast.makeText(context, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show();
        }
    }


    public interface AuthCallback {
        void onSuccess(FirebaseUser user);
        void onError(String errorMessage);
    }
}