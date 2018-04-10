package com.corentindupont.worldvisit.Entity;

import java.util.Date;

/**
 * Created by Corentin on 10/04/2018.
 */

public class Visit {
    private Country country;
    private Date date;
    private static final String dateFormat = "dd MMM. yyyy";


    public Visit() {
    }

    public Visit(Country country, Date date) {
        this.country = country;
        this.date = date;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static String getDateFormat() {
        return dateFormat;
    }
}
