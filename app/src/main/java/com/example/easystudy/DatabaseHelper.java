

package com.example.easystudy;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Test_DB";
    public static final int DATABASE_VERSION = 5; // Updated version
    static final String TABLE_REGISTER = "register";
    static final String TABLE_SUBJECT = "subject"; // New table for subjects
    static final String TABLE_ALARM = "alarm"; // New table for alarms
    static final String TABLE_NOTE = "note"; // New table for notes
    static final String TABLE_TASK = "task"; // New table for tasks

    public static final String COL_ID = "_id";
    public static final String COL_USERNAME = "username";
    public static final String COL_EMAIL = "email";
    public static final String COL_PASSWORD = "password";
    public static final String COL_MOBILE = "mobile";
    public static final String COL_AGE = "age";

    // Columns for the subject table
    public static final String COL_SUBJECT_NAME = "subject_name";
    public static final String COL_SUBJECT_CODE = "subject_code";
    public static final String COL_SUBJECT_DESCRIPTION = "subject_description";

    // Columns for alarm table
    public static final String COL_ALARM_ID = "_id";
    public static final String COL_ALARM_TIME = "alarm_time";

    // Columns for note table
//    public static final String COL_NOTE_ID = "_id";
    public static final String COL_NOTE_CONTENT = "note_content";
    // Columns for the task table
    public static final String COL_TASK = "task_content";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_REGISTER + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USERNAME + " TEXT, " +
                COL_EMAIL + " TEXT, " +
                COL_PASSWORD + " TEXT, " +
                COL_MOBILE + " TEXT, " +
                COL_AGE + " INTEGER)");

        db.execSQL("CREATE TABLE " + TABLE_SUBJECT + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_SUBJECT_NAME + " TEXT, " +
                COL_SUBJECT_CODE + " TEXT, " +
                COL_SUBJECT_DESCRIPTION + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_ALARM + " (" +
                COL_ALARM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_ALARM_TIME + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_NOTE + " (" +
               COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NOTE_CONTENT + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_TASK + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TASK + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALARM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        onCreate(db);
    }

    // Insert user method
    public boolean insertUser(String username, String email, String password, String mobile, int age) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME, username);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PASSWORD, password);
        contentValues.put(COL_MOBILE, mobile);
        contentValues.put(COL_AGE, age);
        long result = db.insert(TABLE_REGISTER, null, contentValues);
        return result != -1;
    }

    // Check user method
    public boolean checkUserByUsername(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_REGISTER + " WHERE " + COL_USERNAME + " = ? AND " + COL_PASSWORD + " = ?", new String[]{username, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
    // Retrieve subject by ID method
    public Cursor getSubjectById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_SUBJECT + " WHERE " + COL_ID + " = ?", new String[]{String.valueOf(id)});
    }


    // Insert subject method
    public boolean insertSubject(String subjectName, String subjectCode, String subjectDescription) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_SUBJECT_NAME, subjectName);
        contentValues.put(COL_SUBJECT_CODE, subjectCode);
        contentValues.put(COL_SUBJECT_DESCRIPTION, subjectDescription);
        long result = db.insert(TABLE_SUBJECT, null, contentValues);
        return result != -1;
    }

    // Insert alarm method
    public boolean insertAlarm(String alarmTime) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ALARM_TIME, alarmTime);
        long result = db.insert(TABLE_ALARM, null, contentValues);
        return result != -1;
    }

    // Insert note method
    public boolean insertNote(String noteContent) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NOTE_CONTENT, noteContent);
        long result = db.insert(TABLE_NOTE, null, contentValues);
        return result != -1;
    }

    // Retrieve notes method
    public Cursor getAllNotes() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NOTE, null);
    }
//    // Insert note method
//    public boolean insertNote(String noteContent) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_NOTE_CONTENT, noteContent);
//
//        long result = -1;
//        try {
//            result = db.insertOrThrow(TABLE_NOTE, null, contentValues);
//        } catch (Exception e) {
//            Log.e("DatabaseHelper", "Error inserting note: " + e.getMessage());
//        } finally {
//            db.close(); // Ensure to close the database connection
//        }
//
//        return result != -1;
//    }

    // Insert task method
    public boolean insertTask(String task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TASK, task);

        long result = db.insert(TABLE_TASK, null, contentValues);
        return result != -1;
    }

    // Get all tasks method
    public List<String> getAllTasks() {
        List<String> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TASK, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String task = cursor.getString(cursor.getColumnIndex(COL_TASK));
                taskList.add(task);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return taskList;
    }

}



