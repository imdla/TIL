## <mark color="#fbc956">키워드</mark>

### 지역 state

- **useState**
  - const [ state, setState ] = useState();
  - setState() : state 변경 함수
  - 지역 상태의 state 관리
- **useEffect**
  - useEffect( 함수, [의존성 배열] )
  - **의존성 배열 옵션**
    - 없을 경우 : 렌더링될 때 마다 실행
    - [] : 처음 마운트 된 후 실행
    - [의존성 배열] : 처음 마운트, 내부 변수 변경될 때 마다 실행

### 전역 state

- **useSelector**
  - const 변수 = useSelector((state) ⇒ state.변수)
  - 전역 상태의 state 변수 가져옴
- **useDispatch**
  - const dispatch = useDispath()
  - dispatch( 전역 state의 set함수() )
  - 전역 상태의 state 함수 실행
  - redux 활용

### path

- **useParams**
  - const 변수 = useParams()
  - index에 정의된 path의 `:변수` 가져옴
- **useNavigation**
  - const navigation = useNavigation()
  - navigate( ”이동할 곳의 path” )
  - path 통한 이동

### Link

- **useLocation**
  - const location = useLocation()
  - const { 변수 } = location.state
  - 이동 : `<Link to={’path’} state={{ data }}></Link>`
  - 이동한 곳에서 전해준 Link의 data를 location의 state를 통해 받음

---

## <mark color="#fbc956">React 정리</mark>

- **Virtual DOM**
- **Component**
  - `export` → `import { component name, component name }`
  - `export default` → `import component name`
- **JSX**
  1. 하나의 루트 엘리먼트로 반환
  2. 모든 태그는 닫아주기
  3. 대부분 캐멀 케이스로 작성
  - 중괄호 사용하기 : JavaScript 코드 사용
    - JavaScript 변수, JavaScript 표현식
    - 이중 중괄호 : JavaScript 객체
  - 따옴표 사용하기 : 문자열 전달
- **Props**
  - **특징**
    - 읽기 전용 : 부모 컴포넌트만 props 변경 가능
    - 단방향 데이터 흐름 : 부모 → 자식 방향으로 데이터 전달
  - **Props 전달하기**
    - 부모 컴포넌트 → 자식 컴포넌트 `<Child name: "john" age={20}></Child>` 로 전달
    - 자식 컴포넌트에서
      ```jsx
      export default function Child(props) {
        // 구조 분해 할당
        const { name, age } = props;
        return `${name}의 나이는 ${age}입니다.`;
      }
      ```
  - **children**
    - 자식을 JSX 전달하기
    - 자식 컴포넌트
      ```jsx
      function Child({ children }) {
        return <div>{children}</div>;
      }
      ```
    - 부모 컴포넌트
      ```jsx
      function Parent() {
        return (
          <Child>
            <Baby name="john" age={1}></Baby>
          </Child>
        );
      }
      ```
- **조건부 렌더링**
  - `if` 조건문
  - 삼항 조건 연산자 ( `? :` )
  - 논리 연산자 ( `&&` )
- **리스트 렌더링**
  - `key` 를 사용해 리스트 항목 순서대로 유지
    - `key` 규칙
      1. key는 형제 간 고유
      2. key가 임의로 변경되는 구조 사용하면 안됨
  - `map()` : 배열을 컴포넌트로 렌더링
  - `filter()` : 배열을 특정 컴포넌트만 렌더링
- **이벤트 핸들러**
  - 함수 전달 (prop으로 넘겨줌)
    - `<button onClick={handleClick}>`
    - 인라인 형태 : `<button onClick={() => console.log('전달')}>`
  - 특징
    1. 컴포넌트 내부에서 정의
    2. `handle` 로 시작해 뒤에 이벤트 이름 붙여 함수 구성
  - 이벤트 핸들러 내에서 prop 읽기
    - `<button onClick={() => console.log(message)}>`
  - 이벤트 핸들러를 prop으로 전달하기
  - 이벤트 핸들러 prop 명명하기
- **상태 관리**

  - `useState` 훅으로 state 변수 추가
    - **state 변수** : `count`
    - **setter 함수** : `setCount`
    - **useState(`초기값`)**

  ```jsx
  import { useState } from "react";

  // 배열 구조 분해
  const [count, setCount] = useState(0);
  ```

  - 리액트에게 어떤 데이터 기억하고 지켜보도록 하고싶을 때 사용
  - state는 컴포넌트 인스턴스에 지역적

- **라우트 관리**

  - 라우팅(Routing) : 사용자가 웹 앱플리케이션 내 다른 페이지로 이동할 수 있도록 경로를 지정하는 과정

  ```bash
  npm create vite@latest my-blog -- --template react
  cd my-blog
  ```

  ```bash
  npm install react-router-dom
  ```

  - **파일 알아보기**

    - App.jsx

      ```jsx
      import React from "react";
      import { RouterProvider } from "react-router-dom";
      import router from "./router";

      export default function App() {
        return (
          <>
            <RouterProvider router={router}></RouterProvider>
          </>
        );
      }
      ```

    - RootLayout.jsx

      ```jsx
      import React from "react";
      import { Outlet } from "react-router-dom";

      export default function RootLayout() {
        return (
          <>
            <header>헤더</header>
            <Outlet></Outlet>
            <footer>푸터</footer>
          </>
        );
      }
      ```

    - router/index.jsx

      - path에 `index: true` 로 부모와 같은 path 가질 수 있음

      ```jsx
      import { createBrowserRouter } from "react-router-dom";
      import RootLayout from "../RootLayout";

      const router = createBrowserRouter([
        {
          path: "/",
          element: <RootLayout />,
          children: [],
        },
      ]);

      export default router;
      ```

  - **동적 라우팅**

    - path에 `posts/{:변수명}` 와 같이 postId는 게시글에 따라 변화
    - 해당 컴포넌트 내부에서 해당 path parameter에 `useParams`로 접근 가능

      ```jsx
      import { useParams } from "react-router-dom";

      const { postId } = useParams();
      ```

  - **페이지 이동**

    - **Link**

      - `<Link to={path}>`

      ```jsx
      import { Link } from 'react-router-dom'

      <Link to={`/posts/${post.id}`}>
      ```

    - **useNavigate()**

      - `navigate(path)`

      ```jsx
      import { useNavigate } from "react-router-dom";

      function Func() {
        const navigate = useNavigate();
        <button onClick={() => navigate(`/posts/${post.id}`)}></button>;
      }
      ```

  - **개별 데이터 주고 받기**

    - 이동한 곳에서 **개별 데이터 넘기기 : state 이용**
      - **Link**
        ```jsx
        <Link to={`/posts/${post.id}` state={{ post }}}>
        ```
      - **useNavigate**
        ```jsx
        <button onClick={() => navigate(`/posts/${post.id}`, state: {post} )}></button>
        ```
    - 이동한 곳에서 **개별 데이터 받기 : `useLocation()`**

      ```jsx
      import { useLocation } from "react-router-dom";

      export function Func() {
        const location = useLocation();
        const { post } = location.state;
      }
      ```

    - **location 더 알아보기**
      - pathname : 현재 경로
      - search : 쿼리 파라미터
        - `useSearchParams` 통해 확인 가능
      - hash : (#) 해시

- **전역 상태 관리**

  - Redux 설치
    ```bash
    npm install @reduxjs/toolkit react-redux
    ```
  - Redux 스토어 설정

    - `src/slices/postsSlice.js`
    - Slice : Redux 상태의 한 부분 관리 위해 로직 모아놓은 단위
    - `initialState` : 초기값 지정
    - `reducer` : state 값 변경 위한 함수들 정의
      - paramete : `state` , `action`
      - `action.payload` 에는 함수 대한 입력값 있음

    ```jsx
    import { createSlice } from "@reduxjs/toolkit";

    const initialState = [
      {
        id: 1,
        title: "제목",
        content: "내용",
      },
    ];

    const postsSlice = createSlice({
      name: "posts",
      initialState,
      reducers: {},
    });

    export default postsSlice.reducer;
    ```

  - slice 모은 store 정의

    - `src/store.js`

    ```jsx
    import { configureStore } from "@reduxjs/toolkit";
    import postsReducer from "./slices/postsSlice";

    const store = configureStore({
      reducer: {
        posts: postsReducer,
      },
    });

    export default store;
    ```

  - App.jsx

    - store 사용

    ```jsx
    import { RouterProvider } from "react-router-dom";
    import router from "./router";

    import { Provider } from "react-redux";
    import store from "./store/store";

    export default function App() {
      return (
        <>
          <Provider store={store}>
            <RouterProvider router={router}></RouterProvider>
          </Provider>
        </>
      );
    }
    ```

  - **useEffect**
    - UI 렌더링 하는 것 외의 다른 작업 수행하도록 하는 hook
    ```jsx
    useEffect(() => {
      // 수행할 작업
    }, [dependencies]); // 의존성 배열
    ```
    - 의존성 배열 비었을 경우(`[]`) : 처음 마운트되었을 때 실행
    - 의존성 배열 있을 경우 : 처음 마운트 및 해당 값 바뀌면 실행

- **api**

  ```bash
  # json server
  npm install
  node server.js
  http://localhost:3000/posts

  # axios
  npm i axios
  ```

  - axios 인스턴스 생성

    - `src/api/axios.js`

    ```jsx
    import axios from "axios";

    const instance = axios.create({
      baseURL: "http://localhost:3000/posts",
    });

    export default instance;
    ```

  - postApi 생성

    - `src/api/postsApi.js`

    ```jsx
    import api from "./axios";

    const postApi = {
      getPosts: async () => {
        const resposne = await api.get("");
        return resposne.data;
      },
      getPostById: async (postId) => {
        const resposne = await api.get(`/${postId}`);
        return resposne.data;
      },
      createPost: async (data) => {
        const resposne = await api.post("", data);
        return resposne.data;
      },
    };

    export default postApi;
    ```

  - api 사용

    ```jsx
    import React, { useEffect, useState } from "react";
    import { Link } from "react-router-dom";
    import postApi from "../api/postsApi";

    export default function PostList() {
      const [posts, setPosts] = useState([]);

      useEffect(() => {
        async function fetchPost() {
          const data = await postApi.getPosts();
          setPosts(data);
        }
        fetchPost();
      }, []);
    }
    ```

- **환경변수**
  - 민감한 정보나 환경별 설정값을 코드와 분리해 관리하는 변수
  - 목적
    - 보안 : 민감한 정보 노출 방지, 비밀키 소스코드에서 분리
    - 환경설정 : api 엔드포인트, 데이터베이스 연결 정보 등 개발 / 테스트 / 운영 환경별 다른 설정값으로 관리
  - `.env` 생성
    - root directory에 생성
    - 변수 저장 : 파일 내 `VITE_{변수명} = data` 로 데이터 저장
    - 변수 사용 : react앱 내부 `import.meta.env.VITE_{변수명}` 으로 사용
