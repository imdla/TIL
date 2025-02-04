## <mark color="#fbc956">M:N 관계</mark>

### 1. @ManyToMany

- 관계 명시 : `@ManyToMany`
  - 중계 테이블 자동 생성

### 2. @ManyToMany 사용 지양하는 이유

1. 중계 테이블에 컬럼 추가 발생
   - 등록일, 수정일 등 추가 정보 넣을 수 없음
2. 쿼리 최적화의 어려움
   - 중간 테이블이 숨겨져 있어 예상치 못한 쿼리가 발생할 수 있음
3. 비즈니스 로직 추가의 제약
   - 중간 테이블에 대한 세밀한 제어 어려움

> 중간 테이블을 직접 생성해 M:N 관계를 → 1:N, N:1 관계로 풀어내어 사용
>
> - 중간 테이블의 이름은 비즈니스 로직에 가깝게 작성

## <mark color="#fbc956">실습 - Tag</mark>

> 게시글에 태그 기능 추가

### 0. 기본 세팅

- `Tag` Entity 생성 (`/domain/tag/Tag`)

  ```java
  @Entity
  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public class Tag extends BaseTimeEntity {
  	@Id
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
  	private Long id;

  	@Column(nullable = false, unique = true)
  	private String name;
  }
  ```

### 1. CREATE : tag 생성

- `TagController`

  ```java
  @RestController
  @RequestMapping("/tags")
  @RequiredArgsConstructor
  public class TagController {
  	private final TagService tagService;

  	@PostMapping
  	public ResponseEntity<ApiResponse<TagResponseDto>> createTag(@RequestBody TagRequestDto requestDto) {
  		return ReponseEntity.status(HttpStatus.CREATED)
  						.body(ApiResponse.ok(
  							tagService.createService(requestDto)
  						));
  	}
  }
  ```

- `TagRequestDto`

  ```java
  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public class TagRequestDto {
  	@NotBlank
  	private String name;

  	public Tag toEntity() {
  		return Tag.builder()
  						.name(this.name)
  						.build();
  	}
  }
  ```

- `TagService`

  ```java
  @Service
  @RequiredArgsConstructor
  @Transactional(readOnly = true)
  public class TagService {
  	private final TagRepository tagRepository;

  	@Transactional
  	public TagResponseDto createService(TagRequestDto requestDto) {
  		return TagResponseDto.from(tagRepository.save(requestDto.toEntity()));
  	}
  }
  ```

- `TagResponseDto`

  ```java
  @Getter
  @Builder
  public class TagResponseDto {
      private final Long id;
      private final String name;

      public static TagResponseDto from(Tag entity) {
          return TagResponseDto.builder()
                  .id(entity.getId())
                  .name(entity.getName())
                  .build();
      }
  }

  ```

### 2. READ : tag 전체

- `TagController`

  ```java
  @GetMapping
  public ResponseEntity<ApiResponse<List<TagResponseDto>>> readTags() {
     return ResponseEntity.ok(ApiResponse.ok(tagService.readTags()));
  }
  ```

- `TagService`
  ```java
  public List<TagResponseDto> readTags() {
     return tagRepository.findAll().stream()
          .map(TagResponseDto::from)
          .toList();
  }
  ```

---

## <mark color="#fbc956">실습 - TagPost</mark>

### 1. TagPost

- Post와 Tag에 대한 M:N 관계 설정을 위해 PostTag Entity 생성

- `domain/post/entity/PostTag`

  - PostTag 는 Post, Tag에 대해 각각 ManyToOne 관계를 가짐

  ```java
  @Entity
  @Getter
  @NoArgsConstructor
  @EntityListeners(AuditingEntityListener.class)
  public class PostTag {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name = "post_id")
      private Post post;

      @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name = "tag_id")
      private Tag tag;

      @CreatedDate
      @Column(updatable = false)
      private LocalDateTime createAt;
  }

  ```

### 2. CREATE

> **post와 tag를 연결시켜주기 (postTag)**

- 기존의 Post에 기존의 Tag 연결

  1. Post, Tag의 정보 입력받아
  2. 각각의 정보 가져오고
  3. PostTag에 넣어줌

- `PostController`
  - post에 대한 정보는 @PathVariable 통해, tag에 대한 정보는 TagRequestDto를 통해 받음
  ```java
  // post와 tag를 가지고 연결시켜주기 (PostTag)
  @PostMapping("/{id}/tags")
  public void addTagToPost(@PathVariable Long id, @Valid @RequestBody TagRequestDto requestDto) {
  	postService.addTagToPost(id, requestDto);
  }
  ```
- `PostService`

  ```java
  @Transactional
  public void addTagToPost(Long id, TagRequestDto requestDto) {
  	Post post = postRepository.findById(id)
  		.orElseThrow(ResourceNotFoundException::new);
  	Tag tag = tagRepository.findByName(requestDto.getName())
  		.orElseThrow(ResourceNotFoundException::new);

  	PostTag postTag = new PostTag();

  	postTag.addTag(tag);

  	postTag.addPost(post);

  	postTagRepository.save(postTag);
  }
  ```

### 3. 방법 1️⃣ READ

> **Comment, Tag 함께 가져오기**

- 게시글 조회 시 tag가 같이 조회되는 것이 자연스러움
- 기존 로직 수정하지 않고 새로운 로직 추가

  - Post → PostTag의 양방향 연관관계 필요

- `Post`

  - Post에 PostTag에 대한 양방향 연관 관계 추가

  ```java
  @OneToMany(mappedBy = "post",  fetch = FetchType.LAZY)
  private List<PostTag> postTags;
  ```

- 양방향 연결관계 생김
- 양쪽 엔티티가 서로의 연관관계를 일관성 있게 유지할 수 있도록하기 위해 각자 상대방 객체를 설정해줌
  - PostTag 필드에 Post 할당되었으므로 Post 필드에 PostTag 넣어줌
  - 연관관계 편의 메서드 사용해도 좋음
- `PostService`

  ```java
  @Transactional
  public void addTagToPost(Long id, TagRequestDto requestDto) {
  	Post post = postRepository.findById(id)
  		.orElseThrow(ResourceNotFoundException::new);
  	Tag tag = tagRepository.findByName(requestDto.getName())
  		.orElseThrow(ResourceNotFoundException::new);

  	PostTag postTag = new PostTag();

  	postTag.addTag(tag);

  	postTag.addPost(post);
  	post.getPostTags().add(postTag);

  	postTagRepository.save(postTag);
  }
  ```

- `PostController`
  ```java
  // 게시글에 대한 댓글과 태그들 함께 조회
  @GetMapping("/{id}/detail")
  public ResponseEntity<ApiResponse<PostWithCommentAndTagResponseDto>> readPostsByIdWithCommentAndTag(@PathVariable Long id) {
  	return ResponseEntity.ok(ApiResponse.ok(
  		postService.readPostsByIdWithCommentAndTag(id)
    ));
  }
  ```
- `PostService`

  ```java
  public PostWithCommentAndTagResponseDto readPostsByIdWithCommentAndTag(Long id) {
  	Post post = postRepository.findByIdWithCommentAndTag(id)
  	.orElseThrow(ResourceNotFoundException::new);

  return PostWithCommentAndTagResponseDto.from(post );
  }
  ```

- `PostRepository`
  ```java
  @Query("SELECT p FROM Post p " +
  	"LEFT JOIN FETCH p.comments c " +
  	"LEFT JOIN FETCH p.postTags pt " +
  	"LEFT JOIN FETCH pt.tag " +
  	"WHERE p.id = :id")
  Optional<Post> findByIdWithCommentAndTag(@Param("id") Long id);
  ```
- `PostWithCommentAddTagResponseDto`

  ```java
  @Getter
  @Builder
  public class PostWithCommentAndTagResponseDto {
      private final Long id;
      private final String title;
      private final String content;
      private final String author;
      private final LocalDateTime createdAt;
      private final LocalDateTime updatedAt;
      private final List<CommentResponseDto> comments;
      private final List<String> tags;

      public static PostWithCommentAndTagResponseDto from(Post entity) {
          return PostWithCommentAndTagResponseDto.builder()
                  .id(entity.getId())
                  .title(entity.getTitle())
                  .content(entity.getContent())
                  .author(entity.getAuthor())
                  .comments(
                         entity.getComments().stream()
                                  .map(CommentResponseDto::from)
                                  .toList()
                  )
                  .tags(
                          entity.getPostTags().stream()
                                  .map(
                                          postTag -> postTag.getTag().getName()
                                  )
                                  .toList()
                  )
                  .createdAt(entity.getCreatedAt())
                  .updatedAt(entity.getUpdatedAt())
                  .build();
      }
  }
  ```

- **게시글에 대한 댓글과 태그들 함께 조회**
  - ⚠️ `MultipleBagFetchException` 에러 발생 !
  - JPA에서 2개 이상의 List는(`OneToMany일때`) fetch join 할 수 없다.. → 카테시안 곱

### 4. 방법 2️⃣ READ

> **Comment, Tag 따로 가져오기**

- `PostController`
  ```java
  // 게시글에 대한 댓글과 태그들 함께 조회
      @GetMapping("/{id}/detail")
      public ResponseEntity<ApiResponse<PostWithCommentAndTagResponseDto>> readPostsByIdWithCommentAndTag(@PathVariable Long id) {
          return ResponseEntity.ok(ApiResponse.ok(
          postService.readPostsByIdWithCommentAndTag(id)
          ));
      }
  ```
- `PostService`

  ```java
  public PostWithCommentAndTagResponseDto readPostsByIdWithCommentAndTag(Long id) {
          Post postWithTag = postRepository.findByIdWithTag(id)
                  .orElseThrow(ResourceNotFoundException::new);
          List<Comment> comments = commentRepository.findByPostId(id);

          return PostWithCommentAndTagResponseDto.from(postWithTag, comments);
      }
  ```

- `PostRepository`
  ```java
  @Query("SELECT p FROM Post p " +
              "LEFT JOIN FETCH p.postTags pt " +
              "LEFT JOIN FETCH pt.tag " +
              "WHERE p.id = :id")
  Optional<Post> findByIdWithTag(@Param("id") Long id);
  ```
- `PostWithCommentAddTagResponseDto`

  - comments 입력받도록 수정

  ```java
  @Getter
  @Builder
  public class PostWithCommentAndTagResponseDto {
      private final Long id;
      private final String title;
      private final String content;
      private final String author;
      private final LocalDateTime createdAt;
      private final LocalDateTime updatedAt;
      private final List<CommentResponseDto> comments;
      private final List<String> tags;

      public static PostWithCommentAndTagResponseDto from(Post entity, List<Comment> comments) {
          return PostWithCommentAndTagResponseDto.builder()
                  .id(entity.getId())
                  .title(entity.getTitle())
                  .content(entity.getContent())
                  .author(entity.getAuthor())
                  .comments(
                          comments.stream()
                                  .map(CommentResponseDto::from)
                                  .toList()
                  )
                  .tags(
                          entity.getPostTags().stream()
                                  .map(
                                          postTag -> postTag.getTag().getName()
                                  )
                                  .toList()
                  )
                  .createdAt(entity.getCreatedAt())
                  .updatedAt(entity.getUpdatedAt())
                  .build();
      }
  }
  ```

- Comment를 하나의 API가 아닌 별도의 API로 사용 가능

  - 오히려 이 방법이 좋을 수 있음

- fetch join이 2개
  ```java
  Hibernate: select p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,pt1_0.post_id,pt1_0.id,pt1_0.create_at,t1_0.id,t1_0.name,p1_0.title,p1_0.updated_at from post p1_0 left join post_tag pt1_0 on p1_0.id=pt1_0.post_id left join tag t1_0 on t1_0.id=pt1_0.tag_id where p1_0.id=?
  Hibernate: select c1_0.id,c1_0.content,c1_0.created_at,c1_0.post_id,c1_0.updated_at from comment c1_0 left join post p1_0 on p1_0.id=c1_0.post_id where p1_0.id=?
  ```

### 5. 방법 3️⃣ READ

> **Batch size 조절**

- 기존의 N+1 문제를 해결하기 위해 fetch join 활용
- 하지만 2개 이상의 리스트에 대한 fetch join 불가능
- **batch size 조절해 fetch join을 방지 가능**

> **batch size**

- 기존의 게시글 - 댓글 조회 과정
  1. 게시글의 목록 조회
     1. 첫 번째 게시글의 댓글 조회
     2. 두 번째 게시글의 댓글 조회
     3. 세 번째 게시글의 댓글 조회
     4. …
  - 위의 과정이 n번째 댓글이 조회됨의 과정을
  - `SELECT * FROM comment WHERE post_id IN ( batch size 만큼 )`
    과정을 통해 N개의 쿼리를 `N/batch size` 만큼의 쿼리로 줄이는 방식
- 100 ~ 1000 사이의 숫자 사용

> **batch size 설정**

- **글로벌 설정**

  - `application.properties`

  ```java
  spring.jpa.properties.hibernate.default_batch_fetch_size=100
  ```

- **개별 설정**
  ```java
  @BatchSize(size = 100)
  @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
  private List<Comment> comments = new ArrayList<>();
  ```

> **batch size 사용**

- `PostController`
  ```java
  // version 3. batch size 사용
  @GetMapping("/{id}/detail/v2")
  public ResponseEntity<ApiResponse<PostWithCommentAndTagResponseDtoV2>> readPostsByIdWithCommentAndTagV2(@PathVariable Long id) {
      return ResponseEntity.ok(ApiResponse.ok(
            postService.readPostsByIdWithCommentAndTagV2(id)
       ));
  }
  ```
- `PostService`

  ```java
  public PostWithCommentAndTagResponseDtoV2 readPostsByIdWithCommentAndTagV2(Long id) {
  	Post post = postRepository.findByIdWithCommentAndTag(id)
  							.orElseThrow(ResourceNotFoundException::new);

  	return PostWithCommentAndTagResponseDtoV2.from(post);
  }
  ```

- `PostRepository`
  ```java
  @Query("SELECT p FROM Post p " +
  	"LEFT JOIN p.comments c " +
  	"LEFT JOIN p.postTags pt " +
  	"LEFT JOIN pt.tag " +
  	"WHERE p.id = :id")
  Optional<Post> findByIdWithCommentAndTag(@Param("id") Long id);
  ```
- `PostWithCommentAddTagResponseDtoV2`

  ```java
  @Getter
  @Builder
  public class PostWithCommentAndTagResponseDtoV2 {
      private final Long id;
      private final String title;
      private final String content;
      private final String author;
      private final LocalDateTime createdAt;
      private final LocalDateTime updatedAt;
      private final List<CommentResponseDto> comments;
      private final List<String> tags;

      public static PostWithCommentAndTagResponseDtoV2 from(Post entity) {
          return PostWithCommentAndTagResponseDto.builder()
                  .id(entity.getId())
                  .title(entity.getTitle())
                  .content(entity.getContent())
                  .author(entity.getAuthor())
                  .comments(
                          entity.getComments().stream()
                                  .map(CommentResponseDto::from)
                                  .toList()
                  )
                  .tags(
                          entity.getPostTags().stream()
                                  .map(
                                          postTag -> postTag.getTag().getName()
                                  )
                                  .toList()
                  )
                  .createdAt(entity.getCreatedAt())
                  .updatedAt(entity.getUpdatedAt())
                  .build();
      }
  }
  ```

- fetch join이 2개인 경우 → batch size로 사용
- in ( … ) : 으로해서 괄호 내부의 것들을 한 번에 가져옴
  → 쿼리 4개 발생

```java
Hibernate: select p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,p1_0.title,p1_0.updated_at from post p1_0 left join comment c1_0 on p1_0.id=c1_0.post_id left join post_tag pt1_0 on p1_0.id=pt1_0.post_id where p1_0.id=?
Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id=?
Hibernate: select pt1_0.post_id,pt1_0.id,pt1_0.create_at,pt1_0.tag_id from post_tag pt1_0 where pt1_0.post_id=?
Hibernate: select t1_0.id,t1_0.name from tag t1_0 where t1_0.id in (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
```

> **BATCH와 FETCH JOIN 함께 사용**

- 같이 사용해 최적화할 수 있음
- 중복(카티션 곱으로 인해)이 있을 수 있어 DISTICNT 사용 필요할 수 있음
- `PostRepository`
  ```java
  @Query("SELECT DISTINCT p FROM Post p " +
  	"LEFT JOIN p.comments c " +
  	"LEFT JOIN FETCH p.postTags pt " +
  	"LEFT JOIN FETCH pt.tag " +
  	"WHERE p.id = :id")
  Optional<Post> findByIdWithCommentAndTag(@Param("id") Long id);
  ```

```java
Hibernate: select distinct p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,pt1_0.post_id,pt1_0.id,pt1_0.create_at,t1_0.id,t1_0.name,p1_0.title,p1_0.updated_at from post p1_0 left join comment c1_0 on p1_0.id=c1_0.post_id left join post_tag pt1_0 on p1_0.id=pt1_0.post_id left join tag t1_0 on t1_0.id=pt1_0.tag_id where p1_0.id=?
Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id=?
```

→ 쿼리 2개 발생
