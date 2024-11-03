## <mark color="#fbc956">Recursion and Stack (재귀와 스택)</mark>

### 1. 재귀 (Recursion)

- 함수에서 다른 함수를 호출할 때, 함수가 자기 자신을 호출함

- `x` 를 `n` 제곱해주는 함수 `pow(x, n)` 을 만들 때, 아래 결과를 만족해야 함
  ```jsx
  pow(2, 2) = 4
  pow(2, 3) = 8
  pow(2, 4) = 16
  ```
- 이를 구현하는 두 가지 방법
  1. **반복적인 사고를 통한 방법** : `for` 루프

     ```jsx
     function pow(x, n) {
       let result = 1;

       for (let i = 0; i < n; i++) {
         result *= x;
       }

       return result;
     }

     console.log(pow(2, 3)); // 8
     ```

  2. **재귀적인 사고를 통한 방법** : 작업을 단순화하고 자기 자신을 호출

     ```jsx
     function pow(x, n) {
       // n == 1 일때
       // 명확한 결괏값을 즉시 도출 => 재귀의 베이스(base)
       if (n == 1) {
         return x;

         // n == 1 이 아닐 때
         // 재귀 단계(recursive step)
       } else {
         return x * pow(x, n - 1);
       }
     }

     console.log(pow(2, 3)); // 8
     ```
  ⇒ `pow` 는 `n == 1` 이 될 때까지 재귀적으로 자신을 호출함

> **재귀를 사용한 코드는 짧음**
> 재귀를 사용한 코드는 반복적 사고에 근거하여 작성한 코드보다 대개 짧음
>
> ```jsx
> function pow(x, n) {
>   return n == 1 ? x : x * pow(x, n - 1);
> }
> ```

### 2. 실행 컨텍스트**(Execution Context)**와 스택(Stack)

- **실행 컨텍스트(execution context)**
  - 함수 실행에 대한 세부 정보를 담고 있는 내부 데이터 구조
  - 제어 흐름의 현재 위치, 변수의 현재 값, `this`의 값 등 상세 내부 정보가 저장됨

### 3. 재귀 호출 함수의 내부 동작

- 재귀 호출이 어떻게 동작하는지 알아보기 위해 **함수의 내부 동작**에 대해 살펴봄
  - **함수 내부에 중첩 호출이 있을 때 절차**
    1. 현재 함수 실행 일시 중지
    2. 중지된 함수와 연관된 실행 컨텍스트

       ⇒ 실행 컨텍스트 스택(execution context stack)에 저장

    3. 중첩 호출 실행
    4. 중첩 호출 실행 끝난 후

       ⇒ 실행 컨텍스트 스택에서 일시 중단한 함수의 실행 컨텍스트 꺼내옴,
       중단한 함수 실행 이어감
- **`pow(2, 3)` 이 호출될 때 실행 컨텍스트의 흐름**
  1. **pow(2, 3)**

     - `pow(2, 3)` 호출 시, 실행 컨텍스트에 변수 `x = 2, n = 3` 이 저장됨
     - 실행 흐름은 함수의 첫 번째 줄에 위치함

     > **Context : { x : 2, n : 3, 첫 번째 줄 }** ⇒ call : pow(2, 3)

     - 현재 상태는 `n == 1` 을 만족하지 못함, 실행 흐름은 `if` 의 두 번째 분기로 넘어감

     ```jsx
     function pow(x, n) {
       // 1
       if (n == 1) {
         // 2
         return x; // 3
       } else {
         // 4
         return x * pow(x, n - 1); // 5
       }
     }

     console.log(pow(2, 3));
     ```

     - 변수는 동일하지만, 실행 흐름의 위치가 변경되며 실행 컨텍스트는 아래와 같이 변경

     > **Context : { x : 2, n : 3, 다섯 번째 줄 }** ⇒ call : pow(2, 3)

     - `x * pow (x, n - 1)` 을 계산하기 위해 새로운 인수 들어가는 `pow` 의 서브 호출(sub call)인 `pow(2, 2)` 만들어야 함

  2. **pow(2, 2)**

     - 중첩 호출 위해, 실행 컨텍스트 스택에 현재 실행 컨텍스트 저장
     - 서브 호출 `pow(2, 2)` 이 시작될 때 실행 컨텍스트 스택

     > **Context : { x : 2, n : 2, 첫 번째 줄 }** ⇒ call : pow(2, 2) **_새 실행 컨텍스트 (상단)_**

     > Context : { x : 2, n : 3, 다섯 번째 줄 } ⇒ call : pow(2, 2) **_기존 컨텍스트 (하단)_**

     - 이전 컨텍스트에 변수 정보, 코드 일시 중단된 줄에 대한 정보 저장되어 있어 서브 호출 끝났을 때 이전 컨텍스트 다시 시작됨

  3. **pow(2, 1)**

     - 동일 과정 다시 반복
     - 다섯 번째 줄에서 인수 `x = 2`, `n = 1` 함께 새로운 서브 호출 만들어짐
     - 새로운 실행 컨텍스트 만들어지고, 이전 실행 컨텍스트는 스택 최상단으로 올라감 (push)

     > **Context : { x : 2, n : 1, 첫 번째 줄 }** ⇒ call : pow(2, 1) **_새 실행 컨텍스트_**

     > Context : { x : 2, n : 2, 다섯 번째 줄 } ⇒ call : pow(2, 2) **_기존 컨텍스트_**

     > Context : { x : 2, n : 3, 다섯 번째 줄 } ⇒ call : pow(2, 2) **_기존 컨텍스트_**

  4. **실행 종료**

     - `pow(2, 1)` 이 실행될 때, 조건 `n == 1` 을 만족시키므로 `if` 문의 첫 번째 분기 실행됨

     ```jsx
     function pow(x, n) {
       if (n == 1) {
         return x;
       } else {
         return x * pow(x, n - 1);
       }
     }
     ```

     ⇒ 호출해야 할 중첩 호출 없음, 함수 종료되고 `2` 반환됨

     - 함수 종료되어 실행 컨텍스트는 메모리에서 삭제됨
     - 스택 맨위엔 이전 실행 컨텍스트 위치

     > **Context : { x : 2, n : 2, 다섯 번째 줄 }** ⇒ call : pow(2, 2) **_기존 컨텍스트_**

     > Context : { x : 2, n : 3, 다섯 번째 줄 } ⇒ call : pow(2, 2) **_기존 컨텍스트_**

     - `pow(2, 2)` 실행 다시 시작, `x * pow(x , n - 1)` 을 계산해 `4` 반환

     ⇒ 다시 이전 컨텍스트가 최상단에 위치

     > **Context : { x : 2, n : 3, 다섯 번째 줄 }** ⇒ call : pow(2, 2) **_기존 컨텍스트_**

     - 마지막 실행 컨텍스트 처리되면 `pow(2, 3) = 8` 이라는 결과 도출

  5. **재귀의 메모리**

     - 위 예시의 재귀 깊이는 3, 재귀 깊이는 스택에 들어가는 실행 컨텍스트의 최댓값과 같음

     ⇒ 실행 컨택스트는 메모리를 차지하여 재귀 사용 시 메모리 요구 사항에 유의

### 4. 반복문 기반의 메모리 사용

- **반복문 기반 알고리즘 사용 시 메모리 절약**
  ```jsx
  function pow(x, n) {
    let result = 1;

    for (let i = 0; i < n; i++) {
      result *= x;
    }

    return result;
  }
  ```
  - 반복을 사용해 만든 함수 `pow` 는 컨텍스트 하나만 사용함 (`i` 와 `result` 가 변경됨)
  - 실행 컨텍스트가 하나로 `n` 에 의존적이지 않고, 필요 메모리가 적음

### 5. 재귀적 순회

```jsx
let company = {
  sales: [
    {
      name: "John",
      salary: 1000,
    },
    {
      name: "Alice",
      salary: 1600,
    },
  ],

  development: {
    sites: [
      {
        name: "Peter",
        salary: 2000,
      },
      {
        name: "Alex",
        salary: 1800,
      },
    ],

    internals: [
      {
        name: "Jack",
        salary: 1300,
      },
    ],
  },
};

let company = {
  // 동일한 객체(간결성을 위해 약간 압축함)
  sales: [
    { name: "John", salary: 1000 },
    { name: "Alice", salary: 1600 },
  ],
  development: {
    sites: [
      { name: "Peter", salary: 2000 },
      { name: "Alex", salary: 1800 },
    ],
    internals: [{ name: "Jack", salary: 1300 }],
  },
};

function sumSalaries(department) {
  if (Array.isArray(department)) {
    // 첫 번째 경우
    return department.reduce((prev, current) => prev + current.salary, 0); // 배열의 요소를 합함
  } else {
    // 두 번째 경우
    let sum = 0;
    for (let subdep of Object.values(department)) {
      sum += sumSalaries(subdep); // 재귀 호출로 각 하위 부서 임직원의 급여 총합을 구함
    }
    return sum;
  }
}
```
