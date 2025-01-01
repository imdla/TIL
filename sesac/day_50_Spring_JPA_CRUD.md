## <mark color="#fbc956">영속성 컨텍스트 (Persistence Context)</mark>

### 1. 영속성 컨텍스트

- 엔티티를 영구 저장하는 환경
- `EntityManager` 통해 엔티티 관리
- 애플리케이션과 데이터베이스 사이에서 중간 계층의 역할
- 트랜잭션 단위로 생성 및 관리 됨

### 2 . 특징

1. **1차 캐시**
   - 영속 상태의 엔티티는 모두 1차 캐시에 저장
   - 조회 시 먼저 1차 캐시에서 찾고, 없으면 DB에서 조회
2. **동일성 보장**
   - 같은 엔티티 조회 시 항상 같은 인스턴스 반환
3. **트랜잭션을 지원하는 쓰기 지연**
   - 트랜잭션 커밋 전까지 SQL을 보내지 않고 모아둠
   - 커밋하는 순간 모아둔 SQL을 DB에 보냄
4. **변경 감지 (Dirty Checking)**
   - 엔티티의 변경 사항을 자동으로 감지
   - 트랜잭션 커밋 시점에 변경된 엔티티를 찾아 UPDATE SQL을 생성
5. **지연 로딩**
   - 연관된 엔티티를 실제 사용하는 시점에 로딩하는 방식

### 3. 엔티티의 생명주기

- **비영속 (new/transient)**
  - 영속성 컨텍스트와 관계가 없는 상태
  ```java
  Post post = new Post();
  post.setTitle("title");
  post.setContent("content");
  ```
- **영속 (managed)**

  - 영속성 컨텍스트에 저장된 상태

  ```java
  // 영속성 컨텍스트에 저장
  em.persist(post);

  // 영속성 컨텍스트에서 조회
  Post findPost = em.find(Post.class, post.getId());
  ```

- **준영속 (detached)**

  - 영속성 컨텍스트에 저장되었다가 분리된 상태
  - 영속성 컨텍스트가 제공하는 기능을 사용할 수 없음

  ```java
  // 영속성 컨텍스트 종료
  em.close();

  // 특정 엔티티를 준영속 상태로 전환
  em.detach(post);

  // 영속성 컨텍스트 초기화
  em.clear();
  ```

- **삭제 (remove)**
  - 영속성 컨텍스트와 데이터베이스에서 삭제된 상태
  ```java
  em.remove(post);
  ```

---

## <mark color="#fbc956">EntityManager & EntityTransaction</mark>

### 1. EntityManager & EntityTransaction

- **EntityManager : 영속성 컨텍스트 관리**
  - 모든 작업 끝난 후 `close()` 호출 필요
- **EntityTransaction** : 트랜잭션 관리
  - 데이터 변경 작업이 끝난 후 반드시 `commit()` 호출
  - 예외 발생 시 반드시 `rollback()` 호출

### 2. 기본 세팅

- `PostRepository`
  - `@Repository` 활용해 bean 등록
  - `@RequireArgsConstructor` 활용해 `final` 붙은 필드만을 가진 생성자 생성

```java
@Repository
@RequireArgsContructor
public class PostRepository {
	@PersistenceUnit
	private final EntityManagerFactory emf;
}
```

### 3. Create

- `PostRepository`
  - `persist()` : 객체 저장

```java
public Post save(Post post) {
	EntityManager em = emf.createEntityManager();
	EntityTransaction tx = em.getTransaction();

	try {
		tx.begin();
		em.persist(post);
		tx.commit();
		return post;
	} catch (Exception e) {
		tx.rollback();
		throw e
	} finally {
		em.close();
	}
}
```

### 4. Read - 단일

- `PostRepository`
  - `find()` : 객체 조회
  - 조회 시 transaction 필요하지 않음

```java
public Post findById(Long id) {
	EntityManager em = emf.createEntityManager();

	try {
		return em.find(Post.class, id);
	} finally {
		em.close();
	}
}
```

> `객체.class` : 클래스의 메타 데이터를 나타내는 객체

### 5. Read - 전체

- `PostRepository`
  - `createQuery` 활용해 JPQL 사용

```java
public List<Post> findAll() {
	EntityManager em = emf.createEntityManager();

	try {
		return em.createQuery("SELECT p FROM Post p", Post.class)
						.getResultList();
	} finally {
		em.close();
	}
}
```

### 6. Update

- `PostRepository`
  - Dirty Checking 통해 명시적인 저장 없이 데이터 수집

```java
public Post update(Long id, Post updatedPost) {
	EntityManager em = emf.createEntityManager();
	EntityTransaction tx = em.getTransaction();

	try {
		tx.begin();
		Post post = em.find(Post.class, id);

		String title = updatedPost.getTitle();
		String content = updatedPost.getContent();

		post.update(title, content);

		tx.commit();
		return post;
	} catch (Exception e) {
		tx.rollback();
		throw e;
	} finally {
		em.close();
	}
}
```

- `PostService`

```java
public Post updatePost(Long id, Post updatedPost) {
	return postRepository.update(id, upsatedPost);
}
```

### 7. Delete

- `PostRepository`

```java
public void delete(Long id) {
	EntityManager em = emf.createEntityManager();
	EntityTransaction tx = em.getTransaction();

	try {
		tx.begin();
		Post post = em.find(Post.class, id);
		em.remove(post);
		tx.commit();
	} catch (Exception e) {
		tx.rollback();
		throw e;
	} finally {
		em.close();
	}
}
```

- `PostService`

```java
public void deletePost(Long id) {
	postRepository.delete(id);
}
```

### 8. JPQL

- JPA 엔티티 객체를 조회하는 객체지향 쿼리
- SQL과 비슷한 문법, 테이블이 아닌 엔티티 객체를 대상으로 쿼리
- 특정 데이터베이스에 종속적이지 않음
- **특징**

  - 기본적인 SELECT에서 문법적 차이가 크게 존재하지 않음

  ```java
  // SQL
  SELECT p.title, p.author FROM posts p WHERE p.category = "Tech";

  // JPQL
  SELECT p.title, p.author FROM Post p WHERE p.category = "Tech";
  ```

  - 객체지향적인 특성 (연관 관계 등)을 활용하는 경우 문법의 차이 존재

  ```java
  // SQL
  SELECT c.txt
  FROM posts p
  JOIN commtents c ON p.id = c.post_id
  WHERE p.title = "Hello World";

  // JPQL
  SELECT c.txt
  FROM Post p
  JOIN p.comments c
  WHERE p.title = "Hello World"l
  ```

### 9. Dirty Checking

- 영속성 컨텍스트에 관리되는 엔티티가 변경되었는지 자동으로 감지해, 트랜잭션이 커밋될 때 변경된 내용을 데이터베이스에 반영하는 메커니즘
- JPA가 영속 상태로 관리하는 엔티티에서만 동작
- 주로 데이터 수정 시 사용

---

## <mark color="#fbc956">@Transactional</mark>

### 1. `@Transactional`

(`import org.springframework.transaction.annotation.Transactional;` 을 활용)

- 클래스나 메서드에 적용해 트랜잭션 관리
- `readOnly` 속성으로 읽기 전용 트랜잭션 관리 가능
  - 조회 성늘 최적화

### 2. 동작 원리

- @Transaction이 붙은 메서드 호출 시 스프링 동작
  1. 트랙잭션 시작
  2. 메서드 실행
  3. 예외가 발생하지 않으면 커밋 (em.getTransaction().commit())
  4. 예외가 발생하면 롤백 (em.getTransaction().rollback())
  5. EntityManager 자동 close

### 3. @Transaction의 위치

- 일반적으로 `Service` 계층에서 관리
  - `Service` 계층 : 비즈니스 로직 수행, 하나의 비즈니스 로직은 여러 Repository 메서드 호출 가능
  - 모든 Repository 메서드가 하나의 트랜잭션으로 처리되어야 함
- `Service` 클래스에 `Transactional(readOnly = true)` 설정하고 쓰기 메서드에만 `@Transactional` 추가하는 방식으로 주로 활용

### 4. 기본 세팅

- `PostRepository`

```java
@Repository
@RequiredArgsContructor
public class PostRepository {
	private final EntityManager em;
}
```

- `PostService`

```java
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
	private final PostRepository postRepository;
}
```

### 5. Create

- `PostRepository`

```java
public Post save(Post post) {
	em.persist(post);
	return post;
}
```

- `PostService`
  - 쓰기 트랜잭션 위해 `@Transactonal` 추가

```java
@Transactionl
public Post createPost(Post post) {
	return postRepository.save(post);
}
```

### 6. Read - 단일

- `PostRepository`

```java
public List<Post> findAll() {
	return em.createQuery("SELECT p FROM Post p", Post.class)
					.getResultList();
}
```

- `PostService`

```java
public List<Post> readPosts() {
	return postRepository.findAll();
}
```

### 7. Read - 전체

- `PostRepository`

```java
public Post findById(Long id) {
	return em.find(Post.class, id);
}
```

- `PostService`

```java
public Post readPostById(Long id) {
	return postRepository.findById(id);
}
```

### 8. Update

- `PostRepository`

```java
public Post update(Long id, Post updatedPost) {
	Post post = em.find(Post.class, id);

	String title = updatedPost.getTitle();
	String content = updatedPost.getContent();

	post.update(title, content);
	return post;
}
```

- `PostService`
  - 쓰기 트랜잭션 위해 `@Transactonal` 추가

```java
@Transactional
public Post updatePost(Long id, Post updatedPost) {
	return postRepository.update(id, updatedPost);
}
```

### 9. Delete

- `PostRepository`

```java
public void delete(Long id) {
	Post post = em.find(Post.class, id);
	em.remove(post);
}
```

- `PostService`
  - 쓰기 트랜잭션 위해 `@Transactonal` 추가

```java
@Transactional
public void deletePost(Long id) {
	postRepository.delete(id);
}
```

---

### ☀️ 오늘의 배움

- 클라이언트 → 서버 → DB
- **컨트롤러 → 서비스 → 레포지토리**
- **JDBC**
  - MyBatis
    - SQL
  - ORM
    - JPA - 구현체 Hibernate
    - Spring Data JPA - JpaRepository
      - JpaRepository : 엔티티에 대한 CRUD를 자동화
- `findById()` : Optional 반환 (null에 대한 처리 필요)

- **트랜잭션** : 하나의 논리적인 작업 단위
  - 트랜잭션 처리
- **ACID 속성**

  - 원자성, 일관성, 격리성, 지속성

- **영속성 컨텍스트** : 엔티티를 영구 저장하는 환경
  - 트랜잭션 단위로 생성, 관리
  - Spring과 DB 사이에서 동작
- **서버 → (요청, 응답) → 데이터베이스**

  - 1차 캐시
    - Entity Manager - 중간에 저장했다가 똑같은 요청 필요 시 꺼내줌 (동일성 보장 위해)
  - 트랜잭션위한 쓰기 지연
    - 커밋 (성공)
    - 롤백 (실패)

- **엔티티 생명주기**
  1. 비영속 : 영속성 컨텍스트가 관리하지 않는 상태
  2. 영속
     - `.persist()` : 새롭게 추가
     - `.find()` : 조회
  3. 준영속 : 1차 캐시 삭제하는 것
  4. 삭제 : `.remove()`
- `.save` 같은 것들 엔티티 매니저 통해 진행됨

- shift + shift : 검색

- **EntityManager와 EntityTransaction**

  - 엔티티 매니저로 영속성 컨텍스트 관리
    - 작업 후 `close()` 호출
  - 엔티티 트랜잭션으로 트랜잭션 관리
    - 변경 작업 끝난 후 `commit()` 호출 → 성공
    - 예외 발생 시 `rollback()` 호출 → 실패

- `try-with-resources`
  - `close()` 자동으로 해줌
  ```java
  try (자원 관련된 정의);
  ```
- 클래스`.class`

  - `class<T>`
  - 클래스에 대한 메타 데이터

- **JPQL**
  - sql과 거의 동일 (객체지향스러운 SQL)
  - 테이블이 아닌 엔티티를 대상
  - more. 쿼리 DSL
- **더티 체킹**

  ![더티체킹.png](/sesac/assets/day50.png)

- **@Transactional**
  - 트랜잭션 관리
  - 메서드 단위로 트랙잭션 실행
