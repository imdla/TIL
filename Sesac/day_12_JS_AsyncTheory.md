## <mark color="#fbc956">Async theory</mark>

> JS는 Single Threaded로 어느 한 순간 동시에 단 하나의 작업만 실행 가능
> ⇒ 비동기 프로그래밍을 통해 유동적으로 작업

### 1. 비동기 처리

- 동기적 구조 : 코드의 실행이 끝나면 다음 코드 실행되는 구조
- 비동기적 구조 : 코드가 실행되는 동안 다른 코드가 실행되는 구조
- JavaScript는 싱글 스레드 기반, 동기적으로 처리되어 동시에 여러 작업 처리 할 수 없음
  ⇒ 이를 해결 위해 네트워크 요청과 같은 시간이 오래 걸리는 작업은 비동기로 처리
  > 💡 **Single Threaded ?**
  >
  > 한 번에 하나(single)의 작업만 처리하는 구조

### 1. Async Programming (비동기 프로그래밍)

> 💡 **Async Programming ?**
> 비동기 프로그래밍은 작업이 완료될 때까지 기다리지 않고 잠재적으로 오래 실행되는 작업을 시작하여 해당 작업이 실행되는 동안에도 다른 이벤트에 응답할 수 있게 하는 기술,
> 작업이 완료되는 프로그램이 결과를 제공

- `setTimeout(func, time)`
  - 지정된 시간(밀리 초 단위)이 지난 후 (콜백) 함수 실행
  ```jsx
  function longWork() {
    setTimeout(() => {
      console.log("완료");
    }, 2000);
  }

  console.log("Hello"); // Hello ***실행 순서 1번***
  longWork(); // 완료 ***실행 순서 3번***
  console.log("World"); // World ***실행 순서 2번***
  ```
- 함수를 호출함으로써 장기적으로 실행되는 작업 시작
  → 이 함수로 작업을 시작하고 즉시 복귀해 다른 이벤트에 계속 응답
  → 작업이 완료되면 결과 알려줌
- `setInterval(func, time)`
  - 지정된 시간(밀리 초 단위) 마다 (콜백) 함수 반복적 실행
  ```jsx
  let count = 0;

  function workCount() {
    setInterval(() => {
      console.log(count);
      count++;
    }, 1000);
  }

  console.log("Hello"); // ***실행 순서 1번***
  workCount();
  console.log("World"); // ***실행 순서 2번***
  // Hello
  // World
  // 0
  // 1
  // 2
  // 3
  // 4
  // 5
  // 6
  // 7
  // 8
  // 9
  ```

### 2. Sync Programming (동기 프로그래밍)

> 💡 **Sync Programming ?**
>
> 동기 프로그래밍은 실질적으로 프로그램을 작성한 순서대로 한줄 씩 실행

```jsx
function longWork() {
  const now = new Date();

  const milliseconds = now.getTime();
  const afterTwoSeconds = milliseconds + 2 * 1000;

  while (new Date().getTime() < afterTwoSeconds) {}

  console.log("완료");
}

// 프로그램 작성한 순서대로 진행
console.log("Hello"); // Hello ***실행 순서 1번***
longWork(); // 완료 ***실행 순서 2번***
console.log("World"); // World ***실행 순서 3번***
```

⇒ 장기 실행 동기 함수에서 함수가 실행 중인 동안 프로그램이 완전히 응답하지 않음, 다른 것을 할 수 없는 문제점 발생
