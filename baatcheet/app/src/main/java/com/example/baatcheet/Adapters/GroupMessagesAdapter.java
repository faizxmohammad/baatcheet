package com.example.baatcheet.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baatcheet.Models.Message;
import com.example.baatcheet.Models.User;
import com.example.baatcheet.R;
import com.example.baatcheet.databinding.ItemReceiveBinding;
import com.example.baatcheet.databinding.ItemReceiveGroupBinding;
import com.example.baatcheet.databinding.ItemSentBinding;
import com.example.baatcheet.databinding.ItemSentGroupBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GroupMessagesAdapter extends RecyclerView.Adapter {

    Context context ;
    ArrayList<Message> messages;

    final int ITEM_SENT = 1;
    final int ITEM_RECEIVE = 2;

    public GroupMessagesAdapter(Context context, ArrayList<Message> messages){
        this.context = context;
        this.messages = messages;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == ITEM_SENT){
            View view = LayoutInflater.from(context).inflate(R.layout.item_sent_group , parent , false);
            return new SentViewHolder(view);
        }
        else{
            View view = LayoutInflater.from(context).inflate(R.layout.item_receive_group , parent , false);
            return new ReceiverViewHolder(view);

        }

    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);
        if(FirebaseAuth.getInstance().getUid().equals(message.getSenderId())){
            return ITEM_SENT;
        }else{
            return  ITEM_RECEIVE;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);
        if(holder.getClass() == SentViewHolder.class){
            SentViewHolder viewHolder = (SentViewHolder) holder;

            if(message.getMessage().equals("photo")){
                viewHolder.binding.image.setVisibility(View.VISIBLE);
                viewHolder.binding.message.setVisibility(View.GONE);


                Glide.with(context)
                        .load(message.getImageUrl())
                        .placeholder(R.drawable.placeholder)
                        .into(viewHolder.binding.image);

            }
            FirebaseDatabase.getInstance()
                            .getReference().child("users")
                            .child(message.getSenderId())
                                    .addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if(snapshot.exists()){
                                                User user = snapshot.getValue(User.class);
                                                viewHolder.binding.name.setText("@ "+user.getName());
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });





            viewHolder.binding.message.setText(message.getMessage());



        }else {
            ReceiverViewHolder viewHolder = (ReceiverViewHolder) holder;
            if(message.getMessage().equals("photo")){
                viewHolder.binding.image.setVisibility(View.VISIBLE);
                viewHolder.binding.message.setVisibility(View.GONE);


                Glide.with(context)
                        .load(message.getImageUrl())
                        .placeholder(R.drawable.placeholder)
                        .into(viewHolder.binding.image);

            }

            FirebaseDatabase.getInstance()
                    .getReference().child("users")
                    .child(message.getSenderId())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                User user = snapshot.getValue(User.class);
                                viewHolder.binding.name.setText("@ "+user.getName());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



            viewHolder.binding.message.setText(message.getMessage());
        }

    }

    @Override
    public int getItemCount() {

        return messages.size();
    }

    public class SentViewHolder extends RecyclerView.ViewHolder{
        ItemSentGroupBinding binding;
        public SentViewHolder(@NonNull View itemView) {

            super(itemView);
            binding = ItemSentGroupBinding.bind(itemView);
        }
    }
    public class ReceiverViewHolder extends RecyclerView.ViewHolder{
        ItemReceiveGroupBinding binding;
        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemReceiveGroupBinding.bind(itemView);
        }
    }
}
