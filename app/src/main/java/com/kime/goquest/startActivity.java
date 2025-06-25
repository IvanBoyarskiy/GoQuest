package com.kime.goquest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseUser;

public class startActivity extends AppCompatActivity {

    private TextView text;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SharedPreferences sharedPref = getSharedPreferences("login_settings", Context.MODE_PRIVATE);
        String email = sharedPref.getString("email", null);
        String password = sharedPref.getString("password", null);

        if (email == null || password == null) {

            startActivity(new Intent(this, Comic_1.class));
            finish();
        } else {
            AuthManager authManager = new AuthManager(getApplicationContext());
            authManager.login(email, password, new AuthManager.AuthCallback() {
                @Override
                public void onSuccess(FirebaseUser user) {
                    Toast.makeText(startActivity.this, "Добро пожаловать, " + user.getEmail(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(startActivity.this, Mainlayout.class));
                    finish();
                }

                @Override
                public void onError(String errorMessage) {
                    Log.e("LoginError", errorMessage);
                    Toast.makeText(startActivity.this, "Ошибка входа: " + errorMessage, Toast.LENGTH_LONG).show();
                    SharedPreferences sharedPref = getSharedPreferences("login_settings", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.clear();
                    editor.apply();
                    startActivity(new Intent(startActivity.this, Register.class));
                    finish();
                }
            });
        }
    }
}