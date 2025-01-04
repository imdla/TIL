## <mark color="#fbc956">커스텀 예외</mark>

### 1. 커스텀 예외

- 일반적으로 커스텀 예외는 `RuntimeException` 상속 받아 만듦

  - 컴파일 시 예외 처리 강제하지 않음 (Unckeched Exception)
  - 스프링의 트랜잭션 롤백 메커니즘과 호환
  - 더 유연한 예외 처리 가능

- `ResourceNotFoundException`

  ```java
  public class ResourceNotFoundException extends RuntimeException {
  	public ResourceNotFoundException(String message) {
  		super(message);
  	}

  	public ResourceNotFoundException() {
  		super("리소스를 찾을 수 없습니다.");
  	}
  }
  ```

### 2. ApiResponse 수정

- error 처리하도록 수정

  ```java
  @Getter
  public class ApiResponse<T> {
  	private final String message;
  	private final String code;
  	private final T data;

  	public static <T> ApiResponse<T> error(String message, String code) {
  		return new AliResponse<> (message, code, null);
  	}
  }
  ```

---

## <mark color="#fbc956">@ExceptionHandler</mark>

### 1. @ExceptionHandler

- 특정 Controller 클래스 내에서 발생하는 예외를 처리하는 메서드를 지정하는 어노테이션
- **장점**
  - 예외 발생 시 자동으로 해당 메서드 실행됨
  - 예외 종류별로 다른 처리 방식 적용 가능
  - ResponseEntity 통한 응답 제어 가능

### 2. PostController 수정

- `PostController`

  ```java
  public class PostController {
  	private final PostService postService;

  	@ExceptionHandler(ResourceNotFoundException.class)
  	public ResponseEntity<ApiResourse<void>> handleResourceNotFound(ResourceNotFoundException ex) {
  		return ResoinseEntity
  							.status(HttpStatus.NOT_FOUND)
  							.body(ApiResponse.error("resource not found", "NOT_FOUND"));
  							// .body(ApiResponse.error(ex.getMesseage(), "NOT_FOUND"));
  	}
  	...
  }
  ```

---

## <mark color="#fbc956">@ControllerAdvice</mark>

### 1. @ControllerAdvice

- 전역적으로 예외 처리하는 클래스를 지정하는 어노테이션
- 모든 Controller에서 발생하는 예외를 한 곳에서 관리 가능
- `@RestConrollerAdvice` = `@ControllerAdvice` + `@ResponseBody`

### 2. 사용

- `GlobalExceptionHandler`

  ```java
  @RestControllerAdvice
  public class GlobalExceptionHandler {
  	@ExceptionHandler(ResourceNotFoundException.class) {
  		public ResponseEntity<ApiResponse<void>> handlerUserNotFound(ResourceNotFoundException ex) {
  			return ResponseEntity
  							.status(HttpStatus.NOT_FOUND)
  							.body(ApiResponse.error(ex.getMessage(), "NOT_FOUND"));
  		}

  		@ExceptionHandler(Exception.class)
  		public ResponseEntity<ApiResponse<void>> handleGeneralException(Exception ex) {
  			return ResponseEntity
  							.status(Https.INTERNAL_SERVER_ERROR)
  							.body(ApiResponse.error("서버 내부 오류가 발생했습니다.", "INTERNAL_SERVER_ERROR"));
  		}

  }
  ```

### 3. 존재하지 않는 URL 요청

- 현재 존재하지 않는 URL에 대해 요청 보내면 500에러 나옴
  → 404로 바꾸기
- `application.properties`

  ```java
  spring.mvc.throw-exception-if-no-handler-found=true
  spring.web.resources.add-mappings=false
  ```

- `GlobalExceptionHandler`
  ```java
  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<ApiResponse<void>> handlerNotFoundException(NoHandlerFoundException ex) {
  	return ResponseEntity
  					.status(HttpStatus.NOT_FOUND)
  					.body(ApiResource.error(
  							"요청한 리소스를 찾을 수 없습니다" + ex.getRequestURL(),
  							"NOT_FOUND"
  					));
  }
  ```

### 4. 허용되지 않은 메서드 요청

- Controller에 존재하는 URL에 대해 허용되지 않은 요청을 보내면 500에러 나옴

  → 405로 바꾸기

- `GlobalExceptionHandler`
  ```java
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ApiResponse<void>> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
  	return ResponseEntity
  					.status(HttpStatus.METHOD_NOT_ALLOWED)
  					.body(ApiResponse.error("method not allowed", "METHOD_NOT_ALLOWED"));
  }
  ```

---

### ☀️ 오늘의 배움

- Object로 전달해주면 **@RestController**가 Json으로 말아줌

  - API 요청 처리
    - url → @Controller와 매핑
    - method
    - data → 메서드의 input의 옴 (@RequestBody)
  - HTTP 요청 JSON/XML 형태 처리

- RESTful

  - url 통해 리소스 정의
  - method를 통해 행위 정의
  - json을 반환

- JSON → DTO → POST (Rspository .save) → DTO → JSON
- **응답**
  - JSON
  - Status Code
    - ResponseEntity : `.ok()` , `.status().body()`
- **응답 DTO** (위의 `status().body()` body 괄호 내 위치)

  - code
  - message
  - data

- **throw** 있을 때 필요한 것

  - try-catch
  - throw : 상위 전달
    - check
    - unchecked

- throw 처리

  - 직접 throw
  - 어디서인가 반드시 처리 해야함
    - 처리안하면 터짐
    - 스프링에서는 자동처리해줌

- **throw** → throw 속한 class에 있는 handler 작동

  ⇒ 응답 (custom api, api response)

- **Exception**

  1. **커스텀 에러** : `RuntimeException` 상속받아 생성
  2. **ApiResponse** 수정 : `error` 일 때 메서드 추가
  3. **@ExceptionHandler** : 클래스 내부에서만 예외 처리 역할
     - Controller에 메서드 추가
     - 예외에 대한 처리
     - 컨트롤러 일종
  4. @**ControllerAdvice** : 전역 예외 처리

     - 예외 처리하는 것을 한 곳에서 관리

     ```java
     @ExceptionHandler(ExceptionName.class)
     public ResponseEntity<ApiResponse<Void>> handleGeneralException(ExceptionName ex) {
       return ResponseEntity
         .status(HttpStatus.INTERNAL_SERVER_ERROR)
         .body(ApiResponse.error("서버 내부 오류가 발생했습니다.", "INTERNAL_SERVER_ERROR"));
     }
     ```

- **Validation**

  - Level 별로 검사 가능 (컨트롤러, 서비스, 레포지토리, DB 등)
  - 클라이언트측 검사
    - 사용자가 임의적으로 수정할 수 있음 (특히, HTML일 때)
    - 목적 : UX 관련
  - 서버측 검사
    - 데이터 자체에 대한 정확성

- 유효성 어노테이션 → 필드에 조건 설정

  - Entity
    - DTO - Request ← 여기에서 validation 체크 할 것 (entity로 만들기 전)
    - DTO - Response

- **실습**

  - Post 필드
    - title : `@NotBlank` , `@Length(min=num, max=num)` - max 지정
    - content : `@NotBlank` , `@Length(min=num, max=num)` - min 지정
    - author : `@Length(min=num, max=num)` - max 지정, `@Pattern(regexp="정규식")`

- **유효성 검사**

  - DTO
    - 사용자로부터 입력 받는 역할 → INPUT 할 때 유효성 검사
  - Entity
    - 영속성 담당 역할 → DB에 대한 validation check

- `ApiResponse` : 응답 템플릿

- **계층별 검증**
  - Controller
    - 입력 자체 검증, (사용자의 입력, 1차적 검증)
  - Service
    - DB랑 상호작용 검증, 자세한 검증이 필요할 때, 비즈니스 규칙

---

> Spring Data Jpa, DTO 이용한 Post의 Create 흐름

![JPA_DTO.png](/sesac/assets/day51.png)

- **Post Create**

  1. PostController
     - URL 통해 data 받아오기 - `@RequestBody`
     - data를 DTO 형태로 만들기 - `PostCreateRequestDto` 생성자
     - PostService에 DTO 전달
  2. PostService
     - DTO를 Post로 만들기 `.toEntity()`
     - PostRepository에 Post를 저장하기 - `.save()`
     - Post를 DTO 형태로 만들어 return - `PostResponseDto.from()` (static)

- **Post Get - 전체**

  1. PostController
     - PostService에 readPost 호출
  2. PostService
     - PostRepository에 Post들 찾아 → DTO로 만들어 return
       - PostRepository.findAll().stream().map(`PostListResponseDto::from`).toList()

- **Post Get - 단일**

  1. PostController
     - id 를 @PathVariable로 받아옴
     - PostService에 id를 전달
  2. PostService
     - PostRepository에서 id에 맞는 Post 찾음
       - PostRepository.findById(id)
       - findById는 `opdational` 반환 → 에러 처리 필요
       - .orElse(() → new IllegalArgumentException())
     - Post를 DTO로 만들어 반환

- **Post Update**

  1. PostController
     - data는 DTO 형태로, id 받아오기
       - 받아오는 data는 title과 content면 충분
     - PostService에 전달
  2. PostService
     - PostRepository에서 id에 맞는 Post 찾음
     - post에 받아온 DTO로 업데이트
     - post를 DTO로 만들어 return

- **PostDelete**
  1. PostController
     - id를 받아오기
     - PostService에 전달
  2. PostService
     - PostRepository에서 id에 맞는 Post 찾음
     - PostRepository에서 post 삭제
