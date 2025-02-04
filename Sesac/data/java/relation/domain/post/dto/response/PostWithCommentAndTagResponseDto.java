package com.example.relation.domain.post.dto.response;

import com.example.relation.domain.comment.Comment;
import com.example.relation.domain.comment.dto.CommentResponseDto;
import com.example.relation.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class PostWithCommentAndTagResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final List<CommentResponseDto> comments;
    private final List<String> tags;

    public static PostWithCommentAndTagResponseDto from(Post entity, List<Comment> comments) {
        return PostWithCommentAndTagResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .author(entity.getAuthor())
                .comments(
                        comments.stream()
                                .map(CommentResponseDto::from)
                                .toList()
                )
                .tags(
                        entity.getPostTags().stream()
                                .map(
                                        postTag -> postTag.getTag().getName()
                                )
                                .toList()
                )
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
