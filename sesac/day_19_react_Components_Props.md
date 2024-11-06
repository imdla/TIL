## <mark color="#fbc956">Components and Props</mark>

### 1. 함수 컴포넌트와 클래스 컴포넌트

- **함수 컴포넌트**

```jsx
function Welcome(props) {
  return <h1>Hello, {props.name}</h1>;
}
```

- **클래스 컴포넌트**

```jsx
class Welcome extends React.Component {
  render() {
    return <h1>Hello, {this.props.name}</h1>;
  }
}
```

### 2. 컴포넌트 렌더링

- 아래의 예는 DOM 태그를 통해 React 엘리먼트 나타냄

```jsx
const element = <div />;
```

- React 엘리먼트는 사용자 정의 컴포넌트로도 나타낼 수 있음

```jsx
const element = <Welcome name="John" />;
```

- React가 사용자 정의 컴포넌트로 작성한 엘리먼트 발견
  ⇒ JSX 어트리뷰트와 자식을 해당 컴포넌트에 단일 **객체**로 전달
  ⇒ 이 객체를 `props` 라고 함

```jsx
function Welcome(props) {
  return <h1>Hello, {props.name}</h1>;
}

const root = ReactDOM.createRoot(document.getElementById("root"));
const element = <Welcome name="John" />;
root.rendor(element);
```

> 컴포넌트의 **이름은 항상 대문자**로 시작
> ⇒ React는 소문자로 시작하는 컴포넌트를 DOM 태그로 처리함

### 3. 컴포넌트 합성

- 컴포넌트는 자신의 출력에 다른 컴포넌트를 참조 가능
- 모든 세부 단계에서 동일한 추상 컴포넌트 사용 가능

```jsx
function Welcome(props) {
  return <h1>Hello, {props.name}</h1>;
}

function App() {
  return (
    <div>
      <Welcome name="Mia" />
      <Welcome name="John" />
      <Welcome name="Michle" />
    </div>
  );
}
```

### 4. 컴포넌트 추출

- 컴포넌트에서 여러 개의 작은 컴포넌트로 나눌 수 있음

```jsx
function Comment(props) {
  return (
    <div className="Comment">
      <div className="UserInfo">
        <img
          className="Avatar"
          src={props.author.avatarUrl}
          alt={props.author.name}
        />
        <div className="UserInfo-name">{props.author.name}</div>
      </div>
      <div className="Comment-text">{props.text}</div>
      <div className="Comment-date">{formatDate(props.date)}</div>
    </div>
  );
}
```

- 위의 컴포넌트는 `author` (객체), `text` (문자열), `date` (날짜) 를 `props`로 받음

1. 위의 컴포넌트에서 `Avatar` 추출

   ```jsx
   function Avatar(props) {
     return (
       <img
         className="Avatar"
         src={props.user.avatarUrl}
         alt={props.user.name}
       />
     );
   }
   ```

2. 추출한 `Avatar` 를 Comment 컴포넌트에 변경

   ```jsx
   function Comment(props) {
     return (
       <div className="Comment">
         <div className="UserInfo">
           <Avatar user={props.author} />
           <div className="UserInfo-name">{props.author.name}</div>
         </div>
         <div className="Comment-text">{props.text}</div>
         <div className="Comment-date">{formatDate(props.date)}</div>
       </div>
     );
   }
   ```

3. Avatar 옆에 사용자의 이름을 렌더링하는 `UserInfo` 컴포넌트 추출

   ```jsx
   function UserInfo() {
     return (
       <div className="UserInfo">
         <Avatar user={props.user} />
         <div className="UserInfo-name">{props.user.name}</div>
       </div>
     );
   }
   ```

4. 추출한 `UserInfo` 를 `Comment` 에 변경

   ```jsx
   function Comment(props) {
     return (
       <div className="Comment">
         <UserInfoName user="props.author" />
         <div className="Comment-text">{props.text}</div>
         <div className="Comment-date">{formatDate(props.date)}</div>
       </div>
     );
   }
   ```

### 5. Props는 읽기 전용(Read-Only)

- 함수 컴포넌트나 클래스 컴포넌트 모두 컴포넌트 **자체 `props`를 수정하면 안됨**
  - 자식 컴포넌트에서 `props` 를 수정할 수 없음
  - 부모 컴포넌트만 `props` 변경 가능
- **단방향 데이터 흐름**
  - 부모 → 자식 방향으로만 데이터 전달

```jsx
function sum(a, b) {
  return a + b;
}
```

- 위의 함수들은 **순수 함수**라고 함
  - 입력값을 바꾸려하지 않고 항상 동일한 입력값에 대해 동일한 결과를 반환

```jsx
function withdraw(account, amount) {
  account.total -= amount;
}
```

- 위의 함수는 자신의 입력값을 변경하기 때문에 순수 함수가 아님

> 모든 React 컴포넌트는 자신의 `props`를 다룰 때 반드시 순수 함수처럼 동작해야 함

### 6. 자식을 JSX로 전달

```jsx
import Card from "./Card";
import Avatar from "./Avatar";

<Card>
  <Avatar />
</Card>;
```

```jsx
export default function Card({ children }) {
  return <div className="card">{children}</div>;
}
```

```css
.card {
  width: fit-content;
  margin: 5px;
  padding: 5px;
  font-size: 20px;
  text-align: center;
  border: 1px solid #aaa;
  border-radius: 20px;
  backgound: #fff;
}
```

```jsx
function Card({ children }) {
  return <div className="card">{children}</div>;
}

export default function Profile() {
  return (
    <Card>
      <Avatar
        size={100}
        person={{
          name: "John",
          imageId: "Yfe0qp2",
        }}
      ></Avatar>
    </Card>
  );
}
```

---

## <mark color="#fbc956">Props</mark>

### 1. 컴포넌트에 정보 전달

- **`props`** : 컴포넌트에 정보 전달 위해 사용되는 개념
- 기본 코드, `Profile` 컴포넌트는 `Avatar` 컴포넌트에 아무런 정보 전달하지 않음

```jsx
import Profile from "./Profile";

function App() {
  return <Profile></Profile>;
}
```

```jsx
function Avatar() {
  return (
    <img
      className="avatar"
      src="https://i.imgur.com/1bX5QH6.jpg"
      alt="John"
      width={100}
      height={100}
    />
  );
}

export default function Profile() {
  return <Avatar></Avatar>;
}
```

### 2. 자식 컴포넌트에 Props 전달

- `Avatar` 에 정의된 변수 `Profile` 통해 넘기도록 함

```jsx
function Avatar() {
  const size = 100;
  const person = {
    name: "john",
    imageUrl: "https://i.imgur.com/1bX5QH6.jpg",
  };

  return (
    <img
      className="avatar"
      src={person.imageUrl}
      alt={person.name}
      width={size}
      height={size}
    />
  );
}

export default function Profile() {
  return <Avatar></Avatar>;
}
```

```jsx
export default function Profile() {
  return (
    <Avatar
      person={{
        name: "John",
        imageUrl: "https://i.imgur.com/1bX5QH6.jpg",
      }}
      size={100}
    ></Avatar>
  );
}
```

### 3. 자식 컴포넌트 내부에서 Props 읽기

- 자식 컴포넌트(`Avatar`) 내부적으로 넘겨 받은 정보 불러와서 사용

```jsx
function Avatar(props) {
  const size = props.size;
  const person = props.person;

  return (
    <img
      className="avatar"
      src={person.imageUrl}
      alt={person.name}
      width={size}
      height={size}
    />
  );
}
```

```jsx
function getImgUrl(person, size = "s") {
  return "https://i.imgur.com/" + person.imageId + size + ".jpg";
}

function Avatar(props) {
  let person = props.person;
  let size = props.size;

  return (
    <img
      className="avatar"
      src={getImgUrl(person)}
      alt={person.name}
      width={size}
      height={size}
    />
  );
}

export default function Profile() {
  return (
    <Avatar
      person={{
        name: "John",
        imageId: "1bX5QH6",
      }}
    ></Avatar>
  );
}
```

- `Avatar` 컴포넌트의 파라미터를 구조 분해 할당 형식으로 작성

```jsx
function Avatar({ person, size }) {}
```

- 컴포넌트와 `props` 활용해 `Avatar` 여러 번 작성

```jsx
function getImgUrl(person, size="s") {
	return (
		"https://i.imgur.com/" + person.imageId + size + ".jpg"
	);
}

// size의 기본값 설정
function Avatar({ person, size = 100 }) {
	return (
		<img
			className="avatar"
			src={getImgUrl(person)}
			alt={person.name}
			width={size}
			height={size}
		/>
	);
}

export default function Profile() {
	return (
		<Avatar
			person={{
				name: "John",
				imageId: "1bX5QH6",
			}}
		>
		</Avatar>
		<Avatar
			person={{
				size={80}
				name: "Mia",
				imageId: "OKS67lh",
			}}
		>
		</Avatar>
		<Avatar
			person={{
				name: "Linda",
				imageId: "1bX5QH6",
			}}
		>
		</Avatar>
	);
}
```

### 4. JSX spread 문법으로 props 전달

- **spread 문법(`…`)**
  - 부모로부터 넘겨 받은 `props` 를 그대로 자식 컴포넌트에게 전달할 경우
  - spread 문법은 제한적으로 사용

```jsx
import Profile from "./Profile";

function App() {
	return (
		<Profile
			person={{
				name: "John",
				imageId: "1bX5QH6",
			}}
			size={100}
		>
		</Profile/>
	);
}

export default App;
```

```jsx
export default function Profile(props) {
  return (
    <div>
      <Avatar {...props} />
    </div>
  );
}
```
