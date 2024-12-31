package com.example.demo.myjpasitev3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jpa/v3/posts")
@RequiredArgsConstructor
public class PostControllerJpaV3 {
    private final PostServiceJpaV3 postServiceJpaV3;

    @PostMapping
    public PostJpaV3 createPost(@RequestBody PostJpaV3 newPost) {
        return postServiceJpaV3.createPost(newPost);
    }

    @GetMapping
    public List<PostJpaV3> readPosts() {
        return postServiceJpaV3.readPosts();
    }

    @GetMapping("/{id}")
    public PostJpaV3 readPostById(@PathVariable Long id) {
        return postServiceJpaV3.readPostById(id);
    }

    @PutMapping("/{id}")
    public PostJpaV3 updatePost(@PathVariable Long id, @RequestBody PostJpaV3 updatedPost) {
        return postServiceJpaV3.updatePost(id, updatedPost);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postServiceJpaV3.deletePost(id);
    }
}
