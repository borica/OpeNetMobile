package com.br.opet.openet.activity;

import android.os.Bundle;

import androidx.viewpager2.widget.ViewPager2;

import com.br.opet.openet.R;
import com.br.opet.openet.activity.defaultActivity.NoBarActivity;
import com.br.opet.openet.adapter.fragmentAdapter.DashboardFragmentAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class DashboardActivity extends NoBarActivity {

    TabLayout headerNavigationTabLayout;
    TabItem friendsTabItem;
    TabItem postsTabItem;
    TabItem messagesTabItem;

    ViewPager2 dashboardFragmentViewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        instanciateComponents();
        TabLayout.Tab tab = headerNavigationTabLayout.getTabAt(1);
        if (tab != null) {
            tab.select();
        }
    }

    private void instanciateComponents() {
        headerNavigationTabLayout = findViewById(R.id.headerNavigationTabLayout);
        friendsTabItem = findViewById(R.id.friendsTabItem);
        postsTabItem = findViewById(R.id.postsTabItem);
        messagesTabItem = findViewById(R.id.messagesTabItem);

        dashboardFragmentViewPager2 = findViewById(R.id.dashboardFragmentViewPager2);
        dashboardFragmentViewPager2.setAdapter(new DashboardFragmentAdapter(getSupportFragmentManager(), getLifecycle()));

        headerNavigationTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                dashboardFragmentViewPager2.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        dashboardFragmentViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                headerNavigationTabLayout.selectTab(headerNavigationTabLayout.getTabAt(position));
            }
        });
    }
}