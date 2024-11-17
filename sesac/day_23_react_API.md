## <mark color="#fbc956">api 활용</mark>

> 이전에 **store로 저장했던 데이터들**을(전역 상태의 데이터들) → **axios를 통해 서버**에서 가져오기 !

- 들어가기 전 react 내 `scr`의 주요 파일 구조 알아보기
  > **scr 주요 파일 구조**
  >
  > - store
  >   - store.js
  >   - slices
  >     - postsSlice.js
  > - router
  >   - index.jsx
  > - pages
  >   - **PostList.jsx**
  >   - **PostDetail.jsx**
  >   - **PostCreate.jsx**
  > - App.jsx

### 0. json server 및 axios 활용

1. json server 다운로드
2. 설치 `npm install`
3. 서버 실행 `node server.js`
   - endpoints : `http://localhost:3000/posts`
4. axios 설치 `npm i axios`

### 1. posts 데이터 가져오기(GET)

- **`axios.get(url)`**
- `PostList` 에서 posts **데이터를 서버에서 가져오기**
  1. **`axios.get(url)`** 을 통해 posts 가져오기
  2. **`useState`로 posts 사용**
     - 처음 post는 빈 상태 → api 데이터 가져오면 post 있음 → post는 변경됨
     - 이는 posts가 변경되는 상황이므로 state를 사용해야 함
  3. **`useEffect` 사용**
     - 의존성 배열에는 변경을 위한 데이터를 넣음
     - **`useEffect` 사용하지 않을 경우 ?**
       → posts는 빈 배열로 페이지 마운트 됨
       → `fetchPosts()` 함수 실행되어 posts 데이터를 `setPosts` 에 전달
       → state 변수인 posts가 변경이 일어나 페이지 리렌더링 일어남
       → 렌더링 후 다시 `fetchPosts()` 함수 실행 → 계속 반복됨

```jsx
import React, { useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
// axios 불러옴
import axios from "axios";

export default function PostList() {
  const navigate = useNavigate();
  // posts를 state로 사용
  const [posts, setPosts] = useState([]);

  // useEffect()를 사용해 처음 마운트된 후
  // fetchPost() 실행해 posts 가져옴
  useEffect(() => {
    async function fetchPost() {
      const url = "http://localhost:3000/posts";
      const response = await axios.get(url);

      const data = response.data;
      setPosts(data);
    }

    fetchPost();
  }, []);

  return (
    <div>
      <h2>posts</h2>

      <ul>
        {posts.map((post) => {
          const { id, title, content } = post;
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

### 2. post 데이터 가져오기(GET)

- **`axios.get(url)`**
- `postDetail`에서 **해당하는 post 페이지 가져오기**
  1. **`useEffect`** 내에 `fatchPost()` 실행
  2. index path의 postId를 **`useParams`** 이용해 가져옴
     - axios url의 id로 지정
  3. axios 이용해 **postId에 해당하는 post data 가져옴**

     → **`setPost`를 통해 post data를 저장**

```jsx
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

export default function PostDetail() {
  const { postId } = useParams();
  const [post, setPost] = useState("");

  useEffect(() => {
    async function fatchPost() {
      let id = postId;
      const url = `http://localhost:3000/posts/${id}`;
      const response = await axios.get(url);

      const data = response.data;
      setPost(data);
    }

    fatchPost();
  }, []);

  return (
    <div>
      detail
      <h3>{post?.title}</h3>
      <p>{post?.content}</p>
    </div>
  );
}
```

- **이전과 비교 (store 사용할 때)**
  1. 이전에는 store에 있는 전역 변수 posts를 state로 가져와
  2. posts의 post.id와 `useParams`로 가져온 postId를 비교해
  3. 일치하는 post를 `setPost`에 저장

```jsx
import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useParams } from "react-router-dom";

export default function PostDetail() {
  const { postId } = useParams();
  const [post, setPost] = useState("");
  const posts = useSelector((state) => state.posts);

  useEffect(() => {
    setPost(posts.find((post) => post.id === parseInt(postId)));
  });

  return (
    <div>
      detail
      <h3>{post?.title}</h3>
      <p>{post?.content}</p>
    </div>
  );
}
```

### 3. post 데이터 보내기 (생성, POST)

- `PostCreate` 에서 **axios로 데이터 보내기**
  - `axios.post(url, data)`
  1. 데이터를 서버에 생성할 때 url, 데이터, method가 필요
  2. 데이터는 formData를 사용
  3. 생성 후 생성한 페이지로 이동해야 함
  4. 생성한 response의 data의 id를 가져와 `navigate` 를 이용해 이동

```jsx
import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { addPost } from "../store/slices/postsSlice";
import { useNavigate } from "react-router-dom";
import axios from "axios";

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

    // axios로 대체
    // dispatch(addPost({ ...formData, id: id }));
    async function createPost() {
      const url = "http://localhost:3000/posts";
      const response = await axios.post(url, formData);
      const data = response.data;
      const id = data.id;
      navigate(`/posts/${id}`);
    }

    createPost();
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
