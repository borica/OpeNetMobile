package com.br.opet.openet.model.dto;

public class PostDTO {

    public PostDTO(){}

    public PostDTO(String id, String title, int like, String created_at, String updated_at, UserDTO user, String post_url) {
        this.id = id;
        this.title = title;
        this.like = like;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.user = user;
        this.post_url = post_url;
    }

    private String id;
    private String title;
    private int like;
    private String created_at;
    private String updated_at;
    private UserDTO user;
    private String post_url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getPost_url() {
        return post_url;
    }

    public void setPost_url(String post_url) {
        this.post_url = post_url;
    }
}
