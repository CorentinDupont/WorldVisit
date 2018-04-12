package com.corentindupont.worldvisit.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.corentindupont.worldvisit.R;

/**
 * Created by Corentin on 10/04/2018.
 */

public class CountryViewHolder extends RecyclerView.ViewHolder {

    private TextView countryNameTV = null;
    private TextView countryCapitalTV = null;
    private TextView countryContinentTV = null;
    private ImageView countryFlagIV = null;

    public CountryViewHolder(View itemView) {
        super(itemView);
        countryNameTV = (TextView) itemView.findViewById(R.id.country_name_text_view);
        countryCapitalTV = (TextView) itemView.findViewById(R.id.country_capital_name_text_view);
        countryContinentTV = (TextView) itemView.findViewById(R.id.country_continent_name_text_view);
        countryFlagIV = (ImageView) itemView.findViewById(R.id.country_flag_image_view);
    }

    public TextView getCountryNameTV() {
        return countryNameTV;
    }

    public TextView getCountryCapitalTV() {
        return countryCapitalTV;
    }

    public TextView getCountryContinentTV() {
        return countryContinentTV;
    }

    public ImageView getCountryFlagIV() {
        return countryFlagIV;
    }
}
