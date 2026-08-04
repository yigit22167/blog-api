package com.yigit.blog_api.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yigit.blog_api.common.exception.PostNotFoundException;
import com.yigit.blog_api.common.exception.SlugAlreadyExistsException;
import com.yigit.blog_api.post.dto.CreatePostRequest;
import com.yigit.blog_api.post.dto.PostResponse;

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
        Post savedPost = postRepository.save(post);

        return postMapper.toResponse(savedPost);
    }

    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        return postMapper.toResponse(post);
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream().map(postMapper::toResponse).toList();
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        postRepository.delete(post);
    }
}