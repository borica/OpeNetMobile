package com.br.opet.openet.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.br.opet.openet.R;
import com.br.opet.openet.activity.defaultActivity.NoBarActivity;
import com.br.opet.openet.adapter.recyclerViewAdapter.FriendsSuggestRecyclerViewAdapter;
import com.br.opet.openet.adapter.recyclerViewAdapter.UserApprovalRecyclerViewAdapter;
import com.br.opet.openet.application.ApplicationContext;
import com.br.opet.openet.listener.UsersToApproveListener;
import com.br.opet.openet.model.UserModel;
import com.br.opet.openet.service.impl.UserServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdminDashboardActivity extends NoBarActivity {

    private static String TAG =  AdminDashboardActivity.class.getName();

    private ApplicationContext applicationContext;

    private SwipeRefreshLayout refreshUsersSwypeRefreshLayout;
    private RelativeLayout nothingToSeeAdministratorRelativeLayout;
    private RelativeLayout usersToApproveRelativeLayout;

    private TextView loginTimeTextView;
    private TextView usersCountTextView;

    private UserServiceImpl  userService;
    private RecyclerView usersToApproveRecyclerView;

    private UserApprovalRecyclerViewAdapter userApprovalRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        instantiateObjects();
        setStatusBarToAdministratorColor();
    }

    private void instantiateObjects() {
        Context mContext = this;
        applicationContext = (ApplicationContext) this.getApplicationContext();
        refreshUsersSwypeRefreshLayout = findViewById(R.id.refreshUsersSwypeRefreshLayout);
        refreshUsersSwypeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setUsersIntoAdapter(mContext);
            }
        });
        nothingToSeeAdministratorRelativeLayout = findViewById(R.id.nothingToSeeAdministratorRelativeLayout);
        usersToApproveRelativeLayout = findViewById(R.id.usersToApproveRelativeLayout);
        userService = new UserServiceImpl(this);
        loginTimeTextView = findViewById(R.id.loginTimeTextView);
        usersCountTextView = findViewById(R.id.usersCountTextView);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:MM");
        loginTimeTextView.setText("Login: "+ sdf.format(new Date()));
        usersToApproveRecyclerView = findViewById(R.id.usersToApproveRecyclerView);
        setUsersIntoAdapter(this);
    }

    public void setUsersIntoAdapter(Context mContext) {
        try {
            userService.usersToApprove(new UsersToApproveListener() {
                @Override
                public void onError(String message) {
                    Log.e(TAG, " erro: " + message);
                }
                @Override
                public void onResponseList(List<UserModel> userModelListResponse) {
                    if(userModelListResponse.size() > 0){
                        usersToApproveRelativeLayout.setVisibility(View.VISIBLE);
                        nothingToSeeAdministratorRelativeLayout.setVisibility(View.GONE);
                        userApprovalRecyclerViewAdapter = new UserApprovalRecyclerViewAdapter(mContext, userModelListResponse);
                        usersToApproveRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                        usersToApproveRecyclerView.setAdapter(userApprovalRecyclerViewAdapter);
                        usersCountTextView.setText("Usuários para aprovação: "+ userApprovalRecyclerViewAdapter.getItemCount());
                        refreshUsersSwypeRefreshLayout.setRefreshing(false);
                    } else {
                        usersToApproveRelativeLayout.setVisibility(View.GONE);
                        nothingToSeeAdministratorRelativeLayout.setVisibility(View.VISIBLE);
                        refreshUsersSwypeRefreshLayout.setRefreshing(false);
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, " erro: " + e.getMessage());
        }
    }

    private void setStatusBarToAdministratorColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.administrator_main_color));
        }
    }
}