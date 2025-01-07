package com.example.demo.usersite;

import com.example.demo.usersite.dto.UserCreateRequestDto;
import com.example.demo.usersite.dto.UserListResoponseDto;
import com.example.demo.usersite.dto.UserResoponseDto;
import com.example.demo.usersite.dto.UserUpdateRequestDto;
import com.example.demo.usersite.exception.ResourseNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // CREATE
    @Transactional
    public UserResoponseDto addUser(UserCreateRequestDto requestDto) {
        // username 중복 검사
        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new IllegalArgumentException();
//            throw new DuplicateUniqueFieldError();
        }
        // email 중복 검사
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException();
        }
        User user = userRepository.save(requestDto.toEntity());
        return UserResoponseDto.from(user);
    }

    // READ - 전체
    public List<UserListResoponseDto> findUsers() {
        return userRepository.findAll().stream()
                .map(UserListResoponseDto::from)
                .toList();
    }

    // READ - 단일
    public UserResoponseDto findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return UserResoponseDto.from(user);
    }

    // UPDATE
    @Transactional
    public UserResoponseDto updateUserById(Long id, UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        user.update(requestDto);
        return UserResoponseDto.from(user);
    }

    // DELETE
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        userRepository.delete(user);
    }

    ///  version 1 & version 2
//    특정 닉네임을 가진 사용자 조회
//    public List<UserListResoponseDto> findByNickname(String nickname) {
//        return userRepository.findByNickname(nickname).stream()
//                .map(UserListResoponseDto::from)
//                .toList();
//    }
///
//    특정 나이의 사용자 조회
//    public List<UserListResoponseDto> findByAge(int age) {
//        return userRepository.findByAge(age).stream()
//                .map(UserListResoponseDto::from)
//                .toList();
//    }

    ///  version 3. Specifications(Cirteria API) 사용하기
    // 특정 닉네임을 가진 사용자 조회 & 특정 나이의 사용자 조회
    public List<UserListResoponseDto> findUser(String nickname, Integer age) {
        Specification<User> spec = Specification
                .where(UserSpecification.withNickname(nickname))
                .and(UserSpecification.withAge(age));
        List<User> users = userRepository.findAll(spec);

        return users.stream()
                .map(UserListResoponseDto::from)
                .toList();
    }

    // 활성화된 사용자 조회
    public List<UserListResoponseDto> findByIsActiveTrue() {
        return userRepository.findByIsActiveTrue().stream()
                .map(UserListResoponseDto::from)
                .toList();
    }

    // 이메일이 특정 도메인으로 끝나는 사용자 조회
    public List<UserListResoponseDto> findByEmailContaining(String emailDomain) {
        return userRepository.findByEmailContaining(emailDomain).stream()
                .map(UserListResoponseDto::from)
                .toList();
    }

    // 특정 나이 이후의 비활성 사용자 조회
    public List<UserListResoponseDto> findByAgeGreaterThanNotActive(int age) {
        return userRepository.findByAgeGreaterThanNotActive(age).stream()
                .map(UserListResoponseDto::from)
                .toList();
    }
}
