package com.example.demo.usersite;

import com.example.demo.usersite.dto.UserCreateRequestDto;
import com.example.demo.usersite.dto.UserListResoponseDto;
import com.example.demo.usersite.dto.UserResoponseDto;
import com.example.demo.usersite.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResoponseDto addUser(UserCreateRequestDto requestDto) {
        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new IllegalArgumentException();
        }
        User user = userRepository.save(requestDto.toEntity());
        return UserResoponseDto.from(user);
    }

    public List<UserListResoponseDto> findUsers() {
        return userRepository.findAll().stream()
                .map(UserListResoponseDto::from)
                .toList();
    }

    public UserResoponseDto findUserById(Long id) {
        User user = userRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException());
        return UserResoponseDto.from(user);
    }

    @Transactional
    public UserResoponseDto updateUserById(Long id, UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException());
        user.update(requestDto);
        return UserResoponseDto.from(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException());

        userRepository.delete(user);
    }

    public List<UserListResoponseDto> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname).stream()
                .map(UserListResoponseDto::from)
                .toList();
    }

    public List<UserListResoponseDto> findByAge(int age) {
        return userRepository.findByAge(age).stream()
                .map(UserListResoponseDto::from)
                .toList();
    }

    public List<UserListResoponseDto> findByIsActive(boolean isActive) {
        return userRepository.findByIsActive(isActive).stream()
                .map(UserListResoponseDto::from)
                .toList();
    }

    public List<UserListResoponseDto> findByEmailContaining(String emailDomain) {
        return userRepository.findByEmailContaining(emailDomain).stream()
                .map(UserListResoponseDto::from)
                .toList();
    }
}
