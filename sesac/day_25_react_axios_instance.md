## <mark color="#fbc956">Axios instance</mark>

[Axios 인스턴스 | Axios Docs](https://axios-http.com/kr/docs/instance)

- 들어가기 전 파일 구조 살펴보기 !
  > **src 파일 구조**
  >
  > - **api**
  >   - **axios.jsx**
  >   - **postApi.jsx**
  > - pages
  >   - PostList.jsx
  >   - PostDetail.jsx
  >   - PostCreate.jsx

### 1. Axios instance 생성

1. **사용자 지정 config로 새로운 Axios 인스턴스 생성 가능**

   - `axios.create([config])`

   ```jsx
   const instance = axios.create({
     baseURL: "https://some-domain.com/api/",
     timeout: 1000,
     headers: { "X-Custom-Header": "foobar" },
   });
   ```

2. **`axios.create()` 로 인스턴스 생성**

   - api 폴더 내 axios.jsx 파일 생성
   - `axios.create()` 로 인스턴스 생성
     - create 내 baseURL 지정
   - instance export

   ```jsx
   import axios from "axios";

   const instance = axios.create({
     baseURL: "http://localhost:3000/posts",
   });

   // axios.get('http://localhost:3000/posts')
   // axios.get(`http://localhost:3000/posts/${postId}`)
   // 위의 코드를 아래처럼 사용 가능
   // instance.get('')
   // instance.get(`/${postId}`)

   export default instance;
   ```

3. **생성한 인스턴스 통해 동작 지정**

   - axios의 인스턴스를 api로 import
   - **postApi 생성하기 → export default**
     - posts GET, post GET, posts POST 각각 함수 지정하기

   ```jsx
   import api from "./axios";

   const postApi = {
     // 1. 리스트 GET
     getPosts: async () => {
       const response = await api.get("");
       return response.data;
     },
     // 2. 개별 GET
     getPostById: async (postId) => {
       const response = await api.get(`/${postId}`);
       return response.data;
     },
     // 3. POST
     createPost: async (formData) => {
       const response = await api.post("", formData);
       return response.data;
     },
   };

   export default postApi;
   ```

### 2. 묶은 api 사용하기

1. `postList.jsx` 에 **`axios.get()` 으로 직접 지정 대신**

   import postApi → **`postApi.getPosts()` 사용**

   ```jsx
   import React, { useEffect } from "react";
   import { Link, useNavigate } from "react-router-dom";
   import { useState } from "react";
   // import axios from "axios";
   import postApi from "../api/postsApi";

   export default function PostList() {
     const navigate = useNavigate();
     const [posts, setPosts] = useState([]);
     const [loading, setLoading] = useState(true);
     const [error, setError] = useState(null);

     useEffect(() => {
       async function fetchPosts() {
         try {
           // const url = "http://localhost:3000/posts/";
           // const response = await axios.get(url);
           // const data = response.data;
           const data = await postApi.getPosts();

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

2. `postDetail.jsx` 에 **`axios.get()` 으로 직접 지정 대신**

   import postApi → **`postApi.getPostById()` 사용**

   ```jsx
   import React, { useEffect, useState } from "react";
   import { useParams } from "react-router-dom";
   // import axios from "axios";
   import { useNavigate } from "react-router-dom";
   import postApi from "../api/postsApi";

   export default function PostDetail() {
     const navigate = useNavigate();

     const { postId } = useParams();
     const [post, setPost] = useState();
     const [loading, setLoading] = useState(true);

     useEffect(() => {
       async function fetchPost() {
         try {
           // const url = `http://localhost:3000/posts/${postId}`;
           // const response = await axios.get(url);
           // const data = response.data;
           const data = await postApi.getPostById(postId);

           setPost(data);
         } catch (err) {
           // navigate("/posts");
           // TODO : 나중에 고칠 것
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

3. `postCreate.jsx` 에 **`axios.post()` 으로 직접 지정 대신**

   import postApi → **`postApi.createPost()`** 사용

   ```jsx
   import React, { useState, useEffect } from "react";
   import { useSelector } from "react-redux";
   import { useNavigate } from "react-router-dom";
   // import axios from "axios";
   import postApi from "../api/postsApi";

   export default function PostCreate() {
     const isLogin = useSelector((state) => state.isLogin);
     const [formData, setFormData] = useState({ title: "", content: "" });
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
       async function createPost() {
         // const url = "http://localhost:3000/posts";
         // const response = await axios.post(url, formData);
         // const data = response.data;
         const data = await postApi.createPost(formData);

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

---

## <mark color="#fbc956">환경 변수</mark>

[Vite의 환경 변수와 모드](https://ko.vite.dev/guide/env-and-mode.html)

> **React-App 파일 구조**
>
> - **.env**
> - src
> - package.json

### 1. `.env` 파일 생성

- root directory에 `.env` 파일 생성
- **데이터 저장**
  - **`VITE_{변수명}`**
  - 상수는 대문자 사용
- **데이터 사용**
  - **`import.meta.env.VITE_{변수명}`**
  - react 앱 내부에서 사용

```jsx
VITE_SECRET_VALUE = "나의 비밀번호";
VITE_TMDB_API_KEY = "나의 비밀번호";
VITE_API_URL = "http://localhost:3000";
```

- **.env.dev : 개발**
- **.env.production : 배포**
