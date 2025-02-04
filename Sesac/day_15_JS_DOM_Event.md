## <mark color="#fbc956">DOM Event</mark>

### 1. Event (이벤트)

- 프로그래밍하고 있는 시스템에서 일어나는 사건(action) 혹은 발생(occurrence)
- 웹 페이지에서 발생하는 사용자 동작과 브라우저의 상태 변화

### 2. 이벤트 종류

- 마우스 이벤트 : `click` , `dbclick` , `mousemove` , `mouseover` , `mouseout`
- 키보드 이벤트 : `keydown` , `keyup` , `keypress`
- 입력 이벤트 : `input`
- 폼 이벤트 : `submit` , `focus` , `blur`

### 3. 이벤트 핸들러 (Event Handler)

- `onclick`, `btn.textContent`, `btn.style`
- 특정 이벤트 발생 시 실행되는 함수
- 하나의 이벤트만 등록 가능
- **인라인 이벤트**
  ```html
  <button onclick="alert('Hello, this is event handler');">Press me</button>
  ```
  - 같은 페이지의 `<script>` 요소 안에서 정의된 함수를 호출하지만, 어트리뷰트 안에 직접적으로 JavaScript을 삽입 가능 ⇒ 비효율적

### 4. 이벤트 리스너(Event Listener)

- **`addEventListener()` : 이벤트 핸들러 추가**
  ```jsx
  addEventListener(type, handler, useCapture);
  ```
  - `type` : 이벤트 종류 지정
  - `handler` : `type` 의 이벤트 발생 시 실행할 함수 지정
  - `useCapture` : 이벤트 캡쳐링 발생 여부 (기본 : `false` → 버블링 발생)
- **`removeEventListener()` : 이벤트 핸들러 제거**

### 5. 이벤트 버블링(Event Bubbling)과 캡처 (Capture)

- **캡처링(capturing)** : 이벤트가 하위 요소로 전파되는 단계
- **버블링(bubbling)** : 이벤트가 상위 요소로 전파되는 단계
- **버블링 중단하기** : **`event.stopPropagation()`**
  - 웹 브라우저는 이벤트 캡처링 먼저 실행 후 버블링 실행

### 6. 매개변수 event

- 이벤트 핸들러 함수에서 매개변수 `event` 통해 이벤트에 대한 정보 얻음
- 이벤트가 발생한 요소, 이벤트 종류 등의 정보 포함
- **종류**

  - **`event.target` : 이벤트 발생한 요소 정보 저장**

    ```html
    <!DOCTYPE html>
    <html>
      <body>
        <button id="btn">클릭</button>

        <script>
          const btn = document.querySelector("#btn");
          btn.addEventListener("click", (event) => {
            console.log(event.target);
          });
        </script>
      </body>
    </html>
    ```

  - **`event.currentTarget` : 속성에 이벤트 리스너가 붙은 요소 정보 저장**

    ```html
    <!DOCTYPE html>
    <html>
      <body>
        <div id="box">
          div Box
          <button id="btn">클릭</button>
        </div>

        <script>
          const box = document.querySelector("#box");
          const btn = document.querySelector("#btn");
          box.addEventListener("click", (event) => {
            console.log(`target: ${event.target}`);
            console.log(`currentTarget: ${event.currentTarget}`);
          });
          btn.addEventListener("click", (event) => {
            console.log(`target: ${event.target}`);
            console.log(`currentTarget: ${event.currentTarget}`);
          });
        </script>
      </body>
    </html>
    ```

### 7. 이벤트 위임 **(Event Delegation)**

- 비슷한 방식으로 여러 요소를 다뤄야할 때 사용
- 요소마다 핸들러를 할당하지 않고, 요소의 공통 조상에 이벤트 핸들러를 단 하나만 할당해도 여러 요소를 한꺼번에 다룰 수 있음
