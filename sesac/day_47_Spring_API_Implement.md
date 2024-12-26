## <mark color="#fbc956">API 구현</mark>

### 1. API 구현

- **`@RestController`**
  - API 요청을 처리하는 컨트롤러를 만들기 위한 어노테이션
  - HTTP 요청을 JSON/XML 형태로 처리
- **`@GetMapping("/")`**
  - HTTP GET 요청을 특정 메서드와 매핑시키는 어노테이션
  - 괄호 안에 URL 저장

```java
package com.example.demo.mysite;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstApi {
	@GetMapping("/")
	public String mainPage() {
		return "Hello world";
	}
}
```

### 2. 컨트롤러

- 클라이언트의 요청을 처리하는 첫 번째 단계
- URL 요청을 처리하고 응답을 반환하는 역할
- Spring에서 `@Controller` 와 `@RestContoller` 를 제공
  - `@Controller`
    - 주로 View(화면)를 반환하기 위해 사용
    - JSP, Thymeleaf 등의 템플릿 엔진과 함께 사용
    - `@ResponseBody` 를 붙여야 데이터를 직접 반환 가능
  - `@RestController`
    - `@Controller` + `@ResponseBody` 와 동일
    - 데이터를 직접 반환하기 위해 사용
    - JSON, XML 형태의 데이터를 주로 반환

### 3. 템플릿 엔진

- 서버에서 가져온 데이터를 HTML에 편리하게 표현할 수 있게 해줌
- Java 코드를 HTML 파일 내에서 사용할 수 있게 해줌

- **종류**
  - Thymeleaf
    - Spring 공식 권장 템플릿 엔진
    - Natural Template (순수 HTML을 그대로 유지)
    - 서버 실행 없이도 화면 확인 가능
  - JSP
    - 가장 오래된 Java 템플릿 엔진
    - HTML 내부에 Java 코드 작성 가능
    - 현재는 점점 사용 줄어드는 추세

- **렌더링 방식의 구분**
  - SSR (Server Side Rendering)
    - 서버에서 HTML을 생성해 클라이언트로 전송
    - 템플릿 엔진이 이 방식을 사용
    - 초기 로딩 속도가 빠르고 SEO에 유리
  - CSR (Client Side Rendering)
    - 클라이언트(브라우저)에서 JavaScript로 화면을 생성
    - React, Vue, Angular 등이 이 방식을 사용
    - 서버 부하가 적고 사용자 경험이 좋음

- **Spring에서**
  - 전통적인 웹 애플리케이션 : 템플릿 엔진 사용
  - REST API + 프론트엔드 분리 : React/Vue 등으로 대체 가능
