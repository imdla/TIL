package com.example.relation.domain.user.controller;

import com.example.relation.domain.user.dto.response.UserResponseDto;
import com.example.relation.domain.user.entity.User;
import com.example.relation.domain.user.service.UserService;
import com.example.relation.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/my/profile")
    public ResponseEntity<ApiResponse<UserResponseDto>> getMyProfile() {

        // Request에서 온 정보라서(요청에서 가져온 data에 대한 처리) -> Controller에서 진행
        // SecurityContextHolder에 저장되어있는 authentication(인증 객체) 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(ApiResponse.ok(
                userService.getMyProfile(user)
        ));
    }

    @GetMapping("/my/profile2")
    public ResponseEntity<ApiResponse<UserResponseDto>> getMyProfile2(
            @AuthenticationPrincipal User user
    ){
        return ResponseEntity.ok(ApiResponse.ok(
                userService.getMyProfile(user)
        ));
    }
}
