package com.example.khalil.deliverysystem.home.presentation;

import com.example.khalil.deliverysystem.commons.DeliveryException;
import com.example.khalil.deliverysystem.home.domain.interactor.BaseUseCase;
import com.example.khalil.deliverysystem.home.domain.interactor.GetDeliveryLists;
import com.example.khalil.deliverysystem.home.domain.model.DeliveryModel;

import java.util.ArrayList;
import java.util.List;

public class DeliveryHomePresenter implements DeliveryHomeContract.Presenter {
    private DeliveryHomeContract.View view;
    GetDeliveryLists getDeliveryLists;
    private List<DeliveryModel> deliveryModelList = new ArrayList<>();
    private GetDeliveryListsCallback deliveryListsCallback;
    private int limit = 10;

    public DeliveryHomePresenter(DeliveryHomeContract.View view, GetDeliveryLists getDeliveryLists) {
        this.view = view;
        this.getDeliveryLists = getDeliveryLists;
        initialize();
    }

    private void initialize() {
        deliveryListsCallback = new GetDeliveryListsCallback();
        getDeliveryLists.setUseCaseCallback(deliveryListsCallback);
    }

    @Override
    public void loadDeliveryList() {
        GetDeliveryLists.RequestModel requestModel = new GetDeliveryLists.RequestModel(deliveryModelList.size(),limit);
        getDeliveryLists.setRequestValues(requestModel);
        getDeliveryLists.executeUseCase();
    }

    private class GetDeliveryListsCallback implements BaseUseCase.UseCaseCallback<GetDeliveryLists.ResponseModel, DeliveryException> {
        @Override
        public void onSuccess(GetDeliveryLists.ResponseModel response) {
            view.showDeliveryList(response.getDeliveryList());
        }

        @Override
        public void onError(DeliveryException exp) {
            view.onError(exp.getErrorMessage());
        }
    }
}
