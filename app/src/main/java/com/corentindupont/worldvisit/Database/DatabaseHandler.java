package com.corentindupont.worldvisit.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Corentin on 12/03/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    //Create and drop table for country entity
    private static final String CREATE_TABLE_COUNTRY=
            "CREATE TABLE " + BaseContract.CountryContract.TABLE_COUNTRY+ " ("
                    + BaseContract.CountryContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + BaseContract.CountryContract.COLUMN_NAME + " TEXT , "
                    + BaseContract.CountryContract.COLUMN_CAPITAL + " TEXT , "
                    + BaseContract.CountryContract.COLUMN_CONTINENT + " TEXT , "
                    + BaseContract.CountryContract.COLUMN_FLAG_SVG_URL + " TEXT );";
    private static final String DROP_TABLE_COUNTRY="DROP TABLE " + BaseContract.CountryContract.TABLE_COUNTRY + ";";

    //Create and drop table for visit entity
    private static final String CREATE_TABLE_VISIT=
            "CREATE TABLE " + BaseContract.VisitContract.TABLE_VISIT+ " ("
                    + BaseContract.VisitContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + BaseContract.VisitContract.COLUMN_DATE + " TEXT , "
                    + BaseContract.VisitContract.COLUMN_COUNTRY_ID + " INTEGER , "
                    + "FOREIGN KEY("+BaseContract.VisitContract.COLUMN_COUNTRY_ID+") REFERENCES "+BaseContract.CountryContract.TABLE_COUNTRY + "("+BaseContract.CountryContract._ID+"));";
    private static final String DROP_TABLE_VISIT="DROP TABLE " + BaseContract.VisitContract.TABLE_VISIT + ";";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Create all table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_COUNTRY);
        db.execSQL(CREATE_TABLE_VISIT);
    }

    //Drop all table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_COUNTRY);
        db.execSQL(DROP_TABLE_VISIT);
        onCreate(db);
    }
}
