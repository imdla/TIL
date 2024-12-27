package com.example.demo.mysite.postMVC;

import com.example.demo.mysite.Post;

public class PostService {
    PostRepository postRepository = new PostRepository();

    // 컨트롤러에게 data 입력받아
    public Post createPost(Post newPost) {

        // validation 체크한 후에
        String title = newPost.getTitle();
        String content = newPost.getContent();

        if (title == null || title.isBlank()) {
            throw new RuntimeException("please input title !");
        }

        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("please input content !");
        }

        // post 생성
        return postRepository.save(newPost);
    }
}
