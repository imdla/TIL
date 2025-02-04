package com.example.relationprac.domain.user;

import com.example.relationprac.domain.orders.Orders;
import com.example.relationprac.domain.team.Team;
import com.example.relationprac.domain.user.dto.UserRequestDto;
import com.example.relationprac.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "orders")
    private Orders orders;

    @Builder
    public User(String username, String email, String nickname, int age, Team team) {
        this.username = username;
        this.email = email;
        this.nickname = nickname;
        this.age = age;
    }

    public User update(UserRequestDto requestDto, Team team) {
        this.username = requestDto.getUsername();
        this.email = requestDto.getEmail();
        this.nickname = requestDto.getNickname();
        this.age = requestDto.getAge();
        setTeam(team);

        return this;
    }

    public void setTeam(Team team) {
        this.team = team;
        team.getUsers().add(this);
    }
}
