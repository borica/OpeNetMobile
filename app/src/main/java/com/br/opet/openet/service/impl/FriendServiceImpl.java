package com.br.opet.openet.service.impl;

import android.content.Context;
import android.util.Log;

import com.br.opet.openet.application.ApplicationContext;
import com.br.opet.openet.enumerator.FriendRoutesEnum;
import com.br.opet.openet.listener.FriendRequestListener;
import com.br.opet.openet.listener.FriendResponseListener;
import com.br.opet.openet.listener.RestResponseListener;
import com.br.opet.openet.model.CourseModel;
import com.br.opet.openet.model.FriendModel;
import com.br.opet.openet.model.dto.FriendRequestDTO;
import com.br.opet.openet.model.dto.UserDTO;
import com.br.opet.openet.service.FriendService;
import com.br.opet.openet.service.impl.defaultRequest.DefaultRestClient;
import com.br.opet.openet.service.util.HTTPUtils;
import com.br.opet.openet.util.ComparatorUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FriendServiceImpl extends DefaultRestClient implements FriendService {

    private static final String TAG = FriendServiceImpl.class.getName();

    private ApplicationContext appContext;
    private Context mContext;

    public FriendServiceImpl(Context mContext) {
        this.mContext = mContext;
        appContext = (ApplicationContext) mContext.getApplicationContext();
        setupClient();
    }

    @Override
    public void allUsersToFriendsSuggestion(FriendResponseListener responseListener) throws Exception {
        String token = appContext.getLoggedUser().getToken();
        doGet(mContext, (HTTPUtils.HOST + FriendRoutesEnum.ALL_USERS_SUGGEST.getRoute()), token, new RestResponseListener() {
            @Override
            public void onError(String message) {
                responseListener.onError(message);
            }
            @Override
            public void onResponse(JSONObject jsonResponse) {
                try {
                    List<FriendModel> usersToSuggestList = new ArrayList<>();
                    JSONArray usersToSuggestJsonArray = jsonResponse.getJSONArray("users");
                    for (int i = 0; i < usersToSuggestJsonArray.length(); i++) {
                        UserDTO userDTO = gson.fromJson(usersToSuggestJsonArray.get(i).toString(), UserDTO.class);
                        if (!appContext.getLoggedUser().getId().equals(userDTO.getId()))
                            usersToSuggestList.add(new FriendModel(userDTO.getId(), userDTO.getName(), userDTO.getAvatar(), new CourseModel(userDTO.getCourse_id())));
                    }
                    Collections.sort(usersToSuggestList, new ComparatorUtil.SortByFriendName());
                    responseListener.onResponseList(usersToSuggestList);
                } catch (JSONException e) {
                    Log.e(TAG, "Erro:\n" + e.getMessage());
                }
            }
        });
    }

    @Override
    public void commonUsersToFriendsSuggestion(FriendResponseListener responseListener) throws Exception {
        String token = appContext.getLoggedUser().getToken();
        doGet(mContext, (HTTPUtils.HOST + FriendRoutesEnum.COMMON_USERS_SUGGEST.getRoute()), token, new RestResponseListener() {
            @Override
            public void onError(String message) {
                responseListener.onError(message);
            }
            @Override
            public void onResponse(JSONObject jsonResponse) {
                try {
                    List<FriendModel> usersToSuggestList = new ArrayList<>();
                    JSONArray usersToSuggestJsonArray = jsonResponse.getJSONArray("users");
                    for (int i = 0; i < usersToSuggestJsonArray.length(); i++) {
                        UserDTO userDTO = gson.fromJson(usersToSuggestJsonArray.get(i).toString(), UserDTO.class);
                        if (!appContext.getLoggedUser().getId().equals(userDTO.getId()))
                            usersToSuggestList.add(new FriendModel(userDTO.getId(), userDTO.getName(), userDTO.getAvatar(), new CourseModel(userDTO.getCourse_id())));
                    }
                    Collections.sort(usersToSuggestList, new ComparatorUtil.SortByFriendName());
                    responseListener.onResponseList(usersToSuggestList);
                } catch (JSONException e) {
                    Log.e(TAG, "Erro:\n" + e.getMessage());
                }
            }
        });
    }

    @Override
    public void allFriendsRequests(FriendResponseListener responseListener) throws Exception {
        String token = appContext.getLoggedUser().getToken();
        doGet(mContext, (HTTPUtils.HOST + FriendRoutesEnum.PEDING_FRIEND_REQUESTS.getRoute()), token, new RestResponseListener() {
            @Override
            public void onError(String message) {
                responseListener.onError(message);
            }
            @Override
            public void onResponse(JSONObject jsonResponse) {
                try {
                    List<FriendModel> usersToSuggestList = new ArrayList<>();
                    JSONArray usersToSuggestJsonArray = jsonResponse.getJSONArray("friends");
                    for (int i = 0; i < usersToSuggestJsonArray.length(); i++) {
                        FriendRequestDTO friendRequestDTO = gson.fromJson(usersToSuggestJsonArray.get(i).toString(), FriendRequestDTO.class);
                        if (!appContext.getLoggedUser().getId().equals(friendRequestDTO.getUser().getId()))
                            usersToSuggestList.add(new FriendModel(friendRequestDTO.getId(), friendRequestDTO.getUser().getName(), friendRequestDTO.getUser().getAvatar(), new CourseModel(friendRequestDTO.getUser().getCourse_id())));
                    }
                    Collections.sort(usersToSuggestList, new ComparatorUtil.SortByFriendName());
                    responseListener.onResponseList(usersToSuggestList);
                } catch (JSONException e) {
                    Log.e(TAG, "Erro:\n" + e.getMessage());
                }
            }
        });
    }

    @Override
    public void allFriends(FriendResponseListener responseListener) throws Exception {
        String token = appContext.getLoggedUser().getToken();
        doGet(mContext, (HTTPUtils.HOST + FriendRoutesEnum.LIST_ALL_FRIENDS.getRoute()), token, new RestResponseListener() {
            @Override
            public void onError(String message) {
                responseListener.onError(message);
            }
            @Override
            public void onResponse(JSONObject jsonResponse) {
                try {
                    List<FriendModel> usersToSuggestList = new ArrayList<>();
                    JSONArray usersToSuggestJsonArray = jsonResponse.getJSONArray("users");
                    for (int i = 0; i < usersToSuggestJsonArray.length(); i++) {
                        UserDTO userDTO = gson.fromJson(usersToSuggestJsonArray.get(i).toString(), UserDTO.class);
                        if (!appContext.getLoggedUser().getId().equals(userDTO.getId()))
                            usersToSuggestList.add(new FriendModel(userDTO.getName(), userDTO.getAvatar(), new CourseModel(userDTO.getCourse_id())));
                    }
                    Collections.sort(usersToSuggestList, new ComparatorUtil.SortByFriendName());
                    responseListener.onResponseList(usersToSuggestList);
                } catch (JSONException e) {
                    Log.e(TAG, "Erro:\n" + e.getMessage());
                }
            }
        });
    }

    public void sendFriendRequest(String id, FriendRequestListener responseListener) {
        try {
            doPost(mContext, HTTPUtils.HOST + FriendRoutesEnum.INVITE_USER_AS_FRIEND.getRoute(), appContext.getLoggedUser().getToken(), new JSONObject().put("friend_id", id), new RestResponseListener() {
                @Override
                public void onError(String message) {
                    responseListener.onError(message);
                }
                @Override
                public void onResponse(JSONObject jsonResponse) {}
            });
        } catch (JSONException e) {
            Log.e(TAG, "Erro:\n" + e.getMessage());
        }
    }

    public void postAcceptFriendRequest(String id, FriendRequestListener responseListener) {
        try {
            doPost(mContext, HTTPUtils.HOST + FriendRoutesEnum.ACCEPT_USER_AS_FRIEND.getRoute(), appContext.getLoggedUser().getToken(), new JSONObject().put("id", id), new RestResponseListener() {
                @Override
                public void onError(String message) {
                    responseListener.onError(message);
                }
                @Override
                public void onResponse(JSONObject jsonResponse) {}
            });

        } catch (JSONException e) {
            Log.e(TAG, "Erro:\n" + e.getMessage());
        }
    }
}
