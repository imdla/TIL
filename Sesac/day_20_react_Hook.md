## <mark color="#fbc956">Hook과 함수 컴포넌트</mark>

### 1. **Hook**

- 함수 컴포넌트에서 React state와 생명주기 기능을 연동 할 수 있게 해주는 함수
- class 안에서는 동작하지 않음

### 2. State Hook

1. **state 변수 선언**

   - 클래스를 사용할 때, constructor 안에서 `this.state`를 `{ count: 0 }`으로 설정함으로써 count를 0으로 초기화함

   ```jsx
   class Example extends React.Component {
     constructor(props) {
       super(props);
       this.state = {
         count: 0,
       };
     }
   }
   ```

   - 함수 컴포넌트는 `this`를 가질 수 없기 때문에 `this.state` 를 할당하거나 읽을 수 없음
     ⇒ `useState` Hook을 직접 컴포넌트에 호출

   ```jsx
   import React, { useState } from "react";

   function Example() {
     const [count, setCount] = useState(0);
     // count가 변경된다면 count와 관련된 화면을 업데이트
     // state 변수 업데이트
     // 리액트 컴포넌트 다시 렌더링하도록 유발 setter 함수 -> setCount
   }
   ```

2. **state 가져오기**

   - 클래스 컴포넌트는 count를 보여주기 위해 `this.state.count`를 사용

   ```jsx
   <p>You clicked {this.state.count} times</p>
   ```

   - 함수 컴포넌트는 `count`를 직접 사용 가능

   ```jsx
   <p>You clicked {count} times</p>
   ```

3. **state 갱신하기**

   - 클래스 컴포넌트는 count를 갱신하기 위해 `this.setState()`를 호출

   ```jsx
   <button onClick={() => this.setState({ count: this.state.count + 1 })}>
     Click
   </button>
   ```

   - 함수 컴포넌트는 `setCount`와`count` 변수를 가지고 있어 `this` 를 호출하지 않아도 됨

   ```jsx
   <button onClick={() => setCount(count + 1)}>Click</button>
   ```

4. **`useState()` 사용법**

   ```jsx
   const [변수명, set함수명] = useState(초기값);
   ```

   ```jsx
   // useState Hook을 React에서 가져옴
   import React, { useState } from "react";

   function Example() {
     // useState Hook을 이용해
     // state 변수와 해당 state를 갱신하는 함수 만들고 초기값 설정
     const [count, setCount] = useState(0);

     return (
       <div>
         <p>클릭한 횟수는 {count}입니다.</p>
         {/* 버튼 클릭 시 setCount 함수 호출해 state 변수 갱신
   			React는 새로운 count 변수를 Example 컴포넌트에 넘기며
   			해당 컴포넌트 리렌더링 */}
         <button onClick={() => setCount(count + 1)}>클릭</button>
       </div>
     );
   }
   ```

5. **여러 state 변수 선언**

   - 하나의 컴포넌트 내에서 State Hook을 여러 개 사용 가능

   ```jsx
   function ExampleManyStates() {
     const [age, setAge] = useState(20);
     const [fruit, setFruit] = useState("apple");
     const [todos, setTodos] = useState([{ text: "Learn Hooks" }]);
     // ...
   }
   ```

   > **대괄호의 의미 ?**
   >
   > - 대괄호를 이용해 state 변수를 선언 시 자바스크립트 문법의 ‘**배열 구조 분해**’를 이용
   > - `useState`를 이용하여 변수를 선언하면 2개의 아이템 쌍이 들어있는 배열로 만듦
   >   - useState 함수로 반환되는 정보 각각 할당해주는 역할
   > - 첫 번째 아이템은 현재 변수, 두 번째 아이템은 해당 변수를 갱신해주는 함수임
   >   - 변수 부분을 `state 변수` , 함수 부분을 `setter 함수` 라 지칭

### 3. Effect Hook

> 💡 **side effects (effects) ?**
>
> - useEffect Hook을 이용해 React에 컴포넌트가 렌더링된 이후 어떤 일 수행할지 말함
> - **Effect Hook** 은 함수 컴포넌트 내에서 side effects를 수행할 수 있게 해줌
>   (React class의 `componentDidMount` 나 `componentDidUpdate`, `componentWillUnmount`와 같은 목적으로 제공되지만, 하나의 API로 통합된 것)

1.  **`useEffect()` 사용법**

    ```jsx
    useEffect(effect 함수, 의존성 배열);
    ```

    ```jsx
    import React, { useState, useEffect } from "react";

    function Example() {
      const [count, setCount] = useState(0);

      // componentDidMount, componentDidUpdate와 비슷
      useEffect(() => {
        document.title = `클릭 횟수: ${count}`;
      });

      return (
        <div>
          <p>클릭한 횟수는 {count}입니다.</p>
          <button onClick={() => setCount(count + 1)}>클릭</button>
        </div>
      );
    }
    ```

2.  **`useEffect()` 특징**

    - `useEffect()` 사용 ⇒ React는 DOM을 바꾼 후 ‘effect’ 함수 실행
    - React는 매 렌더링 이후 effects 실행
      (effects는 컴포넌트 안에 선언되어 있어 props와 state에 접근 가능)
    - effect 해제 ⇒ 해제하는 함수 반환 (선택적)

    ```jsx
    import React, { useState, useEffect } from "react";

    function FriendStatus(props) {
      const [isOnline, setIsOnline] = useState(null);

      function handleStatusChange(status) {
        setIsOnline(status.isOnline);
      }

      useEffect(() => {
        // 친구의 접속 상태 구독 => effect 사용
        ChatAPI.subscribeToFriendStatus(props.friend.id, heandleStatusChange);
        // 구독 해지 => effect 해제
        return () => {
          ChatAPI.unsubscribeFromFriendStatus(
            props.friend.id,
            heandleStatusChange
          );
        };
      });

      if (isOnline === null) {
        return "Loading...";
      }
      return isOnline ? "Online" : "Offline";
    }
    ```

3.  **여러 effect 사용**

    ```jsx
    function FriendStatusWithCounter(props) {
      const [count, setCount] = useState(0);
      useEffect(() => {
        document.title = `클릭 횟수: ${count}`;
      });

      const [isOnline, setIsOnline] = useState(null);
      useEffect(() => {
        ChatAPI.subscribeToFriendStatus(props.friend.id, handleStatusChange);
        return () => {
          ChatAPI.subscribeFromFriendStatus(
            props.friend.id,
            handleStatusChange
          );
        };
      });

      function handleStatusChange(status) {
        setIsOnline(status.inOnline);
      }
    }
    ```

    ⇒ **Hook을 사용**하면 구독을 추가하고 제거하는 로직과 같이 **서로 관련 있는 코드들을 한군데 모아서 작성 가능** ( class 컴포넌트에서 생명주기 메서드는 각각 쪼개어 넣어야 함)

### 4. 정리(Clean-up)를 이용하지 않는 Effects

> 💡 **정리를 이용하지 않는 Effects 사용 ?**
>
> - **React가 DOM을 업데이트 한 뒤 추가로 코드를 실행하는 경우**
>   (네트워크 리퀘스트, DOM 수동 조작, 로깅 등)

1. **Class 사용 예시**

   - class 컴포넌트에서 `render` 메서드 자체는 side effect을 발생시키지 않음
     ⇒ `componentDidMount`와 `componentDidUpdate` 를 이용해 side effect 함

   ```jsx
   class Example extends React.Component {
     consrtuctor(props) {
       super(props);
       this.state = {
         count: 0,
       };
     }

     componentDidMount() {
       document.title = `You clicked ${this.state.count} times`;
     }

     componentDidUpdate() {
       documnet.title = `You clicked ${this.state.count} times`;
     }

     render() {
       return (
         <div>
           <p>You clicked {this.state.count} times</p>
           <button
             onClick={() => this.setState({ count: this.state.count + 1 })}
           >
             Click
           </button>
         </div>
       );
     }
   }
   ```

   - 위의 클래스 컴포넌트에서는 마운트된 단계에서, 업데이트 되는 단계에서 상관없이 side effect 수행해야함
     ⇒ 함수를 별개의 메서드로 뽑아내도 여전히 두 장소에서 같은 함수를 불러내야 함

2. **Hook 사용 예시**

   - useEffect 이용해 side effect 함

   ```jsx
   import React, { useState, useEffect } from "react";

   function Example() {
     const [count, setCount] = useState(0);

     // useEffect를 컴포넌트 내부에 둠
     // => effect 통해 count state 변수에 접근 가능
     useEffect(() => {
       document.title = `클릭 횟수: ${count}`;
     });

     return (
       <div>
         <p>You Click {count} times</p>
         <button onClick={() => setCount(count + 1)}>Click</button>
       </div>
     );
   }
   ```

### 5. 정리(Clean-up)를 이용하는 Effects

> 💡 **정리를 이용하는 Effects 사용 ?**
>
> - 외부 데이터에 **구독(subscription)을 설정해야 하는 경우** 등

1. **Class 사용 예시**

   - class 컴포넌트에서 `componentDidMount`에 구독(subscription)을 설정 후, `componentWillUnmount`에서 이를 정리(clean-up)함

   ```jsx
   class FriendStatus extends React.Component {
     constructor(props) {
       super(props);
       this.state = { isOnline: null };
       this.handleStatusChange = this.handleStatusChange.bind(this);
     }

     componentMount() {
       ChatAPI.subscribeToFriendStatus(
         this.props.friend,
         id,
         this.handleStatusChange
       );
     }

     componentWillUnmount() {
       ChatAPI.subscribeToFriendStatus(
         this.props.friend,
         id,
         this.handleStatusChange
       );
     }

     handleStatusChange(status) {
       this.setState({
         isOnline: status.isOnline,
       });
     }

     render() {
       if (this.state.isOnline === null) {
         return "Loading...";
       }
       return this.state.isOnline ? "Online" : "Offline";
     }
   }
   ```

   - `componentMount` 와 `componentWillUnmount` 두 개의 메서드 내에서 똑같은 effect에 대한 코드가 있지만 생명주기 메서드는 분리하게 만듦

2. **Hook 사용 예시**

   - effect가 함수를 반환하면 React는 그 함수를 정리가 필요한 때(컴포넌트 마운트가 해제될 때)에 실행시킴

   ```jsx
   import React, { useState, useEffect } from "react";

   function FriendStatus(props) {
     const [isOnline, setIsOnline] = useState(null);

     useEffect(() => {
       function handleStatusChange(status) {
         setIsOnline(status.isOnline);
       }
       ChatAPI.subscribeToFriendStatus(props.friend.id, handleStatusChange);
       // effect 이후에 정리(clean-up) 방법 표시
       return function cleanup() {
         ChatAPI.unsubscribeFromFriendStatus(
           props.friend.id,
           handleStatusChange
         );
       };
     });

     if (isOnline === null) {
       return "Loading...";
     }
     return isOnline ? "Online" : "Offline";
   }
   ```

   - 모든 effect는 정리를 위한 함수 반환 가능 ⇒ 구독과 추가와 제거가 모두 하나의 effect를 구성

### 6. Hook 사용 규칙

- **최상위(at the top level)**에서만 호출
- **React 함수 컴포넌트** 내에서만 호출

### 7. Custom Hook

- 컴포넌트 트리에 새 컴포넌트를 추가하지 않고도 컴포넌트 간에 재사용 가능하게 해줌
- 이름을 use로 시작하고 안에서 다른 Hook을 호출함

1. 이 로직을 `useFriendStatus` 라는 custom Hook으로 뽑아냄

   ```jsx
   // 위의 FriendStatus 컴포넌트에서 이 로직을 다른 컴포넌트에서도 재사용 하고 싶을 때
   import React, { useState, useEffect } from "react";

   function useFriendStatus(friendID) {
     const [isOnline, setIsOnline] = useState(null);

     function handleStatusChange(status) {
       setIsOnline(status.isOnline);
     }

     useEffect(() => {
       ChatAPI.subscribeToFriendStatus(friendID, handleStatusChange);
       return () => {
         ChatAPI.subscribeFromFriendStatus(friendID, handleStatusChange);
       };
     });

     return isOnline;
   }
   ```

2. 이 Hook은 `friendID`를 인자로 받아 친구의 접속 상태 반환해줌

   ⇒ 이것을 여러 컴포넌트에서 사용 가능

   ```jsx
   function FriendStatus(props) {
     const inOnline = useFriendStatus(props.friend.id);

     if (isOnline === null) {
       return "Loading...";
     }
     return isOnline ? "Online" : "Offline";
   }
   ```

   ```jsx
   function FriendListItem(props) {
     const isOnline = useFriendStatus(props.friend.id);

     return (
       <li style={{ color: isOnline ? "green" : "black" }}>
         {props.friend.name}
       </li>
     );
   }
   ```

> **각 컴포넌트의 state는 완전히 독립적**
>
> - **Hook은 상태 관련 로직을 재사용**하는 방법 !
> - 실제 각 Hook 호출은 완전히 **독립된 state**를 가짐

### 8. 다른 내장 Hook

- **`useContext()`** : 컴포넌트를 중첩하지 않고도 React context를 구독할 수 있음
  ```jsx
  function Example() {
    const locale = useContext(LocaleContext);
    const theme = useContext(themeContext);
    // ...
  }
  ```
- `useReducer()` : 복잡한 컴포넌트들의 state를 reducer로 관리할 수 있게 해줌
  ```jsx
  function Todos() {
    const [todos, dispatch] = useReducer(todosReducer);
  }
  ```
