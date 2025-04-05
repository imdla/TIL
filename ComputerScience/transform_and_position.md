> 💡 **한 줄 요약**
>
> `transform` 은 브라우저의 composite 단계에서 실행해 reflow나 repaint를 유발하지 않지만, position은 reflow나 repaint를 유발한다.

### 1. 🤔 왜 사용하는가

- 애니메이션이나 동적인 위치 변경 시 `transform` 선호

- **`transform`**

  - 브라우저의 composite 단계에서 실행
  - reflow나 repaint를 유발하지 않음
  - 시각적인 위치만 변경, 실제 문서 흐름과는 무관하게 동작

- **`position`**
  - 이를 통한 위치 변경은 reflow, repaint 유발
  - 주로 레이아웃 구조를 잡거나 부모 기준으로 위치 조정 시 사용
