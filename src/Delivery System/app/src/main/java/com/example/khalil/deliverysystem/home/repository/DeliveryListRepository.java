package com.example.khalil.deliverysystem.home.repository;

import com.example.khalil.deliverysystem.commons.DeliveryException;
import com.example.khalil.deliverysystem.home.domain.model.DeliveryModel;

import java.util.List;

public interface DeliveryListRepository {

    interface DeliveryListCallback {
        void onSuccess(DeliveryListResponse deliveryListResponse);
        void onError(DeliveryException exp);
    }

    class DeliveryListResponse {
        public List<DeliveryModel> coinsForSale;
        public int totalResult;
    }
    void getDeliveryList(int offset);

    void dispose();
}
