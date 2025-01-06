package com.example.demo.usersite.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    @NotBlank(message = "이메일은 필수 입력입니다.")
    @Email
    private String email;

    @NotBlank(message = "닉네임은 필수 입력입니다.")
    @Length(min = 2, max = 10, message = "닉네임은 최대 2자 이상 10자 이하입니다.")
    private String nickname;

    @Range(min = 0, max = 150, message = "나이는 0세 이상 150세 이하입니다.")
    private int age;
}
