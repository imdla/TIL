package com.example.relationprac.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    // username 중복 조회
    boolean existsByUsername(String name);

    // email 중복 조회
    boolean existsByEmail(String email);

    // 활성화된 사용자 조회
    List<User> findByIsActiveTrue();

    // 이메일이 특정 도메인으로 끝나는 사용자 조회
    List<User> findByEmailContaining(String emailDomain);

    // 특정 나이 이상, 비활성화인 사용자 조회
    @Query("SELECT u FROM User u WHERE u.age >= : age AND u.isActive = true")
    List<User> findByAgeGreaterThanInactive(@Param("age") int age);
}
