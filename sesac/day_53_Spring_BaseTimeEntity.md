## <mark color="#fbc956">BaseTimeEntity</mark>

### 1. BaseTimeEntity

- 모든 엔티티에서 공통으로 사용하는 생성시간, 수정시간을 관리하는 클래스

```java
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;
}
```

### 2. @MappedSuperclass

- JPA의 엔티티 클래스가 상속받을 수 있는 매핑 정보를 포함하는 클래스를 지정하는 어노테이션
- 실제 테이블과 매핑되지 않음
- 자식 클래스(엔티티)에 매핑 정보만 제공
- 등록일, 수정일 같이 여러 엔티티에서 공통으로 사용하는 필드를 모을 때 사용

### 3. @EntityListeners

- 엔티티의 변화를 감지하는 리스너를 등록하는 어노테이션
- 엔티티의 생명주기 이벤트(저장, 수정 등)에 대해 이벤트 발생
- 이벤트에 대해 실행할 메서드를 지정해 호출
- @EntityListners(AuditingEntityListener.class)
  - `AuditingEntityListner.class` 에 이벤트에 대해 실행할 메서드가 정의되어 있음

### 4. @CreateDate

- 엔티티가 생성되어 저장될 때 시간이 자동으로 저장됨

### 5. @LastModifiedDate

- 엔티티가 수정될 때 시간이 자동으로 저장됨

---

## <mark color="#fbc956">JPA Auditing</mark>

### 1. JPA Audting

- 엔티티의 생성과 수정을 추적하는 기능
- 생성 시간, 수정 시간 / 생성자, 수정자 자동 기록을 도와줌

### 2. @EnableJpaAuditing

- JPA Auditing 기능을 활성화하는 어노테이션

> 메인 애플리케이션에 적용

```java
@EnableJpaAuditing
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
```

> 설정 파일을 생성해 적용

```java
@Configuration
@EnableJpaAuditing
public class JpaConfig {

}
```

---

## <mark color="#fbc956">실습</mark>

- `BaseTimeEntity`

  ```java
  @Getter
  @MappedSuperclass
  @EntityListeners(AuditingEntityListener.class)
  public abstract class BaseTimeEntity {
  	@CreatedDate
  	@Column(updatable = false)
  	private LocalDateTime createdAt;

  	@LastModifiedDate
  	private LocalDateTime updatedAt;
  }
  ```

- **설정**

  - `DemoApplication`

  ```java
  @SpringBootApplication
  @EnableJpaAuditing
  public class DemoApplication {
  	public static void main(String[] args) {
  		SpringApplication.run(DempApplication.class, args);
  	}
  }
  ```

- **entity 변경**

  - `Post`

  ```java
  public class Post extends BaseTimeEntity {
  }
  ```

- 관련 DTO 수정

  - `PostResponseDto`

  ```java
  @Getter
  @Builder
  public class PostResponseDto {
  	private final long id;
  	private final String title;
  	private final String content;
  	private final String author;
  	private LocalDateTime createdAt;
  	private LocalDateTime updatedAt;

  	public static PostResponse from(Post Entity) {
  		return PostResponseDto.builder()
  						.id(entity.getId())
  						.title(entity.getTitle())
  						.content(entity.getContent())
  						.author(entity.getAuthor())
  						.createdAt(entity.getCreatedAt())
  						.updatedAt(entity.getUodatedAt())
  						.build();
  	}
  }
  ```

---

### ☀️ 오늘의 배움



- **연관관계 복습하기**
- **작성일 추가**

  - Entity에 대한 extends
    (= 테이블을 extends)
    - 필드 추가
    - 1:1 관계로 연관

- @MappedSuperclass
  - 필드를 추가하는 방법
- @EntityListeners(콜백)
  - 엔티티에 대한 생성, 수정, 삭제, 변경에 대한 콜백
- @Column(updatable = false)
  - false는 수정 불가를 의미
- @SpringBootApplication

  - spring boot에 대한 configuration

- 설정 클래스 (ex. `JpaConfig.java`)

  - @Configuration
  - jpa 설정이 많을 때, jpa 설정에 대한 분리할 때 효과적

- @PrePersist : 저장 전에
- @PreUpdate : 업데이트 전에

- **Entity는 DB와 밀접 연관**
  - `spring.jpa.hibernate.ddl-auto=update`
    - 위의 hibernate를 통해 ddl을 자동으로 업데이트하도록 함
    - 새로 create되는 것에 적용됨 (과거의 것은 기존 유지)
  - @Column
    - 엔티티의 필드와 테이블 컬럼 매핑
    - 속성 정의
- @Table

- **JPQL**
  - SQL의 Java 버전
  - 엔티티명 사용, 대소문자 구분 함
  - 별칭 필수
- **파라미터 바인딩 `:`**

  - 동적인 변수 바인딩

- **Spring Data JPA의 JPQL**
  - @Query 이용해 나만의 메서드 생성 가능
  - @Param 이용해 동적 파라미터 받아옴
- **Spring Data JPA의 쿼리 메서드**

  - 메서드 이름만으로 쿼리 실행

- **쿼리 DSL**
  - 간편한 방법
  - 빌더한것 처럼 쿼리 작성 가능
