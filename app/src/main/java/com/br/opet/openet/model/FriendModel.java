package com.br.opet.openet.model;

public class FriendModel {

    String id;
    String name;
    String avatar;
    CourseModel course;

    public FriendModel() {}

    public FriendModel(String name, String avatar, CourseModel course) {
        this.name = name;
        this.avatar = avatar;
        this.course = course;
    }

    public FriendModel(String id, String name, String avatar, CourseModel course) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.course = course;
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

    public String getImage() {
        return avatar;
    }

    public void setImage(String avatar) {
        this.avatar = avatar;
    }

    public CourseModel getCourse() {
        return course;
    }

    public void setCourse(CourseModel course) {
        this.course = course;
    }
}
