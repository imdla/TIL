> 💡 **한 줄 요약**
>
> 리액트의 성능을 최적화하기 위해 대표적으로 리액트의 `memo` 를 사용해 컴포넌트를 메모이제이션 할 수 있다. 이는 컴포넌트의 props가 변경되지 않았을 때, 리렌더링을 방지해 성능을 최적화한다.
>
> 두번째로, `useCallback` 과 `useMemo` 를 활용할 수 있다. `useCallback` 은 함수를 메모이제이션해 불필요한 함수 재생성을 방지하고, `useMemo` 는 값의 재계산을 방지해 성능을 최적화할 수 있다.
>
> 세번째로, 코드 스플리팅은 큰 애플리케이션을 여러 개의 작은 청크로 나누어 필요한 청크만 로드하게 해 초기 로드 시간을 줄일 수 있다.

### 1. 🤔 왜 사용하는가

- 리액트의 성능 최적화

1. **메모제이션**
   - 리액트의 `memo` 사용해 컴포넌트를 메모이제이션
   - 컴포넌트의 props가 변경되지 않았을 때, 리렌더링 방지해 성능 최적화
2. **`useCallback` , `useMemo`**
   - `useCallback` : 함수를 메모제이션해 불필요한 함수 재생성 방지
   - `useMemo` : 값의 재계산을 방지해 성능 최적화
     - 자식 컴포넌트로 전달되는 함수나 값이 변경되지 않을 경우 리렌더링 피할 수 있음
3. **코드 스플리팅**
   - 큰 애플리케이션을 여러 개의 작은 청크로 나누어, 필요한 청크만 로드하게 해 초기 로드 시간 줄임
   - React.lazy, Suspense 사용해 동적으로 컴포넌트 로드 가능

### 2. 💡 무엇인지 아는가(특징)

- **코드 스플리팅 사용해야하는 경우**
  1. **초기 로딩 시간이 길어지는 경우**
     - 애플리케이션이 커지면 → 초기 로딩에 모든 코드 로드하는 것 비효율적
     - 코드 스플리팅 사용해 초기 로드 시 필요한 핵심 코드만 로드
     - 이후 추가적인 기능은 필요할 때 로드해 초기 로딩 속도 개선
  2. **라우트별 코드 분할이 필요한 경우**
     - SPA에서는 각 페이지가 별도의 기능과 UI 가짐
     - 라우트별로 필요한 코드만 분리해 로드 가능
     - 리액트의 React.lazy, Suspense 사용해 라우트별로 컴포넌트 동적으로 불러올 때 유리