package com.br.opet.openet.service;

import android.content.Context;

import com.br.opet.openet.listener.UserServiceResponseListener;
import com.br.opet.openet.model.UserModel;
import com.br.opet.openet.model.dto.RequestUserAuthDTO;
import com.br.opet.openet.model.dto.UserRegisterDTO;

public interface UserService {

    void createUser(Context mContext, UserRegisterDTO RequestUserAuth, final UserServiceResponseListener sessionResponseListener) throws Exception;
    void authenticate(Context mContext, RequestUserAuthDTO RequestUserAuth, final UserServiceResponseListener sessionResponseListener) throws Exception;

}
