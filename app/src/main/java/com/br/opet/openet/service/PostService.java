package com.br.opet.openet.service;

import com.br.opet.openet.listener.PostListener;

public interface PostService {

    void getPosts(PostListener postListener) throws Exception;
    void createPost() throws Exception;

}
