> 💡 **한 줄 요약**
>
> 양방향 @OneToOne일 때 연관관계의 주인이 아닌 엔티티를 조회할 경우, 엔티티는 연관관계를 참조할 FK가 없기 때문에 연관관계의 존재 여부를 알지 못한다. 그래서 JPA는 null 혹은 프록시 객체 중 어떤 것으로 초기화할지 결정할 수 없어, 연관된 엔티티의 존재 여부를 확인하는 추가 쿼리를 실행하기 때문에 Lazy Loading이 동작하지 않는다.

### 1. 🤔 왜 사용하는가

- 양방향 `@OneToOne` 일 때

  - 연관관계의 주인이 아닌 엔티티를 조회할 경우 Lazy Loading이 동작하지 않음
  - JPA는 연관된 엔티티가 없으면 null로 초기화
    있으면 Lazy Loading이 설정되어 있을 경우 프록시 객체로 초기화
  - 데이터베이스의 테이블 관점의 경우
    - 연관관계 주인이 아닌 엔티티는 참조할 FK가 없음
      → 연관관계의 존재 여부 알지 못함
    - JPA는 null 혹은 프록시 객체 중 무엇으로 초기화할지 결정 X
      → 연관된 엔티티의 존재 여부를 확인하는 추가 쿼리 실행
      → Lazy Loading 동작하지 않음
  - 해결 : 단방향으로 모델링, Lazy Loading의 필요성 검토

  ```java
  @Entity(name = "users")
  public class User {

  	@Id
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
  	private Long id;

  	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
  	private Account account;
  }

  @Entity
  public class Account {

  	@Id
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
  	private Long id;

  	@OneToOne(fetch = FetchType.LAZY)
  	private User user;
  }
  ```

  ```java
  @Test
  void lazyTest() {
  	userRepository.save(new User());
  	userRepository.findById(1L).orElseThrow();
  }
  ```
