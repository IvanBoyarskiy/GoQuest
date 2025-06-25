package com.kime.goquest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Map;

public class Profile extends AppCompatActivity {

    private TextView nickname;
    private TextView score;
    private TextView email;

    private TextView task;
    private SharedPreferences.Editor editor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SharedPreferences sharedPref = getSharedPreferences("login_settings", Context.MODE_PRIVATE);
        Achievements.addAchievement("001", sharedPref.getString("email", "null").toString());
        Database database = new Database();
        editor = sharedPref.edit();
        score = findViewById(R.id.score);
        email = findViewById(R.id.email);
        task = findViewById(R.id.task);
        email.setText("email: " + sharedPref.getString("email", "email"));
        nickname = findViewById(R.id.nickname);
        database.getData("users", sharedPref.getString("email", "email"), new Database.FirestoreCallback() {
            @Override
            public void onSuccess(Map<String, Object> data) {
                if (data != null) {
                    nickname.setText(data.get("name").toString());
                } else {
                    nickname.setText("Пользователь");
                }
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("Firestore", "Ошибка: " + errorMessage);
            }
        });
        database.getData("users", sharedPref.getString("email", "email"), new Database.FirestoreCallback() {
            @Override
            public void onSuccess(Map<String, Object> data) {
                if (data != null) {
                    score.setText("Рейтинг: " + data.get("score").toString());
                } else {
                    score.setText("Рейтинг: 0");
                }
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("Firestore", "Ошибка: " + errorMessage);
            }
        });
        database.getData("users", sharedPref.getString("email", "email"), new Database.FirestoreCallback() {
            @Override
            public void onSuccess(Map<String, Object> data) {
                if (data != null) {
                    task.setText("Выполняемое задание: " + (Integer.parseInt(data.get("maxtask").toString())));
                } else {
                    task.setText("Выполняемое задание: 0");
                }
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("Firestore", "Ошибка: " + errorMessage);
            }
        });
    }

    public void toBack(View view){
        startActivity(new Intent(this, Mainlayout.class));
        finish();
    }
    private void showStartConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Выйти из Аккаунта?")
                .setMessage("Вы уверены, что хотите выйти из аккаунта?")
                .setPositiveButton("Да", (dialog, which) -> logout())
                .setNegativeButton("Нет", (dialog, which) -> dialog.dismiss())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    private void logout(){
        SharedPreferences sharedPref = getSharedPreferences("login_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear(); // Удаляем всё
        editor.apply();
        startActivity(new Intent(this, Register.class));
        finish();
    }
    public void LogOut(View view){
        showStartConfirmationDialog();
    }
}