## <mark color="#fbc956">try…catch (예외처리)</mark>

### 1. 예외처리

- 프로그램 실행 중에 발생하는 오류 처리 기술
- 오류(예외)발생 시 프로그램이 종료되지만 예외처리 통해 프로그램 종료 대신 적절한 대처 할 수 있음
  - 발생시킬 때 ⇒ 던짐 (`throw`)
  - 명시적으로 인지할 때 ⇒ 잡음 (`catch`)

### 2. 예외처리 기본 구조

- `try` : 오류 발생 가능성 있는 코드 작성
- `catch` : 오류 발생 시 실행할 코드 작성
- `finally` : 오류 발생 여부와 상관없이 실행할 코드 작성
- `throw` : 예외를 강제로 발생시킴
  - 사용자가 정의한 예외 만들 때 활용, 예외 객체 `Error` 를 던짐

```jsx
try {
  // 오류 발생 가능성 있는 코드
} catch (error) {
  // 오류 발생 시 실행 코드
} finally {
  // 오류 발생과 상관없이 무조건 실행하는 코드
}
```

```jsx
function runner() {
  try {
    // 오류 발생 가능성이 있는 코드 작성
    console.log("Hello");

    // 예외를 강제로 발생
    throw new Error("문제 발생");

    console.log("Welcome"); // 에러 발생 시 try 내부의 error 다음 구문 실행 안됨
  } catch (error) {
    // 에러 발생 시 실행
    console.log("---catch---");
    console.log(error); // 에러 내용 보여줌
  } finally {
    // 에러 발생에 상관없이 항상 실행 (선택)
    console.log("---finally---");
  }
}

runner();
```
