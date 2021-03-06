package com.br.opet.openet.service.impl.defaultRequest;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.br.opet.openet.enumerator.SessionRoutesEnum;
import com.br.opet.openet.listener.RestResponseListener;
import com.br.opet.openet.service.util.HTTPUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DefaultRestClient {

    protected Gson gson;

    protected void setupClient() {
        if (this.gson == null)
            gson = new Gson();
    }

    protected void doPost(Context mContext, String route, String userToken, JSONObject request, RestResponseListener responseListener) {

        RequestQueue requestQueue = RequestSingleton.getInstance(mContext).getRequestQueue();

        JsonObjectRequest makeUserRequest = new JsonObjectRequest(Request.Method.POST, route, request, responseListener::onResponse, error -> {
            String cause;
            if(error.getMessage() == null) {
                if(error.networkResponse != null)
                    cause = String.valueOf(error.networkResponse.statusCode);
                else
                    cause = "Erro não identificado.";
            } else {
                cause = error.getMessage();
            }
            responseListener.onError(cause);
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();

                if (userToken != null && userToken.length() != 0) {
                    headers.put("Authorization", "Bearer " + userToken);
                }

                return headers;
            }
        };

        requestQueue.add(makeUserRequest);
    }

    protected void doPatch(Context mContext, String route, String userToken, JSONObject request, RestResponseListener responseListener) {

        RequestQueue requestQueue = RequestSingleton.getInstance(mContext).getRequestQueue();

        JsonObjectRequest makeUserRequest = new JsonObjectRequest(Request.Method.PATCH, route, request, responseListener::onResponse, error -> {
            String cause;
            if(error.getMessage() == null) {
                if(error.networkResponse != null)
                    cause = String.valueOf(error.networkResponse.statusCode);
                else
                    cause = "Erro não identificado.";
            } else {
                cause = error.getMessage();
            }
            responseListener.onError(cause);
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();

                if (userToken != null && userToken.length() != 0) {
                    headers.put("Authorization", "Bearer " + userToken);
                }

                return headers;
            }
        };

        requestQueue.add(makeUserRequest);
    }

    protected void doGet(Context mContext, String route, String userToken, RestResponseListener responseListener) {

        RequestQueue requestQueue = RequestSingleton.getInstance(mContext).getRequestQueue();

        JsonObjectRequest makeUserRequest = new JsonObjectRequest(Request.Method.GET, route, null, responseListener::onResponse, error -> {
            String cause;
            if(error.getMessage() == null) {
                if(error.networkResponse != null)
                    cause = String.valueOf(error.networkResponse.statusCode);
                else
                    cause = "Erro não identificado.";
            } else {
                cause = error.getMessage();
            }
            responseListener.onError(cause);
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + userToken);
                return headers;
            }
        };

        requestQueue.add(makeUserRequest);
    }

}
