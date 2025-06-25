package com.kime.goquest;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Achievements {
    private String id;
    private String title;
    private String description;
    private Integer icon;

    public Achievements(){}

    public Achievements(String id, String title, String desc, int icon){
        this.id = id;
        this.title = title;
        this.description = desc;
        this.icon = icon;
    }
    public static void addAchievement(String ID, String email) {
        Database database = new Database();

        database.checkFieldExists("achievements", email, ID, new Database.FirestoreFieldCallback() {
            @Override
            public void onFieldExists(boolean exists) {
                if (exists) {
                    Log.d("DBCheck", "Достижение уже добавлено");
                } else {
                    Log.d("DBCheck", "Добавляем новое достижение");

                    Map<String, Object> userData = new HashMap<>();
                    userData.put(ID, getCurrentDate());

                    database.updateData("achievements", email, userData);
                }
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("DBCheck", "Ошибка проверки поля: " + errorMessage);
            }
        });
    }

    private static String getCurrentDate() {
        // Формат даты: день.месяц.год Часы:Минуты:Секунды
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }
    public int getIcon() {
        return icon;
    }
    public String getDescription() {
        return description;
    }
    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setIcon(Integer icon) {
        this.icon = icon;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public static void setUserAchievements(String user, String id){

    }


    public static String getTitleByID(String id){
        Map<String, String> title = Map.of("001", "Это Я!", "002", "Планировщик", "003", "Вот что предстоит!", "004", "Начинающий программист");
        return title.get(id);
    }
    public static String getDescByID(String id){
        Map<String, String> desc = Map.of("001", "Открыть профиль пользователя", "002", "Добавить напоминание о занятии", "003", "Впервые увидеть список заданий", "004", "Пройти Экзамен 1");
        return desc.get(id);
    }
    public static Integer getIconA(){
        return R.drawable.achievement;
    }

}
