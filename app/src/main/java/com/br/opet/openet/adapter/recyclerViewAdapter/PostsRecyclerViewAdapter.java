package com.br.opet.openet.adapter.recyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.br.opet.openet.R;
import com.br.opet.openet.model.PostModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostsRecyclerViewAdapter extends RecyclerView.Adapter<PostsRecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<PostModel> postArrayList;

    public PostsRecyclerViewAdapter(Context mContext, List<PostModel> postsArrayList) {
        this.mContext = mContext;
        this.postArrayList = postsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View v = li.inflate(R.layout.posts_recycleview_row, parent, false);

        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsRecyclerViewAdapter.MyViewHolder holder, int position) {

        if(postArrayList.get(position).getUserOwner().getavatar_url() != null) {
            Picasso.get().load(postArrayList.get(position).getUserOwner().getavatar_url()).into(holder.avatarImageView);
        }

        if(postArrayList.get(position).getPostImageURL() != null) {
            Picasso.get().load(postArrayList.get(position).getPostImageURL()).into(holder.postImageView);
        }

        if(postArrayList.get(position).getText() != null) {
            holder.postTextview.setText(postArrayList.get(position).getText());
        }

        holder.postUserNameTextview.setText(postArrayList.get(position).getUserOwner().getName());
        holder.postUserNameCourseTextview.setText(postArrayList.get(position).getUserOwner().getCouseModel().getCourse());
        holder.postLikeButton.setOnClickListener( v -> {
            Toast.makeText(mContext, "Você curtiu este post!", Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView avatarImageView;
        ImageView postImageView;

        TextView postUserNameTextview;
        TextView postUserNameCourseTextview;
        TextView postTextview;

        Button postLikeButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            postUserNameTextview = itemView.findViewById(R.id.postUserNameTextview);
            postUserNameCourseTextview = itemView.findViewById(R.id.postUserNameCourseTextview);
            postTextview = itemView.findViewById(R.id.postTextview);

            avatarImageView = itemView.findViewById(R.id.avatarImageView);
            postImageView = itemView.findViewById(R.id.postImageView);

            postLikeButton = itemView.findViewById(R.id.postLikeButton);
        }
    }

}
