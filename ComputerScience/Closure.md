> 💡 **한 줄 요약**
>
> 클로저는 함수 선언될 때 스코프를 기억해 함수 생성 이후에도 그 스코프에 접근할 수 있는 기능으로 데이터 은닉, 비동기 작업, 모듈 패턴 구현에 활용된다.
>
> 또한, 클로저는 자바스크립트의 일급 객체 특성과 렉시컬 스코프의 조합으로 만들어진다.

### 1. 🤔 왜 사용하는가

- 함수가 선언될 때 스코프를 기억해, 함수가 생성된 이후에도 그 스코프에 접근할 수 있는 기능
- 자바스크립트의 **일급 객체** 특성 + **렉시컬 스코프**의 조합으로 만들어짐

### 2. 💡 무엇인지 아는가(특징)

- **클로저 동작 방식**

  ```jsx
  function outerFunction(outerVariable) {
    return function innerFunction(innerVariable) {
      console.log("Outer Variable: " + outerVariable);
      console.log("Inner Variable: " + innerVariable);
    };
  }

  const newFunction = outerFunction("outside");
  newFunction("inside");
  ```

  - `innerFunction` 은 `outerFunction` 내부에 정의되어 있음
  - `innerFunction` 은 자신이 생성된 스코프인 `outerFunction` 의 스코프를 기억해 `outerFunction` 의 호출이 완료된 이후에도 그 스코프에 접근 가능
  - `innerFunction` 은 `outerVariable` 에도 접근 가능

- **클로저 활용**
  - 변수와 함수의 접근 범위 제어하고 특정 데이터와 상태 유지위해 활용

1. **데이터 은닉**
   - 클로저는 외부에서 접근할 수 없는 비공개 변수와 함수를 만들 수 있음
   - 데이터를 은닉해 외부 접근 막음
     → 데이터 무결성 유지 가능
   - 특정 함수 내부에서만 접근 가능한 변수 생성해, 이를 조작할 수 있는 함수만 외부로 노출해 안전한 데이터 관리 가능
2. **비동기 작업**
   - 클로저는 비동기 작업에서 이전의 실행 컨텍스트 유지할 때 유용
   - 콜백 함수가 비동기적으로 실행될 때 클로저 사용 시 함수 실행 시점의 변수 참조 가능
3. **모듈 패턴 구현**
   - 모듈 패턴
     - 특정 기능 캡슐화
     - 외부에 공개하고자 하는 부분만 선택적 노출
     → 코드의 응집력 높임, 유지보수성 향상
   - 필요한 함수와 데이터만 외부로 노출해 모듈 패턴 쉽게 구현 가능

### 3. ✅ 장점

- 데이터 무결성 유지
- 코드 응집력 향상
- 유지보수성 향상