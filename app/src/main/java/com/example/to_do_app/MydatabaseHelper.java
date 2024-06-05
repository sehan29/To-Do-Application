package com.example.to_do_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MydatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Tasks1.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "mytask";
    private static final String COLUNM_ID = "_id";
    private static final String COLUNM_TITLE = "task";
    private static final String COLUNM_DESCRIPTION = "description";
    private static final String COLUNM_START_TIME = "st_time";
    private static final String COLUNM_END_TIME = "end_time";



    // User Table
    private static final String USER_TABLE_NAME = "users";
    private static final String USER_COLUMN_ID = "_id";
    private static final String USER_COLUMN_EMAIL = "email";
    private static final String USER_COLUMN_PASSWORD = "password";


    public MydatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUNM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUNM_TITLE + " TEXT, " +
                        COLUNM_DESCRIPTION + " TEXT, " +
                        COLUNM_START_TIME + " TEXT, " +
                        COLUNM_END_TIME + " TEXT);";

        String createUserTableQuery =
                "CREATE TABLE " + USER_TABLE_NAME +
                        " (" + USER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        USER_COLUMN_EMAIL + " TEXT, " +
                        USER_COLUMN_PASSWORD + " TEXT);";

        db.execSQL(query);
        db.execSQL(createUserTableQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(db);
    }

    void addUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_COLUMN_EMAIL, email);
        cv.put(USER_COLUMN_PASSWORD, password);

        //Toast.makeText(context, password, Toast.LENGTH_SHORT).show();

        long result = db.insert(USER_TABLE_NAME, null, cv);
        if(result == -1) {
            Toast.makeText(context, "Registration Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Registered Successfully", Toast.LENGTH_SHORT).show();
        }
    }


    boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(USER_TABLE_NAME, null, USER_COLUMN_EMAIL + "=? AND " + USER_COLUMN_PASSWORD + "=?", new String[]{email, password}, null, null, null);
        boolean userExists = cursor.getCount() > 0;
        cursor.close();
        return userExists;
    }


    void addtask(String title,String description, String st_time, String end_time)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUNM_TITLE,title);
        cv.put(COLUNM_DESCRIPTION,description);

        cv.put(COLUNM_START_TIME,st_time);
        cv.put(COLUNM_END_TIME,end_time);
        Toast.makeText(context,end_time,Toast.LENGTH_SHORT).show();

        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1)
        {
           // Toast.makeText(context,"Failhjd",Toast.LENGTH_SHORT).show();
        }else{
            //Toast.makeText(context,"Faild1212",Toast.LENGTH_SHORT).show();
        }
    }

    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // Getting column indices with validation
        int indexTitle = ((Cursor) cursor).getColumnIndex(COLUNM_TITLE);
        int indexid = cursor.getColumnIndex(COLUNM_ID);
        int indexDescription = cursor.getColumnIndex(COLUNM_DESCRIPTION);
        int indexStartTime = cursor.getColumnIndex(COLUNM_START_TIME);
        int indexEndTime = cursor.getColumnIndex(COLUNM_END_TIME);

        // Check if any column index is -1, indicating the column name does not exist
        if (indexid == -1 || indexTitle == -1 || indexDescription == -1 || indexStartTime == -1 || indexEndTime == -1) {
            throw new IllegalArgumentException("One of the columns does not exist in the database.");
        }

        if (cursor.moveToFirst()) {
            do {

                String id = cursor.getString(indexid);
                String title = cursor.getString(indexTitle);
                String description = cursor.getString(indexDescription);
                String startTime = cursor.getString(indexStartTime);
                String endTime = cursor.getString(indexEndTime);

                Task task = new Task(id,title, description, startTime, endTime);
                taskList.add(task);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return taskList;
    }

    public Task getTask(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] { COLUNM_ID, COLUNM_TITLE, COLUNM_DESCRIPTION }, COLUNM_ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Task task = new Task(cursor.getString(0),cursor.getString(1), cursor.getString(2),"2024:6:6","2024:6:6");  // Assuming Task constructor takes title and description
        cursor.close();
        return task;
    }

    public void deleteTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUNM_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void updateTask(int id, String title, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUNM_TITLE, title);
        cv.put(COLUNM_DESCRIPTION, description);

        db.update(TABLE_NAME, cv, COLUNM_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Item> getHomeTasks() {
        List<Item> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // Getting column indices with validation

        int indexTitle = cursor.getColumnIndex(COLUNM_TITLE);
        int indexDescription = cursor.getColumnIndex(COLUNM_DESCRIPTION);


        // Check if any column index is -1, indicating the column name does not exist
        if (indexTitle == -1 || indexDescription == -1) {
            throw new IllegalArgumentException("One of the columns does not exist in the database.");
        }

        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(indexTitle);
                String description = cursor.getString(indexDescription);


                Item task = new Item(title, description);
                taskList.add(task);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return taskList;
    }

    public int getTasksCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

}
