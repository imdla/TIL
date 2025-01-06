package com.example.demo.usersite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // username 조회
    boolean existsByUsername(String username);

    //    - 특정 닉네임을 가진 사용자 조회
    List<User> findByNickname(String nickname);

    //    - 특정 나이의 사용자 조회
    List<User> findByAge(int age);

    //    - 활성화된 사용자 조회
    List<User> findByIsActive(boolean isActive);

    //    - 이메일이 특정 도메인으로 끝나는 사용자 조회
    List<User> findByEmailContaining(String emailDomain);
}
