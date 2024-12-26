package com.example.demo.mysite;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostApi {
    List<Post> posts = new ArrayList<Post>();
    private Long id = 0L; // 게시글 생성에서 id 생성위한 장치

    {
        // 인스턴스가 생성되었을 때 한 번 실행됨
        // 초기값을 넣을 때 사용
        posts.add(new Post(++id, "title", "content"));
    }

    // create (post / 내용 / url)
    @GetMapping("/posts/create")
    public Post createPost() {
        Post post = new Post(++id, "title", "content");
        posts.add(post);
        return post;
    }

    // read (posts / get)
    @GetMapping("/posts")
    public List<Post> readPosts() {
        return posts;
    }

    // read - 단일 조회
    @GetMapping("/posts/{id}")
    public Post readPostById(@PathVariable Long id) {
        for (Post post : posts) {
            if (post.getId().equals(id)) {
                return post;
            }
        }
        return null;
    }

    // update (변경 내용 / id / url)
    @GetMapping("/posts/{id}/update")
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
}
