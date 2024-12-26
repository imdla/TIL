package com.example.demo.mysite;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostApi2 {
    List<Post> posts = new ArrayList<Post>();
    private Long id = 0L; // 게시글 생성에서 id 생성위한 장치

    {
        // 인스턴스가 생성되었을 때 한 번 실행됨
        // 초기값을 넣을 때 사용
        posts.add(new Post(++id, "title", "content"));
    }

    @PostMapping("/v2/posts")
    public Post createPost() {
        Post post = new Post(++id, "title", "content");
        posts.add(post);
        return post;
    }

    @GetMapping("/v2/posts")
    public List<Post> readPosts() {
        return posts;
    }

    @GetMapping("/v2/posts/{id}")
    public Post readPostById(@PathVariable Long id) {
        for (Post post : posts) {
            if (post.getId().equals(id)) {
                return post;
            }
        }
        return null;
    }

    @PutMapping("/v2/posts/{id}")
    public Post updatePost(@PathVariable Long id) {
        for (Post post : posts) {
            if (post.getId().equals(id)) {
                post.setTitle("update title");
                post.setContent("update content");
                return post;
            }
        }
        return null;
    }

    @DeleteMapping("/v2/posts/{id}")
    public Post deletePost(@PathVariable Long id) {
        for (Post post : posts) {
            if (post.getId().equals(id)) {
                posts.remove(post);
            }
        }
        return null;
    }
}
