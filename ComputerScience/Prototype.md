> 💡 **한 줄 요약**
>
> 프로토타입은 자바스크립트에서 객체 간 상속을 구현하는 메커니즘으로, 자바스크립트의 모든 객체는 기본적으로 `[[Prototype]]` 이라는 숨김 프로퍼티를 가진다.

### 1. 🤔 왜 사용하는가

- **프로토타입**
  - 자바스크립트에서 객체 간 상속을 구현하는 메커니즘
  - 자바스크립트의 모든 객체는 기본적으로 `[[Property]]` 라는 숨김 프로퍼티 가짐
    - `[[Property]]`
      - 다른 객체 참조
      - `null` 값 가짐
  - 프로토타입 연결
    - `Object.create()`
    - 함수 생성자의 `prototype` 프로토타입

### 2. 💡 무엇인지 아는가(특징)

> **프로토타입 상속이 동작하는 방식 → 프로토타입 체인 기반**

1. 객체에서 어떤 프로퍼티 접근할 경우
2. 자바스크립트 엔진은 해당 객체에서 프로퍼티 찾음
3. 찾을 수 없을 경우
   - 객체의 `[[Prototype]]`이 가리키는 프로토타입 객체에서 프로퍼티 탐색
4. 프로토타입 객체에서도 찾지 못할 경우
   - 프로토타입의 프로토타입을 탐색
5. 탐색 과정을 반복
   1. 결국 원하는 프로퍼티 찾음
   2. 프로토타입이 `null`이 되는 단계에 도달
   - 프로토타입 체인을 타고 올라가는 방식으로 탐색
     - **프로토타입 체인** : 프로토타입이 꼬리에 꼬리를 물고 연결된 형태

```jsx
// 1. Object.create() 방식
const dog = {
  greet() {
    console.log("Hello from dog!");
  },
};

// maru의 프로토타입이 dog로 설정
const maru = Object.create(dog);
maru.greet(); // "Hello from dog!"
```

```jsx
// prototype 프로퍼티 방식
function Dog() {}
Dog.prototype.greet = function () {
  console.log("Hello from Dog!");
};

// maru의 프로토타입이 Dog로 설정
const maru = new Dog();
maru.greet(); // "Hello from dog!"
```

- **객체 `maru`가 `dog` 를 프로토타입으로 갖고 있을 경우**
  - `maru.greet()` 호출할 경우
    1. `maru` 에 `greet()`이 없으면 프로토타입인 `dog` 에 `greet()`이 존재하는지 탐색
    2. `dog` 에 `greet()`이 존재할 경우
       - 탐색 멈추고 해당 메서드 호출
    3. `dog`에도 존재하지 않을 경우
       - 프로토타입 체인의 끝에 도달할 때까지 상위 프로토타입을 계속 탐색
