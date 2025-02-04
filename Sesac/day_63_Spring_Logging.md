## <mark color="#fbc956">로깅</mark>

### 1. 로깅

- 애플리케이션의 동작 상태와 문제점을 파악하기 위해 정보를 기록하는 것
- 로그 레벨에 따라 출력 조절 가능, 시간, 클래스명 등 상세한 정보 확인 가능
- 파일로 저장해 과거 이력 확인 가능

### 2. 로그 레벨

- **ERROR**
  - 심각한 문제가 발생한 경우
  - try-catch와 함께 자주 사용됨
  ```java
  try {
      memberRepository.save(member);
  } catch (DataAccessException e) {
      log.error("회원 정보 저장 실패. ID: {}, Error: {}", member.getId(), e.getMessage(), e);
      throw new CustomException("회원 가입 처리 중 오류가 발생했습니다.");
  }
  ```
- **WARN**
  - 잠재적인 문제가 될 수 있는 경우
  - try-catch와 함께 자주 사용됨
  ```java
  try {
      userService.sendEmail(user.getEmail());
  } catch (EmailSendException e) {
      log.warn("이메일 발송 실패. 사용자: {}, 사유: {}", user.getEmail(), e.getMessage());
      // SMS 등 대체 수단으로 알림 발송
  }
  ```
- **INFO**
  - 서비스 운영 중 발생하는 주요 이벤트 기록
  ```java
  // 서버 시작/종료 등 주요 이벤트
  log.info("Application started on port {}", serverPort);

  // API 호출 로그
  log.info("Member registration completed. ID: {}", memberId);
  ```
- **DEBUG**
  - 개발 시 디버깅 목적으로 사용
  - 운영 환경에서는 기록하지 않음
  - ex. 메소드 호출 정보, 변수값 등
  ```java
  // 메소드 호출 시 파라미터 값 확인
  log.debug("findMember request - email: {}", email);

  // 주요 분기 처리 정보
  log.debug("포인트 처리 분기 - 일반회원: {}", point);
  ```
- **TRACE**
  - 가장 상세한 정보를 나타내는 경우
  - 운영 환경에서는 기록하지 않음
  - ex. 루프 반복 정보, 상세 처리 과정 등
  ```java
  // 메소드 진입/종료
  log.trace("메소드 시작: findMember");

  // 루프 내 처리 정보
  log.trace("주문상품 {}번째 처리중, 상품번호: {}", index, productId);
  ```

### 3. `@Slf4j`

- Simple Logging Facade for Java
- lombok에서 제공하는 어노테이션, 로거 인스턴스를 자동으로 생성
  - class에 어노테이션 적용 시 method에서 `log` 인스턴스 바로 활용 가능

### 4. 사용법

- 모든 로그 레벨(error, warn, info, debug, trace)에서 동일하게 사용
- 기본 형식

  ```java
  log.info("메시지 {}", 파라미터)ㅣ
  ```

- 예외(Exception) 로깅

  ```java
  try {
  } catch (Exception e) {
      log.error("에러 메시지: {}", errorMessage, e);
      // Exception 객체는 마지막 파라미터로 전달한다.
  }
  ```

- 성능 문제로 다음과 같은 연산은 사용하지 않음
  ```java
  log.info("사용자 이름:" + name);
  ```

### 5. 실습

- 클래스에 `@Slf4j` 어노테이션 추가
- 메서드 내에 `log` 인스턴스 통해 로깅

- `PostService`
  ```java
  @Slf4j
  public class PostService {
  		public List<PostListResponseDto> readPosts(){
  		    log.info("read posts");
  		    return postRepository.findAll().stream()
  		            .map(PostListResponseDto::from)
  		            .toList();
  }
  ```

### 6. 추가 설정

- 로깅 레밸
  ```java
  # 루트 레벨 전체 로깅 레벨 설정
  logging.level.root=INFO
  ```

### 7. 파일 로깅

```java
# 로그 파일 경로 설정
logging.file.name=./logs/application.log

# 로그 파일 사이즈 제한
logging.logback.rollingpolicy.max-file-size=10MB

# 로그 파일 최대 개
logging.logback.rollingpolicy.max-history=30
```

- **SQL 로깅**

```java
# JPA SQL 쿼리 출력
spring.jpa.show-sql=true
logging.level.org.hibernate.SQL=DEBUG

# 이쁘게 출력
spring.jpa.properties.hibernate.format_sql=true

# 바인딩 파라미터 출력
logging.level.org.hibernate.orm.jdbc.bind=TRACE
```

- 로그 포맷 변경 가능

### 8. 로깅 시 주의사항

- **운영체제 환경 관련**
  - 운영체제 환경에서는 INFO 레벨 이상만 사용 가능
  - 민감한 정보(개인정보, 비밀번호 등)는 절대 로깅하지 않음
  - 로그 파일은 정기적으로 백업하고 관리됨
- **성능 관련**
  - 로그 문자열 연산은 필요한 경우에만 수행
  - DEBUG, TRACE 레벨은 개발 환경에서만 사용 가능
  - SQL 로깅은 개방환경에만 활성화
- **로그 작성 관련**
  - 의미 있는 로그 메시지 작성
  - 예외 발생 시 stack trace를 함께 기록한다.
  - 로그 레벨을 적절히 선탣
