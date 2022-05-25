package com.example.baatcheet.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baatcheet.Models.userStatus;
import com.example.baatcheet.R;
import com.example.baatcheet.databinding.ItemStatusBinding;

import java.util.ArrayList;

public class TopStatusAdapter extends  RecyclerView.Adapter<TopStatusAdapter.TopStatusVIewHolder> {

    Context context;
    ArrayList<userStatus> userStatuses;

    public TopStatusAdapter(Context context , ArrayList<userStatus> userStatuses){
        this.context = context;
        this.userStatuses = userStatuses;
    }

    @NonNull
    @Override
    public TopStatusVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_status, parent , false);



        return new TopStatusVIewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopStatusVIewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return userStatuses.size();
    }

    public class TopStatusVIewHolder extends RecyclerView.ViewHolder {
        @NonNull ItemStatusBinding binding;
        public TopStatusVIewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemStatusBinding.bind(itemView);
        }
    }
}
