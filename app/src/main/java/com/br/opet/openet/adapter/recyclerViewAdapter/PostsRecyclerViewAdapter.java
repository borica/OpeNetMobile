package com.br.opet.openet.adapter.recyclerViewAdapter;

import android.content.Context;
import android.util.Log;
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
import com.br.opet.openet.service.util.HTTPUtils;
import com.br.opet.openet.util.ComponentUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.OkHttpClient;

public class PostsRecyclerViewAdapter extends RecyclerView.Adapter<PostsRecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<PostModel> postArrayList;
    Picasso picasso;

    public PostsRecyclerViewAdapter(Context mContext, List<PostModel> postsArrayList) {
        this.mContext = mContext;
        this.postArrayList = postsArrayList;
        picasso = new Picasso.Builder(mContext)
                .downloader(new OkHttp3Downloader(new OkHttpClient()))
                .build();
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

        if(postArrayList.get(position).getUserOwner().getAvatar() != null) {
            Picasso.get().load(postArrayList.get(position).getUserOwner().getAvatar()).into(holder.avatarImageView, new Callback() {
                @Override
                public void onSuccess() {
                    holder.avatarImageView.setImageBitmap(
                            ComponentUtil.getRoundedCroppedBitmap(
                                    ComponentUtil.cropToSquare(
                                            ComponentUtil.drawableToBitmap(holder.avatarImageView.getDrawable()))));
                }
                @Override
                public void onError(Exception e) {e.printStackTrace();}
            });
        }

        if(postArrayList.get(position).getPostImageURL() != null) {
            picasso.load(postArrayList.get(position).getPostImageURL().replace("localhost", HTTPUtils.HOST_NAME)).into(holder.postImageView, new Callback() {
                @Override
                public void onSuccess() {
                    Log.i("Picasso: ", "Success!");
                }

                @Override
                public void onError(Exception e) {
                    Log.i("Picasso: ", "Fail!");
                }
            });
        }

        if(postArrayList.get(position).getText() != null) {
            holder.postTextview.setText(postArrayList.get(position).getText());
        }

        holder.postUserNameTextview.setText(postArrayList.get(position).getUserOwner().getName());

        if(postArrayList.get(position).getUserOwner().getCouseModel() != null){
            holder.postUserNameCourseTextview.setText(postArrayList.get(position).getUserOwner().getCouseModel().getCourse());
        }

        if(postArrayList.get(position).getLikes() == 0) {
            holder.likeCountTextView.setText("Nenhuma curtida");
        } else if (postArrayList.get(position).getLikes() == 1) {
            holder.likeCountTextView.setText("1 like");
        } else {
            holder.likeCountTextView.setText(postArrayList.get(position).getLikes() + " likes");
        }

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
        TextView likeCountTextView;

        Button postLikeButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            postUserNameTextview = itemView.findViewById(R.id.postUserNameTextview);
            postUserNameCourseTextview = itemView.findViewById(R.id.postUserNameCourseTextview);
            postTextview = itemView.findViewById(R.id.postTextview);
            likeCountTextView = itemView.findViewById(R.id.likeCountTextView);

            avatarImageView = itemView.findViewById(R.id.avatarImageView);
            postImageView = itemView.findViewById(R.id.postImageView);

            postLikeButton = itemView.findViewById(R.id.postLikeButton);
        }
    }

}
