package com.example.relation.domain.post.dto.response;

import com.example.relation.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class PostWithTagResponseDto {
    private final Long id;
    private final String title;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final List<String> tags;


    public static PostWithTagResponseDto from(Post entity) {
        return PostWithTagResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .tags(entity.getPostTags().stream()
                        .map(
                                postTag -> postTag.getTag().getName()
                        ).toList())
                .build();
    }
}
