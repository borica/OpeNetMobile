package com.br.opet.openet.service.impl;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.br.opet.openet.enumerator.SessionRoutesEnum;
import com.br.opet.openet.listener.UserServiceResponseListener;
import com.br.opet.openet.model.UserModel;
import com.br.opet.openet.model.dto.RequestUserAuthDTO;
import com.br.opet.openet.model.dto.UserRegisterDTO;
import com.br.opet.openet.model.dto.UserResponseDTO;
import com.br.opet.openet.service.UserService;
import com.br.opet.openet.service.impl.defaultRequest.RequestSingleton;
import com.br.opet.openet.service.util.HTTPUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

public class UserServiceImpl implements UserService {

    private static final String TAG = UserServiceImpl.class.getName();

    @Override
    public void createUser(Context mContext, UserRegisterDTO RequestUserAuth, final UserServiceResponseListener sessionResponseListener) throws Exception {
        RequestQueue requestQueue = RequestSingleton.getInstance(mContext).getRequestQueue();

        Gson gson = new Gson();

        final JSONObject newUserRequest  = new JSONObject(gson.toJson(RequestUserAuth));

        JsonObjectRequest makeUserRequest = new JsonObjectRequest(Request.Method.POST, (HTTPUtils.HOST.concat("users")), newUserRequest, response -> {

            UserResponseDTO userResponseDTO = gson.fromJson(response.toString(), UserResponseDTO.class);

            sessionResponseListener.onResponse(new UserModel(userResponseDTO));
        }, error -> {
            String cause;

            if(error.getMessage() == null) {
                if(error.networkResponse != null)
                    cause = String.valueOf(error.networkResponse.statusCode);
                else
                    cause = "Erro não identificado.";
            } else {
                cause = error.getMessage();
            }

            Log.e(TAG, "Erro ao autenticar:\n" + cause);
            sessionResponseListener.onError(cause);
        });

        requestQueue.add(makeUserRequest);
    }

    @Override
    public void authenticate(Context mContext, RequestUserAuthDTO RequestUserAuth, final UserServiceResponseListener sessionResponseListener) throws Exception {

        RequestQueue requestQueue = RequestSingleton.getInstance(mContext).getRequestQueue();

        Gson gson = new Gson();

        final JSONObject newUserRequest  = new JSONObject(gson.toJson(RequestUserAuth));

        JsonObjectRequest makeUserRequest = new JsonObjectRequest(Request.Method.POST, (HTTPUtils.HOST + SessionRoutesEnum.SESSION_AUTH_ROUTE.getRoute()), newUserRequest, response -> {

            UserResponseDTO userResponseDTO = gson.fromJson(response.toString(), UserResponseDTO.class);

            sessionResponseListener.onResponse(new UserModel(userResponseDTO));
        }, error -> {
            String cause;

            if(error.getMessage() == null) {
                if(error.networkResponse != null)
                    cause = String.valueOf(error.networkResponse.statusCode);
                else
                    cause = "Erro não identificado.";
            } else {
                cause = error.getMessage();
            }

            Log.e(TAG, "Erro ao autenticar:\n" + cause);
            sessionResponseListener.onError(cause);
        });

        requestQueue.add(makeUserRequest);
    }
}
