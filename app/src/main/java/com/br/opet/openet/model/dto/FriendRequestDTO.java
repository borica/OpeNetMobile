package com.br.opet.openet.model.dto;

import com.br.opet.openet.model.FriendModel;

import java.io.Serializable;

public class FriendRequestDTO implements Serializable {

    String id;
    Boolean accept;
    UserDTO user;

    public FriendRequestDTO() {}

    public FriendRequestDTO(String id, Boolean accept, UserDTO user) {
        this.id = id;
        this.accept = accept;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
