package com.example.demo.usersite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    // username 조회
    boolean existsByUsername(String username);

    // email 조회
    boolean existsByEmail(String email);


    // 특정 닉네임을 가진 사용자 조회
    List<User> findByNickname(String nickname);

    // 특정 나이의 사용자 조회
    List<User> findByAge(int age);

    // 활성화된 사용자 조회
    List<User> findByIsActiveTrue();

    // 이메일이 특정 도메인으로 끝나는 사용자 조회
    List<User> findByEmailContaining(String emailDomain);
//    @Query("SELECT u FROM User u WHERE u.email LIKE '%:emailDomain%'")
//    List<User> findByEmailContaining(@Param("emailDomain") String emailDomain);

    // 특정 나이 이후의 비활성 사용자 조회
    @Query("SELECT u FROM User u WHERE u.age >= :age AND u.isActive = true")
    List<User> findByAgeGreaterThanNotActive(@Param("age") int age);
}
