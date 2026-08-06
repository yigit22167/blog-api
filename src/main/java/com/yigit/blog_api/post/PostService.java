package com.yigit.blog_api.post;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yigit.blog_api.common.exception.PostNotFoundException;
import com.yigit.blog_api.common.exception.SlugAlreadyExistsException;
import com.yigit.blog_api.post.dto.CreatePostRequest;
import com.yigit.blog_api.post.dto.PagedResponse;
import com.yigit.blog_api.post.dto.PostResponse;
import com.yigit.blog_api.post.dto.UpdatePostRequest;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Transactional
    public PostResponse createPost(CreatePostRequest request) {
        if (postRepository.existsBySlug(request.slug())) { // select count(*) from posts where slug = ?
            throw new SlugAlreadyExistsException(request.slug());
        }

        Post post = postMapper.toEntity(request);

        LocalDateTime now = LocalDateTime.now();

        post.setCreatedAt(now);
        post.setUpdatedAt(now);

        if (request.status() == PostStatus.PUBLISHED)
            post.setPublishedAt(now);

        Post savedPost = postRepository.save(post);

        return postMapper.toResponse(savedPost);
    }

    @Transactional
    public PostResponse updatePost(Long id, UpdatePostRequest request) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        if (!post.getSlug().equals(request.slug())
                && postRepository.existsBySlug(request.slug())) {
            throw new SlugAlreadyExistsException(request.slug());
        }

        PostStatus oldStatus = post.getStatus();
        PostStatus newStatus = request.status();

        postMapper.updateEntity(post, request);

        if (oldStatus == PostStatus.DRAFT
                && newStatus == PostStatus.PUBLISHED) {
            post.setPublishedAt(LocalDateTime.now());
        }

        if (oldStatus == PostStatus.PUBLISHED
                && newStatus == PostStatus.DRAFT) {
            post.setPublishedAt(null);
        }

        post.setStatus(newStatus);

        Post savedPost = postRepository.save(post);

        return postMapper.toResponse(savedPost);
    }

    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        return postMapper.toResponse(post);
    }

    public PagedResponse<PostResponse> getAllPosts(Pageable pageable) {
        Page<PostResponse> page = postRepository.findAll(pageable)
                .map(postMapper::toResponse);

        return postMapper.toPagedResponse(page);
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        postRepository.delete(post);
    }
}