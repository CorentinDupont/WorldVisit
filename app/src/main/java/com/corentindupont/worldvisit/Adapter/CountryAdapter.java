package com.corentindupont.worldvisit.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.corentindupont.worldvisit.Database.DAO.DAOVisit;
import com.corentindupont.worldvisit.Entity.Country;
import com.corentindupont.worldvisit.Entity.Visit;
import com.corentindupont.worldvisit.R;
import com.corentindupont.worldvisit.UsefulMethods;
import com.corentindupont.worldvisit.ViewHolder.CountryViewHolder;
import com.corentindupont.worldvisit.ViewHolder.VisitViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Corentin on 10/04/2018.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryViewHolder>{

    //Visit Entity List
    private List<Country> countryList = null;
    //Application context, for Picasso
    private Context context;

    //Constructor
    public CountryAdapter(List<Country> countryList, Context context) {
        this.countryList = countryList;
        this.context = context;
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the item view of the list
        View countryView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(countryView);
    }

    @Override
    public void onBindViewHolder(final CountryViewHolder holder, int position) {
        //Set value to the different view with the corresponding entity
        holder.getCountryNameTV().setText(countryList.get(position).getName());
        holder.getCountryCapitalTV().setText(countryList.get(position).getCapital());
        holder.getCountryContinentTV().setText(countryList.get(position).getContinent());
        //using picasso, get the image at URL, and put it into the image view
        Picasso.with(context).load(countryList.get(position).getAlpha2CodeUrl()).fit().centerCrop().into(holder.getCountryFlagIV());
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    private Country getVisitAtPosition(int position){
        return countryList.get(position);
    }
}
