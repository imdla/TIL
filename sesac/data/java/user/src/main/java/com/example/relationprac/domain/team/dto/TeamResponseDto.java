package com.example.relationprac.domain.team.dto;

import com.example.relationprac.domain.team.Team;
import com.example.relationprac.domain.user.User;
import com.example.relationprac.domain.user.dto.UserResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TeamResponseDto {
    private final Long id;
    private final String teamName;
    private final List<UserResponseDto> users;

    public static TeamResponseDto from(Team entity) {
        return TeamResponseDto.builder()
                .id(entity.getId())
                .teamName(entity.getTeamName())
                .users(entity.getUsers().stream()
                        .map(UserResponseDto::from)
                        .toList())
                .build();
    }
}
