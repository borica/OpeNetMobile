package com.br.opet.openet.fragment.dashboardFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.opet.openet.R;
import com.br.opet.openet.adapter.recyclerViewAdapter.FriendsRecyclerViewAdapter;
import com.br.opet.openet.model.CourseModel;
import com.br.opet.openet.model.FriendModel;

import java.util.ArrayList;
import java.util.List;

public class FriendsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
    }
}