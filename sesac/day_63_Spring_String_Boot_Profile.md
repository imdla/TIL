## <mark color="#fbc956">String Boot 프로필</mark>

### 프로필 설정 파일

- 프로필은 개발, 운영 등 환경별로 다른 설정을 적용하기 위해 사용
- `application.properties` 를 분리해 활용 가능
  - `application-dev.properties` 파일 생성 시, `dev` 개발 환경용 설정 파일이 되고
  - `application=prod.properties` 파일 생성 시, `props` 운영 횐경을 설정 파일이 됨
  - `appication.proprtries` 예시
    - 설정을 통해 개발 환경용 설정파일을 사용해 서버를 실행할 수 있음
    ```java
    spring.profiles.active=dev
    ```
