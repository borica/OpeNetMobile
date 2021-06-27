package com.br.opet.openet.service;

import android.content.ContentResolver;
import android.net.Uri;

import com.br.opet.openet.listener.PostListener;

public interface PostService {

    void getPosts(PostListener postListener) throws Exception;
    void createPost() throws Exception;
    boolean createPostWithImage(ContentResolver contentResolver, Uri pathUri, String postText) throws Exception;

}
