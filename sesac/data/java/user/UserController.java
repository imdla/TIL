package com.example.demo.usersite;

import com.example.demo.myjpasitev4.ApiResponse;
import com.example.demo.usersite.dto.UserCreateRequestDto;
import com.example.demo.usersite.dto.UserListResoponseDto;
import com.example.demo.usersite.dto.UserResoponseDto;
import com.example.demo.usersite.dto.UserUpdateRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
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
    public ResponseEntity<ApiResponse<UserResoponseDto>> addUser(@Valid @RequestBody UserCreateRequestDto requestDto) {
        UserResoponseDto data = userService.addUser(requestDto);
        ApiResponse<UserResoponseDto> response = ApiResponse.ok(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // READ - 전체
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserListResoponseDto>>> findUsers() {
        List<UserListResoponseDto> data = userService.findUsers();
        ApiResponse<List<UserListResoponseDto>> response = ApiResponse.ok(data);
        return ResponseEntity.ok(response);
    }

    // READ - 단일
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResoponseDto>> findUserById(@PathVariable Long id) {
        UserResoponseDto data = userService.findUserById(id);
        ApiResponse<UserResoponseDto> response = ApiResponse.ok(data);
        return ResponseEntity.ok(response);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResoponseDto>> updateUserById(@PathVariable Long id, @Valid @RequestBody UserUpdateRequestDto requestDto) {
        UserResoponseDto data = userService.updateUserById(id, requestDto);
        ApiResponse<UserResoponseDto> response = ApiResponse.ok(data);
        return ResponseEntity.ok(response);
    }

    // DELETE
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

    ///  version 1. 따로 작성
    // 특정 닉네임을 가진 사용자 조회
//    @GetMapping("/user")
//    public ResponseEntity<ApiResponse<List<UserListResoponseDto>>> findByNickname(@RequestParam String nickname) {
//        List<UserListResoponseDto> data = userService.findByNickname(nickname);
//        ApiResponse<List<UserListResoponseDto>> response = ApiResponse.ok(data);
//        return ResponseEntity.ok(response);
//    }
//
//    // 특정 나이의 사용자 조회
//    @GetMapping("/user")
//    public ResponseEntity<ApiResponse<List<UserListResoponseDto>>> findByAge(@RequestParam int age) {
//        List<UserListResoponseDto> data = userService.findByAge(age);
//        ApiResponse<List<UserListResoponseDto>> response = ApiResponse.ok(data);
//        return ResponseEntity.ok(response);
//    }

    ///  version 2. RequestParam과 if문 사용 -> 확장성에 좋지 않음
    // 특정 닉네임을 가진 사용자 조회 & 특정 나이의 사용자 조회
//    @GetMapping("/user")
//    public ResponseEntity<ApiResponse<List<UserListResoponseDto>>> findUser(
//            @RequestParam(required = false) String nickname,
//            @RequestParam(required = false) Integer age) {
//        List<UserListResoponseDto> data;
//
//        if (nickname == null) {
//            data = userService.findByAge(age);
//        } else {
//            data = userService.findByNickname(nickname);
//        }
//        ApiResponse<List<UserListResoponseDto>> response = ApiResponse.ok(data);
//        return ResponseEntity.ok(response);
//    }

    ///  version 3. Specifications(Cirteria API) 사용하기
    // 특정 닉네임을 가진 사용자 조회 & 특정 나이의 사용자 조회
    @GetMapping("/user")
    public List<UserListResoponseDto> findUser(
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) Integer age
    ) {
        return userService.findUser(nickname, age);
    }

    // 활성화된 사용자 조회
    @GetMapping("/activeUser")
    public ResponseEntity<ApiResponse<List<UserListResoponseDto>>> findByIsActiveTrue() {
        List<UserListResoponseDto> data = userService.findByIsActiveTrue();
        ApiResponse<List<UserListResoponseDto>> response = ApiResponse.ok(data);
        return ResponseEntity.ok(response);
    }

    // 이메일이 특정 도메인으로 끝나는 사용자 조회
    @GetMapping("/userEmail")
    public ResponseEntity<ApiResponse<List<UserListResoponseDto>>> findByEmailContaining(@RequestParam String emailDomain) {
        List<UserListResoponseDto> data = userService.findByEmailContaining(emailDomain);
        ApiResponse<List<UserListResoponseDto>> response = ApiResponse.ok(data);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/inactiveUser")
    public ResponseEntity<ApiResponse<List<UserListResoponseDto>>> findByAgeGreaterThanNotActive(@RequestParam int age) {
        List<UserListResoponseDto> data = userService.findByAgeGreaterThanNotActive(age);
        ApiResponse<List<UserListResoponseDto>> response = ApiResponse.ok(data);
        return ResponseEntity.ok(response);
    }
}
