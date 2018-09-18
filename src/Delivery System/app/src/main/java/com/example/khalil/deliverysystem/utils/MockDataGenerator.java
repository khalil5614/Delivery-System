package com.example.khalil.deliverysystem.utils;

import com.example.khalil.deliverysystem.home.domain.model.DeliveryModel;
import com.example.khalil.deliverysystem.home.domain.model.LocationModel;

import java.util.ArrayList;
import java.util.List;

public class MockDataGenerator {
    public static List<DeliveryModel> generateDeliveryList(int offset, int limit) {

        List<DeliveryModel> deliveryModels = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            int id = offset + i;
            DeliveryModel deliveryModel = new DeliveryModel();
            deliveryModel.setmId(id);
            deliveryModel.setmDescription("Delivery Item description of item " + id);
            deliveryModel.setmThumbImage("https://www.what-dog.net/Images/faces2/scroll0015.jpg");

            LocationModel address = new LocationModel();
            address.setmAddress("Address of item " + id);
            address.setmLattitude(22.336093+i*.0005);
            address.setmLongitude(114.155288+i*.0005);
            deliveryModel.setmLocation(address);
            deliveryModels.add(deliveryModel);
        }
        return deliveryModels;
    }
}
