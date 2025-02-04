package com.example.relation.domain.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenResponseDto {
    private final String token;
}
