package com.br.opet.openet.service;

import android.content.Context;

import com.br.opet.openet.listener.UserServiceResponseListener;
import com.br.opet.openet.model.UserModel;
import com.br.opet.openet.model.dto.RequestUserAuthDTO;

public interface UserService {

    void createUser(UserModel saveUser) throws Exception;
    void autenticate(Context mContext, RequestUserAuthDTO RequestUserAuth, final UserServiceResponseListener sessionResponseListener) throws Exception;

}
