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
public class SignupResponseDto {
    private String username;
    private String email;
    private Role role;

    public static SignupResponseDto from(User entity){
        return SignupResponseDto.builder()
                .username(entity.getUsername())
                .email(entity.getEmail())
                .role(entity.getRole())
                .build();
    }
}

