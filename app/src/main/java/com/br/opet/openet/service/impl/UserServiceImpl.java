package com.br.opet.openet.service.impl;

import android.content.Context;
import android.util.Log;

import com.br.opet.openet.application.ApplicationContext;
import com.br.opet.openet.enumerator.FriendRoutesEnum;
import com.br.opet.openet.enumerator.SessionRoutesEnum;
import com.br.opet.openet.enumerator.UserRoutesEnum;
import com.br.opet.openet.listener.FriendRequestListener;
import com.br.opet.openet.listener.RestResponseListener;
import com.br.opet.openet.listener.UserServiceResponseListener;
import com.br.opet.openet.listener.UsersToApproveListener;
import com.br.opet.openet.model.CourseModel;
import com.br.opet.openet.model.FriendModel;
import com.br.opet.openet.model.UserModel;
import com.br.opet.openet.model.dto.RequestUserAuthDTO;
import com.br.opet.openet.model.dto.UserDTO;
import com.br.opet.openet.model.dto.UserRegisterDTO;
import com.br.opet.openet.model.dto.UserResponseDTO;
import com.br.opet.openet.service.UserService;
import com.br.opet.openet.service.impl.defaultRequest.DefaultRestClient;
import com.br.opet.openet.service.util.HTTPUtils;
import com.br.opet.openet.util.ComparatorUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserServiceImpl extends DefaultRestClient implements UserService {

    private static final String TAG = UserServiceImpl.class.getName();

    private ApplicationContext appContext;
    private Context mContext;

    public UserServiceImpl(Context mContext) {
        this.mContext = mContext;
        appContext = (ApplicationContext) mContext.getApplicationContext();
        setupClient();
    }

    @Override
    public void createUser(UserRegisterDTO RequestUserAuth, final UserServiceResponseListener sessionResponseListener) throws Exception {
        doPost(mContext, (HTTPUtils.HOST.concat(UserRoutesEnum.USER_ROUTE.getRoute())), null, new JSONObject(gson.toJson(RequestUserAuth)), new RestResponseListener() {
            @Override
            public void onError(String message) {
                Log.e(TAG, "Erro ao autenticar:\n" + message);
                sessionResponseListener.onError(message);
            }
            @Override
            public void onResponse(JSONObject jsonResponse) {
                sessionResponseListener.onResponse(new UserModel(gson.fromJson(jsonResponse.toString(), UserResponseDTO.class)));
            }
        });
    }

    @Override
    public void authenticate(RequestUserAuthDTO RequestUserAuth, final UserServiceResponseListener sessionResponseListener) throws Exception {
        doPost(mContext, (HTTPUtils.HOST.concat(SessionRoutesEnum.SESSION_AUTH_ROUTE.getRoute())), null, new JSONObject(gson.toJson(RequestUserAuth)), new RestResponseListener() {
            @Override
            public void onError(String message) {
                Log.e(TAG, "Erro ao autenticar:\n" + message);
                sessionResponseListener.onError(message);
            }
            @Override
            public void onResponse(JSONObject jsonResponse) {
                sessionResponseListener.onResponse(new UserModel(gson.fromJson(jsonResponse.toString(), UserResponseDTO.class)));
            }
        });
    }

    @Override
    public void usersToApprove(final UsersToApproveListener sessionResponseListener) throws Exception {
        String token = appContext.getLoggedUser().getToken();
        doGet(mContext, (HTTPUtils.HOST + UserRoutesEnum.USERS_NOT_APPROVED.getRoute()), token, new RestResponseListener() {
            @Override
            public void onError(String message) {
                sessionResponseListener.onError(message);
            }
            @Override
            public void onResponse(JSONObject jsonResponse) {
                try {
                    List<UserModel> usersToApproveList = new ArrayList<>();
                    JSONArray usersToApproveListJsonArray = jsonResponse.getJSONArray("users");
                    for (int i = 0; i < usersToApproveListJsonArray.length(); i++) {
                        UserDTO userDTO = gson.fromJson(usersToApproveListJsonArray.get(i).toString(), UserDTO.class);
                        usersToApproveList.add(new UserModel(userDTO.getId(), userDTO.getName(), new CourseModel(userDTO.getCourse_id()), userDTO.getAvatar()));
                    }
                    //Collections.sort(usersToSuggestList, new ComparatorUtil.SortByFriendName());
                    sessionResponseListener.onResponseList(usersToApproveList);
                } catch (JSONException e) {
                    Log.e(TAG, "Erro:\n" + e.getMessage());
                }
            }
        });
    }

    @Override
    public void postApproveUser(String id, UsersToApproveListener usersToApproveListener) throws Exception {
        try {
            doPatch(mContext, HTTPUtils.HOST + UserRoutesEnum.USER_APPROVE.getRoute(), appContext.getLoggedUser().getToken(), new JSONObject().put("user_id", id).put("approved", "true"), new RestResponseListener() {
                @Override
                public void onError(String message) {
                    usersToApproveListener.onError(message);
                }
                @Override
                public void onResponse(JSONObject jsonResponse) {
                    usersToApproveListener.onResponseList(new ArrayList<>());
                }
            });

        } catch (JSONException e) {
            Log.e(TAG, "Erro:\n" + e.getMessage());
        }
    }
}
