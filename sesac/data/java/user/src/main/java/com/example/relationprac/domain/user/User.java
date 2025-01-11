package com.example.relationprac.domain.user;

import com.example.relationprac.domain.user.dto.UserRequestDto;
import com.example.relationprac.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "age")
    private int age;

    @Column(nullable = false)
    private boolean isActive = true;

    @Builder
    public User(String username, String email, String nickname, int age) {
        this.username = username;
        this.email = email;
        this.nickname = nickname;
        this.age = age;
    }

    public User update(UserRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.email = requestDto.getEmail();
        this.nickname = requestDto.getNickname();
        this.age = requestDto.getAge();

        return this;
    }
}
