## <mark color="#fbc956">React</mark>

### 1. React

- 페이스북에서 개발한 자바스크립트 라이브러리
- 사용자 인터페이스(UI) 구현 위해 사용
- `Virtual DOM` 활용해 화면에 변화가 필요한 부분만 효율적으로 렌더링 가능
- 코드를 Component화하여 간편하고 빠르게 개발 진행 및 쉬운 유지보수

### 2. Component (컴포넌트)

- 재사용 가능한 UI 요소
- 렌더링 가능한 요소를 반환하는 함수를 구성하는 것

---

## <mark color="#fbc956">JSX</mark>

### **1. JSX (JavaScript XML)**

- JavaScipt를 확장한 문법
- React element 생성
- JavaScript 코드 안에 HTML Element가 있는 구조

### 2. JSX 문법

1. **하나의 루트 엘리먼트로 반환**

   - JSX 엘리먼트는 반드시 단 하나의 엘리먼트로 감싸진 구조로 반환
   - 두 개 이상의 엘리먼트 반환 불가

   ```jsx
   // 가능
   function App() {
     return (
       <div>
         <h1>title</h1>
         <p>content</p>
       </div>
     );
   }

   export default App;
   ```

   ```jsx
   // 불가능
   function App() {
   	return (
   		<h1>title</h1>
   		<p>content</p>
   	)
   }

   export default App;
   ```

2. **모든 태그는 닫기**

   - 모든 태그는 반드시 명시적으로 닫아야 함

   ```jsx
   // 가능
   function App() {
     return (
       <div>
         <h1>title</h1>
         <img src="" alt="image" />
       </div>
     );
   }

   export default App;
   ```

   ```jsx
   // 불가능
   function App() {
   	return (
   		<>
   			<h1>title</h1>
   			<img
   				src=""
   				alt="image"
   			>
   		</>
   	)
   }

   export default App;
   ```

3. **대부분 캐멀 케이스로 표현**
   - 작성한 JSX는 JavaScript는 바뀌는 단계에서 작성된 어트리뷰트는 JavaScript 객체의 키가 됨
   - 컴포넌트에서는 어트리뷰트를 변수로 사용해야 하는 상황이 있음
   - JavaScript는 변수명에 제한이 있어 대시 포함하거나 예약어 사용 불가
     - `class` - 자바스크립트의 예약어로 사용되어 `className` 으로 수정 사용
     - `<label>` 의 `for` - `htmlFor` 로 변경해서 사용
     - `checked` , `selected` 등의 단일 속성만 있어도 되는 어트리뷰트 - `checked={true}` 와 같이 값 명시

### 2. JSX에 표현식 포함하기

```jsx
const name = "John";
const element = <h1>Hello, {name}</h1>;
```

- `name` 이라는 변수를 선언한 후, 중괄호로 감싸 JSX 안에 사용
  - JSX 중괄호 안에는 유효한 모든 JavaScript 표현식을 넣을 수 있음

```jsx
function formatName(user) {
	return user.firstName + ' ' + user.lastName;
}

const user = {
	firstName: 'Harper',
	lastName: 'Perez'
};

const element = {
	<h1>
		Hello, {formatName(user)}!
	</h1>
};
```

- 위는 함수 호출 결과인 `formatName(user)`을 `<h1>`엘리먼트에 포함함

### 2. JSX도 표현식

- 컴파일이 끝나면 JSX 표현식이 정규 JavaScript 함수 호출이 되고 JavaScript 객체로 인식됨
- JSX를 `if` 구문 및 `for loop` 안에 사용하고, 변수에 할당하고, 인자로서 받아들이고, 함수로부터 반환할 수 있음

```jsx
function Greeting(user) {
  if (user) {
    return <h1>Hello, {formatName(user)}!</h1>;
  }
  return <h1>Hello, Stranger.</h1>;
}
```

### 3. JSX 속성 정의

1. 어트리뷰트에 **따옴표(`""` , `''`)** 이용 : **문자열 어트리뷰트** 전달

```jsx
const element = <a href="https://www.react.js.org"> link </a>;
```

1. 어트리뷰트에 **중괄호(`{}`)**를 사용 : **JavaScript 표현식(데이터)** 삽입

   - 따옴표(문자열 값에 사용) 또는 중괄호(표현식에 사용) 중 하나만 사용
   - **동일한 어트리뷰트에 두 가지를 동시에 사용 불가**

   ```jsx
   const element = <img src={user.avatarUrl}></img>;
   ```

   - JavaScript 코드를 직접 적용

   ```jsx
   function App() {
     const name = "홍길동";
     return <h1>{name}'s App</h1>;
   }

   export default App;
   ```

   - JavaScript의 변수나 표현식 활용 가능

   ```jsx
   function App() {
     const grade = 80;
     return (
       <h1>
         Grade : {grade}, {grade > 70 ? "pass" : "fail"}
       </h1>
     );
   }

   export default App;
   ```

   - 함수의 값을 받아와 사용하는 형태 가능

   ```jsx
   const today = new Date();

   function formatDate(date) {
     return new Intl.DateTimeFormat(
       'en-US',
       { weekday: 'long' }
     ).format(date);
   }

   export default function TodoList() {
     return (
       <h1>To Do List for {formatDate(today)}</h1>
     );
   }

   export default App;
   ```

2. **이중 중괄호 사용**

   - JSX의 중괄호에 **객체** 전달 가능, 이중 중괄호 형태로 표현
   - JSX에서 인라인으로 CSS 스타일 부여도 가능

   ```jsx
   function App() {
     return (
       <ul
         style={{
           backgroundColor: "black",
         }}
       >
         <li>Coffee</li>
         <li>Tea</li>
         <li>Ade</li>
       </ul>
     );
   }
   ```

   - 객체와 중괄호에 대해 더 알아보기

   ```jsx
   const person = {
     name: "John",
     theme: {
       backgroundColor: "grey",
       color: "pink",
     },
   };

   export default function TodoList() {
     return (
       // person이라는 객체 변수의 theme 데이터 접근
       <div style={person.theme}>
         <img className="avatar" src="img.url" alt="Jodn" />
         <ul>
           <li>lectures</li>
           <li>movies</li>
           <li>sports</li>
         </ul>
       </div>
     );
   }
   ```

> JSX 는 JavaScript에 가까움
> → React DOM은 **camelCase** 프로퍼티 명명 규칙 사용

### 4. JSX로 자식 정의

- JSX 태그는 자식을 포함 가능

```jsx
const element = (
  <div>
    <h1>Hello!</h1>
    <h2>Good to see you here.</h2>
  </div>
);
```

### 5. JSX는 주입 공격을 방지

- JSX에 사용자 입력을 삽입하는 것은 안전함

```jsx
const title = response.potentiallyMaliciousInput;
// 이것은 안전합니다.
const element = <h1>{title}</h1>;
```

- React DOM은 JSX에 삽입된 모든 값을 렌더링 하기 전에 이스케이프함
  - 애플리케이션에서 명시적으로 작성되지 않은 내용은 주입되지 않음
  - 모든 항목은 렌더링되기 전에 문자열로 변환
  - XXS (cross-site-scripting) 공격 방지

### 6. JSX는 객체 표현

- Babel은 JSX를 `React.createElement()` 호출로 컴파일 함
  (아래의 두 예제는 동일함)

```jsx
const element = <h1 className="greeting">Hello, world!</h1>;
```

```jsx
const element = React.createElement(
  "h1",
  { className: "greeting" },
  "Hello, world!"
);
```

- **`React.createElement()`**
  - 버그가 없는 코드를 작성하는 데 도움이 되도록 몇 가지 검사 수행
  - 기본적으로 다음과 같은 **객체** 생성 ⇒ 이 객체를 **React 엘리먼트**라함

```jsx
const element = {
  type: "h1",
  props: {
    className: "greeting",
    children: "Hello, world!",
  },
};
```
