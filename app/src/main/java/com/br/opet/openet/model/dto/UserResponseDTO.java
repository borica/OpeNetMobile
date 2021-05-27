package com.br.opet.openet.model.dto;

import java.io.Serializable;

public class UserResponseDTO implements Serializable {

    UserDTO user;
    String token;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
