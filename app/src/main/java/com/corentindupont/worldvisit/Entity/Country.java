package com.corentindupont.worldvisit.Entity;

/**
 * Created by Corentin on 10/04/2018.
 */

public class Country {
    private int id;
    private String name;
    private String capital;
    private String continent;
    private String alpha2CodeUrl;

    private final static String baseFlagUrl = "http://www.geognos.com/api/en/countries/flag/";

    public Country() {
    }

    public Country(String name, String capital, String continent, String alpha2CodeUrl) {
        this.name = name;
        this.capital = capital;
        this.continent = continent;
        this.alpha2CodeUrl = alpha2CodeUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAlpha2CodeUrl() {
        return alpha2CodeUrl;
    }

    public void setAlpha2CodeUrl(String alpha2CodeUrl) {
        this.alpha2CodeUrl = alpha2CodeUrl;
    }

    //change url flag with only alpha2code param.
    public void setAlpha2CodeUrlByAlpha2Code(String alpha2Code){
        this.alpha2CodeUrl = alpha2CodeUrl + alpha2Code + ".png";
    }

    public static String getBaseFlagUrl() {
        return baseFlagUrl;
    }
}
