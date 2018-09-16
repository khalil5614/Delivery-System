package com.example.khalil.deliverysystem.home.domain.model;

public class DeliveryModel {
    private int mId;
    private String mDescription;
    private String mThumbImage;
    private LocationModel mLocation;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmThumbImage() {
        return mThumbImage;
    }

    public void setmThumbImage(String mThumbImage) {
        this.mThumbImage = mThumbImage;
    }

    public LocationModel getmLocation() {
        return mLocation;
    }

    public void setmLocation(LocationModel mLocation) {
        this.mLocation = mLocation;
    }
}
