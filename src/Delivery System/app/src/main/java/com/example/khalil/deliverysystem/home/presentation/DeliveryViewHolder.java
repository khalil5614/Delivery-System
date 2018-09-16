package com.example.khalil.deliverysystem.home.presentation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khalil.deliverysystem.R;


public class DeliveryViewHolder extends RecyclerView.ViewHolder {

    public View layout;
    public TextView descriptionTV;
    public ImageView deliveryItemImv;


    public DeliveryViewHolder(View itemView) {
        super(itemView);
        layout = itemView;
        descriptionTV = (TextView) itemView.findViewById(R.id.description_tv);
        deliveryItemImv = (ImageView) itemView.findViewById(R.id.delivery_item_imv);
    }
}
