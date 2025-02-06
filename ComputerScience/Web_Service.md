### 1. 🤔 왜 사용하는가

> **웹 서비스 작동 과정**

![image.png](/ComputerScience/assets/web_service.png)

- 정적인 요청
  1. 클라이언트가 요청 보내면
  2. 웹 서버는 해당하는 정적 콘텐츠를 응답
- 동적인 요청
  1. 클라이언트가 요청 보내면
  2. Nginx의 Proxy Server가 클라이언트의 요청을 대신 받아 WAS로 전달해줌
  3. WAS는 DB와 상호작용해 데이터 받아옴
  4. 데이터를 Proxy Server에 전송
  5. Proxy Server는 데이터를 받아 클라이언트에게 전달해줌

### 2. 💡 무엇인지 아는가(특징)

> **WAS / Web Server / Proxy Server**

- **WAS(Web Application Server)**

  - 동적 로직을 처리하는 서버

- **Web Server**

  - HTML, CSS, JS, 이미지 등 콘텐츠를 클라이언트에게 제공하는 서버
  - 클라이언트와 기본적인 HTTP 요청 및 응답 처리하는 서버

- **Proxy Server**
  - 인터넷 상의 여러 네트워크 접속 시 중계 역할 해주는 프로그램
  - 프록시는 요청 가로챈 뒤, 응답 되돌려줌
    - 포워드 프록시 : 인터넷 상에서 어디로든지 요청을 전송해주는 프록시
    - 리버스 프록시 : 인터넷에서 요청을 받으면 내부망 내의 서버로 전송
