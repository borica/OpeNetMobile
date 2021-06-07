package com.br.opet.openet.service;

import android.content.Context;

import com.br.opet.openet.listener.FriendResponseListener;

public interface FriendService {

    void allUsersToFriendsSuggestion(final FriendResponseListener sessionResponseListener) throws Exception;
    void commonUsersToFriendsSuggestion(final FriendResponseListener sessionResponseListener) throws Exception;
    void allFriendsRequests(final FriendResponseListener sessionResponseListener) throws Exception;
    void allFriends(final FriendResponseListener sessionResponseListener) throws Exception;

}
