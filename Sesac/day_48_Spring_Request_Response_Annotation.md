## <mark color="#fbc956">요청(Request) 관련 어노테이션</mark>

### 1. @PathVariable

- URL 경로에서 변수 추출할 때 사용
- 주요 속성
  - `required` (기본값: ture / false 를 사용할 일은 거의 없음)

```java
@GetMapping("/{id}")
public Post readPostById(@PathVariable Long id) {
	for (Post post : posts) {
		if (post.getId().equals(id)) {
			return post;
		}
	}
}
```

### 2. @RequestBody

- HTTP 요청 본문(Body)을 자바 객체로 변환할 때 사용
- 주로 POST, PUT, PATCH 요청에서 JSON 데이터를 전달받을 때 사용
- 특징
  - Content-Type이 appilcation-josn인 경우에만 동작
  - required 속성으로 필수 여부 지정 가능 (기본값 : true)

```java
@PostMapping
public Post createPost(@RequestBody Post newPost) {
	String title = newPost.getTitle();
	String content = newPost.getContent();
	Post post = new Post(++id, title, content);

	posts.add(post);

	return post;
}
```

### 3. @RequestParam

- URL 쿼리 파라미터를 추출할 때 사용
- 주요 속성
  - `required` : 필수 여부 (기본값 : true)
  - `defaultValue` : 기본값 설정, 기본값 설정 시 `required` 여부와 상관없이 사용 가능

```java
@GetMapping("/paged")
public List<Post> getPaged(@RequestParam int page, @RequestParam int size) {
	// 1. 페이지네이션을 위한 더미데이터
	for (int i = 1; i <= 20; i++) {
		String title = "title" + i;
		String content = "content" + i;
		Post post = new Post(++id, title, content);
		posrs.add(post);
	}

	// 2. 시작 인덱스와 끝 인덱스 계산
	int startIndex = (page - 1) * size;
	int endIndex = Math.min(startIndex + size, posts.size());

	// 3. 페이지에 해당하는 데이터만 추출
	return posts.subList(startIndex, endIndex);
}
```

- 기본값 있는 경우

```java
@GetMapping("/paged")
public List<Post> getPagedPosts(
	@RequestParam(defaultValue = "1") int page,
	@RequestParqm(defaultValue = "10") int size
) { ... }
```

### 4. @ModelAttribute

- 요청 파라미터를 객체로 바인딩할 때 사용
- 주로 Form 데이터를 전달받을 때 사용
- 특징
  - Content-Type
    - application/x-www-form-urlencoded : 일반 텍스트 데이터
    - multipart/form-data : 파일을 포함한 데이터
    - 위의 두 가지인 경우 사용 가능
  - Query Parameters도 받을 수 있음

---

## <mark color="#fbc956">응답(Response) 관련 어노테이션</mark>



### 1. @ResponseBody

- 자바 객체를 HTTP 응답 본문으로 변환할 때 사용
- @RestController 사용 시 생략 가능
- 특징
  - 기본적으로 JSON 형태로 변환됨

### 2. @ResponseStatus

- HTTP 응답 상태 코드 지정할 때 사용
- 주요 상태 코드
  - 200 OK : 요청 성공
  - 201 Created : 리소스 생성 성공
  - 204 No Content : 요청 성공했지만 응답 데이터 없음
  - 400 Bad Request : 잘못된 요청
  - 404 Not Found : 리소스를 찾을 수 없음

```java
import org.springframework.http.HttpStatus;

@PostMapping
@ResponseStatus(HttpStatus.CREATE)
public Post createPost(@RequestBody Post newPost) {
	String title = newPost.getTitle();
	String content = newPost.getContent();
	Post post = new Post(++id, title, content);
	posts.add(post);
	return post;
}
```

---

### ☀️ 오늘의 배움



- **백엔드**
  - 비즈니스 로직 처리
- **요청 ↔ 응답**
  - URL 요청 및 method 동작을 통해 resource 가져옴
  - @RestController : 요청을 받아 응답을 해주는, 연결해주는 장치
  - @RequestMapping, @GetMapping 등
- **Query Parameter**
  - GET 방식에서 주로 사용
  - URL을 통해 조회 → 정보가 URL에 노출됨
- **Request Body**
  - 요청 Body에 전달
  - 객체로 들어옴(`key=value`)
  - `RequestBody` 에 json으로 담아 보냄
- **HTTP**

  - content type
  - form 전달 방식 → form-data, x-www-form-urlencoded
  - json 형식 → row

- WAS, **웹 애플리케이션 서버**(Web Application Server, 약자 **WAS**)
  - 동적인 요청을 받아 처리해주는 서버
- **tomcat**
  - 서블릿이라는 클래스 형태로 말아줌
- **서블릿**
  - 자바를 사용하여 웹페이지를 동적으로 생성하는 서버측 프로그램
  - 서버에서 실행되다가 웹 브라우저에서 요청을 하면 해당 기능을 수행한 후 웹 브라우저에 결과를 전송
  - was가 서블릿을 처리해줌
  - 클라이언트가 요청 보내고 응답 보낼 때 서버가 함
    - 웹 서버 → 반드시 필요하진 않음
      - 정적인 리소스 줌
    - WAS → 묵직해서 터질 위험성 있음(ERROR)
      - 동적인 리소스 줌 (데이터베이스)
      - 클라이언트가 → React에 요청 보냄 → WAS로 보냄
    - WAS 내부 Spring이 들어있음 ← http 요청 보냄
      - 양식이 정해진 text
      - 서블릿이라는 클래스 형태로 전달
        - request, response 형식 부여
- 클라이언트 → 요청 보냄(url, method, data)
- 요청 보내는 방식

  1. 브라우저 : get 요청만 보낼 수 있음 (request body 사용 불가)
  2. postman : 다양한 데이터 전송 가능
  3. 쿼리 파라미터

- 데이터 전달 방식

  1. url
     - path
     - query parameter
  2. body
     - json
     - form

- 응답

  - json으로 할 것임
  - @RestController 통해

- @RestController
- @RequestMapping
- @PathVariable
  - 변수는 데이터 식별에 사용 → defualt 없고 require : true
- @RequestBody
  - json 입력 받아 함수 내부에서 사용할 수 있는 장치
- @RequestParam
  - 쿼리 마라미터에 값을 안넣어도 대부분 기본값을 지정해 보여주는 경우가 많음
- @ModelAttribute

  - form의 데이터 받기위한 장치
  - 파일은 파일에 대한 주소 저장

- Content-Type
  - http 요청을 보낼 때 데이터를 담는 방식
- fetch
  - header에 application/json 담아 보냈음 → json으로 전달하기 위한 장치
- 오픈 그래프

- **관심사 분리 SOC**
  - 메서드 분리(모듈화)
  - 관심사 분리 → 클래스 분리 → 파일 분리됨 (한 클래스는 한 파일)
- **POST**
  - 클라이언트 요청 → validation check (비즈니스 로직) → 생성 (CRUD)
- **비즈니스 로직**
  - 컴퓨터 프로그램에서 실세계의 규칙에 따라 데이터를 생성, 표시, 저장, 변경하는 부분
- **3계층 아키텍처**
  1. 표현 계층
     - 요청을 받으면서 Path, Request Body, Params 등 받음 → Controller 부분
  2. 비즈니스 계층
     - 요청을 받으면서 무엇인가 할 것임 → 그 `무엇인가`임
  3. 데이터 접근 계층
     - 데이터에 대한 CRUD
     - 레포지토리로 불림
  - 요청 → 컨트롤러 → 서비스 → 레포지토리 → 데이터
- **3계층 아키텍처 흐름**
  - 컨트롤러
    - 요청 받아 서비스에 맡김, 데이터 넘깅
  - 서비스
    - 데이터 받아 생성위해 해야할 일 처리
    - 컴포지션으로 레포지토리 가지고 있음
  - 레포지토리
    - 생성 끝
- **Optional**
  - 객체가 존재하지 않음을 가정하고 만든 객체
  - null에 대한 처리 자유로움
