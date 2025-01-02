package com.example.demo.myjpasitev4;

import com.example.demo.myjpasitev4.dto.PostCreateRequestDto;
import com.example.demo.myjpasitev4.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
