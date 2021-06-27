package com.br.opet.openet.service.impl;

import android.content.Context;
import android.util.Log;

import com.br.opet.openet.application.ApplicationContext;
import com.br.opet.openet.enumerator.PostRoutesEnum;
import com.br.opet.openet.listener.PostListener;
import com.br.opet.openet.listener.RestResponseListener;
import com.br.opet.openet.model.CourseModel;
import com.br.opet.openet.model.PostModel;
import com.br.opet.openet.model.UserModel;
import com.br.opet.openet.model.dto.PostDTO;
import com.br.opet.openet.model.dto.UserDTO;
import com.br.opet.openet.service.PostService;
import com.br.opet.openet.service.impl.defaultRequest.DefaultRestClient;
import com.br.opet.openet.service.util.HTTPUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PostServiceImpl extends DefaultRestClient implements PostService {

    private static final String TAG = PostServiceImpl.class.getName();

    private ApplicationContext appContext;
    private Context mContext;


    public PostServiceImpl(Context mContext) {
        this.mContext = mContext;
        appContext = (ApplicationContext) mContext.getApplicationContext();
        setupClient();
    }

    @Override
    public void getPosts(PostListener postListener) throws Exception {

        String token = appContext.getLoggedUser().getToken();
        doGet(mContext, (HTTPUtils.HOST + PostRoutesEnum.LIST_ALL_POSTS.getRoute()), token, new RestResponseListener() {
            @Override
            public void onError(String message) {postListener.onError(message);}
            @Override
            public void onResponse(JSONObject jsonResponse) {
                try {
                    List<PostModel> postsList = new ArrayList<>();
                    JSONArray usersToApproveListJsonArray = jsonResponse.getJSONArray("posts");
                    for (int i = 0; i < usersToApproveListJsonArray.length(); i++) {
                        PostDTO postDTO = gson.fromJson(usersToApproveListJsonArray.get(i).toString(), PostDTO.class);
                        postsList.add(new PostModel(postDTO));
                    }
                    postListener.onResponse(postsList);
                } catch (JSONException e) {
                    Log.e(TAG, "Erro:\n" + e.getMessage());
                }
            }
        });

    }

    @Override
    public void createPost() throws Exception {

    }
}
