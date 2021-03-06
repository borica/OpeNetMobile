package com.br.opet.openet.model.dto;

import java.io.Serializable;

public class UserRegisterDTO implements Serializable {

    String name;
    String username;
    String password;
    String email;
    String course_id;
    String birth_date;

    public UserRegisterDTO(String name, String username, String password, String email, String courseId, String birth_date) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.course_id = courseId;
        this.birth_date = birth_date;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

}
