package com.example.khalil.deliverysystem.home.domain.interactor;


public abstract class BaseUseCase<Q, R,E> {

    protected Q mRequestModel;

    protected UseCaseCallback<R,E> mUseCaseCallback;

    public void setRequestValues(Q requestModel) {
        mRequestModel = requestModel;
    }

    public void setUseCaseCallback(UseCaseCallback<R,E> useCaseCallback) {
        mUseCaseCallback = useCaseCallback;
    }

    public abstract void executeUseCase();
    public abstract void dispose();


    public interface UseCaseCallback<R,E> {
        void onSuccess(R response);
        void onError(E exp);
    }
}
