package com.example.relation.global.config;

import com.example.relation.global.security.SecurityPathConfig;
import com.example.relation.global.security.handler.CustomAccessDeniedHandler;
import com.example.relation.global.security.handler.JwtAuthenticationEntryPoint;
import com.example.relation.global.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    // HTTP 요청에 대한 보안 처리 흐름 정의
    // HttpSecurity : HTTP 요청을 보안하는 다양한 옵션 제공
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS 설정 연결
                // csrf 방지 기능 비활성화
                .csrf(csrf -> csrf.disable())
                // 세션 관리 설정
                // 상태없는 세션 관리 방식, 세션을 생성하지 않음
                // REST API에서는 서버에서 상태를 저장하지 않고 JWT 활성화
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // URL 접근 권한 설정
                // "/auth/" 로 시작하는 모든 요청에 대해 인증없이 접근 허용
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/verify").authenticated()
                        .requestMatchers("/auth/**", "/error", "/images/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "swagger-ui.html", "/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, SecurityPathConfig.PUBLIC_GET_URLS).permitAll()
                        .anyRequest().authenticated()
                ).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(accessDeniedHandler)
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:5173");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // DB -> USER, PW -> Encoder
    @Bean
    public AuthenticationManager authenticationManager(
            // 1. UserDetailsService를 필요로 한다. DI -> service를 만들어줘야 한다.
            // -> global/security/service로 이동
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        // 사용자 인증을 위한 클래스
        // 사용자 정보 로드, 비밀번호 비교해 인증 처리
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        // 2. user detail -> DB에서 user를 가져올 수 있는 객체
        // passwordEncoder -> pw를 인코딩할 수 있는 객체
        // 를 활용해서 "authenticationManager"에 대한 구현체를 만들어준다.

        // 인증 수행하는 AuthenticationManager의 구현체
        return new ProviderManager(authProvider);
    }
}
