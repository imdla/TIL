> 💡 **한 줄 요약**
>
> `useRef()` 와 `let` 으로 선언한 변수는 리렌더링 시 동작 방식이 다른데, `let` 은 컴포넌트 리렌더링 시마다 초기화되어 이전 값을 잃어버리지만, `useRef()` 는 리렌더링되어도 값이 유지된다.

### 1. 🤔 왜 사용하는가

> **`useRef()` 와 `let` 의 차이점 ⇒ 리렌더링 시 동작 방식**

- **`let` 으로 선언한 변수**
  - 컴포넌트가 리렌더링될 때마다 초기화되어 이전 값을 잃어버림
- **`useRef()` 로 만든 변수**
  - 리렌더링되어도 값이 유지됨
  - `useState()` 와 달리 상태 값이 변경되어도 컴포넌트가 리렌더링이 유발되지 않음
  - 활용
    1. 주로 DOM 요소에 접근할 때 사용
       - `input` 에 focus 주는 경우
       - 스크롤 위치 조정하는 경우
    2. `setTimeout()` 이나 `setInterval()` 의 ID 저장
       - 컴포넌트가 리렌더링되어도 타이머 ID가 유지되어야 cleanup 함수에서 해당 값을 이용해 타이머 정리 가능

### 2. 💡 무엇인지 아는가(특징)

> **컴포넌트 외부에서 `let` 선언 시 리렌더링 영향 받지 않음**

- 리렌더링 영향 받지 않지만 권장되지 않는 패턴
- 이유
  1. 컴포넌트 외부의 변수는 모든 컴포넌트 인스턴스가 공유함
     - 같은 컴포넌트를 여러 번 사용할 때 예상치 못한 동작 발생
  2. 전역 변수처럼 동작해 코드의 에측 가능성과 유지보수성 떨어짐
  3. 리액트의 원칙 중 하나인 단방향 데이터 흐름에 위배
     - 상태 관리가 리액트의 생명주기 밖에서 이루어지기 때문

⇒ 컴포넌트 상태 관리 시 `useState()` 나 `useRef()` 사용 권장
