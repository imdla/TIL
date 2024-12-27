package com.example.demo.mysite.postMVC;

import com.example.demo.mysite.Post;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    private List<Post> posts = new ArrayList<>();
    private Long id = 0L;

    // 저장. create 자체
    public Post save(Post newPost) {
        String title = newPost.getTitle();
        String content = newPost.getContent();

        Post post = new Post(++id, title, content);
        posts.add(post);
        return post;
    }
}
