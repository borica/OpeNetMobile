package com.br.opet.openet.adapter.recyclerViewAdapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.br.opet.openet.R;
import com.br.opet.openet.fragment.dashboardFragments.FriendsFragment;
import com.br.opet.openet.listener.FriendRequestListener;
import com.br.opet.openet.model.FriendModel;
import com.br.opet.openet.service.impl.FriendServiceImpl;
import com.br.opet.openet.util.ComponentUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Iterator;
import java.util.List;

public class FriendsSuggestRecyclerViewAdapter extends RecyclerView.Adapter<FriendsSuggestRecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<FriendModel> friendsArrayList;
    FriendServiceImpl friendService;

    public FriendsSuggestRecyclerViewAdapter(Context mContext, FriendServiceImpl friendService, List<FriendModel> friends) {
        this.mContext = mContext;
        this.friendsArrayList = friends;
        this.friendService = friendService;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View v = li.inflate(R.layout.friends_suggest_recyclerview_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsSuggestRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.friendNameTextView.setText(friendsArrayList.get(position).getName());
        holder.courseNameTextView.setText(friendsArrayList.get(position).getCourse().getCourse());
        holder.friendId.setText(friendsArrayList.get(position).getId());

        if(friendsArrayList.get(position).getImage() != null)
            Picasso.get().load(friendsArrayList.get(position).getImage()).into(holder.friendSuggestAvatarImageView, new Callback() {
                @Override
                public void onSuccess() {
                    holder.friendSuggestAvatarImageView.setImageBitmap(
                            ComponentUtil.getRoundedCroppedBitmap(
                                    ComponentUtil.cropToSquare(
                                            ComponentUtil.drawableToBitmap(holder.friendSuggestAvatarImageView.getDrawable()))));
                }
                @Override
                public void onError(Exception e) {e.printStackTrace();}
            });
    }

    @Override
    public int getItemCount() {
        return friendsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView friendNameTextView;
        TextView courseNameTextView;
        TextView friendId;
        ImageView friendSuggestAvatarImageView;
        Button sendNewRequestButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            friendNameTextView           = itemView.findViewById(R.id.friendNameTextView);
            courseNameTextView           = itemView.findViewById(R.id.friendCourseTextView);
            friendId                     = itemView.findViewById(R.id.friendId);
            sendNewRequestButton         = itemView.findViewById(R.id.sendNewRequestButton);
            friendSuggestAvatarImageView = itemView.findViewById(R.id.friendSuggestAvatarImageView);

            sendNewRequestButton.setOnClickListener(v -> {
                friendService.sendFriendRequest(friendId.getText().toString(), message -> {
                    ComponentUtil.sendToast(mContext, "Erro ao enviar solicitação, tente novamente.");
                });
                removeItemFromDataSet(friendId.getText().toString());
                notifyDataSetChanged();
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
