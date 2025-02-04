## <mark color="#fbc956">Validation</mark>

### 1. Validation

- 데이터의 유효성을 검사하는 과정
- 잘못된 데이터가 시스템에 유입되는 것 방지

> `throw` 통해 구현한 간단한 Validation

- `/posts` - POST method

```java
@PostMapping
@RequestStatus(HttpStatus.CREATED)
public Post createPost(@RequestBody Post newPost) {
	String title = newPost.getTitle();
	String content = newPost.getContent();

	if (title == null || title.isBlank()) {
		throw new RuntimeException("Input title");
	}

	if (content == null || content.isBlank()) {
		throw new IllegalArgumentException("Input content");
	}

	Post post = new Post(++id, title, content);
	posts.add(post);
	return post;
}
```

- 에러 throw될 경우

  - method 호출하는 상위 어딘가에서 반드시 try catch 해줘야 함
  - 그렇지 않을 경우 프로그램이 종료됨
  - Spring에서 기본적으로 예외 처리 해줌
  - `ExceptionHandler` , `ControllerAdvice` 등을 통해 에러 핸들링 가능

- `/posts/{id}` - PUT method

```java
@PutMapping("/{id}")
public Post updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
	String newTitle = updatedPost.getTitle();
	String newContent = updatedPost.getContent();

	if (newTitle == null || newTitle.isBlank()) {
		throw new RuntimeException("Input title");
	}

	if (newContent == null || newContent.isBlank()) {
		throw new IllegalArgumentException("Input content");
	}

	for (Post post : posts) {
		if (post.getId().equals(id)) {
			post.setTitle(newTitle);
			post.setContent(newContent);
			return post;
		}
	}
	return null;
}
```
