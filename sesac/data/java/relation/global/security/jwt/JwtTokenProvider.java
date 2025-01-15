package com.example.relation.global.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // 1. application.properties에 있는 변수 가져오기
    @Value("${jwt.secret}")
    private String secretKey;

    // 2. 만료기간을 위한 기간 지정
    private final long tokenValidityInMilliseconds = 1000L * 60 * 60 * 24; // 24시간

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(Authentication authentication) {
        // 1. payload에 담을 username 가져오기
        String username = authentication.getName();
        // 2. claims(payload에 들어갈 정보) 에 username 넣어주기
        // subject : username 방식으로 데이터 집어넣음 (중요)
        Claims claims = Jwts.claims().setSubject(username);

        Date now = new Date();
        Date validity = new Date(now.getTime() + tokenValidityInMilliseconds);

        return Jwts.builder()
                // 페이로드 채우기
                .setClaims(claims)
                // 만든 시간
                .setIssuedAt(now)
                // 만료 시간
                .setExpiration(validity)
                // 서명
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }
}