package com.corentindupont.worldvisit.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.corentindupont.worldvisit.Entity.Visit;
import com.corentindupont.worldvisit.R;
import com.corentindupont.worldvisit.UsefulMethods;
import com.corentindupont.worldvisit.ViewHolder.VisitViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Corentin on 10/04/2018.
 */

public class VisitAdapter extends RecyclerView.Adapter<VisitViewHolder>{

    //Visit Entity List
    private List<Visit> visitList = null;
    //Application context, for Picasso
    private Context context;

    //Constructor
    public VisitAdapter(List<Visit> visitList, Context context) {
        this.visitList = visitList;
        this.context = context;
    }

    @Override
    public VisitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the item view of the list
        View visitView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_visit, parent, false);
        return new VisitViewHolder(visitView);
    }

    @Override
    public void onBindViewHolder(VisitViewHolder holder, int position) {
        //Set value to the different view with the corresponding entity
        holder.getCountryNameTV().setText(visitList.get(position).getCountry().getName());
        holder.getCountryCapitalTV().setText(visitList.get(position).getCountry().getCapital());
        holder.getCountryContinentTV().setText(visitList.get(position).getCountry().getContinent());
        //using picasso, get the image at URL, and put it into the image view
        Picasso.with(context).load(visitList.get(position).getCountry().getFlagSvgUrl()).fit().centerCrop().into(holder.getCountryFlagIV());
        //transform visit date in String with a "useful" method.
        holder.getVisitDateTV().setText(UsefulMethods.dateToString(visitList.get(position).getDate(),Visit.getDateFormat()));
    }

    @Override
    public int getItemCount() {
        return visitList.size();
    }
}
