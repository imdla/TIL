package com.example.demo.usersite.dto;

import com.example.demo.usersite.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserListResoponseDto {
    private final long id;
    private final String email;
    private final String nickname;
    private int age;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public static UserListResoponseDto from(User user) {
        return UserListResoponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .age(user.getAge())
                .createAt(user.getCreatedAt())
                .updateAt(user.getUpdatedAt())
                .build();
    }
}
