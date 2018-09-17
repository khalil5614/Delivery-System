package com.example.khalil.deliverysystem.home.domain.model;

import java.io.Serializable;

public class LocationModel implements Serializable {
    private double mLattitude;
    private double mLongitude;
    private String mAddress;

    public double getmLattitude() {
        return mLattitude;
    }

    public void setmLattitude(double mLattitude) {
        this.mLattitude = mLattitude;
    }

    public double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }
}
