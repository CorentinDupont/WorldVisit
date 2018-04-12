package com.corentindupont.worldvisit.Activity;

import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.corentindupont.worldvisit.Adapter.VisitAdapter;
import com.corentindupont.worldvisit.Database.DAO.DAOCountry;
import com.corentindupont.worldvisit.Database.DAO.DAOVisit;
import com.corentindupont.worldvisit.Entity.Country;
import com.corentindupont.worldvisit.Entity.Visit;
import com.corentindupont.worldvisit.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    RecyclerView recyclerView;
    DAOCountry daoCountry;
    DAOVisit daoVisit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize DAOs
        daoCountry = new DAOCountry(MainActivity.this);
        daoVisit = new DAOVisit(MainActivity.this);

        //get the recycler view from the layout
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //best performance
        recyclerView.setHasFixedSize(true);

        //Define how items are organized
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create example content
        /*for(int i=0; i<10; i++){
            Country country = new Country("Paraguay", "Capitaine", "Europa Park", Country.getBaseFlagUrl()+"FR.png");
            Log.i("COUNTRY", "flag url : "+country.getAlpha2CodeUrl());
            daoCountry.insertCountry(country);
            Visit visit = new Visit(daoCountry.selectCountry(1), new Date());
            Log.i("COUNTRY", String.valueOf(daoCountry.selectCountry(1).getId()));
            daoVisit.insertVisit(visit);
        }*/

        List<Visit> visitListEx = daoVisit.selectAllVisits();

        //Add adapter
        VisitAdapter adapter = new VisitAdapter(visitListEx, this);
        recyclerView.setAdapter(adapter);

        //Button add new visit
        Button buttonAddVisit = (Button) findViewById(R.id.add_country_button);
        buttonAddVisit.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.add_country_button:
                Intent intent = new Intent(MainActivity.this, AddCountryActivity.class);
                startActivity(intent);
        }
    }
}
