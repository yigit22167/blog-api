package com.yigit.blog_api.common.exception;

public class SlugAlreadyExistsException extends RuntimeException {

    public SlugAlreadyExistsException(String slug) {
        super("Slug '" + slug + "' already exists.");
    }

}