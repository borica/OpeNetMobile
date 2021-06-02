package com.br.opet.openet.fragment.dashboardFragments;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.br.opet.openet.R;
import com.br.opet.openet.adapter.recyclerViewAdapter.FriendRequestRecyclerViewAdapter;
import com.br.opet.openet.adapter.recyclerViewAdapter.FriendsRecyclerViewAdapter;
import com.br.opet.openet.adapter.recyclerViewAdapter.FriendsSuggestRecyclerViewAdapter;
import com.br.opet.openet.model.CourseModel;
import com.br.opet.openet.model.FriendModel;

import java.util.ArrayList;
import java.util.List;

public class FriendsFragment extends Fragment {

    RelativeLayout friendsListRelativeLayout;
    RelativeLayout friendRequestRelativeLayout;
    RelativeLayout exploreFriendsRelativeLayout;

    Button allFriendsButton;
    Button friendRequestsButton;
    Button exploreFriendsButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instanciateObjects(getView());

        //Preparing mock friendsList
        List<FriendModel> friendsList = new ArrayList<FriendModel>();
        friendsList.add(new FriendModel("Luiza Sonza", new CourseModel("Publicidade e Propaganda")));
        friendsList.add(new FriendModel("Linus Torvalds", new CourseModel("Ciência da Computação")));
        friendsList.add(new FriendModel("Átila Iamarino", new CourseModel("Biologia")));
        friendsList.add(new FriendModel("Jair Bolsonaro", new CourseModel("Administração")));
        friendsList.add(new FriendModel("Jeff Bezos", new CourseModel("Empreendedorismo")));

        //FriendsFragment
        RecyclerView friendsListRecyclerView = view.findViewById(R.id.friendsListRecyclerView);
        friendsListRecyclerView.setAdapter(new FriendsRecyclerViewAdapter(view.getContext(), friendsList));
        friendsListRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        //Preparing mock friendsList
        List<FriendModel> friendsRecommendedList = new ArrayList<FriendModel>();
        friendsRecommendedList.add(new FriendModel("Luiza Sonza", new CourseModel("Publicidade e Propaganda")));
        friendsRecommendedList.add(new FriendModel("Linus Torvalds", new CourseModel("Ciência da Computação")));
        friendsRecommendedList.add(new FriendModel("Átila Iamarino", new CourseModel("Biologia")));
        friendsRecommendedList.add(new FriendModel("Jair Bolsonaro", new CourseModel("Administração")));
        friendsRecommendedList.add(new FriendModel("Jeff Bezos", new CourseModel("Empreendedorismo")));

        //FriendsFragment
        RecyclerView friendsSuggestRecommendedListRecyclerView = view.findViewById(R.id.exploreRecommendedFriendsListRecyclerView);
        friendsSuggestRecommendedListRecyclerView.setAdapter(new FriendsSuggestRecyclerViewAdapter(view.getContext(), friendsRecommendedList));
        friendsSuggestRecommendedListRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        //Preparing mock friendsList
        List<FriendModel> friendsAllList = new ArrayList<FriendModel>();
        friendsAllList.add(new FriendModel("Luiza Sonza", new CourseModel("Publicidade e Propaganda")));
        friendsAllList.add(new FriendModel("Linus Torvalds", new CourseModel("Ciência da Computação")));
        friendsAllList.add(new FriendModel("Átila Iamarino", new CourseModel("Biologia")));
        friendsAllList.add(new FriendModel("Jair Bolsonaro", new CourseModel("Administração")));
        friendsAllList.add(new FriendModel("Jeff Bezos", new CourseModel("Empreendedorismo")));
        friendsAllList.add(new FriendModel("Luiza Sonza", new CourseModel("Publicidade e Propaganda")));
        friendsAllList.add(new FriendModel("Linus Torvalds", new CourseModel("Ciência da Computação")));
        friendsAllList.add(new FriendModel("Átila Iamarino", new CourseModel("Biologia")));
        friendsAllList.add(new FriendModel("Jair Bolsonaro", new CourseModel("Administração")));
        friendsAllList.add(new FriendModel("Jeff Bezos", new CourseModel("Empreendedorismo")));

        //FriendsFragment
        RecyclerView friendsSuggestAllListRecyclerView = view.findViewById(R.id.exploreFriendsListRecyclerView);
        friendsSuggestAllListRecyclerView.setAdapter(new FriendsSuggestRecyclerViewAdapter(view.getContext(), friendsAllList));
        friendsSuggestAllListRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

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

            allFriendsButton.setBackgroundTintList(v.getContext().getResources().getColorStateList(R.color.openet_green));
            allFriendsButton.setTextColor(getResources().getColor(R.color.white));

            friendRequestsButton.setBackgroundTintList(v.getContext().getResources().getColorStateList(R.color.light_gray));
            friendRequestsButton.setTextColor(getResources().getColor(R.color.unselect_button_text));
            exploreFriendsButton.setBackgroundTintList(v.getContext().getResources().getColorStateList(R.color.light_gray));
            exploreFriendsButton.setTextColor(getResources().getColor(R.color.unselect_button_text));

        });

        friendRequestsButton.setOnClickListener(v1 -> {
            friendRequestRelativeLayout.setVisibility(View.VISIBLE);
            exploreFriendsRelativeLayout.setVisibility(View.GONE);
            friendsListRelativeLayout.setVisibility(View.GONE);

            friendRequestsButton.setBackgroundTintList(v.getContext().getResources().getColorStateList(R.color.openet_green));
            friendRequestsButton.setTextColor(getResources().getColor(R.color.white));

            allFriendsButton.setBackgroundTintList(v.getContext().getResources().getColorStateList(R.color.light_gray));
            allFriendsButton.setTextColor(getResources().getColor(R.color.unselect_button_text));
            exploreFriendsButton.setBackgroundTintList(v.getContext().getResources().getColorStateList(R.color.light_gray));
            exploreFriendsButton.setTextColor(getResources().getColor(R.color.unselect_button_text));
        });

        exploreFriendsButton.setOnClickListener(v1 -> {
            friendRequestRelativeLayout.setVisibility(View.GONE);
            exploreFriendsRelativeLayout.setVisibility(View.VISIBLE);
            friendsListRelativeLayout.setVisibility(View.GONE);

            exploreFriendsButton.setBackgroundTintList(v.getContext().getResources().getColorStateList(R.color.openet_green));
            exploreFriendsButton.setTextColor(getResources().getColor(R.color.white));

            allFriendsButton.setBackgroundTintList(v.getContext().getResources().getColorStateList(R.color.light_gray));
            allFriendsButton.setTextColor(getResources().getColor(R.color.unselect_button_text));
            friendRequestsButton.setBackgroundTintList(v.getContext().getResources().getColorStateList(R.color.light_gray));
            friendRequestsButton.setTextColor(getResources().getColor(R.color.unselect_button_text));
        });

    }

}