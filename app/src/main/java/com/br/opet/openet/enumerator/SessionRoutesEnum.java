package com.br.opet.openet.enumerator;

public enum SessionRoutesEnum {

    SESSION_AUTH_ROUTE("session");

    private String route;

    SessionRoutesEnum(String route){
        this.route = route;
    }

    public String getRoute() {
        return route;
    }

}
