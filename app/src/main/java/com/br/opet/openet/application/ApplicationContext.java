package com.br.opet.openet.application;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.br.opet.openet.BuildConfig;
import com.br.opet.openet.model.UserModel;

import net.gotev.uploadservice.UploadServiceConfig;

public class ApplicationContext extends Application {

    public static final String CHANNEL_1_ID = "channel1";
    private NotificationChannel channel1;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
        UploadServiceConfig.initialize(this, CHANNEL_1_ID, BuildConfig.DEBUG);
    }

    private void createNotificationChannels() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel1.setDescription("Notification channel to display upload status");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
    }

    private UserModel loggedUser;

    public NotificationChannel getChannel1() {
        return channel1;
    }

    public void setChannel1(NotificationChannel channel1) {
        this.channel1 = channel1;
    }

    public UserModel getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(UserModel loggedUser) {
        this.loggedUser = loggedUser;
    }
}
