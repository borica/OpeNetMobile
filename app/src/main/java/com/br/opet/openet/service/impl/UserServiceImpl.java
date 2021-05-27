package com.br.opet.openet.service.impl;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.br.opet.openet.model.UserModel;
import com.br.opet.openet.service.UserService;
import com.br.opet.openet.service.impl.defaultRequest.RequestSingleton;
import com.google.gson.Gson;

import org.json.JSONObject;

public class UserServiceImpl implements UserService {

    @Override
    public void createUser(UserModel saveUser) throws Exception {
        //TODO Implement create user method
    }

    @Override
    public UserModel autenticate(UserModel authUser, Context mContext) throws Exception {

        RequestQueue requestQueue = RequestSingleton.getInstance(mContext).getRequestQueue();
        Gson gson = new Gson();

        final JSONObject newUserRequest  = new JSONObject(gson.toJson(authUser));

        //TODO Request Auth -> localhost:3333/session

        return null;
    }
}
