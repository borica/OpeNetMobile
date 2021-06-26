package com.br.opet.openet.service;

import com.br.opet.openet.listener.FriendRequestListener;
import com.br.opet.openet.listener.UserServiceResponseListener;
import com.br.opet.openet.listener.UsersToApproveListener;
import com.br.opet.openet.model.dto.RequestUserAuthDTO;
import com.br.opet.openet.model.dto.UserRegisterDTO;

public interface UserService {

    void createUser(UserRegisterDTO RequestUserAuth, final UserServiceResponseListener sessionResponseListener) throws Exception;
    void authenticate(RequestUserAuthDTO RequestUserAuth, final UserServiceResponseListener sessionResponseListener) throws Exception;
    void usersToApprove(final UsersToApproveListener sessionResponseListener) throws Exception;
    void postApproveUser(String id, UsersToApproveListener usersToApproveListener) throws Exception;
}
