package com.corentindupont.worldvisit.Database;

import android.provider.BaseColumns;

/**
 * Created by Corentin on 12/03/2018.
 */

public class BaseContract {

    // private constructor to not instantiate the class
    private BaseContract() {}

    // table visit content
    public static class VisitContract implements BaseColumns
    {
        public static final String TABLE_VISIT = "Visit";
        public static final String COLUMN_COUNTRY_ID = "country_id";
        public static final String COLUMN_DATE = "date";
    }

    // table visit content
    public static class CountryContract implements BaseColumns
    {
        public static final String TABLE_COUNTRY = "Country";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CAPITAL = "capital";
        public static final String COLUMN_CONTINENT = "continent";
        public static final String COLUMN_FLAG_SVG_URL = "flag_svg_url";
    }

}
