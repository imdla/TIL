> 💡 **한 줄 요약**
>
> `infer` 은 조건부 타입에서 특정 타입 추론에 사용하는데, 타입을 직접 지정하는 것이 아니라 타입스크립트가 해당 타입을 유추할 수 있도록 돕는 역할을 한다.

### 1. 🤔 왜 사용하는가

- **`infer`**
  - 조건부 타입에서 특정 타입 추론에 사용
  - 타입을 직접 지정하는 것이 아니라 타입스크립트가 해당 타입을 유추할 수 있도록 돕는 역할을 함
  - `extends` 를 사용하는 조건부 타입 안에서 활용
    - 특정 타입을 분해해 사용 가능

### 2. 💡 무엇인지 아는가(특징)

> **예시**

```tsx
type GetReturnType<T> = T extends (...args: any[]) => infer R ? R : never;
```

- 함수 타입 `T` 의 반환 타입을 추출하는 유틸리티 타입
- `T`
  - 함수 타입일 경우 → infer R을 통해 반환 타입을 R로 추론
  - 함수 타입이 아닐 경우 → `never` 반환
  ```tsx
  type ReturnType = GetReturnType<() => string>; // string
  ```
- 주의 : `infer` 은 반드시 조건부 타입 안에서 사용
  - 독립적으로 사용 시 문법 오류 발생

> **`extends`**

- 타입스크립트에서의 용도

  1. **제네릭에서 타입을 제한하는 역할**
     - ex. 특정 타입이 반드시 string을 확장해야할 경우
       ```tsx
       type Example<T extends string> = T;
       ```
  2. **조건부 타입에서 특정 타입이 다른 타입을 포함하는지 확인하는 역할**

     ```tsx
     type Check<T> = T extends string ? "문자열" : "다른 타입";

     type Example1 = Check<"hello">; // 문자열
     type Example2 = Check<100>; // '다른 타입'
     ```
