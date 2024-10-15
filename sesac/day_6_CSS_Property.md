## <mark color="#fbc956">CSS의 속성과 모듈</mark>

### 1. CSS 속성

- **텍스트**
  - `color` : 텍스트의 색상 지정
  - `font-size` : 텍스트의 크기 지정
    - 단위 : `px`, `em` , `rem` , `%`
  - `font-family` : 텍스트의 글꼴 지정
  - `font-weight` : 텍스트의 굵기 지정
  - `text-align` : 텍스트의 정렬 방식
- **너비와 높이**
  - `width` : 요소의 너비 지정
    - `min-width` : 요소의 최소 너비
    - `max-width` : 요소의 최대 너비
  - `height` : 요소의 높이 지정
    - `min-height` : 요소의 최소 높이
    - `max-height` : 요소의 최대 높이
- **배경**
  - `background-color` : 요소의 배경 색상 지정
  - `background-image` : 요소의 배경에 이미지 설정
  - `background-repeat` : 배경 이미지의 반복 방식 지정
- **기타**
  - `opcaity` : 요소의 투명도 설정
  - `cursor` : 마우스 올렸을 때 모양 지정
  - `visivility` : 요소의 가시성 설정

### 2. CSS 우선 순위

1. `!important`
2. 요소 인라인 스타일
3. `id` 선택자
4. `class` 선택자
5. `tag` 선택자
6. 나중에 작성된 스타일

### 3. 박스 모델

: 각 요소에 대해 시각적 서식 모델에 따라 생성되고 배치되는 안팎 여백 포함한 사각형 박스 위한 CSS 모듈

- **콘텐츠(Content)** : 텍스트나 이미지 들어가는 내용
- **패딩(Padding)** : 콘텐츠와 테두리 사이 공간, 요소 내부 여백 제공
- **테두리(Border)** : 요소의 가장자리 부분을 감싸는 테두리, 패딩가 마진 사이 위치
- **마진(Margin)** : 요소 바땉 공간, 다른 요소들과의 간격 설정
