package com.br.opet.openet.service.impl;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.br.opet.openet.enumerator.SessionRoutesEnum;
import com.br.opet.openet.enumerator.UserRoutesEnum;
import com.br.opet.openet.listener.RestResponseListener;
import com.br.opet.openet.listener.UserServiceResponseListener;
import com.br.opet.openet.model.UserModel;
import com.br.opet.openet.model.dto.RequestUserAuthDTO;
import com.br.opet.openet.model.dto.UserRegisterDTO;
import com.br.opet.openet.model.dto.UserDTO;
import com.br.opet.openet.model.dto.UserResponseDTO;
import com.br.opet.openet.service.UserService;
import com.br.opet.openet.service.impl.defaultRequest.DefaultRestClient;
import com.br.opet.openet.service.impl.defaultRequest.RequestSingleton;
import com.br.opet.openet.service.util.HTTPUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

public class UserServiceImpl extends DefaultRestClient implements UserService {

    private static final String TAG = UserServiceImpl.class.getName();

    public UserServiceImpl() {
        setupClient();
    }

    @Override
    public void createUser(Context mContext, UserRegisterDTO RequestUserAuth, final UserServiceResponseListener sessionResponseListener) throws Exception {
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
    public void authenticate(Context mContext, RequestUserAuthDTO RequestUserAuth, final UserServiceResponseListener sessionResponseListener) throws Exception {
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
}
