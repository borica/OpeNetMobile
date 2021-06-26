package com.br.opet.openet.service;

import com.br.opet.openet.listener.FriendRequestListener;
import com.br.opet.openet.listener.FriendResponseListener;

public interface FriendService {

    void allUsersToFriendsSuggestion(final FriendResponseListener sessionResponseListener) throws Exception;
    void commonUsersToFriendsSuggestion(final FriendResponseListener sessionResponseListener) throws Exception;
    void allFriendsRequests(final FriendResponseListener sessionResponseListener) throws Exception;
    void allFriends(final FriendResponseListener sessionResponseListener) throws Exception;
    void sendFriendRequest(String id, FriendRequestListener responseListener) throws Exception;
    void postAcceptFriendRequest(String id, FriendRequestListener responseListener) throws Exception;

}
