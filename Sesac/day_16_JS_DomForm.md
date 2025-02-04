## <mark color="#fbc956">DOM Form</mark>

### 1. 폼 제출 이벤트 (submit event)

- 사용자가 입력 폼을 작성하고, 제출 버튼을 클릭하거나 Enter 키를 누를 때 발생하는 이벤트
- `submit` 이벤트는 오직 `form` 태그에서만 발생 및 제어 가능

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
  <body>
    <form id="form-box">
      <label for="name">이름</label>
      <input type="text" id="name" name="name" />
      <button type="submit">제출</button>
    </form>

    <script>
      const form = document.querySelector("#form-box");

      form.addEventListener("submit", () => {
        console.log("form submit event");
      });
    </script>
  </body>
</html>
```

- **`event.preventDefault()`** : 필요에 따라 제출 이벤트 중지 가능

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
  <body>
    <form id="form-box">
      <label for="name">이름</label>
      <input type="text" id="name" name="name" />
      <button type="submit">제출</button>
    </form>

    <script>
      const form = document.querySelector("#form-box");

      form.addEventListener("submit", (event) => {
        event.preventDefault();
        console.log("form submit event");
      });
    </script>
  </body>
</html>
```

- 사용자가 입력 요소(`input`)에 작성한 값은 해당 요소의 `value` 통해 읽고 쓰기 가능

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
  <body>
    <form id="form-box">
      <label for="name">이름</label>
      <input type="text" id="name" name="name" />
      <button type="submit">제출</button>
    </form>

    <script>
      const form = document.querySelector("#form-box");

      form.addEventListener("submit", (event) => {
        event.preventDefault();

        const nameInput = document.querySelector('input[name="name"]');
        console.log(`input value : ${nameInput.value}`);
      });
    </script>
  </body>
</html>
```

### 2. 데이터 유효성 검사(validation)

- 사용자가 입력 폼에 작성한 값이 유효한지 검사하는 과정
- 브라우저는 기본적인 유효성 검사(이메일 등)를 하지만 JavaScript로 추가 유효성 검사 수행 가능

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
  <body>
    <form id="form-box">
      <label for="name">이름</label>
      <input type="text" id="name" name="name" />
      <button type="submit">제출</button>
    </form>

    <script>
      const form = document.querySelector("#form-box");

      form.addEventListener("submit", (event) => {
        const nameInput = document.querySelector('input[name="name"]');

        // 이름 길이 유효성 검사
        if (nameInput.value.length < 5) {
          alert("이름이 짧습니다.");

          // 제출 이벤트 중지
          event.preventDefault();
        } else {
          alert("폼 제출합니다.");
        }
      });
    </script>
  </body>
</html>
```

### 3. 실시간 데이터 처리

- `input` 과 `change` 이벤트 활용해 사용자가 입력한 데이터 실시간 처리 가능
- 사용자의 동작에 반응해 실시간으로 데이터 유효성 검사 가능

1. **`input`**
   - 사용자가 입력 요소의 값 변경할 때 마다 발생
   - 실시간으로 사용자의 동작 감지할 경우 사용
   - 지원 태그 : `<input>` , `<textarea>` , `select`
     (미지원 태그 : `<checkbox>` , `<radio>`)
2. **`change`**

   - 사용자가 입력 끝내고 포커스 다른 곳으로 이동하거나 Enter 키 입력 시 발생
   - 실시간으로 감지 필요한 경우에는 적합하지 않음

   ```html
   <!DOCTYPE html>
   <html lang="en">
     <head>
       <meta charset="UTF-8" />
       <meta name="viewport" content="width=device-width, initial-scale=1.0" />
       <title>Document</title>
     </head>
     <body>
       <form>
         <label for="text-input">텍스트 입력:</label>
         <input type="text" id="text-input" />

         <div class="output">
           <p>input 이벤트 : <span id="input-output"></span></p>
           <p>change 이벤트 : <span id="change-output"></span></p>
         </div>
       </form>

       <script>
         const textInput = document.querySelector("#text-input");
         const inputOutput = document.querySelector("#input-output");
         const changeOutput = document.querySelector("#change-output");

         // input 이벤트
         textInput.addEventListener("input", () => {
           inputOutput.textContent = textInput.value;
         });

         // change 이벤트
         textInput.addEventListener("change", () => {
           changeOutput.textContent = textInput.value;
         });
       </script>
     </body>
   </html>
   ```
