> 💡 **한 줄 요약**
>
> `useState` 는 항상 컴포넌트 최상위에서 호출되어야 한다. 이는 리액트가 state를 관리하는 방식이 `useState` 를 호출하는 순서와 연관되어 있기 때문이다.

### 1. 🤔 왜 사용하는가

- **`useState()`**
  - 리액트는 state를 관리하는 방식이 `useState` 를 호출하는 순서와 연관
    - 리액트는 컴포넌트 내부에서 `useState` 가 호출된 순서를 기준으로 `state` 를 저장하고 업데이트 함
    - `useState` 를 조건문 내부에서 호출 시 - 특정 렌더링 시에는 호출되고 - 다른 렌더링에서 호출되지 않을 수 있음
      → 리액트가 호출 순서를 기반으로 state 를 추적하는 과정에서 혼돈 발생
      ⇒ `useState` 는 항상 컴포넌트 최상위에서 호출되어야 함
