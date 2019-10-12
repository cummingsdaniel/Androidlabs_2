package com.example.androidlabs_2;

import android.app.Activity;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class MyDatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDatabaseFile";
    public static final int VERSION_NUM = 1;
    public static final String TABLE_NAME = "Messages";
    public static final String COL_ID = "_id";
    public static final String COL_MESSAGE = "NAME";
    public static final String IS_SENT = "IS_SENT";


    public MyDatabaseOpenHelper(Activity ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM );
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_MESSAGE + " TEXT, " + IS_SENT + " INTEGER)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
    }

    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
