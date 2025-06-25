package com.kime.goquest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseUser;

import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText psw;
    private SharedPreferences.Editor editor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        email = findViewById(R.id.reg_email);
        psw = findViewById(R.id.reg_password);
        email.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Скрываем клавиатуру
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                // Выполняем нужное действие, например:
                String code = email.getText().toString();
                Log.d("EditText", "Пользователь ввёл: " + code);

                return true; // действие обработано
            }
            return false;
        });
        psw.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Скрываем клавиатуру
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                // Выполняем нужное действие, например:
                String code = psw.getText().toString();
                Log.d("EditText", "Пользователь ввёл: " + code);

                return true; // действие обработано
            }
            return false;
        });
        SharedPreferences sharedPref = getSharedPreferences("login_settings", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

    }
    public void toReg(View view){
        startActivity(new Intent(this, Register.class));
        finish();
    }

    public void login(View view) {
        AuthManager authManager = new AuthManager(getApplicationContext());
        Database database = new Database();
        authManager.login(email.getText().toString(), psw.getText().toString(), new AuthManager.AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser user) {
                Toast.makeText(Login.this, "Добро пожаловать, " + user.getEmail(), Toast.LENGTH_SHORT).show();
                editor.putString("email", email.getText().toString());
                editor.putString("password", psw.getText().toString());
                editor.apply();
                database.getData("users", email.getText().toString(), new Database.FirestoreCallback() {
                    @Override
                    public void onSuccess(Map<String, Object> data) {
                        if (data != null) {
                            editor.putString("name", data.get("name").toString());
                            editor.apply();
                        } else {
                            editor.putString("name", "DefaultUser");
                            editor.apply();
                        }
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Log.e("Firestore", "Ошибка: " + errorMessage);
                    }
                });
                startActivity(new Intent(Login.this, Mainlayout.class));
                finish();
            }

            @Override
            public void onError(String errorMessage) {
                Log.i("Tag","Ошибка входа: " + errorMessage);
                Toast.makeText(Login.this, "Ошибка входа: " + errorMessage, Toast.LENGTH_LONG).show();
                startActivity(new Intent(Login.this, Register.class));
                finish();
            }
        });
    }

}