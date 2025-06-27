package com.kime.goquest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

public class AllTasks extends AppCompatActivity {

    private Button task1;
    private Button task2;
    private Button task3;
    private Button task4;
    private Button task5;
    private Button task6;
    private Button task7;
    private Button task8;
    private Button task9;
    private Button task10;
    private Button task11;
    private Button task12;
    private int maxtask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_tasks);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        task1 = findViewById(R.id.task1);
        task2 = findViewById(R.id.task2);
        task3 = findViewById(R.id.task3);
        task4 = findViewById(R.id.task4);
        task5 = findViewById(R.id.task5);
        task6 = findViewById(R.id.task6);
        task7 = findViewById(R.id.task7);
        task8 = findViewById(R.id.task8);
        task9 = findViewById(R.id.task9);
        task10 = findViewById(R.id.task10);
        task11 = findViewById(R.id.task11);
        task12 = findViewById(R.id.task12);
        Map<String, Button> tasks = new HashMap<>();
        tasks.put("task1", task1);
        tasks.put("task2", task2);
        tasks.put("task3", task3);
        tasks.put("task4", task4);
        tasks.put("task5", task5);
        tasks.put("task6", task6);
        tasks.put("task7", task7);
        tasks.put("task8", task8);
        tasks.put("task9", task9);
        tasks.put("task10", task10);
        tasks.put("task11", task11);
        tasks.put("task12", task12);

        Database database = new Database();
        SharedPreferences sharedPref = getSharedPreferences("login_settings", Context.MODE_PRIVATE);
        Achievements.addAchievement("003", sharedPref.getString("email", "null").toString());
        database.getData("users", sharedPref.getString("email", "email"), new Database.FirestoreCallback() {
            @Override
            public void onSuccess(Map<String, Object> data) {
                if (data != null) {
                    maxtask = Integer.parseInt(data.get("maxtask").toString());
                    if (maxtask < 13) {
                        tasks.get("task" + (maxtask)).setEnabled(true);
                        tasks.get("task" + (maxtask)).setBackgroundColor(getResources().getColor(R.color.main_accent));
                        for (int i = 12; i >= maxtask + 1; i--) {
                            changeColor(tasks.get("task" + i));

                        }
                    }
                } else {
                    maxtask = 1;
                }
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("Firestore", "Ошибка: " + errorMessage);
            }
        });


    }

    private void changeColor(Button btn){
        btn.setBackgroundColor(getResources().getColor(R.color.error));
        btn.setEnabled(false);
    }

    public void toBack(View view){
        startActivity(new Intent(this, Mainlayout.class));
        finish();
    }
    public void toLessons(int num){
        Intent intent = new Intent(this, Lesson1.class);
        intent.putExtra("lesson_number", num);
        startActivity(intent);
    }
    public void toLesson1(View view){
        toLessons(1);
    }
    public void toLesson2(View view){
        toLessons(2);
    }
    public void toLesson3(View view){
        toLessons(3);
    }
    public void toLesson4(View view){
        toLessons(4);
    }
    public void toLesson5(View view){
        toLessons(5);
    }



    public void toTask(int num){
        Intent intent = new Intent(this, Task.class);
        intent.putExtra("task_number", num);
        startActivity(intent);
    }
    public void toTask1(View view){
        toTask(1);
    }
    public void toTask2(View view){
        toTask(2);
    }
    public void toTask3(View view){
        toTask(3);
    }
    public void toTask4(View view){
        toTask(4);
    }
    public void toTask5(View view){
        toTask(5);
    }
    public void toTask6(View view){
        toTask(6);
    }
    public void toTask7(View view){
        toTask(7);
    }
    public void toTask8(View view){
        toTask(8);
    }
    public void toTask9(View view){
        toTask(9);
    }
    public void toTask10(View view){
        toTask(10);
    }
    public void toTask11(View view){
        toTask(11);
    }
    public void toTask12(View view){
        toTask(12);
    }
    public void toTest1(View view){
        if (maxtask == 13) {
            startActivity(new Intent(this, Comic1_pr.class));
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), "Для прохождения Экзамена необходимо пройти все задания", Toast.LENGTH_SHORT).show();
        }
    }

}