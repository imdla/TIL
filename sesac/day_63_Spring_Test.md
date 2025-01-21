## <mark color="#fbc956">테스트 코드</mark>

### 1. 테스트 코드

- 작성한 프로그램이 의도한 대로 동작하는지 검증하는 코드
- 테스트 코드를 통해 버그를 미리 발견하고, 리팩터링 시 안정성을 확보할 수 있음
- 프로그램의 품질을 향상, 유지보수 용이

- JUnit 과 AssertJ
  - **JUnit** : 자바용 단위 테스트 프레임워크
  - **AssertJ** : 테스트 코드를 더 쉽고 읽기 좋게 작성할 수 있게 도와주는 라이브러리

### 2. 테스트 클래스

- `src/test` 내부에 위치
- ctrl + shift + t 통해 생성

### 3. 테스트 메서드

- 메서드명은 가독성이 좋도록 한글 작성 무방함
- `import org.assertj.core.api.Assertions;` 의 `assertThat`을 활용
  - alt + enter 통해 static import 가능

```java
@Test
void 테스트_메소드_이름() {
	// given
	// 테스트 위한 준비 과정

	// when
	// 테스트하고자 하는 실제 로직

	// then
	// 테스트 결과 검증
}
```

### 4. 테스트 실행 순서 관련 어노테이션

- `@BeforeEach` : 각 테스트 메소드 실행 전 실행
- `@AfrerEach` : 각 테스트 메소드 실행 후 실행
- `@BeforeAll` : 모든 테스트 시작 전에 한 번 실행
- `@AfterAll` : 모든 테스트 종료 후 한 번 실행

### 5. AssertJ 주요 메서드

- 객체 같음 검증

  ```java
  assertThat(actual).isEqualTo(expected);
  assertThat(actual).isNotEqualTo(expected);
  ```

- Null 검증

  ```java
  assertThat(actual).isNull();
  assertThat(actual).isNotNull();
  ```

- Optional 값 검증

  ```java
  assertThat(optional).isPresent(); // 값이 존재함
  assertThat(optional).isEmpty();   // 값이 존재하지 않음
  ```

  - 존재한다면, 이후 `.get()` 으로 값 가져올 수 있음

- 크기 비교

  ```java
  assertThat(actual).isGreaterThan(expected);
  assertThat(actual).isGreaterThanOrEqualTo(3);
  assertThat(actual).isLessThan(expected);
  assertThat(actual).isLessThanOrEqualTo(3);
  ```

- 범위 검증

  ```java
  assertThat(actual).isBetween(1, 3);
  assertThat(actual).isPositive();
  assertThat(actual).isNegative();
  assertThat(actual).isZero();
  ```

- 문자열 포함 여부

  ```java
  assertThat(actual).contains("abc"); // abc 포함
  assertThat(actual).containsIgnoringCase("abc"); // 대소문자 무시하고 포함
  assertThat(actual).doesNotContain("abc"); // abc 미포함
  ```

- 문자열 시작/끝

  ```java
  assertThat(actual).startWith("start"); // start로 시작
  assertThat(actual).endWith("end"); // end로 끝남
  ```

- 리스트의 크기와 내용

  ```java
  assertThat(list).hasSize(3); // 크기가 3
  assertThat(list).isEmpty();  // 비어있음
  assertThat(list).isNotEmpty(); // 비어있지 않음
  assertThat(list).contains("A", "B"); // A, B 포함
  assertThat(list).containsExactly("A", "B", "C"); // 정확히 A, B, C만 포함
  ```

- 컬렉션 순서

  ```java
  assertThat(list).isSorted(); // 정렬되어 있음
  assertThat(list).containsExactlyInAnyOrder("A", "B"); // 순서 무관
  ```

- 예외 검증

  ```java
  assertThatThrownBy(() -> someMethod())
  	.isInstanceOf(IllegalArgumentException.class)
  	.hasMessage("Invalid argument");
  ```

- 객체의 속성 검증
  ```java
  assertThat(person).extracting(Person::getName).isEqualTo("John");
  assertThat(person).extracting("name").isEqualTo("Jhon");
  ```

> **기존 설정의 테스트코드에서 lombok 사용 불가**

- 다음과 같은 설정 추가 시 사용 가능
- `build.gradle`
  ```java
  dependencies {
    ...
  	testCompileOnly 'org.projectlombok:lombok'
  	testAnnotationProcessor 'org.projectlombok:lombok'
  }
  ```

---

## <mark color="#fbc956">Mock</mark>

### 1. Mock 객체

- 실제 객체를 대신해 가짜 객체를 만들어 테스트하는 방법
- 외부 의존성을 제거해 독립적인 테스트를 가능하게 함

- **Mock 객체 사용 이유**
  1. **테스트 속도 향상**
     - 실제 DB, 외부 API 호출 없이 테스트 가능
     - 네트워크, 파일 I/O 없이 빠른 테스트 가능
  2. **독립적인 테스트 환경**
     - 외부 시스템과 무관하게 테스트 가능
     - 특정 상황을 시뮬레이션 가능
  3. **예측 가능한 테스트**
     - 테스트마다 동일한 결과 보장
     - 외부 요인에 의한 테스트 실패 방지

### 2. Mockito 프레임워크 기본 사용 방법

- **Mock 객체 동작 정의**

  - mock의 메서드를 실행 시, 다음과 같이 동작할 것

  ```java
  // 값 반환
  given(mock.method()).willReturn(value);

  // 예외 발생
  given(mock.method()).willThrow(new Exception());

  // 호출 여부
  verify(mock).method();
  ```

- 기본 사용법

  ```java
  @ExtendWith(MockitoExtension.class)
  class PostServiceTest {
  	@Mock
  	private PostRepository postRepository; // Mock 객체 생성

  	@InjectMocks
  	private PostService postService; // Mock을 주입받는 객체

  	@Test
  	void test() {
  		// given
  		Post post = new Post("제목", "내용");
  		given(postRepository.findById(1L)) // Mock 동작 정의
  			.willReturn(Optional.of(post));

  		// when
  		Post result = postService.findById(1L);

  		// then
  		verify(postRepository).findById(1L); // 메소드 호출 검증
  	}
  }
  ```

---

## <mark color="#fbc956">단위 테스트 (Unit Test)</mark>

### 1. 단위 테스트

- 개별 코드 단위(메소드, 클래스)가 의도한대로 동작하는지 검증하는 테스트
- 다른 클래스와의 의존성을 제거하고 독립적으로 테스트

- **장점**

  - 실행 속도 매우 빠름
  - 테스트 실패 시 문제 쉽게 찾을 수 있음
  - 리팩토링 시 안정성을 보장함
  - 코드의 문서화 역할

- **단점**
  - 실제 환경과는 다른 격리된 환경에서 테스트
  - Mocking이 필요한 경우 테스트 코드가 복잡해질 수 있음

### 2. Repository unit test

- 테스트 위해 h2 데이터베이스에 대한 의존성 추가함

  - h2 데이터베이스 : 개발 및 테스트 환경에서 사용되는 RDB
  - `build.gradle`
    ```java
    dependencies {
      ...
    	testRuntimeOnly 'com.h2database:h2'
    }
    ```

- `@DataJpaTest`

  - JPA 관련 테스트를 위한 어노테이션

- `PostRepositoryTest`

  - `@BeforeEach` 활용해 테스트를 위한 데이터 생성

  ```java
  @DataJpaTest
  class PostRepositoryTest {
  	@Autowired
  	private PostRepository postRepository;

  	@Autowired
  	private CommentRepository commentRepository;

  	private Post post;
  	private Comment comment1;
  	private Comment comment2;

  	@BeforeEach
  	void setUp() {
  		post = new Post("테스트 제목", "테스트 내용", "테스트 작성자");
  		post = postRepository.save(post);

  		comment1 = new Comment("댓글1", post);
  		comment2 = new Comment("댓글2", post);
  		commentRepository.save(comment1);
  		commentRepository.save(comment2);
  	}
  }
  ```

- `findByIdWithComment_성공`

  ```java
  @Test
  void findByIdWithComment_성공() {
  	// when
  	Optional<Post> foundPost = postRepository.findByIdWithComment(post.getId);

  	// then
  	assertThat(foundPost).isPresent();
  	assertThat(foundPost.get().getComments()).hasSize(2);
  }
  ```

- `findAllWithCommentCountDTO_성공`

  ```java
  @Test
  void findAllWithCommentCountDTO_성공() {
  	// when
  	List<PostListWithCommentCountResponseDto> results =
  			postRepository.findAllWithCommentCountDto();

  	// then
  	assertThat(results).hasSize(1);

  	PostWithCommentCountResponseDto dto = results.get(0);

  	assertThat(dto.id()).isEqualTo(post.getId());
  	assertThat(dto.title()).isEqualTo("테스트 제목");
  	assertThat(dto.commentCount()).isEqualTo(2L);
  }
  ```

- `findAllByTagName_태그이름이_없는_경우_빈리스트_반환`

  ```java
  @Test
  void findAllByTagName_태그이름이_없는_경우_빈리스트_반환() {
  	// given
  	String notExistTagName = "존재하지않는태그";

  	// when
  	List<Post> posts = postRepository.findAllByTagName(notExistTagName);

  	// then
  	assetThat(posts).isEmpty();
  }
  ```

### 3. Service unit test

- `PostServiceTest`

  ```java
  import org.junit.jupiter.api.Test;
  import org.junit.jupiter.api.extension.ExtendWith;
  import org.mockito.InjectMocks;
  import org.mockito.Mock;
  import org.mockito.junit.jupiter.MockitoExtension;

  import static org.assertj.core.api.Assertions.*;
  import static org.mockito.BDDMockito.*;

  @ExtendWith(MockitoExtension.class)
  class PostServiceTest {
  	@Mock
  	private PostRepository postRepository;

  	@InjectMocks
  	private PostService postService;
  }
  ```

- `createPost_성공`

  ```java
  @Test
  void createPost_성공() {
  	// given
  	PostCreateRequestDto requestDto = new PostCreateRequestDto(
  		"테스트 제목",
  		"테스트 내용",
  		"테스트 작성자"
  	);
  	Post post = requestDto.toEntity();
  	given(postRepository.save(any(Post.class))).willReturn(post);

  	// when
  	PostResponseDto result = postService.createPost(requestDto);

  	// then
  	assertThat(result.getTitle()).isEqualTo("테스트 제목");
  	assertThat(result.getContent()).isEqualTo("테스트 내용");
  	verify(postRepository).save(any(Post.class));
  }
  ```

- `readPostByIdV2_성공`

  ```java
  @Test
  void readPostByIdV2_성공() {
  	// given
  	Long postId = 1L;
  	Post post = Post.builder()
  							.title("테스트 제목")
  							.content("테스트 내용")
  							.author("테스트 작성자")
  							.build();

  	Comment comment = Comment.builder()
  										.content("댓글 내용")
  										.post(post)
  										.build();

  	given(postRepository.findByIdWithComment(postId)).willReturn(Optional.of(post));

  	// when
  	PostWithCommentResponseDtoV2 result = postService.readPostByIdV2(postId);

  	// then
  	assertThat(result).isNotNull();
  	assertThat(result.getTitle()).isEqualTo("테스트 제목");
  	assertThat(result.getContent()).isEqualTo("테스트 내용");
  	assertThat(result.getComments()).hasSize(1);
  	assertThat(result.getComments().get(0).getContent()).isEqualTo("댓글 내용");
  	verify(postRepository).findByIdWithComment(postId);
  }
  ```

- `readPostById_실패_게시글이_없는_경우`

  ```java
  @Test
  void readPostById_실패_게시글이_없는_경우() {
  	// given
  	Long postId = 1L;
  	given(postRepository.findById(postId)).willReturn(Optional.empty());

  	// when & then
  	assertThatThrownBy(() -> postService.readPostById(postId))
  		.isInstanceOf(ResourceNotFoundException.class);

  	verify(postRepository).findById(postId);
  	verify(commentRepository, never()).findByPostId(anyLong());
  }
  ```

---

## <mark color="#fbc956">통합 테스트</mark>

### 1. 통합 테스트

- 애플리케이션의 여러 계층을 함께 테스트하는 방식
- 실제 운영 환경과 가장 유사한 환경에서 테스트 수행
- `@SpringBootTest` 어노테이션 사용

- **특징**
  1. **모든 빈을 로드해 테스트**
     - 실제 애플리케이션 구동과 동일한 환경
     - Security, JPA 등 모든 설정이 포함됨
  2. **전체 흐름을 한번에 테스트 가능**
     - Controller → Service → Repository 전체 흐름
     - 실제 DB 연동 가능

### 2. 실습

- `PostIntegrationTest`

  ```java
  import static org.assertj.core.api.Assertions.*;
  import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
  import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
  import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
  import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

  @ActiveProfiles("test")
  @SpringBootTest()
  @AutoConfigureMockMvc(addFilters = false) // 시큐리티 필터 비활성화
  class PostIntegrationTest {
  	@Autowired
  	private MockMvc mockMvc;

  	@Autowired
  	private PostRepository postRepository;

  	@BeforeEach
  	void setUp() {
  		postRepository.deleteAll(); // 각 테스트 전 데이터 초기화
  	}
  }
  ```

- `DataInitializer`

  - 테스트에서 실행되지 않도록 프로필 분리

  ```java
  @Profile("!test")
  public class DataInitializer implements CommandLineRunner { ... }
  ```

- `게시글_생성_테스트()`

  ```java
  @Test
  void 게시글_생성_테스트() throw Exception {
  	// given
  	PostCreateRequestDto request = new PostCreateRequestDto(
  		"제목입니다",
  		"내용입니다",
  		"작성자"
  	);

  	// when
  	ResultActions result = mockMvc.perform(
  		post("/posts")
  			.contentType(MediaType.APPLICATION_JSON)
  			.content(new ObjectMapper().writeValueAsString(request))
  	);

  	// then
  	result.andExpect(status().isCreated())
  		.endExpect(jsonPath("$.data.title").value("제목입니다"))
  		.endExpect(jsonPath("$.data.content").value("내용입니다"))

  	// DB 저장 확인
  	List<Post> posts = postRepository.findAll();
  	assertThat(posts).hasSize(1);
  	assertThat(posts.get(0).getTitle()).isEqualTo("제목입니다");
  }
  ```

- `게시글_단건조회_성공`

  ```java
  @Test
  void 게시글_단건조회_성공() throws Exception {
  	// given
  	Post savedPost = postRepository.save(Post.builder()
  		.title("제목입니다")
  		.content("내용입니다")
  		.author("작성자")
  		.build()
  	);

  	// when & then
  	mockMvc.perform(get("/posts/" + savePost.getId()))
  				.andExpect(status().isOk())
  				.andExpect(jsonPath("$.data.id").value(savedPost.getId()))
  				.andExpect(jsonPath("$.data.title").value("제목입니다"))
  				.andExpect(jsonPath("$.data.content").value("내용입니다"))
  				.andExpect(jsonPath("$.data.author").value("작성자")
  	);
  }
  ```

- `게시글_단건조회_실패_게시글이_없는_경우`

  ```java
  @Test
  void 게시글_단건조회_실패_게시글이_없는_경우() throws Exception {
  	// given
  	Long notExistId = 999L;

  	// when & then
  	mockMvc.perform(get("/posts/" + notExistId()))
  				.andExpect(status().isNotFound());
  }
  ```

---

## <mark color="#fbc956">테스트 코드 작성 시 주의사항</mark>

1. **테스트는 독립적으로 실행되어야 함**
   - 다른 테스트에 영향 받지 않아야 함
   - 테스트 순서에 의존하지 않아야 함
2. **테스트는 반복 가능해야 함**
   - 몇 번을 실행해도 같은 결과가 나와야 함
3. **테스트 이름은 명확해야 함**
   - 테스트가 무엇을 검증하는지 이름만 보고도 알 수 있어야 함
