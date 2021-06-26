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
import com.br.opet.openet.activity.AdminDashboardActivity;
import com.br.opet.openet.listener.UsersToApproveListener;
import com.br.opet.openet.model.UserModel;
import com.br.opet.openet.service.impl.UserServiceImpl;
import com.squareup.picasso.Picasso;

import java.util.Iterator;
import java.util.List;

public class UserApprovalRecyclerViewAdapter extends RecyclerView.Adapter<UserApprovalRecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<UserModel> usersArrayList;
    UserServiceImpl userServiceImpl;

    public UserApprovalRecyclerViewAdapter(Context mContext, List<UserModel> users) {
        this.mContext = mContext;
        usersArrayList = users;
        userServiceImpl = new UserServiceImpl(mContext);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View v = li.inflate(R.layout.friend_request_recyclerview_row, parent, false);

        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserApprovalRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.userNameTextView.setText(usersArrayList.get(position).getName());
        holder.courseNameTextView.setText(usersArrayList.get(position).getCouseModel().getCourse());
        holder.userId.setText(usersArrayList.get(position).getId());

        if(usersArrayList.get(position).getAvatar() != null)
            Picasso.get().load(usersArrayList.get(position).getAvatar()).into(holder.userAvatarImageview);
    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView userNameTextView;
        TextView courseNameTextView;
        TextView userId;
        ImageView userAvatarImageview;
        Button acceptUserButton;
        Button denyUserButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameTextView      = itemView.findViewById(R.id.friendNameTextView);
            courseNameTextView    = itemView.findViewById(R.id.friendCourseTextView);
            acceptUserButton      = itemView.findViewById(R.id.acceptFriendButton);
            denyUserButton        = itemView.findViewById(R.id.removeFriendButton);
            userId                = itemView.findViewById(R.id.friendId);
            userAvatarImageview   = itemView.findViewById(R.id.friendRequestAvatar);

            denyUserButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItemFromDataSet(userId.getText().toString());
                    notifyDataSetChanged();
                }
            });

            acceptUserButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        userServiceImpl.postApproveUser(userId.getText().toString(), new UsersToApproveListener() {
                            @Override
                            public void onError(String message) {
                                Log.e("MyViewHolder: ", message);
                            }
                            @Override
                            public void onResponseList(List<UserModel> userModelListResponse) {
                                if (mContext instanceof AdminDashboardActivity) {
                                    ((AdminDashboardActivity)mContext).setUsersIntoAdapter(mContext);
                                }
                            }
                        });
                    } catch (Exception e) {
                        Log.e("MyViewHolder: ", e.getMessage());
                    }
                }
            });
        }
    }

    public void setItems(List<UserModel> usersList) {
        this.usersArrayList = usersList;
    }

    private void removeItemFromDataSet(String id){
        Iterator<UserModel> i = usersArrayList.iterator();
        while (i.hasNext()) {
            UserModel friend = (UserModel) i.next();
            if(friend.getId().equals(id)) {
                i.remove();
            }
        }
    }

}
