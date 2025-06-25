package com.kime.goquest;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class AddPlan extends AppCompatActivity {

    private Spinner spinnerMonth;
    private EditText editDay, editHour, editMinute;

    private CalendarManager calendarManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);

        calendarManager = new CalendarManager(this);
        SharedPreferences sharedPref = getSharedPreferences("login_settings", Context.MODE_PRIVATE);

        editDay = findViewById(R.id.editDay);
        editHour = findViewById(R.id.editHour);
        editMinute = findViewById(R.id.editMinute);
        spinnerMonth = findViewById(R.id.spinnerMonth);

        String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, months);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonth.setAdapter(adapter);

        findViewById(R.id.btnSave).setOnClickListener(v -> {
            try {
                int day = Integer.parseInt(editDay.getText().toString());
                int hour = Integer.parseInt(editHour.getText().toString());
                int minute = Integer.parseInt(editMinute.getText().toString());

                if (!isValidTime(day, hour, minute)) return;

                long monthIndex = spinnerMonth.getSelectedItemPosition();
                long startTime = Utils.getMillisFromDMHM(day, (int) monthIndex, hour, minute);


                long calendarId = calendarManager.getAvailableCalendarId();
                if (calendarId == -1) {
                    Toast.makeText(this, "Не найден доступный календарь", Toast.LENGTH_SHORT).show();
                    return;
                }


                long eventId = calendarManager.addEvent(calendarId,
                        "Занятие GO",
                        "Изучай Go!",
                        startTime,
                        startTime + 60 * 60 * 1000); // 1 час длительности

                Achievements.addAchievement("002", sharedPref.getString("email", "null").toString());

                if (eventId != -1) {
                    finish();
                }

            } catch (Exception e) {
                Toast.makeText(this, "Введите корректные данные", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btnCancel).setOnClickListener(v -> finish());
    }

    private boolean isValidTime(int day, int hour, int minute) {
        if (day < 1 || day > 31) {
            editDay.setError("День от 1 до 31");
            return false;
        }
        if (hour < 0 || hour > 23) {
            editHour.setError("Часы от 0 до 23");
            return false;
        }
        if (minute < 0 || minute > 59) {
            editMinute.setError("Минуты от 0 до 59");
            return false;
        }
        return true;
    }


    public void toBack(View view){
        startActivity(new Intent(this, Mainlayout.class));
        finish();
    }
}