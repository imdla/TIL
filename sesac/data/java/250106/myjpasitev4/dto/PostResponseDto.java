package com.example.demo.myjpasitev4.dto;

import com.example.demo.myjpasitev4.PostV4;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder // AllArgsContructor 생성됨
public class PostResponseDto {
    private final int id;

    private final String title;
    private final String content;
    private final String author;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public static PostResponseDto from(PostV4 entity) {
        return PostResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .author(entity.getAuthor())
                .createAt(entity.getCreatedAt())
                .updateAt(entity.getUpdatedAt())
                .build();

//        return new PostResponseDto(entity.getId(), entity.getTitle() ...);
    }
}
