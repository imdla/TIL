## <mark color="#fbc956">페이징</mark>

### 1. 페이징

- 데이터베이스에서 대량의 데이터 조회 시, 모든 데이터를 한 번에 가져올 경우 성능 문제 발생 가능
- 데이터를 일정한 크기로 나눠 필요 부분만 가져옴
- **사용** : `JpaRepository` 메서드에 `Pageable` 을 parameter로 넣음

### 2. `Pageable`

- Spring Data JPA에서 제공하는 페이징과 정렬위한 인터페이스
- 페이징에 필요한 정보 담음
  - page : 페이지 번호 (0부터 시작)
    - frontend 개발자와 협업 시 주의
  - size : 페이지 크기
  - sort : 정렬 방식
    - 필드명, 정렬 방식으로 사용
    - 존재하지 않는 필드 사용 시 `PropertyReferenceException` 을 throw 함

### 3. Pageable 사용법

- `import org.springframework.data.domain.Pageable;`
- Controller 메서드에 Pageable 타입 파라미터 선언

  - 자동으로 query parameter 바인딩 됨

  ```java
  @GetMapping("/articles")
  public Page<Article> getArticles(Pageable pageable) {
  	return articleService.getArticles(pageable);
  }
  ```

- 요청 예시

  ```java
  // 기본 요청
  GET /articles?page=0&size=10

  // 정렬 추가
  GET /articles?page=0&size=10&sort=createdAt,desc

  // 다중 정렬 (createdAt 내림차순, title 오름차순)
  GET /articles?page=0&size=10&sort=createdAt,desc&sort=title
  ```

### 4. Pageable 관련 설정

- `application.properties`

  - 다음과 같은 설정들 추가 가능

  ```java
  # 페이지 번호를 1부터 시작할지 여부
  spring.data.web.pageable.one-indexed-parameters=true

  # 한번에 가져올 수 있는 최대 데이터 개수
  spring.data.web.pageable.max-page-size=100
  ```

- 기본값
  - `@PageableDefault` : 특정 API에만 기본값을 적용
  ```java
  @GetMapping("/articles")
  public Page<Article> getArticles(
  	@PageableDefault(
  		size = 20,
  		sort = "createdAt",
  		direction = Sort.Direction.DESC
  	) Pageable pageable
  ) {
  	return articleService.getArticle(pageable);
  }
  ```
  - `application.properties` 에도 추가 가능
  ```java
  # 페이지당 기본 데이터 개수
  spring.data.web.pageable.default-page-size=20
  ```

### 5. Page

- 페이징된 데이터의 결과를 담는 인터페이스
- 실제 데이터 외에도 페이징 처리에 필요한 다양한 정보 제공
  - `getTotalPages()` : 전체 페이지 수
  - `getTotalElements()` : 전체 데이터 수
  - `getContent()` : 현재 페이지의 데이터
  - `hasNext()` : 다음 페이지 존재 여부
  - `hasPrevious()` : 이젠 페이지 존재 여부
  - `isFirst()` : 현재 페이지가 첫 페이지인지
  - `isLast()` : 현재 페이지가 마지막 페이지인지

---

## <mark color="#fbc956">실습</mark>

### 1. READ - 기본

> 기본 Post에 대해 paging

- `PostController`

  - `Pageable pageable` 받음

  ```java
  @GetMapping("/pages")
  public ResponseEntity<ApiResponse<List<PostListResponseDto>>> readPostsWithPage(Pageable pageable) {
  	return ResponseEntity.ok(ApiResponse.ok(
  		postService.readPostsWithPage(pageable)
  	));
  }
  ```

- `PostService`

  - JpaRepository에서 제공하는 메서드들은 `pageable` 을 메서드 오버로딩해 받을 수 있음

  ```java
  public List<PostListResponseDto> readPostsWithPage(Pageable pageable) {
  	return postRepository.findAll(pageable).getContent()
  					.stream().map(PostListResponseDto::from).toList();
  }
  ```

- 요청
  - `/posts/pages?page=0&size=2`
  - `/posts/pages?page=0&size=2&sort=title,desc`

### 2. READ - 추가 정보

> paging
>
> - 전체 페이지 수
> - 다음 페이지 존재 여부
>
> 위를 포함한 응답으로 변경

- `PostController`

  ```java
  @GetMapping("/pages/detail")
  public ResponseEntity<ApiResponse<PostListWithPageResponseDto>> readPostsWithPageDetail(Pageable pageable) {
  	return ResponseEntity.ok(ApiResponse.ok(
  		postService.readPostsWithPageDetail(pageable)
  	));
  }

  ```

- `PostService`

  ```java
  public PostListWithPageResponseDto readPostsWithPageDetail(Pageable pageable) {
  	return PostListWithPageResponseDto.from(postRepository.findAll(pageable));
  }
  ```

- `PostListWithPageResponseDto`

  - 기존 응답인 `List<PostListResponseDto>` 를 하나의 응답으로 받음
  - `totalPages` , `hasNext` , `hasPrevious` 와 같은 page 관련 추가 정보 담음
  - 기존 `PostListResponseDto` 의 경우 이미 있던 class 활용해도 좋고, 재활용 될 일이 없을 경우 `inner class` 로 활용 가능
    - inner class : class 내부에 정의된 class, 정의되어 있는 class 내부에서만 활용
      - 바로 안에 정의되어 있어 직관적으로 사용 가능

  ```java
  @Getter
  @Builder
  public class PostListWithPageResponseDto {
      private List<PostListResponseDto> posts;

      private long totalPages;
      private boolean hasNext;
      private boolean hasPrevious;

      public static PostListWithPageResponseDto from(Page<Post> posts){
          return PostListWithPageResponseDto.builder()
                  .posts(
                          posts.getContent().stream().map(
                                  PostListResponseDto::from
                          ).toList()
                  )
                  .totalPages(posts.getTotalPages())
                  .hasNext(posts.hasNext())
                  .hasPrevious(posts.hasPrevious())
                  .build();
      }

      @Getter
      @Builder
      static class PostListResponseDto {
          private Long id;
          private String title;
          private LocalDateTime createdAt;
          private LocalDateTime updatedAt;

          public static PostListResponseDto from(Post entity){
              return PostListResponseDto.builder()
                      .id(entity.getId())
                      .title(entity.getTitle())
                      .createdAt(entity.getCreatedAt())
                      .updatedAt(entity.getUpdatedAt())
                      .build();
          }

      }
  }
  ```

---

## <mark color="#fbc956">OneToMany의 paging</mark>

### 1. OneToMany의 paging

- `OneToMany` 관계에서 `paging` 과 `fetch join` 함께 사용 불가
  1. 데이터가 예상과 다르게 올 수 있음
     - 1번 게시글에 댓글이 2개, 2번 게시글에 댓글이 2개인 상황
       - `size = 2` 요청을 보낼 경우 아래 두 가지로 생각 가능
         - 1, 2번 게시글 가져옴
         - 1번 게시글의 댓글이 2개이기 때문에 2개의 record에 대한 정보인 게시글 1번만 가져옴
  2. 최근 버전은 이를 자동적으로 해결해주지만, memory 상에서 처리해 좋지 못함
  3. batch size를 조절하는 방식으로 `N+1` 문제 개선 가능
- `ManyToOne` 에서 사용 가능

### 2. 실습

- `PostController`
  ```java
  @GetMapping("/detail/pages")
  public ResponseEntity<ApiResponse<List<PostWithCommentResponseDtoV2>>> readPostsWithCommentPage(Pageable pageable) {
  	return ResponseEntity.ok(ApiResponse.ok(
  					postService.readPostsWithCommentPage(pageable)
  	));
  }
  ```
- `PostService`

  ```java
  public List<PostWithCommentResponseDtoV2> readPostWithCommentPage(Pageable pageable) {
  	return postRepository.findPostsWithCommentPage(pageable)
  					.getContent().stream().map(
  						PostWithCommentResponseDtoV2::from
  					).toList();
  }
  ```

- `PostRepository`
  ```java
  @Query("SELECT p FROM Post p " +
  				"LEFT JOIN FETCH p.comments")
  Page<Post> findPostsWithCommentPage(Pageable pageable);
  ```
  - 결과는 정상적으로 나오지만, 다음과 같은 메시지 확인 가능
    ```java
    HHH90003004: firstResult/maxResults specified with collection fetch; applying in memory
    ```

### 3. 추가 고려 사항

1. **페이지 크기(size) 제한**
   - `application.properties` 에서 설정한 최대값보다 큰 요청이 올 경우
     - 설정값으로 제한해 처리 or exception을 throw할 수 있음
2. **페이지 번호(page) 검증**
   - 잘못된 페이지 번호 요청 시 exception을 throw
     - 음수 값 (-1, -2, …)
     - 전체 페이지 수를 초과하는 값
     - 숫자가 아닌 값 (”abc”, “한글”, …)
3. **정렬(sort) 제한**
   - 보안상 정렬하면 안 되는 필드 제한 (password 등)
   - 성능상 정렬이 부담되는 필드 제한 (큰 용량의 content 등)
   - DB 인덱스가 없는 필드의 정렬 제한

---

### ☀️ 오늘의 배움

- **번들링**
  - React 사용 시 프론트엔드 부분에서 브라우저가 혼자 다 해서 번들링 활용
- Spring MVC
- **3계층 레이어드 아키텍처**
  - Controller : 요청을 받아 응답을 줌, validation check
    - url : 리소스 식별
    - method : 식별된 데이터로 행동할 것
    - data : path variable, request body, request param
  - Service : 비즈니스 로직, @Transactional,
  - Repository : JpaRepository 활용
- **DTO**

- Page

  - List + $\alpha$ 기능

- page와 page의 데이터 나오는 JSON 생각해보기

  ```json
  {
    "message": "Success",
    "code": "SUCCESS",
    "data": {
      "pages": {
        "current-page": 0,
        "last-page": 10
      },

      "result": [
        {
          "id": 1,
          "title": "new title",
          "createdAt": "2025-01-09T17:35:19.564866",
          "updatedAt": "2025-01-09T17:35:19.564866"
        },
        {
          "id": 2,
          "title": "new title",
          "createdAt": "2025-01-09T17:35:38.366266",
          "updatedAt": "2025-01-09T17:35:38.366266"
        },
        {
          "id": 3,
          "title": "new title",
          "createdAt": "2025-01-09T17:35:39.693247",
          "updatedAt": "2025-01-09T17:35:39.693247"
        }
      ]
    }
  }
  ```

- **innerClass**

  - 긴밀히 연결됨을 표현할 수 있음
  - 클래스 내의 클래스를 중첩하는 것
  - 함께 속하는 클래스를 그룹화
  - 장점 : 유지 관리 쉬움, 코드 읽기 쉬움, 코드 모듈 최적
  - 유형
    1. 중첩된 내부 클래스
    2. 메서드 로컬 내부 클래스
    3. 정적 중첩 클래스
    4. 익명의 내부 클래스

- OneToMany의 관계에서 fetch와 paging은 같이 사용 X

- 이미지 저장하는 방법

  - 이미지용 EC2
  - 서버 돌아가는 공간에 저장

- MIM
