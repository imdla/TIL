package com.example.relationprac.domain.user;

import com.example.relationprac.domain.user.dto.UserRequestDto;
import com.example.relationprac.domain.user.dto.UserResponseDto;
import com.example.relationprac.global.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    // CREATE
    public UserResponseDto addUser(UserRequestDto requestDto) {
        // username 중복 검사
        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new IllegalArgumentException();
        }

        // email 중복 검사
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException();
        }

        User user = userRepository.save(requestDto.toEntity());
        return UserResponseDto.from(user);
    }

    // READ ALL
    public List<UserResponseDto> findUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::from)
                .toList();
    }

    // READ ONE
    public UserResponseDto findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return UserResponseDto.from(user);
    }

    // UPDATE
    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        user.update(requestDto);
        return UserResponseDto.from(user);
    }

    // DELETE
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        userRepository.delete(user);
    }

    // 특정 닉네임을 가진 사용자 조회 & 특정 나이 사용자 조회
    public List<UserResponseDto> filterUser(String nickname, Integer age) {
        Specification<User> spec = Specification
                .where(UserSpecification.withNickname(nickname))
                .and(UserSpecification.withAge(age));
        List<User> users = userRepository.findAll(spec);

        return users.stream()
                .map(UserResponseDto::from)
                .toList();
    }

    // 활성화된 사용자 조회
    public List<UserResponseDto> findByIsActive() {
        return userRepository.findByIsActiveTrue().stream()
                .map(UserResponseDto::from)
                .toList();
    }

    // 이메일이 특정 도메인으로 끝나는 사용자 조회
    public List<UserResponseDto> findByContainEmail(String emailDomain) {
        return userRepository.findByEmailContaining(emailDomain).stream()
                .map(UserResponseDto::from)
                .toList();
    }

    // 특정 나이 이상, 비활성화인 사용자 조회
    public List<UserResponseDto> findByAgeGreaterThanInactive(int age) {
        return userRepository.findByAgeGreaterThanInactive(age).stream()
                .map(UserResponseDto::from)
                .toList();
    }
}
