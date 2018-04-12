package com.corentindupont.worldvisit.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.corentindupont.worldvisit.Adapter.CountryAdapter;
import com.corentindupont.worldvisit.Adapter.VisitAdapter;
import com.corentindupont.worldvisit.Database.DAO.DAOCountry;
import com.corentindupont.worldvisit.Database.DAO.DAOVisit;
import com.corentindupont.worldvisit.Entity.Country;
import com.corentindupont.worldvisit.Entity.Visit;
import com.corentindupont.worldvisit.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class AddCountryActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener{

    RecyclerView recyclerView;
    DAOCountry daoCountry;
    CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_country);

        //initialize DAOs
        daoCountry = new DAOCountry(AddCountryActivity.this);

        //get the recycler view from the layout
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //best performance
        recyclerView.setHasFixedSize(true);

        //Define how items are organized
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Insert countries in database with the API.
        downloadCountriesFromAPI();
        //select all countries from database
        List<Country> countryList = daoCountry.selectAllCountries();

        //Add adapter
        countryAdapter = new CountryAdapter(countryList, this);
        recyclerView.setAdapter(countryAdapter);

        // listener :
        recyclerView.addOnItemTouchListener(AddCountryActivity.this);
    }

    private void downloadCountriesFromAPI(){
        /*AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://restcountries.eu/rest/v2/all", new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
                Toast.makeText(AddCountryActivity.this, "Téléchargement des pays ...", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //called if request failed
                Toast.makeText(AddCountryActivity.this, "Echec du chargement des pays.", Toast.LENGTH_SHORT).show();
                Log.i("COUNTRY", "download error : " + error);
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                //called if request succeed
                String responseString = new String(response);
                try {
                    JSONArray jsonArray = new JSONArray(responseString);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Country country = new Country(
                                jsonObject.getString("name"),
                                jsonObject.getString("capital"),
                                jsonObject.getString("region"),
                                Country.getBaseFlagUrl() + jsonObject.getString("alpha2Code") + ".png"
                        );
                        if(daoCountry.selectCountryByName(country.getName()) == null){
                            daoCountry.insertCountry(country);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });*/

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://restcountries.eu/rest/v2/all";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //called if request succeed
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Log.i("COUNTRY", String.valueOf(jsonObject));
                                Country country = new Country(
                                        jsonObject.getString("name"),
                                        jsonObject.getString("capital"),
                                        jsonObject.getString("region"),
                                        Country.getBaseFlagUrl() + jsonObject.getString("alpha2Code") + ".png"
                                );

                                Log.i("COUNTRY", String.valueOf(daoCountry.selectCountryByName(country.getName()).getId()));
                                //if the country doesn't already exist in database
                                if(daoCountry.selectCountryByName(country.getName()).getId() == 0){
                                    daoCountry.insertCountry(country);
                                    Log.i("COUNTRY", "new country added in database !!");

                                }
                                countryAdapter.notifyItemInserted(countryAdapter.getItemCount()-1);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddCountryActivity.this, "Echec du chargement des pays.", Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
