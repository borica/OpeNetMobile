package com.br.opet.openet.model.dto;

public class RequestUserAuthDTO {

    String username;
    String password;

    public RequestUserAuthDTO(){}

    public RequestUserAuthDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
