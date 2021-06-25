package com.br.opet.openet.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Switch;

import com.br.opet.openet.fragment.dashboardFragments.FriendsFragment;

public class FriendsFragmentReceiver extends BroadcastReceiver {

    FriendsFragment friendsFragment;

    public FriendsFragmentReceiver(){}

    public FriendsFragmentReceiver(FriendsFragment friendsFragmentCaller){
        this.friendsFragment = friendsFragmentCaller;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        switch(intent.getAction()){
            case "updateRequestsView":
                friendsFragment.callFriendRequestService(context);
                break;
            case "updateFriendsView":
                friendsFragment.callAllFriendsService(context);
                break;
            case "updateExploreView":
                friendsFragment.callAllUsersToSuggestionService(context);
                friendsFragment.callCommonUsersToSuggestionService(context);
                break;
            default:
                throw new UnsupportedOperationException("Must specify action");
        }
    }
}