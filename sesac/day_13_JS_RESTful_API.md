## <mark color="#fbc956">RESTful API</mark>

### 1. Interface (인터페이스)

- **Interface (인터페이스)**
  - 서로 다른 두 개의 시스템, 장치 사이에서 정보나 신호 주고받는 경우의 접점이나 경계면
  - 사용자가 기기를 쉽게 동작시키는데 도움을 주는 시스템
- **GUI (Graphical User Interface)**
  - 사용자가 그래픽 요소를 통해 컴퓨터와 상호작용 할 수 있게 하는 사용자 인터페이스
- **CLI (Command Line Interface)**
  - 사용자가 텍스트 기반 명령어를 입력하여 컴퓨터와 상호작용하는 인터페이스

### 2. API

- **API (Application Programming Interface)**
  - 소프트웨어 어플리케이션이 서로 상호작용 할 수 있도록 하는 인터페이스

### 3. URI

- **URI (Uniform Resource Identifier)**
  - 인터넷에 있는 지원에 대한 식별자
- **URL (Uniform Resource Locator)**
  - 인터넷에 있는 자원을 식별하는 주소
- **URN (Uniform Resource Name)**

  - 인터넷에 있는 자원을 식별하는 이름

- **URL 구조**
  - **Scheme**
    - 브라우저가 리소스 요청하는데 사용해야 하는 프로토콜
    - http, https, ftp (파일 전송)
  - **Domain name**
    - 웹 서버 또는 IP 주소
  - **Port**
    - 웹 서버의 리소스에 접근하는데 사용되는 기술적인 게이트
    - 몇 포트들은 약속으로 정해져 있으며 이 경우는 생략 가능
      - http(80), https(443)
  - **Path to the file (Path parameter)**
    - 웹 서버에 있는 리소스 경로
  - **Parameters (query parameter)**
    - 웹 서버에 제공되는 추가적인 정보
    - `&` 기호로 구분된 `키=값` 쌍으로 주어짐
  - **Anchor**
    - 리소스 내부의 책갈피 역할
    - 브라우저에 해당 책갈피 지점의 콘텐츠를 표시하도록 지시

### 4. RESTful API

- 두 컴퓨터 시스템이 인터넷을 통해 정보를 안전하게 교환하기 위해 사용되는 인터페이스
- 인터넷 상의 리소스를 효율적으로 관리하고 사용하기 위한 아키텍처 스타일을 제공, 웹 API의 일반적인 디자인 원칙 정의
  1. 클라이언트-서버 디커플링
  2. 캐시가능성
  3. 무상태
  4. 계층화 시스템
  5. 균일한 인터페이스

1. **Request (요청)**
   - 요청을 통해 어떤 `데이터(resource)` 에게 어떤 `동작` 하고, 어떤 `응답` 바라는지 알 수 있음
   - **HTTP method (동작)**
     - GET : 데이터 조회
     - POST : 데이터 생성
     - PUT : 데이터 수정
     - DELETE : 데이터 삭제
   - **추가적인 데이터**
     - **HTTP header**
       - 요청 헤더는 클라이언트와 서버 간 교환되는 메타데이터 포함
     - **데이터**
       - 데이터를 생성, 수정하는 경우
     - **파라미터**
       - 리소스에 대한 추가 정보를 요청하는 쿼리 파라미터
       - 클라이언트를 빠르게 인증하는 쿠키 파라미터
   1. **Response (응답)**
      - **데이터의 표현**
        - 요청에 대한 응답으로 JSON 형식 주로 사용
        - JSON (JavaScipt Object Notation)
          : 속성-값 쌍(attribute-value pairs), 배열 자료형(array data types) 또는 기타 모든 시리얼화 가능한 값(serializable value) 등을 전달하기 위해 인간이 읽을 수 있는 텍스트 사용하는 개방형 표준 포맷
          - serialization (직렬화)
            - 데이터 구조나 오브젝트 상태를 동일하거나 다른 컴퓨터 환경에 저장하고 나중에 재구성할 수 있는 포맷으로 변환하는 과정
        - 과거, XML 형식 주로 사용
      - **HTTP status code**
        - 요청이 성공, 추가 조치 여부, 오류 발생 등을 나타내는 표준화된 코드
          - Informational responses (100 - 199)
          - Successful responses (200 - 299)
            - 정상 동작
          - Redirection messages (300 - 399)
          - Client error responses (400 - 499)
            - 사용자 잘못
          - Server error responses (500 - 599)
            - 개발자 잘못

### 5. API endpoint (API 엔드포인트)

- API가 서비스를 제공하기 위해 네트워크 상에서 호출될 수 있는 특정 URL 주소 의미
- API가 리소스에 접근하고 데이터를 조회, 생성, 수정, 삭제 하는 등의 작업을 수행할 수 있게 하는 진입점

---

## <mark color="#fbc956">json-server</mark>

### 1. json-server

- json 파일을 데이터베이스로 활용해 간단하게 REST API 서버 만들어 주는 패키지
- 백엔드가 구현되지 않았을 때 임시 또는 테스트용 API 서버로 사용 가능
- 일종의 간이 API 서버 역할

### 2. json-server 설치

```bash
npm install json-server
```

### 3. json-server 실행

```bash
npx json-server {데이터베이스 파일명.json} --watch
```
