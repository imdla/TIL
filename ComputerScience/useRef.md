> 💡 **한 줄 요약**
>
> `useRef` 는 컴포넌트 내에서 변경 가능한 값을 저장하고 관리할 수 있는 훅으로, DOM 요소에 접근하거나 값을 유지하면서 렌더링을 트리거 하지 않을 수 있다.

### 1. 🤔 왜 사용하는가

- **`useRef`**
  - React의 훅 중 하나
  - 컴포넌트 내에서 변경 가능한 값을 저장하고 관리 가능
  - **목적**
    1. DOM 요소에 접근
    2. 값을 유지하면서 렌더링을 트리거하지 않음

### 2. 💡 무엇인지 아는가(특징)

1. **`useRef()` 는 DOM 요소에 접근할 때 사용**

   - 특정 DOM 요소에 직접 접근하고 싶을 때 `useRef()` 사용해 해당 요소의 참조 얻을 수 있음
   - `useEffect()` 나 이벤트 핸들러 내에서 해당 DOM 요소에 직접 작업을 수행할 때 유용
     - 입력 필드에 포커스 설정하고 싶을 때, input 요소에 접근 가능

   ```jsx
   const inputRef = useRef(null);

   useEffect(() => {
   	inputRef.current.focus();
   }, []);

   return <input ref={inputRef}> />;
   ```

1. **`useRef` 는 값을 유지하면서 렌더링을 트리거하지 않기 위해 사용**

   - `useState()` : 일반적으로 상태 값을 관리할 때 사용, 상태 변화가 리렌더링을 트리거함
   - `useRef()` : 값이 변경되어도 리렌더링을 트리거 하지 않음
     - 타이머 id를 추적할 때 `useRef()`사용 가능
     - 해당 상태 값이 업데이트될 때 컴포넌트를 리렌더링하지 않음

   ```jsx
   const timeRef = useRef(null);

   const startTimer = () => {
     timeRef.current = setInterval(() => {
       console.log("타이머 실행");
     }, 1000);
   };

   const stopTimer = () => {
     clearInterval(timerRef.current);
   };
   ```
