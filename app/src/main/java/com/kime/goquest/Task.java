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
import java.util.HashMap;
import java.util.Map;

public class Task extends AppCompatActivity {

    private int currentTaskNumber;
    private TextView taskDescription;
    private EditText editTextGoCode;
    private TextView textStatus;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);


        currentTaskNumber = getIntent().getIntExtra("task_number", -1);
        if (currentTaskNumber == -1) {
            finish();
            return;
        }
        Button buttonTab = findViewById(R.id.buttonTab);

        buttonTab.setOnClickListener(v -> {
            int start = editTextGoCode.getSelectionStart();
            Editable editable = editTextGoCode.getEditableText();

            editable.insert(start, "\t");
        });

        TextView taskNumber = findViewById(R.id.taskNumber);
        taskDescription = findViewById(R.id.taskDescription);
        editTextGoCode = findViewById(R.id.editTextGoCode);
        textStatus = findViewById(R.id.textStatus);
        Button buttonExit = findViewById(R.id.buttonExit);

        taskNumber.setText("Задание #" + currentTaskNumber);
        taskDescription.setText(TaskManager.getDescription(currentTaskNumber));

        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(v -> {
            String goCode = editTextGoCode.getText().toString();
            checkTask(goCode, currentTaskNumber);
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Task.this, AllTasks.class));
                finish();
            }
        });
    }

    private void checkTask(String goCode, int taskNumber) {
        buttonSubmit.setEnabled(false);
        String expectedOutput = TaskManager.getExpectedOutput(taskNumber);

        GoCodeAIExecutor aiExecutor = new GoCodeAIExecutor();
        aiExecutor.analyzeGoCode(goCode, result -> {
            runOnUiThread(() -> {
                if (result.contains(expectedOutput)) {
                    textStatus.setText("✅ Результат верный!");
                    textStatus.setTextColor(getBaseContext().getResources().getColor(android.R.color.holo_green_dark));
                    SharedPreferences sharedPref = getSharedPreferences("login_settings", Context.MODE_PRIVATE);
                    Database database = new Database();
                    Map<String, Object> userData = new HashMap<>();
                    if (taskNumber == 1001){
                        Achievements.addAchievement("004", sharedPref.getString("email", "null").toString());
                        startActivity(new Intent(this, Comic1_t.class));
                        finish();
                    }

                   database.getData("users", sharedPref.getString("email", "null").toString(), new Database.FirestoreCallback() {
                        @Override
                        public void onSuccess(Map<String, Object> data) {
                            userData.put("maxtask", currentTaskNumber + 1);
                            userData.put("score", Integer.parseInt(data.get("score").toString()) + 100);
                            database.updateData("users", sharedPref.getString("email", "null").toString(), userData);
                        }
                        @Override
                        public void onError(String errorMessage) {

                        }
                    });
                } else {
                    textStatus.setText("❌ Результат неверен.");
                    textStatus.setTextColor(getBaseContext().getResources().getColor(android.R.color.holo_red_dark));
                    if (taskNumber == 1001){
                        SharedPreferences sharedPref = getSharedPreferences("login_settings", Context.MODE_PRIVATE);
                        Achievements.addAchievement("004", sharedPref.getString("email", "null").toString());
                        startActivity(new Intent(this, Comic1_f.class));
                        finish();
                    }
                }
                buttonSubmit.setEnabled(true);
            });
        });
    }
}