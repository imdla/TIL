package com.example.demo.myjpasite;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceJpa {
    private final PostRepositoryJpa postRepositoryJpa;

    public PostServiceJpa(PostRepositoryJpa postRepositoryJpa) {
        this.postRepositoryJpa = postRepositoryJpa;
    }

    public PostJpa createPost(PostJpa post) {
        return postRepositoryJpa.save(post);
    }

    public List<PostJpa> readPosts() {
        return postRepositoryJpa.findAll();
    }

    public PostJpa readPostById(Long id) {
        return postRepositoryJpa.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not existed post"));
    }

    public PostJpa updatePost(Long id, PostJpa updatedPost) {
        // 수정할 post 찾아야 함
        PostJpa post = postRepositoryJpa.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not existed post"));

        String title = updatedPost.getTitle();
        String content = updatedPost.getContent();

        post.update(title, content);

        return postRepositoryJpa.save(post);
    }

    public void deletePost(Long id) {
        PostJpa post = postRepositoryJpa.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not existed id"));
        postRepositoryJpa.delete(post);
    }
}
