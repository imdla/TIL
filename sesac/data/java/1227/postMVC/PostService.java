package com.example.demo.mysite.postMVC;

import com.example.demo.mysite.Post;

import java.util.List;

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

    public List<Post> readPosts() {
        return postRepository.findAll();
    }

    public Post readPostById(Long id) {
        Post post = postRepository.findById(id);

        if (post == null) {
            throw new IllegalArgumentException("No one id");
        }
        return postRepository.findById(id);
    }

    public Post updatePost(Long id, Post updatePost) {
        Post post = postRepository.findById(id);
//        if (post == null) {
//            throw new IllegalArgumentException("No input id");
//        }
        checkPostIsNull(post);

        validatePostData(updatePost);

        return postRepository.modify(id, updatePost);
    }

    public void deletePost(Long id) {
        Post post = postRepository.findById(id);
        checkPostIsNull(post);

        postRepository.delete(post);
    }

    public void checkPostIsNull(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("Invalid id !");
        }
    }

    public void validatePostData(Post post) {
        String title = post.getTitle();
        String content = post.getContent();

        if (title == null || title.isBlank()) {
            throw new RuntimeException("Please Input title");
        }

        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Please Input content");
        }
    }
}
