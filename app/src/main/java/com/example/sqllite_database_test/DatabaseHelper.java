package com.example.sqllite_database_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final  String DATABASE_NAME = "Company.db";
    public static final  String TABLE_NAME = "Employee";

    public static final  String COLUMN_ID = "ID";
    public static final  String COLUMN_NAME = "Name";
    public static final  String COLUMN_SALARY = "Salary";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
              COLUMN_NAME + " TEXT NOT NULL," + COLUMN_SALARY + " INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addEmployee(String id, String name , int salary){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,id);
        values.put(COLUMN_NAME,name);
        values.put(COLUMN_SALARY,salary);
        db.insert(TABLE_NAME,null,values);

    }

    public Cursor viewEmployee() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;

    }

    public Integer deleteEmployee(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete(TABLE_NAME,"ID = ?", new String[] {id});
    }
}
