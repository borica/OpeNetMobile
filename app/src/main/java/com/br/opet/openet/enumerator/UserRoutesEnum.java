package com.br.opet.openet.enumerator;

public enum UserRoutesEnum {

    USER_ROUTE("users");

    private String route;

    UserRoutesEnum(String route){
        this.route = route;
    }

    public String getRoute() {
        return route;
    }

}
