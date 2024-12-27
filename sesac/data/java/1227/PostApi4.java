package com.example.demo.mysite;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v4/posts")
public class PostApi4 {
    List<Post> posts = new ArrayList<Post>();
    private Long id = 0L; // 게시글 생성에서 id 생성위한 장치

    {
        // 인스턴스가 생성되었을 때 한 번 실행됨
        // 초기값을 넣을 때 사용
        posts.add(new Post(++id, "title", "content"));
    }

    @PostMapping
    public Post createPost(@RequestBody Post newPost) {
        System.out.println(newPost.getTitle());
        System.out.println(newPost.getContent());

        String title = newPost.getTitle();
        String content = newPost.getContent();

        Post post = new Post(++id, title, content);
        posts.add(post);
        return post;
    }

    @GetMapping
    public List<Post> readPosts() {
        return posts;
    }

    @GetMapping("/{id}")
    public Post readPostById(@PathVariable Long id) {
        for (Post post : posts) {
            if (post.getId().equals(id)) {
                return post;
            }
        }
        return null;
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post updatePost) {
        for (Post post : posts) {
            if (post.getId().equals(id)) {
                System.out.println(updatePost.getTitle());
                System.out.println(updatePost.getContent());

                String title = updatePost.getTitle();
                String content = updatePost.getContent();

                post.setTitle(title);
                post.setContent(content);
                return post;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Post deletePost(@PathVariable Long id) {
        for (Post post : posts) {
            if (post.getId().equals(id)) {
                posts.remove(post);
            }
        }
        return null;
    }
}
