package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Richard Huynh on 9/4/2016.
 */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View earthquakeItemView = convertView;
        if (earthquakeItemView == null) {
            earthquakeItemView = LayoutInflater.from(getContext()).inflate(
                R.layout.earthquake_item, parent, false);
        }

        // work like normal
        Earthquake earthquake = getItem(position);

        // grab textviews
        TextView magText = (TextView) earthquakeItemView.findViewById(R.id.mag_text);
        TextView proximityText = (TextView) earthquakeItemView.findViewById(
                R.id.proximity_text);
        TextView primaryLocationText = (TextView) earthquakeItemView.findViewById(
                R.id.primary_location_text);
        TextView dateText = (TextView) earthquakeItemView.findViewById(R.id.date_text);
        TextView timeText = (TextView) earthquakeItemView.findViewById(R.id.time_text);

        // format decimal to tenths place
        double mag = earthquake.getMagnitude();
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        magText.setText(decimalFormat.format(mag));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magText.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(earthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        // split place to have primary location and proximity
        String place = earthquake.getPlace();
        if (place.contains("of")) {
            String[] parts = place.split("(?<= of )", 2); // lookbehind split by ' of '
            proximityText.setText(parts[0]);
            primaryLocationText.setText(parts[1].trim());
        } else {
            proximityText.setText("Near the");
            primaryLocationText.setText(place);
        }

        // convert long to date
        SimpleDateFormat formatDate = new SimpleDateFormat("MMM dd, yyyy");
        SimpleDateFormat formatTime = new SimpleDateFormat("h:mm a");
        Date date = new Date(earthquake.getDate());
        dateText.setText(formatDate.format(date));
        timeText.setText(formatTime.format(date));

        return earthquakeItemView;
    }

    private int getMagnitudeColor(double magnitude) {

        // determine which colors will be assigned to what magnitudes
        int magFloor = (int) Math.floor(magnitude);
        int magnitudeColor;
        switch (magFloor) {
            case 0:
            case 1:
                magnitudeColor = R.color.magnitude1;
                break;
            case 2:
                magnitudeColor = R.color.magnitude2;
                break;
            case 3:
                magnitudeColor = R.color.magnitude3;
                break;
            case 4:
                magnitudeColor = R.color.magnitude4;
                break;
            case 5:
                magnitudeColor = R.color.magnitude5;
                break;
            case 6:
                magnitudeColor = R.color.magnitude6;
                break;
            case 7:
                magnitudeColor = R.color.magnitude7;
                break;
            case 8:
                magnitudeColor = R.color.magnitude8;
                break;
            case 9:
                magnitudeColor = R.color.magnitude9;
                break;
            default:
                magnitudeColor = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColor);

    }
}
