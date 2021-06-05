package com.br.opet.openet.fragment.dashboardFragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.br.opet.openet.R;
import com.br.opet.openet.adapter.recyclerViewAdapter.FriendRequestRecyclerViewAdapter;
import com.br.opet.openet.adapter.recyclerViewAdapter.FriendsRecyclerViewAdapter;
import com.br.opet.openet.adapter.recyclerViewAdapter.FriendsSuggestRecyclerViewAdapter;
import com.br.opet.openet.listener.FriendResponseListener;
import com.br.opet.openet.model.CourseModel;
import com.br.opet.openet.model.FriendModel;
import com.br.opet.openet.service.FriendService;
import com.br.opet.openet.service.impl.FriendServiceImpl;
import com.br.opet.openet.util.ComponentUtil;

import java.util.ArrayList;
import java.util.List;

public class FriendsFragment extends Fragment {

    FriendServiceImpl friendService;

    RelativeLayout friendsListRelativeLayout;
    RelativeLayout friendRequestRelativeLayout;
    RelativeLayout exploreFriendsRelativeLayout;

    Button allFriendsButton;
    Button friendRequestsButton;
    Button exploreFriendsButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instanciateObjects(getView());
        ComponentUtil.changeButtonBackgroundEnabled(view.getContext(), allFriendsButton);
        ComponentUtil.changeButtonBackgroundDisabled(view.getContext(), friendRequestsButton);
        ComponentUtil.changeButtonBackgroundDisabled(view.getContext(), exploreFriendsButton);

        //FriendsFragment
        setupAllFriends(view);
        setupCommonUsersSuggestion(view);
        setupAllUsersSuggestion(view);

        //Preparing mock friendsList
        List<FriendModel> friendRequestsList = new ArrayList<FriendModel>();
        friendRequestsList.add(new FriendModel("Luiza Sonza", new CourseModel("Publicidade e Propaganda")));
        friendRequestsList.add(new FriendModel("Linus Torvalds", new CourseModel("Ciência da Computação")));
        friendRequestsList.add(new FriendModel("Átila Iamarino", new CourseModel("Biologia")));
        friendRequestsList.add(new FriendModel("Jair Bolsonaro", new CourseModel("Administração")));
        friendRequestsList.add(new FriendModel("Jeff Bezos", new CourseModel("Empreendedorismo")));
        friendRequestsList.add(new FriendModel("Luiza Sonza", new CourseModel("Publicidade e Propaganda")));
        friendRequestsList.add(new FriendModel("Linus Torvalds", new CourseModel("Ciência da Computação")));
        friendRequestsList.add(new FriendModel("Átila Iamarino", new CourseModel("Biologia")));
        friendRequestsList.add(new FriendModel("Jair Bolsonaro", new CourseModel("Administração")));
        friendRequestsList.add(new FriendModel("Jeff Bezos", new CourseModel("Empreendedorismo")));

        //FriendsFragment
        RecyclerView friendRequestsListRecyclerView = view.findViewById(R.id.friendRequestListRecyclerView);
        friendRequestsListRecyclerView.setAdapter(new FriendRequestRecyclerViewAdapter(view.getContext(), friendRequestsList));
        friendRequestsListRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void instanciateObjects(View v) {
        friendService = new FriendServiceImpl(v.getContext());

        this.friendsListRelativeLayout = getView().findViewById(R.id.friendsListRelativeLayout);
        this.friendRequestRelativeLayout = getView().findViewById(R.id.friendRequestRelativeLayout);
        this.exploreFriendsRelativeLayout = getView().findViewById(R.id.exploreFriendsRelativeLayout);

        this.allFriendsButton = getView().findViewById(R.id.allFriendsButton);
        this.friendRequestsButton = getView().findViewById(R.id.friendRequestsButton);
        this.exploreFriendsButton = getView().findViewById(R.id.exploreFriendsButton);

        allFriendsButton.setOnClickListener(v1 -> {
            friendRequestRelativeLayout.setVisibility(View.GONE);
            exploreFriendsRelativeLayout.setVisibility(View.GONE);
            friendsListRelativeLayout.setVisibility(View.VISIBLE);

            ComponentUtil.changeButtonBackgroundEnabled(v.getContext(), allFriendsButton);
            ComponentUtil.changeButtonBackgroundDisabled(v.getContext(), friendRequestsButton);
            ComponentUtil.changeButtonBackgroundDisabled(v.getContext(), exploreFriendsButton);
        });

        friendRequestsButton.setOnClickListener(v1 -> {
            friendRequestRelativeLayout.setVisibility(View.VISIBLE);
            exploreFriendsRelativeLayout.setVisibility(View.GONE);
            friendsListRelativeLayout.setVisibility(View.GONE);

            ComponentUtil.changeButtonBackgroundEnabled(v.getContext(), friendRequestsButton);
            ComponentUtil.changeButtonBackgroundDisabled(v.getContext(), allFriendsButton);
            ComponentUtil.changeButtonBackgroundDisabled(v.getContext(), exploreFriendsButton);
        });

        exploreFriendsButton.setOnClickListener(v1 -> {
            friendRequestRelativeLayout.setVisibility(View.GONE);
            exploreFriendsRelativeLayout.setVisibility(View.VISIBLE);
            friendsListRelativeLayout.setVisibility(View.GONE);

            ComponentUtil.changeButtonBackgroundEnabled(v.getContext(), exploreFriendsButton);
            ComponentUtil.changeButtonBackgroundDisabled(v.getContext(), allFriendsButton);
            ComponentUtil.changeButtonBackgroundDisabled(v.getContext(), friendRequestsButton);
        });

    }

    private void setupAllFriends(View v) {
        RecyclerView friendsListRecyclerView = v.findViewById(R.id.friendsListRecyclerView);
        RelativeLayout nothingToSeeAllFriends = v.findViewById(R.id.nothingToSeeAllFriends);
        try {
            friendService.allFriends(new FriendResponseListener() {
                @Override
                public void onError(String message) {
                    //TODO HANDLE ERROR
                }
                @Override
                public void onResponse(FriendModel friendModelResponse) {

                }
                @Override
                public void onResponseList(List<FriendModel> friendModelListResponse) {
                    if(friendModelListResponse.size() > 0){
                        friendsListRecyclerView.setAdapter(new FriendsRecyclerViewAdapter(v.getContext(), friendModelListResponse));
                        friendsListRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
                    } else {
                        nothingToSeeAllFriends.setVisibility(View.VISIBLE);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupAllUsersSuggestion(View v) {
        RecyclerView friendsSuggestAllListRecyclerView = v.findViewById(R.id.exploreFriendsListRecyclerView);
        try {
            friendService.allUsersToFriendsSuggestion(new FriendResponseListener() {
                @Override
                public void onError(String message) {
                    //TODO HANDLE ERROR
                }
                @Override
                public void onResponse(FriendModel friendModelResponse) {

                }
                @Override
                public void onResponseList(List<FriendModel> friendModelListResponse) {
                    friendsSuggestAllListRecyclerView.setAdapter(new FriendsSuggestRecyclerViewAdapter(v.getContext(), friendModelListResponse));
                    friendsSuggestAllListRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupCommonUsersSuggestion(View v) {
        RecyclerView friendsSuggestRecommendedListRecyclerView = v.findViewById(R.id.exploreRecommendedFriendsListRecyclerView);
        try {
            friendService.allUsersToFriendsSuggestion(new FriendResponseListener() {
                @Override
                public void onError(String message) {
                    //TODO HANDLE ERROR
                }
                @Override
                public void onResponse(FriendModel friendModelResponse) {

                }
                @Override
                public void onResponseList(List<FriendModel> friendModelListResponse) {
                    friendsSuggestRecommendedListRecyclerView.setAdapter(new FriendsSuggestRecyclerViewAdapter(v.getContext(), friendModelListResponse));
                    friendsSuggestRecommendedListRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}