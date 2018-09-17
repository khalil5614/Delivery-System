package com.example.khalil.deliverysystem.home.domain.model;


import java.io.Serializable;

public class DeliveryModel implements Serializable {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        else if (obj instanceof DeliveryModel) {
            DeliveryModel model = (DeliveryModel) obj;
            if (this.mId == model.getmId())
                return true;
        }
        return false;
    }
}
