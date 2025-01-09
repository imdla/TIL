package com.example.relation.domain.post.dto;


import java.time.LocalDateTime;

public record PostListWithCommentCountResponseDto(
        Long id,
        String title,
        LocalDateTime createdAt,
        Long commentCount
) {}
