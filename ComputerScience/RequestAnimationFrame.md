> 💡 **한 줄 요약**
>
> `requestAnimationFrame()`은 브라우저의 화면 갱신 주기에 맞춰 콜백 함수를 실행하도록 요청하는 API이다.

### 1. 🤔 왜 사용하는가

- **`requestAnimationFrame()`**

  - 브라우저의 화면 갱신 주기에 맞춰 콜백 함수를 실행하도록 요청하는 API
  - 애니메이션을 보다 부드럽게 렌더링 가능, 성능 최적화
  - 목적
    - 애니메이션을 위한 프레임 렌더링을 브라우저의 화면 갱신 속도에 맞춤
    - 일반 브라우저 - 화면의 프레임을 1초당 60번(60fps)이나 120번(120fps) 갱신
    - `requestAnimationFrame()` - 이러한 갱신 주기에 맞춰 콜백 실행
  - 장점 : 브라우저가 최적의 시점에 프레임 렌더링하도록 보장
    → 부드러운 사용자 경험 제공, 불필요한 연산 줄임

  ```jsx
  const animate = () => {
  	/* 애니메이션 동작 */

  	if () {
  		return;
  	}

  	requestAnimationFrame(animate)
  }

  requestAnimationFrame(animate)
  ```

- `setTimeout()` , `setInterval()`
  - 이를 사용해도 일정한 간격으로 함수 실행 가능
  - 하지만, 브라우저의 화면 갱신 주기와 맞지 않는 경우 발생
    → 프레임 드롭 및 애니메이션 끊김 현상 발생 가능

### 2. 💡 무엇인지 아는가(특징)

> **requestAnimationFrame 콜백은 태스크 큐에서 관리되는지 ?**

- requestAnimationFrame 콜백은 태스크 큐나 마이크로태스크 큐와 다른 곳에서 관리됨
- map of animation frame callbacks라는 map 형태의 자료구조에서 별도로 관리됨
  - 독자적인 실행 주기를 가짐

### 3. ✅ 장점

1. 디스플레이의 주사율에 맞게 콜백의 실행 주기가 동적으로 조정
   - 60Hz, 120Hz, 144Hz 등 여러 주사율의 디스플레이에서 최적의 타이밍을 찾아 실행
2. 백그라운트 탭이나 hidden 상태일 때는 실행을 중단
   - 사용자의 배터리 수명과 시스템 리소스 절약에 도움
