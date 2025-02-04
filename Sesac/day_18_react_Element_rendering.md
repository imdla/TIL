## <mark color="#fbc956">Element Rendering (엘리먼트 렌더링)</mark>

### 1. element

- React 앱의 가장 작은 단위
- 화면에 표시할 내용 기술
- React 엘리먼트는 일반 객체이며 쉽게 생성 가능

```jsx
const element = <h1>Hello, world!</h1>;
```

### 2. DOM에 엘리먼트 렌더링

- **`ReactDOM.createRoot()` : HTML 파일의 React DOM에서 관리할 엘리먼트 선택**
- **`삽입할 위치의요소.render(삽입 요소)` : React DOM에서 관리할 엘리먼트에 렌더링**
- HTML 파일에 `<div>` 있다고 가정
- 이 안에 들어가는 모든 엘리먼트를 React DOM에서 관리해 **루트(root) DOM 노드**라 부름

```html
<!-- HTML -->
<div id="root"></div>
```

```jsx
const root = ReactDOM.createRoot(document.getElementById("root"));

const element = <h1>Hello, world</h1>;
root.render(element);
```

- React 엘리먼트를 렌더링하기 위해
  ⇒ DOM 엘리먼트를 `ReactDOM.createRoot()` 에 전달
  ⇒ React 엘리먼트를 `root.render()` 에 전달

### 3. 렌더링 된 엘리먼트 업데이트

- React 엘리먼트 → **불변객체 (엘리먼트 생성 후 자식 및 속성 변경 불가)**
- 업데이트의 경우 새로운 엘리먼트 생성 후 이를 `root.render()` 로 전달함

```jsx
const root = ReactDOM.createRoot(document.getElementById("root"));

function tick() {
  const element = (
    <div>
      <h1>Hello, world!</h1>
      <h2>It is {new Date().toLocaleTimeString()}.</h2>
    </div>
  );
  root.render(element);
}

setInterval(tick, 1000);
```

- 위 함수는 `setInterval()` 콜백 이용해 초마다 `root.render()` 호출

### 4. 변경된 부분만 업데이트

- React DOM은 해당 엘리먼트와 그 자식 엘리먼트 vs 이전의 엘리먼트 비교
- DOM을 원하는 상태로 만드는데 필요한 경우만 DOM을 업데이트 함

(위의 예시에서는 내용이 변경된 `{new Date().toLocaleTimeString()}` 부분만 업데이트 함 )
