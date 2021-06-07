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
import com.br.opet.openet.adapter.recyclerViewAdapter.PostsRecyclerViewAdapter;
import com.br.opet.openet.model.CourseModel;
import com.br.opet.openet.model.PostModel;
import com.br.opet.openet.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Preparing mock friendsList
        List<PostModel> postsLists = new ArrayList<PostModel>();
        postsLists.add(new PostModel(null,
                                        new UserModel("Bruna Oliveira", new CourseModel("Farmacia"), "https://robohash.org/56574"),
                                        "Planetinhas",
                                        "https://i.pinimg.com/564x/73/48/81/7348814e35c2277cc69c55df6fcb8a81.jpg",
                                        23));

        postsLists.add(new PostModel(null,
                new UserModel("Xuxa Meneguel", new CourseModel("Jornalismo"), "https://robohash.org/56584"),
                "baixinhos",
                "https://i.pinimg.com/564x/dc/a1/9c/dca19cb2f7039206e535b106e71f7c80.jpg",
                99));

        postsLists.add(new PostModel(null,
                new UserModel("Raphael Lucas", new CourseModel("Administração"), "https://robohash.org/56555"),
                "Só sei que nada sei!",
                "https://i.pinimg.com/564x/dd/c1/c4/ddc1c44f1fd4436a6de0d7246abd271c.jpg",
                2));

        postsLists.add(new PostModel(null,
                new UserModel("Bruna Oliveira", new CourseModel("Farmacia"), "https://robohash.org/56564"),
                "Já falei que sou alternativo hoje ?",
                "https://i.pinimg.com/564x/e9/ae/31/e9ae3163e8907a72031ea3621e9b06d7.jpg",
                0));

        //FriendsFragment
        RecyclerView postsRecyclerView = view.findViewById(R.id.postsRecyclerView);
        postsRecyclerView.setAdapter(new PostsRecyclerViewAdapter(view.getContext(), postsLists));
        postsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

    }
}