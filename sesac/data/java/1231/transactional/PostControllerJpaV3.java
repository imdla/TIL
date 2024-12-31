package com.example.demo.myjpasitev3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jpa/v3/posts")
@RequiredArgsConstructor
public class PostControllerJpaV3 {
    private final PostServiceJpaV3 postServiceJpa;

//    @PostMapping
//    public PostJpaV3 createPost(@RequestBody PostJpaV3 newPost) {
//        return postServiceJpa.createPost(newPost);
//    }
//
//    @GetMapping
//    public List<PostJpaV3> readPosts() {
//        return postServiceJpa.readPosts();
//    }
//
//    @GetMapping("/{id}")
//    public PostJpaV3 readPostById(@PathVariable Long id) {
//        return postServiceJpa.readPostById(id);
//    }
//
//    @PutMapping("/{id}")
//    public PostJpaV3 updatePost(@PathVariable Long id, @RequestBody PostJpaV3 updatedPost) {
//        return postServiceJpa.updatePost(id, updatedPost);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deletePost(@PathVariable Long id) {
//        postServiceJpa.deletePost(id);
//    }
}
