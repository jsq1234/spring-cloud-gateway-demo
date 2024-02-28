package com.demo.postsservice.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.demo.postsservice.dto.PostBody;
import com.demo.postsservice.models.Post;
import com.demo.postsservice.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/")
    public ResponseEntity<Post> createPost(
            @RequestHeader(name = "user_id", required = true) String userId,
            @RequestBody PostBody postMessage) {

        UUID uuid = UUID.fromString(userId);
        Post post = postService.createPost(uuid, postMessage);
        return ResponseEntity.status(201).body(post);
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPosts(
            @RequestHeader(name = "user_id", required = true) String userId) {

        UUID uuid = UUID.fromString(userId);
        List<Post> posts = postService.getAllPostsByUser(uuid);
        return ResponseEntity.ok().body(posts);
    }
}
