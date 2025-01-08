## <mark color="#fbc956">즉시로딩과 지연로딩</mark>

### 1. 즉딩 로딩 (EAGER)

- 엔티티 조회할 때 연관된 엔티티도 함께 조회
- **조회 시점**
  - 최초 엔티티를 조회하는 시점에 연관된 엔티티도 함께 로딩
  - 조회 쿼리에 조인이 발생
- **장점**
  - 연관된 엔티티를 사용할 때 추가 쿼리가 발생하지 않음
  - 한 번의 쿼리로 필요한 데이터를 모두 가져올 수 있음
- **단점**
  - 불필요한 데이터도 함께 로딩되어 성능이 저하될 수 있음
  - 연관관계가 복잡한 경우 예측하기 어려운 쿼리 발생 가능

### 2. 지연 로딩 (LAZY)

- 연관된 엔티티를 실제 사용할 때 조회
- **조회 시점**
  - 최초 엔티티 조회 시에는 프록시 객체만 로딩
  - 실제 연관 엔티티를 사용하는 시점에 추가 쿼리 발생
- **장점**
  - 불필요한 데이터를 로딩하지 않아 초기 로딩 속도가 빠름
  - 메모리를 효율적으로 사용 가능
- **단점**
  - 연관 엔티티를 사용하는 시점에 추가 쿼리를 발생
  - 여러 연관 엔티티를 사용할 때, N + 1 문제 발생 가능

### 3. 기본값

- `@OneToMany` : LAZY
- `@ManyToOne` : EAGAR
- `@ManyToMany` : LAZY
- `@OneToOne` : EAGER

### 4. 로딩 전략 기본 원칙

- 모든 곳에서 지연 로딩 사용
- 성능 최적화가 필요한 곳에서만 `fetch join` 사용

### 5. fetch join

- JPQL에서 성능 최적화를 위해 제공하는 특별한 기능
- 연관된 엔티티나 컬렉션을 한 번의 SQL로 함께 조회하는 방법
- 즉시로딩과는 다르게 필요한 시점에 명시적으로 조인 가능

### 6. @EntityGraph

- JPA가 제공하는 fetch join을 간단히 사용할 수 있는 기능
- attributePaths에 바로 fetch join할 속성 지정

---

## <mark color="#fbc956">☀️ 오늘의 배움</mark>

### **즉시 로딩 & 지연 로딩**

- **즉시 로딩**
  - 사용할 경우 good
  - 사용하지 않으면 리소스 낭비
- **지연 로딩**

  - 사용할 경우 쿼리 2개
  - 사용하지 않으면 good

- **🤔 이렇게 하면 좋을 텐데 !**
  - 사용안하면 → 안가져옴
  - 사용하면 → 가져옴
- **🤩 그러면 이렇게 하자 !**

  ⇒ 항상 lazy 하고 사용할 때 eager

  ⇒ lazy + fetch join 사용할 것임

  - LAZY는 엔티티에 설정하는 것 !
  - fetch join : JPQL 레포지토리 단에 설정하는 것 !

- **프록시 객체**
  - 메서드는 접근 가능
  - JPA 지연로딩 사용하기 위한 가짜 객체
  - 정의만 가져온 것 !

---

## <mark color="#fbc956">👀 비교해보기</mark>

### 0. 준비

- `/domain/example/ExampleController` 생성

  ```java
  package com.example.relation.domain.example;

  @RestController
  @RequestMapping("/example")
  @RequiredArgsConstructor
  public class ExampleController {

      private final PostRepository postRepository;

  }

  ```

### **1. getComments 사용할 경우**

- `ExampleController`
  ```java
  @GetMapping("/basic/{postId}")
  public void LoadingExample1(@PathVariable Long postId){
      Post post = postRepository.findById(postId).orElseThrow();
      int commentSize = post.getComments().size();
  }
  ```

> **LAZY**

- 쿼리 2개 발생
  ```
  Hibernate: select p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,p1_0.title,p1_0.updated_at from post p1_0 where p1_0.id=?
  Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id=?
  ```
- 사용 코드
  ```java
  @OneToMany(mappedBy = "post",  fetch = FetchType.LAZY)
  private List<Comment> comments = new ArrayList<>();
  ```

> **EAGER**

- 쿼리 1개 발생
  ```
  Hibernate: select p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,p1_0.title,p1_0.updated_at,c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from post p1_0 left join comment c1_0 on p1_0.id=c1_0.post_id where p1_0.id=?
  ```
- 사용 코드
  ```java
  @OneToMany(mappedBy = "post",  fetch = FetchType.EAGER)
  private List<Comment> comments = new ArrayList<>();
  ```

> 💡 **getComments를 사용할 경우**
>
> - LAZY는 comments 사용할 때 가져옴 → 쿼리 2개 발생
>   - post를 가져오는 쿼리 실행
>   - post.getComments() 통해 comments를 사용할 때 실행
> - EAGER는 사용유무 관계없이 한 번에 다 가져옴 → 쿼리 1개 발생 - post 가져오면서 연관된 comments를 같이 가져옴

### **2. getComments 사용하지 않는 경우**

- `ExampleController`
  ```java
  @GetMapping("/basic/{postId}")
  public void LoadingExample1(@PathVariable Long postId){
      Post post = postRepository.findById(postId).orElseThrow();
      List<Comment> comments = post.getComments();
  }
  ```

> **LAZY**

- 쿼리 1개 발생
  ```
  Hibernate: select p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,p1_0.title,p1_0.updated_at from post p1_0 where p1_0.id=?
  ```
- 사용 코드
  ```java
  @OneToMany(mappedBy = "post",  fetch = FetchType.LAZY)
  private List<Comment> comments = new ArrayList<>();
  ```

> **EAGER**

- 쿼리 1개 발생
  ```
  Hibernate: select p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,p1_0.title,p1_0.updated_at,c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from post p1_0 left join comment c1_0 on p1_0.id=c1_0.post_id where p1_0.id=?
  ```
- 사용 코드
  ```java
  @OneToMany(mappedBy = "post",  fetch = FetchType.EAGER)
  private List<Comment> comments = new ArrayList<>();
  ```

> 💡 **getComments를 사용하지 않는 경우**
>
> - LAZY는 comments 사용하지 않으니 post만 가져옴 → 쿼리 1개 발생
> - EAGER는 사용유무 관계없이 한 번에 다 가져옴 → 쿼리 1개 발생 - 사용하지 않는 불필요한 데이터도 조회

### 3. fetch join - getComments 사용하는 경우

- `fetch join` : 레포지토리에서 진행해 명시적으로 사용 가능

> **LAZY**

- 쿼리 2개 발생
  ```
  Hibernate: select p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,p1_0.title,p1_0.updated_at from post p1_0 where p1_0.id=?
  Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id=?
  ```
- 사용 코드
  ```java
  @GetMapping("/basic/{postId}")
      public void LoadingExample1(@PathVariable Long postId){
          Post post = postRepository.findById(postId).orElseThrow();
          int commentSize = post.getComments().size();
  }
  ```
  ```java
  @OneToMany(mappedBy = "post",  fetch = FetchType.LAZY)
  private List<Comment> comments = new ArrayList<>();
  ```

> **LAZY 일 때, fetch join**

- 쿼리 1개 발생
  ```
  Hibernate: select p1_0.id,p1_0.author,c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at,p1_0.content,p1_0.created_at,p1_0.title,p1_0.updated_at from post p1_0 left join comment c1_0 on p1_0.id=c1_0.post_id where p1_0.id=?
  ```
- 사용 코드
  ```java
  @GetMapping("/fetch/{postId}")
      public void LoadingExample2(@PathVariable Long postId){
          Post post = postRepository.findByIdWithCommentFetch(postId).orElseThrow();
          int commentSize = post.getComments().size();
      }
  ```
  ```java
  @OneToMany(mappedBy = "post",  fetch = FetchType.LAZY)
  private List<Comment> comments = new ArrayList<>();
  ```
  ```java
  @Query("SELECT p FROM Post p LEFT JOIN FETCH  p.comments WHERE p.id = :id")
  Optional<Post> findByIdWithCommentFetch(@Param("id") Long id);
  ```

> 💡 **fetch join - getComments 사용하는 경우**
>
> - LAZY는 comments 사용할 때 가져옴 → 쿼리 2개 발생
> - fetch join 사용 시 한 번의 SQL으로 함께 조회 → 쿼리 1개 발생

---

## <mark color="#fbc956">**N + 1 문제**</mark>

### **0. N+1 문제**

- post list 조회 + post마다 getComments
- 리스트 조회할 때, 연관 관계 있을 때, 한 번에 조회할 때

- comments를 포함한 post에 대한 List 조회할 경우
  - post 조회
  - post가 가진 댓글 1 조회
  - post가 가진 댓글 2 조회
  - …
    → (댓글 수 + 1)개의 쿼리가 날라갈 수 있음
- fetch join을 이용해 해결

### 1. **N+1 문제 :** EAGER과 LAZY 사용

- `ExampleController`
  ```java
  @GetMapping("/nplus1/basic")
  public void LoadingExample3(){
  		List<Post> posts  = postRepository.findAll();
  		posts.stream().map(PostWithCommentResponseDtoV2::from).toList();
  }
  ```
  - Post는 5개 가정

> **EAGER**

- 쿼리 6개 발생
  ```
  Hibernate: select p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,p1_0.title,p1_0.updated_at from post p1_0
  Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id=?
  Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id=?
  Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id=?
  Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id=?
  Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id=?
  ```
- 사용 코드
  ```java
  @OneToMany(mappedBy = "post",  fetch = FetchType.EAGER)
  private List<Comment> comments = new ArrayList<>();
  ```

> **LAZY**

- 쿼리 6개 발생
  ```
  Hibernate: select p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,p1_0.title,p1_0.updated_at from post p1_0
  Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id=?
  Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id=?
  Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id=?
  Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id=?
  Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id=?
  ```
- 사용 코드
  ```java
  @OneToMany(mappedBy = "post",  fetch = FetchType.LAZY)
  private List<Comment> comments = new ArrayList<>();
  ```

> 💡 **EAGER과 LAZY 사용**
>
> - LAZY는 comment 사용할 때 post마다 가져옴 → 쿼리 6개 발생
> - EAGER은 한꺼번에 PostList와 post마다 conmment 조회 → 쿼리 6개 발생
>
> ⇒ LAZY, EAGER에 상관없이 N+1개의 쿼리가 날라감

### 2. **N+1 문제 해결 :** FETCH JOIN 사용

> **FETCH JOIN**

- 장점 : LAZY, EAGER 사용할 때 N+1개 쿼리 날라가는 것과 달리 한 번의 쿼리로 실행
- 단점 : 동적 쿼리 작성 시 복잡해짐 → 해결 : 쿼리 DSL 사용
- 쿼리 1개 발생
  ```
  Hibernate: select p1_0.id,p1_0.author,c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at,p1_0.content,p1_0.created_at,p1_0.title,p1_0.updated_at from post p1_0 left join comment c1_0 on p1_0.id=c1_0.post_id
  ```
- 사용 코드
  ```java
  @GetMapping("/nplus1/fetch")
  public void LoadingExample4(){
  		List<Post> posts  = postRepository.findAllWithCommentFetch();
  		posts.stream().map(PostWithCommentResponseDtoV2::from).toList();
  }
  ```
  ```java
  @Query("SELECT p FROM Post p LEFT JOIN FETCH  p.comments")
  List<Post> findAllWithCommentFetch();
  ```

> 💡 **FETCH JOIN 사용**
>
> FETCH JOIN은 한번의 SQL에 한번에 가져옴 → 쿼리 1개 발생

### 3. **N+1 문제 해결 :** Entity Graph 사용

> **@Entity Graph**

- 장점 : JPQL없이 작성 가능
- 단점 : 커스터마이징 어려움
- 쿼리 1개 발생
  ```
  Hibernate: select p1_0.id,p1_0.author,c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at,p1_0.content,p1_0.created_at,p1_0.title,p1_0.updated_at from post p1_0 left join comment c1_0 on p1_0.id=c1_0.post_id
  ```
- 사용 코드
  ```java
  @GetMapping("/nplus1/entity-graph")
  public void LoadingExample5(){
  		List<Post> posts  =postRepository.findAllWithCommentEntityGraph();
  		posts.stream().map(PostWithCommentResponseDtoV2::from).toList();
  }
  ```
  ```java
  @EntityGraph(attributePaths = {"comments"})
  @Query("SELECT p FROM Post p")
  List<Post> findAllWithCommentEntityGraph();
  ```

> 💡 **Entity Graph 사용**
>
> Entity Graph는 fetch join 간단히 사용할 수 있음 → 쿼리 1개 발생

---

## <mark color="#fbc956">프록시</mark>

### 1. 프록시 객체

- JPA에서 지연로딩을 구현하기 위해 사용하는 기술
- 실제 엔티티 객체 대신 데이터베이스 조회를 지연할 수 있는 가짜 객체

- **특징**

  - 프록시 객체는 실제 객체의 상속 받아 만들어짐
  - 프록시 객체는 실제 객체의 참조를 보관
  - 프록시 객체를 통해 실제 객체를 사용하는 시점에 데이터베이스를 조회해 실제 객체 생성

- **동작 방식**

```java
@Entity
public class Member {
	@ManyToOne(fetch = FetchType.LAZY)
	private Team team;

	// 1. member 조회
	Member member = MemberRepository.findById(id);

	// 2. member.getTeam() 호출 시 프록시 객체 반환
	Team team = member.getTeam(); // 프록시 객체

	// 3. team.getName()처럼 실제 team 객체를 사용하는 시점에
	// 데이터베이스를 조회해 실제 team 객체를 생성
	String teamName = team.getName(); // SQL 실행
}
```
