package com.yigit.blog_api.post.dto;

import com.yigit.blog_api.post.PostStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePostRequest(

        @NotBlank(message = "Title is required.") @Size(max = 150) String title,

        @NotBlank(message = "Slug is required.") @Size(max = 180) String slug,

        @NotBlank(message = "Summary is required.") @Size(max = 300) String summary,

        @NotBlank(message = "Content is required.") String content,

        String coverImageUrl,

        @NotNull(message = "Status is required.") PostStatus status

) {
}