package com.yigit.blog_api.post.dto;

import com.yigit.blog_api.post.PostStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePostRequest(

    @NotBlank
    @Size(max = 150)
    String title,

    @NotBlank
    @Size(max = 180)
    String slug,

    @NotBlank
    @Size(max = 300)
    String summary,

    @NotBlank
    String content,

    String coverImageUrl,

    @NotNull
    PostStatus status

) {}