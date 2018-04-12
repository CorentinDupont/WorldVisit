package com.corentindupont.worldvisit.ViewHolder;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.corentindupont.worldvisit.R;

/**
 * Created by Corentin on 10/04/2018.
 */

public class VisitViewHolder extends RecyclerView.ViewHolder {

    private TextView countryNameTV = null;
    private TextView countryCapitalTV = null;
    private TextView countryContinentTV = null;
    private TextView visitDateTV = null;
    private ImageView countryFlagIV = null;
    private LinearLayout deleteButtonLL = null;

    public VisitViewHolder(View itemView) {
        super(itemView);
        countryNameTV = (TextView) itemView.findViewById(R.id.country_name_text_view);
        countryCapitalTV = (TextView) itemView.findViewById(R.id.country_capital_name_text_view);
        countryContinentTV = (TextView) itemView.findViewById(R.id.country_continent_name_text_view);
        visitDateTV = (TextView) itemView.findViewById(R.id.visit_date_text_view);
        countryFlagIV = (ImageView) itemView.findViewById(R.id.country_flag_image_view);
        deleteButtonLL = (LinearLayout) itemView.findViewById(R.id.delete_button);
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

    public TextView getVisitDateTV() {
        return visitDateTV;
    }

    public ImageView getCountryFlagIV() {
        return countryFlagIV;
    }

    public LinearLayout getDeleteButtonLL() {
        return deleteButtonLL;
    }
}
