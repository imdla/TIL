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

- AND, OR
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
