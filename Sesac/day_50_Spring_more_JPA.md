## <mark color="#fbc956">@Column 어노테이션</mark>

### 1. `@Column`

- JPA에서 엔티티의 필드와 테이블의 컬럼을 매핑할 때 사용

### 2. 주요 속성

- **name** : 컬럼명 지정

  - 기본값 : 필드명과 동일
  - ex. `@Column(name = "user_name")`

- **nullable** : NULL 허용 여부

  - 기본값 : true
  - ex. `@Column(nullable = false)`

- **length** : 문자열 길이 제한

  - 기본값 : 255
  - ex. `@Column(length = 100)`

- **unique** : 유니크 제약 조건 설정

  - 기본값 : false
  - ex. `@Column(unique = true)`

- **columnDefinition** : 컬럼 정의를 직접 지정
  - ex. `@Column(columnDefinition = "TEXT")`

### 3. Validation과의 차이

- @Column
  - DB 테이블의 스키마를 정의
  - 애플리케이션 실행 시점에 DB 테이블 생성에 영향
  - DB 레벨의 제약조건 설정
- Validation
  - 애플리케이션 실행 중 데이터 검증
  - 요청 데이터가 서비스 로직에 도달하기 전에 검증
  - 비즈니스 로직 레벨의 제약조건 설정

### 4. 기타 어노테이션

- @Enumerated(EnumType.STRING)
  - 자바 enum 타입을 엔티티 클래스에서 사용할 때 사용
  - EnumType.STRING : enum의 이름을 DB에 저장

```java
public eum Status {
	ACTIVE,
	INACTIVE,
	PENDING,
	BANNED
}

@Entity
public class SomeEntity {
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status;
}
```

---

## <mark color="#fbc956">JPQL</mark>

### 1. JPQL

- 테이블명이 아닌 엔티티명 사용
- 엔티티명은 대소문자 구분
- JPQL 키워드는 대소문자 구분하지 않음
- 별칭이 필수
- 묵시적 조인보다 명시적 조인 권장

> JPQL 사용

```java
// 기본 SELECT
"SELECT m FROM Member m"

// WHERE
"SELECT m FROM Member m WHERE m.age > 18"

"SELECT m FROM Member m WHERE m.age > 18 AND m.username LIKE 'kim%'"

// ORDER BY
"SELECT m FROM Member m ORDER BY m.age ASC"

// GROUP BY
"SELECT m.team.name, COUNT(m) FROM Member m GROUP BY m.team.name"
```

### 2. 파라미터 바인딩

- JPQL 쿼리에 값을 안전하게 넣는 방법
- `:` 변수명의 위치에 변수 넣을 수 있음

```java
String username = "kim";
String jpql = "SELECT m FROM Member m WHERE m.username = :username";

List<Member> members = em.createQuery(jpql)
				.setParameter("username", username)
				.getResultList();
```

> Bad Example

```java
String username = "kim";

String jpql = "SELECT m FROM Member m WHERE m.username = '" + username + "'";
```

### 3. 결과 조회 API

- `getResultList()`
  - 결과가 하나 이상일 때 사용
  - 결과 없을 경우 빈 컬렉션 반환
- `getSingleResult()`
  - 결과가 정확히 하나일 때 사용
  - 결과가 없으면 NoResultException
  - 결과가 둘 이상이면 NonUniqueResultException

### 4. JpaRepository에서의 JPQL

- **정의** : 추상 메서드에 `@Query` 어노테이션 사용

  ```java
  public interface PostRepository extends JpaRepository<Post, Long> {
  	@Query("SELECT p FROM Post p")
  	List<Post> findAllPost();
  }
  ```

- **파라미터 바인딩** : 메서드의 parameter에 작성

  ```java
  @Query("SELECT p FROM Post p WHERE p.author = :author")
  List<Post> findByAuthor(@Param("author") String author);
  ```

- **수정, 삭제** : `@Modifying` 어노테이션
  ```java
  @Modifying
  @Query("UPDATE Post p SET p.title = :title WHERE p.id = :id")
  int updateTitle(@Param("id") Long id, @Param("title") String title)
  ```

---

## <mark color="#fbc956">Spring Data JPA의 쿼리 메서드</mark>

### 1. Spring Data JPA의 쿼리 메서드

- 메서드의 이름으로 쿼리를 생성하는 기능
- JpaRepository 인터페이스에서 제공
- 별도의 JPQL 작성 없이 메서드 이름만으로 쿼리 실행 가능

> Entity

```java
@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTIFY)
	private Long id;

	private String username;
	private String email;
	private int age;
	...
}
```

- `JpaRepository` 를 extends한 `Repository`에 추상 메서드 추가할 경우 자동으로 쿼리 생성

```java
public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByEmail(String email);
	List<Member> findByAgeGreaterThan(int age);
	boolean existsByUsername(String username);
}
```

### 2. 기본 사용법

- `findBy` : SELECT 쿼리를 자동으로 생성

  - `findBy{ field }()` : field로 조회

- And, Or
  - `findByUsernameAndAge(String username, int age)`
- Between
  - `findByAgeBetween(int startAge, int endAge)`
- LessThan, GreaterThan
  - `findByAgeLessThan(int age)`
- Like
  - `findByUsernameLike(String username)`
  - argument에 와일드카드 사용 가능
- Containing
  - `findByEmailContaining(String emailDomain)`
- OrderBy
  - `findByAgeOrderByUsernameAsc(int age)`
- boolean
  - `findByFiedFalse()`
  - `findByFiedTrue()`
  - field의 값에 따라 조회
