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
