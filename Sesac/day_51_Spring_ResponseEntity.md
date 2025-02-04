## <mark color="#fbc956">ResponseEntity</mark>

### 1. ResponseEntity

- Spring Framework에서 HTTP 응답을 상세하게 제어할 수 있게 해주는 클래스
- HTTP Status Code, Headers, Body 등을 직접 제어해 클라이언트에게 더 명확한 응답 전달

- **ReponseEntity의 구조**
  - HTTP Status Code : 요청 처리 상태를 나타내는 코드
  - HTTP Headers : 응답에 대한 추가 정보 담는 헤더
  - Response Body : 실제 데이터 담는 본문

### 2. HTTP Status Code

- 요청 성공 여부 / 추가 조치 필요여부 / 오류 발생 여부 등을 나타내는 표준화된 코드

> **HTTP Status Code**
>
> - **Informational responses (100 - 199)**
> - **Susccessful responses (200 - 299)**
>   - 정상 동작
>   - 200 (OK) : 요청 성공
>   - 201 (Created) : 리소스 생성 성공
>   - 204 (No Content) : 요청은 성공했지만 응답 데이터 없음
> - **Redirection message (300 - 399)**
> - **Client error responses (400 - 499)**
>   - 사용자 잘못
>   - 400 (Bad Request) : 잘못된 요청
>   - 401 (Unauthorized) : 인증 필요
>   - 403 (Forbidden) : 권한이 없는 요청
>   - 404 (Not Found) : 리소스를 찾을 수 없음
>   - 405 (Method Not Allowed) : 허용되지 않은 메서드
> - **Server error responses (500 - 599)**
>   - 개발자 잘못

### 3. HTTP Headers

- 클라이언트와 서버가 요청 또는 응답으로 부가적인 정보 전송할 수 있도록 해줌
- 요청/응답에 대한 메타 데이터를 포함, 이는 본문의 내용이나 동작 방식 등을 제어하는데 사용
- 주로 활용되는 headers
  - `Content-Type` : 요청이나 응답의 데이터 형식 (JSON, XML 등)
  - `Authorization` : 인증 토큰이나 자격 증명
  - `Cache-Control` : 캐시 관리
  - `Accept` : 서버가 반환할 콘텐츠 유형

### 4. ResponseEntity 사용

- 형태 : `ResponseEntity <T>`

- **기본응답**

  ```java
  ResponseEntity.ok(data);
  ```

  - `ok()` : 200 상태 코드
  - `created(URI location)` : 201 상태 코드, Location 헤더 포함
  - `noContent()` : 204 상태 코드
  - `badRequest()` : 400 상태 코드
  - `notFound()` : 404 상태 코드
  - body가 없는 경우 `build()` 사용
    - `ResponseEntity.noContent().build();`

- **상태 코드 담은 응답**

  ```java
  ResponseEntity.status(HttpStatus.status).body(data);
  ```

- **상태 코드와 헤더를 담은 응답**
  ```java
  ReponseEntity
  	.status(HttpStatus.OK)
  	.header("Custom-Header", "value")
  	.body(data);
  ```

---

## <mark color="#fbc956">API 응답 구조화</mark>

### 1. API 응답 구조화

- 현재는 응답의 body에 데이터만 전달됨
  → 클라이언트 위해 추가적인 data 전달 가능
- 서버에서 클라이언트로 전달하는 데이터의 형식을 일관되게 설계하는 것

- **장점**

  1. 프론트엔드 개발 효율성
     - 모든 API가 동일한 구조로 응답하므로 공통 처리 가능
     - 성공/실패 여부 명확히 판단 가능
  2. 에러 처리 용이성
     - 상세한 에러 메시지와 코드 전달 가능
     - 프론트엔드에서 에러 상황별 대응 쉬움
  3. 확장성과 유지보수성
     - API 문서화 용이
     - 버전 관리 쉬음

- **일반적인 응답 구조**
  - message : 응답에 대한 설명 메시지
  - code : 비즈니스 처리 결과 코드
  - data : 실제 응답 데이터

### 2. 실습

- `ApiResponse`

  ```java
  @Getter
  public class ApiResponse<T> {
  	private final String message;
  	private final String code;
  	private final T data;

  	private ApiResponse(T data) {
  		this.message = "Success";
  		this.code = "SUCCESS";
  		this.data = data;
  	}

  	private ApiResponse(String message, String code, T data) {
  		this.message = message;
  		this.code = code;
  		this.data = data;
  	}

  	public static <T> ApiResponse<T> ok(T data) {
  		return new ApiResponse<>(data);
  	}

  	public static <T> ApiResponse<T> ok(String message, String code, T data) {
  		return new ApiResponse<>(message, code, data);
  	}
  }
  ```

- `PostController`

  ```java
  @RestController
  @RequestMapping("/jpa/v4/posts")
  @RequiredArgsConstructor
  public class PostControllerV4 {
      private final PostServiceV4 postServiceV4;

      // Post method / url / data
      @PostMapping
      public ResponseEntity<ApiResponse<PostResponseDto>> createPost(@RequestBody PostCreateRequestDto requestDto) {
  //        PostResponseDto data = postServiceV4.createPost(requestDto);
  //        ApiResponse<PostResponseDto> response = ApiResponse.ok(data);
  //        return ResponseEntity.status(HttpStatus.CREATED).body(response);

          return ResponseEntity
                  .status(HttpStatus.CREATED)
                  .body(
                          ApiResponse.ok(
                                  "게시글이 정상적으로 작성되었습니다.",
                                  "CREATED",
                                  postServiceV4.createPost(requestDto)
                          )
                  );
      }

      // Get method / url / 전체
      @GetMapping
      public ResponseEntity<ApiResponse<List<PostListResponseDto>>> readPosts() {
  //        List<PostListResponseDto> data = postServiceV4.readPosts();
  //        ApiResponse<List<PostListResponseDto>> response = ApiResponse.ok(data);
  //        return ResponseEntity.ok(response);

          return ResponseEntity.ok(ApiResponse.ok(postServiceV4.readPosts()));
      }

      // Get method / url / 단일
      @GetMapping("/{id}")
      public ResponseEntity<ApiResponse<PostResponseDto>> readPostById(@PathVariable Long id) {
          return ResponseEntity.ok(ApiResponse.ok(postServiceV4.readPostById(id)));
      }

      // Update
      @PutMapping("/{id}")
      public ResponseEntity<ApiResponse<PostResponseDto>> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
          return ResponseEntity.ok((ApiResponse.ok(postServiceV4.updatePost(id, requestDto))));
      }

      // Delete
  //    @DeleteMapping("/{id}")
  //    @ResponseStatus(HttpStatus.NO_CONTENT)
  //    public ResponseEntity<Object> deletePost(@PathVariable Long id) {
  //        postServiceV4.deletePost(id);
  //        return ResponseEntity.noContent().build();
  //    }

      @DeleteMapping("/{id}")
  //    @ResponseStatus(HttpStatus.NO_CONTENT)
  //    사용자에게 보이기 때문에 소중한 정보를 담으면 안됨
      public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable Long id) {
          postServiceV4.deletePost(id);
          return ResponseEntity.ok(
                  ApiResponse.ok(
                          "게시글이 정상적으로 삭제되었습니다.",
                          "DELETED",
                          null
                  )
          );
      }
  }

  ```
