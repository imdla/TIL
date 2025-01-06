package com.example.demo.usersite;

import com.example.demo.myjpasitev4.ApiResponse;
import com.example.demo.usersite.dto.UserCreateRequestDto;
import com.example.demo.usersite.dto.UserListResoponseDto;
import com.example.demo.usersite.dto.UserResoponseDto;
import com.example.demo.usersite.dto.UserUpdateRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserResoponseDto>> addUser(@Valid @RequestBody UserCreateRequestDto requestDto) {
        UserResoponseDto data = userService.addUser(requestDto);
        ApiResponse<UserResoponseDto> response = ApiResponse.ok(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserListResoponseDto>>> findUsers() {
        List<UserListResoponseDto> data = userService.findUsers();
        ApiResponse<List<UserListResoponseDto>> response = ApiResponse.ok(data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResoponseDto>> findUserById(@PathVariable Long id) {
        UserResoponseDto data = userService.findUserById(id);
        ApiResponse<UserResoponseDto> response = ApiResponse.ok(data);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResoponseDto>> updateUserById(@PathVariable Long id, @Valid @RequestBody UserUpdateRequestDto requestDto) {
        UserResoponseDto data = userService.updateUserById(id, requestDto);
        ApiResponse<UserResoponseDto> response = ApiResponse.ok(data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(
                ApiResponse.ok(
                        "유저가 정상적으로 삭제되었습니다.",
                        "DELETE",
                        null
                )
        );
    }

    // 특정 닉네임을 가진 사용자 조회
    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<ApiResponse<List<UserListResoponseDto>>> findByNickname(@PathVariable String nickname) {
        List<UserListResoponseDto> data = userService.findByNickname(nickname);
        ApiResponse<List<UserListResoponseDto>> response = ApiResponse.ok(data);
        return ResponseEntity.ok(response);
    }

    // 특정 나이의 사용자 조회
    @GetMapping("/age/{age}")
    public ResponseEntity<ApiResponse<List<UserListResoponseDto>>> findByAge(@PathVariable int age) {
        List<UserListResoponseDto> data = userService.findByAge(age);
        ApiResponse<List<UserListResoponseDto>> response = ApiResponse.ok(data);
        return ResponseEntity.ok(response);
    }

    // 활성화된 사용자 조회
    @GetMapping("/active/{isActive}")
    public ResponseEntity<ApiResponse<List<UserListResoponseDto>>> findByIsActive(@PathVariable boolean isActive) {
        List<UserListResoponseDto> data = userService.findByIsActive(isActive);
        ApiResponse<List<UserListResoponseDto>> response = ApiResponse.ok(data);
        return ResponseEntity.ok(response);
    }

    // 이메일이 특정 도메인으로 끝나는 사용자 조회
    @GetMapping("/email/{emailDomain}")
    public ResponseEntity<ApiResponse<List<UserListResoponseDto>>> findByEmailContaining(@PathVariable String emailDomain) {
        List<UserListResoponseDto> data = userService.findByEmailContaining(emailDomain);
        ApiResponse<List<UserListResoponseDto>> response = ApiResponse.ok(data);
        return ResponseEntity.ok(response);
    }
}
