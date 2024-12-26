## <mark color="#fbc956">게시판 구현</mark>

### 1. 준비

> **`id`, `제목`, `내용` 갖는 간단한 게시글 구현**
>
> - 게시글 클래스
> - 게시글을 CRUD하는 행위에 대한 클래스

- `mysite/Post`

  ```java
  public class Post {
  	private Long id;
  	private String title;
  	private String content;

  	// 생성자 및 setter/getter 추가
  }
  ```

- `mysite/PostApi`

  ```java
  package com.example.demo.mysite;

  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.RestController;

  import java.util.ArrayList;
  import java.util.List;

  @RestController
  public class PostApi {
  	List<Post> posts = new ArrayList<>();
  	private Long id = 0L;
  }
  ```

### 2. `Create`

- `/posts/create`

  ```java
  @RestController
  public class PostApi {
  	List<Post> posts = new ArrayList<>();
  	private Long id = 0L;

  	@GetMapping("/posts/create")
  	public Post createPost() {
  		Post post = new Post(++id, "title", "content");
  		posts.add(post);
  		return post;
  	}
  }
  ```

### 3. `Read` - 전체 게시글

- `/posts`

  - 조회를 위한 기본 게시글 추가하는 초기화 블록 생성

  ```java
  @RestController
  public class PostApi {
  	List<Post> posts = new ArrayList<>();
  	private Long id = 0L;

  	{
  		posts.add(new Post(++id, "initial title", "initial content"));
  	}

  	@GetMapping("/posts/create")
  	public Post createPost() {
  		Post post = new Post(++id, "title", "content");
  		posts.add(post);
  		return post;
  	}

  	@GetMapping("/posts")
  	public List<Post> readPost() {
  		return posts;
  	}
  }
  ```

### 4. `Read` - 단일 게시글

- `/posts/{id}`

  - id를 위한 변수 필요

  ```java
  @RestController
  public class PostApi {
  	List<Post> posts = new ArrayList<>();
  	private Long id = 0L;

  	{
  		posts.add(new Post(++id, "initial title", "initial content"));
  	}

  	@GetMapping("/posts/create")
  	public Post createPost() {
  		Post post = new Post(++id, "title", "content");
  		posts.add(post);
  		return post;
  	}

  	@GetMapping("/posts")
  	public List<Post> readPost() {
  		return posts;
  	}

  	@GetMapping("/posts/{id}")
  	public Post readPostById(@PathVariable Long id) {
  		for (Post post : posts) {
  			if (post.getId().equals(id)) {
  				return post;
  			}
  		}
  		return null;
  	}
  }
  ```

- **`@PathVariable`**
  - URL 경로에 변수를 넣어 사용하는 방법
  - `{변수명}`으로 설정

1. 기본 사용법

   ```java
   @GetMapping("/posts/{id}")
   public String readPostById(@PathVariable Long id) {
   	return  "Post number: " + id;
   }
   ```

   ```java
   @GetMapping("/posts/{id}")
   public String readPostById(@PathVariable("id") Long id) {
   	return  "Post number: " + id;
   }
   ```

   - 적용되지 않는 경우
     - Settings → Build, Excution, Deployment → Complier → Java Complier → Additional ~
       - `-parameters` 추가
     - `build` 폴더 삭제

2. 변수명이 다른 경우

   ```java
   @GetMapping("/posts/{postId}")
   public String readPostById(@PathVariable("postId") Long id) {
   	return  "Post number: " + id;
   }
   ```

3. 여러 개의 PathVariable 사용

   ```java
   @GetMapping("/categories/{categoryId}/posts/{postId}")
   public String readPostById(
   	@PathVariable Long categoryId,
   	@PathVariable Long postId
   ) {
   	return  "Category number: " + categoryId + ", Post number: " + postId;
   }
   ```

4. 정규 표현식 사용

   - 숫자만 허용할 경우

   ```java
   @GetMapping("/posts/{id:[0-9]+}")
   public String readPostById(@PathVariable Long id) {
   	return  "Post number: " + id;
   }
   ```

### 5. `Update`

- `/posts/{id}/update`

  ```java
  @RestController
  public class PostApi {
  	List<Post> posts = new ArrayList<>();
  	private Long id = 0L;

  	{
  		posts.add(new Post(++id, "initial title", "initial content"));
  	}

  	@GetMapping("/posts/create")
  	public Post createPost() {
  		Post post = new Post(++id, "title", "content");
  		posts.add(post);
  		return post;
  	}

  	@GetMapping("/posts")
  	public List<Post> readPost() {
  		return posts;
  	}

  	@GetMapping("/posts/{id}")
  	public Post readPostById(@PathVariable Long id) {
  		for (Post post : posts) {
  			if (post.getId().equals(id)) {
  				return post;
  			}
  		}
  		return null;
  	}

  	@GetMapping("/posts/{id}/update")
  	public Post UpdatePost(@PathVariable Long id) {
  		for (Post post : posts) {
  			if (post.getId().equals(id)) {
  				post.setTitle("update title");
  				post.setContent("update content");
  				return post;
  			}
  		}
  		return null;
  	}
  }
  ```

### 6. `Delete`

- `/posts/{id}/delete`

  ```java
  @RestController
  public class PostApi {
  	List<Post> posts = new ArrayList<>();
  	private Long id = 0L;

  	{
  		posts.add(new Post(++id, "initial title", "initial content"));
  	}

  	@GetMapping("/posts/create")
  	public Post createPost() {
  		Post post = new Post(++id, "title", "content");
  		posts.add(post);
  		return post;
  	}

  	@GetMapping("/posts")
  	public List<Post> readPost() {
  		return posts;
  	}

  	@GetMapping("/posts/{id}")
  	public Post readPostById(@PathVariable Long id) {
  		for (Post post : posts) {
  			if (post.getId().equals(id)) {
  				return post;
  			}
  		}
  		return null;
  	}

  	@GetMapping("/posts/{id}/update")
  	public Post UpdatePost(@PathVariable Long id) {
  		for (Post post : posts) {
  			if (post.getId().equals(id)) {
  				post.setTitle("update title");
  				post.setContent("update content");
  				return post;
  			}
  		}
  		return null;
  	}

  	@GetMapping("/posts/{id}/delete")
  	public Post deletePost(@PathVarible Long id) {
  		Post removedPost = null;
  		for (Post post : posts) {
  			if (post.getId().equals(id)) {
  				removedPost = post;
  				break;
  			}
  		}
  		posts.remove(removedPost);
  		return null;
  	}
  }
  ```

---

## <mark color="#fbc956">RESTful한 게시판 구현</mark>

|                            | 기존               | RESTful                     |
| -------------------------- | ------------------ | --------------------------- |
| create                     | /posts/create      | posts + POST method         |
| read (전체)                | /posts             | /posts + GET method         |
| read (단일)                | /posts/{id}        | /posts/{id} + GET method    |
| update                     | /posts/{id}/update | /posts/{id} + PUT method, /posts/{id} + PATCH method |
| delete                     | /posts/{id}/delete | /posts/{id} + DELETE method |

### 1. HTTP 요청 메서드 (HTTP Request Methods)

- GET 요청 (`@GetMapping`)
  - 데이터 조회에 사용
  - URL에 데이터가 노출됨
- POST 요청 (`@PostMapping`)
  - 새로운 데이터 생성에 사용
  - Request Body에 데이터를 담아 전송
- PUT 요청 (`@PutMapping`)
  - 데이터 전체 수정에 사용
  - 기존 데이터를 완전히 대체
- PATCH 요청 (`@PatchMapping`)
  - 데이터 부분 수정에 사용
  - 필요한 필드만 업데이트
- DELETE 요청 (`@DeleteMapping`)
  - 데이터 삭제에 사용

```java
@RestController
public class PostApi {
	List<Post> posts = new ArrayList<>();
	private Long id = 0L;

	{
		posts.add(new Post(++id, "initial title", "initial content"));
	}

	@PostMapping("/posts")
	public Post createPost() {
		Post post = new Post(++id, "title", "content");
		posts.add(post);
		return post;
	}

	@GetMapping("/posts")
	public List<Post> readPost() {
		return posts;
	}

	@GetMapping("/posts/{id}")
	public Post readPostById(@PathVariable Long id) {
		for (Post post : posts) {
			if (post.getId().equals(id)) {
				return post;
			}
		}
		return null;
	}

	@PutMapping("/posts/{id}")
	public Post UpdatePost(@PathVariable Long id) {
		for (Post post : posts) {
			if (post.getId().equals(id)) {
				post.setTitle("update title");
				post.setContent("update content");
				return post;
			}
		}
		return null;
	}

	@DeleteMapping("/posts/{id}")
	public Post deletePost(@PathVariable Long id) {

    Post removedPost = null;
    for (Post post : posts) {
        if (post.getId().equals(id)) {
            removedPost = post;
            break;
        }
    }
    posts.remove(removedPost);
    return null;
	}
}
```

### 2. Postman


- 브라우저는 GET 요청만 처리 가능해 기존 방식으로 POST, PUT, PATCH, DELETE에 대한 요청을 처리할 수 없음
- 포스트맨 활용해 POST 요청 처리 가능

### 3. 공통 URL 경로 설정

- URL에 `posts` 중복 되어 있음
- `@RequestMapping` 을 클래스 레벨에 선언
  - URL 중복 줄일 수 있음
  - 하위 메서드들은 이 경로 뒤에 추가 경로 붙여 사용
  - 클래스 레벨과 같을 경우 생략 가능
- `@GetMapping` = `@RequestMapping(method = RequestMethod.GET)`
  - `@GetMapping("/posts")` = `@RequestMapping(value = "/posts", method = RequestMethod.GET)`

```java
@RestController
@RequestMapping("/posts")
public class PostApi {
	List<Post> posts = new ArrayList<>();
	private Long id = 0L;

	{
		posts.add(new Post(++id, "initial title", "initial content"));
	}

	@PostMapping
	public Post createPost() {
		Post post = new Post(++id, "title", "content");
		posts.add(post);
		return post;
	}

	@GetMapping
	public List<Post> readPost() {
		return posts;
	}

	@GetMapping("/{id}")
	public Post readPostById(@PathVariable Long id) {
		for (Post post : posts) {
			if (post.getId().equals(id)) {
				return post;
			}
		}
		return null;
	}

	@PutMapping("/{id}")
	public Post UpdatePost(@PathVariable Long id) {
		for (Post post : posts) {
			if (post.getId().equals(id)) {
				post.setTitle("update title");
				post.setContent("update content");
				return post;
			}
		}
		return null;
	}

	@DeleteMapping("/{id}")
	public Post deletePost(@PathVariable Long id) {

    Post removedPost = null;
    for (Post post : posts) {
        if (post.getId().equals(id)) {
            removedPost = post;
            break;
        }
    }
    posts.remove(removedPost);
    return null;
	}
}
```
