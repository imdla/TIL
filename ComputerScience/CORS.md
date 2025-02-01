> 💡 **한 줄 요약**
>
> CORS는 출처가 다른 곳의 리소스 요청 시 접근 권한을 부여하는 메커니즘이다. CSRF를 방지하기 위해 SOP가 있지만, 현대에는 다른 출처의 리소스를 사용하는 경우가 많아 CORS를 통해 이를 해결할 수 있다.

### 1. 🤔 왜 사용하는가

- **CORS (Cross Origin Resource Sharing)**

  - 출처가 다른 곳의 리소스를 요청할 때 접근 권한을 부여하는 메커니즘

- **리소스를 주고 받는 두 곳의 출처가 다를 경우 ⇒ 출처가 교차함**
  - 출처는 URL뿐만 아니라 프로토콜과 포트까지 포함
  - 클라이언트의 출처가 미허용일 경우, CORS 에러 발생 가능

### 2. 💡 무엇인지 아는가(특징)

> **CORS 필요성**

- 과거, 크로스 사이트 요청 위조(CSRF, Cross-Site Request Forgery) 문제
  - CSRF : 피해자 브라우저에서 다른 애플리케이션으로 가짜 클라이언트 요청을 전송하는 공격
- CSRF 예방 위해 브라우저는 동일 출처 정책(SOP, Same-Origin Policy) 구현
  - SOP가 구현된 브라우저는 클라이언트와 동일한 출처의 리소스로만 요청 보낼 수 있음
  - SOP의 한계
    - 현대 웹 애플리케이션은 다른 출처의 리소스 사용하는 경우 많음
    ⇒ SOP 확장한 CORS 필요

> **CORS 작동 방법**

1. **Simple Request 보내는 경우**

   ![image.png](/ComputerScience/assets/CORS_1.png)

   → 브라우저 요청 메시지에 Origin 헤더와 응답 메시지의 Access-Control-Allow-Origin 헤더를 비교

   - CORS 위반하지는지 확인
   - Origin에는 현재 요청하는 클라이언트의 출처(프로토콜, 도메인, 포트)
   - Access-Control-Allow-Origin에는 리소스 요청을 허용하는 출처 작성

- **Simple Request(단순 요청)인 경우**
  - 요청 메서드 (GET, POST, HEAD)
  - 수동으로 설정한 요청 헤더 (Accept. Accept-Language, Content-Language, Content-Type, Range)
  - Content-Type 헤더 (application/x-www-form-urlencoded, multipart/form-data, text/plain)

1. 브라우저가 **사전 요청(Preflight Request)**을 보내는 경우

   ![image.png](/ComputerScience/assets/CORS_2.png)

   - 브라우저가 본 요청을 보내기 이전, Preflight Request를 OPTIONS 메서드로 요청을 보내 실제 요청이 안전한지 확인
   - Preflight Request 요청 시 전달해야 할 것
     - Access-Control-Request-Method로 실 요청 메서드
     - Access-Control-Request-Headers 헤더에 실 요청의 추가 헤더 목록
   - 이에 대한 응답
     - 대응되는 Access-Control-Allow-Methods와 Access-Control-Headers
     - Preflight Request로 인한 추가 요청 줄이기 위해 캐시 기간을 Access-Control-Max-Age 담아 보냄

1. **인증된 요청(Crefential Request)을 사용하는 방식**

   ![image.png](/ComputerScience/assets/CORS_3.png)

   - 쿠키나 토큰과 같은 인증 정보를 포함한 요청일 경우 수행
   - 서버에서 Access-Control-Allow-Credentials를 true로 설정
   - Access-Control-Allow-Origin에 와일드카드 사용하지 못함
