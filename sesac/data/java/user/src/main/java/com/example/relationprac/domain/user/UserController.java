package com.example.relationprac.domain.user;

import com.example.relationprac.domain.user.dto.UserRequestDto;
import com.example.relationprac.domain.user.dto.UserResponseDto;
import com.example.relationprac.global.response.ApiResponse;
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

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDto>> addUser(@Valid  @RequestBody UserRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok(
                        userService.addUser(requestDto)
                ));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> findUsers() {
        return ResponseEntity.ok(ApiResponse.ok(
                userService.findUsers()
        ));
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(
                userService.findUserById(id)
        ));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok(ApiResponse.ok(
                userService.updateUser(id, requestDto)
        ));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        ResponseEntity.ok(ApiResponse.ok(
                "유저가 정상적으로 삭제되었습니다.",
                "DELETE",
                null
        ));
    }

    // 특정 닉네임을 가진 사용자 조회 & 특정 나이 사용자 조회
    @GetMapping("/user")
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> filterUser(
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) Integer age
    ){
        return ResponseEntity.ok(ApiResponse.ok(
                userService.filterUser(nickname, age)
        ));
    }

    // 활성화된 사용자 조회
    @GetMapping("/activeUser")
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> findByIsActive() {
        return ResponseEntity.ok(ApiResponse.ok(
                userService.findByIsActive()
        ));
    }

    // 이메일이 특정 도메인으로 끝나는 사용자 조회
    @GetMapping("/userEmail")
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> findByContainEmail(@RequestParam String emailDomain) {
        return ResponseEntity.ok(ApiResponse.ok(
                userService.findByContainEmail(emailDomain)
        ));
    }

    // 특정 나이 이상, 비활성화인 사용자 조회
    @GetMapping("/inactiveUser")
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> findByAgeGreaterThanInactive(@RequestParam int age) {
        return ResponseEntity.ok(ApiResponse.ok(
                userService.findByAgeGreaterThanInactive(age)
        ));
    }
}
