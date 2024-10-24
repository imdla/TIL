## <mark color="#fbc956">Async와 Await</mark>

### 1. Async와 Await

- `promise` 를 쉽고 직관적으로 사용할 수 있는 키워드
- **`async` 키워드** : 함수 앞에 붙을 수 있음, 해당 함수의 반환 값은 `promise`
- **`await` 키워드** : `async` 함수 안에서 사용할 수 있는 키워드
  - 비동기 작업 끝날 때까지 함수 동작 멈추고, 비동기 작업의 완료 기다림

### 2. async 함수

- `function` 앞에 `async`를 붙이면 해당 함수는 항상 `Promise`를 반환함

```jsx
async function asyncFunc() {
  return "완료";
}

asyncFunc().then(console.log); // 완료
```

### 3. await

- `async` 함수 안에서만 작동,
- `Promise`가 처리될 때까지 함수 실행을 기다리게 하여 `Promise`가 처리되면 그 결과와 함께 실행 재개됨

```jsx
async function asyncFunc() {
  let promise = new Promise((resolve, reject) => {
    setTimeout(() => resolve("완료"), 1000);
  });

  let result = await promise;

  console.log(result);
}

asyncFunc(); // 완료
```

- 예외처리 `.catch()` 필요시 `try...catch` 문 사용

```jsx
const getPromise = (seconds) =>
  new Promise((resolve, reject) => {
    setTimeout(() => {
      reject("에러");
    }, seconds * 1000);
  });

async function runner() {
  try {
    const result1 = await getPromise(1);
    console.log(result1);
    const result2 = await getPromise(2);
    console.log(result2);
    const result3 = await getPromise(3);
    console.log(result3);
  } catch (error) {
    console.log("---catch error---");
    console.log(error);
  } finally {
    console.log("---finally---");
  }
}

runner();
/**
---catch error---
에러
---finally---
*/
```
