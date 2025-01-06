> 💡 **한 줄 요약**
>
> Controlled Componenet는 리액트 state를 통해 입력값을 제어하는 컴포넌트로, 변경할 때 마다 onChange 이벤트 핸들러 통해 상태 업데이트 할 수 있다.
>
> Uncontrolled Component는 리액트 state가 아닌 DOM 자체가 입력값을 제어하는 방식으로, `ref` 사용해 DOM 요소에 직접 접근해 값을 읽거나 조작할 수 있다.

### 1. 🤔 왜 사용하는가

- **Controlled Component**

  - 리액트 state를 통해 입력값을 제어하는 컴포넌트
  - 입력 요소의 값(value)을 리액트 상태와 동기화, 사용자가 입력을 변경할 때 마다 onChange 이벤트 핸들러를 통해 상태 업데이트
  - ex. useState 활용한 input value 제어

- **Uncontrolled Component**
  - 리액트의 상태가 아닌 DOM 자체가 입력값을 제어하는 방식
  - 입력 요소의 값은 DOM에서 직접 관리, 리액트는 제어하지 않음
  - `ref` 사용해 DOM 요소에 직접 접근해 값을 읽기 및 조작
  - ex. useRef 사용해 생성된 참조 객체인 input과 관련된 ref

### 2. 💡 무엇인지 아는가(특징)

- **Controlled Component**

  - 값 입력할 때 마다 유효성 검증 실시간 해줘야 할 경우 사용

- **Uncontrolled Component**
  - 단순한 입력 필드 포함된 폼에서 ref 사용

### 3. ✅ 장점

- **Controlled Component**

  - 입력값 쉽게 검증 및 변경 가능
  - 복잡한 폼 로직 처리에 유리

- **Uncontrolled Component**
  - 입력 값을 직접 접근 및 조작
  - 간단한 폼이나 초기값 중요한 상황에 사용
