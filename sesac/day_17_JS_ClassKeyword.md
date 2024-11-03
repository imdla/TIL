## <mark color="#fbc956">Class</mark>

## 1. 객체 지향 프로그래밍(OOP)

- Java 및 C++를 비롯한 많은 프로그래밍 언어의 기본이 되는 프로그래밍 패러다임
- 각 객체가 시스템의 특정 측면을 나타내는 객체 모음으로 시스템을 모델링 하는 것

### 2. Class Keyword

- 객체지향 프로그래밍에서 특정 객체(인스턴스)를 생성하기 위한 변수와 메소드(함수)를 정의하는 일종의 틀
- JavsScript에서 함수의 한 종류

### 3. 클래스 (class) 생성

- 클래스 생성 후, `new 클래스명()` 호출 시 내부에 정의한 메서드가 들어있는 객체 생성
- **`constructor()`** : 객체의 기본 상태 설정해주는 생성자 메서드
  - `new` 에 의해 자동 호출

```jsx
class 클래스명 {
	constructor() { ... }
	method() { ... }
	...
}
```

```jsx
class AnimalGroup {
  // property
  name;
  age;

  // constructor - 생성자
  // 생성자는 클래스 정의의 일부로 작성, 일반적으로 클래스 자체와 동일한 이름 가짐
  constructor(name, age) {
    this.name = name;
    this.age = age;
  }

  // method
  sayName() {
    return `저는 ${this.name}입니다.`;
  }
}
```

### 2. 인스턴스 생성

- **인스턴스**
  - 각 구체적인 객체는 클래스의 인스턴스라고 함
  - 인스턴스 생성 프로세스는 생성자라는 특수 함수에 의해 수행됨

```jsx
const dog = new AnimalGroup("개", 5);
console.log(dog); // AnimalGroup { name: '개', age: 5 }

const cat = new AnimalGroup("고양이", 4);
console.log(cat); // AnimalGroup { name: '고양이', age: 4 }

const cow = new AnimalGroup("소", 3);
console.log(cow); // AnimalGroup { name: '소', age: 3 }
```

### 3. 객체 호출

```jsx
console.log(dog.name); // 개
console.log(dog.age); // 5

// 아래의 경우는 다형성
console.log(dog.sayName()); // 저는 개입니다.
console.log(cat.sayName()); // 저는 고양이입니다.
```

> **다형성?** method의 이름은 같지만 다른 클래스에서 구현이 다른 경우

### 4. 클래스와 인스턴스 타입

- 클래스는 함수의 한 종류

```jsx
console.log(typeof AnimalGroup); // function
console.log(typeof dog); // object
```
