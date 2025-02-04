## <mark color="#fbc956">환경 변수 (Environment Variable)</mark>

### 1. 운영체제 관리 변수

- 운영체제(OS) 단위에서 관리되는 key-value 쌍으로 이루어진 데이터
- **사용**
  1. 환경 변수 파일 `.env` 을 활용
  2. 명령어 통해 환경 변수 직접 설정
  - 애플리케이션에서 환경 변수 불러와 사용
- 민감한 데이터 숨기거나 동적으로 관리 필요한 데이터를 환경 변수로 관리

### 2. Spring Boot 환경 변수 관리

- 의존성
  ```java
  // build.gradle
  dependencies{
  		implementation 'me.paulschwarz:spring-dotenv:4.0.0'
  }
  ```
- 환경 변수 파일 `.env` 작성 예시
  ```java
  MESSAGE=Hello Spring Boot
  SERVER_PORT=8080
  ```
- `*.java` 파일에서 환경 변수 불러오기
  ```java
  import org.springframework.beans.factory.annotation.Value;
  ```
  ```java
  @Value("${MESSAGE}")
  String message;
  ```
- `application.properties` 파일에서 불러오기
  ```java
  server.port=${SERVER_PORT}
  ```

### 3. React(Vite) 환경 변수

- 환경 변수 파일 `.env` 작성 예시
  - 환경 변수 key 이름은 `VITE_` 로 시작
  ```jsx
  VITE_MESSAGE = "Hello React";
  VITE_API_URL = "http://localhost:8080/api";
  ```
- 리액트에서 환경 변수 불러오기
  ```jsx
  const message = import.meta.env.VITE_MESSAGE;
  ```

---

## <mark color="#fbc956">실습</mark>

### 1. 환경 변수 대체하기

1. backend와 frontend 파일 내부 각각 환경 변수 파일 `.env` 생성
2. 코드 일부를 환경 변수로 대체

   - `backend/src/main/java/com/example/backend/config/WebConfig.java`
     ```jsx
     // 클라이언트 주소(CLIENT_ORIGIN)를 환경 변수로 대체
     String clientOrigin = "http://localhost:5173";
     ```
   - `bcakend/src/main/resources/application.properties`

     - 데이터베이스 설정 값은 각자 환경에 맞게 대처

     ```jsx
     # 서버의 포트 번호(SERVER_PORT)를 환경 변수로 대체한다.
     server.port=8080

     # MySQL 연결 설정
     # 아래 요소를 환경 변수로 대체한다.
     # 데이터베이스 서버 주소(DATABASE_HOST)
     # 데이터베이스 서버 포트(DATABASE_PORT)
     # 데이터베이스 이름(DATABASE_NAME)
     # 데이터베이스 서버 접속 사용자명(DATABASE_USERNAME)
     # 데이터베이스 서버 접속 비밀번호(DATABASE_PASSWORD!)
     spring.datasource.url=jdbc:mysql://localhost:3306/demo
     spring.datasource.username=root
     spring.datasource.password=1q2w3e4r!
     ```

     ```jsx
     # 서버의 포트 번호(SERVER_PORT)를 환경 변수로 대체한다.
     server.port=${SERVER_PORT}

     # MySQL 연결 설정
     # 아래 요소를 환경 변수로 대체한다.
     # 데이터베이스 서버 주소(DATABASE_HOST)
     # 데이터베이스 서버 포트(DATABASE_PORT)
     # 데이터베이스 이름(DATABASE_NAME)
     # 데이터베이스 서버 접속 사용자명(DATABASE_USERNAME)
     # 데이터베이스 서버 접속 비밀번호(DATABASE_PASSWORD)
     spring.datasource.url=jdbc:mysql://${DATABASE_HOST}:${DATABASE_PORT}/${DATABASE_NAME}
     spring.datasource.username=${DATABASE_USERNAME}
     spring.datasource.password=${DATABASE_PASSWORD}

     # JPA
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true

     ```

3. mysql 컨테이너 실행

   ```jsx
   docker run -p 3307:3306 -e MYSQL_ROOT_PASSWORD=1q2w3e4r! -e MYSQL_DATABASE=db mysql:8.0
   ```

4. `fontend/src/App.jsx`

   ```jsx
   // API 서버의 주소(VITE_API_URL)를 환경 변수로 대체
   const API_URL = "http://localhost:8080/api/";
   ```

---

### ☀️ 오늘의 배움

- **환경 변수**
  - 민감한 데이터 숨김
  - 동적으로 관리가 필요한 데이터에 사용
