package com.example.khalil.deliverysystem.home.repository;

import com.example.khalil.deliverysystem.home.domain.interactor.GetDeliveryLists;
import com.example.khalil.deliverysystem.home.repository.DeliveryListRepository;
import com.example.khalil.deliverysystem.utils.MockDataGenerator;

public class DeliveryListRepositoryMockImp implements DeliveryListRepository {
    @Override
    public void getDeliveryList(GetDeliveryLists.RequestModel requestModel, DeliveryListCallback callback) {
        DeliveryListResponse response = new DeliveryListResponse();
        response.setDeliveryList(MockDataGenerator.generateDeliveryList(requestModel.getOffset(), requestModel.getLimit()));
        callback.onSuccess(response);
    }

    @Override
    public void dispose() {

    }
}
