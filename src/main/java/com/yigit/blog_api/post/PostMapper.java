package com.yigit.blog_api.post;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.yigit.blog_api.post.dto.CreatePostRequest;
import com.yigit.blog_api.post.dto.PagedResponse;
import com.yigit.blog_api.post.dto.PostResponse;
import com.yigit.blog_api.post.dto.UpdatePostRequest;

@Component
public class PostMapper {
    public PostResponse toResponse(Post post) {

        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getSlug(),
                post.getSummary(),
                post.getContent(),
                post.getCoverImageUrl(),
                post.getStatus(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getPublishedAt());
    }

    public Post toEntity(CreatePostRequest request) {

        Post post = new Post();

        post.setTitle(request.title());
        post.setSlug(request.slug());
        post.setSummary(request.summary());
        post.setContent(request.content());
        post.setCoverImageUrl(request.coverImageUrl());
        post.setStatus(request.status());

        return post;
    }

    public void updateEntity(Post post, UpdatePostRequest request) {
        post.setTitle(request.title());
        post.setSlug(request.slug());
        post.setSummary(request.summary());
        post.setContent(request.content());
        post.setCoverImageUrl(request.coverImageUrl());
        post.setUpdatedAt(LocalDateTime.now());
    }

    public PagedResponse<PostResponse> toPagedResponse(Page<PostResponse> posts) {
        return new PagedResponse<>(
                posts.getContent(),
                posts.getNumber(),
                posts.getSize(),
                posts.getTotalElements(),
                posts.getTotalPages(),
                posts.isFirst(),
                posts.isLast());
    }
}
