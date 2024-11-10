## <mark color="#fbc956">Handling Event (이벤트 처리하기)</mark>

### 1. 이벤트 처리 문법

- React의 이벤트를 **카멜 케이스(camelCase)** 사용
- JSX 사용해 문자열 아닌 **함수로 구현해 Props 형태로 이벤트 핸들러 전달**

### 2. 이벤트 처리 차이

1. **camelCase와 JSX 사용**
   - HTML
     ```html
     <button onclick="activate()">Activate</button>
     ```
   - React
     ```jsx
     <button onClick={activate}>Activate</button>
     ```
2. `preventDefault` 호출

   - React 에서는 `false` 를 반환해도 기본 동작을 방지 할 수 없음
   - 반드시 `preventDefault` 명시적으로 호출해야함
   - HTML
     ```html
     <form onsubmit="console.log('You clicked submit.'); return false">
       <button type="submit">Submit</button>
     </form>
     ```
   - React

     ```jsx
     function Form() {
       function handleSubmit(e) {
         e.preventDefault();
         console.log("You clicked submit.");
       }

       return (
         <form onSubmit={handleSubmit}>
           <button type="submit">Submit</button>
         </form>
       );
     }
     ```

### 3. 클래스 컴포넌트의 이벤트 처리

- **바인딩**

  > 💡 JavaScript에서 클래스 메서드는 **바인딩 되어 있지 않음**
  > ⇒ `this.handleClick`을 바인딩하지 않고 `onClick` 에 전달하면 함수가 실제 호출될 때 `this`는 `undefined` 됨
  >
  > ⇒ `onClick={this.handleClick}`과 같이 **`()`를 사용하지 않고 메서드를 참조할 경우 해당 메서드 바인딩 필요**

  ```jsx
  class Toggle extends React.Component {
    constructor(props) {
      super(props);
      this.state = { isToggleOn: true };

      // callback에서 'this'를 사용하기 위해서는 바인딩 필수적
      this.handleClick = this.handleCilck.bind(this);
    }

    handleclick() {
      this.setState((prevState) => ({
        isToggleOn: !prevState.isToggleOn,
      }));
    }

    render() {
      return (
        <button onClick={this.handleClick}>
          {this.state.isToggleOn ? "On" : "Off"}
        </button>
      );
    }
  }
  ```

- 바인드 해결 방법 ⇒ **Class fields syntax** 사용

  ```jsx
  class MyButton extends React.Component {
    // 'this'가 handleClick에서 바인딩 됨
    handleClick = () => {
      console.log("this is:", this);
    };

    render() {
      return <button onClick={this.handleClick}>Click</button>;
    }
  }
  ```

- 바인드 해결 방법 ⇒ **콜백에 Arrow function** 사용

  ```jsx
  class MyButton extends React.Component {
    handleClick = () => {
      console.log("this is:", this);
    };

    render() {
      // 'this'가 handleClick에서 바인딩 됨
      return <button onClick={() => this.handleClick()}>Click</button>;
    }
  }
  ```

  - 이 문법은 `handleClick`이 렌더링 될 때 마다 다른 콜백이 생성됨
    ⇒ 콜백이 하위 컴포넌트에 props로서 전달될 경우 그 컴포넌트 들은 다시 렌더링을 수행 할 수 있음
    ⇒ **생성자 안에서 바인딩**하거나 **클래스 필드 문법** 사용 권장

### 4. 함수 컴포넌트의 이벤트 처리

- 함수 안에 함수로 정의
- Arrow function 사용 정의

```jsx
import React, { useState } from "react";

function Toggle(props) {
  const [isToggleOn, setIsToggleOn] = useState(true);

  // 1번. 함수 안에 함수로 정의
  function handleClick() {
    setIsToggleOn((isToggleOn) => !isToggleOn);
  }

  // 2번. arrow function 사용하여 정의
  const handleClick = () => {
    setIsToggleOn((isToggleOn) => !isToggleOn);
  };

  return <button onClick={handleClick}>{isToggleOn ? "On" : "Off"}</button>;
}
```

- 더 알아보기

```jsx
export default function Button() {
  function handleClick() {
    console.log("버튼 클릭");
  }

  return <button onClick={handleClick}>클릭</button>;
}
```

- **함수 전달**
  - `<button onClick={handleClick}>` : `함수()` 와 같은 형태로 호출하는 것이 아닌 함수를 정의하고 이를 porp 형태로 함수 이름만 전달
  - `<button onClick={() => alert('...')}>` : 인라인 형태로 함수 전달 시 익명 함수 형태로 전달 가능

### 5. 이벤트 핸들러 내 Prop 읽기

- 이벤트 핸들러를 컴포넌트 내부에서 관리하게 되면 해당 컴포넌트의 prop에 접근 가능

```jsx
export default function AlertButton({ message, children }) {
  return <button onClick={() => alert(message)}>{children}</button>;
}
```

```jsx
import AlertButton from "./AlertButton.jsx";

export default function Toolbar() {
  return (
    <div>
      <AlertButton message="확인되었습니다">확인</AlertButton>
      <AlertButton message="취소되었습니다">취소</AlertButton>
    </div>
  );
}
```

### 5. 이벤트 핸들러에 인자 전달하기

- 이벤트 핸들러에 추가적인 매개변수를 전달하는 것 일반적
- 클래스 컴포넌트
  ```jsx
  // arrow function
  // 명시적으로 event를 두번째 매개변수로 넣어줌
  <button onClick={(event) => this.deleteRow(id, event)}>Delete Row</button>
  // Function.prototype.bind
  // event가 자동으로 id 이후에 두번째 매개변수로 전달됨
  <button onClick={this.deleteRow.bind(this, id)}>Delete Row</button>
  ```
- 함수 컴포넌트

  ```jsx
  function Mybutton(props) {
    const handleDelete = (id, event) => {
      console.log(id, event.target);
    };

    return <button onClick={(event) => handleDelete(1, event)}>Delete</button>;
  }
  ```

- 자식 컴포넌트의 이벤트 핸들러를 부모 컴포넌트의 단계에서 지정할 경우

  ```jsx
  export default function Button({ onClick, children }) {
    return <button onClick={onClick}>{children}</button>;
  }
  ```

  ```jsx
  import Button from "./Button.jsx";

  export default function AlertButton({ alertName }) {
    function handleClick() {
      alret(`{alertName}되었습니다`);
    }

    return <Button onClick={handleClick}>{alertName}</Button>;
  }
  ```

  ```jsx
  import AlertButton from './AlertButton.jsx'

  export dafult function Toolbar() {
  	return(
  		<div>
  			<AlertButton alertName="확인"></AlertButton>
  			<AlertButton alertName="취소"></AlertButton>
  		</div>
  	);
  }
  ```
