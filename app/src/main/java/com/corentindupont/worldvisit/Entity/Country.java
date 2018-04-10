package com.corentindupont.worldvisit.Entity;

import java.util.Date;

/**
 * Created by Corentin on 10/04/2018.
 */

public class Country {
    private String name;
    private String capital;
    private String continent;
    private String flagSvgUrl;

    public Country() {
    }

    public Country(String name, String capital, String continent, String flagSvgUrl) {
        this.name = name;
        this.capital = capital;
        this.continent = continent;
        this.flagSvgUrl = flagSvgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getFlagSvgUrl() {
        return flagSvgUrl;
    }

    public void setFlagSvgUrl(String flagSvgUrl) {
        this.flagSvgUrl = flagSvgUrl;
    }
}
