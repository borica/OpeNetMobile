package com.br.opet.openet.enumerator;

public enum UserRoutesEnum {

    USER_ROUTE("users"),
    USER_APPROVE("users/approved"),
    USERS_NOT_APPROVED("users/disapproved");

    private String route;

    UserRoutesEnum(String route){
        this.route = route;
    }

    public String getRoute() {
        return route;
    }

}
