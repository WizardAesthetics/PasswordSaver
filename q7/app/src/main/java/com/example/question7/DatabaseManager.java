package com.example.question7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.LinkedList;

public class DatabaseManager extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "PASSWORD_DATABASE";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "PASSWORD_TABLE";

    public DatabaseManager(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        String command = "create table " + TABLE_NAME + "(" +
                "NAME text, " +
                "PASSWORD text)";

        db.execSQL(command);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    public void insert(Password password)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put("NAME", password.getName());
        row.put("PASSWORD", password.getPassword());
        db.insert(TABLE_NAME, null, row);

        db.close();
    }

    public void delete(String name)
    {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_NAME, "NAME = ?", new String[]{name});

        db.close();
    }

    public void update(Password password)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put("NAME", password.getName());
        row.put("PASSWORD", password.getPassword());
        db.update(TABLE_NAME, row, "NAME = ?", new String[]{password.getName()});

        db.close();
    }

    public LinkedList<Password> all()
    {
        SQLiteDatabase db = getWritableDatabase();

        LinkedList<Password> list = new LinkedList<Password>();

        Cursor cursor = db.query(TABLE_NAME, new String[]{"NAME", "PASSWORD"},
                null, null, null, null, null);

        while (cursor.moveToNext() && cursor!=null)
        {
            String name = cursor.getString(0);
            String passwordString = cursor.getString(1);
            Password password = new Password(name, passwordString);
            list.addLast(password);
        }

        cursor.close();
        db.close();

        return list;
    }

    public void clear()
    {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_NAME, null, null);

        db.close();
    }
}