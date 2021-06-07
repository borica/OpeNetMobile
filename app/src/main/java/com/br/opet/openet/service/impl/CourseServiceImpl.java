package com.br.opet.openet.service.impl;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.br.opet.openet.enumerator.SessionRoutesEnum;
import com.br.opet.openet.listener.CourseServiceResponseListener;
import com.br.opet.openet.listener.UserServiceResponseListener;
import com.br.opet.openet.model.CourseModel;
import com.br.opet.openet.model.UserModel;
import com.br.opet.openet.model.dto.RequestUserAuthDTO;
import com.br.opet.openet.model.dto.UserResponseDTO;
import com.br.opet.openet.service.CourseService;
import com.br.opet.openet.service.UserService;
import com.br.opet.openet.service.impl.defaultRequest.RequestSingleton;
import com.br.opet.openet.service.util.HTTPUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CourseServiceImpl implements CourseService {

    private static final String TAG = CourseServiceImpl.class.getName();

    @Override
    public void listCourses(Context mContext, final CourseServiceResponseListener sessionResponseListener) throws Exception {
        RequestQueue requestQueue = RequestSingleton.getInstance(mContext).getRequestQueue();
        Gson gson = new Gson();

        JsonArrayRequest makeUserRequest = new JsonArrayRequest(Request.Method.GET, (HTTPUtils.HOST.concat("courses")), null, response -> {
            ArrayList<CourseModel> cursos = new ArrayList<>();
            for (int i = 0; i < response.length(); i++) {
                try {
                    CourseModel course = gson.fromJson(response.get(i).toString(), CourseModel.class);
                    cursos.add(course);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            sessionResponseListener.onResponse(cursos);
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
