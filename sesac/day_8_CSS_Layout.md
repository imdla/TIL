## <mark color="#fbc956">CSS - Grid Layout</mark>

### 1. Grid

- 수평선과 수직선으로 이루어진 집합체, 디자인 요소를 정렬할 수 있는 패턴 생성
- 행(rows)과 열(columns) 기준으로 2차원 레이아웃 시스템을 배치

### 2. Grid 구성

- **그리드 컨테이너(Grid Container)**
  - 그리드 레이아웃이 적용된 부모 요소
  - 자식 요소들을 그리드 아이템으로 변환해 행과 열 기준으로 배치
- **그리드 아이템(Grid Items)**
  - 그리드 컨테이너 내부 자식 요소들, 그리드 레이아웃 따라 배치되는 개별 요소들

### 3. Grid 속성

- **그리드 컨테이너 속성**
  - **`display: grid` : 부모 요소를 그리드 컨테이너로 만듦**
    ```html
    <div class="container">
      <div class="item"></div>
      <div class="item"></div>
      <div class="item"></div>
      <div class="item"></div>
      <div class="item"></div>
      <div class="item"></div>
      <div class="item"></div>
    </div>
    ```
    ```css
    .container {
      display: grid;
    }
    ```
  - **`grid-template-columns` : 그리드 컨테이너에서 열(columns)의 크기 설정**
    - 단위 : `px` , `%` , `fr`
      (`fr` : 그리드 컨테이너에서 그리드 행과 열 크기 유연하게 조정 가능한 공간의 한 분율)
    - `repeat()` 통해 전체 또는 일부 섹션만 반복 가
    ```css
    .container {
      display: grid;
      grid-template-columns: 1fr 1fr 1fr;
    }
    ```
  - **`grid-template-rows` : 그리드 컨테이너에서 행(rows)의 크기 설정**
  - **`grid-gap` : 그리드 아이템 사이의 간격 설정**
    - `row-gap` : 행 사이의 간격
    - `column-gap` : 열 사이의 간격
    - `gap` : 열과 행 모두의 약어
    ```css
    .container {
      display: grid;
      grid-template-columns: 1fr 1fr 1fr;
      grid-gap: 1rem;
    }
    ```
- **그리드 아이템 속성**
  - **`grid-column` : 그리드 아이템이 차지할 열의 시작과 끝 설정**
    - `grid-column-start`
    - `grid-column-end`
    - `span {n}` : 시작과 끝 없이 차지할 공간 크기 설정 가능
  - **`grid-row` : 그리드 아이템이 차지할 행의 시작과 끝을 설정**
    - `span {n}` : 시작과 끝 없이 차지할 공간 크기 설정 가능
  - **`grid-area` : 그리드 아이템이 특정 영역 차지하도록 설정, 행과 열의 시작과 끝을 한번에 지정**
  - **`justify-self` / `align-self` : 셀 내부 그리드 아이템을 수평/수직 정렬**

### 4. **명시적 그리드와 암시적 그리드**

- **명시적 그리드** : `grid-template-columns` / `grid-template-rows`
- **암시적 그리드 :** 콘텐츠가 해당 그리드 외부에 배치될 때 추가 그리드 선을 그려 정의된 명시적 그리드 확장함
  - `grid-auto-rows` / `grid-auto-columns`
  - **`minmax()`** : 트랙의 최소 및 최대 크기 설정

---

## <mark color="#fbc956">CSS - Media Query</mark>

### 1. Media Query

- 뷰포트의 크기에 따라 다른 레이아웃 생성

### 2. Media Query 구성

- 어떤 미디어위한 것인지 브라우저에 알려주는 미디어 유형 → 선택 사항
  - `all` , `print` , `screen`
- CSS 적용되기 위해 전달되어야 하는 규칙 또는 조건문
- 조건문이 참일 때 적용되는 CSS 규칙 집합

```css
@media media-type and (media-feature-rule) {
  /* 조건 만족 시 적용 스타일 */
  요소 {
    속성: 값;
  }
}
```

### 3. 미디어 기능 규칙

- **너비와 높이**
  - **`min-width: {n}`** : n 넓이 이상 스타일 적용
  - **`max-width: {n}`** : n 넓이 이하 스타일 적용
- **방향성**
  - **`orientation`** : 세로 모드인지 가로 모드인지 검사
    - `lanscape` : 가로 모드 (화면 너비 > 높이)
    - `portrait` : 세로 모드 (화면 너비 < 높이)

---

## <mark color="#fbc956">CSS - Animation</mark>

### 1. 트랜지션 (transition)

- **트랜지션 (transition)**
  : CSS 속성의 변화를 부드럽게 전환하는 효과
- **구성 : `transition: {속성} {지속시간} {타이밍 함수} {지연 시간};`**
  - `transition-property` : 트랜지션 효과 적용할 CSS 속성
  - `transition-duration` : 트랜지션 진행되는 시간
  - `transition-timing-function` : 트랜지션의 속도 곡선
    - `ease` , `linear` , `ease-in` , `ease-out`
  - `transition-delay` : 트랜지션 시작되기 전까지 지연 시간

### 2. 애니메이션 (animation)

- **애니메이션 (animation)**
  : 트랜지션과 유사항 기능, 더 복잡한 애니메이션 만들 수 있음
- **구성**

  ```css
  /* 애니메이션 정의 */
  @keyframes 애니메이션_이름 {
    0% {
      /* 초기 상태 */
    }
    100% {
      /* 최종 상태 */
    }
  }

  /* 애니메이션 적용 */
  선택자 {
    animation: [애니메이션_이름] [지속시간] [타이밍함수] [지연시간] [반복 횟수]
      [방향];
  }
  ```

  - `animation-name` : 실행할 애니메이션 이름
  - `animation-duration` : 애니메이션이 실행되는 시간
  - `animation-timing-function` : 애니메이션의 속도 곡선
  - `animation-delay` : 애니메니션이 시작되기 전가지 지연 시간
  - `animation-iteration-count` : 애니메이션 반복 횟수
    - `infinite` - 무한 반복
  - `animation-direction` : 애니메이션 진행 방향
    - `normal` , `reverse` , `alternate`

### 3. 변환 (transform)

- **변환 (transform)**
  : 요소를 이동, 회전, 크기 조절 또는 기울임으로 시각적으로 변형시키는 기능
- **구조 : `transform: [변환 함수];`**
- **변환 함수**
  - `translate(x, y)` : 요소를 x축과 y축으로 이동
    - `translate3d` , `translateX` , `translateY` , `translateZ`
  - `rotate(각도deg)` : 요소를 회전
    - `rotate3d` , `rotateX` , `rotateY` , `rotateZ`
  - `scale(x, y)` : 요소의 크기를 확대/축소
    - `scale3d` , `scaleX` , `scaleY` , `scaleZ`
  - `skew(x, y)` : 요소를 기울임
    - `skewX` , `skewY`
