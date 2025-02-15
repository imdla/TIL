## <mark color="#fbc956">Authentication & Authorization</mark>

### 1. Authentication (인증)

- 사용자, 장치의 신원을 확인하는 과정
- 즉, 로그인

### 2. Authorization (권한 부여)

- 인증된 사용자나 시스템이 수행할 수 있는 작업의 유형과 범위를 결정하는 과정
- ex. 관리자는 공지사항 작성 가능, 개인 사용자는 불가능

### 3. HTTP 특징

1. **비연결성 (Connectionless)**
   - 클라이언트가 서버에 요청을 보내고 서버가 그 요청에 응답을 한 후, 연결이 바로 끊어짐
   - 요청이 독립적임
   - 단, HTTP/1.1 부터는 Keep-Alive 통해 일정 시간 동안 연결 유지 가능
2. **무상태 (Stateless)**
   - 이전 요청에 대한 정보나 상태를 저장하지 않음
3. **클라이언트 - 서버 구조**

> HTTP는 비연결성, 무상태임에도 불구하고 한 번 로그인하면 계속 로그인 되어있다 !
>
> 🤔 그것을 알아보자 !

---

## <mark color="#fbc956">쿠키와 세션</mark>

### 1. 쿠키 (Cookies)

- 사용자의 웹 브라우저에 저장되는 작은 데이터 조각
- 이 데이터는 사용자가 웹 사이트를 방문할 때 마다 헤더를 통해 서버에 다시 전송
- 사용자의 세션 관리, 개인화 설정, 사용자 행동 기록 등에 사용
- **주요 속성**
  1. **Expires / Max-Age**
     - 쿠키의 유효 기간을 설정
     - Expires : 만료 날짜를 지정
     - Max-Age : 현재부터 만료까지의 시간(초)을 지정
     - 둘 다 없으면 세션 쿠키로 동작 (브라우저 종료 시 삭제)
  2. **HttpOnly**
     - JavaScript로의 접근을 차단
     - JavaScript의 document.cookie로 접근 불가
  3. **Secure**
     - HTTPS 프로토콜에서만 전송 가능

### 2. 세션 (Sessions)

- 서버 측에서 사용자 정보를 저장하는 방법
- 세션 ID를 통해 사용자를 식별, 이 ID는 쿠키를 통해 사용자의 브라우저에 저장
- 사용자의 로그인 상태 유지 및 사용자별로 개인화된 정보 관리

- **특징**
  1. **서버 자원 사용**
     - 서버의 메모리나 DB에 정보가 저장
     - 클라이언트 수가 많아지면 서버 부하 증가
  2. **보안성**
     - 중요 정보가 서버에 저장되어 안전
     - 세션 ID만 클라이언트에 전달
     - 세션 ID는 유추하기 어려운 긴 문자열로 생성
     - 서버에서 세션을 강제로 만료시킬 수 있음

---

## <mark color="#fbc956">토큰</mark>

### 1. 토큰 (Tokens)

- 인증 정보를 안전하게 전송하기 위해 사용되는 암호화된 문자열
- 사용자의 로그인 정보를 서버에 저장하는 대신, 클라이언트 측에 저장

### 2. JWT (JSON Web Token)

- 웹 표준, 두 개체 사이에서 JSON 객체를 사용해 가볍고 자가 수용적인(self-contained) 방식으로 정보를 안전하게 전송하기 위해 설계
- 주로 인증 및 정보 교환에 사용

- **특징**

  1. **자가 수용성 (Self-contained)**
     - 필요한 모든 정보를 자체적으로 포함하고 있어, 별도의 정보 조회 없이도 검증과 사용자 식별 가능
  2. **확장성 (Scalability)**
     - 상태를 서버에 저장하지 않기 때문에, 애플리케이션의 확장성이 높아짐
  3. **다양한 플랫폼과 언어에서 사용 가능**
     - JWT는 JSON 포맷을 기반으로 하기 때문에 다양한 프로그래밍 언어와 플랫폼에서 쉽게 사용 가능

  - 즉, MSA에 적합

- **구성**

  1. **헤더** : 토큰 타입, 암호화 알고리즘이 명시
  2. **페이로드** : 토큰으로 관리할 데이터가 저장
     - 민감한 정보는 저장하지 않아야 함
  3. **서명** : 서명을 통해 JWT가 유효한지 확인 가능

- **보안**

  - **Access Token**
    - 실제 리소스에 접근할 때 사용
    - 비교적 짧은 유효기간
  - **Refresh Token**
    - Access Token 재발급에 사용
    - 비교적 긴 유효기간
    - 보안을 위해 서버 DB에 저장하기도 함

- **주의사항**
  1. 토큰의 크기가 세션 ID보다 큼
  2. 한 번 발급된 토큰은 만료 전까지 강제 회수 어려움
  3. 페이로드 정보가 암호화되지 않음 (Base64 인코딩만 됨)
  4. Stateless하기 때문에 토큰 발급 후 사용자의 권한 변경하기 어려움

### 3. MSA와 모놀리식 아키텍처

- **모놀리식 아키텍처 (Monolithic Architecture)**

  - 하나의 큰 애플리케이션에 모든 기능이 포함된 형태
  - 모든 비즈니스 로직이 하나의 프로젝트에 통합되어 있음
  - 하나의 데이터베이스를 사용

- **MSA (Microservices Architecture)**
  - 작은 독립적인 서비스들의 집합
  - 각 서비스가 독립적인 비즈니스 로직과 데이터베이스를 가짐
  - 서비스 간 통신은 API를 통해 이루어짐
  - **장점**
    - 서비스별 독립적인 개발과 배포 가능
    - 서비스별 다양한 기술 스택 사용 가능
    - 장애가 다른 서비스에 영향을 주지 않음
    - 필요한 서비스만 확장 가능
    - 서비스별 독립적인 팀 운영 가능
  - **단점**
    - 서비스간 통신 비용 발생
    - 트랜잭션 관리 복잡함
    - 테스트와 배포 복잡함
    - 서비스간 데이터 정합성 유지 어려움
    - 모니터링과 로깅 복잡함

### 4. 실시간 통신 방식

> **HTTP 프로토콜**

- **Polling**

  - 클라이언트가 주기적으로 서버에 요청을 보내는 방식
  - **특징**
    - 구현이 간단함
    - 불필요한 요청이 많이 발생함
    - 실시간성이 떨어짐
  - **사용**
    - 주기적인 데이터 업데이트가 필요한 경우
    - 실시간성이 크게 중요하지 않은 경우

- **Long Polling**

  - 클라이언트가 서버에 요청을 보내고, 서버는 이벤트가 발생할 때까지 응답을 보류하는 방식
  - **특징**
    - Polling보다 불필요한 요청이 적음
    - 서버 부하가 Polling보다 적음
    - 여전히 연결을 주기적으로 맺고 끊어야 함
  - **사용**
    - 실시간 알림
    - 메시지 전송 확인

- **Server-Sent Events (SSE)**
  - 서버가 클라이언트로 단방향 통신을 하는 방식
  - **특징**
    - 서버에서 클라이언트로의 단방향 통신만 가능
    - WebSocket보다 가벼움
  - **사용**
    - 실시간 알림
    - 주가 정보 업데이트
    - 뉴스 피드

> **WebSocket**

- **WebSocket**
  - 양방향 실시간 통신이 가능한 프로토콜
  - **특징**
    - 연결을 한 번 맺으면 계속 유지됨
    - 양방향 통신이 가능
    - 실시간성이 높음
    - HTTP 보다 오버헤드 적음
    - 서버 자원을 지속적으로 사용해 클라이언트 수가 많아지면 서버 부하가 큼
    - 방화벽이나 프록시 설정에 따라 연결이 차단될 수 있음
  - **사용**
    - 실시간 채팅
    - 온라인 게임
    - 실시간 협업 도구

---

### ☀️ 오늘의 배움

- **쓰레드**
  - 힙 : 컨트롤러, 서비스, 레포지토리
  - 스택 : 스택에 컨트롤러, 서비스, 레포지토리 가져와서 사용

---

- **쿠키와 세션**

  1. **상품 페이지 요청**
     - 클라이언트 → ( 상품 페이지 요청 ) → 서버
     - 클라이언트 ← ( HTML ) ← 서버
  2. **장바구니 담기**
     - 클라이언트 → ( 장바구니 담기 ) → 서버 → (session에 저장) → DB
     - 클라이언트 ← ( HTML + `session_id = abc123` 전달 ) ← 서버 ← (session_id 전달) ← DB
       - 서버는 DB에 `key(session_id): value(json)` 로 저장 (abc123 : {귤 : 1 } )
  3. **장바구니 이동**
     - 클라이언트 → ( 장바구니로 이동 요청 + 쿠키 ) → 서버
     - 클라이언트 ← ( `session_id` + HTML ) ← 서버

- **로컬 스토리지**
  - 브라우저가 꺼져도 계속 유지
- **세션 스토리지**

  - 브라우저가 켜져 있을 때만 유지, 적용

- user 테이블 접근

  - 그 후에는 세션만 검증

- radis Database

- 세션 존재 의의 : **인증 + 유저 식별 정보**
