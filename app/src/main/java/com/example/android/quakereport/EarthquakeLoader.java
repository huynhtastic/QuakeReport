package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.util.Log;

import java.net.URL;
import java.util.List;

/**
 * Created by Richard Huynh on 9/8/2016.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    String mUrl;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
        Log.v("Earthquakeloader", "constructor called");
    }

    @Override
    protected void onStartLoading() {
        Log.v("Earthquakeloader", "onstartloading called");
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        Log.v("Earthquakeloader", "loadinbackground called");
        if (mUrl != null)   { return QueryUtils.fetchEarthquakeData(mUrl); }
        else                { return null; }
    }

}