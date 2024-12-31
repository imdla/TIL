package com.example.demo.myjpasitev2;

import com.example.demo.myjpasite.PostJpa;
import com.example.demo.myjpasite.PostRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceJpaV2 {
    private final PostRepositoryJpaV2 postRepositoryJpa;

    public PostJpaV2 createPost(PostJpaV2 post) {
        return postRepositoryJpa.save(post);
    }

    public List<PostJpaV2> readPosts() {
        return postRepositoryJpa.findAll();
    }

    public PostJpaV2 readPostById(Long id) {
        return postRepositoryJpa.findById(id);
    }

    public PostJpaV2 updatePost(Long id, PostJpaV2 updatedPost) {
        return postRepositoryJpa.update(id, updatedPost);
    }

    public void deletePost(Long id) {
        postRepositoryJpa.delete(id);
    }
}
