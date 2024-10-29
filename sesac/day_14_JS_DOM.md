## <mark color="#fbc956">DOM</mark>

### 1. DOM (Document Object Model)

- HTML 문서를 트리 구조 형태의 객체(Object)로 표현한 것
- 웹 페이지를 동적으로 조작하기 위해 브라우저와 JavsScript 사이에서 중간 다리 역할
- DOM 통한 요소 선택 및 조작, 요소의 속성 변경, 이벤트 처리 등 가능

### 2. DOM Tree와 Node

- **노드 (Node)** : DOM 트리에서의 각 단위
- **트리 구성 노드**
  - **부모 노드 (Parent Node)** : 다른 노드를 자식으로 갖는 노드
  - **자식 노드 (Child Node)** : 부모 노드의 하위에 연결된 노드들
  - **형제 노드 (Scilbling Node)** : 같은 부모 노드를 공유하는 노드들
- **노드 종류**
  - **문서 노드 (Document Node)** : DOM 트리 최상위 노드, 문서 전체 대표
  - **요소 노드 (Element Node)** : HTML 요소 나타냄, 다른 노드를 자식으로 가질 수 있음
  - **텍스트 노드 (Text Node)** : 요소 노드의 콘텐츠인 텍스트를 나타냄
  - **속성 노드 (Attribute Node)** : 요소의 속성, 요소 노드의 일부로 간주
  - **주석 노드 (Comment Node)** : HTML 문서의 주석 나타냄

---

## <mark color="#fbc956">DOM 요소 선택 및 조작</mark>



### 1. DOM 요소 선택

1. **`querySelector()`**

   - CSS 선택자를 이용해 첫 번째 일치하는 요소 노드 반환
   - 단일 요소 선택 시 사용

   ```jsx
   const element = document.querySelector(선택자);
   ```

2. **`querySelectorAll()`**

   - CSS 선택자 이용해 일치하는 모든 요소 노드 저장한 `NodeList` 반환
   - 배열 함수 사용해 반복 가능

   ```jsx
   const elements = document.querySelectorAll(선택자);
   ```

### 2. DOM 요소 조작

1. **`textContent` : 텍스트 조작**

   - 요소의 텍스트 내용 읽거나 변경
   - 요소 내부의 모든 텍스트를 단순한 문자열 취급
   - **자식 노드 있을 경우**
     - 텍스트 읽을 때 자식 노드의 텍스트도 읽음
     - 텍스트 수정할 때 모든 노드 제거됨

   ```html
   <!DOCTYPE html>
   <html lang="en">
     <head>
       <meta charset="UTF-8" />
       <meta name="viewport" content="width=device-width, initial-scale=1.0" />
       <title>Document</title>
     </head>
     <body>
       <h1>Hello World</h1>

       <script>
         // 요소의 텍스트 내용 읽음
         const heading = document.querySelector("h1");
         console.log(heading.textContent);

         // 요소의 텍스트 내용 변경
         heading.textContent = "Hello Students";
         console.log(heading.textContent);
       </script>
     </body>
   </html>
   ```

2. **속성 조작**

   - **`getAttribute()` : 요소의 특정 속성 값 읽음**
   - **`setAttribute()` : 요소의 특정 속성 값 조작**
   - 속성에 직접 접근에 속성 조작 가능 (`element.attribute`)
   - 클래스에 접근 시 `className` 사용

   ```html
   <!DOCTYPE html>
   <html lang="en">
     <head>
       <meta charset="UTF-8" />
       <meta name="viewport" content="width=device-width, initial-scale=1.0" />
       <title>Document</title>
     </head>
     <body>
       <h1>Hello World</h1>
       <a id="link" href="https://www.google.com"></a>

       <script>
         // 요소 선택
         const link = document.querySelector("#link");

         // 속성 값 읽음
         console.log(link.getAttribute("href"));

         // 속성 값 변경
         link.setAttribute("href", "https://www.naver.com");

         // 속성 직접 접근
         console.log(link.href);

         // id 직접 조작 및 접근
         link.href = "https://www.youtube.com";
         console.log(link.href);

         // class 직접 조작 및 접근
         link.className = "link tag";
         console.log(link.className);
       </script>
     </body>
   </html>
   ```

3. **`classList` : 클래스 조작**

   - 요소의 클래스 목록을 배열처럼 관리
   - **메서드**
     - `add()` : 클래스 목록에 새로운 클래스 추가
     - `remove()` : 클래스 목록에서 클래스 제거
     - `toggle()` : 클래스 목록에 클래스가 없으면 추가, 있으면 제거
     - `contains()` : 클래스 포함 여부 확인

   ```html
   <!DOCTYPE html>
   <html lang="en">
     <head>
       <meta charset="UTF-8" />
       <meta name="viewport" content="width=device-width, initial-scale=1.0" />
       <title>Document</title>
       <style>
         .active {
           color: red;
         }

         .hidden {
           display: none;
         }
       </style>
     </head>
     <body>
       <p id="content" class="paragraph">Hello World</p>

       <script>
         const content = document.querySelector("#content");

         // 클래스 목록에 새로운 클래스 추가
         content.classList.add("active");

         // 클래스 목록에 클래스 제거
         content.classList.remove("paragraph");

         // 클래스 토클
         content.classList.toggle("hidden");

         // 클래스 포함 여부 확인
         const isinClass = content.classList.contains("active");
         console.log(isinClass); // true
       </script>
     </body>
   </html>
   ```

4. **`style` : 스타일 조작**

   - 요소의 인라인 스타일 관리
   - 기존 css의 kebab-case 대신 camelCase 사용

   ```html
   <!DOCTYPE html>
   <html lang="en">
     <head>
       <meta charset="UTF-8" />
       <meta name="viewport" content="width=device-width, initial-scale=1.0" />
       <title>Document</title>
     </head>
     <body>
       <h1>Hello World</h1>

       <script>
         const heading = document.querySelector('h1');
         heading.style.backgroundColor: red;
       </script>
     </body>
   </html>
   ```

### 3. className과 classList

- **`className`**
  - 클래스 전체를 문자열로 관리
  - 클래스 전체를 새롭게 작성, 제거 시 유용
  - 여러 클래스 한 번에 지정할 경우 유용
- **`classList`**
  - `DOMTokenList` 객체로 클래스 목록을 배열처럼 관리
  - 메서드로 추가 / 제거 / 토글 사용 가능
  - 개별 클래스 관리에 유용
