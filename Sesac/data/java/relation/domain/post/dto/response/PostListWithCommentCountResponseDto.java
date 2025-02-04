package com.example.relation.domain.post.dto.response;


import java.time.LocalDateTime;

public record PostListWithCommentCountResponseDto(
        Long id,
        String title,
        LocalDateTime createdAt,
        Long commentCount
) {}
