/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    private static final String LOG_TAG = EarthquakeActivity.class.getName();

    private static final String USGS_QUERY =
            "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    private static final int EARTHQUAKE_LOADER_ID = 1;

    private ListView mEarthquakeListView;
    // Create a new {@link ArrayAdapter} of earthquakes
    private EarthquakeAdapter mAdapter;
    private TextView mEmptyTextView;
    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        // Find a reference to the {@link ListView} in the layout
        mEarthquakeListView = (ListView) findViewById(R.id.list);
        mEmptyTextView = (TextView) findViewById(R.id.empty_text);
        mProgress = (ProgressBar) findViewById(R.id.loading_spinner);

        mAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());
        mEarthquakeListView.setAdapter(mAdapter);
        mEarthquakeListView.setEmptyView(mEmptyTextView);

        // set listitem click listener to redirect to USGS details page
        mEarthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Earthquake eq = mAdapter.getItem(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(eq.getUrl()));
                startActivity(intent);
            }
        });

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        Log.v("Earthquakeloader", "oncreateloader called");
        return new EarthquakeLoader(this, USGS_QUERY);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        mProgress.setVisibility(View.GONE);
        Log.v("Earthquakeloader", "onloadfinished called");
        mEmptyTextView.setText("No earthquakes found.");
        mAdapter.clear();


        if (earthquakes != null && !earthquakes.isEmpty()) {
            mAdapter.addAll(earthquakes);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        Log.v("Earthquakeloader", "onloadreset called");
        mAdapter.clear();
    }

}
