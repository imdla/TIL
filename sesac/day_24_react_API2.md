## <mark color="#fbc956">api 활용</mark>

### 1. 로딩중 표시하기

1. `PostList` 에 **`loading` state** 지정
2. **`useEffect` 실행 (처음 마운트 될때)**
   1. `axios` 로 data가 불러와지면
   2. `setLoading` 을 통해 state 변경
3. 조건문을 통해 `loading`의 state가 `true` 인 상태일 경우 loading중 표시

```jsx
import React, { useEffect } from "react";
// import { useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import axios from "axios";

export default function PostList() {
  const navigate = useNavigate();
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // const posts = useSelector((state) => state.posts);
    async function fetchPosts() {
      const url = "http://localhost:3000/posts";
      const response = await axios.get(url);
      // const response = await axios({ url: url });

      const data = response.data;
      setLoading(false);
      setPosts(data);
    }

    fetchPosts();
  }, []);

  if (loading) {
    return <div>로딩 중</div>;
  }

  // posts의 useState() <- 빈 값으로 했을 때
  // posts 값이 없을 경우 상황 대응
  // if (!posts) {
  //   return <div>데이터 없음</div>
  // }

  return (
    <div>
      <h2>posts</h2>

      <ul>
        {posts.map((post) => {
          const { id, title } = post;
          return (
            <li key={id}>
              <Link to={`/posts/${id}`}>
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

      <button>
        <Link to="/posts/create">게시글 생성</Link>
      </button>
    </div>
  );
}
```

### 2. 에러 표시하기

1. `PostList` 에 `error` state 지정
2. `useEffect` 실행 (처음 마운트 될때)
   1. **`try ~ catch ~ finally`** 구문 이용
   2. `try` : `axios` 로 data가 불러와지면 `setPosts(data)` 로 data 저장
   3. **`catch`** : error 발생 시 `setError(err.message)` 로 `error` state 변경
   4. **`finally`** : 항상 try문 실행 후 `loading` 의 state는 `false` 가 되도록 state 변경
3. 조건문을 통해 `error`의 state가 `true` 인 상태일 경우 error 표시

```jsx
import React, { useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import axios from "axios";

export default function PostList() {
  const navigate = useNavigate();
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    async function fetchPosts() {
      try {
        const url = "http://localhost:3";
        const response = await axios.get(url);
        const data = response.data;

        setPosts(data);
      } catch (err) {
        setError(err.message);
        console.error(err);
        console.log("무엇인가 error");
      } finally {
        setLoading(false);
      }
    }
    fetchPosts();
  }, []);

  if (error) {
    return <div>{error}</div>;
  }

  if (loading) {
    return <div>로딩 중</div>;
  }

  return (
    <div>
      <h2>posts</h2>

      <ul>
        {posts.map((post) => {
          const { id, title } = post;
          return (
            <li key={id}>
              <Link to={`/posts/${id}`}>
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

      <button>
        <Link to="/posts/create">게시글 생성</Link>
      </button>
    </div>
  );
}
```

### 3. Detail 페이지에 로딩과 에러 만들기

- `PostDetail` 에서 `axios` 로 데이터 가져왔을 때 로딩과 에러 대응
  1. `PostDetail` 에 `loading` state 지정
  2. `useEffect` 실행 (처음 마운트 될때)
     1. **`try ~ catch ~ finally`** 구문 이용
     2. `try` : `axios` 로 data가 불러와지면 `setPosts(data)` 로 data 저장
     3. **`catch`** : error 발생 시 `setUseNavigate` 이용해 `/notfound` 페이지로 이동
     4. **`finally`** : 항상 try문 실행 후 `loading` 의 state는 `false` 가 되도록 state 변경
  3. 조건문을 통해 `loading`의 state가 `true` 인 상태일 경우 loading중 표시

```jsx
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function PostDetail() {
  const navigate = useNavigate();

  const { postId } = useParams();
  const [post, setPost] = useState();
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function fetchPost() {
      try {
        const url = `http://localhost:3000/posts/${postId}`;
        const response = await axios.get(url);

        const data = response.data;
        setPost(data);
      } catch (err) {
        navigate("/notfound", { replace: true });
      } finally {
        setLoading(false);
      }
    }

    fetchPost();
  }, []);

  if (loading) {
    return <div>로딩 중</div>;
  }

  return (
    <div>
      detail
      <h3>{post?.title}</h3>
      <p>{post?.content}</p>
    </div>
  );
}
```

- `index` 파일에 `notfound` 컴포넌트 경로 및 path 추가
  - `‘/’` 의 경로와 같은 라인(형제)에 위치함

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
    // errorElement: <NotFound />,
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
]);

export default router;
```
