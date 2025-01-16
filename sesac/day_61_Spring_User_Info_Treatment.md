## <mark color="#fbc956">사용자 정보 처리</mark>

### 1. 로그인한 유저 정보 확인

> **🤔 로그인한 유저의 정보를 확인할 때 필요한 정보는 무엇일까 ?**
>
> - 나 (로그인한 유저) → JWT 통해 가져옴
>   - `SecurityContextHolder` 에 `authenentication` 통해 data 가져옴

- `UserController`

  ```java
  @RestController
  @RequiredArgsConstructor
  public class UserController {
  	private final UserService userService;

  	@GetMapping("/my/profile")
  	public ResponseEntity<ApiResponse<UserResponseDto>> getMyProfile() {

  		// Request에서 온 정보라서(요청에서 가져온 data에 대한 처리) -> Controller에서 진행
  		// SecurityContextHolder에 저장되어있는 authentication(인증 객체) 가져오기
  		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
  		User user = (User) authentication.getPrincipal();

  		return ResponseEntity.ok(ApiResponse.ok(
  			userService.getMyProfile(user)
  		));
  	}
  }
  ```

  - 축약 코드
    - 인증된 사용자의 정보 가져올 때, 축약 가능
    ```java
    @GetMapping("/my/profile2")
    public ResponseEntity<ApiResponse<UserResponseDto>> getMyProfile2 (
    	@AuthenticationPrincipal User user
    ) {
    	return ResponseEntity.ok(ApiResponse.ok(
    		userService.getMyProfile(user)
    	));
    }
    ```
  - `SecurityContextHoler`

    - Spring Security에서 현재 인증 정보를 저장하는 컨테이너
    - `.getContext()`
      - 현재 `SecurityContext` 가져옴
      - `.getAuthentication()`
        - 현재 인증된 사용자의 정보를 담고 있는 `Authentication` 객체 반환

  - `authentication.getPrincipal()`
    - 인증된 사용자의 주체 정보 반환
    - `UserDetails` 또는 `UserDetails` 을 구현한 객체로 캐스팅 가능
    - 인증 되지 않은 경우, `anonymousUser` 라는 문자열 반환

- `UserService`

  ```java
  @Service
  @RequiredArgsConstructor
  @Transactional(readOnly = true)
  public class UserService {
  	public final UserRepository userRepository;

  	public UserResponseDto getMyProfile(User user) {
  		return UserResponseDto.from(user);
  	}
  }
  ```

- `UserResponseDto`

  ```java
  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public class UserResponseDto {
  	private String username;
  	private String email;
  	private Role role;

  	public static UserResponseDto from(User entity) {
  		return UserResponseDto.builder()
  						.username(entity.getUsername())
  						.email(entity.getEmail())
  						.role(entity.getRole())
  						.build();
  	}
  }
  ```

### 2. Post에 작성자 추가

> 🤔 **게시글 작성 시 필요한 정보는 무엇일까 ?**
>
> - title → @RequestBody
> - content → @RequestBody
> - author → JWT 활용

- `Post2`

  ```java
  @Entity
  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public class Post2 {
  	@Id
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
  	private Long id;

  	@Column(nullable = false, length = 20)
  	private String title;

  	@Column(nullable = false)
  	private String content;

  	@ManyToOne(fetch = FetchType.LAZY)
  	@JoinColumn(name = "author_id", nullable = false)
  	private User author;

  	@Builder
  	public Post2(String title, String content, User author) {
  		this.title = title;
  		this.content = content;
  		this.author = author;
  	}
  }
  ```

- `PostController`

  - title과 content → @RequestBody 통해 받아옴
  - author → JWT 통해 user 정보 받아옴 (dto에서 받는 대신, 인증 정보에서 가져옴)

  ```java
   @PostMapping("/post2")
   public ResponseEntity<ApiResponse<Post2ResponseDto>> createPost2(
          @Valid @RequestBody Post2WithAuthorCreateRequestDto requestDto,
          @AuthenticationPrincipal User user
  ) {
       return ResponseEntity
              .status(HttpStatus.CREATED)
              .body(ApiResponse.ok(
                    postService.createPost2(requestDto, user)
              ));
  }
  ```

- `Post2CreateWithAuthorRequestDto`

  - author를 client에게서가 아닌, 인증 정보로부터 받아오도록 로직 변경

  ```java
  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public class Post2CreateWithAuthorRequestDto {
  	@NotBlank
  	@Length(max = 20)
  	private String title;

  	@NotBlank
  	@Length(min = 5)
  	private String content;

  	public Post2 toEntity(User author) {
  		return Post2.builder()
  						.title(this.title)
  						.content(this.content)
  						.author(author)
  						.build();
  	}
  }
  ```

- `PostService`

  ```java
  @Transactional
  public Post2ResponseDto createPost2 (
  	Post2ResponseWithAuthorRequestDto requestDto, User user
  ) {
  	return Post2ResponseDto.from(
  		post2Repository.save(requestDto.toEntity(user))
  	);
  }
  ```

- `Post2ResponseDto`

  - author의 정보를 DTO를 활용해 매핑해줌

  ```java
  @Getter
  @Builder
  public class Post2ResponseDto {
  	private final Long id;
  	private final String title;
  	private final String content;
  	private UserResponseDto author;

  	public static Post2ResponseDto.from(Post2 entity) {
  		return Post2ResponseDto.builder()
  						.id(entity.getId())
  						.title(entity.getTitle())
  						.content(entity.getContent())
  						.author(UserResponseDto.from(entity.getAuthor()))
  						.build();
  	}
  }
  ```

### 3. 내가 작성한 Post 보기

> **🤔 내가 작성한 게시글을 볼 때 필요한 정보는 무엇일까 ?**
>
> - 나 → @AuthenticationPrinciple
> - posts → Pageable

- `UserController`

  ```java
  @GetMapping("/my/posts")
  public ResponseEntity<ApiResponse<Post2ListPageResponseDto>> getMyPosts (
  	@AuthenticationPrincipal User user,
  	Pageable pageable
  ){
  	return ResponseEntity.ok(ApiResponse.ok(
  		userService.getMyPosts(user, pageable)
  	));
  }
  ```

- `UserService`

  ```java
  public Post2ListPageResponseDto getMyPosts(User user, Pageable pageable) {
  	return Post2ListPageResponseDto.from(
  		post2Repository.findAllByAuthorId(user.getId(), pageable)
  	);
  }
  ```

- `Post2Repository`

  ```java
  Page<Post2> findAllByAuthorId(Long authorId, Pageable pageable);
  ```

  - `Post2` 와 `User` 는 `ManyToOne` 관계, Fetch join을 써도 문제 발생하지 않음

  ```java
  @Query("SELECT p FROM Post2 p " +
  				"LEFT JOIN FETCH p.author " +
  				"WHERE p.author.id = :authorId")
  Page<Post2> findAllByAuthorId(@Param("authorId") Long authorId, Pageable pageable);
  ```

- `Post2ListPageResponseDto`

  ```java
  @Getter
  @Builder
  public class Post2ListPageResponseDto {
  	private List<Post2ResponseDto> posts;

  	private long totalPages;
  	private boolean hasNext;
  	private boolean hasPrevious;

  	public static Post2ListPageResponseDto from(Page<Post2> posts) {
  		return Post2ListPageResponseDto.builder()
  						.totalPages(posts.getTotalPages())
  						.hasNext(posts.hasNext())
  						.hasPrevious(posts.hasPrevious())
  						.posts(
  							posts.getContent().stream().map(
  								Post2ResponseDto::from
  							).toList()
  						).build();
  	}
  }
  ```

### 4. API 공개 범위 설정

> **현재 기본적인 Post 조회에도 항상 인증 요구**
>
> - 기본 조회의 경우 인증이 필요없도록 변경하자 !

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
  			.requestMatchers(HttpMethod.GET, "/posts/**").permitAll()
  			.anyRequest().authenticated()
  		)
  		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
  		.exceptionHandling(exception -> exception
  			.accessDeniedHandler(accessDeniedHandler)
  			.authenticationEntryPoint(jwtAuthenticationEntryPoint)
  		);
  	return http.build();
  }
  ```

- `/global/security/SecurityPathConfig`

  - 허용하는 URL들을 한 곳에 모아 관리 가능

  ```java
  public class SecurityPathConfig {
  	public static final String[] PUBLIC_GET_URLS = {
  		"/posts/**",
  		"/tags/**"
  	};

  	public static final String[] PRIVATE_GET_URLS = {
  		"/my/**"
  	};
  }
  ```

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
    			.requestMatchers(HttpMethod.GET, SecurityPathConfig.PUBLIC_GET_URLS).permitAll()
    			.anyRequest().authenticated()
    		)
    		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
    		.exceptionHandling(exception -> exception
    			.accessDeniedHandler(accessDeniedHandler)
    			.authenticationEntryPoint(jwtAuthenticationEntryPoint)
    		);
    	return http.build();
    }
    ```

### 5. 기타 전체 공개 추천 URL

- `.requestMatchers("/error").permitAll()`

  - 에러 발생 시 redirect 되는 url
  - 배포할 때는 공개하지 않도록 설정
  - `GlobalExceptionHandler` 에서 `Exception` 에 대한 처리를 하면 필요 없음
    - 개발할 경우, 우선 전체 `Exception` 에 대한 처리 하지 않고 `/error` 열어두는 것 추천

- `.requestMatchers("/images/**").permitAll()`
  - 정적 리소스에 대한 공개
  - `images` 는 이미지 업로드 시 설정한 url을 지정

### SecurityContext

- `JwtAuthenticationFilter`

  - 기존
    ```java
    SecurityContextHolder.getContext().setAuthentication(authentication);
    ```
  - 변경

    ```java
    SecurityContext context = SecurityContextHolder.createEmptyContext();

    context.setAuthentication(authentication);

    SecurityContextHolder.setContext(context);
    ```

---

### ☀️ 오늘의 배움

- 함수형 프로그래밍 방식으로 메서드 체이닝(security filter chain) 사용

- **Login**

  1. 서버에 들어와서 UserTable에서 username 확인
  2. username 확인 됨 (너 맞아)
  3. JWT 만들어 반환

- **id,pw & jwt**

  - id,pw → 계속 입력 및 조회 과정 필요
  - jwt → 담아서 한번에 조회 가능

- OAuth : 소셜 로그인 등 할 때 사용

  - 보안 관련 처리의 독립적인 것

- SecurityContext

  - thread local로 이루어짐
    - 쓰레드마다 내부에서 가지고 있는 변수
    - 요청마다 다른 authentication 이 SecurityContext로 들어갈 수 있음

- `@AuthenticationPrincipal`

  - = `SecurityContextHolder.*getContext*().getAuthentication().getPrincipal()` 과 동일

- **Refresh token**
  - acc 만료 시 refresh 토큰
  - session id를 대조할 때와 비슷
