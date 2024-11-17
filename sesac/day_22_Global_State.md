## <mark color="#fbc956">전역 상태 관리</mark>

### 1. Redux 설치

- 들어가기 전 react 내 `scr`의 주요 파일 구조 알아보기

  > **scr 주요 파일 구조**
  >
  > - **store**
  >   - store.js
  >   - **slices**
  >     - postsSlice.js
  > - **router**
  >   - index.jsx
  > - **pages**
  >   - PostList.jsx
  >   - PostDetail.jsx
  >   - PostCreate.jsx
  > - App.jsx

- **Redux 설치**
  ```bash
  npm install @reduxjs/toolkit react-redux
  ```

### 2. 데이터를 전역으로 사용하기 위해 Slice 저장

- **`slices/postSlice.js`** 에서 posts라는 데이터를 Slice로 저장
  - **`Slice`** : Redux 상태의 한 부분을 관리하기 위한 로직을 모아놓은 단위
  - **`initialState`** : 초기값을 지정
  - **`reducers`** : state 값 변경을 위한 함수들이 정의
    - 함수는 `state` , `action` 두 개의 parameter 가짐
    - `action.payload` : 함수에 대한 입력 값 있음

```jsx
import { createSlice } from "@reduxjs/toolkit";

const initialState = [
  {
    id: 1,
    title: "첫 번째 프로젝트",
    content:
      "간단한 Todo 앱을 만들었습니다. UI 구성과 상태 관리의 중요성을 배웠습니다.",
  },
  {
    id: 2,
    title: "리액트와 함께한 성장",
    content:
      "리액트를 이용해 컴포넌트 기반 개발을 익히고 재사용성을 높이는 방법을 배웠습니다.",
  },
  {
    id: 3,
    title: "팀 프로젝트 경험",
    content:
      "팀 프로젝트에서 협업하여 일정 관리 앱을 개발했고, Git을 활용한 버전 관리와 코드 리뷰의 중요성을 배웠습니다.",
  },
];
{/* postsSlice라는 변수로 slice를 만듦 */}
const postsSlice = createSlice({
  name: "posts",         {/* 이름 지정 */}
  initialState,          {/* 초기값 지정 */}
  reducers: {},          {/* state값 변경 위한 함수 지정 */}
});

export default postsSlice.reducer;

```

### 3. Slice 저장한 변수를 store에 정의

- **`store/store.js`** 에서 store 변수에 각 Slice 데이터 변수를 저장
  - slice들을 모은 store 정의

```jsx
import { configureStore } from "@reduxjs/toolkit";
import postsReducer from "./slices/postsSlice";

{
  /* slice들을 모은 store 정의 */
}
const store = configureStore({
  reducer: {
    posts: postsReducer,
  },
});

export default store;
```

### 5. Store를 사용할 수 있도록 Provider 생성 및 지정

- **`App.jsx`** 에서 `RouterProvider` 를 감싼 **`Provider`** 생성
  - store를 사용할 수 있도록 지정

```jsx
import React from "react";
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

### 6. Component에서 Redux 사용하기

- `pages/PostList.jsx` 에서 **Redux 사용하기**

  - **`useSelector`** : state에 있는 data 가져옴
    - store에 정의된 posts 가져옴

  ```jsx
  import React from "react";
  import { useSelector } from "react-redux";
  import { Link, useNavigate } from "react-router-dom";

  export default function PostList() {
    const navigate = useNavigate();
    const posts = useSelector((state) => state.posts);

    return (
      <div>
        <h2>posts</h2>
        <ul>
          {posts.map((post) => {
            const { id, title, content } = post;
            return (
              <li key={id}>
                <Link to={`/posts/${id}`} state={{ post }}>
                  <h3>{title}</h3>
                </Link>
                <h3
                  onClick={() => {
                    // 이동을 하고 싶다
                    navigate(`/posts/${id}`);
                  }}
                >
                  {title}
                </h3>
              </li>
            );
          })}
        </ul>
      </div>
    );
  }
  ```

- `pages/PostDetail.jsx` 에서 **Redux 사용하기**
  - 4번에서와 같이 `useSelector` 로 store에 정의된 posts 가져옴

```jsx
import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useParams } from "react-router-dom";

export default function PostDetail() {
  // useParams() 이용해 index에 정의한 path의 변수 가져옴
  const { postId } = useParams();

  // useSelector 사용해 store에 정의된 posts 사용
  const posts = useSelector((state) => state.posts);
  // 현재 컴포넌트 내부에서 사용할 post라는 state 정의
  // 아래의 setPost() 통해 전역변수 posts에서 각 post 데이터 가져옴
  const [post, setPost] = useState();

  // useEffect()
  // 렌더링이 완료되었을 때 실행
  // 의존성 배열의 변수가 변경되었을 때 실행
  useEffect(() => {
    // setPost() 통해 전역변수 posts에서 각 post 중 id가
    // useParams로 가져온 현재 path인 postId와 일치하는지 확인해
    // 일치하는 post 가져옴
    setPost(posts.find((post) => post.id === parseInt(postId)));
  });

  return (
    <div>
      {/* setPost()가 렌더링을 트리거하기 전 post 사용됨
		      -> 이를 막기 위해 옵셔널 체이닝 사용 */}
      <h3>{post?.title}</h3>
      <p>{post?.content}</p>
    </div>
  );
}
```

- **위의 코드 자세히 살펴보기**

1. **옵셔널 체이닝**

   - `setPost()` 가 렌더링을 트리거 하기 전에 `return` 문에 있는 `post` 변수가 실행됨
   - 현재 post 변수에 할당된 값은 없음
     (`const [post, setPost] = useState();` 참고)

   → 할당된 값이 없는 상태로 변수 실행되어 `Cannot read properties of undefined (reading 'title')` 로 나옴

   → 이를 막기 위해 옵셔널 체이닝을 사용해 `undefined` 반환

> 💡**옵셔널 체이닝 (`?.`)**
>
> - `?.` 앞의 대상이 `undefined` 나 `null` 일 경우 평가를 멈추고 `undefined` 반환
> - `?.` 앞의 변수는 꼭 선언되어 있어야
> - 옵셔널 체이닝 사용 시 프로퍼티 없는 중첩 객체를 에러 없이 안전하게 접근 가능

```jsx
return (
  <div>
    {/* setPost()가 렌더링을 트리거하기 전 post 사용됨
		      -> 이를 막기 위해 옵셔널 체이닝 사용 */}
    <h3>{post?.title}</h3>
    <p>{post?.content}</p>
  </div>
);
```

2. **`useEffect()`**

   - `setPost()` 가 실행되며 PostDetail 페이지가 리렌더링 됨
     → 다시 `setPost()` 가 실행됨 → 이것이 계속 반복됨
   - `useEffect()` 사용해 return이 **마운트 된 후**
     → `useEffect()` 내부 **`setPost()` 를 수행**

   ```jsx
   import React, { useEffect, useState } from "react";

   useEffect(() => {
     setPost(posts.find((post) => post.id === parseInt(postId)));
   });
   ```

> 💡 **`useEffect()`**
>
> UI를 렌더링하는 것 외의 다른 작업을 수행하도록 하는 hook

- **`useEffect()` 구조**

  ```jsx
  useEffect(() => {
    // 수행할 작업
  }, [dependencies]); // 의존성 배열
  ```

  - `dependencies` 비어있을 때 (`[]`) : 처음 마운트되었을 때 실행
  - `dependencies` 존재할 때 : 처음 마운트 되었을 때와 해당 값이 변경될 때 실행

    ```jsx
    const [count, setCount] = useState(0);

    useEffect(() => {
      console.log("count 변경 : ", count);
    }, [count]); // count가 변경될 때마다 실행
    ```

3. **옵셔널 체이닝 대신 조건부 렌더링 가능**

   - 옵셔널 체이닝의 경우

   ```jsx
   return (
     <div>
       <h3>{post?.title}</h3>
       <p>{post?.content}</p>
     </div>
   );
   ```

   - 조건부 렌더링의 경우

   ```jsx
   if (!post) {
     return <p>게시물을 찾을 수 없습니다</p>;
   }
   return (
     <div>
       <h3>{post.title}</h3>
       <p>{post.content}</p>
     </div>
   );
   ```

   ⇒ 조건부 렌더링과 같이 설정할 경우 post의 프로퍼티가 없을 때의 상황을 게시물이 없음으로 처리할 수 있음

---

## <mark color="#fbc956">Reducer 사용하기 - post 데이터</mark>

- **들어가기 전 react 내 `scr`의 주요 파일 구조 알아보기**
  > **scr 주요 파일 구조**
  >
  > - store
  >   - store.js
  >   - slices
  >     - **postsSlice.js**
  > - router
  >   - index.jsx
  > - components
  >   - **Header.jsx**
  > - pages
  >   - PostList.jsx
  >   - PostDetail.jsx
  >   - **PostCreate.jsx**
  > - App.jsx

> 위의 예시에 이어서 **post를 생성하는 페이지**를 만들기

### 1. Reducers 생성하기

- `postsSlice.js` 에서 **`reducers` 에 함수(기능) 추가**하기

  1. **`addPost` 라는 함수 생성**

     → 매개변수(`state, action`) 을 받아

     → posts 리스트(`state`)에 새로운 데이터(`action.payload`) 를 추가 (`push`) 함

  2. **생성한 함수 `export`**
     - 생성한 `addPost` 함수를 `postsSlice` 의 `actions` 으로 내보냄

  ```jsx
  import { createSlice } from "@reduxjs/toolkit";

  const initialState = [
    {
      id: 1,
      title: "첫 번째 프로젝트",
      content:
        "간단한 Todo 앱을 만들었습니다. UI 구성과 상태 관리의 중요성을 배웠습니다.",
    },
    {
      id: 2,
      title: "리액트와 함께한 성장",
      content:
        "리액트를 이용해 컴포넌트 기반 개발을 익히고 재사용성을 높이는 방법을 배웠습니다.",
    },
    {
      id: 3,
      title: "팀 프로젝트 경험",
      content:
        "팀 프로젝트에서 협업하여 일정 관리 앱을 개발했고, Git을 활용한 버전 관리와 코드 리뷰의 중요성을 배웠습니다.",
    },
  ];

  const postsSlice = createSlice({
    name: "posts",
    initialState,
    reducers: {
      addPost: (state, action) => {
        // 리스트에.push(새로운 데이터)
        state.push(action.payload);
      },
    },
  });

  {
    /* 생성한 함수 내보내기 */
  }
  export const { addPost } = postsSlice.actions;
  export default postsSlice.reducer;
  ```

### 2. 게시글 생성 페이지 및 기능 추가

- `pages/PostCreat.jsx` **페이지 생성**
  1. title과 content 입력 받을 form 생성
  2. form의 내용을 전달하는 제출 button 생성

```jsx
import React, { useState, useEffect } from "react";

export default function PostCreate() {
  return (
    <>
      <h3>Post Create</h3>
      <form onSubmit={handleSubmit}>
        <label htmlFor="title">제목 : </label>
        <input type="text" name="title" id="title" />

        <label htmlFor="content">내용 : </label>
        <textarea name="content" id="content" />

        <button>생성</button>
      </form>
    </>
  );
}
```

- `pages/PostCreat.jsx` 에 **state 추가하기**
  - input 에 텍스트 입력 시 input의 value 저장하는 state 만들기
  1. **`state` 생성**
     - `formData` : form의 title과 content의 input value를 저장
     - `setFormData` : set 함수 생성
     - 초기값을 title
  2. input에 **`onChange` 이벤트 생성**
     - form의 title과 content의 input을 받을 때 작동
     - 입력이 바뀌면 `handleChange`를 호출
  3. **`handleChange` 함수 생성**
     - `setFormData` 이용해 입력 변경이 들어올 경우 title과 content에 맞는 값 변경
     - 스프레드 오퍼레이터 통해 원래 값을 가져와 + 변경 값으로 formData 갱신

```jsx
import React, { useState } from "react";

export default function PostCreate() {
  const [formData, setFormData] = useState({ title: "", content: "" });

  // input value를 formData에 저장
  function handleChange(e) {
    return setFormData({ ...formData, [e.target.name]: e.target.value });
  }

  return (
    <>
      <h3>Post Create</h3>
      <form>
        <label htmlFor="title">제목 : </label>
        <input type="text" name="title" id="title" onChange={handleChange} />

        <label htmlFor="content">내용 : </label>
        <textarea name="content" id="content" onChange={handleChange} />

        <button>생성</button>
      </form>
    </>
  );
}
```

### 3. 게시글 생성 시 전역 데이터에 추가하기

- `pages/PostCreat.jsx` 에서 form 제출 시 입력 값 **전역 데이터에 추가**
  1. form에 `onSubmit` 이벤트를 `handleSubmit` 함수로 지정
  2. `handleSubmit` 함수 생성
     - form 고유 기능의 제출 및 렌더링 막기 위해 **`preventDefault()` 지정**
     - **`useDispath()`** : 전역 변수의 함수인 `addPost` 실행
     - **`useNavigate()`** 이용해 게시글 생성 후 해당 게시글로 이동

```jsx
import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { addPost } from "../store/slices/postsSlice";
import { useNavigate } from "react-router-dom";

export default function PostCreate() {
  const [formData, setFormData] = useState({ title: "", content: "" });
  const dispatch = useDispatch();
  const navigate = useNavigate();

  // input value를 formData에 저장
  function handleChange(e) {
    return setFormData({ ...formData, [e.target.name]: e.target.value });
  }

  // form 제출했을 때
  function handleSubmit(e) {
    e.preventDefault();

    const id = Date.now();

    dispatch(addPost({ ...formData, id: id }));
    navigate(`/posts/${id}`);
    // navigate(`/posts`);
  }

  return (
    <>
      <h3>Post Create</h3>
      <form onSubmit={handleSubmit}>
        <label htmlFor="title">제목 : </label>
        <input type="text" name="title" id="title" onChange={handleChange} />

        <label htmlFor="content">내용 : </label>
        <textarea name="content" id="content" onChange={handleChange} />

        <button>생성</button>
      </form>
    </>
  );
}
```

- `Header` 에 게시글 생성 **링크 생성**

  - Link를 이용해 posts/create로 가는 링크 생성

- index.jsx

```jsx
import React from "react";
import { Link } from "react-router-dom";

export default function Header() {

  return (
    <header>
      <ul>
        <li>
          <Link to="/">Home으로</Link>
        </li>
        <li>
          <Link to="/posts">게시글로</Link>
        </li>
        <li>
          <Link to='/posts/create'>게시글 생성</a>
        </li>
      </ul>
    </header>
  );
}

```

---

## <mark color="#fbc956">Reducer 사용하기 - login 기능</mark>

- **들어가기 전 react 내 `scr`의 주요 파일 구조 알아보기**
  > **scr 주요 파일 구조**
  >
  > - store
  >   - store.js
  >   - slices
  >     - **postsSlice.js**
  >     - **loginSlice.js**
  > - router
  > - components
  >   - **Header.jsx**
  > - pages
  >   - PostList.jsx
  >   - PostDetail.jsx
  >   - **PostCreate.jsx**
  >   - **requestLogin.jsx**
  > - App.jsx

### 1. login 데이터를 Slice 저장

- **`store/slices/loginSlice.jsx` 생성**
  - 전역으로 사용할 isLogin slice를 생성
- `loginSlice` 의 **`reducer` 를 export**
  - reducers인 `switchLogin` 도 loginSlice의 actions로 export

```jsx
import { createSlice } from "@reduxjs/toolkit";

const loginSlice = createSlice({
  name: "isLogin",
  initialState: false,
  reducers: {
    switchLogin: (state) => {
      return !state;
    },
  },
});

export const { switchLogin } = loginSlice.actions;
export default loginSlice.reducer;
```

### 2. slice 저장한 데이터를 store에 저장

- **store에 `isLogin` slice 추가**

```jsx
import { configureStore } from "@reduxjs/toolkit";
import postsReducer from "./slices/postsSlice";
import loginReducer from "./slices/loginSlice";

const store = configureStore({
  reducer: {
    posts: postsReducer,
    isLogin: loginReducer,
  },
});

export default store;
```

### 3. login 버튼 생성

1. **login 버튼의 로그인 or 로그아웃 동작** 생성하기

   - `Header` 에 **`login` 버튼 생성**
     - `isLogin` 이 `true` 일 경우 ‘로그인’ 상태, `false` 일 경우 ‘로그아웃’ 상태
   - `login` 버튼에 **`onClick` 이벤트 `handleClick` 지정**
     - `useDispatch()` 로 `switchLogin` reducer 사용
     - `useSelector()` 사용해 전역 변수인 `isLogin` 가져옴
     - login 버튼이 클릭되면 → `switchLogin` 함수 실행, 인자로 `isLogin` 넣어줌
   - **삼항 연산자 사용**
     - 전역 변수인 `isLogin` 의 값에 따라 버튼이 ‘로그인’인지 ‘로그아웃’ 인지 정함

   ```jsx
   import React from "react";
   import { useSelector } from "react-redux";
   import { Link } from "react-router-dom";
   import { switchLogin } from "../store/slices/loginSlice";
   import { useDispatch } from "react-redux";

   export default function Header() {
     const dispatch = useDispatch();
     const isLogin = useSelector((state) => state.isLogin);

     function handleClick() {
       dispatch(switchLogin(isLogin));
     }

     return (
       <header>
         <ul>
           <li>
             <Link to="/">Home으로</Link>
           </li>
           <li>
             <Link to="/posts">게시글로</Link>
           </li>
           <li>
             <Link to="/posts/create">게시글 생성</Link>
           </li>
         </ul>

         <button
           style={{
             backgroundColor: isLogin ? "gray" : "blue",
           }}
           onClick={handleClick}
         >
           {isLogin ? "로그아웃" : "로그인"}
         </button>
       </header>
     );
   }
   ```

2. **`isLogin` 에 따라 게시글 생성 페이지 보여줄지 말지 정하기**

   - 게시글 생성 링크에 `onClick` 이벤트로 `handleLoginCreate` 함수 지정
   - `isLogin` 의 값에 따라 `useNavigation()` 이용해 게시글 생성 페이지(`'/posts/create'`)로 이동하거나 로그인 요청 페이지(`'/request-login'`)로 이동하도록 함

   ```jsx
   import React from "react";
   import { useSelector } from "react-redux";
   import { Link, useNavigate } from "react-router-dom";
   import { switchLogin } from "../store/slices/loginSlice";
   import { useDispatch } from "react-redux";

   export default function Header() {
     const dispatch = useDispatch();
     const navigate = useNavigate();
     const isLogin = useSelector((state) => state.isLogin);

     function handleClick() {
       dispatch(switchLogin(isLogin));
     }

     function handleLoginCreate() {
       isLogin ? navigate(`/posts/create`) : navigate("/request-login");
     }

     return (
       <header>
         <ul>
           <li>
             <Link to="/">Home으로</Link>
           </li>
           <li>
             <Link to="/posts">게시글로</Link>
           </li>
           <li>
             <a onClick={handleLoginCreate}>게시글 생성</a>
           </li>
         </ul>

         <button
           style={{
             backgroundColor: isLogin ? "gray" : "blue",
           }}
           onClick={handleClick}
         >
           {isLogin ? "로그아웃" : "로그인"}
         </button>
       </header>
     );
   }
   ```

3. `requestLogin` 페이지 생성

   - 로그인이 안된 상태인데 게시글 생성 페이지에 들어가려 할 때 보여주는 페이지

   ```jsx
   import React from "react";
   import { useNavigate } from "react-router-dom";

   // 이전 페이지 버튼을 누르면
   // 상위 페이지로 갈 수 있음
   export default function LequestLogin() {
     const navigate = useNavigate();
     function handleClick() {
       navigate("..", { relative: "path" });
     }

     return (
       <div>
         <h2>Request Login</h2>
         <p>로그인이 필요한 페이지입니다.</p>

         <button onClick={handleClick}>이전 페이지</button>
       </div>
     );
   }
   ```

4. `index` 에 `requestLogin` 컴포넌트와 path 추가하기

   ```jsx
   import { createBrowserRouter, Navigate } from "react-router-dom";
   import Home from "../pages/Home";
   import PostList from "../pages/PostList";
   import RootLayout from "../RootLayout";
   import PostDetail from "../pages/PostDetail";
   import PostCreate from "../pages/postCreate";
   import NotFound from "../pages/NotFound";
   import LequestLogin from "../pages/LequestLogin";

   const router = createBrowserRouter([
     {
       path: "/",
       element: <RootLayout />,
       children: [
         {
           index: true,
           element: <Home />,
         },
         {
           path: "/posts",
           element: <PostList />,
         },
         {
           path: "/posts/create",
           element: <PostCreate />,
         },
         {
           path: "/posts/:postId",
           element: <PostDetail />,
         },
       ],
     },
     {
       path: "/notfound",
       element: <NotFound />,
     },
     {
       path: "/request-login",
       element: <LequestLogin />,
     },
     // url에 다른 입력 값이 들어올 경우
     // <Notfound /> 페이지로 이동 후
     // url을 notfound url로 replace함
     {
       path: "*",
       element: <Navigate to="/notfound" replace />,
     },
   ]);

   export default router;
   ```

### 4. login 데이터에 따른 동작 생성

1. `isLogin` 에 따라 `false` 일 경우 home 으로 가도록 지정
   - `useEffect` 이용해 처음 페이지가 마운트 된 후 `isLogin` 이 `false` 면 홈이동
   - 의존성 배열에 `isLogin` 을 넣어 `isLogin` 에 변화가 있을 경우 다시 실행되도록 지정

```jsx
import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { addPost } from "../store/slices/postsSlice";
import { useNavigate } from "react-router-dom";

export default function PostCreate() {
  const isLogin = useSelector((state) => state.isLogin);
  const [formData, setFormData] = useState({ title: "", content: "" });
  // useDispatch : setter를 실행시키는 함수
  const dispatch = useDispatch();
  const navigate = useNavigate();

  // 처음 마운트 + isLogin 바뀌었을 때
  useEffect(() => {
    if (!isLogin) {
      navigate("/");
    }
  }, [isLogin]);

  // input value를 formData에 저장
  function handleChange(e) {
    return setFormData({ ...formData, [e.target.name]: e.target.value });
  }

  // form 제출했을 때
  function handleSubmit(e) {
    e.preventDefault();

    const id = Date.now();

    dispatch(addPost({ ...formData, id: id }));
    navigate(`/posts/${id}`);
  }

  return (
    <>
      <h3>Post Create</h3>
      <form onSubmit={handleSubmit}>
        <label htmlFor="title">제목 : </label>
        <input type="text" name="title" id="title" onChange={handleChange} />

        <label htmlFor="content">내용 : </label>
        <textarea name="content" id="content" onChange={handleChange} />

        <button>생성</button>
      </form>
    </>
  );
}
```
