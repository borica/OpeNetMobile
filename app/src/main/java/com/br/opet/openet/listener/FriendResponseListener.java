package com.br.opet.openet.listener;

import com.br.opet.openet.model.FriendModel;

import java.util.List;

public interface FriendResponseListener {

    void onError(String message);
    void onResponse(FriendModel friendModelResponse);
    void onResponseList(List<FriendModel> friendModelListResponse);

}
