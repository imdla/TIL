package com.example.relationprac.domain.user.dto;

import com.example.relationprac.domain.team.Team;
import com.example.relationprac.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserResponseDto {
    private final Long id;
    private final String username;
    private final String email;
    private final String nickname;
    private final int age;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private final String team;

    public static UserResponseDto from(User entity) {
        return UserResponseDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .nickname(entity.getNickname())
                .age(entity.getAge())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .team(entity.getTeam().getTeamName())
                .build();
    }
}
