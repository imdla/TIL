> 💡 **한 줄 요약**
>
> 쌓임 맥락은 가상의 z축을 상정해 HTML 요소들을 3차원으로 개념화한 것으로, 이는 요소들이 쌓이는 순서에 영향을 미친다.

### 1. 🤔 왜 사용하는가

- **쌓임 맥락(Stacking Context)**
  - 가상의 z축을 상정해 HTML 요소들을 3차원으로 개념화한 것
  - 이는 요소들이 쌓이는 순서에 영향을 미침
    1. 기본적으로 HTML 요소는 DOM 순서에 따라 쌓임
    2. HTML 상에서 위쪽에 위치할수록 아래쪽에 쌓이는 방식
    3. `position` 속성이 적용되어 있는 요소들
       - `z-index`값이 낮을수록 아래쪽으로 쌓임
       - `z-index`값이 높을수록 위쪽으로 쌓임
    4. **쌓임 맥락이 생성되는 조건**
       - `position`속성이 `relative`, `absolute`이고, `z-index`값이 `auto`가 아닌 경우
       - `position`속성이 `fixed` 또는 `sticky`인 경우
       - `display`가 `flex`또는 `grid`이고, `z-index`가 설정된 경우
       - `opacity`값이 1미만인 경우
       - `transform`, `filter`, `backdrop-filter`등의 속성이 적용되는 경우

### 2. 💡 무엇인지 아는가(특징)

> **쌓임 맥락의 동작 예시**

```html
<div style="position: relative; z-index: 1;">
  A 요소 (z-index: 1)
  <div style="position: absolute; z-index: 999;">A-1 요소 (z-index: 999)</div>

  <div style="position: relative; z-index: 2;">B 요소 (z-index: 2)</div>
</div>
```

- 가장 위쪽에 쌓이는 요소 : B 요소
  - 그 다음 : A-1 요소
  - 그 다음 : A 요소
- 최상위 쌓임 맥락 내의 요소들인 A 요소와 B 요소의 `z-index`값을 비교했을 때, B 요소가 크기 때문에 가장 위쪽에 쌓임
- A 요소가 형성한 쌓임 맥락 내에서 `z-index`값을 비교했을 때, A 요소보다 `z-index` 가 큰 A-1 요소가 더 위쪽에 쌓임

-> `z-index` 값이 더 크다고 무조건 위쪽에 쌓이는 것이 아님
