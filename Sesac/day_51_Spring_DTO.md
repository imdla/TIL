## <mark color="#fbc956">DTO</mark>

### 1. DTO (Data Transfer Object)

- 계층간 데이터 전달을 위한 객체

> **Entity와 DTO 분리하는 이유**

- 도메인 모델의 변경으로부터 API 스펙 보호
- 불필요한 데이터 전송 방지
- 양방향 연관관계로 인한 순환 참조 문제 방지
- API 스펙에 맞는 유연한 데이터 구조 제공

### 2. Entity vs DTO

- **Entity**
  - 실제 DB 테이블과 매핑되는 클래스
  - 기본 키, 컬럼, 연관 관계 (ex. `@OneToMany` , `@ManyToOne`) 등을 정의
  - 비즈니스 로직 포함 가능
  - 데이터의 영속성 담당
- **DTO**
  - 오직 데이터 전달만을 위한 클래스
  - 클라이언트에게 필요한 필드만 포함
  - 비즈니스 로직을 포함하지 않음
  - 각 API에 맞는 데이터 구조 제공

> `ModelMapper` , `MapStruct` 등의 매핑 라이브러리 활용 가능

### 3. Request DTO

- 클라이언트에서 서버로 데이터를 전달할 때 사용하는 객체
- 주로 @RequestBody와 함께 사용
- 데이터 유효성 검증을 위한 어노테이션 포함 가능
- @RequestBody 와 사용되기 위해 `@NoArgsContructor` 필요

### 4. Response DTO

- 서버에서 클라이언트로 데이터 반환할 때 사용하는 객체
- Entity의 민감한 정보를 제외하고 필요한 정보만 전달
- 여러 Entity의 데이터를 조합해 반환 가능
- 필드에 `final` 함께 사용

---

## <mark color="#fbc956">Builder 패턴</mark>

### 1. Builder 패턴

- 복잡한 객체의 생성 과정과 표현 방법을 분리해 다양한 구성의 인스턴스 만드는 생성 패턴
- lombok의 `@Builder` 활용해 사용

- **일반 생성자의 문제점**
  - 매개변수가 많을 경우 가독성이 떨어짐
  - 어떤 값이 어떤 필드에 매핑되는지 알기 어려움
  - 선택적 매개변수가 많을 경우 생성자 오버로딩이 많아짐

### 2. 특징

1. **가독성 향상**
   - 생성자의 파라미터가 많을 때 각 값의 의미를 명확히 알 수 있음
   - 선택적 파라미터 처리 용이
2. **불변성 보장**
   - 객체 생성 시점에 모든 값을 설정
   - setter 사용을 줄여 안전한 객체 생성 가능
3. **유연성**
   - 필수값과 선택값을 구분해 처리 가능
   - 다양한 객체 생성 패턴 지원

### 3. 사용

- **Entity :** `id` 를 제외한 빌더가 필요하므로 메서드 레벨에 정의

  ```java
  @Entity
  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public class User {
  	@Id
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
  	private Long id;

  	private String name;
  	private String eamil;

  	@Builder
  	public User(String name, String email) {
  		this.name = name;
  		this.email = email;
  	}
  }
  ```

- **DTO** : 전체 필드에 대해 빌더 필요 → 클래스 레벨에 정의
  - 생성자 없을 경우 자동으로 모든 필드에 대한 생성자 만들어줌
  - 따로 생성자 만들었을 경우, `@AllArgsConstructor` 와 같이 사용
  ```java
  @Getter
  @Builder
  @NoArgsContructor
  @AllArgsContructor
  public class UserCreateRequestDto {
  	private String name;
  	private String email;
  }
  ```
- 사용
  ```java
  User user = User.builder()
  	.name("Name")
  	.eamil("Email")
  	.build();
  ```

---

## <mark color="#fbc956">정적 팩토리 메서드 패턴</mark>

### 1. 정적 팩토리 메서드 패턴

- 객체 생성을 캡슐화하는 패턴
- 생성자 대신 `static` 메서드를 통해 객체 생성

### 2. 특징

1. **명확한 의도 표현**
   - `from()` , `of()` 같은 메서드 이름 통해 객체 생성 의도를 명확히 표현 가능
2. **캡슐화된 객체 생성**
   - 정적 팩토리 메서드는 생성자를 직접 노출하지 않아 객체 생성 로직을 캡슐화 가능
   - DTO 생성하는 과정에서 추가적인 검증이나 데이터 변환 로직이 필요한 경우, 생성자 대신 팩토리 메서드에 로직 넣을 수 있음
   - 호출할 때마다 새로운 객체 생성할 필요 없음 (캐싱, 객체 풀링 등 가능)
3. **객체 생성의 유연성**
   - 하위 자료형 객체 반환 가능
   - ex. `결제` 객체에 대한 생성 시, `OO결제` 인스턴스 반환
4. **무결성 보장**
   - 생성자를 private으로 하면 불완전하거나 잘못된 상태로 객체 생성되는 것 방지 가능
   - 객체 생성 시점에 추가적인 검증 로직 수행 가능

### 3. 사용

- **정의**

  ```java
  @Getter
  @Builder
  public class UserResponseDto {
  	private final Long id;
  	private final String name;
  	private final String email;

  	public static UserResponseDto from(User user) {
  		return UserResponseDto.builder()
  						.id(user.getId())
  						.name(user.getName())
  						.email(user.getEmail())
  						.build();
  	}
  }
  ```

- **사용**
  ```java
  UserResponseDto userResponseDto = UserResponseDto.from(user);
  ```

---

## <mark color="#fbc956">실습</mark>

### 1. 기본 세팅

- `Post` Entity 생성

  ```java
  @Entity
  @Getter
  @NoArgsContructor(access = AccessLevel.PROTECTED)
  public class Post {
  	@Id
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
  	private Long id;

  	private String title;
  	private String content;
  	ptivate String author;
  }
  ```

- `PostController` 생성

  ```java
  @RestController
  @RequestMapping("/jpa/posts")
  @RequiredArgsConstuctor
  public class PostController {
  	private final PostService postService;
  }
  ```

- `PostService` 생성

  ```java
  @Service
  @Transactional(readOnly = true)
  @RequiredArgsConstructor
  public class PostService {
  	private final PostRepository postRepository;
  }
  ```

- `PostRepository` 생성
  - Spring Data Jpa 사용
  ```java
  public interface PostRepository extends JpaRepository<Post, Long> {
  }
  ```

### 2. Create

- 요청 / 응답에 사용하는 Data를 Entity 아닌 DTO로 변경

- `PostController`

  - 요청에 사용하는 Data를 Dto로 사용

  ```java
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PostResponseDto createPost(@RequestBody PostCreateRequestDto requestDto) {
  	return postService.createPost(requestDto);
  }
  ```

- `PostCreateRequestDto` 생성

  - 클라이언트에게 `id` 받지 않음 → 나머지 필드만으로 이루어짐
  - 입력받은 데이터 활용해 `Post` 객체 생성 위해 `toEntity` 메서드 구현

  ```java
  @Getter
  @NoArgsContructor
  public class PostCreateRequestDto {
  	private String title;
  	private String content;
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

- `Post`

  - 빌더 추가

  ```java
  @Builder
  public Post(String title, String content, String author) {
  	this.title = title;
  	this.content = content;
  	this.author = author;
  }
  ```

- `PostService`

  - 응답에서 사용하는 Data를 Dto로 사용

  ```java
  @Transantional
  public PostResponseDto createPost(PostCreateRequestDto requestDto) {
  	Post post = postRepository.save(requestDto.toEntity());
  	return PostResponseDto.from(post);
  }
  ```

- `PostResponseDto` 생성

  ```java
  @Getter
  @Builder
  public class PostResponseDto {
  	private final Long id;
  	private final String title;
  	private final String content;
  	private final String author;

  	public static PostResponseDto from(Post entity) {
  		return PostResponseDto.builder()
  						.id(entity.getId())
  						.title(entity.getTitle())
  						.content(entity.getContent())
  						.author(entity.getAuthor())
  						.build();
  	}
  }
  ```

### 3. Read - 전체

- `PostController`

  ```java
  @GetMapping
  public List<PostListResponseDto> readPosts() {
  	return postService.readPosts();
  }
  ```

- `PostService`

  - 응답에서 사용하는 Data를 Dto로 사용
  - repository 통해 가져온 데이터를 stream api 활용해 Dto로 응답
  - 여러 개의 `Post` 조회하는 경우, `Post` 에 들어있는 모든 필드가 필요하지 않으므로 Dto 분리

  ```java
  public List<PostListResoponseDto> readPosts() {
  	return postRepository.findAll().stream()
  					.map(PostListResponseDto::from)
  					.toList();
  }
  ```

- `PostListResponseDto` 생성

  ```java
  @Getter
  @Builder
  public class PostListResponseDto {
  	private Long id;
  	private String title;

  	public static PostListResponseDto from(Post entity) {
  		return PostListResponseDto.builder()
  						.id(entity.getId())
  						.title(entity.getTitle())
  						.build();
  	}
  }
  ```

### 4. Read - 단일

- `PostController`

  ```java
  @GetMapping("/{id}")
  public PostResponseDto readPostById(@PathVarible Long id) {
  	return postService.readById(id);
  }
  ```

- `PostService`
  - 응답에서 사용하는 Data를 Dto로 사용
  ```java
  public PostResponseDto readPostById(Long id) {
  	Post post = postRepository.findById(id)
  				.orElseThrow(() -> new IllegalArgumentException());
  	return PostResponseDto.from(post);
  }
  ```

### 5. Update

- `PostController`

  - 요청에서 사용하는 Data를 Dto로 사용
  - `Post` 수정하는 경우 `Post` 에 들어있는 모든 필드가 필요하지 않으므로 Dto 분리

  ```java
  @PutMapping("/{id}")
  public PostResponseDto updatePost(@PathVariable Lond id, @RequestBody PostUpdateRequestDto requestDto) {
  	return postService.updatePost(id, requestDto);
  }
  ```

- `PostUpdateRequestDto` 생성
  - id, author는 수정하지 않으므로 나머지 필드만으로 이루어짐
  ```java
  @Getter
  public class PostUpdateRequestDto {
  	private String title;
  	private String content;
  }
  ```
- `PostService`

  - Dirty Checking을 통한 수정 → `.save()` 필요 없음

  ```java
  @Transactional
  public PostResponseDto updatePost(Long id, PostUpdateRequestDto requestDto) {
  	Post post = postRepository.findById(id)
  				.orElseThrow(() -> new IllegalArgumentsException());

  	post.update(requestDto);
  	return PostRepository.from(post);
  }
  ```

- `Post`

  - update 메서드 추가

  ```java
  public Post update(PostUpdateRequestDto requestDto) {
  	this.title = requestDto.getTitle();
  	this.content = requestDto.getContent();

  	return this;
  }
  ```

### 6. Delete

- `PostController`

  ```java
  @DeleteMapping("/{id}")
  public void deletePost(@PathVariable Long id) {
  	postService.deletePost(id);
  }
  ```

- `PostService`

  ```java
  @Transactional
  public void deletePost(Long id) {
  	Post post = postRepository.findById(id)
  				.orElseThrow(() -> new IllegalArgumentException());

  	postRepository.delete(post);
  }
  ```

### 7. @Transactional

- Spring Data Jpa 활용할 경우 `Repository` 단에서 트랜잭션 처리 됨
  → `Service` 에서 @Transactional 작성할 필요 없음
- 단, 하나의 `Service` 에서 여러 개의 `Repository` 호출하는 경우 @Transactional 명시하는 것이 좋음
- 안전성과 일관성 보장 위해 기존 방식대로 `Service` 단에 `@Transactional(readOnly=true)` 명시하고 쓰기 작업일 경우 `@Transactional` 추가해 사용 가능

---

### ☀️ 오늘의 배움

- 요청 → 데이터 처리 → 응답
  - 각 구간마다 필요한 데이터가 다름 → 분리하자 ! (DTO로 전달)
- **DTO : 이동하기 위한 객체**

  - 클라이언트 (요청 : JOSN) → `@RequestBody` 컨트롤러 (요청 : Request DTO) → (Entity로 변경)
    - New DTO
    - 요청에 대한 Validation check
      → 서비스, 레포지토리 진행
      → (응답 : Response DTO) → (응답 : JSON)
    - 어떤 데이터 줄 것인지 정함 (필요한 정보만 전달)

- **Builder**

  - builder에 id 넣어주지 않음
  - Builder : 생성자 대체
  - NoArgsConstructor : request dto라 필요
    - 빈 객체 만들어 하나씩 집어넣음
  - AllArgsConstructor : 빌더를 위해서 필요
    - 전체를 위한 공간 만들어둠

- **정적 메서드 패턴**
  - 생성자에 자유를 주기 위해, 생성자 순수하게 유지하기 위해
  - Integer.valueOf = new Integer(123)
  - 명확한 의도 표현
  - 호출할 때 마다 객체 생성 필요 없음
    → 싱글 톤 패턴 (클래스에 대한 인스턴스 1개면 충분 → 메모리 낭지 방지)
    → 객체 풀링 (여러 개의 인스턴스 만들어 필요할 때 마다 꺼내 씀 → 생성 및 삭제 반복 과정 방지)
- 쓰레드 : 일 하는 단위

  - 쓰레드 풀 : 쓰레드들을 모아놓음

- **Contructor**

  - @NoArgsConstructor : 기본 생성자
  - @AllArgsConstructor : 모든 필드에 대한 생성자
  - @RequiredArgsConstructor : final로 된 생성자
    - DI할 때 사용
    - final은 변경 불가능
    - final 생성했을 때만 값을 넣을 수 있음

- response Dto에서 build할 때 메서드

  - new
  - static

  ```java
  // static
  public static PostResponseDto from(PostV4 entity) {

          return PostResponseDto.builder()
                  .id(entity.getId())
                  .title(entity.getTitle())
                  .content(entity.getContent())
                  .author(entity.getAuthor())
                  .build();
  }

  // new
  public PostResponseDto() {
  				return new PostResponseDto(entity.getId(), entity.getTitle() ...);
  }
  ```

- **DTO** 사용 이유 : 관심사 분리

  - **Controller**
    - 입력 : request DTO
    - call : service (입력)
  - **service**
    - 입력 : request DTO
    - request DTO → Post entity - toEntity - builder 활용
    - Post save → respository.save()
    - 저장된 post → response DTO - from 정적 팩토리 메서드
    - 응답 : response DTO
  - **Controller**
    - 응답 : service의 return : response DTO

- `Transactional()`

  - isolation
  - propagation
  - timeout() : 어느정도 반환 시간(5~10s)을 정함

- url을 가지고 정보, method를 가지고 행위를 짐작 → RESTful
