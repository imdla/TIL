> 💡 **한 줄 요약**
>
> `Content-Type` 은 HTTP 요청과 응답에서 전송되는 데이터의 타입을 명시하는 헤더로, 서버와 클라이언트가 데이터를 주고받을 때 올바르게 해석할 수 있도록 한다.

### 1. 🤔 왜 사용하는가

- **`Content-Type`**

  - HTTP 요청과 응답에서 전송되는 데이터의 타입을 명시하는 헤더
  - 서버와 클라이언트가 데이터를 주고받을 때, 올바르게 해석할 수 있도록 함
  - ex. JSON 데이터를 전송하는 경우
    - `Content-Type: application/json` 사용 시
      → 서버는 해당 데이터가 JSON 형식이라는 것을 알고 적절한 방식으로 해석 가능
  - ex. 서버가 클라이언트에게 HTML을 응답하는 경우
    - `Content-Type: text/html` 지정 시
      → 브라우저는 이를 HTML로 렌더링 가능

- **`Content-Type` 헤더**
  - MIME 타입 기반
  - `[type]/[subtype]` 형식 구성
  - ex. JSON 데이터 → `application/json`
    - HTML 문서 → `text/html`
    - 파일 업로드 → `multipart/form-data`
  - 정확히 지정하지 않을 경우, 클라이언트나 서버에서 데이터를 올바르게 해석하지 못할 수도 있음

### 2. 💡 무엇인지 아는가(특징)

> **`Content-Type`과 `Accept` 헤더의 차이**

- **`Content-Type` 헤더**
  - 전송되는 데이터의 타입을 지정
- **`Accept` 헤더**
  - 응답으로 받고자 하는 데이터의 타입 지정
