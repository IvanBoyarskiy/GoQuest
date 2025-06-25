package com.kime.goquest;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Mainlayout extends AppCompatActivity {

    private LinearLayout achievementsLayout;
    private LinearLayout achievement;
    private TextView nickname;
    private TextView score;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPref;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mainlayout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sharedPref = getSharedPreferences("login_settings", Context.MODE_PRIVATE);
        Database database = new Database();
        editor = sharedPref.edit();
        score = findViewById(R.id.score);
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

        String user = sharedPref.getString("email", null);
        achievementsLayout = findViewById(R.id.achievements);
        List<Achievements> achievements = new ArrayList<>();
        achievementsLayout.removeAllViews();

        getAchievements(sharedPref.getString("email", "null"), achievements2 -> {
            for (String ID : achievements2) {
                Log.d("Achievement", ID);
                achievements.add(new Achievements(ID, Achievements.getTitleByID(ID), Achievements.getDescByID(ID), Achievements.getIconA()));
            }
            for (Achievements achievement_no : achievements) {
                View achievementView = LayoutInflater.from(this).inflate(R.layout.achievement, achievementsLayout, false);

                TextView title = achievementView.findViewById(R.id.title);
                ImageView icon = achievementView.findViewById(R.id.image);

                title.setText(achievement_no.getTitle());
                icon.setImageResource(achievement_no.getIcon());

                achievementsLayout.addView(achievementView);
            }
        });




    }

    public static void getAchievements(String email, AchievementCallback callback) {
        Database database = new Database();

        database.getAchievementFields("achievements", email, new FirestoreAchievementCallback() {
            @Override
            public void onSuccess(String[] fields) {
                callback.onAchievementsLoaded(fields);
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("Achievements", "Ошибка загрузки: " + errorMessage);
                callback.onAchievementsLoaded(new String[0]);
            }
        });
    }

    public void toProfile(View view){
        startActivity(new Intent(this, Profile.class));
        finish();
    }
    public void toCalendar(View view){
        startActivity(new Intent(this, Plan_list.class));
        finish();
    }
    public void toWork(View view){
        startActivity(new Intent(this, AllTasks.class));
        finish();

    }

}