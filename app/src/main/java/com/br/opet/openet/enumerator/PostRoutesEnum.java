package com.br.opet.openet.enumerator;

public enum PostRoutesEnum {

    LIST_ALL_POSTS("post/list"),
    CREATE_POST("post/");

    private String route;

    PostRoutesEnum(String route){
        this.route = route;
    }

    public String getRoute() {
        return route;
    }

}
