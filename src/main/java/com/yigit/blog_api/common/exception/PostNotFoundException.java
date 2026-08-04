package com.yigit.blog_api.common.exception;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(Long id) {
        super("Post with id " + id + " not found.");
    }

    public PostNotFoundException(String slug) {
        super("Post with slug '" + slug + "' not found.");
    }

}