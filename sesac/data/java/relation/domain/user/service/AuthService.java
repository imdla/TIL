package com.example.relation.domain.user.service;

import com.example.relation.domain.user.UserRepository;
import com.example.relation.domain.user.dto.request.LoginRequestDto;
import com.example.relation.domain.user.dto.request.SignupRequestDto;
import com.example.relation.domain.user.dto.response.SignupResponseDto;
import com.example.relation.domain.user.dto.response.TokenResponseDto;
import com.example.relation.domain.user.entity.User;
import com.example.relation.global.exception.DuplicationEntityException;
import com.example.relation.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.Authentication;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 사용자가 제공한 인증정보 검증, 해당 사용자가 유효한지 확인
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    // 회원가입
    @Transactional
    public SignupResponseDto signup(SignupRequestDto requestDto) {

        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new DuplicationEntityException("이미 사용 중인 아이디입니다.");
        }

        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new DuplicationEntityException("이미 사용 중인 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        User user = requestDto.toEntity(encodedPassword);

        return SignupResponseDto.from(userRepository.save(user));
    }

    // 로그인
    // 1. DTO를 받습니다.
    public TokenResponseDto login(LoginRequestDto requestDto){
        // 3. 객체로 만든 것을 manager에게 통과시켜 인증 정보가 들어있는 "authentication" 객체를 만듭니다.
        // 4. "authenticationManager"를 활용하기 위해 DI를 해줍니다 -> security config로 이동
        Authentication authentication = authenticationManager.authenticate(
                // Spring Security에서 사용되는 기본적인 인증 토큰
                // 아이디와 비밀번호 활용해 토큰 생성
                new UsernamePasswordAuthenticationToken(
                        // 2. DTO로부터 온 정보를 객체로 만듭니다.
                        requestDto.getUsername(),
                        requestDto.getPassword()
                )
        );

        // 5. jwt 프로바이더를 DI 해줘야 한다. -> jwtTokenProvider 만들어주자
        // security/jwt/jwtTokenProvider
        String jwt = jwtTokenProvider.createToken(authentication);

        return new TokenResponseDto(jwt);
    }
}
