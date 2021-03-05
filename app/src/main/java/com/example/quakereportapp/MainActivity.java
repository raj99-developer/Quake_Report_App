package com.example.quakereportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static com.example.quakereportapp.QueryUtils.extractEarthquakes;

public class MainActivity extends AppCompatActivity {

    //public static final LOG_TAG=MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a fake list of earthquake locations.
        ArrayList<Earthquake>earthquakes= null;
        try {
            earthquakes = QueryUtils.extractEarthquakes();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        earthquakes.add(new Earthquake("7.9","San Fransisco", "Feb 2, 2015"));
//        earthquakes.add(new Earthquake("6.9","London", "Feb 3, 2015"));
//        earthquakes.add(new Earthquake("5.6","Mexico", "Feb 9, 2016"));
//        earthquakes.add(new Earthquake("2.9","Tockyo", "March 2, 2017"));
//        earthquakes.add(new Earthquake("3.5","Moscow", "April 9, 2018"));
//        earthquakes.add(new Earthquake("4.4","Paris", "May 5, 2019"));
//        earthquakes.add(new Earthquake("8.0","Rio de janerio", "June 22, 2020"));

        ListView earthquakelistview=(ListView)findViewById(R.id.list);
        final ArrayAdapter<Earthquake> adapter= new EarthquakeAdapter(this, earthquakes);

        // Create a new adapter that takes the list of earthquakes as input
        final EarthquakeAdapter adapter1= new EarthquakeAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakelistview.setAdapter(adapter1);
        earthquakelistview.setAdapter(adapter);

        earthquakelistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Earthquake currentearthquake= adapter1.getItem(i);
                assert currentearthquake != null;
                Uri earthquakeuri= Uri.parse(currentearthquake.getUrl());
                Intent webIntent= new Intent(Intent.ACTION_VIEW, earthquakeuri);
                startActivity(webIntent);

            }
        });
    }
}