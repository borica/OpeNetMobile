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
    Boolean isAdmin;
    CourseModel couseModel;
    Date birth_date;
    Date created_at;
    Date updated_at;
    String avatar_url;
    String token;

    public UserModel(){}

    public UserModel(String name, CourseModel couseModel, String avatarUrl ){
        this.name = name;
        this.couseModel = couseModel;
        this.avatar_url = avatarUrl;
    }

    public UserModel(String id, String name, CourseModel couseModel, String avatarUrl ){
        this.id = id;
        this.name = name;
        this.couseModel = couseModel;
        this.avatar_url = avatarUrl;
    }

    public UserModel(String username, String password){
        this.username = username;
        this.password = password;
    }

    public  UserModel(UserResponseDTO userResponseDTO) {
        this.id = userResponseDTO.getUser().getId();
        this.name = userResponseDTO.getUser().getName();
        this.username = userResponseDTO.getUser().getUsername();
        this.password = userResponseDTO.getUser().getPassword();
        this.email = userResponseDTO.getUser().getEmail();
        this.avatar = userResponseDTO.getUser().getAvatar();
        this.isAdmin = userResponseDTO.getUser().isAdmin();
        //TODO Parse CourseId to CourseModel
        //this.couseModel = userResponseDTO.getUser().get ;
        //TODO Parse birth_date to Date
        //this.birth_date;
        //TODO Parse created_at to Date
        //this.created_at;
        //TODO Parse updated_at to Date
        //this.updated_at;
        this.avatar_url = userResponseDTO.getUser().getAvatar_url();
        this.token = userResponseDTO.getToken();
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
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

    public Date getcreated_at() {
        return created_at;
    }

    public void setcreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getupdated_at() {
        return updated_at;
    }

    public void setupdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getavatar_url() {
        return avatar_url;
    }

    public void setavatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", isAdmin=" + isAdmin +
                ", couseModel=" + couseModel +
                ", birth_date=" + birth_date +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", avatar_url='" + avatar_url + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
