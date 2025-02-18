> 💡 **한 줄 요약**
>
> `resolve()` 는 Promise를 완료시키는 함수로, Promise가 성공적으로 끝났을 때 결과 값을 넘겨주는 함수이다. `fulfilled` 는 `resolve()` 가 호출되면, 그 결과로 발생하는 완료된 상태를 의미한다.

### 1. 🤔 왜 사용하는가

- **`resolve()`**

  - Promise를 **완료시키는 함수**
  - Promise가 성공적으로 끝났을 때 결과 값을 넘겨주는 함수
  - Promise를 성공적으로 마무리 짓는 행위

- **`fulfilled`**
  - 해당 Promise가 **완료된 상태**
  - Promise 완료로 발생하는 완료된 상태

> **예시 1**

1. 어떤 비동기 작업 완료
2. `resolve()` 호출
   - “작업 끝남, 결과 전달”
   - Promise 상태는 ‘이행됨’으로 변경 ⇒ `fulfilled`

### 2. 💡 무엇인지 아는가(특징)

> **`resolve()` 작업 실패 ?**

- **`resolve()` 가 실패하는 상황은 발생하지 않음**
  - `resolve()` 는 Promise를 이행(`fulfilled`)으로 만드는 함수
  - 성공적인 결과를 전달할 때 사용 (실패 자체와 관련 없음)
- **Promise 실패 시 → `reject()` 호출**
- `then()` : `resolve()` 된 값을 처리
- `catch()` : `reject()` 된 오류를 처리
