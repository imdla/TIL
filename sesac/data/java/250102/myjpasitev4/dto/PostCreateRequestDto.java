package com.example.demo.myjpasitev4.dto;

import com.example.demo.myjpasitev4.PostV4;
import lombok.Getter;
import lombok.NoArgsConstructor;

// title, content, author 받아서 Post 만드는 역할
@Getter
@NoArgsConstructor
public class PostCreateRequestDto {
    private String title;
    private String content;
    private String author;

    public PostV4 toEntity() {
        return PostV4.builder()
                .title(this.title)
                .content(this.content)
                .author(this.author)
                .build();
    }
}
