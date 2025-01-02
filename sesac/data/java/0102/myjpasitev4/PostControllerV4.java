package com.example.demo.myjpasitev4;

import com.example.demo.myjpasitev4.dto.PostCreateRequestDto;
import com.example.demo.myjpasitev4.dto.PostListResponseDto;
import com.example.demo.myjpasitev4.dto.PostResponseDto;
import com.example.demo.myjpasitev4.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// DTO 사용pri

@RestController
@RequestMapping("/jpa/v4/posts")
@RequiredArgsConstructor
public class PostControllerV4 {
    private final PostServiceV4 postServiceV4;

    // Post method / url / data
    @PostMapping
    public PostResponseDto createPost(@RequestBody PostCreateRequestDto requestDto) {
        return postServiceV4.createPost(requestDto);
    }

    // Get method / url / 전체
    @GetMapping
    public List<PostListResponseDto> readPosts() {
        return postServiceV4.readPosts();
    }

    // Get method / url / 단일
    @GetMapping("/{id}")
    public PostResponseDto readPostById(@PathVariable Long id) {
        return postServiceV4.readPostById(id);
    }

    // Update
    @PutMapping("/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
        return postServiceV4.updatePost(id, requestDto);
    }

    // Delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id) {
        postServiceV4.deletePost(id);
    }
}
