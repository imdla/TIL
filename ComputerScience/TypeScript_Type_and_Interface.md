> 💡 **한 줄 요약**
>
> 타입스크립트의 interface는 선언 병합을 통해 여러 번 선언이 가능해, 주로 객체 타입 확장에 유리하다. type은 튜플 등 복잡한 타입을 사용 및 유연한 연산자를 통해 복잡한 타입 조합에 적합하다.

### 1. 🤔 왜 사용하는가

- **`interface`**
  - **객체의 형태 확장**에 용이
- **`type`**
  - 튜플, 인터섹션, 유니온 등을 이용해 더 복잡한 타입 정의 및 조합을 표현

### 2. 💡 무엇인지 아는가(특징)

- **`interface` 예시 및 특징**

  - 선언 병합 지원
    - 여러 번 선언 가능
    - 주로 객체 타입 확장 시 유리
    - 동일 이름 가진 interface 여러 번 선언 시, 속성들이 자동으로 합쳐짐

  ```tsx
  interface Person {
    age: number;
    name: string;
    isBirthday: boolean;
  }

  interface Person {
    address: string;
  }

  const person1: Person = {
    age: 1,
    name: "john",
    isBirthday: false,
    address: "1010",
  };
  ```

- **`type` 예시 및 특징**

  - 동일 이름으로 중복 선언 시 에러 발생
  - 튜플과 같은 복잡한 타입 표현 가능
    - 복잡한 타입 조합 위해 인터섹션(`&`)과 유니온(`|`) 연산자 지원

  ```tsx
  // 여러 타입을 조합하는 방식

  type BasicInfo = {
    name: string;
    age: number;
  };

  type ContactInfo = {
    email: string;
    phone: string;
  };

  // 인터섹션 타입(&)을 사용해 두 타입 결합해 하나의 타입으로 생성
  type PersonInfo = BasicInfo & ContactInfo;

  const Person2: PersonInfo = {
    name: "john",
    age: 1,
    email: "john@example.com",
    phone: "123-4567-7890",
  };
  ```

### 3. ✅ 장점

- **`interface`**
  - 선언 병합을 통해 여러 번 선언 가능
  - 주로 객체 타입 확장에 유리
- **`type`**
  - 튜플 등 복잡한 타입 사용
  - 유연한 연산자 통해 복잡한 타입 조합에 표현
