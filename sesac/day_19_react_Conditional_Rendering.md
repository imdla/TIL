## <mark color="#fbc956">Conditional Rendering (조건부 렌더링)</mark>

### 1. **Conditionnal Rendering**

- 조건에 따라 렌더링이 달라지는 것
- 특정 조건에 따라 화면에 숨기거나 나타나게 하고 싶은 경우 사용할 수 있는 방식
- 자바스크립트의 `if` 문이나 **조건 연산자**를 활용

1. 아래의 두 컴포넌트를 만듦

```jsx
function UserGreeting(props) {
  return <h1>환영합니다!</h1>;
}

function GuestGreeting(props) {
  return <h1>로그인 해주세요.</h1>;
}
```

1. 사용자의 로그인 상태에 맞게 위 컴포넌트 중 하나를 보여주는 `Greeting` 컴포넌트 만듦

```jsx
function Greeting(props) {
  const isLoggedIn = props.isLoggedIn;
  if (isLoggedIn) {
    return <UserGreeting />;
  }
  return <GuestGreeting />;
}

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<Greeting isLoggedIn={false} />);
```

- 위의 예시는 `isLoggedIn` `prop`에 따라 다른 인사말을 렌더링 함 ⇒ **조건부 렌더링**

### 2. truthy 와 falsy

- t**ruthy**
  - `true`
  - `{}` (empty object)
  - `[]` (empty array)
  - `1` (number, not zero)
  - `"0"`, `"false"` (string, nor empty)
- **falsy**
  - `false`
  - `0`, `-0` (zero, minus zero)
  - `0n` (BigInt zero)
  - `''`, `""`, ```` (empty string)
  - `null`
  - `undefined`
  - `NaN` (not a number)

### 3. Element Variable

- 엘리먼트를 저장하기 위해 변수 사용 가능
- 출력의 다른 부분은 변하지 않은 채 컴포넌트의 일부를 조건부 렌더링 할 수 있음

```jsx
function LoginButton(props) {
  return <button onClick={props.onClick}>로그인</button>;
}

function LogoutButton(props) {
  return <button onClick={props.onClick}>로그아웃</button>;
}
```

1. 로그인과 로그아웃 버튼을 나타내는 두 컴포넌트를 만들고

   아래에서 `LoginControl`이라는 컴포넌트를 만듦

```jsx
function LoginControl(props) {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLoginClick = () => {
    setIsLoggedIn(true);
  };

  const handleLogoutClick = () => {
    setIsLoggedIn(false);
  };

  let button;
  if (isLoggenIn) {
    button = <LogoutButton onClick={handleLogoutClick} />;
  } else {
    button = <LoginButton onClick={handleLoginClick} />;
  }

  return (
    <div>
      <Greeting isLoggedIn={isLoggedIn} />
      {button}
    </div>
  );
}
```

- 컴포넌트는 현재 상태에 맞게 `<LoginButton />`이나 `<LogoutButton />` 을 렌더링하고, 이전 예시의 `<Greeting />`도 함께 렌더링함

### 4. if 문

```jsx
function Item({ name, isPacked }) {
  if (isPacked) {
    return <li className="item">{name} ✅</li>;
  }
  return <li className="item">{name}</li>;
}

export default function PackingList() {
  return (
    <section>
      <h1>Sally Ride's Packing List</h1>
      <ul>
        <Item isPacked={true} name="Space suit" />
        <Item isPacked={true} name="Helmet with a golden leaf" />
        <Item isPacked={false} name="Photo of Tam" />
      </ul>
    </section>
  );
}
```

- JSX 아닌 `null` 리턴해 화면에 아무것도 표시하지 않을 수 있음

```jsx
function Item({ name, isPacked }) {
  if (isPacked) {
    return null;
  }
  return <li className="item">{name}</li>;
}
```

### 5. Inline conditions

- 여러 조건을 JSX 안에서 인라인으로 처리하는 방법
- **inline `if`** ⇒ 논리 AND 연산자 ( `&&` ) 사용
- **inline `if-else`** ⇒ 삼항 조건연산자 `condition ? true : false` 사용

### 6. 논리 AND 연산자

- JSX 안에는 중괄호 이용해 표현식을 포함할 수 있음
  ⇒ 그 안에 논리 연산자 `&&` 를 사용해 엘리먼트 조건부를 만듦
- 조건이 참일 때 렌더링하고, 그렇지 않다면 아무것도 렌더링 하지 않음
- `true && expression` → `expression` (출력됨)
- `false && expression` → `false` (건너뜀)

```jsx
function Item({ name, isPacked }) {
  return (
    <li className="item">
      {name} {isPacked && "✅"}
    </li>
  );
}
```

> **falsy 표현식 반환 식 주의**
> ⇒ `&&` 뒤의 표현식은 건너뛰지만, falsy 표현식이 반환됨
>
> ```jsx
> render() {
> 	const count = 0;
> 	return (
> 		<div>
> 			{count && <h1>Message: {count}</h1>}
> 		</div>
> 	);
> }
> ```
>
> 위의 예시에서 `<div>0</div>`이 render 메서드에서 반환됨

```jsx
function Mailbox(props) {
  const unreadMessages = props.unreadMessages;
  return (
    <div>
      <h1>Hello!</h1>
      {unreadMessages.length > 0 && (
        <h2>현재 {unreadMessages.length}개의 읽지 않은 메세지가 있습니다.</h2>
      )}
    </div>
  );
}

const messages = ["React", "Re: React", "Re:Re: React"];

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<Mailbox unreadMessages={messages} />);
```

### 7. 삼항 조건 연산자

- 삼항 연산자 `condition ? true : false` 사용해 엘리먼트를 조건부로 렌더링함

```jsx
function Item({ name, isPacked }) {
  return (
    <li className="item">{isPacked ? <del>{name + " ✅"}</del> : name}</li>
  );
}
```

```jsx
function UserStatus(props) {
  return (
    <div>
      이 사용자는 현재 <b>{props.isLoggedIn ? "로그인" : "로그인하지 않은"}</b>{" "}
      상태입니다.
    </div>
  );
}
```

- 아래의 예처럼, 문자열이 아닌 엘리먼트를 넣어 사용 가능

```jsx
function LoginControl(props) {
  const [isLoggedIn, setLoggedIn] = useState(false);

  const handleLoginClick = () => {
    setIsLoggedIn(true);
  };

  const handleLogoutClick = () => {
    setIsLoggedIn(false);
  };

  return (
    <div>
      <Greeting isLoggedIn={isLoggedIn} />
      {isLoggedIn ? (
        <LogoutButton onClick={handleLogoutClick} />
      ) : (
        <LoginButton onClick={handleLoginClick} />
      )}
    </div>
  );
}
```

### 8. 변수에 조건부로 JSX 할당

- 기본 JavaScript 문법을 활용

```jsx
function Item({ name, isPacked }) {
  let itemContent = name;
  if (isPacked) {
    itemContent = name + " ✅";
  }
  return <li className="item">{itemContent}</li>;
}
```

### 9. Component 렌더링 막기

- 다른 컴포넌트에 의해 렌더링될 때 컴포넌트 자체를 숨기고 싶으면
  ⇒ `null` 반환

```jsx
function WarningBanner(props) {
  if (!props.warning) {
    return null;
  }
  return <div>경고!</div>;
}

function MainPage(props) {
  const [showWarning, setShowWarning] = useState(false);

  const handleToggleClick = () => {
    setShowWarning((prevShowWarning) => !prevShowWarning);
  };

  return (
    <div>
      <WarningBanner warning={showWarning} />
      <button onClick={handleToggleClick}>
        {showWarning ? "감추기" : "보이기"}
      </button>
    </div>
  );
}
```

- 컴포넌트의 `render` 메서드로 부터 `null` 반환
  ⇒ 생명주기 메서드 호출에 영향 없음
  ⇒ `componenetDidUpdate` 는 계속해서 호출됨

```jsx
const userType = "user";

const mappingPage = {
  admin: <AdminPage></AdminPage>,
  manager: <ManagerPage></ManagerPage>,
  user: <UserPage></UserPage>,
};

const renderedPage = mappingPage[userType];
```
