package com.br.opet.openet.adapter.recyclerViewAdapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.br.opet.openet.R;
import com.br.opet.openet.model.FriendModel;
import com.br.opet.openet.util.ComponentUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FriendsRecyclerViewAdapter extends RecyclerView.Adapter<FriendsRecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<FriendModel> friendsArrayList;

    public FriendsRecyclerViewAdapter(Context mContext, List<FriendModel> friends) {
        this.mContext = mContext;
        friendsArrayList = friends;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View v = li.inflate(R.layout.friends_recyclerview_row, parent, false);

        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.friendNameTextView.setText(friendsArrayList.get(position).getName());
        holder.courseNameTextView.setText(friendsArrayList.get(position).getCourse().getCourse());

        if(friendsArrayList.get(position).getImage() != null) {
            Picasso.get().load(friendsArrayList.get(position).getImage()).into(holder.friendAvatarImageview, new Callback() {
                @Override
                public void onSuccess() {
                    holder.friendAvatarImageview.setImageBitmap(
                            ComponentUtil.getRoundedCroppedBitmap(
                                    ComponentUtil.cropToSquare(
                                            ComponentUtil.drawableToBitmap(holder.friendAvatarImageview.getDrawable()))));
                }
                @Override
                public void onError(Exception e) {e.printStackTrace();}
            });
        }
    }

    @Override
    public int getItemCount() {
        return friendsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView friendNameTextView;
        TextView courseNameTextView;
        ImageView friendAvatarImageview;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            friendNameTextView      = itemView.findViewById(R.id.friendNameTextView);
            courseNameTextView      = itemView.findViewById(R.id.friendCourseTextView);
            friendAvatarImageview   = itemView.findViewById(R.id.friendAvatarImageView);
        }
    }

    public void setItems(List<FriendModel> friendList) {
        this.friendsArrayList = friendList;
    }

}
