## <mark color="#fbc956">DTO in JPQL</maek>

### 1. DTO in JPQL

- JPQL 내부에서 DTO 활용 가능

```java
SELECT new 패키지.DTOClass(param1, param2, ...)
FROM Entity e
WHERE ...
```

- 사용

  ```java
  // DTO 클래스
  @Getter
  @AllArgsConstuctor
  public class MemberDto {
  	private String username;
  	private int age;
  }
  ```

  ```java
  // Repository
  @Repository
  public interface MemberRepository extends JpaRepository<Member, Long> {
  	@Query("SELECT new com.example.dto.MemberDto(m.username, m.age) " +
  						"FROM Member m " +
  						"WHERE m.age >= :age")
  	List<MemberDto> findDtoByAge(@Param("age") int age);
  }
  ```

- **JPQL 내부에서 DTO 직접 활용 시 장점**

  - 성능 최적화
  - 집계함수 활용 용이

- **주의사항**
  - DTO 생성하는 new 연산자에는 반드시 패키지 경로를 포함한 전체 클래스명 작성
  - 파라미터 순서와 타입이 정확히 일치

---

## <mark color="#fbc956">record</mark>

### 1. record

(Java 14에서 도입)

- 불변 데이터 객체를 생성하기 위한 특별한 종류의 클래스
- **자동으로 생성되는 구성**

  - 생성자
  - getter 메서드
  - equals()
  - hashCode()
  - toString()

- **장점**

  1. 코드 매우 간결해짐
  2. 불변성 보장
     - 모든 필드가 final
     - setter 생성 불가능
  3. 명확한 의도 전달 가능
     - DTO나 데이터 전달용 객체임을 바로 알 수 있음
     - 코드 가독성 향상

- **단점**
  1. 제약사항이 많음
     - 상속 불가능
     - 추가 필드 선언 불가능
     - 모든 필드가 final (장점이자 단점)
  2. Builder 패턴 사용 불가능
     - @Builder 어노테이션 적용 불가
     - 필드 많을 때 생성자 사용 불편
  3. JPA 엔티티로 사용 불가능
     - 기본 생성자 없음
     - 필드 수정 불가
     - 프록시 생성 불가

> 단순한 DTO의 경우, 사용하면 좋음
> 필드가 많은 경우, 기존의 방식이 유리

### 2. 사용

- 기존 DTO
  ```java
  @Getter
  @AllArgsConstuctor
  @EqualsAndHashCode
  public class PostDto {
  	private final Long id;
  	private final String title;
  }
  ```
- record
  ```java
  public record PostDto(Long id, String title) {}
  ```

---

## <mark color="#fbc956">실습 - READ 댓글 개수 조회</mark>

> 게시글의 목록을 댓글의 개수를 함께 반환
>
> - **집계의 경우**
>   - comment 데이터를 직접 활용하는 것이 아님
>   - fetch join이 필요하지 않음

### 0. 기본 세팅

- `PostListWithCommentCountResonseDto`
  - 간단한 DTO의 경우 record 사용해도 좋음
  ```java
  public record PostListWithCommentCountResponseDto (
  	Long id,
  	String title,
  	LocalDateTime createAt,
  	Long commentCount
  ) {}
  ```

### 방법 1️⃣. entity와 count 가져와 DTO 변경, 집계함수

- `PostController`
  ```java
  // 댓글 개수 조회 (집계 함수)
  @GetMapping("/comment-count")
  public ResponseEntity<ApiResponse<List<PostListWithCommentCountResponseDto>>> readPostsWithCommentCount() {
  	return ResponseEntity.ok(ApiResponse.ok(
  		postService.readPostsWithCommentCount())
  	);
  }
  ```
- `PostService`
  ```java
  public List<PostListWithCommentCountResponseDto> readPostsWithCommentCount() {
  	List<Object[]> results = postRepository.findAllWithCommentCount();
  		return results.stream()
  			.map(
  				result -> {
  					Post post = (Post) result[0];
  					Long CommentCount = (Long) result[1];
  					// dto의 from 과정
  					return new PostListWithCommentCountResponseDto(
  						post.getId(),
  						post.getTitle(),
  						post.getCreatedAt(),
  						CommentCount
  					);
  			})
  			.toList();
  }
  ```
- `PostRepository`
  ```java
  @Query("SELECT p, COUNT(c) " +
  	"FROM Post p " +
  	"LEFT JOIN p.comments c " +
  	"GROUP BY p")
  List<Object[]> findAllWithCommentCount();
  ```
- 집계 함수 사용을 통해 최적화 가능 !
- LAZY이지만 집계 함수라 → 1개의 쿼리만 발생
  ```java
  Hibernate: select p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,p1_0.title,p1_0.updated_at,count(c1_0.id) from post p1_0 left join comment c1_0 on p1_0.id=c1_0.post_id group by p1_0.id
  ```

### 방법 2️⃣. JPQL에 DTO 사용

- `PostController`
  ```java
  // 댓글 개수 조회2 (JPQL에 DTO 사용)
  @GetMapping("/comment-count-dto")
  public ResponseEntity<ApiResponse<List<PostListWithCommentCountResponseDto>>> readPostsWithCommentCountDto() {
  	return ResponseEntity.ok(ApiResponse.ok(
  		postService.readPostsWithCommentCountDto()
  	));
  }
  ```
- `PostService`
  ```java
  public List<PostListWithCommentCountResponseDto> readPostsWithCommentCountDto() {
  	return postRepository.findAllWithCommentCountDTO();
  }
  ```
- `PostRepository`

  ```java
  @Query("SELECT new com.example.relation.domain.post.dto.PostListWithCommentCountResponseDto(p.id, p.title, p.createdAt, COUNT(c)) " +
  	"FROM Post p " +
  	"LEFT JOIN p.comments c " +
  	"GROUP BY p")
  List<PostListWithCommentCountResponseDto> findAllWithCommentCountDTO();
  ```

- 동일하게 1개의 쿼리 발생
- 방법1의 예제보다 Service를 간단한 로직으로 작성 가능
- 하지만 JPQL에 DTO의 패키지 작성 필요
  ```java
  Hibernate: select p1_0.id,p1_0.title,p1_0.created_at,count(c1_0.id) from post p1_0 left join comment c1_0 on p1_0.id=c1_0.post_id group by p1_0.id
  ```
