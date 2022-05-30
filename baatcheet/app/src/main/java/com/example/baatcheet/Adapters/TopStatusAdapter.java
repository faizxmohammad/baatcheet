package com.example.baatcheet.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baatcheet.Activities.MainActivity;
import com.example.baatcheet.Models.Status;
import com.example.baatcheet.Models.userStatus;
import com.example.baatcheet.R;
import com.example.baatcheet.databinding.ItemStatusBinding;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Predicates;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Iterables;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import omari.hamza.storyview.StoryView;
import omari.hamza.storyview.callback.StoryClickListeners;
import omari.hamza.storyview.model.MyStory;
import xute.storyview.StoryModel;

public class TopStatusAdapter extends  RecyclerView.Adapter<TopStatusAdapter.TopStatusVIewHolder> {

    Context context;
    private  ArrayList<userStatus> userStatuses;

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
        userStatus userStatus = userStatuses.get(position);

        Status lastStatus = userStatus.getStatuses().get(userStatus.getStatuses().size() - 1);

        Glide.with(context).load(lastStatus.getImageUrl()).into(holder.binding.image);
        holder.binding.circularStatusView.setPortionsCount(userStatus.getStatuses().size());


        holder.binding.circularStatusView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<MyStory> myStories = new ArrayList<MyStory>();
                userStatus.getStatuses().removeAll(Collections.singletonList(null));

    for (Status status : userStatus.getStatuses()) {

        myStories.add(new MyStory(
                status.getImageUrl()

        ));
    }



                new StoryView.Builder(((MainActivity)context).getSupportFragmentManager())
                        .setStoriesList(myStories) // Required
                        .setStoryDuration(10000) // Default is 2000 Millis (2 Seconds)
                        .setTitleText(userStatus.getName()) // Default is Hidden
                        .setSubtitleText("") // Default is Hidden
                        .setTitleLogoUrl(userStatus.getProfileImage()) // Default is Hidden
                        .setStoryClickListeners(new StoryClickListeners() {
                            @Override
                            public void onDescriptionClickListener(int position) {
                                //your action
                            }

                            @Override
                            public void onTitleIconClickListener(int position) {
                                //your action
                            }
                        }) // Optional Listeners
                        .build() // Must be called before calling show method
                        .show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return userStatuses.size();
    }

    public class TopStatusVIewHolder extends RecyclerView.ViewHolder {

        ItemStatusBinding binding;
        public TopStatusVIewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemStatusBinding.bind(itemView);
        }
    }
}
