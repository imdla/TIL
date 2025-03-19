> 💡 **한 줄 요약**
>
> `event.target` 은 이벤트가 실제로 발생한 요소이고, `event.currentTarget` 은 이벤트 리스너가 연결된 요소이다.

### 1. 🤔 왜 사용하는가

- **`event.target`**

  - 이벤트가 실제로 발생한 요소

- **`event.currentTarget`**
  - 이벤트 리스너가 연결된 요소

> **`event.target` 과 `event.currentTarget` 의 공통점**

- 이벤트 객체의 속성

> **`event.target` 과 `event.currentTarget` 의 차이점**

- **이벤트**
  - 특정 요소에서 발생한 후 부모 요소들로 전파되는 과정(버블링) 진행
  - 이때, `target` 과 `currentTarget` **이 다르게 동작함**
- 예시
  - 부모 요소에 이벤트 리스너 등록했지만, 자식 요소에서 이벤트 발생해 버블링된 상황
  - `target` \*\*\*\*→ 이벤트가 발생한 요소인 자식 요소 가리킴
  - `currentTarget` → 이벤트 리스너가 연결된 요소인 부모 요소 가리킴
