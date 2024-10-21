## <mark color="#fbc956">CSS의 Layout</mark>

- **CSS Layout**
  : 웹 페이지에 포함될 요소들을 레이아웃에서의 해당 요소가 어디에 위치할지 제어
- **레이아웃 종류**
  - 일반 흐름 (`normal flow`)
  - `display`
  - `flexbox`
  - `grid`
  - `floats`
  - `position`

### 1. 포지션 속성

: 문서 상 요소를 배치하는 방법 지정

(좌표 속성 : `top` , `right` , `left` , `bottom` , `z-index`)

- **포지션 속성 종류**
  - **`static`** (default)
    - 요소를 문서 흐름에 따라 배치
    - 좌표 속성 적용 안됨
    - 자기 위치의 공간 유지
  - **`relative`**
    - 요소를 일반적인 문서 흐름 내에서 상대적으로 배치
    - 원래 자리에서 좌표 속성만큼 이동
    - 자기 위치의 공간 유지
  - **`absolute`**
    - 가장 가까운 위치 지정 조상 요소에 대해 상대적 배치
    - 위치 지정 가진(`relative` , `absolute` , `fixed`) 조상 요소 기준으로 좌표 속성만큼 이동
    - 자기 위치의 공간 유지하지 않음
  - **`fixed`**
    - 뷰포트에 고정해 배치하며, 문서의 흐름에 영향 받지 않음
    - 자기 위치의 공간 유지하지 않음
  - **`sticky`**
    - `relative` 와 `fixed` 의 혼합 형태, 스크롤 위치따라 배치 달라짐
    - 일반적으로 `relative` 처럼 상대 위치 지정 요소로 배치되지만
      → 가장 가까운 블록 레벨 조상(컨테이닝 블록) 기준으로 지정한 임계값(`top` 등)을 넘어가면 `fixed` 처럼 화면에 고정되었다가
      → 컨테이닝 블록 반대편을 만나면 해제됨

---

## <mark color="#fbc956">CSS의 Flexbox</mark>

### 1. Flexbox

- 행과 열 형태로 항목 무리를 배치하는 일차원 레이아웃 메서드
- `display` 의 속성 값 중 하나로 레이아웃 유연하게 구현하기 위한 모델

### 2. Flexbox 구성

- **기본 축 (main axis)**
  - flex item이 배치되고 있는 방향으로 진행하는 축
  - 기본 축의 시작과 끝 → main start, main end
- **교차 축 (cross axis)**
  - flex item이 내부에 배치되는 방향에 직각을 이루는 축
  - 교차 축의 시작과 끝 → cross start, cross end
- **플렉스 컨테이너 (flex container)**
  - `display: flex` 가 설정된 부모 요소
  - flex item의 배치 방식을 정의
- **플렉스 아이템 (flex item)**
  - flex container 내부에 flexbox로 레이아웃되는 항목

### 3. Flex Container의 속성

- **`flex-direction` : flex item이 배치될 방향 설정**
  - `row` (기본값) : 왼쪽 → 오른쪽 (수평 배치)
  - `row-reverse` : 오른쪽 → 왼쪽 (수평 배치)
  - `column` : 위 → 아래 (수직 배치)
  - `column-reverse` : 아래 → 위 (수직 배치)
- **`justify-content` : 기본 축(main-axis) 따라 flex item 정렬**
  - `flex-start` : 아이템을 시작점에 정렬
  - `flex-end` : 아이템을 끝점에서 정렬
  - `center` : 아이템을 가운데 정렬
  - `space-between` : 아이템 사이 동일 간격, 양 끝 여백 없음
  - `space-around` : 아이템 주위 동일 간격, 양 끝 동일 여백 있음
- **`align-items` : 교차 축(cross-axis) 따라 flex item 정렬**
  - `stretch` (default) : 아이템이 컨테이너 높이 맞춰 늘어남
  - `flex-start` : 교차 축의 시작점에서 정렬
  - `flex-end` : 교차 축의 끝점에서 정렬
  - `center` : 교차 축의 중앙에서 정렬
  - `baseline` : 텍스트 기준선에 맞춰 정렬
- **`flex-wrap` : 아이템이 컨테이너 밖으로 이탈 시 처리 방법**
  - `nowrap` (default) : 줄바꿈 없음
  - `wrap` : 여러 줄로 나눠 배치
  - `wrap-reverse` : 역순으로 나눠 배치

### 4. Flex item의 속성

- **`align-self` : 특정 아이템의 교차 축 정렬 방식 지정**
  - `auto` (default) : 속성에 값 지정 시 부모 요소의 `align-items` 속성 덮음
  - `flex-start` : 아이템을 교차 축의 시작점에 정렬
  - `flex-end` : 아이템을 교차 축의 끝점에 정렬
  - `center` : 아이템을 교차 축의 가운데에 정렬
  - `baseline` : 아이템을 텍스트의 기준선에 맞춰 정렬
- **`flex-grow` : 아이템이 컨테이너 내 남은 공간 얼마나 채울지 결정**
  - 기본값 = 0 , 값이 클수록 더 많은 공간 차지
- **`flex-shrink` : 공간 부족 시 아이템이 얼마나 줄어들지 결정**
  - 기본값 = 1 , 0으로 설정 시 아이템이 줄어들지 않음
- **`flex-basis` : 플렉스 아이템의 기본 크기 설정**
  - 아이템의 최소 너비나 높이 설정
