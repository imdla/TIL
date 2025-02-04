## <mark color="#fbc956">JavaScipt Fetch</mark>

### 1. Fetch 함수

- JavaScript에서 비동기적으로 HTTP 요청을 보내고, 응답을 처리하는 내장 함수
- Promise 기반으로 동작해 쉽게 비동기 처리 가능

### 2. 문법

- **`url`** : 요청을 보낼 대상 URL
- **`options`** : HTTP 요청 추가 정보를 설정할 수 있는 객체
  - **`header`** : HTTP 요청 헤더 설정
  - **`method`** : HTTP 요청 방식 설정
  - **`body`** : HTTP 요청 시 추가 전송할 데이터 추가 작성
- **`response`** : 서버의 응답 데이터
- **`.json()`** : 응답 데이터를 JSON 형식으로 처리해 JavaScript 객체를 반환하는 함수로 Promise를 반환

```jsx
fetch(url, options)
  // 응답 데이터 JSON으로 변환
  .then((response) => respone.json())
  // JSON으로 변환한 데이터 처리
  .then((data) => console.log(data))
  // 에러 발생 시 에러 처리
  .catch((error) => console.error("Error:", error));
```

### 3. HTTP mathod

- 클라이언트와 서버 간의 통신에서 통신(요청)의 목적을 명시하기 위한 수단
- 클라이언트의 요청 HTTP method에 따라 서버가 요청을 처리하는 방식 달라짐

1. **GET** - 특정 리소스 가져오는 경우

   - 서버로부터 특정 `id` 의 리소스 가져옴
   - 엔드포인트 : `GET /posts/{id}`

   ```jsx
   fetch("https://jsonplaceholder.typicode.com/posts/1")
     .then((response) => respose.json())
     .then((json) => console.log(json))
     .catch((error) => console.log(error));
   ```

   ```jsx
   async function getPost() {
     try {
       const respose = await fetch(
         "https://jsonplaceholder.typicode.com/posts/1"
       );
       const data = response.json();
       console.log(data);
     } catch (error) {
       console.error("Error:", error);
     }
   }
   ```

2. **GET** - 리소스 목록 가져오는 경우

   - 서버로부터 리소스 목록 가져옴
   - 엔드포인트 : `GET /posts/`

   ```jsx
   fetch("https://jsonplaceholder.typicode.com/posts")
     .then((response) => response.json())
     .then((json) => console.log(json))
     .catch((error) => console.error(error));
   ```

   ```jsx
   async function getPost() {
     try {
       const response = await fetch(
         "https://jsonplaceholder.typicode.com/posts"
       );
       const data = await respose.json();
       console.log(data);
     } catch (error) {
       console.error("Error:", error);
     }
   }
   ```

3. **POST**

   - 서버에 데이터 전송해 새로운 리소스 생성
   - 엔드포인트 : `POST / posts`

   ```jsx
   fetch("https://jsonplaceholder.typicode.com/posts", {
     method: "POST",
     body: JSON.stringify({
       title: "My Title",
       body: "My Content",
       userId: 1,
     }),
     headers: {
       "Content-type": "applocation/json; charset=UTF-8",
     },
   })
     .then((response) => response.json())
     .then((json) => console.log(response))
     .catch((error) => console.error(erro));
   ```

   ```jsx
   async function createPost() {
     try {
       const response = await fetch(
         "https://jsonplaceholder.typicode.com/posts",
         {
           method: "POST",
           body: JSON.stringify({
             title: "My Title",
             body: "My Content",
             userId: 1,
           }),
           headers: {
             "Content-type": "application/json; charset=UTF-8",
           },
         }
       );

       const data = response.json();
       console.log(data);
     } catch (error) {
       console.error("Error:", error);
     }
   }

   createPost();
   ```

4. **PUT**

   - 서버의 리소스 수정
   - 엔드포인트 : `PUT /posts/{id}`

   ```jsx
   fetch("https://jsonplaceholder.typicode.com/posts/1", {
     method: "PUT",
     body: JSON.stringify({
       id: 1,
       title: "My Title",
       body: "My Content",
       userId: 1,
     }),
     headers: {
       "Content-type": "application/json; charset=UTF-8",
     },
   })
     .then((response) => reponse.json())
     .then((json) => console.log(json))
     .catch((error) => console.error(error));
   ```

   ```jsx
   async function updatePost() {
     try {
       const response = await fetch(
         "https://jsonplaceholder.typicode.com/posts/1",
         {
           method: "PUT",
           body: JSON.stringify({
             id: 1,
             title: "My Title",
             body: "My Content",
             userId: 1,
           }),
           headers: {
             "Content-type": "application/json; charset=UTF-8",
           },
         }
       );

       const data = response.json();
       console.log(data);
     } catch (error) {
       console.error("Error:", error);
     }
   }

   updatePost();
   ```

5. **PATCH**

   - 서버의 리소스를 부분적으로 수정
   - 엔드포인트 : `PATCH /posts/{id}`

   ```jsx
   fetch("https://jsonplaceholder.typicode.com/posts/1", {
     method: "PATCH",
     body: JSON.stringify({
       title: "My Title",
     }),
     headers: {
       "Content-type": "application/json; charset=UTF-8",
     },
   })
     .then((response) => response.json())
     .then((json) => console.log(json))
     .catch((error) => console.error(error));
   ```

   ```jsx
   async function partialUpdatePost() {
     try {
       const response = await fetch(
         "https://jsonplaceholder.typicode.com/posts/1",
         {
           method: "PATCH",
           body: JSON.stingify({
             title: "My Title",
           }),
           headers: {
             "Content-type": "application/json; charset=UTF-8",
           },
         }
       );

       const data = response.json();
       console.log(data);
     } catch (error) {
       console.error("Error:", error);
     }
   }

   partialUpdatePost();
   ```

6. **DELETE**

   - 서버의 리소스 삭제
   - 엔드포인트 : `DELETE /posts/{id}`

   ```jsx
   fetch("https://jsonplaceholder.typicode.com/posts/1", {
     method: "DELETE",
   });
   ```

   ```jsx
   async function deletePost() {
     const response = await fetch(
       "https://jsonplaceholder.typicode.com/posts/1",
       {
         method: "DELETE",
       }
     );
   }

   deletePost();
   ```

7. **with query parameter**

   - `key:value` 형태의 query parameter 추가 가능
   - `URLSearchParams` : URL의 쿼리 파라미터를 처리해주는 내장 객체

   ```jsx
   const baseURL = "https://jsonplaceholder.typicode.com/posts";
   const params = new URLSearchParams({
     userId: 1,
   });

   const URL = `${baseURL}?${params}`;

   fetch(URL)
     .then((response) => response.json())
     .then((json) => console.log(json))
     .catch((error) => console.error(error));
   ```
