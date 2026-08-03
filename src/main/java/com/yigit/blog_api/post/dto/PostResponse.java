package com.yigit.blog_api.post.dto;

import java.time.LocalDateTime;

import com.yigit.blog_api.post.PostStatus;

public record PostResponse(

    Long id,

    String title,

    String slug,

    String summary,

    String content,

    String coverImageUrl,

    PostStatus status,

    LocalDateTime createdAt,

    LocalDateTime updatedAt,

    LocalDateTime publishedAt

) {
}