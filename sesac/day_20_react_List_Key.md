## <mark color="#fbc956">List and Key (목록과 키)</mark>

### 1. JavaScript의 리스트 변환

```jsx
const numbers = [1, 2, 3, 4, 5];
const doubled = numbers.map((number) => number * 2);
console.log(doubled); // [ 2, 4, 6, 8, 10 ]
```

- `map()` 메서드를 이용해 `numbers`의 배열 값을 두 배로 만들고 `map()` 에서 반환하는 새 배열을 `doubled` 변수에 할당해 확인하는 코드
  ⇒ **React에서 배열을 엘리먼트 리스트로 만드는 방식은 이와 동일**

### 2. 여러 개의 컴포넌트 렌더링

- 엘리먼트 모음 만들고 중괄호 `{}` 이용해 JSX에 포함 가능

```jsx
const numbers = [1, 2, 3, 4, 5];
const listItems = numbers.map((number) => <li>{number}</li>);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<ul>{listItems}</ul>);
```

- `map()` 메서드를 이용해 `numbers` 배열 반복 실행
- 각 항목에 대해 `<li>` 엘리먼트 반환하고 엘리먼트 결과를 `listItems`에 저장함 ⇒ `<ul>` 엘리먼트 안에 전체 `listItems` 배열 포함 가능

### 3. 기본 리스트 컴포넌트

- 일반적으로 컴포넌트 안에서 리스트를 렌더링 함

```jsx
funtion NumberList(props) {
	const numbers = props.numbers;
	const listItems = numbers.map((number) =>
		<li>{number}</li>
	);
	return (
		<ul>{listItems}</ul>
	);
}

const numbers = [1, 2, 3, 4, 5];
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<NumberList numbers={numbers} />);
```

- 이전 예시를 통해 `numbers` 배열을 받아 순서없는 엘리먼트 리스트를 출력하는 컴포넌트로 리팩토링 가능
  ⇒ 이 코드 실행 시 `key`를 넣어야 하는 경고 표시

> **`Key` :** 엘리먼트 리스트를 만들 때 포함해야 하는 특수한 문자열 어트리뷰트
> (아래에서 자세히)

- 아래의 예시에서 `numbers.map()` 안에서 리스트의 각 항목에 `key` 할당해 `key` 누락 문제 해결

```jsx
funtion NumberList(props) {
	const numbers = props.numbers;
	const listItems = numbers.map((number) =>
		<li key={number.toStrimg()}>
			{number}
		</li>
	);
	return (
		<ul>{listItems}</ul>
	);
}

const numbers = [1, 2, 3, 4, 5];
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<NumberList numbers={numbers} />);
```

### 4. Key

- React가 어떤 항목을 변경, 추가, 삭제할지 식별하는 것 도움
  - React가 컴포넌트 조작하는데 명확한 식별자를 부여하는 것
- 엘리먼트에 안정적인 고유성을 부여하기 위해 배열 내부의 엘리먼트에 지정해야 함
- **`key` 규칙**

  1. `key`는 형제 간에 고유해야 함
  2. `key`가 임의로 변경되는 구조는 사용하면 안됨

- 리스트의 다른 항목들 사이에서 **해당 항목을 고유하게 식별할 수 있는 문자열** 사용
  ```jsx
  const numbers = [1, 2, 3, 4, 5];
  const listItems = numbers.map((numbers) => (
    <li key={numbers.toString()}>{number}</li>
  ));
  ```
- **데이터의 ID** 사용
  ```jsx
  const todoItems = todos.map((todo) => <li key={todo.id}>{todo.text}</li>);
  ```
- **항목의 인덱스** 사용
  (항목의 순서가 바뀔 수 있는 경우 권장하지 않음)
  ```jsx
  const todoItems = todos.map((todo, index) => (
    <li key={index}>{todo.text}</li>
  ));
  ```
- 임의의 **문자열을 조합**해 사용

### 5. Key로 컴포넌트 추출

- `key`는 주변 배열의 `context`에서만 의미 있음

⇒ `ListItems` 컴포넌트를 추출한 경우, `ListItems` 안에 있는 `<li>` 엘리먼트가 아니라 `<ListItems />` 엘리먼트가 `Key`를 가져야 함

```jsx
// Wrong
function ListItems(props) {
  const value = props.value;
  return (
    // 여기에는 key를 지정할 필요 없음
    <li key={value.toString()}>{value}</li>
  );
}

function NumberList(props) {
  const numbers = props.numbers;
  const listItems = numbers.map((number) => (
    // 여기에 key를 지정해야 함
    <ListItems value={number} />
  ));
  return <ul>{listItems}</ul>;
}
```

```jsx
// correct
function ListItems(props) {
  return <li>{props.value}</li>;
}

function NumberList(props) {
  const numbers = props.numbers;
  const listItems = numbers.map((number) => (
    // Key 지정 올바른 자리
    <ListItems key={number.toString()} value={number} />
  ));
  return <ul>{listItems}</ul>;
}
```

### 6. Key는 형제 사이에서 고유한 값

- `Key`는 배열 안 형제 사이에서 고유하고, 전체 범위에서 고유할 필요는 없음

⇒ 두 개의 다른 배열을 만들 때 동일한 `Key` 사용 가능

```jsx
function Blog(props) {
  const sidebar = (
    <ul>
      {props.posts.map((post) => (
        <li key={post.id}>{post.title}</li>
      ))}
    </ul>
  );
  const content = props.posts.map((post) => (
    <div key={post.id}>
      <h3>{post.title}</h3>
      <p>{post.content}</p>
    </div>
  ));
  return (
    <div>
      {sidebar}
      <hr />
      {content}
    </div>
  );
}

const posts = [
  { id: 1, title: "Hello World", content: "리액트에 대해 배워봅시다." },
  { id: 2, title: "List and Key", content: "리스트와 키에 대해 배웁니다." },
];

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<Blog posts={posts} />);
```

- 컴포넌트에서 `key`와 동일한 값이 필요하면 다른 이름의 `prop`으로 명시적으로 전달함

```jsx
const content = post.map((post) => (
  <Post key={post.id} id={post.id} title={post.title} />
));
```

- 위에서 `post` 컴포넌트는 `props.id` 를 읽을 수 있지만 `props.key` 는 읽을 수 없음

### 7. JSX에 map() 포함

- 위 예시에서 별도의 `listItems` 변수를 선언하고 이를 JSX에 포함함

```jsx
function NumberList(props) {
  const numbers = props.numbers;
  const listItems = numbers.map((number) => (
    <ListItems key={number.toString()} value={number} />
  ));
  return <ul>{listItems}</ul>;
}
```

- JSX를 사용 시 중괄호 안에 모든 표현식 포함 가능, `map()` 함수의 결과 인라인으로 처리 가능

```jsx
function NumberList(props) {
  const numbers = props.numbers;
  return (
    <ul>
      {numbers.map((number) => (
        <ListItems key={number.toStrimg()} value={number} />
      ))}
    </ul>
  );
}
```

- 이 방식은 코드가 깔끔해지지만 너무 중첩된다면 컴포넌트로 추출하는 것이 좋음

### 8. 배열 항목들을 필터링

- 배열 메서드인 `filter` , `map`활용

```jsx
export const people = [
  {
    id: 0,
    name: "Creola Katherine Johnson",
    profession: "mathematician",
    accomplishment: "spaceflight calculations",
    imageId: "MK3eW3A",
  },
  {
    id: 1,
    name: "Mario José Molina-Pasquel Henríquez",
    profession: "chemist",
    accomplishment: "discovery of Arctic ozone hole",
    imageId: "mynHUSa",
  },
  {
    id: 2,
    name: "Mohammad Abdus Salam",
    profession: "physicist",
    accomplishment: "electromagnetism theory",
    imageId: "bE7W1ji",
  },
  {
    id: 3,
    name: "Percy Lavon Julian",
    profession: "chemist",
    accomplishment:
      "pioneering cortisone drugs, steroids and birth control pills",
    imageId: "IOjWm71",
  },
  {
    id: 4,
    name: "Subrahmanyan Chandrasekhar",
    profession: "astrophysicist",
    accomplishment: "white dwarf star mass calculations",
    imageId: "lrWQx8l",
  },
];
```

```jsx
export function getImageUrl(person) {
  return "https://i.imgur.com/" + person.imageId + "s.jpg";
}
```

```jsx
import { people } from "./data.js";
import { getImageUrl } from "./utils.js";

export default function List() {
  const chemists = people.filter((person) => person.profession === "chemist");

  const listItems = chemists.map((person) => (
    <li>
      <img src={getImageUrl(person)} alt={person.name} />
      <p>
        <b>{person.name}:</b>
        {" " + person.profession + " "}
        known for {person.accomplishment}
      </p>
    </li>
  ));
  return <ul>{listItems}</ul>;
}
```
