package com.example.khalil.deliverysystem.home.domain.model;

import com.google.android.gms.maps.model.LatLng;

public class NamedLocation {
    private String locationName;
    private LatLng location;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }
}
