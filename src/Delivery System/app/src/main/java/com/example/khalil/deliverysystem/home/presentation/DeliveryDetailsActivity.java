package com.example.khalil.deliverysystem.home.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.khalil.deliverysystem.R;
import com.example.khalil.deliverysystem.home.domain.model.DeliveryModel;
import com.example.khalil.deliverysystem.home.domain.model.NamedLocation;
import com.example.khalil.deliverysystem.utils.Constants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class DeliveryDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {
    DeliveryModel deliveryItem;
    private MapView locationMapView;
    private GoogleMap locationMap;
    public TextView descriptionTV;
    public ImageView receiverImageImv;
    private boolean isLocationSetted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_details);
        initToolbar();
        initView();
    }

    void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.delivery_details);
    }

    void initView() {

        locationMapView = findViewById(R.id.location_mv);
        descriptionTV = findViewById(R.id.description_tv);
        receiverImageImv = findViewById(R.id.receiver_image_imv);

        Intent intent = getIntent();
        if (intent != null) {
            deliveryItem = (DeliveryModel) intent.getSerializableExtra(Constants.DELIVERY_ITEM_DETAILS);

            if (deliveryItem != null) {
                locationMapView.onCreate(null);
                locationMapView.getMapAsync(this);


                descriptionTV.setText(deliveryItem.getmDescription());
                Glide.with(DeliveryDetailsActivity.this)
                        .load(deliveryItem.getmThumbImage())
                        .apply(new RequestOptions().override(100, 100).centerCrop())
                        .into(receiverImageImv);
                setLocationMap();

            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        locationMap = googleMap;
        MapsInitializer.initialize(DeliveryDetailsActivity.this);
        if(!isLocationSetted){
            setLocationMap();
        }
    }

    public void setLocationMap() {
        NamedLocation namedLocation = null;
        if (deliveryItem.getmLocation() != null) {
            LatLng loc = new LatLng(deliveryItem.getmLocation().getmLattitude(), deliveryItem.getmLocation().getmLongitude());
            namedLocation = new NamedLocation();
            namedLocation.setLocation(loc);
            namedLocation.setLocationName(deliveryItem.getmLocation().getmAddress());
        }
        if (locationMap != null && namedLocation != null) {
         /*   locationMap.moveCamera(CameraUpdateFactory.newLatLngZoom(namedLocation.getLocation(), 16f));
            Marker marker = locationMap.addMarker(new MarkerOptions().position(namedLocation.getLocation()).title(namedLocation.getLocationName()).infoWindowAnchor(0.5f, 0.5f));
            marker.showInfoWindow();
            locationMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);*/
            isLocationSetted = true;
            locationMap.moveCamera(CameraUpdateFactory.newLatLngZoom(namedLocation.getLocation(), 16f));
            locationMap.getUiSettings().setMapToolbarEnabled(false);
            Marker marker = locationMap.addMarker(new MarkerOptions().position(namedLocation.getLocation()).title(namedLocation.getLocationName()).infoWindowAnchor(0.5f, 0.5f));
            marker.showInfoWindow();
            // Set the map type back to normal.
            locationMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            locationMapView.setVisibility(View.VISIBLE);

        }
    }
}
