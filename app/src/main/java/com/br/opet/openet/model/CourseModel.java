package com.br.opet.openet.model;

import com.br.opet.openet.model.dto.CourseDTO;

public class CourseModel {

    String id;
    String course;
    String timeCourse;
    String created;
    String updated;

    public CourseModel(){}

    public CourseModel(CourseDTO courseDTO) {
        this.id = courseDTO.getId();
        this.course = courseDTO.getCourse();
        this.timeCourse = courseDTO.getTime_course();
        this.created = courseDTO.getCreated_at();
        this.updated = courseDTO.getUpdated_at();
    }

    public CourseModel(String courseName){
        this.course = courseName;
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
        return timeCourse;
    }

    public void setTimeCourse(String timeCourse) {
        this.timeCourse = timeCourse;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
