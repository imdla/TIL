package com.example.relation.domain.post.dto;

import com.example.relation.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostListResponseDto {
    private final Long id;
    private final String title;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static PostListResponseDto from(Post entity){
        return PostListResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

}