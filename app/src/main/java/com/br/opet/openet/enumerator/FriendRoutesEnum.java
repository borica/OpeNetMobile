package com.br.opet.openet.enumerator;

public enum FriendRoutesEnum {

    LIST_ALL_FRIENDS("friends"),
    ALL_USERS_SUGGEST("users/approved"),
    COMMON_USERS_SUGGEST("users/"),
    PEDING_FRIEND_REQUESTS("friends/pending"),
    INVITE_USER_AS_FRIEND("friends/invite"),
    ACCEPT_USER_AS_FRIEND("friends/accept"),
    REJECT_USER_AS_FRIEND("friends/reject");

    private String route;

    FriendRoutesEnum(String route){
        this.route = route;
    }

    public String getRoute() {
        return route;
    }

}
