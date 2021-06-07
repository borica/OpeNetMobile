package com.br.opet.openet.adapter.recyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.br.opet.openet.R;
import com.br.opet.openet.model.FriendModel;

import java.util.List;

public class FriendsSuggestRecyclerViewAdapter extends RecyclerView.Adapter<FriendsSuggestRecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<FriendModel> friendsArrayList;

    public FriendsSuggestRecyclerViewAdapter(Context mContext, List<FriendModel> friends) {
        this.mContext = mContext;
        friendsArrayList = friends;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View v = li.inflate(R.layout.friends_suggest_recyclerview_row, parent, false);

        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsSuggestRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.friendNameTextView.setText(friendsArrayList.get(position).getName());
        holder.courseNameTextView.setText(friendsArrayList.get(position).getCourse().getCourse());
    }

    @Override
    public int getItemCount() {
        return friendsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView friendNameTextView;
        TextView courseNameTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            friendNameTextView = itemView.findViewById(R.id.friendNameTextView);
            courseNameTextView = itemView.findViewById(R.id.friendCourseTextView);
        }
    }

}
