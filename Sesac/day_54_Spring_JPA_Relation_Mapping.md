## <mark color="#fbc956">JPA 연관관계 매핑</mark>

### 1. JPA 연관관계 매핑

- 데이터베이스의 테이블 간 관계를 객체와 매핑하는 방법
- 객체의 참조와 테이블의 외래키를 매핑

### 2. JPA Entity와 데이터베이스 Table 비교

- **데이터베이스 테이블**
  - FK 사용해 연관 관계 설정
  - JOIN 통해 데이터 접근
- **JPA Entity**
  - 테이블과 같은 방식으로 FK를 가질 경우, 다른 Entity에 접근 불가
    - ex. Post 객체가 comment_id 가지고 있을 경우, post.getComment() 같은 객체 지향적인 설계는 불가능
  - Entity는 FK 가지는 것이 아니라, 참조관계 가짐
    - ex. Post 객체가 Comment 참조해 post.getComments() 통해 Comment 접근 가능

## <mark color="#fbc956">JPA 단방향, 양방향 연관관계</mark>

### 1. 연관관계의 방향

- 객체에서 참조가 있는 방향
- 테이블의 외래키는 양방향 조인 가능하지만, 객체의 참조는 한쪽 방향으로만 접근 가능
- 방향에 따라 단방향, 양방향 연관관계로 나뉨

### 2. 단뱡향 연관관계

- 한 쪽의 엔티티만 다른 엔티티를 참조하는 것
  - Post 에서는 Comment에 접근 불가

```java
@Entity
public class Comment {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;
}
```

```java
@Entity
public class Post {
	@Id
	@GeneratedValue
	private Long id;
}
```

### 3. 양방향 연관관계

- 양쪽 엔티티가 서로를 참조하는 것
  - Comment에서 Post에, Post에서 Comment에 접근 가능

```java
@Entity
public class Comment {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post; // 게시글 참조
}
```

```java
@Entity
public class Post {
	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(mappedBy = "post")
	private List<Comment> comments = new ArrayList<>(); // 댓글 목록 참조
}
```

---

## <mark color="#fbc956">연관관계 종류</mark>

### 1. 다대일 (N:1)

- 가장 많이 사용하는 연관관계
- 데이터베이스의 FK가 존재하는 테이블에 작성
- **관계 명시** : `@ManyToOne`
- **외래키 매핑** : `@JoinColumn`
  - name : 매핑할 외래키 컬럼명 (`필드명_id` 를 주로 활용)
  - nullable : null 허용 여부

```java
@Entity
public class Member {
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
}
```

### 2. 일대다 (1:N)

- 일대다 단방향은 권장하지 않음, 다대일의 상대방으로 사용됨
- **관계 명시** : `@OneToMany`
  - mappedBy : 양방향 관계에서 연관관계의 주인을 지정
    - 반대쪽 엔티티의 필드명을 값으로 설정
  - cascade : 영속성 전이 설정
  - orphanRemoval : 고아 객체 제거 여부
  - fetch : 연관된 엔티티의 로딩 전략을 설정

```java
@Entity
public class Team {
	@OneToMany(mappedBy = "team")
	private List<Member> members = new ArrayList<>();
}
```

### 3. 일대일 (1:1)

- **관계 명시** : `@OneToOne`
- 반대 편도 일대일 관계 가짐
- 주 테이블이나 대상 테이블 중 외래키 선택 가능

### 4. 다대다 (M : N)

- **관계 명시** : `@ManyToMany`
- 실무에서 사용하지 않는 것을 권장
- 대신, 중간 엔티티를 만들어 일대다, 다대일 관계로 만듦

---

## <mark color="#fbc956">연관관계의 주인</mark>

### 1. 연관관계의 주인

- **데이터베이스의 테이블**
  - 연관관계에서 외래키를 가진 쪽에 관계 관리, 두 테이블 간의 연결 담당
- **JPA Entity**

  - 객체 간의 양방향 연관관계를 표현 가능, 서로가 서로를 참조
  - 이 때, 무한 참조 문제나 일관성 문제 방지하기 위해 한쪽을 연관 관계의 주인으로 설정해 관계를 관리

- **주인**
  - 테이블에서 외래키는 가지는 쪽, N쪽
  - 주인만이 외래키 등록, 수정 가능
- **주인이 아닌 쪽**
  - 읽기만 가능, 연관 관계에 대한 등록, 수정 불가능
  - mappedBy를 통해 주인을 지정

### 2. 연관관계 편의 메소드

- 하나의 메소드로 두 객체의 관계를 한 번에 설정하는 메소드
- 주인 엔티티나 반대 엔티티 중 한 곳에만 작성

- **연관관계 편의 메소드 사용 이유**

  - JPA의 영속성 컨텍스트는 데이터베이스에 데이터를 반영하기 전까지 객체 상태로 존재
  - 연관관계의 주인만 설정하면, DB에는 정상적으로 반영됨
  - 영속성 컨텍스트에서는 순수 객체 상태로 존재하기 때문에 양쪽 모두 관계를 설정해주는 것이 안전

- `memberA`가 `teamA` 에 들어간다는 것 = `teamA` 의 멤버 목록에 `memberA` 가 추가되는 것
  - 이 때, `memberA` 에 `teamA` 을 추가하는 메서드에 `teamA` 의 멤버 목록에 `memberA` 를 추가하는 기능을 같이 작성해줌

```java
public class Member {
	public void joinTeam(Team team) {
		this.team = team;
		team.getMembers().add(this);
	}
}
```

### 3. 연관관계 매핑 시 주의할 점

- 연관관계는 단방향만으로 충분, 양방향의 경우 역참조가 발생할 때 작성
- 양방향 매핑 시 무한 루프를 조심해야 함
  - toString(), lombok, JSON 생성 라이브러리 등
- 엔티티를 API 응답으로 직접 반환하지 않음
- 컬렉션은 필드에서 초기화하는 것이 안전

---

### ☀️ 오늘의 배움

- Table에서는 JOIN을 통해 A→ B, B→A 가능
- Entity에서는 참조 사용

  - 참조 : 필드를 가지고 있는지 ?

- **@Entity**

  - 데이테베이스 스키마 연결
    - 테이블에서 사용하던 ID 대신 객체에 대한 **참조**를 사용하겠다
    - id느낌으로 사용하기 위해 장치 필요 → @JoinColumn(name = "post_id")

- **연관관계의 주인**

  - FK를 가진 곳이 주인 ! → CRUD 할 수 있음

- **서로 명시함 !**

  - Member에서 FK를 명시함 → @JoinColumn(name = "team_id")
  - Team에서 주인을 명시함 → @OneToMany(mappedBy = "team")
    - 읽기 전용, 컨트롤 권한의 양도
    - members는 team에 매여 있어서 직접 set으로 수정이 안됨

- **계층형 구조**
  - C, S, R 으로 패키지 나누어 분리
- **도메인 패키지 구조**

  - POST 따로, COMMENT 따로
  - DDD

- **요청 (댓글 작성)**
  - URL
    - `posts/{post_id}/comments`
  - method
    - post
  - data
    - content, post에 대한 정보
    - url
      - path variable
        - `post_id` : 포스트에 대한 정보
      - request params
    - body
      - request body
