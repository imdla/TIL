## <mark color="#fbc956">Swagger</mark>

### 1. Swagger

- REST API를 문서화하고 테스트할 수 있게 해주는 오픈소스 도구
- 어노테이션 기반으로 문서화, 실제 코드와 문서가 동기화됨
- API 엔드포인트를 웹 UI에서 직접 테스트 가능

### 2. 실습

- 의존성 추가

  - `build.gradle`

  ```java
  dependencies {
    ...
  	implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.3'
  }
  ```

- `application.properties`

  ```java
  springdoc.swagger-ui.path=/swagger-ui.html
  springdoc.api-docs.path=/api-docs
  ```

- `global/config/SwaggerConfig`

  ```java
  import io.swagger.v3.oas.models.OpenAPI;
  import io.swagger.v3.oas.models.info.Info;

  @Configuration
  public class SwaggerConfig {

      @Bean
      public OpenAPI openAPI() {
          Info info = new Info()
                  .title("프로젝트명 API Document")
                  .version("v1.0.0")
                  .description("API 명세서");

          return new OpenAPI()
                  .info(info);
      }
  }
  ```

- `SecurityFilterChain`
  ```java
  .authorizeHttpRequests(auth -> auth
      .requestMatchers("/swagger-ui/**", "swagger-ui.html", "/api-docs/**").permitAll()
  		...
  )
  ```

---

## <mark color="#fbc956">주요 어노테이션</mark>

### 1. API 컨트롤러 레벨 어노테이션

- `@Tag`
  - API 그룹 설정
  - `name` : API 그룹의 이름
  - `description` : API 그룹에 대한 설명
  ```java
  @Tag(name = "회원 관리", description = "회원 관리 관련 API")
  @RestController
  public class MemberController {}
  ```

### 2. API 메서드 레벨 어노테이션

- `@Operation`

  - 각 API에 대한 설명 추가
  - 속성
    - summary : API에 대한 간단한 설명
    - description : API에 대한 상세한 설명

  ```java
  @Operation(
      summary = "회원 가입",
      description = "새로운 회원을 등록합니다.",
  )
  ```

- `@ApiResponses`
  - API 응답에 대한 설명 추가
  - 배열 형태로 정의 가능
  - `responseCode` : HTTP 상태 코드
  - `description` : 응답에 대한 설명
  - `content` : 응답 데이터의 형식
    - `@Content` : API 응답의 본문(body) 내용을 정의
    - `@Schema` : 응답 데이터의 구조를 특정 클래스로 지정
  ```java
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "조회 성공",
          content = @Content(schema = @Schema(implementation = MemberResponse.class))
      ),
      @ApiResponse(
          responseCode = "404",
          description = "회원을 찾을 수 없음"
      )
  })
  ```

### 3. DTO 클래스 레벨 어노테이션

- `@Schema`

  - DTO 클래스의 필드에 대한 설명 추가
  - `description` : 필드에 대한 설명
  - `example` : 예시 값
  - `required` : 필수 여부

  ```java
  public class MemberRequest {
      @Schema(
          description = "이메일 주소",
          example = "user@example.com",
          required = true
      )
      private String email;

      @Schema(
          description = "비밀번호",
          example = "password123",
          minLength = 8,
          maxLength = 20
      )
      private String password;
  }
  ```

### 4. Parameter 어노테이션

- `@Parameter`
  - API 요청 파라미터에 대한 설명 추가
  - `description` : 파라미터 설명
  - `required` : 필수 여부
  - `example` : 예시 값
  ```java
  @GetMapping("/search")
  public List<MemberResponse> searchMembers(
      @Parameter(
          description = "검색할 회원 이름",
          required = true,
          example = "홍길동"
      )
      @RequestParam String name
  ) {}
  ```

### 5. 실습

- `PostController`

  ```java
  @Tag(name = "게시글 관리", description = "게시글 관리 API")
  @RestController
  @RequestMapping("/posts")
  @RequiredArgsConstructor
  public class PostController {
  ```

- `createPost`

  ```java
  @Operation(
  	summary = "게시글 작성",
  	description = """
  		제목, 내용을 입력받아 게시글을 작성한다.
  	"""
  )
  @PostMapping
  public ResponseEntity<ApiResponse<PostResponseDto>> createPost
  ```

- `PostCreateRequestDto`

  ```java
  @NotBlank()
  @Length(max = 20)
  @Schema(description = "제목", example = "게시글 제목")
  private String title;

  @NotBlank
  @Length(min = 5)
  @Schema(description = "내용", example = "게시글 내용")
  private String content;
  ```

---

## <mark color="#fbc956">Spring Rest Docs</mark>

### 1. Spring Rest Docs

- 테스트 코드를 기반으로 API 문서를 자동으로 생성하는 도구
- API 스펙 정보가 코드에 침투하지 않음
  (Swagger와 달리 어노테이션이 필요없음)

### 2. Spring Rest Docs 와 Swagger

- **Spring Rest Docs**
  - 장점 : 테스트 코드 기반으로 신뢰성 높은 문서 생성
  - 단점 : 테스트 코드 작성이 필수적이며, 설정 복잡함
- **Swagger**
  - 장점 : 설정 간단, API를 직접 테스트 가능
  - 단점 : 운영 코드에 문서화를 위한 어노테이션이 포함됨

---

### ☀️ 오늘의 배움

- **CommentForm으로 댓글 생성 (submit 했을 때)**

  - 댓글 작성, detailPage에 영향
  - 댓글을 보여주자

  1. ☑️ **props** (부모 → 자식으로 data 전달)
  2. 자식 → 부모로 data 전달

- 필요한 것

  - postId 통해 post 가지고 와야함 → postId를 commentForm으로 전달
  - post 변경이 필요함 → setPost를 commentForm으로 전달해 변경

- **API 문서**

  - swagger 이용
  - spring rest docs

- **TTD**

- **로깅**

  - 시간, 로그 레벨, 로그 파일 등

- **테스트 코드**

  - 기존 파일의 위치랑 같은 곳에 있음

- **단위 테스트**

  - 레포지토리 통과한다면 → db에 데이터 있어야 함
  - 컨트롤러, 서비스, 레포지토리 따로 테스트하는 것
    - 서비스 검사할 때, 레포지토리는 사용하면 안됨
    - 데이터베이스에 들어갔을 것 같은 녀석(mok 객체)을 사용할 것임
  - given(테스트 준비 과정), when(테스트하고자 하는 실제 로직), then(테스트 결과 검증)

- **mok 객체**
  - 레포지토리를 대신하는 가짜 객체
  - 서비스는 레포지토리를 사용
    - db에 문제가 있을 경우, 서비스에 문제가 있는지 레포지토리에 문제가 있는지 모름
    - mok을 활용해 레포지토리를 대신할 것임
