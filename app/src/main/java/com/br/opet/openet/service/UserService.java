package com.br.opet.openet.service;

import com.br.opet.openet.model.UserModel;

public interface UserService {

    void createUser(UserModel saveUser) throws Exception;
    UserModel autenticate(UserModel authUser) throws Exception;

}
