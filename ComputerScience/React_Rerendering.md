> 💡 **한 줄 요약**
>
> 리액트에서 컴포넌트가 불필요하게 리렌더링되는 상황을 방지하기 위해 크게 세가지가 있다.
>
> 첫째, `React.memo` 를 활용해 컴포넌트를 메모이제이션할 수 있다. 컴포넌트의 props가 변경되지 않으면 컴포넌트를 리렌더링하지 않도록 한다.
>
> 둘째, `useMemo` 와 `useCallback` 을 사용해 각각 값과 함수를 메모이제이션할 수 있다. `useMemo` 는 계산 비용이 많이 드는 결과를, `useCallback` 은 자식 컴포넌트에 전달되는 콜백 함수를 메모이제이션한다.
>
> 셋째, React DevTools Profiler 를 활용해 불필요한 리렌더링이 발생하는 컴포넌트를 식별하고 최적화할 수 있다.

### 1. 🤔 왜 사용하는가

- **React에서 불필요한 리렌더링 방지 방법**

  1. **React.memo**

     - 컴포넌트를 메모이제이션
     - 컴포넌트의 props가 변경되지 않으면 컴포넌트를 리렌더링하지 않음
     - 부모 컴포넌트가 자주 업데이트 상황에서 유용

     ```jsx
     const MemoizedComponent = React.memo(MyComponent);
     ```

  2. **`useMemo`, `useCallback` 훅 사용**

     - 각각 값과 함수를 메모이제이션
     - `useMemo` : 계산 비용이 많이 드는 연산 결과
     - `useCallback` : 자식 컴포넌트에 전달되는 콜백 함수를 메모이제이션

     ```jsx
     const memoizedValue = useMemo(() => computedExpensiveValue(a, b), [a, b]);
     const memoizedCallback = useCallback(() => doSomething(a, b), [a, b]);
     ```

  3. **React DevTools의 Profiler 활용**
     - 불필요한 리렌더링 발생하는 컴포넌트 식별 및 최적화
     - 최적화는 성능 문제가 실제로 발생할 때 적용하는 것이 좋음
     - 과도한 최적화는 코드의 복잡성을 증가시킬 수 있음

### 2. 💡 무엇인지 아는가(특징)

> `useCallback` , `useMemo` 와 같은 메모이제이션을 많이 적용하면 성능이 향상될까 ?

- 메모이제이션을 무분별하게 적용하는 것 → 성능에 부정적인 영향 미칠 수 있음
  - 메모이제이션도 또 다른 비용이 발생하는 작업임
- React
  - 이전 값을 메모리에 저장해야 해 메모리 비용이 발생
  - 의존성 배열의 각 항목을 비교하는 작업을 수행해야하기 때문

```jsx
// 간단한 함수의 경우 메모이제이션이 불필요
const handleClick = useCallback(() => {
  console.log("clicked");
}, []);

// 복잡한 연산의 경우 메모이제이션이 효율적
const expensiveValue = useMemo(() => {
  return complexCalculation(items);
}, [items]);
```

- 복잡한 계산이 필요한 경우 (컴포넌트가 자주 리렌더링 되는 경우)

  - 성능 최적화가 필요한 경우 메모이제이션이 성능에 도움이 됨

- **성능 최적화**
  1. 실제 병목이 발생하는 지점을 React DevTools Profiler로 확인
  2. 선별적 적용
