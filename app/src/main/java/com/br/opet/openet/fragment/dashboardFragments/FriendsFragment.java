package com.br.opet.openet.fragment.dashboardFragments;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

    public static final String TAG = FriendsFragment.class.getName();

    FriendServiceImpl friendService;

    //Lists displayed in each friends layout
    List<FriendModel> allFriendsList;
    List<FriendModel> friendRequestsList;
    List<FriendModel> commonUsersToSuggestList;
    List<FriendModel> allUsersToSuggestList;

    //All Friends related components
    RecyclerView friendsListRecyclerView;
    FriendsRecyclerViewAdapter friendsListRecyclerViewAdapter;
    RelativeLayout nothingToSeeAllFriends;

    //All Friend Requests related components
    RecyclerView friendRequestsListRecyclerView;
    FriendRequestRecyclerViewAdapter friendRequestsListRecyclerViewAdapter;
    RelativeLayout nothingToSeeFriendRequest;

    //All Friend Suggestion related components
    RelativeLayout allUsersToSuggestRelativeLayout;
    RecyclerView friendsSuggestAllListRecyclerView;
    FriendsSuggestRecyclerViewAdapter friendsSuggestAllListRecyclerViewAdapter;

    //Common Friend Suggestion related components
    RelativeLayout recommendedFriendsRelativeLayout;
    RecyclerView friendsSuggestRecommendedListRecyclerView;
    FriendsSuggestRecyclerViewAdapter friendsSuggestRecommendedListRecyclerViewAdapter;

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

        instanciateObjects(view);

        //Setting buttons for first time interaction (All friends button will be enable by default)
        ComponentUtil.changeButtonBackgroundDisabled(view.getContext(), friendRequestsButton);
        ComponentUtil.changeButtonBackgroundDisabled(view.getContext(), exploreFriendsButton);
        setupFriendsLists(view);
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

        allFriendsList = new ArrayList<>();
        friendRequestsList = new ArrayList<>();
        commonUsersToSuggestList = new ArrayList<>();
        allUsersToSuggestList = new ArrayList<>();
    }

    private void setupFriendsLists(View view){
        setupAllFriends(view);
        setupFriendRequests(view);
        setupCommonUsersSuggestion(view);
        setupAllUsersSuggestion(view);
    }

    private void setupAllFriends(View v) {
        friendsListRecyclerView = v.findViewById(R.id.friendsListRecyclerView);
        friendsListRecyclerViewAdapter = new FriendsRecyclerViewAdapter(v.getContext(), allFriendsList);
        friendsListRecyclerView.setAdapter(friendsListRecyclerViewAdapter);
        friendsListRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        nothingToSeeAllFriends = v.findViewById(R.id.nothingToSeeAllFriends);
        callAllFriendsService();
    }

    private void callAllFriendsService(){
        try {
            friendService.allFriends(new FriendResponseListener() {
                @Override
                public void onError(String message) {
                    ComponentUtil.sendToast(FriendsFragment.this.getContext(), message);
                    Log.e(TAG, message);
                }
                @Override
                public void onResponse(FriendModel friendModelResponse) {

                }
                @Override
                public void onResponseList(List<FriendModel> friendModelListResponse) {
                    if(friendModelListResponse.size() > 0){
                        allFriendsList = friendModelListResponse;
                        friendsListRecyclerViewAdapter.setItems(allFriendsList);
                        friendsListRecyclerViewAdapter.notifyDataSetChanged();
                        friendsListRecyclerView.setVisibility(View.VISIBLE);
                        nothingToSeeAllFriends.setVisibility(View.GONE);
                    } else {
                        friendsListRecyclerView.setVisibility(View.GONE);
                        nothingToSeeAllFriends.setVisibility(View.VISIBLE);
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private void setupFriendRequests(View v) {
        //FriendsFragment
        friendRequestsListRecyclerView = v.findViewById(R.id.friendRequestListRecyclerView);
        nothingToSeeFriendRequest = v.findViewById(R.id.nothingToSeeFriendRequest);
        friendRequestsListRecyclerViewAdapter = new FriendRequestRecyclerViewAdapter(v.getContext(), friendRequestsList);
        friendRequestsListRecyclerView.setAdapter(friendRequestsListRecyclerViewAdapter);
        friendRequestsListRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        callFriendRequestService();
    }

    private void callFriendRequestService(){
        try {
            friendService.allFriendsRequests(new FriendResponseListener() {
                @Override
                public void onError(String message) {
                    ComponentUtil.sendToast(FriendsFragment.this.getContext(), message);
                    Log.e(TAG, message);
                }
                @Override
                public void onResponse(FriendModel friendModelResponse) {

                }
                @Override
                public void onResponseList(List<FriendModel> friendModelListResponse) {
                    if(friendModelListResponse.size() > 0){
                        friendRequestsList = friendModelListResponse;
                        friendRequestsListRecyclerViewAdapter.setItems(friendRequestsList);
                        friendRequestsListRecyclerViewAdapter.notifyDataSetChanged();
                        friendRequestsListRecyclerView.setVisibility(View.VISIBLE);
                        nothingToSeeFriendRequest.setVisibility(View.GONE);
                    } else {
                        friendRequestsListRecyclerView.setVisibility(View.GONE);
                        nothingToSeeFriendRequest.setVisibility(View.VISIBLE);
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private void setupAllUsersSuggestion(View v) {
        allUsersToSuggestRelativeLayout = v.findViewById(R.id.allUsersToSuggestRelativeLayout);
        friendsSuggestAllListRecyclerView = v.findViewById(R.id.exploreFriendsListRecyclerView);
        friendsSuggestAllListRecyclerViewAdapter = new FriendsSuggestRecyclerViewAdapter(v.getContext(), this.friendService, allUsersToSuggestList);
        friendsSuggestAllListRecyclerView.setAdapter(friendsSuggestAllListRecyclerViewAdapter);
        friendsSuggestAllListRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        callAllUsersToSuggestionService();
    }

    private void callAllUsersToSuggestionService(){
        try {
            friendService.allUsersToFriendsSuggestion(new FriendResponseListener() {
                @Override
                public void onError(String message) {
                    ComponentUtil.sendToast(FriendsFragment.this.getContext(), message);
                    Log.e(TAG, message);
                }
                @Override
                public void onResponse(FriendModel friendModelResponse) {

                }
                @Override
                public void onResponseList(List<FriendModel> friendModelListResponse) {
                    allUsersToSuggestList = friendModelListResponse;
                    friendsSuggestAllListRecyclerViewAdapter.setItems(allUsersToSuggestList);
                    friendsSuggestAllListRecyclerViewAdapter.notifyDataSetChanged();
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private void setupCommonUsersSuggestion(View v) {
        recommendedFriendsRelativeLayout = v.findViewById(R.id.recommendedFriendsRelativeLayout);
        friendsSuggestRecommendedListRecyclerView = v.findViewById(R.id.exploreRecommendedFriendsListRecyclerView);
        friendsSuggestRecommendedListRecyclerViewAdapter = new FriendsSuggestRecyclerViewAdapter(v.getContext(), this.friendService, commonUsersToSuggestList);
        friendsSuggestRecommendedListRecyclerView.setAdapter(friendsSuggestRecommendedListRecyclerViewAdapter);
        friendsSuggestRecommendedListRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        callCommonUsersToSuggestionService();
    }

    private void callCommonUsersToSuggestionService(){
        try {
            friendService.commonUsersToFriendsSuggestion(new FriendResponseListener() {
                @Override
                public void onError(String message) {
                    ComponentUtil.sendToast(FriendsFragment.this.getContext(), message);
                    Log.e(TAG, message);
                }

                @Override
                public void onResponse(FriendModel friendModelResponse) {

                }

                @Override
                public void onResponseList(List<FriendModel> friendModelListResponse) {
                    if(friendModelListResponse.size() > 0){
                        commonUsersToSuggestList = friendModelListResponse;
                        friendsSuggestRecommendedListRecyclerViewAdapter.setItems(commonUsersToSuggestList);
                        friendsSuggestRecommendedListRecyclerViewAdapter.notifyDataSetChanged();
                        recommendedFriendsRelativeLayout.setVisibility(View.VISIBLE);
                        allUsersToSuggestRelativeLayout.setMinimumHeight(250);
                    } else {
                        recommendedFriendsRelativeLayout.setVisibility(View.GONE);
                        allUsersToSuggestRelativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
}