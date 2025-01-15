## <mark color="#fbc956">Spring Security</mark>

### 1. Spring Security

- Spring 기반 애플리케이션의 보안(인증과 권한 등)을 담당하는 스프링 하위 프레임워크
- **기능**
  - **세션 관리** : 로그인한 사용자의 세션을 안전하게 관리
  - **암호화** : 비밀번호 등 민감한 정보를 안전하게 암호화
  - **보안 헤더 관리** : HTTP 보안 헤더를 자동으로 추가
  - **URL 기반의 보안 설정** : 특정 URL에 대한 접근 제어 가능

### 2. 의존성 추가

- `build.gradle`
  ```java
  dependencies {
    ...
  	implementation 'org.springframework.boot:spring-boot-starter-security'
  	implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
  	runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
  	runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.5'
  }
  ```

---

## <mark color="#fbc956">회원가입</mark>

> 회원가입은 User Entity에 대한 Create 로직
>
> - `domain/user` 패키지 생성

### 1. userDetails

- Spring Security의 UserDetails는 사용자의 핵심 정보를 담고 있는 인터페이스
- 인증 과정에서 필요한 정보 제공
- **추상 메서드**
  - `getUsername()` : 사용자의 식별자(주로 ID나 이메일) 반환
  - `getPassword()` : 암호화된 비밀번호 반환
  - `getAuthorities()` : 사용자의 권한 목록 반환 (ROLE_USER, ROLE_ADMIN 등)
  - `isAccountNonExpired()` : 계정 만료 여부
  - `isAccountNonLocked()` : 계정 잠금 여부
  - `isCredentialsNonExpired()` : 비밀번호 만료 여부
  - `isEnabled()` : 계정 활성화 여부

### 2. User

> UserDetails를 implements 함
>
> - `isEnabled` 등의 로직등은 특정 기획사항이 없다면 `true` 로 지정

- `entity/User`

  - `@Table(name = "app_user")`
    - DB 테이블 이름을 변경
    - mysql table의 예약어와 겹치지 않게 하기위해 새로운 테이블 이름 활용

  ```java
  @Entity
  @Table(name = "app_user")
  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public class User implements UserDetails {
  	@Id
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
  	private Long id;

  	@Column(unique = true)
  	private String username;

  	private String password;

  	@Column(unique = true)
  	private String email;

  	@Enumerated(EnumType.STRING)
  	private Role role;

  	@Builder
  	public User (String username, String password, String nickname, String email, Role role) {
  		this.username = username;
  		this.password = password;
  		this.email = email;
  		this.role = role;
  	}

  	@Override
  	public Collection<? extends GrantedAuthority> getAuthorities() {
  		return List.of(new SimpleGrantedAuthority(role.name()));
  	}

  	@Override
  	public boolean isAccountNonExpired() {
  		return true;
  	}

  	@Override
  	public boolean isAccountNonLocked() {
  		return true;
  	}

  	@Override
  	public boolean isCredentialsNonExpired() {
  		return true;
  	}

  	@Override
  	public boolean isEnabled() {
  		return true;
  	}
  }
  ```

- `getAuthorities` 는 사용자의 권한(Authority)을 정의하는 메서드

  ```java
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
  	return List.of(new SimpleGrantedAuthority(role.name()));
  }
  ```

- `entity/Role`

  - Role을 통해 사용자의 권한 정의
  - `ROLE_` 접두어 사용

  ```java
  public enum Role {
  	ROLE_USER,
  	ROLE_ADMIN
  }
  ```

- `Controller`
  - `AuthController` : 인증 관련 기능
  - `UserController` : 사용자의 정보를 다루는 API 처리
    두 가지로 구분해 사용 예정
- `controller/AuthController`

  ```java
  @RestController
  @RequestMapping("/auth")
  @RequitedArgsConstructor
  public class AuthController {
  	private final AuthService authService;

  	@PostMapping("/signup")
  	public ResponseEntity<ApiResponse<SignupResponseDto>> signup(
  		@Valid @RequestBody SignupRequestDto requestDto
  	) {
  		return ResponseEntity
  						.status(HttpStatus.CREATED)
  						.body(ApiResponse.ok(
  							authService.signup(requestDto)
  						));
  	}
  }
  ```

- `dto/SignupRequestDto`

  ```java
  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public class SignupRequestDto {
  	@NotBlank(message = "아이디는 필수 입력입니다.")
  	private String username;

  	@NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
             message = "비밀번호는 8자 이상, 영문, 숫자, 특수문자를 포함해야 합니다.")
    private String password;

    @NotBlank(message = "이메일은 필수 입력입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    public User toEntity(String encodedPassword) {
  	  return User.builder()
  					  .username(this.getUsername())
  					  .password(encodedPassword)
  					  .email(this.getEmail())
  					  .role(Role.ROLE_USER)
  					  .build();
    }
  }
  ```

  - role은 기본값으로 `Role.ROLE_USER` 넣어줌
  - `regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$"`
    - 정규 표현식, 비밀번호의 제약사항 정의
    - `^` : 문자열 시작
    - `(?=.*[A-Za-z])` : 알파벳이 적어도 하나 이상 포함
    - `(?=.*\\d)` : 숫자가 적어도 하나 이상 포함
    - `(?=.*[@$!%*#?&])` : `@$!%*#?&` 에 해당하는 특수문자를 하나 이상 포함
    - `[A-Za-z\\d@$!%*#?&]{8,}` : 최소 8자리 이상의 알파벳, 숫자 또는 특수문자
    - `$` : 문자열 끝

- `AuthService`

  ```java
  @Service
  @RequiredArgsConstructor
  @Transactional(readOnly = true)
  public class AuthService {
  	private final UserRepository userRepository;
  	private final PasswordEncoder passwordEncoder;

  	@Transactional
  	public SingupResponseDto signup(SignupRequestDto requestDto) {
  		if (userRepository.existsByUsername(requestDto.getUsername())) {
  			throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
  		}

  		if (userRepository.existsByEmail(requestDto.getEmail())) {
  			throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
  		}

  		String encodedPassword = passwordEncoder.encode(requestDto.getPassword);

  		User user = requestDto.toEntity(encodedPassword);

  		return SignupResponseDto.from(userRepository.save(user));
  	}
  }
  ```

- `UserRepository`

  ```java
  public interface UserRepository extends JpaRepository<User, Long> {
  	Optional<User> findByUsername(String username);
  	boolean existsByUsername(String username);
  	boolean existsByEmail(String email);
  }
  ```

- `SignupResponseDto`

  ```java
  @Getter
  @Builder
  @NoArgsConstuctor
  @AllArgsConstuctor
  public class SignupResponseDto {
  	private String username;
  	private String email;
  	private Role role;

  	public static SignupResponseDto from(User entity) {
  		return SignupResponseDto.builder()
  						.username(entity.getUsername())
  						.email(entity.getEmail())
  						.role(entity.getRole())
  						.build();
  	}
  }
  ```

### 3. SecurityConfig

- `passwordEncoder`

  - 비밀번호를 해싱함
  - `passwordEncoder` 를 활용하기 위해 `PasswordEncoder` 를 Bean으로 등혹해주어야 함

- `global/config/SecurityConfig`
  ```java
  @Configuration
  @EnableWebSecurity
  public class SecurityConfig {
  	@Bean
  	public PasswordEncoder passwordEncoder() {
  		return new BCryptPasswordEncoder();
  	}
  }
  ```
  - `BCryptPasswordEncoder`
    - 일반적으로 사용되는 해싱 알고리즘
    - 비밀번호 해싱할 경우 `salt` 를 자동으로 추가함
  - `@EnableWebSecurity`
    - Spring Security 설정을 활성화하는 어노테이션
    - 기본적으로 모든 URL을 보호해 모든 요청에 대해 인증 및 권한 검사를 적용
    - 다음과 같은 설정을 통해 `auth` 로 시작하는 URL은 인증 및 권한 검사를 하지 않을 수 있음

### 4. SecurityFilterChain

- HTTP 요청에 대한 보안 처리 흐름 정의
- 요청이 들어올 때마다 어떤 보안 필터들이 적용될지, 각 필터가 요청을 어떻게 처리할지 정의

- `SecurityConfig`
  ```java
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  	http
  		.csrf(csrf -> csrf.disable())
  		.sessionManagement(session -> session
  				.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
  		.authorizeHttpRequests(auth -> auth
  				.requestMatchers("/auth/**").permitAll()
  				.anyRequest().authenticated()
  	);
  	return http.build();
  }
  ```
  - `HttpSrcurity`
    - HTTP 요청을 보안하는 다양한 옵션을 제공
  - `csrf(csrf -> csrf.disable())`
    - CSRF 공격을 방지하기 위한 기능을 비활성화함
    - **CSRF**
      - Cross-Site Request Forgery
      - 사이트 간 요청 위조
      - CSRF 공격을 쿠키를 통해 이루어짐, REST API는 헤더를 이용해 인증을 하므로 CSRF 공격 방지를 할 필요가 없음
        - REST API도 쿠키를 통해 인증할 수 있는데, 이때 추가적인 조치 필요
  - `.sessionManagement(session -> session
.sessionCreationPolicy(SessionCreationPolicy.STATELESS))` - 세션 관리에 대한 설정 - 상태 없는(stateless) 세션 관리 방식, 즉 세션을 생성하지 않음 - REST API에서 서버에서 상태를 저장하지 않고 JWT 활용
  - `authorizeHttpRequests(auth -> auth
.requestMatchers("/auth/**").permitAll()
.anyRequest().authenticated())` - URL 접근 권한 설정 - `/auth/`로 시작하는 모든 요청에 대해 인증 없이 접근할 수 있도록 허용 - 로그인, 회원가입 등의 인증없이 접근 가능한 경로를 설정 - 이외 다른 요청에 대해 인증 요구
