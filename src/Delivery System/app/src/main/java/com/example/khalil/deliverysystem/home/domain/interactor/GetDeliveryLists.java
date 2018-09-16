package com.example.khalil.deliverysystem.home.domain.interactor;

import com.example.khalil.deliverysystem.commons.DeliveryException;
import com.example.khalil.deliverysystem.home.domain.model.DeliveryModel;
import com.example.khalil.deliverysystem.home.repository.DeliveryListRepository;

import java.util.List;

public class GetDeliveryLists extends BaseUseCase<GetDeliveryLists.RequestModel, GetDeliveryLists.ResponseModel, DeliveryException>
        implements DeliveryListRepository.DeliveryListCallback {
    private DeliveryListRepository repository;

    public GetDeliveryLists(DeliveryListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onSuccess(DeliveryListRepository.DeliveryListResponse deliveryListResponse) {
        mUseCaseCallback.onSuccess(new ResponseModel(deliveryListResponse.deliveryList));
    }

    @Override
    public void onError(DeliveryException exp) {
        mUseCaseCallback.onError(exp);
    }

    @Override
    public void executeUseCase() {
        repository.getDeliveryList(mRequestModel, this);
    }

    @Override
    public void dispose() {
        repository.dispose();
    }

    public static class RequestModel {
        private int offset;

        public RequestModel(int offset) {
            this.offset = offset;
        }

        public int getOffset() {
            return offset;
        }
    }

    public class ResponseModel {
        private final List<DeliveryModel> deliveryList;

        public ResponseModel(List<DeliveryModel> deliveryList) {
            this.deliveryList = deliveryList;
        }

        public List<DeliveryModel> getDeliveryList() {
            return deliveryList;
        }
    }
}
