package com.br.opet.openet.listener;

import com.br.opet.openet.model.CourseModel;

import java.util.ArrayList;

public interface CourseServiceResponseListener {

    void onError(String message);

    void onResponse(ArrayList<CourseModel> courses);
}
