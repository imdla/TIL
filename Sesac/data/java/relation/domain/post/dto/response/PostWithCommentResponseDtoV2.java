package com.example.relation.domain.post.dto.response;

import com.example.relation.domain.comment.dto.CommentResponseDto;
import com.example.relation.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class PostWithCommentResponseDtoV2 {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final List<CommentResponseDto> comments;

    public static PostWithCommentResponseDtoV2 from(Post entity) {
        return PostWithCommentResponseDtoV2.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .author(entity.getAuthor())
                .comments(
                        entity.getComments().stream()
                                .map(CommentResponseDto::from)
                                .toList()
                )
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
