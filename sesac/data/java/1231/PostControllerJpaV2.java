package com.example.demo.myjpasitev2;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jpa/posts")
@RequiredArgsConstructor
public class PostControllerJpaV2 {
    private final PostServiceJpaV2 postServiceJpa;

//    @PostMapping
//    public PostJpaV2 createPost(@RequestBody PostJpaV2 newPost) {
//        return postServiceJpa.createPost(newPost);
//    }
//
//    @GetMapping
//    public List<PostJpaV2> readPosts() {
//        return postServiceJpa.readPosts();
//    }
//
//    @GetMapping("/{id}")
//    public PostJpaV2 readPostById(@PathVariable Long id) {
//        return postServiceJpa.readPostById(id);
//    }
//
//    @PutMapping("/{id}")
//    public PostJpaV2 updatePost(@PathVariable Long id, @RequestBody PostJpaV2 updatedPost) {
//        return postServiceJpa.updatePost(id, updatedPost);
//    }
//
//    @DeleteMapping("/id}")
//    public void deletePost(@PathVariable Long id) {
//        postServiceJpa.deletePost(id);
//    }
}
