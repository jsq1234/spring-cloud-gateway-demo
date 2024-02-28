package com.demo.postsservice.service;

import java.util.UUID;

import java.util.List;
import org.springframework.stereotype.Component;

import com.demo.postsservice.dto.PostBody;
import com.demo.postsservice.models.Post;
import com.demo.postsservice.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> getAllPostsByUser(UUID userId) {
        return postRepository.findByUserId(userId);
    }

    public void deleteAllPostsByUser(UUID userId) {
        postRepository.deleteAllByUserId(userId);
    }

    public Post createPost(UUID userId, PostBody postBody) {
        Post post = Post.builder()
                .userId(userId)
                .message(postBody.message())
                .build();
        postRepository.save(post);
        return post;
    }
}
