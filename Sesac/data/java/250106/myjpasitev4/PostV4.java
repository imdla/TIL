package com.example.demo.myjpasitev4;

import com.example.demo.myjpasitev4.dto.PostUpdateRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostV4 extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String content;
    private String author;

    @Builder
    public PostV4(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public PostV4 update(PostUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();

        return this;
    }
}
