package com.br.opet.openet.listener;

import com.br.opet.openet.model.UserModel;
import com.br.opet.openet.service.UserService;

public interface UserServiceResponseListener {

    void onError(String message);

    void onResponse(UserModel userModelResponse);
}
