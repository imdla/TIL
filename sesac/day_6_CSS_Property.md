<<<<<<< HEAD
## <mark color="#fbc956">CSS의 속성</mark>

### 1. CSS의 텍스트 속성

- **`color` : 텍스트의 색상 지정**
  - `rgb(255, 255, 255)` , `#FFFFFF` , `white`
- **`font-size` : 텍스트의 크기 지정**
- **`font-family` : 텍스트의 글꼴 지정**
- **`font-weight` : 텍스트의 굵기 지정**
  - `normal` (일반 두께) , `bold` (굵은 두께) , `lighter` (부모 요소 굵기보다 한 단계 가벼움) , `bolder` (부모 요소 굵기보다 한 단계 무거움)
  - `100`-`900` : 위의 키워드보다 세분화된 제어
- **`font-style` : 텍스트의 스타일 지정**
  - `normal` : 일반 글꼴 설정
  - `italic` : 글꼴의 기울임꼴 버전 사용
  - `oblique` : 일반 버전을 기울여 만든 이탤릭체 글꼴의 시뮬레이션 버전 사용
- **`font-transform` : 글꼴 변환**
  - `none` : 변환 방지
  - `uppercase` : 텍스트를 대문자로 변환
  - `lowercase` : 텍스트를 소문자로 변환
  - `capitalize` : 고정 너비 사각형 내부에 작성되도록 모든 글리프 변환해 정렬
- **`text-align` : 텍스트의 정렬 방식**
  - `left` : 텍스트 왼쪽 정렬
  - `right` : 텍스트 오른쪽 정렬
  - `center` : 텍스트 중앙 정렬
  - `justify` : 모든 텍스트 줄이 동일한 너비 되도록 단어 사이 간격 조정해 텍스트 펼침
- **`text-decoration` : 텍스트의 밑줄이나 오버라인 지정**
  - `none` : 모든 텍스트 장식 해제
  - `underline` : 텍스트에 밑줄
  - `overline` : 텍스트에 오버라인
  - `line-through` : 텍스트 위 취소선
- **`text-shadow(수평값, 수직값, 흐림 반경, 색상 단위)` : 텍스트의 그림자 속성**
- **`line-heigh` : 텍스트 줄의 높이 설정**
- **spacing : 문자 및 단어 간격**
  - `letter-spacing` : 문자 사이 간격
  - `word-spacing` : 단어 사이 간격
- **`text-overflow` : 표시되지 않은 오버플로된 콘텐츠를 알리는 방법 정의**
- **`white-space` : 요소 내부 공백 및 관련 줄 바꿈 처리 방법**
- **`direction` : 텍스트 방향 정의**
- **`word-wrap` : 오버플로 방지 위한 브라우저가 단어 내 줄을 끊을 수 있는지 여부 지정**

```html
<p>Hello world!</p>
```

```css
p {
  /* 대표적인 텍스트 속성들 */
  color: red;
  font-size: 24px;
  font-family: Helvetica, Arial, sans-serif;
  font-weight: bold;
  text-align: center;
  text-decoration: underline;
}
```

### 2. CSS의 목록 속성

- **`list-style-type`** : 목록에 사용할 글머리 기호 유형
  - 순서 없는 리스트 → 사각형, 원형
  - 순서 있는 리스트 → 숫자, 문자, 로마 숫자
- **`list-style-position`** : 글머리 기호가 목록 항목 내부에 표시되는지 아니면 각 항목 시작 전에 목록 항목 외부에 표시되는지 설정
- **`list-style-image`** : 글머리 기호에 대한 사용자 지정 이미지 사용

### 3. CSS의 박스 속성

- **너비와 높이**
  - **`width` : 요소의 너비 지정**
    - `min-width` : 요소의 최소 너비
    - `max-width` : 요소의 최대 너비
  - **`height` : 요소의 높이 지정**
    - `min-height` : 요소의 최소 높이
    - `max-height` : 요소의 최대 높이
- **테두리**
  - **`border` : 테두리의 색상, 너비 및 스타일 설정하는 속기 속성**
    - `border-width` : 테두리 두께 설정
    - `border-style` : 테두리 스타일 지정
    - `border-color` : 테두리 색상 지정
  - `border-radius` : 각 모서리 둥글게 설정

### 4. CSS의 배경 속성

- **배경**
  - **`background-color` : 요소의 배경 색상 지정**
  - **`background-image` : 요소의 배경에 이미지 설정**
    - `url()` : 링크로 이미지 설정
    - `gradient` : 그라디언트 생성 (`linear-gradient` , `radial-gradient`)
  - **`background-repeat` : 배경 이미지의 반복 방식 지정**
    - `repeat` , `no-repeat` , `repeat-x` , `repeat-y`
  - **`background-size` : 이미지의 크기를 배경 따라 지정**
    - `cover` : 이미지를 박스 면적에 덮으며 가로 및 세로 비율 유지
      - 박스 보다 이미지가 클 수 있음
    - `contain` : 이미지를 박스 안 들어가기에 적합한 크기로 만듦
      - 박스보다 이미지가 작을 수 있음
  - **`background-position: 수평값, 수직값` : 적용되는 박스에서 배경 이미지의 위치 선택**
  - **`background-attachment` : 내용이 스크롤 될 때 스크롤하는 방법 지정**
    - `scroll` : 요소의 배경이 스크롤되도록 함
    - `fixed` : 요소의 배경 viewport에 고정시켜, 페이지나 요소 내용 스크롤되지 않도록 함
    - `local` : 요소 스크롤 시 배경과 함께 스크롤
- **기타**
  - **`opcaity` : 요소의 투명도 설정**
  - **`cursor` : 마우스 올렸을 때 모양 지정**
    - `pointer` , `default` , `text` , `move`
  - **`visivility` : 요소의 가시성 설정**
    - `vidible` ,`hidden`

### 5. 단**위**

- **절대 단위 (Absolute Units)**
  : 고정된 크기 나타냄, 화면의 크기나 해상도 관계없이 크기 일정
  - **`px`** (픽셀)
    - 화면의 물리적인 픽셀 단위 기준
    - 1px = 화면의 한 픽셀 크기
- **상대 단위 (Relative Units**)
  : 다른 요소나 화면의 크기 기준으로 계산
  - **`%`** (백분율) : 부모 요소의 크기에 대한 비율]
  - **`em`** : 현재 요소의 글꼴 크기 기준으로 한 상대 단위
  - **`rem`** : 루트 요소의 글꼴 크기 기준으로 한 상대 단위 (기본적으로 16px)
  - **`vw`** (뷰포트 너비) : 뷰포트의 너비를 기준으로 한 상대 단위
    - 1vw = 뷰포트 너비의 1%
  - **`vh`** (뷰포트 높이) : 뷰포트의 높이를 기준으로 한 상대 단위
    - 1vh = 뷰포트 높이의 1%

### 6. CSS 우선 순위

1. `!important` : 강제로 우선시 하는 키워드
=======
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
>>>>>>> 9612eaf0452bfc093740cc627f42c8f01de801a7
2. 요소 인라인 스타일
3. `id` 선택자
4. `class` 선택자
5. `tag` 선택자
6. 나중에 작성된 스타일

<<<<<<< HEAD
---

## <mark color="#fbc956">CSS의 박스 모델</mark>

### 1. 박스 유형 - 디스플레이 유형

- **`block`**
  - 수평으로 연장되어 상위 콘테이너만큼 공간을 채움
  - 새 줄로 행갈이 함
  - `width` 와 `height` 속성 적용 가능
  - 패딩, 마진, 보더로 → 다른 요소들 박스로부터 밀려남
- **`inline`**
  - 새 줄로 행갈이 하지 않음
  - `width` 와 `height` 속성 적용 불가능
  - 패딩, 마진, 보더로 → 다른 요소들 박스로부터 밀려나지 않음
- **`inline-block`**
  - 새 줄로 행갈이 하지 않음
  - `width` 와 `height` 속성 적용 가능
  - 패딩, 마진, 보더로 → 다른 요소들 박스로부터 밀려남
- **`none`**
  - 요소를 숨김 → 웹 페이지에서 보이지 않으며 공간도 차지하지 않음
- **일반 흐름 (normal flow)**
  - 페이지 레이아웃을 제어하기 위해 아무것도 하지 않을 경우, 브라우저가 기본값으로 HTML 페이지를 배치하는 방법

### 2. 박스 모델 구성
=======
### 3. 박스 모델
>>>>>>> 9612eaf0452bfc093740cc627f42c8f01de801a7

: 각 요소에 대해 시각적 서식 모델에 따라 생성되고 배치되는 안팎 여백 포함한 사각형 박스 위한 CSS 모듈

- **콘텐츠(Content)** : 텍스트나 이미지 들어가는 내용
<<<<<<< HEAD
  - `width` 와 `hright` 속성 사용
- **패딩(Padding)** : 콘텐츠와 테두리 사이 공간, 요소 내부 여백 제공
  - `padding` , `padding-top` , `padding-right` , `padding-bottom` , `padding-left`
- **테두리(Border)** : 요소의 가장자리 부분을 감싸는 테두리, 패딩과 마진 사이 위치
  - `border` , `border-top` , `border-right` , `border-bottom` , `border-left`
    - `border-width` , `border-style` , `border-color`
- **마진(Margin)** : 요소 바깥 공간, 다른 요소들과의 간격 설정
  - `margin` , `margin-top` , `margin-right` , `margin-bottom` , `margin-left`

### 3. 대체 CSS 박스 모델

- **`box-sizing`**
  - **`content-box`** (default) - 기본 박스 모델
    : `width` 와 `height` 가 콘텐츠 영역만 정의
  - **`border-box`** - 대체 박스 모델
    : 설정한 크기에 따라 정의된 영역만큼 테두리 박스가 포함되도록 전달
    (`width` 와 `height` 가 패딩과 테두리까지 포함해 전체 크기 정의)
=======
- **패딩(Padding)** : 콘텐츠와 테두리 사이 공간, 요소 내부 여백 제공
- **테두리(Border)** : 요소의 가장자리 부분을 감싸는 테두리, 패딩가 마진 사이 위치
- **마진(Margin)** : 요소 바땉 공간, 다른 요소들과의 간격 설정
>>>>>>> 9612eaf0452bfc093740cc627f42c8f01de801a7
