package com.yigit.blog_api.post.dto;

import com.yigit.blog_api.post.PostStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdatePostRequest(

        @NotBlank(message = "Title is required.") @Size(max = 150, message = "Title cannot exceed 150 characters.") String title,

        @NotBlank(message = "Slug is required.") @Size(max = 180, message = "Slug cannot exceed 180 characters.") String slug,

        @NotBlank(message = "Summary is required.") @Size(max = 300, message = "Summary cannot exceed 300 characters.") String summary,

        @NotBlank(message = "Content is required.") String content,

        @Size(max = 500, message = "Cover image URL cannot exceed 500 characters.") String coverImageUrl,

        @NotNull(message = "Status is required.") PostStatus status

) {
}