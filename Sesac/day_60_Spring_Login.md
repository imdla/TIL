## <mark color="#fbc956">로그인</mark>

> **로그인은 JWT에 대한 CREATE**

### 1. `AuthController`

- 🤔 LoninRequestDto 에는 무엇이 필요 ? (= 로그인할 때 무엇이 필요할까?)

  - username (아이디)
  - password (비밀번호)

- LoginRequestDto를 받아 Service에 넘겨줌
- 코드

  - `AuthController`
    ```java
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponseDto>> login(
    	@Valid @RequestBody LoginRequestDto requestDto
    ) {
    	return ResponseEntity
    					.status(HttpStatus.CREATED)
    					.body(ApiResponse.ok(
    						authService.login(requestDto)
    					));
    }
    ```
  - `LoginRequestDto`

    ```java
    @Getter
    @NoArgsConstructor
    public class LoginRequestDto {
    	@NotBlank(message = "아이디는 필수 입력입니다.")
    	private String username;

    	@NotBlank(message = "비밀번호는 필수 입력입니다.")
    	private String password;
    }
    ```

### 2. `AuthService`

- **🤔 로그인할 때 필요한 비즈니스 로직은 무엇일까 ?**
  - username과 password를 인증해 인증된 객체로 생성
  - 인증된 정보를 → JWT 으로 생성
- 코드

  - `AuthService`

    ```java
    private final AuthticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenResponseDto login(LoginRequestDto requestDto) {
     // **인증된 정보 들어있는 authentication 객체 생성**
    	Authentication authentication = authenticationManager.authenticate(
    		new UsernamePasswordAuthenticationToken(
    			requestDto.getUsername(),
    			requestDto.getPassword()
    		)
    	);

    	// **authentication을 통해 JWT Token 생성**
    	String jwt = jwtTokenProvider.createToken(authentication)l

    	// **JWT Token을 DTO로 RETURN**
    	return new TokenResponseDto(jwt);
    }
    ```

1. **인증된 정보 들어있는 authentication 객체 생성**
   - **AuthenticationManager**
     - 사용자가 제공한 인증 정보(아이디, 비밀번호 등)를 검증, 해당 사용자가 유효한지 확인
     - `UsernamePasswordAuthenticationToken`
       - Spring Security에서 사용되는 기본적인 인증 토큰
       - 아이디, 비밀번호를 활용해 토큰 생성
     - `.authenticate`
       - 토큰을 바탕으로 정보 검증
     - `authenticationManager` 활용 위해 `AuthenticationManager` 를 bean으로 등록 필요
       ⇒ 아이디와 비밀번호를 인증해 `authentication` 객체 반환

- 코드

  - `SecurityConfig`

    ```java
    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
     ) {
    	 **// 사용자 정보 로드해 비밀번호 비교 → 인증 처리**
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);

    		**// 인증 처리된 구현체 생성**
        return new ProviderManager(authProvider);
    }
    ```

    1. **`DaoAuthenticationProvider`**

       - 사용자 인증을 위한 클래스
       - 사용자 정보를 로드, 비밀번호 비교해 인증 처리

       - **`UserDetailsService`** (인증의 과정에서 필요한 service)
         - 사용자의 정보를 조회하는 인터페이스
       - `PasswordEncoder` → password를 인코딩할 수 있는 객체

    2. **`ProviderManager`** 통해 인증 처리된 구현체 생성
       - 인증을 수행하는 `AuthenticationManager` 의 구현체

  - `global/security/service/CustomUserDetailsService`

    - `UserDetailService` 를 implements 하여 생성

    ```java
    @Service
    @RequiredArgsConstructor
    public class CustomUserDetailsService implements UserDetailsService {
    	private final UserRepository userRepository;

    	@Override
    	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    		return userRepository.findByUsername(username)
    						.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    	}
    }
    ```

2. **authentication을 통해 JWT 생성**

   - **JwtTokenProvider**

     - `jwtTokenProvider.createToken(authentication)`

       - JWT 토큰 생성
       - `jwtTokenProvider` 활용 위해 `JwtTokenProvider` 를 bean으로 등록

     - secretKey 가져오기
     - 만료 기간 지정 (`tokenValidityInMilliseconds`)
     - **createToken**
       - 1번에서 생성한 구현체 authentication로 JWT 생성
       - 페이로드 채우기, 만든 시간, 만료 시간, 서명

- 코드

  - `global/security/jwt/JwtTokenProvider`

    ```java
    @Component
    public class JwtTokenProvider {

    		// application.properties에 있는 변수 가져오기
        @Value("${jwt.secret}")
        private String secretKey;

    		// 만료기간 지정
        private final long tokenValidityInMilliseconds = 1000L * 60 * 60; // 1시간

    		// secretKey를 base64로 encode
        @PostConstruct
        protected void init() {
            secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        }

    		// Token 만들기
        public String createToken(Authentication authentication) {
            // 인증된 정보의 authentication에서 username 가져와
            String username = authentication.getName();
            // claims(payload)에 username 넣어주기
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
    ```

    1. `jwt.secret` 설정

       - `application.properties`

       ```java
       jwt.secret=시크릿키
       ```

       - 시크릿키는 다음과 같은 명령어 통해 생성된 랜덤한 64바이트 문자열 사용
         - window: git bash
         ```bash
         echo $(openssl rand -base64 64)
         ```

    2. `Claims claims = Jwts.*claims*().setSubject(username);`

       - JWT 토큰에 포함될 페이로드 설정

    3. JWT 생성

       ```java
       return Jwts.builder()
              // 페이로드 설정
              .setClaims(claims)
              // 발급 시간 설정
              setIssuedAt(now)
              // 만료 기간 설정
              .setExpiration(validity)
              // 서명
              .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
              // JWT를 문자열 형식으로 반환
              .compact();
       ```

3. **JWT Token을 DTO로 RETURN**

- 코드
  - `TokenResponseDto`
    ```java
    @Getter
    @RequiredArgsConstructor
    public class TokenResponseDto {
    	private final String token;
    }
    ```

---

## <mark color="#fbc956">로그인 후 요청</mark>

> **`/auth` 로 시작하지 않는 모든 URL에 대해 JWT를 검증해 인증**

- **요청**
  - URL
  - Method
  - Data
  - Headers
    - Key : `Authorization`
    - Value : `Bearer {Token}`

### 1. filterChain

- **🤔 Headers로 들어온 JWT Token 검증 !**
  - JWT Token 검증해 username을 인증 객체로 만들어 security context에 넣기

### 1-1. `SecurityConfig`

- `UsernamePasswordAuthenticationFilter` 이전에 `jwtAuthenticationFilter` 확인하기
- 코드

  - `SecurityConfig`

    ```java
    @RequiredArgsConstuctor
    public class SecurityConfig {
    	private final JwtAuthenticationFilter jwtAuthenticationFilter

    	...

    	@Bean SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    		http
    			.csrf(csrf -> csrf.diable())
    			.sessionManagement(session -> session
    				.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    			.authorizeHttpRequests(auth -> auth
    				.requestMatchers("/auth/**").permitAll()
    				.anyRequest().authenticated())
    			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    		return http.build();
    	}
    }
    ```

  - `.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)`
    - `UsernamePasswordAuthenticationFilter` 이전에 `jwtAuthenticationFilter` 를 추가
    - 즉, JWT 기반 인증을 우선시함

### 1-2. `JwtAuthenticationFilter`

- 코드

  - `global/security/jwt/JwtAuthenticationFilter`

    ```java
    @Component
    @RequiredArgsConstructor
    public class JwtAuthenticationFilter extends OncePerRequestFilter {

        private final JwtTokenProvider jwtTokenProvider;
        private final UserDetailsService userDetailsService;

        @Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain filterChain) throws ServletException, IOException {
            **// 1. 요청으로부터 JWT 토큰 가져오기**
            String token = getTokenFromRequest(request);

    				**// 2. 토큰에 값이 있는지 확인 && 유효성 검사**
            if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {

    						**// 3. 토큰의 payload에서 username 가져오기**
                String username = jwtTokenProvider.getUsername(token);

                **// 4. userDetailsService에서 username 통해 데이터 가져오기**
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

    						**// 5. user data 바탕으로 authentication라는 인증 객체 생성**
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

    						**// 6. SecurityContext에 인증 객체 넣기**
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        }

    		**// Validation 검사**
        private String getTokenFromRequest(HttpServletRequest request) {

    		    **// 1. 요청 header로부터 Authorization key 값을 가지는 value 가져옴**
            String bearerToken = request.getHeader("Authorization");

            **// 2. value가 비어있지 않고, Bearer로 시작할 경우 뒤에 있는 token return**
            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
                return bearerToken.substring(7);
            }
            return null;
        }
    }
    ```

1. **요청에서 JWT 토큰 가져오기 - `getTokenFromRequest()`**

   - **`getTokenFromRequest()`**
     - `Authorization` 헤더에서 JWT(Token)를 추출하기 위한 메서드
   - 요청의 headers에서 "Authorization"이라는 key값 가지는 value 가져오기
   - value가 비어있지 않고 & Bearer로 시작하면 뒤에 있는 token을 return

2. **토큰에 값이 있는지 & 유효성 검사 -** `StringUtils.*hasText*(token)`,`JwtTokenProvider.validateToken()`

   - `StringUtils.*hasText*()`
     - null, 공백 문자열을 체크
   - 토큰에서 payload에서 username 가져오기 - `JwtTokenProvider.getUsername()`

     - 코드

       - `JwtTokenProvider`

         ```java
         public boolean validateToken(String token) {
         	try {
         		Jwts.parserBuilder()
         					.setSigningkey(Keys.hmacShaKeyFor(secretKey.getBytes()))
         					.build()
         					.parseClaimsJws(token);
         		return true;
         	} catch (JwtException | IllegalArgumentException e) {
         		return false;
         	}
         }

         public String getUsername(String token) {
         	return Jwts.parserBuilder()
         								.setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
         								.build()
         								.parseClaimsJws(token)
         								.getBody()
         								.getSubject();
         }
         ```

         - `validateToken()`

           - 토큰이 위조되었는지 확인
           - `setSigningKey` : `secretKey` 입력
           - `parseClaimsJws`
             - 토큰의 서명 검증, 토큰의 본문(Claims)을 디코딩
             - 만약, 토큰이 변조되었거나 만료되었다면 예외 던짐

         - `getUsername()`
           - `.getBody().getSubject()`
             - 본문을 가져와 subject 값을 반환
             - 로그인 과정에서 `Jwts.claims().setSubject(username)` 를 통해 할당했던 username 가져옴

   - userDetailsService에서 username 통한 데이터 가져오기 - `userDetailsService.loadUserByUsername(username)`
   - user data 바탕으로 `authentication` 인증 객체 만들기
     ```java
     Authentication authentication =
     	new UsernamePasswordAuthenticationToken(
     		userDetails,
     		null,
     		userDetails.getAuthorities()
     	);
     ```
     - 이미 인증이 끝난 사용자 정보를 바탕으로 인증 객체 생성
   - SecurityContext에 인증 객체 넣기
     - `SecurityContextHolder.*getContext*().setAuthentication(authentication);`
       - 인증된 사용자의 인증 정보를 `SecurityContext` 에 저장
       - `SecurityContext` : 현재 인증된 사용자의 정보를 저장 및 관리하는 객체

3. **인증 처리 끝난 후 요청을 다음 필터로 전달**
   - `filterChain.doFilter(request, response);`
     - 인증 처리가 끝난 후, 요청을 다음 필터로 전달

---

## <mark color="#fbc956">인증/권한에 따른 적절한 응답</mark>

> **현재 인증이 되지 않은 상황에서 요청 보낼 경우 빈 응답이 옴**
>
> ApiResponse에 맞추어 응답 작성하기

- `SecurityConfig`

  ```java
  private final CustomAccessDeniedHandler accessDeniedHandler;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  	http
  	.csrf(csrf -> csrf.disable())
  	.sessionManagement(session -> session
  		.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
  	.authorizeHttpRequests(auth -> auth
  		.requestMatchers("/auth/**").permitAll()
  		.anyRequest().authenticated()
  	).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
  	.exceptionHandling(exception -> exception
  		.accessDeniedHandler(accessDeniedHandler)
  		.authenticationEntryPoint(jwtAuthenticationEntryPoint)
  	);

  	return http.build();
  }
  ```

- `global/security/handler/CustomAccessDeniedHandler`

  - 권한이 없는 리소스에 접근할 때 발생하는 `accessDeniedException` 을 처리

  ```java
  @Component
  public class CustomAccessDeniedHandler implements AccessDeniedHandler {

      private final ObjectMapper objectMapper = new ObjectMapper();

      @Override
      public void handle(HttpServletRequest request,
                         HttpServletResponse response,
                         AccessDeniedException accessDeniedException) throws IOException {

          response.setContentType("application/json;charset=UTF-8");
          response.setStatus(HttpServletResponse.SC_FORBIDDEN);

          ApiResponse<Void> errorResponse = ApiResponse.error("접근 권한이 없습니다.", "FORBIDDEN");
          response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
      }
  }
  ```

- `global/security/handler/JwtAuthenticationEntryPoint`

  - 인증되지 않은 사용자가 보호된 리소스에 접근할 때 발생하는 `authException` 을 처리

  ```java
  @Component
  public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

      private final ObjectMapper objectMapper = new ObjectMapper();

      @Override
      public void commence(HttpServletRequest request,
                           HttpServletResponse response,
                           AuthenticationException authException) throws IOException {

          response.setContentType("application/json;charset=UTF-8");
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

          ApiResponse<Void> errorResponse = ApiResponse.error( "인증이 필요합니다.","UNAUTHORIZED");
          response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
      }
  }
  ```

---

### ☀️ 오늘의 배움

- 로그인 → JWT CREATE 하는 과정
- `authentication` : 인증 정보 가짐
- User는 `UserDetails` 상속 받아 User를 몰라도 관리 가능
- `UserDetailsService` : 인증의 과정에서 필요한 service

  - 역할 분리

- 요청이 tomcat을 통해서 옴

  - 서블렛으로 매핑되서 옴

- filterChain - jwt 검증해 username을 인증 객체로 만들어 scurity context에 넣기
  1. header
  2. url
  3. method
  4. data - token 들어감
- Controller
- Service
- Repository
