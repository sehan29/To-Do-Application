package com.example.to_do_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MydatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Tasks.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "mytask";
    private static final String COLUNM_ID = "_id";
    private static final String COLUNM_TITLE = "task";
    private static final String COLUNM_DESCRIPTION = "description";
    private static final String COLUNM_START_TIME = "st_time";
    private static final String COLUNM_END_TIME = "end_time";


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
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
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
}
