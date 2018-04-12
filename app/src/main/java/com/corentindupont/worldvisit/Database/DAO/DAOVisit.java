package com.corentindupont.worldvisit.Database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.corentindupont.worldvisit.Database.BaseContract;
import com.corentindupont.worldvisit.Database.DAOBase;
import com.corentindupont.worldvisit.Entity.Visit;
import com.corentindupont.worldvisit.Entity.Visit;
import com.corentindupont.worldvisit.UsefulMethods;

import java.util.ArrayList;

/**
 * Created by Corentin on 11/04/2018.
 */

public class DAOVisit extends DAOBase {
    //Use for
    private Context context;

    public DAOVisit(Context pContext) {
        super(pContext);
        this.context = pContext;
    }

    public int insertVisit(Visit visit){
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BaseContract.VisitContract.COLUMN_DATE, UsefulMethods.dateToString(visit.getDate(), Visit.getDateFormat()));
        contentValues.put(BaseContract.VisitContract.COLUMN_COUNTRY_ID, visit.getCountry().getId());
        Integer generatedId =  (int)mDb.insert(BaseContract.VisitContract.TABLE_VISIT, null, contentValues);
        close();
        return generatedId;
    }

    public Visit selectVisit(int visitId){
        open();
        Visit visit = new Visit();

        String[] projection = {BaseContract.VisitContract._ID,
                BaseContract.VisitContract.COLUMN_DATE,
                BaseContract.VisitContract.COLUMN_COUNTRY_ID};

        String selection = BaseContract.VisitContract._ID + " = ? ";
        String[] selectionArgs = {String.valueOf(visitId)};
        String tri = BaseContract.VisitContract._ID+ " DESC ";

        Cursor c = mDb.query(
                BaseContract.VisitContract.TABLE_VISIT, // table sur laquelle faire la requète
                projection, // colonnes à retourner
                selection, // colonnes pour la clause WHERE
                selectionArgs, // valeurs pour la clause WHERE
                null, // GROUP BY (inactif)
                null, // HAVING (inactif)
                tri, // ordre de tri
                null); // LIMIT (inactif)
        if(c.moveToFirst()){
            visit.setId(c.getInt(0));
            visit.setDate(UsefulMethods.stringToDate(c.getString(1), Visit.getDateFormat()));
            DAOCountry daoCountry = new DAOCountry(context);
            visit.setCountry(daoCountry.selectCountry(c.getInt(2)));
            c.close();
        }
        close();
        return visit;
    }

    public void updateVisit(int visitIdToUpdate, Visit visit){
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BaseContract.VisitContract.COLUMN_DATE, UsefulMethods.dateToString(visit.getDate(), Visit.getDateFormat()));
        contentValues.put(BaseContract.VisitContract.COLUMN_COUNTRY_ID, visit.getCountry().getId());
        mDb.update(BaseContract.VisitContract.TABLE_VISIT, contentValues, BaseContract.VisitContract._ID+" = "+visitIdToUpdate, null);
        close();
    }

    public void deleteVisit(int visitId){
        open();
        mDb.delete(BaseContract.VisitContract.TABLE_VISIT, BaseContract.VisitContract._ID + " = ?", new String[] {String.valueOf(visitId)});
        close();
    }


    public ArrayList<Visit> selectAllVisits(){
        open();
        ArrayList<Visit> all_Visits = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+ BaseContract.VisitContract.TABLE_VISIT;
        Cursor c = mDb.rawQuery(selectQuery, null);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            Visit visit = new Visit();
            visit.setId(c.getInt(0));
            visit.setDate(UsefulMethods.stringToDate(c.getString(1), Visit.getDateFormat()));
            DAOCountry daoCountry = new DAOCountry(context);
            visit.setCountry(daoCountry.selectCountry(c.getInt(2)));
            Log.i("ENTITY-VISIT", "country " + visit.getCountry());
            all_Visits.add(visit);
        }
        c.close();
        close();
        return all_Visits;
    }
}
