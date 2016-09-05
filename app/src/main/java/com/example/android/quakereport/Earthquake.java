package com.example.android.quakereport;

/**
 * Created by Richard Huynh on 9/4/2016.
 */
public class Earthquake {

    private double mMagnitude;
    private String mPlace;
    private long mDate;

    /**
     * Constructs new {@link Earthquake} object
     * @param magnitude size/magnitude of earthquake
     * @param place place where earthquake occurred
     * @param date date when earthquake occurred
     */
    public Earthquake(double magnitude, String place, long date) {
        mMagnitude = magnitude;
        mPlace = place;
        mDate = date;
    }

    /**
     * Returns the magnitude of the earthquake
     * @return magnitude; type double
     */
    public double getMagnitude() {
        return mMagnitude;
    }

    /**
     * Returns where the earthquake takes place
     * @return place; type String
     */
    public String getPlace() {
        return mPlace;
    }

    /**
     * Returns when the earthquake took place
     * @return date; type long
     */
    public long getDate() {
        return mDate;
    }
}
