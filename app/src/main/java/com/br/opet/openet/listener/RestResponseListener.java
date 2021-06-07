package com.br.opet.openet.listener;

import com.br.opet.openet.model.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

public interface RestResponseListener {
    void onError(String message);
    void onResponse(JSONObject jsonResponse);
}
