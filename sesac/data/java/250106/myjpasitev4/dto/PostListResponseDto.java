package com.example.demo.myjpasitev4.dto;

import com.example.demo.myjpasitev4.PostV4;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostListResponseDto {
    private final int id;
    private final String title;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public static PostListResponseDto from(PostV4 entity) {
        return PostListResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .createAt(entity.getCreatedAt())
                .updateAt(entity.getUpdatedAt())
                .build();
    }
}
