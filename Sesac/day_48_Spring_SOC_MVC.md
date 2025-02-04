## <mark color="#fbc956">관심사 분리 (Separation of Conserns, Soc)</mark>

### 1. 관심사 분리

- 하나의 프로그램을 각각의 독립된 관심사로 분리하는 설계 원칙
- 프로그램의 각 부분이 단일한 목적을 가지도록 구성
- 장점
  1. 코드의 유지보수성 향상 : 특정 부분을 수정할 경우 다른 부분에 영향 주지 않음
  2. 재사용성 향상 : 분리된 각 모듈을 다른 프로젝트에서도 사용 가능
  3. 테스트 용이성 : 각 부분을 독립적으로 테스트 가능
  4. 협업 용이 : 개발자들이 각자 맡은 부분을 독립적으로 개발 가능

> 코드를 여러 과정으로 구분

```java
// HTTP Request에 대한 부분
@PostMapping
@RequestStatus(HttpStatus.CREATED)
public Post createPost(@RequestBody Post newPost) {
	String title = newPost.getTitle();
	String content = newPost.getContent();

	// 비즈니스 로직 처리하는 부분
	if (title == null || title.isBlank()) {
		throw new RuntimeException("Input title");
	}

	if (content == null || content.isBlank()) {
		throw new IllegalArgumentException("Input Content");
	}

	// 데이터 생성에 대한 부분
	Post post = new Post(++id, title, content);
	posts.add(post);
	return post
}
```

---

## <mark color="#fbc956">3계층 아키텍처 (3-Tier Architecture)</mark>



### 1. 3계층 아키텍처

- 소프트웨어 시스템을 Presentation Layer, Business Layer, Data Layer로 나누는 설계 패턴
- 각 계층은 독립적으로 개발 및 유지보수 가능
- 서로 명확히 분리되어 있어 변경 시 영향 최소화 가능

### 2. Presentation Layer (표현 계층)

- 사용자와의 상호작용 담당
- 데이터의 입력 받아 Business Layer에 전달
- Spring에서 Controller가 해당

### 3. Business Layer (비즈니스 계층)

- 비즈니스 로직과 애플리케이션의 핵심 기능 처리
- 데이터의 유효성 검증하고 Presentation Layer와 Data Layer 중계
- Spring에서 Servive가 해당

### 4. Data Access Layer (데이터 접근 계층)

- 데이터베이스와의 상호작용 담당
- 데이터를 저장, 조회, 업데이트, 삭제하는 기능 제공
- Spring에서 Repository가 해당

---

## <mark color="#fbc956">사용</mark>



### 1. 준비

- **파일 생성**
  - `PostController` : HTTP 요청 담당
  - `PostService` : 비즈니스 로직 담당
  - `PostRepository` : 데이터에 대한 CRUD 담당
- **요청 - 응답 로직**
  1. 클라이언트가 요청 보냄
  2. Controller가 요청 받음
  3. Service에서 비즈니스 로직 처리
  4. Repository에서 데이터 처리
  5. Controller가 응답 반환
- **파일 로직**
  - `PostController` 에서 `PostService` 호출 → `PostService` 에서 `PostRepository` 호출

### 2. Create

- `PostRepository` : 게시글 생성에 대한 로직 담당

```java
import java.util.ArrayList;
import java.util.List;

public class PostRepository {
	private List<Post> posts = new ArrayList<>();
	private Long id = 0L;

	public Post save(Post newPost) {
		String title = newPost.getTitle();
		String content = newPost.getContent();

		Post post = new Post(++id, title, content);
		posts.add(post);
		return post;
	}
}
```

- `PostService` : 비즈니스 로직 담당
  - 여기서 Validation 체크, `PostRepository` 호출
    (`PostRepository` 사용 위해 컴포지션 사용)

```java
public class PostService {
	private PostRepository postRepository = new PostRepository();

	public Post createPost(Post newPost) {
		String title = newPost.getTitle();
		String content = newPost.getContent();

		if (title == null || title.isBlank()) {
			throw new RuntimeException("Input title");
		}

		if (content == null || content.isBlank()) {
			throw new IllegalArgumentException("Input content");
		}

		return postRepository.save(newPost);
	}
}
```

- `PostController` : HTTP 요청 담당
  - `PostService` 호출
    (`PostService` 사용 위해 컴포지션 사용)

```java
@RestController
@RequestMapping("/posts")
public class PostController {
	private PostService postService = new PostService();

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Post createPost(@RequestBody Post newPost) {
		return postService.createPost(newPost);
	}
}
```

### 3. Read - 전체 게시글

- `PostRepository`

```java
public List<Post> findAll() {
	return posts;
}
```

- `PostService`

```java
public List<Post> readPosts() {
	return postRepository.findAll();
}
```

- `PostController`

```java
@GetMapping
public List<Post> readPost() {
	return postService.readPosts();
}
```

### 4. Read - 단일 게시글

- `PostRepository`

```java
public Post findById(Long id) {
	for (Post post : posts) {
		if (post.getId().equals(id)) {
			return post;
		}
	}
	return null;
}
```

- `PostService`

```java
public Post readPostById(Long id) {
	Post post = postRepository.findById(id);
	if (post == null) {
		throw new IllegalArgumentException("id without");
	}
	return post;
}
```

- `PostController`

```java
@GetMapping("/{id}")
public Post readPostById(@PathVariable Long id) {
	return postService.readPostById(id);
}
```

### 5. Update

- `PostRepository`

```java
public Post modify(Long id, Post updatedPost) {
	String newTitle = updatedPost.getTitle();
	String newContent = updatedPost.getContent();

	for (Post post : posts) {
		if (post.get().equals(id)) {
			post.setTitle(newTitle);
			post.setContent(newContent);
			return post;
		}
	}
	return null;
}
```

- `PostService`

```java
public Post updatePost(Long id, Post updatedPost) {
	Post post = postRepository.findById(id);
	checkPostIsNull(post);
	validatePostData(updatedPost);
	return postRepository.modify(id, updatedPost);
}

public void checkPostIsNull(Post post) {
	if (post == null) {
		throw new IllegalAtgumentException("id without");
	}
}

public void validatePostData(Post post) {
	if (title == null || title.isblank()) {
		throw new RuntimeException("Please Input title");
	}

	if (content == null || content.isBlank()) {
		throw new IllegalArgumentException("Please Input content");
	}
}
```

- `PostController`

```java
@GetMapping("/{id}")
public Post updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
	return postService.updatePost(id, updatedPost);
}
```

### 6. Delete

- `PostRepository`

```java
public void delete(Long id) {
	posts.remove(post);
}
```

- `PostService`

```java
public Post deletePost(Long id) {
	Post post = postRepository.findById(id);
	if (post == null) {
		throw new IllegalArgumentException("id without");
	}
	postRepoditory.delete(post);
}
```

- `PostController`

```java
@GetMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deletePost(@PathVariable Long id) {
	postService.deletePost(id);
}
```

---

## <mark color="#fbc956">Spring MVC</mark>


### 1. Spring MVC

- Spring Framework에서 제공하는 웹 모듈
- MVC(Model-View-Controller) 디자인 패턴을 기반으로 웹 애플리케이션을 구축
- 구성 요소를 분리해 개발해 무거운 프로젝트도 효율적으로 관리 가능

### 2. MVC 패턴의 구성요소

- **Model**
  - 애플리케이션의 데이터와 비즈니스 로직 담당
  - Service와 Repository가 Model에 해당
- **View**
  - 사용자에게 보여지는 화면 담당
  - HTML, JSP, Thymeleaf 등이 View에 해당
  - REST API 사용할 경우 JSON 형태의 데이터가 View가 됨
- **Controller**
  - 사용자의 요청을 받아 Model과 View 연결하는 역할
  - @Controller 또는 @RestController 어노테이션 사용

> Spring MVC 관련 어노테이션 : `@RestController`, `@RequestMapping`, `@PathVariable`
