## <mark color="#fbc956">Validation</mark>

### 1. 유효성 검사 (Validation)

- 사용자가 입력한 데이터가 애플리케이션에서 요구하는 규칙과 형식에 맞는지 확인하는 과정
- 데이터의 정확성과 품질 보장 위해 사용

1. **데이터 무결성 보장**
   - 잘못된 데이터가 시스템에 유입되는 것 방지
   - 데이터베이스의 품질 유지
2. **보안 강화**
   - 악의적인 데이터 입력 방지
   - SQL 인젝션 등의 공격 예방
3. **사용자 경험 향상**
   - 즉각적인 피드백 제공
   - 잘못된 입력에 대한 명확한 안내
4. **서버 부하 감소**
   - 잘못된 데이터를 초기 단계에서 차단
   - 불필요한 데이터베이스 조작 방지

### 2. Validation 종류

- **클라이언트 측 검사**

  - JavaScript를 이용한 실시간 검사
  - HTML5의 기본 유효성 검사 속성 활용
  - 즉각적인 사용자 피드백 제공
  - 서버 요청 감소

- **서버 측 검사**
  - Spring의 Bean Validation 사용
  - 데이터베이스에 저장하기 전 최종 검사
  - 보안적으로 더 안전함
  - 모든 요청에 대해 반드시 수행되어야 함

### 3. Bean Validation

- Java에서 객체의 데이터를 검사하기 위한 표준화된 프레임워크
- 객체의 속성에 대한 검사 규칙을 어노테이션으로 정의
- 도메인 모델의 유효성 검사 로직을 분리해 관리
- 재사용 가능한 검사 로직 구현 가능

### 4. 사용

- 유효성 검사 적용할 클래스에 검사 어노테니션 추가해 제약 조건 설정

  ```java
  public class UserDto {
  	@NotNull(message = "이름이 빈 칸입니다.")
  	@Size(min = 2, max = 50, message = "이름은 2자 이상 50자 이하여야 합니다.")
  	private String name;
  }
  ```

- `@Valid` 어노테이션 사용해 DTO 검사를 트리거
  - 유효하지 않은 입력 값은 `MethodArgumentNotValidException` 을 throw
  ```java
  @PostMapping
  public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO) {
  	...
  }
  ```

---

## <mark color="#fbc956">자주 사용되는 어노테이션</mark>

- 공통적으로 message 속성 가짐
- 설명된 상황에서 유효성 검사를 실패함

### 1. Null 관련

- `@NotNull`
  - null값인 경우
- `@NotEmpty`
  - null값이거나 빈 문자열이거나 size가 0인 Collection인 경우
  - List와 같은 Collection에서 주로 사용
- `@NotBlank`
  - null값이거나 빈 문자열이거나 공백인 경우
  - String에서 사용

### 2. 문자열 관련

- `@Size(min = num, max = num)`
  - 문자열, 배열, 컬렉션의 크기가 지정된 범위를 벗어난 경우
- `@Length(min = num, max = num)`
  - 문자열의 길이가 지정된 범위를 벗어난 경우
  - `@Size` 와 유사하나 문자열에만 특화
- `@Pattern(regexp = "정규식")`
  - 문자열이 지정된 정규식 패턴과 일치하지 않는 경우

### 3. 숫자 관련

- `@Min(value=num)`
  - 숫자가 지정된 값 미만인 경우
- `@Max(value=num)`
  - 숫자가 지정된 값 초과인 경우
- `@Positive`
  - 숫자가 양수가 아닌 경우
- `@Range(min=num, max=num)`
  - 숫자가 지정된 범위를 벗어난 경우

### 4. 날짜 관련

- `@Past`
  - 과거 날짜가 아닌 경우
- `@PastOrPresent`
  - 현재 또는 과거 날짜가 아닌 경우
- `@Future`
  - 미래 날짜가 아닌 경우
- `@FutureOrPresent`
  - 현재 또는 미래 날짜가 아닌 경우

### 5. 기타

- `@Email`
  - 문자열이 이메일 형식과 일치하지 않는 경우
- `@URL`
  - 문자열이 URL 형식과 일치하지 않는 경우

---

## <mark color="#fbc956">실습</mark>

- `Post` Entity

  - title : 값 있어야 함, 최대 20글자
  - content : 값 있어야 함, 최소 5글자
  - author : null 허용, 2~6글자

- `PostCreateRequestDto`

  - 요청 받을 때 유효성 검사를 시행해 request dto에서 정의

  ```java
  public class PostCreateRequestDto {
  	@NotBlank(message = "제목은 필수 입력 값입니다.")
  	@Length(max = 20, message = "제목은 20자 이하여야 합니다.")
  	private String title;

  	@NotBlank(message = "내용은 필수 입력 값입니다.")
  	@Length(min = 5, message = "내용은 최소 5자 이상이어야 합니다.")
  	private String content;

  	@Length(min = 2, max = 10, message = "작성자 이름은 2자 이상 10자 이하여야 합니다.")
  	private String author;
  	...
  }
  ```

- `PostController`

  - RequestBody 앞에 `@Valid` 추가
  - `PostCreateRequestDto` 에 유효성 검사 관련 어노테이션 추가해 update 로직에는 작동하지 않음

  ```java
  @PostMapping
  public ResponseEntity<ApiResponse<PostResponseDto>> createPost(@Valid @RequestBody PostCreateRequestDto requestDto) {

     return ResponseEntity
         .status(HttpStatus.CREATED)
         .body(
             ApiResponse.ok(
                "게시글이 정상적으로 작성되었습니다.",
                "CREATED",
                postService.createPost(requestDto)
          )
     );
  }
  ```

- `GlobalExceptionHandler`
  - 유효성 검사에 실패했을 때 `MethodArgumentNotValidException` 대한 처리 해줌
  ```java
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<void>> handleValidationException(MethodArgumentNotValidException ex) {
  	return ResponseEntity
  					.status(HttpStatus.BAD_REQUEST)
  					.body(ApiResponse.error(
  						"입력값 검증에 실패하였습니다.",
  						"INVALID_INPUT"
  					));
  }
  ```
- 자세한 메시지 전달 가능

  - `GlobalExceptionHandler`

  ```java
  @ExceptionHandler(MethodArgumentNotValidException.class)
      public ResponseEntity<ApiResponse<Object>> handleValidationException(
              MethodArgumentNotValidException ex
      ) {
          // 모든 검증 오류 수집
          Map<String, String> errors = new HashMap<>();

          ex.getBindingResult()
                  .getFieldErrors()
                  .forEach(error ->
                          errors.put(
                                  error.getField(),
                                  error.getDefaultMessage()
                          )
                  );

          return ResponseEntity
                  .status(HttpStatus.BAD_REQUEST)
                  .body(ApiResponse.error(
                          "입력값 검증에 실패하였습니다.",
                          "INVALID_INPUT",
                          errors
                  ));
      }
  ```

  - `ApiResponse`
    - errors에 대한 Map 입력 받을 수 있도록 수정

  ```java
  @Getter
  public class ApiResponse<T> {
      private final String message;
      private final String code;
      private final T data;
      private final Map<String, String> errors;

      private ApiResponse(T data) {
          this.message = "Success";
          this.code = "SUCCESS";
          this.data = data;
          this.errors = null;
      }

      private ApiResponse(String message, String code, T data, Map<String, String> errors) {
          this.message = message;
          this.code = code;
          this.data = data;
          this.errors = errors;
      }

      ...

      public static <T> ApiResponse<T> error(String message, String code, Map<String, String> errors) {
          return new ApiResponse<>(message, code, null, errors);
      }
  }
  ```

---

## <mark color="#fbc956">계층별 검증</mark>

### 1. Controller 계층 검증

- **주요 역할**
  - HTTP 요청 데이터의 기본적인 형식과 제약조건 검증
  - 비즈니스 로직 수행 전에 잘못된 데이터 차단
  - 빠른 실패를 통한 서버 리소스 절약
- **검증 방법**
  - Bean Validation을 통한 DTO 클래스 검증
  - `@Valid` 또는 `@Validated` 사용

### 2. Service 계층의 검증

- **주요 역할**
  - 비즈니스 규칙에 따른 데이터 검증
  - 여러 데이터를 조합해 검증
  - DB 조회가 필요한 검증
- **검증 방법**
  - 직접 검증 로직 구현
  - Custom Exception 활용
- ex. 게시글 개수 제한, 회원가입 시 중복 이메일 확인 등
  ```java
  if (userRespository.existsByEmail(email)) {
  	throw new DuplicateEmailException("이미 사용 중인 이메일입니다.");
  }
  ```

---

## <mark color="#fbc956">@Validated</mark>

### 1. `@Validated`

- `@Valid` 는 Java가 제공하는 어노테이션
- `@Validated` 는 Spring 프레임워크가 제공
  - 그룹 기능 지원해 상황에 따라 다른 유효성 검사 적용 가능
  - 동일한 DTO 클래스 사용하더라도 생성, 수정 등 각 상황에 맞는 검증 가능

### 2. 사용

- `ValidationGroups` 생성

  ```java
  public final class ValidationGroups {
  	public interface CreatePost {}
  	public interface UpdatePost {}
  }
  ```

- 통합 DTO

  - 어노테이션에 groups를 명시

  ```java
  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public class PostRequestDto {
      @NotBlank(groups = {ValidationGroups.CreatePost.class, ValidationGroups.UpdatePost.class},
              message = "제목은 필수 입력 값입니다.")
      @Size(max = 20, groups = {ValidationGroups.CreatePost.class, ValidationGroups.UpdatePost.class},
              message = "제목은 20자 이하여야 합니다.")
      private String title;

      @NotBlank(groups = {ValidationGroups.CreatePost.class, ValidationGroups.UpdatePost.class},
              message = "내용은 필수 입력 값입니다.")
      @Size(min = 5, groups = {ValidationGroups.CreatePost.class, ValidationGroups.UpdatePost.class},
              message = "내용은 최소 5자 이상이어야 합니다.")
      private String content;

      @Length(min = 2, max = 10, groups = {ValidationGroups.CreatePost.class},
              message = "작성자 이름은 2자 이상 10자 이하여야 합니다.")
      private String author;

      public Post toEntity() {
          return Post.builder()
                  .title(this.title)
                  .content(this.content)
                  .author(this.author)
                  .build();
      }
  }
  ```

- `PostController`

  - `@Valid` 대신 `@Validated` 활용해 DTO에 명시한 그룹에 대해 유효성 검사 실행

  ```java
  @PostMapping
  public ResponseEntity<ApiResponse<PostResponseDto>> createPost(
  	@Validated(ValidationGroups.CreatePost.class) @RequestBody PostRequestDto requestDto
  ) { ... }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<PostRequestDto>> updatePost(
  	@PathVariable Long id,
  	@Validated(ValidationGroups.UpdatePost.class) @RequestBody PostRequestDto requestDto
  ) { ... }
  ```
