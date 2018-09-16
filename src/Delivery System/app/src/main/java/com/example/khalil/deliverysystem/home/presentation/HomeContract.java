package com.example.khalil.deliverysystem.home.presentation;

public interface HomeContract {
    interface View {
        void showDeliveryList();
    }

    interface Presenter {
        void getDeliveryList(int offset);
    }
}
