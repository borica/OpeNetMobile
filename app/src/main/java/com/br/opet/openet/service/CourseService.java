package com.br.opet.openet.service;

import android.content.Context;

import com.br.opet.openet.listener.CourseServiceResponseListener;

public interface CourseService {

    void listCourses(Context context, CourseServiceResponseListener sessionResponseListener) throws Exception;

}
