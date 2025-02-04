package com.example.demo.myjpasitev4.dto;

import com.example.demo.myjpasitev4.PostV4;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

// title, content, author 받아서 Post 만드는 역할
@Getter
@NoArgsConstructor
public class PostCreateRequestDto {

    @NotBlank(message = "제목은 필수 입력입니다.")
    @Length(max = 20, message = "내용은 최대 20자 이하여야 입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력입니다.")
    @Length(min = 5, message = "내용은 최소 5자 이상이어야 입니다.")
    private String content;

    @Length(min = 2, max = 10, message = "작성 이름은 2자 이상 10자 이하여야 입니다.")
    private String author;

    public PostV4 toEntity() {
        return PostV4.builder()
                .title(this.title)
                .content(this.content)
                .author(this.author)
                .build();
    }
}
