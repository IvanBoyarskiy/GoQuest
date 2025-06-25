package com.kime.goquest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Test1 extends AppCompatActivity {

    private EditText editTextExamCode;
    private TextView textExamStatus;
    private Button buttonSubmit;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1_activity);

        editTextExamCode = findViewById(R.id.editTextExamCode);
        textExamStatus = findViewById(R.id.textExamStatus);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        Button buttonTab = findViewById(R.id.buttonTab);
        sharedPref = getSharedPreferences("login_settings", Context.MODE_PRIVATE);

        TextView examTaskText = findViewById(R.id.examTaskText);
        examTaskText.setText(getExamTask());

        buttonTab.setOnClickListener(v -> {
            int start = editTextExamCode.getSelectionStart();
            Editable editable = editTextExamCode.getEditableText();
            editable.insert(start, "\t"); // 4 пробела как Tab
        });

        buttonSubmit.setOnClickListener(v -> checkExam(editTextExamCode.getText().toString()));
    }

    private String getExamTask() {
        return "Задание: напишите программу, которая:\n\n" +
                "1. Объявляет переменную name string = \"Гость\"\n" +
                "2. Создаёт функцию greet(), которая выводит \"Добро пожаловать, [name]!\"\n" +
                "3. Проверяет: если name == \"Админ\", выводит \"Доступ разрешён\"\n" +
                "4. Использует цикл for от 1 до 5 и выводит \"Попытка [i]\"";
    }

    private void checkExam(String goCode) {
        buttonSubmit.setEnabled(false);
        textExamStatus.setText("Проверяю...");

        GoCodeAIExecutor aiExecutor = new GoCodeAIExecutor();
        aiExecutor.analyzeGoCode(goCode, result -> {
            runOnUiThread(() -> {
                if (isCorrect(result)) {
                    textExamStatus.setText("✅ Задание выполнено верно!");
                    textExamStatus.setTextColor(getColor(android.R.color.holo_green_dark));
                    Achievements.addAchievement("004", sharedPref.getString("email", "null").toString());
                    startActivity(new Intent(this, Comic1_t.class));
                    finish();
                } else {
                    textExamStatus.setText("❌ Неверный результат.\nВывод программы:\n" + result);
                    textExamStatus.setTextColor(getColor(android.R.color.holo_red_dark));
                    startActivity(new Intent(this, Comic1_f.class));
                    finish();
                }
                buttonSubmit.setEnabled(true);
            });
        });
    }

    private boolean isCorrect(String output) {
        return output.contains("Добро пожаловать, Гость!") &&
                output.contains("Доступ запрещён") &&
                output.contains("Попытка 1") &&
                output.contains("Попытка 5");
    }

    public void toBack(View view) {
        startActivity(new Intent(this, AllTasks.class));
        finish();
    }
}