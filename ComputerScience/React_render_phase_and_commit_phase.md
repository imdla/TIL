> 💡 **한 줄 요약**
>
> render phase는 변화된 UI를 결정하는 계산 과정이고, commit phase는 계산된 결과를 실제로 반영하는 단계이다.
>
> 이들이 동기화 될 때 두가지 특징이 있는데, 단계적 진행과 병목 관리가 있다. 단계적 진행은 render phase가 완료되면 즉시 commit phase를 실행하지 않고, 다른 높은 우선순위 작업부터 처리 후 commit phase를 실행한다. 이를 통해 동기화 필요한 작업을 효율적으로 관리해 사용자 경험을 개선할 수 있다. 병목 관리는 render phase에서 모든 변경 사항이 Fiber Tree에 준비된 상태에서 commit phase로 넘어가 render와 commit 단계의 일관성을 유지해 두 단계가 순차적으로 작동하게 한다. 이를 통해 UI가 정확하게 동기화되고 불필요한 재렌더링을 방지할 수 있다.

### 1. 🤔 왜 사용하는가

- **리액트의 렌더링 과정 단계**
  1. render phase
  2. commit phase

1. **render phase**
   - 리액트가 변화된 상태나 props에 따라 어떤 UI가 변경되어야 할지 결정하는 단계
   - 실제로 DOM을 업데이트하지 않고, 변경사항을 가상 DOM에서 계산해 비교
   - 이 과정은 순수하게 계산 과정
     - 성능에 영향 주지 않도록 중단되거나 다시 실행될 수 있음
     - React 18에서 도입된 Concurrent Mode 통해 비동기적으로 처리 가능
2. **commit phase**
   - 실제로 변화된 UI를 DOM에 반영하는 단계
   - 리액트는 가상 DOM에서 계산된 결과를 실제 DOM에 적용, 변화된 UI를 브라우저에 렌더링함
   - DOM 업데이트 이후, `useEffect` 와 같은 사이드 이펙트를 발생시키는 훅들이 실행됨

> render phase는 변화된 UI를 결정하는 계산 과정
>
> commit phase는 그 계산된 결과를 실제로 반영하는 단계

### 2. 💡 무엇인지 아는가(특징)

- **render phase와 commit phase가 동기화될 때 특징**
  1. **단계적 진행**
     - render phase가 완료되면 리액트는 즉시 commit phase를 실행하지 않고
     - 다른 높은 우선순위 작업이 있다면 먼저 처리한 후, 나중에 commit phase 실행함
  2. **병목 관리**
     - render phase에서 모든 변경 사항이 Fiber Tree에 준비된 상태에서 commit phase로 넘어가
     - render와 commit 단계의 일관성이 유지됨

### 3. ✅ 장점

- **단계적 진행**
  - React는 동기화가 필요한 작업을 효율적 관리, 사용자 경험 개선
- **병목 관리**
  - 두 단계가 순차적으로 작동해, UI가 정확하게 동기화 됨
  - 불필요한 재렌더링 방지함
