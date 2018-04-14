package com.corentindupont.worldvisit.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.corentindupont.worldvisit.Constant;
import com.corentindupont.worldvisit.Database.DAO.DAOVisit;
import com.corentindupont.worldvisit.Entity.Country;
import com.corentindupont.worldvisit.Entity.Visit;
import com.corentindupont.worldvisit.R;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Date;

public class VisitDatePickerActivity extends AppCompatActivity implements View.OnClickListener{

    Country selectedCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_date_picker);

        //getting country from previous activity
        String jsonCountry;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonCountry = extras.getString(Constant.COUNTRY_OBJECT);
            selectedCountry = new Gson().fromJson(jsonCountry, Country.class);
        }

        Button validateDateButton = findViewById(R.id.validate_date_button);
        validateDateButton.setOnClickListener(this);


    }
    public static Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.validate_date_button:
                //get the corresponding country, and transform it to json, to pass it to the next activity.
                Intent activity = new Intent(VisitDatePickerActivity.this,MainActivity.class);
                DatePicker datePicker = findViewById(R.id.date_picker);
                Visit visit = new Visit(selectedCountry, getDateFromDatePicker(datePicker));
                DAOVisit daoVisit = new DAOVisit(VisitDatePickerActivity.this);
                daoVisit.insertVisit(visit);
                activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(activity);
        }
    }
}
