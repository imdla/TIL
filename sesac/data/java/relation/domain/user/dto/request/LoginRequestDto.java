package com.example.relation.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "아이디는 필수 입력입니다.")
    private String username;

    @NotBlank(message = "아이디는 필수 입력입니다.")
    private String password;
}
