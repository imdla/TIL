package com.example.relation.domain.user.dto.response;

import com.example.relation.domain.user.entity.Role;
import com.example.relation.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String username;
    private String email;
    private Role role;

    public static UserResponseDto from(User entity){
        return UserResponseDto.builder()
                .username(entity.getUsername())
                .email(entity.getEmail())
                .role(entity.getRole())
                .build();
    }
}
