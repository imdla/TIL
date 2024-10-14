## <mark color="#fbc956">HTML</mark>

### HTML(Hyper Text Markup Language)

: 웹 이루는 가장 기초적 요소, 웹 콘텐츠의 의미와 **`구조`** 정의

- **Hypertext**
  : 웹 페이지를 다른 페이지로 연결하는 링크

### 요소(element)

- **태그(tag)** : 요소의 기능과 역할 부여
  - **여는 태그(Openin tag)** : 요소의 이름 구성, 요소가 시작되는 곳
  - **닫는 태그(Closing tag)** : 요소 이름 앞 전방향 슬래시 포함, 요소의 끝 나타냄
    - 종료 태그가 없는 것도 존재
- **콘텐츠(Content)** : 요소의 내용
- **요소(Element)** : 여는 태그 + 닫는 태그 + 콘텐츠 구성

### 태그(tag)

1. **속성**
   - **블록 태그(Block tags)**
     - 웹 페이지 상 블록을 만듦 → 블록 태그 이전 · 이후 사이 줄 바꿈
     - 일반적으로 페이지의 구조적 요소 나타낼 때 사용
   - **인라인 태그(Inline tags)**
     - 항상 블록 레벨 태그 내 포함됨 → 새로운 줄을 만들지 않음
     - 큰 범위에 적용 X, 작은 부분에 적용 O
     - ex. `<a>`, `<em>`, `<strong>` , `<span>`
2. **의미**
   - **의미론적 태그(Semantic tags)**
     - 문서 구조와 내용 정의해 콘텐츠의 의미 전달
     - 검색 엔진 최적화 향상(SEO)에 중요 역할
     - **종류**
       - `<header>` : 머리말
       - `<nav>` : 네비게이션
       - `<section>` : 섹션 구분
       - `<article>` : 콘텐츠 블록
       - `<footer>` : 바닥글
   - **비의미론적 태그(Non-semantic tag)**
     - 콘텐츠 의미 전달하지 않음
     - 콘텐츠 그룹화하거나 스타일링에 주로 사용
     - **종류**
       - `<div>` : 콘텐츠 그룹화
       - `<span>` : 텍스트나 다른 인라인 요소 그룹화

### 속성(attributes)

: 콘텐츠로 표시되지 않는 추가적 정보와 기능 제공, 동작 방식 제어

- 클래스 속성 이용 → 특정 해당 요소의 스타일 설정 가능
- **구성**
  - `속성명 = “속성값”`
  - 요소 이름 (2개 이상일 경우 속성 사이 공백)
- **예**
  - `href` : a 태그의 고유 속성, 연결하고자 하는 웹 주소
  - `title` : 링크에 대한 추가 정보
  - `target` : 링크가 어떻게 열릴지 지정

### 중첩(nesting)

: 요소 안 다른 요소 사용

```html
<!-- 정상적 중첩 -->
<p>My dog is <strong>very</strong> cute!</p>

<!-- 비정상적 중첩 -->
<p>My dog is <strong>very</p> cute!</strong>
```

> 🚫 **주의 사항**
>
> 블록 레벨 요소는 인라인 요소에 중첩될 수 없지만, 블록 레벨 요소는 다른 블록 레벨 요소에 중첩될 수 있음

### HTML 문서

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
  <body></body>
</html>
```

- `<!DOCTYPE html>` : 필수 서문, 좋은 HTML 페이지가 따라야 할 규칙
- `<html></html>` : 페이지 전체 콘텐츠를 감싸고 있음, 루트 요소로 문서의 고유 언어 설정하는 `lang` 속성 포함
- `<head></head>` : 키워드와 검색 결과 표시되는 페이지 설명, CSS, 문자 집합 선언 등 포함
- `<meta charset="utf-8">` : 대부분 문자 포함되어 있는 utf-8으로 설정
- `<meta name="viewport" content="width=device-width">` : 뷰포트는 뷰포트의 너비에서 페이지 렌더링 되도록 함 (뷰포트보다 넓은 페이지 렌더링 후 축소하는 것 방지)
- `<title></title>` : 브라우저의 탭에 나타나는 페이지의 제목
- `<body><body>` : 웹 사용자에게 보여지는 컨텐츠

### 이미지(image)

```html
<img src="이미지 주소" alt="이미지 설명" />
```

- `src` (source) : 이미지 파일의 경로
- `alt` (alter) : 이미지 볼 수 없는 사용자 위한 `설명 텍스트`

---

## <mark color="#fbc956">문자 나타내기</mark>

### 제목(heading)

- `<h1></h1>`, `<h2></h2>`, `<h3></h3>`, `<h4></h4>`, `<h5></h5>`, `<h6></h6>`
- 내용의 특정 부분이 제목 or 하위 제목임을 구체화

### 문단(paragraph)

- `<p></p>`
- 문자의 문단 포함, 일반적인 문자 내용 주로 나타냄

### 목록(list)

- **순서 없는 목록**
  - `<ul></ul>`
- **순서 있는 목록**
  - `<ol></ol>`
- **목록의 항목**
  - `<li></li>`

### 링크(link)

- `<a href="링크 주소"></a>`
- 링크 연결

### 주석(comments)

- `<!-- 주석 내용 -->`
- 코드의 메모나 설명을 작성
- 웹 사용자에게 보이지 않음
- 주석 내 주석은 사용할 수 없음

---

## <mark color="#fbc956">form tag</mark>

### form tag

- 정보 제출위한 대화형 컨트롤 포함하는 문서 구획
- **`<form action="" method="" />`**
  - `action` : 양식 데이터 처리할 프로그램의 url
  - `method` : 양식 제출할 때 사용할 http 메소드
    - `get` : 데이터 조회
    - `post` : 서버의 데이터베이스에 접근

### 구성 요소

- `<input>` : 사용자가 데이터 입력할 수 있는 한 줄 필드
  - `type` 속성따라 입력 필드 목적 다름
    - `text` : 일반 텍스트 입력
    - `password` : 입력된 텍스트 마스킹 처리 (비밀번호)
    - `submit` : 폼 제출 버튼
    - `checkbox` : 체크박스 (여러개 선택)
    - `radio` : 라디오 버튼 (하나만 선택)
    - `email` : 이메일 주소 입력, 이메일 형식 검사
    - `number` : 숫자 입력
    - `hidden` : 화면에 표시되지 않는 숨겨진 입력
- `<textarea>` : 사용자 입력 여러줄 텍스트 입력 필드
- `<select>` : 옵션 중 선택 가능한 드롭다운 목록
- `<button>` : 클릭 가능한 버튼

### 추가 속성

- `id` : `label`의 `for`와 연결
- `name` : form 컨트롤의 이름, 이름:값의 쌍으로 폼과 함께 제출
- `placeholder` : 빈칸일 때 표시되는 텍스트
- `value` : 초기값 설정
- `required` : 제출 전 값을 입력해야 함을 지정
