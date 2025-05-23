> 💡 **한 줄 요약**
>
> CORS 설정 없이 SOP를 우회해 외부 서버와 통신하기 위해서는 프록시 서버를 이용할 수 있다. 브라우저 측에서 직접 외부 서버에 요청 보내지 않고, 클라이언트와 동일한 origin 프로기 서버 통해 요청을 보낼 경우 SOP의 제한을 피할 수 있다.

### 1. 🤔 왜 사용하는가

- **CORS 설정 없이 SOP 우회해 외부 서버와 통신 방법 → 프록시 서버 이용**
  - 브라우저 측에서 직접 외부 서버에 요청 보내지 않고, 클라이언트와 동일한 origin의 프록시 서버를 통해 요청 보낼 경우 SOP 제한 피할 수 있음
- **프록시 서버**
  - 브라우저 대신 외부 서버에 요청을 보내고 응답을 받는 역할을 대리 수행하는 서버

### 2. 💡 무엇인지 아는가(특징)

- 클라이언트 측 도메인 : `client.com`
- 서버 측 도메인 : `server.com`
- CORS 설정을 별도로 하지 않았을 경우
  - 도메인이 달라 브라우저 단에서 SOP에 의해 통신이 차단됨
- 프록시 서버 사용할 경우
  - 브라우저가 아닌 클라이언트 서버 통해 `server.com`에 요청을 보낼 경우 응답을 받을 수 있음
    - 서버와 서버 간의 통신에는 SOP가 적용되지 않음
  - 클라이언트 서버는 `client.com/api/xxx`와 같은 경로로 `server.com`으로부터 받은 응답을 브라우저(클라이언트)에 반환
  - 클라이언트 측 origin과 서버 측 origin이 `client.com`으로 일치해 정상적으로 응답을 수신할 수 있음
