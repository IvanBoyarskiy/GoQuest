package com.kime.goquest;

import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static long getMillisFromDMHM(int day, int monthIndex, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.MONTH, monthIndex);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    public static void setNumberPickerTextColor(NumberPicker picker, int color) {
        for (int i = 0; i < picker.getChildCount(); i++) {
            View child = picker.getChildAt(i);
            if (child instanceof EditText) {
                ((EditText) child).setTextColor(color);
            }
        }
    }

    public static void setNumberPickerTextSize(NumberPicker picker, float sizeInSp) {
        for (int i = 0; i < picker.getChildCount(); i++) {
            View child = picker.getChildAt(i);
            if (child instanceof EditText) {
                ((EditText) child).setTextSize(sizeInSp);
            }
        }
    }

    public static String formatTime(long millis) {
        return new SimpleDateFormat("dd.MM HH:mm", Locale.getDefault()).format(new Date(millis));
    }
}