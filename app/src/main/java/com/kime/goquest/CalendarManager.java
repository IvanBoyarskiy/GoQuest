package com.kime.goquest;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class CalendarManager {

    private static final String TAG = "CalendarManager";
    private Context context;

    public CalendarManager(Context context) {
        this.context = context.getApplicationContext();
    }


    public long getAvailableCalendarId() {
        ContentResolver contentResolver = context.getContentResolver();

        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String[] projection = new String[]{
                CalendarContract.Calendars._ID,
                CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
                CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL
        };

        Cursor cursor = contentResolver.query(uri, projection, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(CalendarContract.Calendars._ID));
                int accessLevel = cursor.getInt(cursor.getColumnIndexOrThrow(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL));


                if (accessLevel >= CalendarContract.Calendars.CAL_ACCESS_CONTRIBUTOR) {
                    cursor.close();
                    return id;
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        Log.e(TAG, "Не найдено подходящих календарей");
        return -1;
    }




    public long addEvent(long calendarId, String title, String description, long startTime, long endTime) {
        ContentResolver contentResolver = context.getContentResolver();

        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.CALENDAR_ID, calendarId);
        values.put(CalendarContract.Events.TITLE, title);
        values.put(CalendarContract.Events.DESCRIPTION, description);
        values.put(CalendarContract.Events.DTSTART, startTime);
        values.put(CalendarContract.Events.DTEND, endTime);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());

        Uri uri = contentResolver.insert(CalendarContract.Events.CONTENT_URI, values);

        if (uri == null) {
            Log.e(TAG, "Ошибка создания события");
            return -1;
        }

        long eventId = Long.parseLong(uri.getLastPathSegment());
        Log.d(TAG, "Событие добавлено с ID: " + eventId);

        addReminder(eventId, 10);

        return eventId;
    }


    public boolean updateEvent(long eventId, String newTitle, String newDescription, long newStartTime, long newEndTime) {
        ContentResolver contentResolver = context.getContentResolver();

        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.TITLE, newTitle);
        values.put(CalendarContract.Events.DESCRIPTION, newDescription);
        values.put(CalendarContract.Events.DTSTART, newStartTime);
        values.put(CalendarContract.Events.DTEND, newEndTime);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());

        Uri uri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, eventId);
        int rows = contentResolver.update(uri, values, null, null);

        Log.d(TAG, rows + " строк обновлено");
        return rows > 0;
    }

    public List<PlanItem> getAllEventsOnlyGo() {
        List<PlanItem> events = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();

        Uri uri = CalendarContract.Events.CONTENT_URI;
        String[] projection = new String[]{
                CalendarContract.Events._ID,
                CalendarContract.Events.TITLE,
                CalendarContract.Events.DESCRIPTION,
                CalendarContract.Events.DTSTART
        };

        String selection = CalendarContract.Events.TITLE + "=?";
        String[] selectionArgs = new String[]{"Занятие GO"};

        Cursor cursor = contentResolver.query(uri, projection, selection, selectionArgs, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(CalendarContract.Events._ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(CalendarContract.Events.TITLE));
                String desc = cursor.getString(cursor.getColumnIndexOrThrow(CalendarContract.Events.DESCRIPTION));
                long start = cursor.getLong(cursor.getColumnIndexOrThrow(CalendarContract.Events.DTSTART));

                events.add(new PlanItem(id, title, desc, start)); // без endTime
            } while (cursor.moveToNext());
            cursor.close();
        }

        return events;
    }


    public boolean deleteEvent(long eventId) {
        ContentResolver contentResolver = context.getContentResolver();

        Uri uri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, eventId);
        int rows = contentResolver.delete(uri, null, null);

        Log.d(TAG, rows + " строк удалено");
        return rows > 0;
    }


    void addReminder(long eventId, int minutesBefore) {
        ContentResolver contentResolver = context.getContentResolver();

        ContentValues values = new ContentValues();
        values.put(CalendarContract.Reminders.EVENT_ID, eventId);
        values.put(CalendarContract.Reminders.MINUTES, minutesBefore);
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);

        Uri uri = contentResolver.insert(CalendarContract.Reminders.CONTENT_URI, values);

        if (uri == null) {
            Log.e(TAG, "Ошибка добавления напоминания");
        } else {
            Log.d(TAG, "Напоминание добавлено");
        }
    }
}