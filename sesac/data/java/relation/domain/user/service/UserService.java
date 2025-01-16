package com.example.relation.domain.user.service;

import com.example.relation.domain.user.UserRepository;
import com.example.relation.domain.user.dto.response.UserResponseDto;
import com.example.relation.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto getMyProfile(User user) {
        // JWT 파싱할 때 user 정보 들고왔기에 userRepository 생략 가능
        return UserResponseDto.from(user);
    }
}
