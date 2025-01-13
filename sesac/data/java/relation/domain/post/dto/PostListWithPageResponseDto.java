package com.example.relation.domain.post.dto;

import com.example.relation.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class PostListWithPageResponseDto {
    private final List<PostListItemResponseDto> posts;
    private final long totalPages;
    private final boolean hasNext;
    private final boolean hasPrevious;

    public static PostListWithPageResponseDto from(Page<Post> posts) {
        return PostListWithPageResponseDto.builder()
                .posts(
                        posts.getContent().stream().map(
                                PostListItemResponseDto::from
                        ).toList()
                )
                .totalPages(posts.getTotalPages())
                .hasNext(posts.hasNext())
                .hasPrevious(posts.hasPrevious())
                .build();
    }

    @Getter
    @Builder
    public static class PostListItemResponseDto {
        private final Long id;
        private final String title;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;

        public static PostListItemResponseDto from(Post entity){
            return PostListItemResponseDto.builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .createdAt(entity.getCreatedAt())
                    .updatedAt(entity.getUpdatedAt())
                    .build();
        }

    }
}
