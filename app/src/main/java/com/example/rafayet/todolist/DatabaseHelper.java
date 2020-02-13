package com.example.rafayet.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "todo_manager";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_TASK = "Task";
    static final String TABLE_CATEGORY = "Category";


    String CREATE_TABLE_TASK = " CREATE TABLE " + TABLE_TASK +
            " ( " + Task.COL_ID + " INTEGER PRIMARY KEY, " + Task.COL_NAME + " TEXT, " + Task.COL_DESCRIPTION + " TEXT, " + Task.COL_CATEGORYID +
            " INTEGER NOT NULL, " + Task.COL_DATE +
            " INTEGER NOT NULL, " + Task.COL_STATUS + " TEXT, "
            + " FOREIGN KEY (" + Task.COL_CATEGORYID + ") REFERENCES " + TABLE_CATEGORY + "(" + Category.COL_ID + "));";

    String CREATE_TABLE_CATEGORY = " CREATE TABLE " + TABLE_CATEGORY +
            " ( " + Category.COL_ID + " INTEGER PRIMARY KEY , " + Category.COL_NAME + " TEXT NOT NULL UNIQUE)";

    String INSERT_CATEGORY = "INSERT INTO " + TABLE_CATEGORY + " (" + Category.COL_NAME + ") VALUES ('Shopping'), ('Personal'), ('Academic'), ('Political'), ('Work'), ('Movies to watch'), ('Manage Project')";

    //static final String CREATE_TABLE_TASK = "CREATE TABLE "+TABLE_TASK+ " (Id int PRIMARY KEY AUTOINCREMENT NOT NULL, CategoryID int NOT NULL, Name text not null, Description text, Date DATETIME DEFAULT CURRENT_TIMESTAMP)";

    //static final String CREATE_TABLE_CATEGORY = "CREATE TABLE Category (Id int PRIMARY KEY AUTOINCREMENT NOT NULL, Name text NOT NULL)";

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TASK);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(INSERT_CATEGORY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXIST " + TABLE_TASK);
        db.execSQL(" DROP TABLE IF EXIST " + TABLE_CATEGORY);
        onCreate(db);
    }
}