package com.br.opet.openet.fragment.dashboardFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.br.opet.openet.R;
import com.br.opet.openet.adapter.recyclerViewAdapter.PostsRecyclerViewAdapter;
import com.br.opet.openet.listener.PostListener;
import com.br.opet.openet.model.PostModel;
import com.br.opet.openet.service.impl.PostServiceImpl;

import java.util.List;

public class PostsFragment extends Fragment {

    private static final String TAG = PostsFragment.class.getName();

    private View view;
    private PostServiceImpl postService;

    private SwipeRefreshLayout postSwipeRefreshLayout;
    private RecyclerView postsRecyclerView;
    private RelativeLayout createNewPostRelativeLayout;
    private RelativeLayout newPostRelativeLayout;
    private ImageView closeNewPostImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        instantiateObjects();
    }

    private void instantiateObjects() {
        postService = new PostServiceImpl(view.getContext());
        postsRecyclerView = view.findViewById(R.id.postsRecyclerView);
        newPostRelativeLayout = view.findViewById(R.id.newPostRelativeLayout);;
        createNewPostRelativeLayout = view.findViewById(R.id.createNewPostRelativeLayout);
        createNewPostRelativeLayout.setOnClickListener(v -> {
            newPostRelativeLayout.setVisibility(View.VISIBLE);
        });
        closeNewPostImageView = view.findViewById(R.id.closeNewPostImageView);
        closeNewPostImageView.setOnClickListener(v -> {
            newPostRelativeLayout.setVisibility(View.GONE);
        });
        postSwipeRefreshLayout = view.findViewById(R.id.postSwipeRefreshLayout);

        postSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                callGetPostsRequest();
            }
        });
        callGetPostsRequest();
    }

    private void callGetPostsRequest() {
        try {
            postService.getPosts(new PostListener() {
                @Override
                public void onError(String message) { Log.e(TAG, "Erro:\n" + message); }
                @Override
                public void onResponse(List<PostModel> postList) {
                    postsRecyclerView.setAdapter(new PostsRecyclerViewAdapter(view.getContext(), postList));
                    postsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                    postSwipeRefreshLayout.setRefreshing(false);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Erro:\n" + e.getMessage());
        }
    }
}