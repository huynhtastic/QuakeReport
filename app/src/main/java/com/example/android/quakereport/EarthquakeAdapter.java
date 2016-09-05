package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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
        TextView placeText = (TextView) earthquakeItemView.findViewById(R.id.place_text);
        TextView dateText = (TextView) earthquakeItemView.findViewById(R.id.date_text);

        // set text
        magText.setText(Double.toString(earthquake.getMagnitude()));
        placeText.setText(earthquake.getPlace());
        dateText.setText(earthquake.getDate());

        return earthquakeItemView;
    }
}
