package com.br.opet.openet.model;

public class PostModel {

    String id;
    UserModel userOwner;
    String text;
    String postImageURL;
    int likes;

    public PostModel() {}

    public PostModel(String id, UserModel user, String postText, String postImageURL, int likes) {

        this.id = id;
        this.userOwner = user;
        this.text = postText;
        this.postImageURL = postImageURL;
        this.likes = likes;

    }

    public String getPostImageURL() {
        return postImageURL;
    }

    public void setPostImageURL(String postImageURL) {
        this.postImageURL = postImageURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserModel getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(UserModel userOwner) {
        this.userOwner = userOwner;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

}
