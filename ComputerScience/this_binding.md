> 💡 **한 줄 요약**
>
> 자바스크립트의 this는 함수가 호출되는 방식에 따라 값이 달라진다.

### 1. 🤔 왜 사용하는가

- **자바스크립트 `this`**
  → 함수가 호출되는 방식에 따라 값이 달라짐

### 2. 💡 무엇인지 아는가(특징)

> **`this` 바인딩 방식**

1. **전역 호출**

   - 전역에서 함수 호출 시, `this` 전역 객체를 참조해야 함
     - **브라우저 환경**
       - `window` 객체 가리킴
     - **Node.js 환경**
       - `global` 객체 가리킴

   ```jsx
   function globalFunc() {
     console.log(this);
   }
   globalFunc(); // 브라우저 : window, Node.js: global
   ```

2. **메서드 호출**

   - 객체의 메서드로 호출된 함수에서는 `this` 가 해당 객체 참조함

   ```jsx
   const obj = {
     name: "Alice",
     greet: function () {
       console.log(this.name);
     },
   };
   obj.greet(); // "Alice"
   ```

3. **생성자 함수와 클래스**

   - 생성자 함수나 클래스에서 `this` 는 새로 생성되는 객체인 인스턴스를 참조

   ```jsx
   function Person(name) {
     this.name = name;
   }
   const person = new Person("Alice");
   console.log(person.name); // "Alice"
   ```

4. **명시적 바인딩**

   - `call()` , `apply()`, `bind()` 메서드 사용 시 `this` 를 명시적으로 설정 가능

   ```jsx
   function greet() {
     console.log(this.name);
   }
   const user = { name: "Alice" };
   greet.call(user);
   ("Alice");
   ```

5. **화살표 함수**

   - 화살표 함수는 상위 스코프의 `this` 를 상속받음
   - 자체적인 `this` 를 가지지 않아, 사용 위치에 따라 `this` 결정됨

   ```jsx
   const obj = {
     name: "Alice",
     greet: () => console.log(this.name),
   };
   obj.greet(); // undefined ( 전역 this)
   ```

6. **DOM 이벤트 핸들러**

   - DOM 요소의 이벤트 핸들러에서 `this` 는 기본적으로 이벤트를 발생시킨 요소를 참조
   - 화살표 함수 사용 시 상위 스코프의 `this` 참조함

   ```jsx
   button.addEventListener("click", function () {
     console.log(this); // 클릭된 button 요소
   });
   ```
