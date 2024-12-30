## <mark color="#fbc956">스프링 컨테이너</mark>

### 1. 스프링 컨테이너

- 스프링 빈의 생명주기를 관리하는 핵심 요소
- 객체의 생성, 관리, 제거 담당
- 스프링 빈을 모아두고 관리하는 장소

### 2. 스프링 빈 (Spring Bean)

- 스프링 컨테이너가 관리하는 자바 객체를 의미
- **빈 등록 방법**
  - 컴포넌트 스캔과 @Component 어노테이션을 통한 자동 등록
  - 자바 설정 클래스에서 @Bean을 통한 수동 등록

### 2. 스테레오타입 어노테이션

- @Component : 일반적인 스프링 빈으로 등록하는 기본 어노테이션
- @Component를 포함하는 세부 어노테이션
  - @Controller : 웹 MVC 컨트롤러로 사용되는 클래스 지정
  - @RestController : REST API 컨트롤러로 사용되는 클래스 지정
  - @Service : 비즈니스 로직을 처리하는 서비스 계층의 클래스 지정
  - @Reposiory : 데이터 접근 계층의 클래스 지정
  - @Configuration : 설정을 위한 클래스 지정

---

## <mark color="#fbc956">제어의 역전 (Inversion of Control, IoC)</mark>

### 1. 제어의 역전

- 객체의 생성과 관리를 개발자가 아닌 프레임워크가 담당하는 것을 의미
- 기존에는 개발자가 직접 객체를 생성하고 관리했지만, 스프링에서는 컨테이너가 대신 관리
- IoC 구현체 : DI

### 2. 의존성 주입 (Dependency Injection, DI)

- 객체가 필요로 하는 의존성을 외부에서 주입하는 방식
  - 의존성 : 한 클래스가 다른 클래스를 필요로 하는 것
    (`PostService` 가 `PosrRepository` 를 필요로 하는 경우)
- 스프링이 자동으로 객체를 생성하고 주입해줌
- **기존 방식(컴포지션)과 차이**

  ```java
  // 기존 방식 (컴포지션)
  public class PostService {
  	private PostRepository postRepository = new PostRepository();
  }

  // DI 방식
  @Service
  public class PostService {
  	private final PostRepository postRepository;

  	public PostService (PostRepository postRepository) {
  		this.postRepository = postRepository;
  	}
  }
  ```

- **DI의 장점**

  1. 객체 간의 결합도 낮춤

     ```java
     // DI 없이 직접 객체 생성 - 강한 결합
     public class UserService {
     	// UserRepository 구현체를 직접 생성 - 변경이 어려움
     	private UserRepository repository = new MySQLUserRepository;
     }

     // DI 사용 - 느슨한 결합
     public class UserService {
     	// 인터페이스에 의존
     	private final UserRepository repository;

     	// 어떤 구현체든 주입 가능 (MySQL, MongoDB, Redis 등)
     	public UserService(UserRepository repository) {
     		this.repository = repository;
     	}
     }
     ```

  2. 테스트 용이

     ```java
     // 테스트가 어려운 코드
     public class UserService {
     	private UserRepository repository = new MySQLUserRepository;
     	// 실제 DB 연결이 필요해서 테스트가 어려움
     }

     // 테스트하기 쉬운 코드
     @Test
     void UserTest() {
     	// 실제 DB 대신 가짜 객체 사용 가능
     	UserRepository mockRepository = mock(UserRepository.class);

     	UserService service = new UserService(mockRepository);
     	User user = service.getUrl(1L);

     	assertEquals("test", user.getName());
     }
     ```

  3. 코드의 재사용성 높아짐

## @Autowired 어노테이션

---

### 1. @Autowired

- 스프링 컨테이너가 자동으로 의존성을 주입할 때 사용하는 어노테이션
- 특징 : 타입을 기준으로 의존성 주입
  ```java
  @Autowired
  private UserRepository userRepository;
  ```
- **@Autowired 사용 가능 위치**
  - 생성자 (생성자가 하나일 경우, 생략 가능)
  - 수정자 (setter)
  - 필드
- **@Autowired 동작 원리**
  - 주입 시점
    1. 스프링 컨테이너가 빈을 생성
    2. @Autowired가 붙은 위치에 의존성을 주입
  - 주입 대상 탐색
    1. 타입이 같은 빈을 찾음
    2. 여러 개의 빈이 있을 경우
       - @Primary가 있는 빈을 주입
       - @Qualifier로 지정된 빈을 주입
       - 이름이 같은 빈을 주입
    3. 위 조건으로 구분이 안될 경우 오류 발생

---

## <mark color="#fbc956">의존성 주입(DI)의 3가지 방식</mark>

### 1. 생성자 주입 (Constructor Injection)

- 생성자를 통해 의존성을 주입하는 방식
- 특징
  1. 불변성 보장 : 한 번 주입된 후 변경 불가
  2. 필수 의존성에 사용 : 객체 생성 시점에 모든 의존성 주입
  3. 순환 참조 방지 : 컴파일 시점에 순환 참조 확인 가능
  4. 생성자가 하나일 경우 @Autowired 생략 가능

```java
@Service
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.useRepositoyry = useReository;
	}
}
```

### 2. 수정자 주입 (Setter Injection)

- setter 메서드를 총해 의존성을 주입받는 방식
- 특징
  - 선택적 의존성에 사용 : 없어도 객체 생성 가능
  - 의존성 변경 가능 : 런타임에 의존성을 보장할 수 없음

```java
@Service
public class UserService {
	public class UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}

```

### 3. 필드 주입 (Field Injection)

- 필드에 직접 의존성 주입하는 방식
- 특징
  - 코드 간결
  - 외부에서 변경 불가능
  - 테스트하기 어려움
  - 순환 참조 발생 가능

```java
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
}
```

### 4. 순환 참조 (Circular Dependency)

- A가 B를 참조하고, B가 A를 참조하는 경우
- 스프링 애플리케이션이 실행되지 않음

```java
@Service
public class AService {
	private final BService bService;

	public AService(BService bService) {
		this.bService = bService;
	}
}

@Service
public class BService {
	private final AService aService;

	public BService(AService aService) {
		this.aService = aService;
	}
}
```

### 사용

- `PostController`

  - 기존
    ```java
    @RestConroller
    @RequestMapping("/posts")
    public class PostController {
    	PostService postService = new PostService();
    	...
    }
    ```
  - 변경

    ```java
    @RestController
    @RequestMapping("/posts")
    public class PostController {
    	private final PostService postService;

    	public PostController(PostService postService) {
    		this.postService = postService;
    	}
    	...
    }
    ```

- `PostService`

  - 기존
    ```java
    public class PostService {
    	PostRepository postRepository = new PostRepository();
    }
    ```
  - 변경

    ```java
    @Service
    public class PostService {
    	private final PostRepository postRepository;

    	public PostService(PostRepository postRepository) {
    		this.postRepository = postRepository;
    	}
    }
    ```

- `PostRepository`
  - 기존
    ```java
    public class PostRepository {
     ...
    }
    ```
  - 변경
    ```java
    @Repository
    public class PostRepository {
    	...
    }
    ```

---

### ☀️ 오늘의 배움

- **스프링 컨테이너**
  - 스프링 빈 관리
- **스프링 빈**
  - 클래스(인스턴스)
  - @Component : 스프링 빈 등록
  - @Bean : 수동 등록
- **싱글 톤 패턴**
  - 한 클래스에서 하나의 인스턴스만 생성
  - 의존성을 가진 객체도 변하지 않음(`final`)
- **제어의 역전**
  - (일반적으로 프레임워크를 가져와서 개발자가 흐름을 만들어감)
  - 객체 생성, 관리를 개발자 아닌 프레임워크가 담당
- **의존성 주입 (DI)**
  - 객체가 필요한 의존성 외부에서 주입
  - Before. 컴포지션으로 의존성 부여
  - After. 필드와 생성자로 의존성 부여
  - 주입 방식
    - 생성자 주입
    - 수정자 주입
      - setter 이용해 나중에 주입하는 과정
    - 필드 주입
- **@Autowired**

  - 자동으로 의존성 주입
  - 타입이 같은 빈을 찾아감

- 동적 SQL
- DB 연결 → JDBC → MyBatis → ORM → JPA → Spring data JPA
- **sql 권한 부여**

- **Optional**
  - null 나타내기 좋음 (값이 없음을 나타낼 때) → throw → 404 not found
- application.property
  - `spring.jpa.hibernate.ddl-auto=create`
    `spring.jpa.hibernate.ddl-auto=update`
