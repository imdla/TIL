> 💡 **한 줄 요약**
>
> Node는 DOM을 구성하는 가장 기본적인 구성 단위로, DOM 트리의 모든 구성 요소를 포함하는 포괄적인 개념이다. Element는 Node의 특정 타입 중 하나로, HTML이나 XML 태그로 표현되는 객체이다.

### 1. 🤔 왜 사용하는가

- **Node**

  - DOM을 구성하는 가장 기본적인 구성 단위
  - DOM 트리의 모든 구성 요소를 포함하는 포괄적인 개념
  - 타입
    - **Document Node** : HTML 문서 전체를 나타내는 루트 노드
    - **Element Node** : HTML 태그
    - **Text Node** : 텍스트 내용
    - **Comment Node** : 주석

- **Element**

  - Node의 특정 타입 중 하나
  - HTML이나 XML 태그로 표현되는 객체
  - `id` , `class` , `style` 과 같은 HTML 속성 가질 수 있음
  - `querySelector()` , `getElementsByClassName()` 과 같은 메서드 사용 가능

- 예시
  - `<div>Hello <!-- 주석 --> World</div>`
    - `div` 태그 → Element Node 이면서 동시에 Node
    - `Hello`, `World` 텍스트 → Text Node
    - 주석 → Comment Node

### 2. 💡 무엇인지 아는가(특징)

- `textContent`
  - Node의 속성
  - 모든 종류의 Node에서 사용 가능
- `innerHTML`

  - Element의 속성
  - Element에만 사용 가능

- `childNodes`
  - Node의 속성
  - 주어진 요소의 모든 자식 Node를 포함하는 `NodeList` 를 반환
    - Element뿐만 아니라 모든 종류의 Node 포함
    - HTML, 텍스트, 주석도 포함
- `children`
  - Element 속성
  - Element 타입의 자식 노드만 포함하는 `HTMLCollection` 반환
    - 텍스트 노드 및 주석 노드 제외
    - HTML 요소 노드만 포함
