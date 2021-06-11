package com.br.opet.openet.model;

public class FriendRequestModel {

    String id;
    String user;
    String friend;
    Boolean accept;

    public FriendRequestModel() {}

    public FriendRequestModel(String id, String user, String friend, Boolean accept) {
        this.id = id;
        this.user = user;
        this.friend = friend;
        this.accept = accept;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }
}
