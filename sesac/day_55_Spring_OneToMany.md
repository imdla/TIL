## <mark color="#fbc956">기본 세팅</mark>

- Comment Entity 생성

  - Comment : Post = N : 1 관계 ⇒ `@ManyToOne` 사용
  - 외래키의 필드명 : `post_id`

  ```java
  @Getter
  @Entity
  @NoArgsContructor(access = AccessLevel.PROTECTED)
  public class Comment extends BaseTimeEntity {
  	@Id
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
  	private Long id;

  	@ManyToOne
  	@JoinColumn(name = "post_id")
  	private Post post;

  	private String content;
  }
  ```

---

## <mark color="#fbc956">CREATE</mark>

### 1. `CommentController`

- RESTful한 URL 지정
- 댓글 작성할 게시글 지정 : `postId`
- 댓글 작성을 위한 데이터 입력 : `requestDto`
- commentService의 댓글 생성 메서드 호출
- ResponseEntity, ApiResponse 설정

```java
@RestController
@RequestMapping("/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;

	@PostMapping
	public ResponseEntity<ApiResponse<CommentResponseDto>> createComment(
		@PathVariable Long postId,
		@Valid @RequestBody CommentRequestDto requestDto
	){
		return ResponseEntity
						.status(HttpStatus.CREATED)
						.body(
							ApiResponse.ok(
								"댓글이 성공적으로 작성되었습니다.",
								"CREATED",
								commentService.createComment(postId, requestDto)
							)
						);
	}
}
```

### 2. `CommentRequestDto`

```java
@Getter
@NoArgsConstuctor
public class CommentRequestDto {
	@NotBlank
	@Length(min = 5)
	private String content;
}
```

### 3. `CommentService`

- postId를 통해 댓글 작성할 게시글 가져오기
- requestDto의 데이터 + post 가지고 comment 생성
- comment 저장 후 CommentResponseDto로 반환

```java
@Service
@RequiredArgsContructor
@Transactional(readOnly = true)
public class CommentService {
	private final CommentRepository commentRepository;
	private final PostRepository postRepository;

	@Transactional
	public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto) {
		Post post = postRepository.findById(postId)
								.orElseThrow(() -> new ResourceNot FoundException());
		Comment comment = commentRespository.save(requestDto.toEntity(post));
		return CommentResponseDto.from(comment);
	}
}
```

### 4. `CommentRequestDto`

- toEntity 메서드 정의

```java
public Comment toEntity(Post post) {
	return Comment.builder()
					.content(this.content)
					.post(post)
					.build();
}
```

### 5. `Comment`

- Builder 추가

```java
@Builder
public Comment(Post post, String content) {
	this.post = post;
	this.content = content;
}
```

### 6. `CommentResponseDto`

```java
@Getter
@Builder
public class CommentResponseDto {
	private final Long id;
	private final String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public static CommentResponseDto from(Comment entity) {
		return CommentResponseDto.builder()
						.id(getId())
						.content(getContent())
						.createdAt(getCreatedAt())
						.updatedAt(getUpdatedAt())
						.builder();
	}
}
```

### 7. 연관관계 편의 메소드

- comment에 post 추가 시 post의 comment 목록에도 post 추가
- `Comment`

  ```java
  @Builder
  public Comment(Post post, String content) {
  	this.content = content;
  	setPost(post);
  }

  public void setPost(Post post) {
  	this.post = post;
  	post.getComments().add(this)
  }
  ```

---

## <mark color="#fbc956">READ</mark>

### 1. 들어가기 전

- 댓글의 조회는 게시글의 detail 페이지에서 하는 것이 자연스러움
- post에서 comments에 접근하기 위해 양방향 연결관계 추가
  - `Post`
  ```java
  public class Post extends BaseTimeEntity {
  	...
  	@OneToMany(mappedBy = "post")
  	private List<Comment> comments;
  }
  ```

### **방식** 1️⃣**. Post와 Comments 따로 가져오기**

> 게시글 정보 따로, 해당 게시글에 대한 댓글 목록을 따로 만들어 Dto로 합쳐 전달
>
> - URL : `/posts/{post_id}`

1.  Post의 READ - 단일 조회
    - `PostController`
      ```java
      @GetMapping("/{id}")
      public ResponseEntity<ApiResponse<PostWithCommentResponseDto>> readPostById(@PathVariable Long id) {
      	return ResponseEntity.ok(ApiResponse.ok(postService.readById(id)));
      }
      ```
2.  `post` 와 함께 `comments` 가져오는 로직 각각 작성
    - postId와 일치하는 Post 전달할 때, postId와 일치하는 comments 전달
    - Service에서 Post와 Comment 따로 가져와 DTO로 바꿔주는 것
    - `PostService`
      ```java
      public PostWithCommentResponseDto readPostById(Long id) {
      	Post post = postRepository.findById(id)
      							.orElseThrow(() -> new ResourceNotFoundException());
      	List<Comment> comments = commentRepository.findByPostId(id);
      	return PostWithCommentResponseDto.from(post, comments);
      }
      ```
3.  postId를 가지는 모든 comment 가져오기
    - `CommentRespository`
      ```java
      public interface CommentRepository extends JpaRepository<Comment, Long> {
      	List<Comment> findByPostId(Long postId);
      }
      ```
4.  Post와 comments를 Dto로 묶어서 전달

    - 객체를 받아 DTO로 바꾸기 → from 메서드에서 진행
    - `List<Comment> comments` 를 받아
      → `List<CommentRequestDto> comments`로 변경 (DTO 형태로 변경)
    - DTO comments builder할 때 Entity comments를 stream 활용해 Dto로 변경
    - Post의 JSON 형태와 `PostWithCommentResponseDto`

      ```
      // 전달하는 Post의 JSON 형태
      P : {
          id:
          title:
          content:
          comments : [
            commentsDto,
            commentsDto,
            commentsDto,
          ]
      }
      ```

      ```java
      @Getter
      @Builder
      public class PostWithCommentResponseDto {
          private final Long id;
          private final String title;
          private final String content;
          private final String author;
          private final LocalDateTime createdAt;
          private final LocalDateTime updatedAt;
          private final List<CommentResponseDto> comments;

      public static PostWithCommentResponseDto from(Post entity, List<Comment> comments) {
              return PostWithCommentResponseDto.builder()
                      .id(entity.getId())
                      .title(entity.getTitle())
                      .content(entity.getContent())
                      .author(entity.getAuthor())
                      .comments(
                              comments.stream()
                                      .map(CommentResponseDto::from)
                                      .toList()
                      )
                      .createdAt(entity.getCreatedAt())
                      .updatedAt(entity.getUpdatedAt())
                      .build();
          }
      ```

- ⚠️ 쿼리문이 2개 발생
  ```java
  Hibernate: select p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,p1_0.title,p1_0.updated_at from post p1_0 where p1_0.id=?
  Hibernate: select c1_0.id,c1_0.content,c1_0.created_at,c1_0.post_id,c1_0.updated_at from comment c1_0 left join post p1_0 on p1_0.id=c1_0.post_id where p1_0.id=?
  ```

### 방식 2️⃣. **Post와 Comments 같이 가져오기**

> join을 통해 게시글을 가져올 때 댓글을 함께 가져옴

1. 새로운 매핑 생성
   - `/v2/{id}` 대한 새로운 매핑 생성
   - postService의 `readPostByIdV2` 호출
   - `PostController`
     ```java
     @GetMapping("/v2/{id}")
     public ResponseEntity<ApiResponse<PostWithCommentResponseDtoV2>> readPostByIdV2 (@PathVariable Long id) {
     	return ResponseEntity.ok(ApiResponse.ok(postService.readPostByIdV2(id)));
     }
     ```
2. Service에서 postId를 검사해 **Post를 가져올 때, Comment도 같이 가져오는 로직 작성**
   - `PostService`
     ```java
     public PostWithCommentResponseDtoV2 readPostByIdV2(Long id) {
     	Post post = postRepository.findByIdWithComment(id)
     							.orElseThrow(() -> new ResourceNotFoundException());
     	return PostWithCommentResponseDtoV2.from(post);
     }
     ```
3. postId를 가지는 모든 comment 가져오기
   - postRepository에서 `findByWithComment` 메서드 생성
   - 쿼리문 생성 → postId와 일치하는 comments들 JOIN해 Post 생성
   - `PostRepository`
     ```java
     public interface PostRepository extends JpaRepository<Post, Long> {
     	@Query("SELECT p FROM Post p LEFT JOIN p.comments WHERE p.id = :id")
     	Optional<Post> findByIdComment(@Param("id") Long id);
     }
     ```
4. post가 comments를 이미 가지고 있으므로 `entity.getComments()` 통해 comments에 접근

   - 가져온 Post → DTO로 변경 (`PostWithCommentResponseDtoV2`)
   - 1번에서 comments를 인자로 받아왔지만
   - 현재는 Post로 comment를 다 받아왔음
   - Post라는 entity의 필드에 comments가 있다 !
     → getComments()하면 comments를 가지고 올 수 있다
   - `PostWithCommentResponseDtoV2`

     ```java
     @Getter
     @Builder
     public class PostWithCommentResponseDtoV2 {
         private final Long id;
         private final String title;
         private final String content;
         private final String author;
         private final LocalDateTime createdAt;
         private final LocalDateTime updatedAt;
         private final List<CommentResponseDto> comments;

     public static PostWithCommentResponseDtoV2 from(Post entity, List<Comment> comments) {
             return PostWithCommentResponseDtoV2.builder()
                     .id(entity.getId())
                     .title(entity.getTitle())
                     .content(entity.getContent())
                     .author(entity.getAuthor())
                     .comments(
                             entity.getComments().stream()
                                     .map(CommentResponseDto::from)
                                     .toList()
                     )
                     .createdAt(entity.getCreatedAt())
                     .updatedAt(entity.getUpdatedAt())
                     .build();
         }
     ```

- ⚠️ 아직도 쿼리문이 2개 발생
  ```java
  Hibernate: select p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,p1_0.title,p1_0.updated_at from post p1_0 left join comment c1_0 on p1_0.id=c1_0.post_id where p1_0.id=?
  Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id=?
  ```

---

## <mark color="#fbc956">UPDATE</mark>

> URL : `/posts/{post_id}/comments/{comment_id}`
>
> - RESTful → 식별 기능, 계층 구조 표시

### 1. `CommentController`

- postId, commentId, requestDto 전달받음

```java
@PutMapping("/{commentId}")
public ResponseEntity<ApiResponse<CommentResponseDto>> update(
	@PathVariable Long postId,
	@PathVariable Long commentId,
	@Valid @RequestBody CommentRequestDto requestDto
){
	return ResponseEntity.ok(ApiResponse.ok(commentService.updateComment(postId, commentId, requestDto)));
}
```

### 2. `CommentService`

- comment 조회 후 update 실행

```java
@Transactional
public CommentResponseDto updateComment(Long postId, Long commentId, CommentRequestDto requestDto) {
	Comment comment = commentRepository.findById(commentId)
										.orElseThrow(() -> new ResourceNotFoundException());
	comment.update(requestDto);
	return CommentResponseDto.from(comment);
}
```

### 3. `Comment`

- Comment에 update 로직 추가

```java
public Comment update (CommentRequestDto requestDto) {
	this.content = requestDto.getContent();
	return this;
}
```

---

### ☀️ 오늘의 배움

- **연관관계 편의 메서드**

  - memberA ← teamA 설정 시
    teamA ← memberA 설정해줌
  - 연관관계 주인 설정 시 DB에는 정상적 반영
  - 영속성 컨텍스트에는 순수 객체 상태로 존재해 양쪽 모두 관계 설정

  ```java
  @Builder
      public Comment(String content, Post post) {
          this.content = content;
          setPost(post);
      }

      public void setPost(Post post) {
          this.post = post;
          post.getComments().add(this);
      }
  ```
