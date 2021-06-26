package com.br.opet.openet.listener;

import com.br.opet.openet.model.UserModel;

import java.util.List;

public interface UsersToApproveListener {

    void onError(String message);
    void onResponseList(List<UserModel> userModelListResponse);

}
