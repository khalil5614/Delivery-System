package com.example.khalil.deliverysystem.home.presentation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.khalil.deliverysystem.R;
import com.example.khalil.deliverysystem.home.domain.model.DeliveryModel;

import java.util.Collections;
import java.util.List;


public class DeliveryRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<DeliveryModel> deliveryModelList = Collections.emptyList();

    private DeliveryItemClickListener deliveryItemClickListener;

    public interface DeliveryItemClickListener {
        void onItemClick(DeliveryModel deliveryModel);
    }

    public DeliveryRecyclerAdapter() {
    }

    public void setDeliveryItemClickListener(DeliveryItemClickListener deliveryItemClickListener) {
        this.deliveryItemClickListener = deliveryItemClickListener;
    }

    public void setDeliveryModelList(List<DeliveryModel> deliveryModelList) {
        this.deliveryModelList = deliveryModelList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.delivery_item_layout, parent, false);
        return new DeliveryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        DeliveryViewHolder viewHolder = (DeliveryViewHolder) holder;
        final DeliveryModel deliveryModel = deliveryModelList.get(position);
        viewHolder.descriptionTV.setText(deliveryModel.getmDescription());
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deliveryItemClickListener.onItemClick(deliveryModel);
            }
        });
        Glide.with(viewHolder.layout.getContext())
                .load(deliveryModel.getmThumbImage())
                .apply(new RequestOptions().override(100, 100).centerCrop())
                .into(viewHolder.receiverImageImv);
    }

    @Override
    public int getItemCount() {
        return deliveryModelList.size();
    }
}
