package com.corentindupont.worldvisit.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.corentindupont.worldvisit.Adapter.VisitAdapter;
import com.corentindupont.worldvisit.Entity.Country;
import com.corentindupont.worldvisit.Entity.Visit;
import com.corentindupont.worldvisit.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the recycler view from the layout
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //best performance
        recyclerView.setHasFixedSize(true);

        //Define how items are organized
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create example content
        List<Visit> visitListEx = new ArrayList<>();
        for(int i=0; i<10; i++){
            Country country = new Country("Paraguay", "Capitaine", "Europa Park", "https://restcountries.eu/data/pry.svg");
            Visit visit = new Visit(country, new Date());
            visitListEx.add(visit);
        }

        //Add adapter
        VisitAdapter adapter = new VisitAdapter(visitListEx, this);
        recyclerView.setAdapter(adapter);
    }
}
