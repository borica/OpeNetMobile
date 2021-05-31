package com.br.opet.openet.application;

import android.app.Application;

import com.br.opet.openet.model.UserModel;

public class ApplicationContext extends Application {

    private UserModel loggedUser;

    public UserModel getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(UserModel loggedUser) {
        this.loggedUser = loggedUser;
    }
}
