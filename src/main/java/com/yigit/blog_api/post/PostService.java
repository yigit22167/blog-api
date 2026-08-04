package com.yigit.blog_api.post;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (postRepository.existsBySlug(request.slug())) {
            throw new SlugAlreadyExistsException(request.slug());
        }

        Post post = postMapper.toEntity(request);
        Post savedPost = postRepository.save(post);

        return postMapper.toResponse(savedPost);
    }
}