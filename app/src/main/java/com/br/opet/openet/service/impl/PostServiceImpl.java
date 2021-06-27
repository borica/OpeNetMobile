package com.br.opet.openet.service.impl;

import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.StringRes;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.br.opet.openet.R;
import com.br.opet.openet.activity.DashboardActivity;
import com.br.opet.openet.application.ApplicationContext;
import com.br.opet.openet.enumerator.PostRoutesEnum;
import com.br.opet.openet.listener.PostListener;
import com.br.opet.openet.listener.RestResponseListener;
import com.br.opet.openet.model.PostModel;
import com.br.opet.openet.model.dto.PostDTO;
import com.br.opet.openet.service.PostService;
import com.br.opet.openet.service.impl.defaultRequest.DefaultRestClient;
import com.br.opet.openet.service.util.HTTPUtils;
import com.br.opet.openet.util.ComponentUtil;

import net.gotev.uploadservice.data.UploadNotificationAction;
import net.gotev.uploadservice.data.UploadNotificationConfig;
import net.gotev.uploadservice.data.UploadNotificationStatusConfig;
import net.gotev.uploadservice.extensions.ContextExtensionsKt;
import net.gotev.uploadservice.protocols.multipart.MultipartUploadRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PostServiceImpl extends DefaultRestClient implements PostService {

    private static final String TAG = PostServiceImpl.class.getName();

    private ApplicationContext appContext;
    private Context mContext;


    public PostServiceImpl(Context mContext) {
        this.mContext = mContext;
        appContext = (ApplicationContext) mContext.getApplicationContext();
        setupClient();
    }

    @Override
    public void getPosts(PostListener postListener) throws Exception {

        String token = appContext.getLoggedUser().getToken();
        doGet(mContext, (HTTPUtils.HOST + PostRoutesEnum.LIST_ALL_POSTS.getRoute()), token, new RestResponseListener() {
            @Override
            public void onError(String message) {postListener.onError(message);}
            @Override
            public void onResponse(JSONObject jsonResponse) {
                try {
                    List<PostModel> postsList = new ArrayList<>();
                    JSONArray usersToApproveListJsonArray = jsonResponse.getJSONArray("posts");
                    for (int i = 0; i < usersToApproveListJsonArray.length(); i++) {
                        PostDTO postDTO = gson.fromJson(usersToApproveListJsonArray.get(i).toString(), PostDTO.class);
                        postsList.add(new PostModel(postDTO));
                    }
                    postListener.onResponse(postsList);
                } catch (JSONException e) {
                    Log.e(TAG, "Erro:\n" + e.getMessage());
                }
            }
        });

    }

    @Override
    public void createPost() throws Exception {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean createPostWithImage(ContentResolver contentResolver, Uri pathUri, String postText) throws Exception {
        Boolean success = true;
        String path = ComponentUtil.getPath(pathUri, contentResolver);
        NotificationChannel channel1 = appContext.getChannel1();
        UploadNotificationStatusConfig uploadNotificationStatusConfig = new UploadNotificationStatusConfig("Novo post - OpeNet", "Fazendo upload do seu post :)", android.R.drawable.ic_menu_upload);
        //Uploading code
        try {
            //Creating a multi part request
            new MultipartUploadRequest(mContext, (HTTPUtils.HOST + PostRoutesEnum.CREATE_POST.getRoute()))
                    .setMethod("POST")
                    .addFileToUpload(path, "post", "", "multipart/form-data") //Adding file
                    .addParameter("title", postText) //Adding text parameter to the request
                    .addHeader("Authorization", "Bearer " + appContext.getLoggedUser().getToken())
                    .setNotificationConfig((context, uploadId)->getNotificationConfig(uploadId, R.string.app_name))
                    .setMaxRetries(2)
                    .startUpload(); //Starting the upload

        } catch (Exception exc) {
            success = false;
        }
        return success;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public UploadNotificationConfig getNotificationConfig(final String uploadId, @StringRes int title) {
        PendingIntent clickIntent = PendingIntent.getActivity(
                mContext, 1, new Intent(mContext, DashboardActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        final boolean autoClear = false;
        final Bitmap largeIcon = null;
        final boolean clearOnAction = true;
        final boolean ringToneEnabled = true;
        final ArrayList<UploadNotificationAction> noActions = new ArrayList<>(1);

        final UploadNotificationAction cancelAction = new UploadNotificationAction(
                R.drawable.ic_close,
                "Cancelar upload",
                ContextExtensionsKt.getCancelUploadIntent(mContext, uploadId)
        );

        final ArrayList<UploadNotificationAction> progressActions = new ArrayList<>(1);
        progressActions.add(cancelAction);

        UploadNotificationStatusConfig progress = new UploadNotificationStatusConfig(
                "Openet",
                "Realizando upload",
                android.R.drawable.ic_menu_upload,
                Color.BLUE,
                largeIcon,
                clickIntent,
                progressActions,
                clearOnAction,
                autoClear
        );

        UploadNotificationStatusConfig success = new UploadNotificationStatusConfig(
                "Openet",
                "Upload realizado com sucesso",
                android.R.drawable.ic_menu_upload,
                Color.GREEN,
                largeIcon,
                clickIntent,
                noActions,
                clearOnAction,
                autoClear
        );

        UploadNotificationStatusConfig error = new UploadNotificationStatusConfig(
                "Openet",
                "Erro ao realizar upload",
                android.R.drawable.ic_menu_upload,
                Color.RED,
                largeIcon,
                clickIntent,
                noActions,
                clearOnAction,
                autoClear
        );

        UploadNotificationStatusConfig cancelled = new UploadNotificationStatusConfig(
                "Openet",
                "Upload foi cancelado :(",
                android.R.drawable.ic_menu_upload,
                Color.YELLOW,
                largeIcon,
                clickIntent,
                noActions,
                clearOnAction
        );

        return new UploadNotificationConfig(appContext.getChannel1().getId(), ringToneEnabled, progress, success, error, cancelled);
    }
}
