package com.yigit.blog_api.post;

import org.springframework.web.bind.annotation.RestController;

import com.yigit.blog_api.post.dto.CreatePostRequest;
import com.yigit.blog_api.post.dto.PagedResponse;
import com.yigit.blog_api.post.dto.PostResponse;
import com.yigit.blog_api.post.dto.UpdatePostRequest;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@Valid @RequestBody CreatePostRequest request) {
        PostResponse postResponse = postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long id,
            @Valid @RequestBody UpdatePostRequest request) {
        PostResponse postResponse = postService.updatePost(id, request);
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        PostResponse postResponse = postService.getPostById(id);
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping
    public ResponseEntity<PagedResponse<PostResponse>> getAllPosts(Pageable pageable) {
        PagedResponse<PostResponse> postResponses = postService.getAllPosts(pageable);
        return ResponseEntity.ok(postResponses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

}
