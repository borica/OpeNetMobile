package com.br.opet.openet.service;

import android.content.Context;

import com.br.opet.openet.model.UserModel;

public interface UserService {

    void createUser(UserModel saveUser) throws Exception;
    UserModel autenticate(UserModel authUser, Context mContext) throws Exception;

}
