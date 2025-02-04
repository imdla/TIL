## <mark color="#fbc956">CSS</mark>

### 1. CSS (Cascading Style Sheets)

- **CSS(Cascading Style Sheets)**
  - HTML이나 XML로 작성된 문서의 표시 방법을 기술하기 위한 stylesheet 언어
  - 웹페이지를 꾸미려고 작성하는 코드
- **Cascading**
  - 구조 또는 컨텐츠에 적용되는 스타일의 우선 순위 결정 방식

### 2. CSS 구조

```css
p {
  color: red;
}

/* p -> Selector */
/* color: red; -> Declaration */
/* color -> Property */
/* red -> Property value */
```

- **rule set(rule)** : 전체 구조
- **선택자(selector)**
  - 꾸밀 요소 선택
- **선언(Declaration)**
  - 꾸미기 원하는 요소의 속성
- **속성(Property)**
  - HTML 요소를 꾸밀 수 있는 방법
- **속성 값(Property value)**
  - 스타일링 속성에 대한 값
- **조건**
  - rule set은 중괄호 `{}` 로 감싸져야 함
  - 선언 안의 각 `속성 : 해당 값` 으로 콜론(`:`) 구분
  - 그 다음 선언 구분위해 세미콜론(`;`) 사용

### 3. CSS 적용 방법

- **인라인 스타일(Inline Styles)**
  - `style` 속성 내 포함된 한 요소만 영향 주는 CSS 선언
  - CSS 정보와 HTML 구조 정보 혼합해 코드를 읽고 이해하는데 어려움
  ```html
  <!DOCTYPE html>
  <html>
    <head>
      <meta charset="utf-8" />
      <title>CSS</title>
    </head>
    <body>
      <h1 style="color: blue;">Hello world!</h1>
    </body>
  </html>
  ```
- **내부 스타일 시트(Internal Style Sheet)**
  - HTML `<head>` 안에 포함된 `<style>` 요소 내부에 CSS 배치
  ```html
  <!DOCTYPE html>
  <html>
    <head>
      <meta charset="utf-8" />
      <title>CSS</title>
      <style>
        h1 {
          color: blue;
        }
      </style>
    </head>
    <body>
      <h1>Hello world!</h1>
    </body>
  </html>
  ```
- **외부 스타일 시트(External Style Sheet)**
  - CSS를 여러 페이지에 연결 가능, 가장 일반적이고 유용한 방법
  - CSS 확장자가 `.css` 인 별도 파일에 작성 → HTML `<link>` 요소에서 참조
  ```html
  <!DOCTYPE html>
  <html>
    <head>
      <meta charset="utf-8" />
      <title>CSS</title>
      <link rel="stylesheet" href="styles.css" />
    </head>
    <body></body>
  </html>
  ```

---

## <mark color="#fbc956">선택자(Selector)</mark>

### 1. 선택자 유형

- **전체 선택자(Universal Selector)**
  - 문서 내 모든 요소 선택
  ```css
  * {
    color: #333333;
  }
  ```
- **요소 선택자(Element Selector)**
  - 특정 타입의 모든 HTML 요소 선택
  ```css
  p {
    color: red;
  }
  ```
- **아이디 선택자(ID Selector)**
  - `#{아이디명}`
  - 특정 아이디 가진 요소 선택 (아이디당 하나의 요소만 허용)
  ```html
  <p id="my-id">Hello world!</p>
  ```
  ```css
  #my-id {
    color: red;
  }
  ```
- **클래스 선택자(Class Selector)**
  - `.{클래스명}`
  - 특정 클래스 가진 페이지의 요소 (한 페이지에 여러번 나타날 수 있음)
  ```html
  <p class="my-class">Hello world!</p>
  ```
  ```css
  .my-class {
    color: red;
  }
  ```
- **속성 선택자(Attribute Selector)**
  - 특정 속성 갖는 페이지 요소
  ```html
  <img src="url" />
  ```
  ```css
  img[src] {
    width: 100px;
  }
  ```
- **자식 선택자(Child Combinator)**
  - 특정 부모 요소 바로 아래 요소인 자식 요소 선택
  ```css
  div > p {
    color: red;
  }
  ```
- **자손 선택자(Descendant Combinator)**
  - 특정 부모 요소의 모든 하위 요소 선택
  ```css
  div p {
    color: red;
  }
  ```
- **수도 클래스 선택자(Pseudo Class Selector)**
  - 특정 요소이지만 특정 상태에 있는 요소
  ```css
  /* 마우스 포인터가 링크 위에 있을 때(hover)만 선택 */
  a:hover {
    color: red;
  }
  ```
- **그룹화 선택자**
  - 여러 선택자에 동일한 스타일 적용
  ```css
  div,
  p {
    color: red;
  }
  ```
