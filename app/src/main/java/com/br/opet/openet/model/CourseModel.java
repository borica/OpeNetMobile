package com.br.opet.openet.model;

public class CourseModel {

    String id;
    String course;
    String time_course;
    String created_at;
    String updated_at;

    public CourseModel(String id, String course, String timeCourse, String created, String updated) {
        this.id = id;
        this.course = course;
        this.time_course = timeCourse;
        this.created_at = created;
        this.updated_at = updated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTimeCourse() {
        return time_course;
    }

    public void setTimeCourse(String timeCourse) {
        this.time_course = timeCourse;
    }

    public String getCreated() {
        return created_at;
    }

    public void setCreated(String created) {
        this.created_at = created;
    }

    public String getUpdated() {
        return updated_at;
    }

    public void setUpdated(String updated) {
        this.updated_at = updated;
    }

    @Override
    public String toString() {
        return course;
    }
}
