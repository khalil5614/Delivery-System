package com.example.khalil.deliverysystem.home.domain.interactor;

import com.example.khalil.deliverysystem.commons.DeliveryException;
import com.example.khalil.deliverysystem.home.repository.DeliveryListRepository;

public class GetDeliveryLists extends BaseUseCase<GetDeliveryLists.RequestModel, GetDeliveryLists.ResponseModel, DeliveryException>
        implements DeliveryListRepository.DeliveryListCallback {

    @Override
    public void onSuccess(DeliveryListRepository.DeliveryListResponse deliveryListResponse) {

    }

    @Override
    public void onError(DeliveryException exp) {

    }

    @Override
    public void executeUseCase() {

    }

    @Override
    public void dispose() {

    }

    class RequestModel {

    }

    class ResponseModel {
    }
}
