## <mark color="#fbc956">Execution Context (실행 컨텍스트)</mark>

### 1. Execution Context (실행 컨텍스트)

> 💡 **Execution Context ?**
>
> 코드 실행에 필요한 모든 데이터를 담고 있는 환경
> 실행하려는 JS 코드와 코드를 실행했을 때 필요한 정보를 담고있는 특수한 환경

### 2. 실행 컨텍스트(Execution Context) 타입

- **Global Context**
  - 최상위 execution context
  - 코드 실행하면 무조건 생성되는 context
  - 웹에서의 `window` 객체나
    node.js에서의 `global` 객체를 생성하고 들고 있음
- **Function Context**
  - 함수가 실행될 때 마다 함수별로 실행되는 context
  - 함수 실행에 대한 모든 정보를 담고 있음

```jsx
function one() {
  console.log("run one"); // 2-2-1번
  console.log("run one finished"); // 2-2-2번
}

function two() {
  console.log("run two"); // 2-1번
  one(); // 2-2번 => one 함수 실행
  console.log("run two finished"); // 2-3번
}

function three() {
  console.log("run three"); // 1번
  two(); // 2번 => two 함수 실행
  console.log("run three finished"); // 3번
}

three();
```

- 위와 같이 실행할 경우, 아래와 같이 동작함
  - `run three` → `three()` 실행
  - `run two` → `two()` 실행
  - `run one` → `one()` 실행
  - `run one finished` → `one()` 종료
  - `run two finished` → `two()` 종료
  - `run three finished` → `three()` 종료

### 3. 비동기 처리 메커니즘

- **JavaScript 런타임 환경**
  - **콜 스택(Call Stack)**
    - 호출된 함수가 동기적으로 실행되는 메모리 공간
    - 하나만 존재해 한 번에 하나의 작업만 처리 가능
  - **콜백 큐(Callback Queue) / 이벤트 큐(Event Queue) / 테스크 큐(Task Queue)**
    - 비동기 작업 완료 후 실행 기다리는 콜백 함수가 저장되는 메모리 공간
    - 콜 스택이 비어있을 때 이벤트 큐에 있는 콜백 함수가 콜 스택으로 이동 후 실행
  - **이벤트 루프(Event Loop)**
    - 콜백 큐에 있는 함수를 콜 스택으로 이동시키는 작업 진행
    - 위 작업 지속적으로 수행해 비동기 작업 처리
  - **Web APIs**
    - 웹 브라우저 의해 처리되는 비동기 작업 도구 모임
    - 콜백 함수를 비동기로 처리, 처리 끝나면 콜백 함수를 이벤트 큐로 전달
    - 멀티 스레드 환경으로 백그라운드에서 콜백 함수를 비동기로 처리 가능
    - 실질적으로 콜백 함수의 비동기 작업을 처리하는 파트
    - 기능
      - `Fetch` : 네트워크 요청 처리
      - `Geolocation API` : 위치 정보 처리
      - `setTimeout` : 일정 시간 후 콜백 함수 실행
      - `setInterval` : 일정 시간 마다 콜백 함수 실행

### 4. Call Stack이 생성될 때

- **Creation Phase**
  1. `Global Object` 생성
     - `window` 또는 `global` 객체 생성되고 함수에서 `arguments` 객체 생성
  2. `this`를 `window` 또는 `global`에 바인딩
  3. 변수와 함수를 `Memory Heap`에 배정, 기본값을 `undefined`로 저장

     → 호이스팅 발생 이유
- **Execution Phase**
  1. 코드 실행
  2. 필요 시 `Execution Context` 생성

```jsx
// 1번 (실행순서 - 아래 설명글 참고)
var num1 = 20; // 2번
var num2 = 30; // 3번

function multiply(x, y) {
  var result = x * y; // 5번, 7번

  return result;
}

var result1 = multiply(num1, num2); // 4번
var result2 = multiply(100, 200); // 6번
```

위와 같이 실행할 경우 아래와 같이 동작함

- **1. 실행**
  - **Global Scope**
    - `num1` : undefined
    - `num2` : undefined
    - `multiply` : multiply(x, y)
    - `result1` : undefined
    - `result2` : undefined
    **⇒ `Global Object` 생성, 선언된 변수와 함수 `Memory Heap`에 배정됨**
    → 호이스팅 발생 이유
- **2. `var num1 = 20;` 실행**
  - **Global Scope**
    - `num1` : 20
      - **Execution Phase**에서 \*\*\*\*`num1` 변수 실행
    - `num2` : undefined
    - `multiply` : multiply(x, y)
    - `result1` : undefined
    - `result2` : undefined
- **3. `var num2 = 30;` 실행**
  - **Global Scope**
    - `num1` : 20
    - `num2` : 30
      - **Execution Phase**에서 \*\*\*\*`num2` 변수 실행
    - `multiply` : multiply(x, y)
    - `result1` : undefined
    - `result2` : undefined
- **4. `var result1 = multiply(num1, num2);` 실행**
  - **Local Scope**
    - `this` : window
    - `result` : undefined
    - `x` : 20
    - `y` : 30
  - **Global Scope**
    - `num1` : 20
    - `num2` : 30
    - `multiply` : multiply(x, y)
    - `result1` : undefined
    - `result2` : undefined
- **5. `var result1 = multiply(num1, num2);` 실행에서**
  **`var result = x * y;` 실행**
  - **Local Scope**
    - `this` : window
    - `result` : 600
    - `x` : 20
    - `y` : 30
  - **Global Scope**
    - `num1` : 20
    - `num2` : 30
    - `multiply` : multiply(x, y)
    - `result1` : 600
    - `result2` : undefined
- **6. `var result2 = multiply(100, 200);` 실행에서**
  - **Global Scope**
    - `num1` : 20
    - `num2` : 30
    - `multiply` : multiply(x, y)
    - `result1` : 600
    - `result2` : undefined
- **7. `var result1 = multiply(num1, num2);` 실행에서**
  **`var result = x * y;` 실행**
  - **Local Scope**
    - `this` : window
    - `result` : undefined
    - `x` : 100
    - `y` : 200
  - **Global Scope**
    - `num1` : 20
    - `num2` : 30
    - `multiply` : multiply(x, y)
    - `result1` : 600
    - `result2` : 20000
