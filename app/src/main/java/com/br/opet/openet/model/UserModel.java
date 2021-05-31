package com.br.opet.openet.model;

import com.br.opet.openet.model.dto.UserResponseDTO;

import java.io.Serializable;
import java.util.Date;

public class UserModel implements Serializable {

    String id;
    String name;
    String username;
    String password;
    String email;
    String avatar;
    CourseModel couseModel;
    Date birth_date;
    Date created;
    Date update;
    String avatarUrl;
    String token;

    public UserModel(){}

    public UserModel(String username, String password){
        this.username = username;
        this.password = password;
    }

    public UserModel(UserResponseDTO userResponseDTO) {
        this.id = userResponseDTO.getUser().getId();
        this.name = userResponseDTO.getUser().getName();
        this.username = userResponseDTO.getUser().getUsername();
        this.password = userResponseDTO.getUser().getPassword();
        this.email = userResponseDTO.getUser().getEmail();
        this.avatar = userResponseDTO.getUser().getAvatar();
        //TODO Parse CourseId to CourseModel
        //this.couseModel = userResponseDTO.getUser().get ;
        //TODO Parse birth_date to Date
        //this.birth_date;
        //TODO Parse created to Date
        //this.created;
        //TODO Parse update to Date
        //this.update;
        this.avatarUrl = userResponseDTO.getUser().getAvatar_url();
        this.token = userResponseDTO.getToken();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public CourseModel getCouseModel() {
        return couseModel;
    }

    public void setCouseModel(CourseModel couseModel) {
        this.couseModel = couseModel;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
