package com.br.opet.openet.service.impl;

import android.content.Context;

import com.br.opet.openet.application.ApplicationContext;
import com.br.opet.openet.enumerator.FriendRoutesEnum;
import com.br.opet.openet.listener.FriendResponseListener;
import com.br.opet.openet.listener.RestResponseListener;
import com.br.opet.openet.model.CourseModel;
import com.br.opet.openet.model.FriendModel;
import com.br.opet.openet.model.dto.UserDTO;
import com.br.opet.openet.service.FriendService;
import com.br.opet.openet.service.impl.defaultRequest.DefaultRestClient;
import com.br.opet.openet.service.util.HTTPUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/*
Cinco rotas foram criadas

        GET - /friends/pending

        Vai listar todos os pedidos de amizade pendendte
        -----------------------------------------------------------
        GET - /friends

        Vai listar todos os pedidos de amizades que foram aceitos
        -----------------------------------------------------------
        POST - /friends/accept

        body precisa passar o id do pedido de amizade
        {
        id: "uuid"
        }

        Isso vai aceitar o pedido de amizade
        -----------------------------------------------------------
        POST - /friends/invite

        body precisa passar o id do amiguinho
        {
        friend_id: "uuid"
        }

        Vai enviar um pedido de amizade;
        -----------------------------------------------------------
        DELETE - /friends/reject

        body precisa passar o id do pedido de amizade
        {
        "id": "d9d0bfa3-c142-48ce-8bd9-3407274a4188"
        }

        Vai rejeitar o pedido de amizade

 */

public class FriendServiceImpl extends DefaultRestClient implements FriendService {

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
                            usersToSuggestList.add(new FriendModel(userDTO.getName(), userDTO.getAvatar_url(), new CourseModel(userDTO.getCourse_id())));
                    }
                    responseListener.onResponseList(usersToSuggestList);
                } catch (JSONException e) {
                    e.printStackTrace();
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
                            usersToSuggestList.add(new FriendModel(userDTO.getName(), userDTO.getAvatar_url(), new CourseModel(userDTO.getCourse_id())));
                    }
                    responseListener.onResponseList(usersToSuggestList);
                } catch (JSONException e) {
                    e.printStackTrace();
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
                        UserDTO userDTO = gson.fromJson(usersToSuggestJsonArray.get(i).toString(), UserDTO.class);
                        if (!appContext.getLoggedUser().getId().equals(userDTO.getId()))
                            usersToSuggestList.add(new FriendModel(userDTO.getName(), userDTO.getAvatar_url(), new CourseModel(userDTO.getCourse_id())));
                    }
                    responseListener.onResponseList(usersToSuggestList);
                } catch (JSONException e) {
                    e.printStackTrace();
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
                    JSONArray usersToSuggestJsonArray = jsonResponse.getJSONArray("friends");
                    for (int i = 0; i < usersToSuggestJsonArray.length(); i++) {
                        UserDTO userDTO = gson.fromJson(usersToSuggestJsonArray.get(i).toString(), UserDTO.class);
                        if (!appContext.getLoggedUser().getId().equals(userDTO.getId()))
                            usersToSuggestList.add(new FriendModel(userDTO.getName(), userDTO.getAvatar_url(), new CourseModel(userDTO.getCourse_id())));
                    }
                    responseListener.onResponseList(usersToSuggestList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
