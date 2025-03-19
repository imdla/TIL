> 💡 **한 줄 요약**
>
> HTML 데이터 속성은 사용자 정의 데이터를 HTML 요소에 저장하기 위해 사용되는 속성으로, 이는 DOM 요소에 특정 데이터를 바인딩하고, 자바스크립트 로직에서 해당 데이터를 활용하기 위해 사용된다.

### 1. 🤔 왜 사용하는가

- **데이터 속성**
  - 사용자 정의 데이터를 HTML 요소에 저장하기 위해 사용되는 속성
  - 선언 방법 : `data-` 로 시작하는 속성을 HTML 태그에 추가
  - ex. `<div data-user-id=”12345” data-role=”admin”></div>`
    - 데이터 속성 : `data-user-id` 와 `data-role`

### 2. 💡 무엇인지 아는가(특징)

- **자바스크립트를 통해 데이터 속성에 접근**

  - `dataset` 객체 활용
  - HTML의 데이터 속성 이름이 JS의 camelCase로 매핑
  - ex. `data-user-id` → `dataset.userId` 로 매핑
    - `data-role` → `dataset.role`로 매핑

- **CSS에서 데이터 속성 활용**

  - `attr()` 함수 및 속성 선택자 통해 데이터 속성의 값을 기반으로 스타일 적용 가능

  ```css
  /* attr() 함수 사용 접근 */
  article::before {
    content: attr(data-parent);
  }

  /* 속성 선택자 사용 접근 */
  article[data-columns="3"] {
    width: 400px;
  }
  ```

- **데이터 속성의 활용**
  - DOM 요소에 특정 데이터 바인딩할 경우
  - 자바스크립트 로직에서 해당 데이터를 활용하기 위해 사용
  - ex. 버튼 클릭 이벤트에서 특정 데이터 전달
    - 데이터를 기반으로 UI를 동적으로 변경
  - 장점 : HTML과 자바스크립트 간 데이터 상호작용 간단히 구현 가능
