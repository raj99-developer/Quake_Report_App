package com.example.quakereportapp;

public class Earthquake {
    private double mag;
    private String place;
    private long mTimemilliseconds;
    private String url;

    public Earthquake(double defaultmag, String defaultplace, long defaultdate, String defaulturl)
    {
        mag=defaultmag;
        place=defaultplace;
        mTimemilliseconds=defaultdate;
        url=defaulturl;
    }

    public  double getMag() {
        return mag;
    }

    public String getlocation() {
        return place;
    }

    public long getmTimemilliseconds() {
        return mTimemilliseconds;
    }

    public String getUrl() {
        return url;
    }
}
