## <mark color="#fbc956">Condition (조건문)</mark>

### 1. 조건문

- 논리적 평가를 통해 코드 실행 여부를 결정하는 코드 제어문
- 조건식이 `true`(참)일 때 코드 블록 실행, `false`(거짓)일 때 실행되지 않음

### 2. 조건문 구조

- 조건식은 소괄호 `()` 안에 작성
- 조건식이 `true`(참)일 때 실행될 코드 블록은 중괄호 `{}` 로 구분

```jsx
if (조건식) {
  // 조건식 참일 때 실행될 코드
}
```

### 3. if … else 문

- **`if` 문**
  - 조건이 `true` (참)일 경우 코드 블록 실행
  ```jsx
  if (조건) {
    // 조건(condition)이 참(true)일 경우 실행할 코드
  }

  // 조건식과 관계없이 실행할 다른 코드
  ```
- **`if else` 문**
  - "**만약** **조건**이 `true`면, 코드 A를 실행하고, **아니면**(`false`) 코드 B를 실행한다."
  ```jsx
  if (조건) {
    // 코드 A
    // 조건(condition)이 참(true)일 경우 실행할 코드
  } else {
    // 코드 B
    // 조건(condition)이 거짓(false)일 경우 실행할 코드
  }
  ```
  - 짧은 스타일 - 중괄호 없이 쓰여진 `if...else`
    ```jsx
    if (조건) 만약 조건(condition)이 참일 경우 실행할 코드
    else 대신 실행할 다른 코드
    ```
- **`else if` 문**
  - 조건식이 여러 개 필요할 때 사용
  - 조건식을 위에서 하나씩 평가하며, 참인 조건식 있다면 다른 조건식 평가하지 않음
  ```jsx
  if (조건1) {
    // 조건1이 참(true)일 경우 실행할 코드
  } else if (조건2) {
    // 조건2이 참(true)일 경우 실행할 코드
  } else if (조건3) {
    // 조건3이 참(true)일 경우 실행할 코드
  } else if (조건4) {
    // 조건4이 참(true)일 경우 실행할 코드
  } else {
    // 아무 조건도 참(true)이지 않을 경우 실행할 코드
  }
  ```

### 4. switch 문

- 입력으로 하나의 표현식/값을 받고 , 값과 일치하는 하나를 찾을 때까지 여러 항목을 살펴보고 그에 맞는 코드를 실행
- `break` : 다음 case 실행 방지
- `default` : 일치하는 case 없을 때 실행

```jsx
switch (expression) {
  case 값1:
    // run this code
    break;

  case 값2:
    // run this code instead
    break;

  // ... 원하는 만큼 많은 case를 포함

  default:
  // actually, just run this code
}
```
