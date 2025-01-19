## <mark color="#fbc956">기본 세팅</mark>

### 1. 더미데이터

- `application.properties`

  - 서버 시작하면 table들이 다시 생성되도록 설정

  ```java
  spring.jpa.hibernate.ddl-auto=create
  ```

- `/global/DataInitializer`

  - `CommandLineRunner` 구현
    - Spring Boot 애플리케이션이 시작될 때 자동으로 실행되는 인터페이스
    - 스프링 컨테이너가 완전히 생성된 후 실행되어 빈을 사용할 수 있음
    - `JpaRepository`의 `saveAll()` 을 활용해 여러 개의 entity를 한 번에 저장할 수 있음
  - `DataInitializer`

    ```java

    @Component
    @RequiredArgsConstructor
    public class DataInitializer implements CommandLineRunner {

        private final PostRepository postRepository;
        private final AuthService authService;
        private final PostTagRepository postTagRepository;
        private final CommentRepository commentRepository;
        private final TagRepository tagRepository;

        @Override
        public void run(String... args) throws Exception {
            initUser();
            initPost();
        }

        public void initUser() {

            SignupRequestDto requestDto = SignupRequestDto.builder()
                    .username("jun")
                    .password("haha1332!")
                    .email("jun@example.com")
                    .build();

            authService.signup(requestDto);
        }

        @Transactional
        public void initPost(){
            if (postRepository.count() == 0){

                for (int i=1; i<=3; i++) {
                    Post post = Post.builder()
                            .title("title" + i)
                            .content("content" + i)
                            .build();
                    postRepository.save(post);

                    for (int j=1; j<=2; j++){
                        Tag tag = Tag.builder().name("tag" + i + j).build();
                        tagRepository.save(tag);

                        PostTag postTag = new PostTag();
                        postTag.addPost(post);
                        postTag.addTag(tag);

                        postTagRepository.save(postTag);

                        Comment comment = Comment.builder()
                                .content("comment" + i + j)
                                .post(post).build();
                        commentRepository.save(comment);
                    }
                }
            }
        }
    }
    ```

### 2. react project

- 프로젝트 루트에서 `npm i` 통해 모듈 설치
- `npm run dev` 통해 서버 실행
- `.env` 에 스프링 서버 URL이 들어있음

---

## <mark color="#fbc956">CORS</mark>

### 1. CORS

- Cross Origin Resource Sharing
- 웹 브라우저에서 보안 상의 이유로 다른 출처(Origin)의 리소스 요청을 제한하는 정책
- 악의적인 스크립트가 다른 웹 사이트의 중요한 데이터에 접근하는 것을 방지
- 서버에서 허용할 출처(Origin)을 설정해 안전한 리소스 공유 가능하게 함

### 2. Origin

- 웹 페이지의 프로토콜, 도메인, 포트를 모두 포함하는 주소를 의미
  - 같은 도메인이라도 다른 포트는 다른 출처로 가준
  - localhost와 127.0.0.1도 서로 다른 출처로 간주
- 프로토콜, 도메인 포트 중 하나만 달라도 CORS 정책으로 인해 에러 발생 가능

### 해결 방법 1️⃣ `@CrossOrigin`

- `@CrossOrigin(origins = "Client URL")`
- 컨트롤러단에서 클래스, 또는 메서드에 어노테이션을 작성
- client로부터 오는 요청 받을 수 있음

### 해결 방법 2️⃣ 전역 CROS 설정 (WebConfig)

- `WebConfig` 에서 관련 설정 가능

- `WebConfig`
  - `allowedOrigins` 의 URL 끝에 `/` 를 붙이지 않음
  ```java
  @Override
  public void addCorsMappings(CorsRegistry registry) {
  	registry.addMapping("/**")
  					.allowedOrigins("http://localhost:5173")
  					.allowedMethods("GET", "POST", "PUT", "DELETE")
  					.allowedHeaders("*")
  					.allowCredentials(true)
  					.maxAge(3600);
  }
  ```

### 해결 방법 3️⃣ 전역 CROS 설정 (SecurityConfig)

- `SecurityConfig` 에서 관련 설정 가능

  - `SecurityConfig` 에서 처리할 경우 `WebConfig` 에서 처리하지 않아도 됨

- `SecurityConfig`

  - CORS 설정은 최상단 위치
  - 모든 요청에 대한 기본 정책, 인증/인가 이전 처리
  - `SecurityConfig`

    ```java
    @Bean
    // HTTP 요청에 대한 보안 처리 흐름 정의
    // HttpSecurity : HTTP 요청을 보안하는 다양한 옵션 제공
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http
    		// CORS 설정 연결
    		.cors(cors -> cors.configurationSource(corsConfigurationSource()))
    		// csrf 방지 기능 비활성화
    		.csrf(csrf -> csrf.disable())
    		...
    	);

    	return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
    	CorsConfiguration configuration = new CorsConfiguration();
    	configuration.addAllowedOrigin("http://localhost:5173");
    	configuration.addAllowedMethod("*");
    	configuration.addAllowedHeader("*");
    	configuration.setAllowCredentials(true);

    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	source.registerCorsConfiguration("/**", configuration);
    	return source;
    }
    ```

    - `configuration.setAllowCredentials(true)`
      - 쿠키 전달 시 사용
    - `UrlBasedCorsConfigurationSource`
      - CORS 설정을 어떤 URL 패턴에 적용할지 지정

### 3. Preflighr Request

- 실제 요청 전에 브라우저가 자동으로 보내는 `OPTIONS` 요청
- 실제 요청이 서버에게 안전한지 확인하는 과정
- 브라우저가 자동으로 처리, 개발자가 직접 구현할 필요 없음

---

## <mark color="#fbc956">Read - 전체</mark>

> 🤔 **Post의 List를 읽기 위해 ?**
>
> - post List의 data를 가져오는 API 요청
>   - 실패했을 때 에러 관리

- `PostList.jsx`

  ```jsx
  import React, { useEffect, useState } from "react";
  import { Link } from "react-router-dom";

  import postApi from "../api/postsApi";

  export default function PostList() {
    const [posts, setPosts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => {
      async function fetchPosts() {
        // 게시글 조회 로직
        try {
          // 정상 로직
          const response = await postApi.getPosts();
          const data = response.data;

          setPosts(data.data);
        } catch (err) {
          // 에러 발생했을 경우
          setError(err.message);
          console.error(err.responase);
        } finally {
          // 둘 다의 경우 로딩 끝남
          setLoading(false);
        }
      }

      fetchPosts();
    }, []);

    if (loading) {
      return <div>로딩중</div>;
    }

    if (error) {
      return <div>{error}</div>;
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
                  <h3>
                    {id} : {title}
                  </h3>
                </Link>
              </li>
            );
          })}
        </ul>
      </div>
    );
  }
  ```

---

## <mark color="#fbc956">Read - 단일</mark>

> 🤔 **Post를 읽기 위해 ?**
>
> - 해당하는 postId를 useParams를 이용해 받아와
> - postId의 Post data를 가져오는 API 요청
>   - 실패했을 때 에러 관리

- `PostDetail.jsx`

  ```jsx
  import React, { useEffect, useState } from "react";
  import { useNavigate, useParams } from "react-router-dom";
  import postApi from "../api/postsApi";

  export default function PostDetail() {
    const [post, setPost] = useState({});
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    const { postId } = useParams();

    useEffect(() => {
      async function fetchPost() {
        try {
          const response = await postApi.getPostById(postId);
          setPost(response.data.data);
        } catch (err) {
          setError(err.message);
          console.error(err.responase);
        } finally {
          setLoading(false);
        }
      }
      fetchPost();
    }, []);

    if (loading) return <div>로딩중...</div>;

    if (error.status == 404) {
      return <h3>존재하지 않는 게시글입니다.</h3>;
    }
    return (
      <div>
        <h3>{post?.title}</h3>
        <p>{post?.content}</p>
        <hr />
        <div>
          tags :
          {post?.tags?.map((tag) => {
            return <span key={tag}># {tag} </span>;
          })}
        </div>
        <hr />
        {post?.comments?.length ? (
          <ol>
            {post?.comments?.map((comment) => {
              return <li key={`comment-${comment.id}`}>{comment.content}</li>;
            })}
          </ol>
        ) : (
          <div>댓글이 없습니다.</div>
        )}
      </div>
    );
  }
  ```

---

## <mark color="#fbc956">회원가입</mark>

> **🤔 회원가입 할 때 필요한 것은 무엇일까 ?**
>
> - 아이디와 비밀번호를 받아 회원가입하는 API에 요청

- `Signup.jsx`

  ```jsx
  import React, { useState } from "react";
  import authApi from "../api/authApi";
  import { useNavigate } from "react-router-dom";

  export default function SignUp() {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
      username: "",
      email: "",
      password: "",
    });
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState("");

    const handleFormInput = (e) => {
      const { name, value } = e.target;
      setFormData((prev) => ({
        ...prev,
        [name]: value,
      }));
    };

    const handleSubmit = async (e) => {
      e.preventDefault();
      setError("");
      setIsLoading(true);

      try {
        await authApi.signup(formData);
        alert("회원가입 성공");
        navigate("/");
      } catch (err) {
        setError(err.message);
        console.error(err.response);
      } finally {
        setIsLoading(false);
      }
    };

    return (
      <div>
        <h2>회원가입</h2>
        <form onSubmit={handleSubmit}>
          <label htmlFor="username">사용자 이름 : </label>
          <input
            id="username"
            name="username"
            required
            placeholder="사용자 이름"
            value={formData.username}
            onChange={handleFormInput}
          />

          <div>
            <label htmlFor="email">이메일</label>
            <input
              id="email"
              name="email"
              type="email"
              required
              placeholder="이메일"
              value={formData.email}
              onChange={handleFormInput}
            />
          </div>

          <div>
            <label htmlFor="password">비밀번호</label>
            <input
              id="password"
              name="password"
              type="password"
              required
              placeholder="비밀번호"
              value={formData.password}
              onChange={handleFormInput}
            />
          </div>

          {error && <div>{error}</div>}

          <button type="submit">{isLoading ? "처리중..." : "회원가입"}</button>
        </form>
      </div>
    );
  }
  ```

---

## <mark color="#fbc956">로그인</mark>

> **🤔 로그인 할 때 필요한 것은 무엇일까 ?**
>
> - 아이디와 비밀번호를 받아 로그인하는 API에 요청
> - 서버에서 JWT 응답
> - JWT를 저장
>   - 전역 상태
>   - 로컬 스토리지

- **로그인 과정**

  - username, password 입력 → 서버에서 JWT 응답 → JWT를 클라이언트에 저장
  - JWT 저장 위치는 LocalStorage 또는 전역 상태에 저장
    - 모든 컴포넌트가 같은 상태 가지기 위해 전역 상태 통해 관리하는 것이 좋음
    - 새로고침 시 사라지기 때문에 LocalStorage에도 함께 저장

- **토큰은 Store에 저장해 사용**
  - 기본값 : LocalStorage에서 저장된 token 꺼내옴
  - 로그인 시 : 응답으로 온 token을 Store에 저장, LocalStorage에도 함께 저장
  - 로그아웃 시 : Store의 token을 삭제, LocalStorage의 token도 삭제

### LocalStorage

- 웹 브라우저 제공하는 데이터 저장소
- 브라우저에 키-값의 쌍으로 데이터 저장
- 영구적으로 데이터가 보관됨 (브라우저를 닫아도 유지)
- SessionStorage : 브라우저 탭을 닫으면 데이터 삭제

- `Login.jsx`

  - response에서 token 가져온 후 `dispatch` 통해 전역 상태 저장

  ```jsx
  import React, { useState } from "react";
  import { useNavigate } from "react-router-dom";
  import authApi from "../api/authApi";
  import { useDispatch } from "react-redux";
  import { login } from "../store/slices/authSlice";
  import useFormInput from "../hook/useFormInput";

  export default function Login() {
    const [formData, setFormData] = useState({
      username: "",
      password: "",
    });

    const [error, setError] = useState("");
    const [isLoading, setIsLoading] = useState(false);
    const navigate = useNavigate();
    const dispatch = useDispatch();

    // 커스텀 훅 만들어보기
    const handleFormInput = (e) => {
      const { name, value } = e.target;
      setFormData((prev) => ({
        ...prev,
        [name]: value,
      }));
      // useFormInput(e);
    };

    const handleSubmit = async (e) => {
      e.preventDefault();
      setError("");
      setIsLoading(true);

      try {
        const response = await authApi.login(formData);
        const data = response.data;

        const { token } = data.data;
        console.log(token);
        dispatch(login(token));
        navigate("/");
      } catch (err) {
        console.error(err);
        setError(err.message || "로그인 중 오류가 발생했습니다.");
      } finally {
        setIsLoading(false);
      }
    };

    return (
      <div>
        <h2>로그인</h2>
        <form onSubmit={handleSubmit}>
          <label htmlFor="username">사용자 이름 : </label>
          <input
            id="username"
            name="username"
            required
            placeholder="사용자 이름"
            value={formData.username}
            onChange={handleFormInput}
          />

          <div>
            <label htmlFor="password">비밀번호</label>
            <input
              id="password"
              name="password"
              type="password"
              required
              placeholder="비밀번호"
              value={formData.password}
              onChange={handleFormInput}
            />
          </div>

          {error && <div>{error}</div>}

          <button type="submit">{isLoading ? "처리중..." : "로그인"}</button>
        </form>
      </div>
    );
  }
  ```

- `authSlice.js`

  ```jsx
  import { createSlice } from "@reduxjs/toolkit";

  const initialState = {
    token: localStorage.getItem("token"),
    isLoggedIn: !!localStorage.getItem("token"),
    user: {
      name: "anonimoususer",
    },
  };

  const authSlice = createSlice({
    name: "auth",
    initialState,
    reducers: {
      login: (state, action) => {
        state.token = action.payload;
        state.isLoggedIn = true;
        localStorage.setItem("token", action.payload);
      },
      logout: (state, action) => {
        state.token = null;
        state.isLoggedIn = false;
        localStorage.removeItem("token");
      },
    },
  });

  export const { login, logout } = authSlice.actions;
  export default authSlice.reducer;
  ```

---

## <mark color="#fbc956">로그인 이후 요청</mark>

> **🤔 로그인 이후 요청할 때 무엇이 필요할까?**
>
> - 전역 상태에 저장된 JWT를 요청마다 보내줌

- `api/axios.js`

  ```jsx
  import axios from "axios";
  import store from "../store/store";

  const api = axios.create({
    baseURL: import.meta.env.VITE_APP_API_URL,
  });

  // interceptor
  // interceptor로 에러핸들링 가능
  api.interceptors.request.use((config) => {
    const token = store.getState().auth.token;
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  });

  export default api;
  ```

### interceptor

- HTTP 통신에서 요청이나 응답을 가로채 처리할 수 있는 미들웨어
- `store` 에서 `token` 을 가져온 후, `token` 이 존재하면 `header` 에 추가해줌
- store에 있는 값은 useSelector 통해 가져와야 하지만, use로 시작하는 훅들은 컴포넌트 아니면 사용 할 수 없음
  - store에 직접 접근해 token 가져와 사용함

---

## <mark color="#fbc956">만료된 토큰에 대한 처리</mark>

- 사이트에 최초 접근 시 토큰이 만료된 경우에도 `isLoggedIn: !!localStorage/getItem("token")` 을 통해 로그인 된 것으로 판단 가능
- 사이트 최초 접근 시 token에 대한 validation 처리 필요

- `AuthController.java`

  - 단순히 JWT 검증을 위한 endpoint

  ```java
  @GetMapping("/verify")
  public void verify(){}
  ```

- `SecurityConfig.java`

  - `/auth/**` 를 인증하지 않기 때문에 `/auth/verify` 에 대해 인증 필요하도록 만들어줌
  - `/auth/**` 보다 `/auth/verify` 가 먼저 처리되도록 순서 조심

  ```java
  .authorizeHttpRequests(auth -> auth
  	.requestMatchers("/auth/verify").authenticated()
  	.requestMatchers("/auth/**", "/error", "/images/**").permitAll()
  	.requestMatchers(HttpMethod.GET, SecurityPathConfig.PUBLIC_GET_URLS).permitAll()
  	.anyRequest().authenticated()
  )
  ```

- `authApi.js`

  ```jsx
  // jwt 검사
  verify: async () => {
  	const response = await api.get(`${ENDPOINT}/verify`);
    return response;
  },
  ```

- 사이트에 최초 접근 시
  - `App.jsx` 에서 처리해줘야 하지만, Store를 사용해야 해 `Provider` 내부인 `RootLayout` 에서 진행
  - 더 나은 로직 위해 `AuthProvider` 와 같은 컴포넌트 생성
    - Provider → RouterProvider → AuthProvider ⇒ RootLayout 순으로 접근해 설계
- `RootLayout.jsx`

  ```java
  import React, { useEffect } from "react";
  import { Outlet } from "react-router-dom";
  import Header from "./components/Header";
  import { useDispatch } from "react-redux";
  import authApi from "./api/authApi";
  import { logout } from "./store/slices/authSlice";
  export default function RootLayout() {
    const dispatch = useDispatch();

    useEffect(() => {
      const verifyToken = async () => {
        try {
          await authApi.verify();
        } catch (err) {
          dispatch(logout());
        }
      };
      verifyToken();
    }, []);

    return (
      <>
        <Header></Header>
        <Outlet></Outlet>
        <footer></footer>
      </>
    );
  }

  ```

---

## <mark color="#fbc956">Create</mark>

- `PostCreate.jsx`

  ```jsx
  import React, { useEffect, useState } from "react";
  import { useDispatch, useSelector } from "react-redux";
  import { Navigate, useNavigate } from "react-router-dom";
  import postApi from "../api/postsApi";

  export default function PostCreate() {
    const navigate = useNavigate();

    const { isLoggedIn } = useSelector((state) => state.auth);
    const [formData, setFormData] = useState({ title: "", content: "" });
    const [error, setError] = useState("");

    useEffect(() => {
      if (!isLoggedIn) {
        navigate("/");
      }
    }, [isLoggedIn]);

    function handleFormInput(e) {
      const inputValue = e.target.value;
      const key = e.target.name;
      setFormData({
        ...formData,
        [key]: inputValue,
      });
    }

    function handleSubmit(e) {
      e.preventDefault();
      setError("");

      async function createPost() {
        try {
          const response = await postApi.createPost(formData);
          const data = response.data;
          const id = data.data.id;
          navigate(`/posts/${id}`);
        } catch (err) {
          console.log(err);
        }
      }
      createPost();
    }

    return (
      <>
        <h3>게시글 작성</h3>
        <form onSubmit={handleSubmit}>
          <label htmlFor="title">제목 : </label>
          <input
            id="title"
            name="title"
            required
            type="text"
            value={formData.title}
            onChange={handleFormInput}
          />
          <label>
            내용 :
            <textarea
              id="content"
              name="content"
              required
              value={formData.content}
              onChange={handleFormInput}
            ></textarea>
          </label>

          <button>제출</button>
        </form>
      </>
    );
  }
  ```

---

## <mark color="#fbc956">Comment Create</mark>

- PostDetail에 Comment 추가하는 Form 생성

- `PostDetail.jsx`

  ```jsx
  <CommentForm setPost={setPost} postId={postId}></CommentForm>
  ```

- `CommentForm.jsx`

  ```jsx
  import React, { useState } from "react";
  import postApi from "../api/postsApi";

  export default function CommentForm({ setPost, postId }) {
    const [formData, setFormData] = useState({ content: "" });

    function handleSubmit(e) {
      console.log(postId);
      e.preventDefault();

      async function createComment() {
        try {
          console.log(content);
          const response = await postApi.createComment(postId, formData);
          const data = response.data;
          setPost((prev) => {
            return {
              ...prev,
              comments: [...prev.comments, data.data],
            };
          });
          setFormData({ content: "" });
        } catch (err) {
          console.log(err);
        }
      }
      createComment();
    }
    function handleFormInput(e) {
      const inputValue = e.target.value;
      const key = e.target.name;
      setFormData({
        ...formData,
        [key]: inputValue,
      });
    }
    return (
      <>
        <div>댓글 작성</div>
        <form onSubmit={handleSubmit}>
          <textarea
            id="content"
            name="content"
            required
            value={formData.content}
            onChange={handleFormInput}
          ></textarea>
          <button>제출</button>
        </form>
      </>
    );
  }
  ```

---

### ☀️ 오늘의 배움

- **암호화 ↔ 복호화**

  - 암호화를 하면 복호화가 가능
  - JWT

- **해싱**

  - 복호화를 할 수 없음
  - 현재 react와 spring의 pw에

- @alias

- **AJAX (Asynchronous JavaScript XML)**

  - XMLHttpRequest 기술을 사용해 복잡하고 동적인 페이지 구성하는 프로그래밍 방식
    - 전체 페이지가 로드되지 않고 HTML 페이지 일부 DOM만 업데이트하는 좀 더 복잡한 웹페이지 생성 가능
    - 웹 페이지 일부 리로드 되는 동안 코드 계속 실행되어, 비동기식으로 작업 가능
    - 백그라운드 영역에서 서버와 통신해, 그 결과를 웹 페이지 일부분에만 표시 가능
  - 코드 내부에서 요청을 받아 응답을 해주는 기술

- **교차 출처 리소스 공유(Cross-Origin Resource Sharing, CORS)**

  - 다른 출처의 리소스 요청 제한
    - 이 API를 사용하는 웹 애플리케이션이 애플리케이션이 로드된 동일한 출처에서만 리소스 요청 가능
  - 브라우저가 막음
    - 브라우저와 서버 간의 안전한 교차 출처 요청 및 데이터 전송 지원
  - 서버가 클라이언트에게 어떤 리소스 공유할 예정이라고 하면 됨

  - 서버 데이터에 부수 효과 일으킬 수 있는 HTTP 방법(GET 이외 HTTP 메서드)
    1. 브라우저가 HTTP 메서드로 서버에서 지원하는 메서드를 요구하는 요청함 (사전 요청)
    2. 서버로부터 승인 받음 → 실제 요청 보내도록 지시
    3. 서버는 요청 + 자격 증명(쿠기, HTTP 인증)을 전송해야하는지 여부를 클라이언트에 알릴 수 있음

- Origin
- `setAllowCredentials` : 쿠키 전달

- HEAD : 메타 데이터 전달
- OPTIONS : 웹에서 필요

- **Preflight Request** : 서버가 안전한지 확인하는 요청

  - OPTIONS 메서드를 사용해 다른 출처의 리소스에 HTTP 요청을 보냄

- custom header

- 클라이언트-서버 디커플링

  - 서버가 클라이언트에게 허락

- Validation

  1. 클라이언트 : ux 개선
  2. 백엔드 : 데이터 정합성
     1. 컨트롤러 : @Valid
     2. 서비스 : Unique 등

- redux 더티체킹

> **로그인**

1. 아이디, 비밀번호 입력
2. 입력 받은 아이디, 비밀번호를 axios 요청
3. 서버에서 data(token) 응답 받음
4. token을 저장 (`dispatch(login(token))`)
   1. token을 store에 저장
   2. token을 rocalstorage에 저장

- JWT 저장

  - 브라우저
  - REDUX (전역 상태) → 단점 : 새로고침하면 날라감

  ⇒ 우리는 redux에 저장한 후, 새로고침하고 나면 브라우저에서 가져올 것임

- use~ → hook → 컴포넌트 내부에서만 실행 가능
- axios의 getState는 use 없이 바로 사용 (컴포넌트 내부가 아니라서)

  ```
  api.interceptors.request.use((config) => {
    const token = store.getState().auth.token;
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  });

  export default api;
  ```

- interceptors 를 통해 에러 핸들링을 할 수 있음

- **토큰이 비정상적일 때 (authProvider)**

  - 토큰 만료 / 로컬스토리지 토큰쪽에 이상한 값 넣었을 때
  - `.requestMatchers("/auth/verify").authenticated()`
    - security filter 확인하도록 함
  - refresh token 과정이 app.jsx에 추가될 수 있음
  - “에러 관리 및 로그인 다시 해줘“ 로직 추가

- 에러 핸들링
  - 리다이렉트
  - 에러 그냥 보여주기
