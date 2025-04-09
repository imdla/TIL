> 💡 **한 줄 요약**
>
> Server Action은 서버에서 실행되며 브라우저에서 호출할 수 있는 비동기 함수로, 서버 로직을 직접 호출해 클라이언트와 서버 간의 상호작용을 간소화할 수 있게 해준다.

### 1. 🤔 왜 사용하는가

- **Server Action**
  - Next.js에서 제공하는 기능
  - 서버에서 실행되며 브라우저에서 호출할 수 있는 비동기 함수
  - 서버 로직을 직접 호출함으로써 클라이언트와 서버 간의 상호작용을 간소화할 수 있게 해줌
  - ex. 백엔드 서버와 API 통신하는 대신 Next 서버에서 데이터베이스에 직접 접근 가능

### 2. 💡 사용 방법

- **정의** : `use server` 디렉티브 사용
  - 함수가 서버에서만 실행되도록 지정

```tsx
"use server";

export async function createReviewAction(data: FormData) {
  const content = data.get("content");
  // 데이터베이스 저장 등의 작업
}
```

```tsx
<form action={createReviewAction}>
  <textarea name="content" required />
  <button type="submit">Submit</button>
</form>
```

- Server action 이용 시 폼 제출할 때 해당 정보를 가지고 데이터베이스 저장과 같은 서버 작업 수행 가능

### 3. ✅ 장점

1. **클라이언트와 서버 간 상호작용 간소화**

   - 기존
     - 데이터베이스와 관련된 처리 위해 백엔드 API와 통신하는 방법 사용
   - Server Action
     - 백엔드 API와 통신하지 않고, Next 서버에서 직접 데이터베이스 작업 수행 가능

   → 개발 생산성 향상

   → 네트워크 통신 감소해 성능면 이점

2. Server Action 로직은 클라이언트에 전송되지 않음

   → 보안에 도움

   - 외부에 노출되면 안되는 정보나 로직을 숨기는 데 활용 가능
   - 클라이언트 단의 일부 로직을 Server Action으로 옮길 경우 번들의 크기 감소에 기여

3. **JS가 로드되기 이전의 시점에도 서버와 상호작용 가능**
   - Server Action의 html `<form>` 은 `action` 속성을 이용해 폼 데이터를 서버에 전송
   - JS가 로드되지 않거나 비활성화되어도 서버와 통신 가능
