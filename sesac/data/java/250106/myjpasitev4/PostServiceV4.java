package com.example.demo.myjpasitev4;

import com.example.demo.myjpasitev4.dto.PostCreateRequestDto;
import com.example.demo.myjpasitev4.dto.PostListResponseDto;
import com.example.demo.myjpasitev4.dto.PostResponseDto;
import com.example.demo.myjpasitev4.dto.PostUpdateRequestDto;
import com.example.demo.myjpasitev4.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceV4 {
    private final PostRepositoryV4 postRepositoryV4;

    @Transactional
    public PostResponseDto createPost(PostCreateRequestDto requestDto) {
        PostV4 post = postRepositoryV4.save(requestDto.toEntity());
        return PostResponseDto.from(post);
    }

    public List<PostListResponseDto> readPosts() {
        // PostListResponseDto 통해 제한된 정보 줌
        // map((x) -> PostResponseDto.from(x))
        return postRepositoryV4.findAll().stream()
                .map(PostListResponseDto::from)
                .toList();
    }

    public PostResponseDto readPostById(Long id) {
        PostV4 post = postRepositoryV4.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        return PostResponseDto.from(post);
    }

    @Transactional()
    public PostResponseDto updatePost(Long id, PostUpdateRequestDto requestDto) {
        PostV4 post = postRepositoryV4.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        post.update(requestDto);
//        더티 체킹이 해줘서 save할 필요 없음
//        -> 수정만 해도 반영이 됨
//        postRepositoryV4.save(post);
        return PostResponseDto.from(post);
    }

    @Transactional
    public void deletePost(Long id) {
        PostV4 post = postRepositoryV4.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        postRepositoryV4.delete(post);
    }
}
