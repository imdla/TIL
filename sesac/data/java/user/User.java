package com.example.demo.usersite;

import com.example.demo.usersite.dto.UserUpdateRequestDto;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, updatable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickname;

    private int age;
    private boolean isActive = true;

    @Builder
    public User(String username, String email, String nickname, int age) {
        this.username = username;
        this.email = email;
        this.nickname = nickname;
        this.age = age;
    }

    public User update(UserUpdateRequestDto requestDto) {
        this.email = requestDto.getEmail();
        this.nickname = requestDto.getNickname();
        this.age = requestDto.getAge();

        return this;
    }
}
