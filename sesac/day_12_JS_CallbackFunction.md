## <mark color="#fbc956">Callback Function (콜백 함수)</mark>

### 1. Callback Function (콜백 함수)

> 💡 **Callback Function ?**
>
> 콜백은 적절한 시점에 호출될 것으로 예상하여 다른 함수로 전달되는 함수

- 다른 함수의 인자로 전달되어 특정 작업이 완료된 후 실행되는 함수
- 배열 메서드, 비동기 처리 등에서 사용

```jsx
function waitAndRun() {
  setTimeout(() => {
    console.log("완료");
  }, 2000);
}

waitAndRun(); // 완료
```

### 2. 일반 함수와 비교

- **일반 함수**
  - 즉시 실행
- **콜백 함수**
  - 특정 이벤트나 작업이 완료된 후 실행
  - 비동기 처리 상황에서 순차적인 동작 보장 위해 사용

### 3. 콜백 속 콜백

- 비동기 작업 처리하는 콜백 함수 중첩되어 코드가 복잡해지고 가독성 떨어짐

```jsx
function waitAndRun2() {
  setTimeout(() => {
    console.log("1번 콜백 완료");
    setTimeout(() => {
      console.log("2번 콜백 완료");
      setTimeout(() => {
        console.log("3번 콜백 완료");
      }, 2000);
    }, 2000);
  }, 2000);
}

waitAndRun2();
/**
1번 콜백 완료
2번 콜백 완료
3번 콜백 완료
*/
```

⇒ 콜백 중첩 시 오류 처리가 어려워 질 수 있음, 상위 레벨에서 한 번만 오류를 처리하는 대신 피라미드의 각 레벨에서 오류를 처리해야하는 경우 발생

⇒ 콜백 지옥

---

## <mark color="#fbc956">Higher-Order Function (고차 함수)</mark>

### 1. Higher-Order Function (고차 함수)

- 함수를 인자로 받거나 다른 함수를 반환하는 함수
- JavaScript 함수는 일급개체로 함수를 값처럼 사용 가능

### 2. First-Class Citizen (일급 객체)

- 값처럼 사용 가능
- JavaScript 함수는 일급 개체로 변수에 할당되거나 다른 함수의 인자로 전달되거나 함수의 반환값에 사용 가능

### 3. 일급 객체 특징

1. **변수에 할당**

   - 함수를 변수에 할당해 값을 저장하거나 사용 가능

   ```jsx
   const greet = function (name) {
     return `안녕하세요, ${name}님`;
   };

   console.log(greet("홍길동"));
   ```

2. **함수의 인자로 전달**

   - 함수를 다른 함수의 인자로 전달할 수 있으며, 이는 콜백 함수 구현 시 유용

   ```jsx
   function greet() {
     console.log("안녕하세요 반갑습니다.");
   }

   function greetAndRun(callbackFunc) {
     console.log("접속");
     callbackFunc();
     console.log("시작");
   }

   greetAndRun(greet);
   ```

3. **함수의 반환값으로 사용**

   - 함수는 다른 함수의 반환값으로 사용될 수 있으며, 이로 인해 동적 함수 생성 가능

   ```jsx
   const greet = (name) => {
     return (id) => `${name}님 Id: ${id} 확인되었습니다.`;
   };

   const nameInput = greet("홍길동");

   console.log(nameInput("dong"));
   ```

4. **데이터 구조에 저장 가능**

   - 함수는 배열이나 객체와 같은 데이터 구조에 저장 가능

   ```jsx
   const operations = {
     add: (a, b) => a + b,
     multiply: (a, b) => a * b,
   };

   console.log(operations.add(1, 2));
   console.log(operations.multiply(1, 2));
   ```
