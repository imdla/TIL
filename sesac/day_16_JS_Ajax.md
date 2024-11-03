## <mark color="#fbc956">Ajax</mark>


### 1. Ajax(Asynchronous JavaScript And XML)

- 서버와의 데이터 통신을 비동기적으로 처리하는 기술
- 웹 페이지에서 새로고침을 하지 않고 서버와 통신 할 수 있게 해줌
- **필요성**
    - 웹 페이지와 새로고침 없이 서버와 통신, 필요한 데이터만 부분적으로 받을 수 있음
    - 처리하는 데이터 양이 작아 페이지 로딩 시간이 단축
    - 필요한 데이터만 받아 네트워크 사용량이 감소
    - 새로고침과 페이지 전환 없이 빠르게 콘텐츠 이용할 수 있어 사용자 경험 향상

### 2. Axios

- `Promise` 기반의 HTTP 통신 라이브러리
- `fetch()` 와 사용법 비슷, JSON 데이터를 기본적으로 처리해줌
- Axios 설치 : CDN 활용 /  npm 활용 라이브러리 설치 가능
1. **GET : `axios.get(url)`**
    
    ```jsx
    async function readTodo(todoId) {
      try {
        const url = `https://jsonplaceholder.typicode.com/todos/${todoId}`;
    
        // GET 요청 & 응답 
        const response = await axios.get(url);
        console.log(response.data);
      } catch (error) {
        console.error('에러 발생 : ', error);
      }
    }
    
    readTodo(1);
    ```
    
2. **POST : `axios.post(url, data)`**
    
    ```jsx
    async function createTodo() {
      try {
        const url = `https://jsonplaceholder.typicode.com/todos`;
    
        // POST 요청 & 응답
        const response = await axios.get(url, newTodo);
    
        console.log(response.data);
      } catch (error) {
        console.error('에러 발생 : ', error);
      }
    }
    
    const newTodo = {
      title: '할일',
      completed: false,
      userId: 1, 
    }
    
    createTodo(newTodo)
    ```
    

### 3. 비동기적 DOM 조작

- 버튼 클릭 → 서버에 요청 → 받은 데이터 기반 새로운 DOM 생성

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
  </head>
  </head>
  <body>
    <div id="container">
      <button id="button">데이터 요청</button>
      <p>할 일 목록</p>
    </div>

    <script>
      const button = document.querySelector('#button');
      const container = document.querySelector('#container');

      // 비동기 데이터 요청 함수
      async function getData(todoId) {
        try {
          const url = `https://jsonplaceholder.typicode.com/todos/${todoId}`;
          const response = await axios.get(url);

          const pTag = document.createElement('p');
          pTag.textContent = `할 일 : ${response.data.title}`

          container.append(pTag);
        } catch (error) {
          console.error('오류 발생 : ', error);
        }
      }

      let todoId = 1;

      // 버튼 click 이벤트
      button.addEventListener('click', () => {
        getData(todoId);

        todoId = todoId + 1;
      });
    </script>
  </body>
</html>

```