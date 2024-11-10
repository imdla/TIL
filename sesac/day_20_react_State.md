## <mark color="#fbc956">\*State and Lifecycle (상태와 생명주기)></mark>

```jsx
const root = React.DOM.creatRoot(document.getElementById("root"));

function Clock() {
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

예시에서 `Clock` 컴포넌트를 완전히 재사용하고 캡슐화 함

⇒ 이 컴포넌트는 스스로 타이머를 설정하고 매초 스스로 업데이트함

```jsx
const root = ReactDOM.createRoot(document.getElementById('root'));

function Clock(props) {
	return (
			<div>
				<h1>Hello, world!</h1>
				<h2>It is {props.date.toLocaleTimeString().}</h2>
			</div>
	);
}

function tick() {
	root.render(<Clock date={new Date()} />);
}

setInterval(tick, 1000);
```

위에서 `Clock` 이 타이머를 설정하고 매초 업데이트 하는 것이 `Clock` 의 구현 세부 사항이 되어야 하므로, `Clock`이 스스로 업데이트 하도록 아래의 예에서 구현함

```jsx
root.render(<Clock />);
```

위를 구현하기 위해 `Clock` 컴포넌트에 ‘state’ 를 추가해야 함

> **State**는 props와 유사하지만, 비공개이며 컴포넌트에 의해 완전히 제어됨

### 1. 함수에서 클래스로 변환

다섯 단계로 Clock과 같은 함수 컴포넌트를 클래스로 변환 가능

1. `React.Component` 를 확장하는 동일한 이름의 `class` 생성
2. `render()` 라고 불리는 빈 메서드를 추가
3. 함수의 내용을 `render()` 메서드 안으로 옮김
4. `render()` 내용 안에 있는 `props`를 `this.props`로 변경함
5. 남아있는 빈 함수 선언을 삭제

```jsx
class Clock extends React.Component {
  render() {
    return (
      <div>
        <h1>Hello, world!</h1>
        <h2>It is {this.props.date.toLocaleTimeString()}.</h2>
      </div>
    );
  }
}
```

⇒ `Clock` 은 이제 함수가 아닌 클래스로 정의 됨

`render` 메서드는 업데이트가 발생할 때마다 호출되나, 같은 DOM 노드로 `<Clock />`을 렌더링하는 경우 `Clock` 클래스의 단일 인스턴스만 사용

⇒ 로컬 state와 생명주기 메서드와 같은 부가적인 기능을 사용할 수 있게 함

### 2. 클래스에 로컬 State 추가

- 세 단계로 `date` 를 props에서 state로 이동

1. render() 메서드 안에 있는 `this.props.date` 를 `this.state.date` 로 변경

   ```jsx
   class Clock extends React.Component {
     render() {
       return (
         <div>
           <h1>Hello, world!</h1>
           <h2>It is {this.state.date.toLocalTimeString()}.</h2>
         </div>
       );
     }
   }
   ```

2. 초기 `this.state` 를 지정하는 class constructor 를 추가합니다.

   ```jsx
   class Clock extends React.Component {
     constructor(props) {
       // 클래스 컴포넌트는 항상 props로 기본 structor를 호출해야 함
       super(props);
       this.state = { date: new Date() };
     }

     render() {
       return (
         <div>
           <h1>Hello, world!</h1>
           <h2>It is {this.state.date.toLocaleTimeString()}.</h2>
         </div>
       );
     }
   }
   ```

3. `<Clock />`요소에서 `date` prop을 삭제함

   ```jsx
   root.render(<Clock />);
   ```

   ```jsx
   class Clock extends React.Component {
     constructor(props) {
       super(props);
       this.state = { date: new Date() };
     }

     render() {
       return (
         <div>
           <h1>Hello, world!</h1>
           <h2>It is {this.state.date.toLocaleTimeString()}.</h2>
         </div>
       );
     }
   }

   const root = ReactDOM.createRoot(document.getElementById("root"));
   root.render(<Clock />);
   ```

### 3. 생명주기 메서드를 클래스에 추가

- **마운팅** - 처음 DOM에 렌더링됨
- **언마운팅** - 생성된 DOM이 삭제됨

```jsx
class Clock extends React.component {
  constructor(props) {
    super(props);
    this.state = { date: new Date() };
  }

  componentDidMount() {}

  componentWillUnmount() {}

  render() {
    return (
      <div>
        <h1>Hello, world!</h1>
        <h2>It is {this.state}</h2>
      </div>
    );
  }
}
```

- `componentDidMount()` - 컴포넌트 출력물이 DOM에 렌더링 된 후 실행

```jsx
componentDidMount() {
	this.timerID = setInterval(
		() => this.tick(),
		1000
	);
}
```

- `componentWillUnmount()` - 생명주기 메서드 안에 있는 타이머 분해

```jsx
componentWillUnmount() {
	clearInterval(this.timerID);
}
```

아래에서 `Clock` 컴포넌트가 매초 작동하도록 하는 `tick()` 메서드 구현함

⇒ 컴포넌트 로컬 state 업데이트 위해 `this.setState()` 사용

```jsx
class Clock extends React.Component {
  constructor(props) {
    super(props);
    this.state = { date: new Date() };
  }

  componentDidMount() {
    this.timerID = setInterval(() => this.tick(), 1000);
  }

  componentWillUnmount() {
    clearInterval(this.timerID);
  }

  tick() {
    this.setState({
      date: new Date(),
    });
  }

  render() {
    return (
      <div>
        <h1>Hello, world!</h1>
        <h2>It is {this.state.date.toLocaleTimeString()}.</h2>
      </div>
    );
  }
}

const root = ReactDOM.createRoot(document.detElementById("root"));
root.render(<Clock />);
```

### 4. State 사용

- **State 수정** ⇒ `setState()` 사용
- `this.state` 를 수정할 수 있는 공간은 constructor임

```jsx
// Wrong
this.state.comment = "Hello";
// Correct
this.setState({ comment: "Hello" });
```

- **State 업데이트는 비동기적 일 수 있음**
  - `this.props` 와 `this.state` 가 비동기적으로 업데이트 될 수 있음
    ⇒ 함수를 인자로 사용하는 `setState()` 사용함

```jsx
// Wrong
this.setState({
  counter: this.state.counter + this.props.increment,
});
//Correct
this.setState((state, props) => ({
  counter: state.counter + props.increment,
}));
```

- **State 업데이트는 병합됨**
  - `setState()` 를 호출할 때 React는 제공한 객체를 현재 state로 병합함

```jsx
constructor(props) {
	super(props);
	this.state = {
		posts: [],
		comments: []
	};
}
```

- state는 다양한 독립적인 변수를 포함할 수 있음
  ⇒ 별도의 `setState()` 호출로 이러한 변수를 독립적으로 업데이트 할 수 있음

```jsx
componentDidMount() {
	fetchPosts().then(response => {
		this.setState({
			posts: response.posts
		});
	});

	fetchComments().then(response => {
		this.setState({
			comments: response.comments
		});
	});
}
```

### 5. 데이터는 아래로 흐름

하향식 / 단방향식 데이터 흐름 ⇒ state가 소유하고 설정한 컴포넌트 이외에는 어떤 컴포넌트에도 접근할 수 없음, 컴포넌트는 자신의 state를 자식 컴포넌트에 props로 전달할 수 있음

```jsx
<FormattedDate date={this.state.date} />;

function FormattedDate(props) {
  return <h2>It is {props.date.toLocaleTimeString()}.</h2>;
}
```
