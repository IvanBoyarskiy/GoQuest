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

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    private EditText email;
    private EditText name;
    private EditText psw;
    private EditText psw_check;
    private SharedPreferences.Editor editor;
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        email = findViewById(R.id.reg_email);
        name = findViewById(R.id.reg_name);
        psw = findViewById(R.id.reg_password);
        psw_check = findViewById(R.id.reg_password_check);
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
        name.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Скрываем клавиатуру
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                // Выполняем нужное действие, например:
                String code = name.getText().toString();
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
        psw_check.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Скрываем клавиатуру
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                // Выполняем нужное действие, например:
                String code = psw_check.getText().toString();
                Log.d("EditText", "Пользователь ввёл: " + code);

                return true; // действие обработано
            }
            return false;
        });
        SharedPreferences sharedPref = getSharedPreferences("login_settings", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    public void toLogin(View view){
        startActivity(new Intent(this, Login.class));
        finish();
    }

    public void RegUser(View view){
        if (psw.getText().toString().equals(psw_check.getText().toString())){
            if (isValidEmail(email.getText().toString())){
                AuthManager authManager = new AuthManager(getApplicationContext());
                Database database = new Database();
                authManager.register(email.getText().toString(), psw.getText().toString(), new AuthManager.AuthCallback() {
                    @Override
                    public void onSuccess(FirebaseUser user) {
                        Toast.makeText(Register.this, "Регистрация успешна: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                        editor.putString("name", name.getText().toString());
                        editor.putString("email", email.getText().toString());
                        editor.putString("password", psw.getText().toString());
                        editor.apply();

                        authManager.login(email.getText().toString(), psw.getText().toString(), new AuthManager.AuthCallback() {
                            @Override
                            public void onSuccess(FirebaseUser user) {
                                Map<String, Object> userData = new HashMap<>();
                                Map<String, Object> achData = new HashMap<>();
                                userData.put("name", name.getText().toString());
                                userData.put("email", email.getText().toString());
                                userData.put("password", psw.getText().toString());
                                userData.put("maxtask", 1);
                                userData.put("score", 0);
                                database.saveData("users", email.getText().toString(), userData);
                                database.saveData("achievements", email.getText().toString(), achData);
                                Toast.makeText(Register.this, "Добро пожаловать, " + user.getEmail(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this, Mainlayout.class));
                                finish();
                            }

                            @Override
                            public void onError(String errorMessage) {
                                Log.i("Tag","Ошибка входа: " + errorMessage);
                                Toast.makeText(Register.this, "Ошибка входа: " + errorMessage, Toast.LENGTH_LONG).show();

                            }
                        });
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Toast.makeText(Register.this, "Ошибка: " + errorMessage, Toast.LENGTH_LONG).show();
                    }
                });
            }
            else {
                Toast.makeText(getApplicationContext(), "Проверьте правильность эл.почты!", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Пароли не совпадают!", Toast.LENGTH_SHORT).show();
        }
    }
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = Pattern.compile(EMAIL_REGEX).matcher(email);
        return matcher.matches();
    }
}