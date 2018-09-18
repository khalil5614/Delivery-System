package com.example.khalil.deliverysystem.home.presentation;

import com.example.khalil.deliverysystem.home.domain.model.DeliveryModel;

import java.util.List;

public interface DeliveryHomeContract {
    interface View {
        void showDeliveryList(List<DeliveryModel> deliveryList);
        void showChangeDeliveryList();
        void onError(String message);

    }

    interface Presenter {
        void loadDeliveryList();
    }
}
