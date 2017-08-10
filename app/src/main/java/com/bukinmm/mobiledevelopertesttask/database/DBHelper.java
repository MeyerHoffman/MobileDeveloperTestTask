package com.bukinmm.mobiledevelopertesttask.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bukinmm.mobiledevelopertesttask.database.DBSchema.UsersTable;

public class DBHelper extends SQLiteOpenHelper{

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "mainBase.db";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + UsersTable.NAME + "(" + " _id integer primary key autoincrement, " +
        UsersTable.Cols.UUID + ", " + UsersTable.Cols.LOGIN + ", " +
                UsersTable.Cols.PASSWORD + ")");

        db.execSQL("create table " + DBSchema.PropertiesTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                DBSchema.PropertiesTable.Cols.UUID + ", " +
                DBSchema.PropertiesTable.Cols.ADRESS + ", " +
                DBSchema.PropertiesTable.Cols.AREA + ", " +
                DBSchema.PropertiesTable.Cols.PRICE + ", " +
                DBSchema.PropertiesTable.Cols.NUMBER_OF_ROOMS + ", " +
                DBSchema.PropertiesTable.Cols.PRICE_PER_METER + ", " +
                DBSchema.PropertiesTable.Cols.FLOOR + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}










































