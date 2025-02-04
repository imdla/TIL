package com.example.relation.domain.post.dto;

import com.example.relation.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static PostResponseDto from(Post entity) {
        return PostResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .author(entity.getAuthor())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}