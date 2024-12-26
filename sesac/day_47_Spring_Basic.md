## <mark color="#fbc956">Spring</mark>

### 1. Web Server / Web Application Server

> Client → Web Server → WAS → DB

- **Web Server**
  - 정적인 컨텐츠(파일)을 제공하는 서버
  - HTML, CSS, 이미지 등 변하지 않는 파일들을 클라이언트(브라우저)에 전달
  - 대표적 웹 서버 : Apache, Nginx
- **WAS (Web Application Server)**
  - 동적인 컨텐츠를 제공하는 서버
  - 사용자의 요청에 따라 데이터를 처리해 다른 결과를 보여줌
  - Java로 작성한 프로그램을 실행하고 결과를 사용자에게 전달
  - 대표적인 WAS : Tomcat

### 2. Spring

- Java 기반의 애플리케이션 개발을 위한 오픈소스 프레임워크
  - 현재 가장 널리 사용되는 Java 프레임워크
- 웹 사이트나 웹 서비스를 만들 때 필요한 많은 기능들을 미리 구현해 개발자가 비즈니스 로직에만 집중할 수 있게 해줌

### 3. Spring boot

- Spring을 더 쉽게 사용할 수 있게 해주는 도구
- 복잡한 설정 없이 바로 개발 시작 가능
- 필요한 라브러리들을 자동으로 설치해줌
- 서버가 내장되어 있어 따로 설치할 필요 없음

---

## <mark color="#fbc956">Spring start</mark>

### 1. 추가 설정 - Spring Boot DevTools

- 개발 생산성을 높여주는 도구
- 자동 재시작 (Auto Restart)
  - 코드를 수정하면 자동으로 서버 재시작
  - 일일이 서버 껐다가 켤 필요 없음
- 라이브 리로드 (Live Reload)
  - 정적 자원 수정 시 자동으로 브라우저 새로고침
  - 개발할 때 수정사항을 바로 확인 가능

### 2. 추가 설정 - Lombok

- 자바의 반복적인 코드 작성을 줄여주는 라이브러리
- getter / setter 등 반복적인 코드를 자동 삽입

---

### ☀️ 오늘의 배움

- **next**
  - 첫 페이지에 html+css+js+data 를 줘서 seo 문제를 해결함
- **Web Server**
  - 누가 요청을 보내도 같은 정보를 보내주는 서버
- **WAS**
  - 요청을 받고 동적인 응답을 줌
  - 내부에서 비즈니스 로직 처리
- 어노테이션
  - 후처리기 역할
- `@Controller`
  - 화면을 보여줌
- 초기화 블록
  - 인스턴스가 생성되었을 때 한 번 실행됨
- `@PathVariable("변수명")`
  - `@GetMapping("/posts/{id}")` 에서 경로 상의 변수를 사용할 때
- `@RequestMapping("/v3/posts")`
  - baseUrl 묶어줌
