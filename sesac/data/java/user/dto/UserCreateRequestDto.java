package com.example.demo.usersite.dto;

import com.example.demo.usersite.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Getter
@NoArgsConstructor
public class UserCreateRequestDto {

    @NotBlank(message = "이름은 필수 입력입니다.")
    @Length(min = 3, max = 20, message = "이름은 최대 3자 이상 20자 이하입니다.")
    private String username;

    @NotBlank(message = "이메일은 필수 입력입니다.")
    @Email(message = "올바른 이메일 형식을 입력해주세요.")
    private String email;

    @NotBlank(message = "닉네임은 필수 입력입니다.")
    @Length(min = 2, max = 10, message = "닉네임은 최대 2자 이상 10자 이하입니다.")
    private String nickname;

    @Range(min = 0, max = 150, message = "나이는 0세 이상 150세 이하입니다.")
    private int age;

    public User toEntity() {
        return User.builder()
                .username(this.username)
                .email(this.email)
                .nickname(this.nickname)
                .age(this.age)
                .build();
    }
}
