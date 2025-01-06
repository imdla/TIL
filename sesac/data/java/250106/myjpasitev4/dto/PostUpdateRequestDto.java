package com.example.demo.myjpasitev4.dto;

import com.example.demo.myjpasitev4.PostV4;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor // 필요하지만, 이 경우는 기본 생성자를 자동으로 만들어주기 때문에 필요없음
public class PostUpdateRequestDto {
    @NotBlank(message = "제목은 필수 입력입니다.")
    @Length(max = 20, message = "내용은 최대 20자 이하여야 입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력입니다.")
    @Length(min = 5, message = "내용은 최소 5자 이상이어야 입니다.")
    private String content;
}
