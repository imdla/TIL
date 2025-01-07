package com.example.demo.usersite.dto;

import com.example.demo.usersite.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserResoponseDto {
    private final int id;
    private final String username;
    private final String email;
    private final String nickname;
    private int age;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public static UserResoponseDto from(User user) {
        return UserResoponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .age(user.getAge())
                .createAt(user.getCreatedAt())
                .updateAt(user.getUpdatedAt())
                .build();
    }
}
