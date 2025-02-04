## <mark color="#fbc956">엔티티 매핑</mark>

### 1. 엔티티 매핑

- Java의 class와 데이터베이스의 테이블을 연결시킴

- **@Entity**

  - JPA가 관리하는 객체임을 명시하는 어노테이션
  - 데이터베이스의 테이블과 매핑
  - @Table(name={table_name})을 활용해 이름이 다른 테이블과 연결시킬 수 있음

- **@Id**

  - 기본 키를 매핑하는 어노테이션

- **@GeneratedValue**
  - 기본 키 자동 생성해주는 어노테이션
  - strategy 속성
    - `IDENTITY` : 데이터베이스에 위임 (MySQL)
    - 사용하는 데이터베이스의 종류에 따라 선택 사용

### 2. 주의사항

- 엔티티에는 기본 생성자 필수
- final 필드, final 클래스 (상속 불가능한 클래스)는 사용 불가
- getter는 대부분 필드에 필요하지만, setter는 사용되는 경우에만 사용

  - setter의 경우 의미있는 메서드명으로 활용
    (OrderStatus → CancelOrder / Address → changeAddress)

- `mySite/Post`

```java
@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String content;

	protected Post() {}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
}
```

### 3. with Lombok

- **@Getter**
  - Getter를 생성
- **@Setter**
  - Setter를 생성 (Entity에서는 잘 사용되지 않음)
- @NoArgsConstructor(access = AccessLevel.PROTECTED)

  - 파라미터가 없는 기본 생성자 생성
  - `access = AccessLevel.PROTECTED` : 접근제어자를 proteced로 설정

- `mySite/Post`

```java
@Entity
@Getter
@NoArgsContructor(access = AccessLevel.PROTECTED)
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String content;
}
```

---

## <mark color="#fbc956">JpaRepository</mark>

### 1. JpaRepository

- Spring Data JPA에서 제공하는 데이터 접근 인터페이스
- Repository 인터페이스만 작성하면 실제 구현체는 Spring이 자동으로 생성함
- 기본적인 CRUD 메서드를 제공
- `JpaRepository<엔티티 타입, ID 타입>` 을 상속해 사용

- `mysite/PostRepository`
  ```java
  public interface PostRepository extends JpaRepository<Post Long> {
  }
  ```

### 2. 기본 제공 메서드

- **저장 및 수정**

  - `.save(instance)` : 단일 엔티티에 대해 있으면 수정, 없으면 저장

- **조회**

  - `.findAll()` : 모든 엔티티 조회, `List<T>` 반환
  - `.findById(id)` : 단일 엔티티 조회, `Optional<T>` 반환

- **삭제**

  - `.delete(instance)` : 단일 엔티티 삭제
  - `.deleteById(id)` : ID로 엔티티 삭제
    - 엔티티 없을 경우 `EmptyResultDataAccessException` 발생

- **유틸리티 메서드**
  - `.count()` : 엔티티 총 개수 반환
  - `.existsById(id)` : ID로 엔티티 존재 여부 확인s
