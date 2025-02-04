## <mark color="#fbc956">Spring Data JPA 이용한 CRUD</mark>

### 1. 준비

- PostController 와 PostService 생성

- `PostController`
  ```java
  @RestController
  @RequestMapping("/jpa/posts")
  public class PostController {
  	private final  PostService postService;

  	public PostController(PostService postService) {
  		this.postService = postService;
  	}
  }
  ```
- `PostService`
  ```java
  @Service
  public class PostService {
  	private final PostRepository postRepository;

  	public PostService(PostRepository postRepository) {
  		this.postRepository = postRepository;
  	}
  }
  ```

### 2. Create

- `PostService`
  ```java
  public Post createPost(Post post) {
  	return postRepository.save(post);
  }
  ```
- `PostController`
  ```java
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Post createPost(@RequestBody Post newPost) {
  	return postService.createPost(newPost);
  }
  ```

### 3. Read - 전체

- `PostService`
  ```java
  public List<Post> readPosts() {
  	return postRepository.findAll();
  }
  ```
- `PostController`
  ```java
  @GetMapping
  public List<Post> readPosts() {
  	return postService.readPosts();
  }
  ```

### 4. Read - 단일

- `PostService`
  - JpaRepository의 `findById` 는 Optional을 return
  ```java
  public Post readPostById(Long id) {
  	return postRepository.findById(id)
  		.orElseThrow(() -> new IllegalArgumentException("없는 id"));
  }
  ```
- `PostController`
  ```java
  @GetMapping("/{id}")
  public Post readPostById(@PathVariable Long id) {
  	return postService.readPostByid(id);
  }
  ```

### 5. Update

- `PostService`
  - 기존에 존재하는 instance에 대해 값 변경 후 `.save()` 를 수정
    - 기존 instance를 변경하기 위한 메서드 필요
  ```java
  public Post updatePost(Long id, Post updatedPost) {
  	Post post = postRepository.findById(id)
  		.orElseThrow(() -> new IllegalArgument("없는 id"));

  	String newTitle = updatedPost.getTitle();
  	String newContent = updatedPost.getContent();

  	post.update(newTitle, newContent);

  	return postRepository.save(post);
  }
  ```
- `Post`
  - Entity에 수정 위한 메서드 추가
  - 게시글의 수정은 title, content에 대해 함께 일어나는 것
    → 각 Setter 사용하는 것보다 함께 수정이 자연스러움
  ```java
  public Post update(String title, String content) {
  	this.title = title;
  	this.content = content;

  	return this;
  }
  ```
- `PostController`
  ```java
  @PostMapping("/{id}")
  public Post updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
  	return postService.updatePost(id, updatedPost);
  }
  ```

### 6. Delete

- `PostService`
  ```java
  public void deletePost(Long id) {
  	Post post = postRepository.findById(id)
  		.orElseThrow(() -> new IllegalArgumentation("없는 id"));
  	postRepository.delete(post);
  }
  ```
- `PostController`
  ```java
  @DeleteMapping("/{id}")
  @RequestStatus(Httptatus.NO_CONTENT)
  public void deletePost(@PathVariable Long id) {
  	postService.delete(id);
  }
  ```
