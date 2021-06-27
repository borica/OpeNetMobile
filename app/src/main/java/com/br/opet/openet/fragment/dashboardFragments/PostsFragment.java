package com.br.opet.openet.fragment.dashboardFragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.br.opet.openet.R;
import com.br.opet.openet.activity.DashboardActivity;
import com.br.opet.openet.adapter.recyclerViewAdapter.PostsRecyclerViewAdapter;
import com.br.opet.openet.listener.PostListener;
import com.br.opet.openet.model.PostModel;
import com.br.opet.openet.service.impl.PostServiceImpl;
import com.br.opet.openet.util.ComponentUtil;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class PostsFragment extends Fragment {

    private static final String TAG = PostsFragment.class.getName();

    //Image request code
    private int PICK_IMAGE_REQUEST = 1;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;

    private View view;
    private PostServiceImpl postService;

    private SwipeRefreshLayout postSwipeRefreshLayout;
    private RecyclerView postsRecyclerView;
    private RelativeLayout createNewPostRelativeLayout;
    private RelativeLayout newPostRelativeLayout;
    private RelativeLayout addNewPicRelativeLayout;
    private RelativeLayout addedPictureRelativeLayout;
    private ImageView closeNewPostImageView;
    private ImageView addedPictureImageView;
    private TextInputEditText postTextTextInputEditText;
    private Button finishPostButton;
    private Bitmap bitmapImageFromUserGallery;

    private Uri filePath;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        instantiateObjects();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void instantiateObjects() {
        postService = new PostServiceImpl(view.getContext());
        postsRecyclerView = view.findViewById(R.id.postsRecyclerView);
        newPostRelativeLayout = view.findViewById(R.id.newPostRelativeLayout);
        addedPictureRelativeLayout = view.findViewById(R.id.addedPictureRelativeLayout);
        finishPostButton = view.findViewById(R.id.finishPostButton);
        finishPostButton.setOnClickListener(v -> {
            try {
                if(this.postService.createPostWithImage(requireActivity().getContentResolver(), filePath, postTextTextInputEditText.getText().toString())){
                    ComponentUtil.sendToast(this.getContext(), "Realizando upload do seu post :)");
                } else {
                    ComponentUtil.sendToast(this.getContext(), "Erro ao criar seu post :(");
                }
            } catch (Exception e) {
                Log.e(TAG, "Erro:\n" + e.getMessage());
                ComponentUtil.sendToast(this.getContext(), "Erro ao criar seu post :(");
            }
        });
        postTextTextInputEditText = view.findViewById(R.id.postTextTextInputEditText);
        addNewPicRelativeLayout = view.findViewById(R.id.addNewPicRelativeLayout);
        addNewPicRelativeLayout.setOnClickListener(v -> {
            showFileChooser();
        });
        createNewPostRelativeLayout = view.findViewById(R.id.createNewPostRelativeLayout);
        createNewPostRelativeLayout.setOnClickListener(v -> {
            newPostRelativeLayout.setVisibility(View.VISIBLE);
            requestStoragePermission();
        });
        addedPictureImageView = view.findViewById(R.id.addedPictureImageView);
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

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmapImageFromUserGallery = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), filePath);
                addedPictureImageView.setImageBitmap(bitmapImageFromUserGallery);
                addedPictureRelativeLayout.setVisibility(View.VISIBLE);

            } catch (IOException e) {
                Log.e(TAG, "Erro:\n" + e.getMessage());
            }
        }
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

    //Requesting permission
    private void requestStoragePermission() {
        DashboardActivity dashboardActivity = (DashboardActivity) requireActivity();
        if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(dashboardActivity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        ActivityCompat.requestPermissions(dashboardActivity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


}