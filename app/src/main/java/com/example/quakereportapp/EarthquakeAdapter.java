package com.example.quakereportapp;
import android.graphics.drawable.GradientDrawable;
import java.text.DecimalFormat;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{

    private static final String LOCATION_SEPARATOR="of";
    public EarthquakeAdapter(Context context, List<Earthquake>earthquakes)
    {
        super(context,0,earthquakes);
    }
    private String formatDate(Date dateobject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateobject);
    }

    private String formatMagnitude(double magnitude)
    {
        DecimalFormat magnitudeformat= new DecimalFormat("0.0");
        return magnitudeformat.format(magnitude);
    }
    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateobject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateobject);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
    public View getView(int position, View convetView, ViewGroup parent)
    {
        View lisitemview=convetView;
        if(lisitemview==null)
        {
            lisitemview= LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }


        // find the earthquake at the given postion in the list of item
        Earthquake currentEarthquake=getItem(position);

        // find the textview with magnitude id
        TextView magnitude=(TextView)lisitemview.findViewById(R.id.textview1);
        // Format the magnitude to show 1 decimal place
        String fomattedmagnitude=formatMagnitude(currentEarthquake.getMag());
        //display the magnitude of current earthquake in that textview
        magnitude.setText(fomattedmagnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        String originalLocation=currentEarthquake.getlocation();

        String primarylocation;
        String locationoffset;

        if(originalLocation.contains(LOCATION_SEPARATOR))
        {
            String[] parts=originalLocation.split(LOCATION_SEPARATOR);
            locationoffset=parts[0]+LOCATION_SEPARATOR;
            primarylocation=parts[1];
        }
        else
        {
            locationoffset=getContext().getString(R.string.near_the);
            primarylocation=originalLocation;
        }


        // find the textview with offset id
        TextView locationOffsetView=(TextView)lisitemview.findViewById(R.id.location_offset);
        //display the place of current earthquake in that textview
        locationOffsetView.setText(locationoffset);

        // find the textview with location id
        TextView primaryLocationView=(TextView)lisitemview.findViewById(R.id.primary_location);
        //display the place of current earthquake in that textview
        primaryLocationView.setText(primarylocation);


        //create a new Date object from the time in milliseconds of the earthquake
        Date dateobject= new Date(currentEarthquake.getmTimemilliseconds());

        // Find the TextView with view ID date
        TextView dateView = (TextView) lisitemview.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateobject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);


        return lisitemview;
    }

}
