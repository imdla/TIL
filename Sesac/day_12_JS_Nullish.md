## <mark color="#fbc956">Nullish 병합 연산자</mark>

### 1. Nullish 병합 연산자

- 값이 `null` 또는 `undefined` 일 때 기본값을 설정하는 연산자
- OR 논리 연산자와 비교
  - OR 논리 연산자 `||` : `false` 인 값을 기본값으로 대체
  - Nullish 연산자 `??` : 오직 `null` 과 `undefined` 만 대체

### 2. Nullish 병합 연산자 사용

- **`??`**

  ```jsx
  let nickname = null;
  let defaultName = "용궁의 거북이";

  let name = nickname ?? defaultName;
  console.log(name); // 용궁의 거북이
  ```

- OR 논리 연산자와 비교

  ```jsx
  let wakeup = 0;
  let defaultWakeup = 7;

  let alarmOr = wakeup || defaultWakeup;
  console.log(alarmOr); // 7

  let alarmNullish = wakeup ?? defaultWakeup;
  console.log(alarmNullish); // 0
  ```

- 옵셔널 체이닝과 Nullish 병합 연산자 활용

  - 객체에 존재하지 않는 속성에 접근 시 `undefined` 대신 기본값으로 대체

  ```jsx
  const alarm = {
    user: "홍길동",
    time: {},
  };

  let defaultWakeup = 7;

  let wakeup = alarm?.time?.wakeup ?? defaultWakeup;
  console.log(wakeup); // 7
  ```
