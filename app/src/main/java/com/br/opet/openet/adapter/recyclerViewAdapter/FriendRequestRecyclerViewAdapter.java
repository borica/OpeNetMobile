package com.br.opet.openet.adapter.recyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.br.opet.openet.R;
import com.br.opet.openet.model.FriendModel;
import com.br.opet.openet.service.impl.FriendServiceImpl;
import com.squareup.picasso.Picasso;

import java.util.Iterator;
import java.util.List;

public class FriendRequestRecyclerViewAdapter extends RecyclerView.Adapter<FriendRequestRecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<FriendModel> friendsArrayList;
    FriendServiceImpl friendService;

    public FriendRequestRecyclerViewAdapter(Context mContext, List<FriendModel> friends) {
        this.mContext = mContext;
        friendsArrayList = friends;
        friendService = new FriendServiceImpl(mContext);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View v = li.inflate(R.layout.friend_request_recyclerview_row, parent, false);

        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendRequestRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.friendNameTextView.setText(friendsArrayList.get(position).getName());
        holder.courseNameTextView.setText(friendsArrayList.get(position).getCourse().getCourse());
        holder.friendId.setText(friendsArrayList.get(position).getId());

        if(friendsArrayList.get(position).getImage() != null)
            Picasso.get().load(friendsArrayList.get(position).getImage()).into(holder.friendAvatarImageview);
    }

    @Override
    public int getItemCount() {
        return friendsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView friendNameTextView;
        TextView courseNameTextView;
        TextView friendId;
        ImageView friendAvatarImageview;
        Button acceptFriendButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            friendNameTextView      = itemView.findViewById(R.id.friendNameTextView);
            courseNameTextView      = itemView.findViewById(R.id.friendCourseTextView);
            acceptFriendButton      = itemView.findViewById(R.id.acceptFriendButton);
            friendId                = itemView.findViewById(R.id.friendId);
            friendAvatarImageview   = itemView.findViewById(R.id.friendRequestAvatar);

            acceptFriendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    friendService.postAcceptFriendRequest(friendId.getText().toString(), message -> Log.e("MyViewHolder: ", message));
                    removeItemFromDataSet(friendId.getText().toString());
                    notifyDataSetChanged();
                    Intent friendsFragmentReceiverIntentAction1 = new Intent("updateFriendsView");
                    Intent friendsFragmentReceiverIntentAction2 = new Intent("updateRequestsView");
                    mContext.sendBroadcast(friendsFragmentReceiverIntentAction1);
                    mContext.sendBroadcast(friendsFragmentReceiverIntentAction2);
                }
            });
        }
    }

    public void setItems(List<FriendModel> friendList) {
        this.friendsArrayList = friendList;
    }

    private void removeItemFromDataSet(String id){
        Iterator<FriendModel> i = friendsArrayList.iterator();
        while (i.hasNext()) {
            FriendModel friend = (FriendModel) i.next();
            if(friend.getId().equals(id)) {
                i.remove();
            }
        }
    }

}
