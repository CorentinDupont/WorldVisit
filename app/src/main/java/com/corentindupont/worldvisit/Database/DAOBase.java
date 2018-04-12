package com.corentindupont.worldvisit.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Corentin on 12/03/2018.
 */

public abstract class DAOBase {
    //if there are change in the database structure, please change this value.
    protected final static int VERSION = 5;
    // file name wich represent my database
    protected final static String NAME = "worldvisit.db";

    public static SQLiteDatabase mDb = null;
    protected static DatabaseHandler mHandler = null;

    public DAOBase(Context pContext) {
        mHandler = new DatabaseHandler(pContext, NAME, null, VERSION);
    }

    public SQLiteDatabase open() {
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    public void fkOn(){
        mDb.execSQL("PRAGMA foreign_keys=ON");
    }

    public void fkOff(){
        mDb.execSQL("PRAGMA foreign_keys=OFF");
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }

    public int getVersion(){
        return VERSION;
    }
}
