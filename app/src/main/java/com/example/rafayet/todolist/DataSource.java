package com.example.rafayet.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DataSource {

    private DatabaseHelper helper;
    private SQLiteDatabase database;
    private Task task;
    private Category category;


    public DataSource(Context context) {
        helper = new DatabaseHelper(context);
    }

    private void open() {
        database = helper.getWritableDatabase();
    }

    private void close() {
        helper.close();
    }


    public boolean addTask(Task task) {

        this.open();
        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        ContentValues contentValues = new ContentValues();
        contentValues.put(Task.COL_NAME, task.getName());
        contentValues.put(Task.COL_CATEGORYID, task.getCategoryId());
        contentValues.put(Task.COL_DATE, task.getDate());
        contentValues.put(Task.COL_DESCRIPTION, task.getDescription());
        contentValues.put(Task.COL_STATUS, Task.getColStatus());

        long inserted = database.insert(DatabaseHelper.TABLE_TASK, null, contentValues);

        database.close();
        this.close();

        return inserted > 0;

    }

    public Task getTask(int id) {

        this.open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_TASK, new String[]{Task.COL_ID, Task.COL_NAME, Task.COL_DESCRIPTION, Task.COL_DATE, Task.COL_CATEGORYID, Task.COL_STATUS},
                Task.COL_ID + " =" + id, null, null, null, null);

        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            int mId = cursor.getInt(cursor.getColumnIndex(Task.COL_ID));
            String name = cursor.getString(cursor.getColumnIndex(Task.COL_NAME));
            String descripttion = cursor.getString(cursor.getColumnIndex(Task.COL_DESCRIPTION));
            int date = cursor.getInt(cursor.getColumnIndex(Task.COL_DATE));
            int categoryId = cursor.getInt(cursor.getColumnIndex(Task.COL_CATEGORYID));
            String status = cursor.getString(cursor.getColumnIndex(Task.COL_STATUS));
            task = new Task(categoryId, name, descripttion, date, status);
            task.setId(mId);
        }
        cursor.close();
        this.close();
        return task;
    }

    public ArrayList<Task> getAllTask(int days) {

        Calendar calendar = Calendar.getInstance();
        Date toDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        Date nextDate = calendar.getTime();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        int endDate = Utility.dateToInt(dateFormatter.format(nextDate));
        int startDate = Utility.dateToInt(dateFormatter.format(toDate));

        ArrayList<Task> taskLIst = new ArrayList<>();
        this.open();

        //Cursor cursor = database.query(DatabaseHelper.TABLE_CONTACT_INFO, null, null, null, null, null, null);
        Cursor cursor = database.rawQuery("SELECT *FROM  " + DatabaseHelper.TABLE_TASK + " WHERE " + Task.COL_DATE + " BETWEEN " + startDate + " AND " + endDate + " ORDER BY " + Task.COL_DATE, null);


        if (cursor != null && cursor.getCount() > 0) {

            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                int mId = cursor.getInt(cursor.getColumnIndex(Task.COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(Task.COL_NAME));
                String descripttion = cursor.getString(cursor.getColumnIndex(Task.COL_DESCRIPTION));
                int date = cursor.getInt(cursor.getColumnIndex(Task.COL_DATE));
                int categoryId = cursor.getInt(cursor.getColumnIndex(Task.COL_CATEGORYID));
                String status = cursor.getString(cursor.getColumnIndex(Task.COL_STATUS));
                task = new Task(categoryId, name, descripttion, date, status);
                task.setId(mId);
                taskLIst.add(task);
                cursor.moveToNext();

            }
            cursor.close();
            this.close();
        }
        return taskLIst;
    }

    public ArrayList<Task> getAllTask() {

        ArrayList<Task> taskLIst = new ArrayList<>();
        this.open();

        //Cursor cursor = database.query(DatabaseHelper.TABLE_CONTACT_INFO, null, null, null, null, null, null);
        Cursor cursor = database.rawQuery("SELECT *FROM  " + DatabaseHelper.TABLE_TASK + "  ORDER BY " + Task.COL_DATE, null);

        if (cursor != null && cursor.getCount() > 0) {

            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                int mId = cursor.getInt(cursor.getColumnIndex(Task.COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(Task.COL_NAME));
                String descripttion = cursor.getString(cursor.getColumnIndex(Task.COL_DESCRIPTION));
                int date = cursor.getInt(cursor.getColumnIndex(Task.COL_DATE));
                int categoryId = cursor.getInt(cursor.getColumnIndex(Task.COL_CATEGORYID));
                String status = cursor.getString(cursor.getColumnIndex(Task.COL_STATUS));
                task = new Task(categoryId, name, descripttion, date, status);
                task.setId(mId);
                taskLIst.add(task);
                cursor.moveToNext();

            }
            cursor.close();
            this.close();
        }
        return taskLIst;
    }


    public boolean updateTask(int id, Task task) {
        this.open();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Task.COL_NAME, task.getName());
        contentValues.put(Task.COL_CATEGORYID, task.getCategoryId());
        contentValues.put(Task.COL_DATE, task.getDate());
        contentValues.put(Task.COL_DESCRIPTION, task.getDescription());
        contentValues.put(Task.COL_STATUS, Task.getColStatus());

        int updated = database.update(DatabaseHelper.TABLE_TASK, contentValues, Task.COL_ID + " = " + id, null);
        this.close();

        return updated > 0;
    }

    public boolean deleteTask(int id) {
        this.open();

        int deleted = database.delete(DatabaseHelper.TABLE_TASK, Task.COL_ID + " = " + id, null);
        this.close();

        return deleted > 0;

    }


    public boolean addCategory(Category category) {

        this.open();
        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        ContentValues contentValues = new ContentValues();
        contentValues.put(Category.COL_NAME, category.getName());

        long inserted = database.insert(DatabaseHelper.TABLE_CATEGORY, null, contentValues);

        database.close();
        this.close();

        return inserted > 0;

    }

    public Category getCategory(int id) {

        this.open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_CATEGORY, new String[]{Category.COL_ID, Category.COL_NAME},
                Category.COL_ID + " =" + id, null, null, null, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            int mId = cursor.getInt(cursor.getColumnIndex(Category.COL_ID));
            String name = cursor.getString(cursor.getColumnIndex(Category.COL_NAME));
            category = new Category(name);
            category.setId(mId);
            cursor.close();
            this.close();
            return category;
        }
        cursor.close();
        this.close();
        return null;
    }

    public ArrayList<Category> getAllCategory() {

        ArrayList<Category> categorieList = new ArrayList<>();
        this.open();

        //Cursor cursor = database.query(DatabaseHelper.TABLE_CONTACT_INFO, null, null, null, null, null, null);
        Cursor cursor = database.rawQuery("SELECT *FROM  " + DatabaseHelper.TABLE_CATEGORY, null);

        if (cursor != null && cursor.getCount() > 0) {

            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                int mId = cursor.getInt(cursor.getColumnIndex(Category.COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(Category.COL_NAME));
                category = new Category(name);
                category.setId(mId);
                categorieList.add(category);
                cursor.moveToNext();

            }
            cursor.close();
            this.close();
        }
        return categorieList;
    }

    public boolean updateCategory(int id, Category category) {
        this.open();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Category.COL_NAME, category.getName());

        int updated = database.update(DatabaseHelper.TABLE_TASK, contentValues, Task.COL_ID + " = " + id, null);
        this.close();

        return updated > 0;
    }

    public boolean deleteCategory(int id) {
        this.open();

        int deleted = database.delete(DatabaseHelper.TABLE_CATEGORY, Category.COL_ID + " = " + id, null);
        this.close();

        return deleted > 0;

    }

}
