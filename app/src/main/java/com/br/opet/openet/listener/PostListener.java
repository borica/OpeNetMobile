package com.br.opet.openet.listener;

import com.br.opet.openet.model.PostModel;

import java.util.List;

public interface PostListener {
    void onError(String message);
    void onResponse(List<PostModel> postList);
}
