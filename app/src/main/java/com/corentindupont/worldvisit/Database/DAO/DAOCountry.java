package com.corentindupont.worldvisit.Database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.corentindupont.worldvisit.Database.BaseContract;
import com.corentindupont.worldvisit.Database.DAOBase;
import com.corentindupont.worldvisit.Entity.Country;

import java.util.ArrayList;

/**
 * Created by Corentin on 11/04/2018.
 */

public class DAOCountry extends DAOBase {
    public DAOCountry(Context pContext) {
        super(pContext);
    }

    public int insertCountry(Country country){
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BaseContract.CountryContract.COLUMN_NAME, country.getName());
        contentValues.put(BaseContract.CountryContract.COLUMN_CAPITAL, country.getCapital());
        contentValues.put(BaseContract.CountryContract.COLUMN_CONTINENT, country.getContinent());
        contentValues.put(BaseContract.CountryContract.COLUMN_FLAG_SVG_URL, country.getAlpha2CodeUrl());
        Integer generatedId =  (int)mDb.insert(BaseContract.CountryContract.TABLE_COUNTRY, null, contentValues);
        close();
        return generatedId;
    }

    public Country selectCountry(int countryId){
        open();
        Country country = new Country();

        String[] projection = {BaseContract.CountryContract._ID,
                BaseContract.CountryContract.COLUMN_NAME,
                BaseContract.CountryContract.COLUMN_CAPITAL,
                BaseContract.CountryContract.COLUMN_CONTINENT,
                BaseContract.CountryContract.COLUMN_FLAG_SVG_URL};

        String selection = BaseContract.CountryContract._ID + " = ? ";
        String[] selectionArgs = {String.valueOf(countryId)};
        String tri = BaseContract.CountryContract._ID+ " DESC ";

        Cursor c = mDb.query(
                BaseContract.CountryContract.TABLE_COUNTRY, // table sur laquelle faire la requète
                projection, // colonnes à retourner
                selection, // colonnes pour la clause WHERE
                selectionArgs, // valeurs pour la clause WHERE
                null, // GROUP BY (inactif)
                null, // HAVING (inactif)
                tri, // ordre de tri
                null); // LIMIT (inactif)
        if(c.moveToFirst()){
            country.setId(c.getInt(0));
            country.setName(c.getString(1));
            country.setCapital(c.getString(2));
            country.setContinent(c.getString(3));
            country.setAlpha2CodeUrl(c.getString(4));
            c.close();
        }
        close();
        return country;
    }

    public void updateCountry(int countryIdToUpdate, Country country){
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BaseContract.CountryContract.COLUMN_NAME, country.getName());
        contentValues.put(BaseContract.CountryContract.COLUMN_CAPITAL, country.getCapital());
        contentValues.put(BaseContract.CountryContract.COLUMN_CONTINENT, country.getContinent());
        contentValues.put(BaseContract.CountryContract.COLUMN_FLAG_SVG_URL, country.getAlpha2CodeUrl());
        mDb.update(BaseContract.CountryContract.TABLE_COUNTRY, contentValues, BaseContract.CountryContract._ID+" = "+countryIdToUpdate, null);
        close();
    }

    public void deleteCountry(int countryId){
        open();
        mDb.delete(BaseContract.CountryContract.TABLE_COUNTRY, BaseContract.CountryContract._ID + " = ?", new String[] {String.valueOf(countryId)});
        close();
    }


    public ArrayList<Country> selectAllCountries(){
        open();
        ArrayList<Country> all_countries = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+ BaseContract.CountryContract.TABLE_COUNTRY;
        Cursor c = mDb.rawQuery(selectQuery, null);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            Country country = new Country();
            country.setId(c.getInt(0));
            country.setName(c.getString(1));
            country.setCapital(c.getString(2));
            country.setContinent(c.getString(3));
            country.setAlpha2CodeUrl(c.getString(4));
            all_countries.add(country);
        }
        c.close();
        close();
        return all_countries;
    }
}
