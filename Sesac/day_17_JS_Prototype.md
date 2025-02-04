## <mark color="#fbc956">Prototype (프로토타입)</mark>

### 1. 프로토타입 기반 언어 **(prototype-based language)**

- 모든 객체들이 메소드와 속성들을 상속 받기 위한 템플릿으로써 **prototype-based language** 가짐

### 2. 프로토타입 (**prototype**)

- 개발 사이클의 초기 단계에서 제품 혹은 어플리케이션의 외형이나 동작을 보여줄 수 있는 모델
- 각 객체에는 **프로토타입**이라는 다른 객체에 대한 링크를 보유하는 비공개 속성이 있음
- 그 프로토타입 객체도 **자신만의 프로토타입**을 가지고 있으며, 프로토타입으로 `null`을 가진 객체에 도달할 때까지 연결은 계속됨

### 3. [[Prototype]]

- 자바스크립트의 객체는 명세서에서 명명한 `[[Prototype]]` 이라는 **숨김 프로퍼티**를 가짐
- 이 **숨김 프로퍼티 값**은 `null` 이거나 **다른 객체에 대한 참조**가 됨
- 다른 객체를 참조하는 경우 → **참조 대상**을 **프로토타입(prototype)**이라 부름

---

## <mark color="#fbc956">프로토타입 상속</mark>

### 1. 프로토타입 상속 (prototypal inheritance)

- `object` 프로퍼티를 읽으려고 하는데 해당 프로퍼티가 없으면 자바스크립트는 자동으로 프로토타입에서 프로퍼티를 찾음

> `__proto__` 는 `[[Prototype]]` 용 getter-setter
>
> → 근래에 작성된 스크립트에선 `__proto__` 대신 함수 `Object.getPrototypeOf`나 `Object.setPrototypeOf` 를 사용해 프로토타입을 `get`(획득)하거나 `set`(설정)함

```jsx
let professor = {
  sayHi: true,
};

let mia = {
  teach: true,
};

// 객체 mia에서 자동으로 professor라는 객체에서 프로퍼티 받음
mia.__proto__ = professor;

// mia에서 professor의 프로퍼티와 메서드 사용 가능
console.log(mia.sayHi); // true
console.log(mia.teach); // true
```

> 💡 **상속 프로퍼티(inherited property) ?**
>
> 프로토타입에서 상속받은 프로퍼티

### 2. 프로토타입 체인

- 프로토타입 객체도 또 다시 상위 프로토타입 객체로부터 메소드와 속성을 상속 받을 수 있고, 그 상위 프로토타입 객체도 마참가지임

```jsx
let person = {
  walk: true,
  speak() {
    return "말합니다";
  },
};

let professor = {
  sayHi: true,
  __proto__: person,
};

let mia = {
  name: "미아",
  __proto__: professor,
};

console.log(mia.speak()); // 말합니다
console.log(mia.sayHi); // true
```

### 3. 프로토타입 체인의 제약사항

- **순환 참조(circular reference)**는 허용되지 않음
  - `__proto__` 를 이용해 닫힌 형태로 다른 객체를 참조하면 에러 발생
- `__proto__` 의 값 : **객체나 `null`만** 가능, 다른 자료형은 무시
- 객체엔 **오직 하나의 `[[Prototype]]` 만** 있을 수 있음, 두 개의 객체를 상속받지 못함

---

## <mark color="#fbc956">프로토타입의 변경</mark>

### 1. 프로토타입의 추가와 수정

```jsx
let person = {
  walk: true,
  speak() {},
};

let professor = {
  __proto__: person,
};

professor.speak = function () {
  return "교수님이 말합니다.";
};

// professor에 직접 추가한 메서드가 실행
console.log(professor.speak()); // 교수님이 말합니다.
```

- **접근자 프로퍼티에서 추가와 수정**
  - 접근자 프로퍼티는 `setter` 함수를 사용해 프로퍼티에 값을 할당함
    → 접근자 프로퍼티에 값을 할당하면 `setter` 함수가 호출됨

```jsx
let user = {
  firstName: "John",
  lastName: "Smith",

  get fullName() {
    return `${this.firstName} ${this.lastName}`;
  },

  set fullName(value) {
    [this.firstName, this.lastName] = value.split(" ");
  },
};

let admin = {
  __proto__: user,
  isAdmin: true,
};

console.log(admin.fullName); // John Smith

// setter 함수 실행
admin.fullName = "Alice Cooper";

console.log(admin.fullName); // Alice Cooper
console.log(user.fullName); // John Smith
```

---

## <mark color="#fbc956">프로토타입의 활용</mark>

### 1. 프로퍼티 반환

- **프로토타입에서 반복**
  - **`Object.keys()`** : **객체 자신만**의 키만 반환
  - **`for...in`** : 객체 자신의 키와 상속 프로토타입 키 **모두** 순회

```jsx
let professor = {
  speak: true,
};

let mia = {
  teach: true,
  __proto__: professor,
};

// Object.keys는 객체 자신만의 키만 반환
console.log(Object.keys(mia)); // [ 'teach' ]

// for...in은 객체 자신의 키와 상속 프로퍼티의 키 모두 순회
for (let key in mia) {
  console.log(key);
} // teach, speak
```

### 2. 프로퍼티의 상속 여부 확인

- **`obj.hasOwnProperty(key)`**
  - 상속 받은 프로퍼티의 경우 : `false` 반환
  - 고유 프로퍼티일 경우 : `true` 반환

```jsx
function Professor(name, year) {
  this.name = name;
  this.year = year;

  this.sayHi = function () {
    return `${this.name} 인사를 합니다.`;
  };
}

const mia = new Professor("미아", 2023);
const john = new Professor("존", 2022);

console.log(mia.sayHi()); // 미아 인사를 합니다.
console.log(john.sayHi()); // 존 인사를 합니다.

// sayHi는 mia의 고유 프로퍼티임
console.log(mia.hasOwnProperty("sayHi")); // true
// sayHi는 각 객체의 고유 프로퍼티 => 같지 않음
console.log(mia.sayHi === john.sayHi); // false
```

### 3. 프로토타입에서 메서드 빌려오기

```jsx
function Dancer(name, height) {
  this.name = name;
  this.height = height;
}

// Dancer prototype에 sayHi 추가
Dancer.prototype.sayHi = function () {
  return `${this.name} 인사를 합니다.`;
};

const mia = new Dancer("미아", 170);
const john = new Dancer("존", 180);

console.log(mia.sayHi()); // 미아 인사를 합니다.
console.log(john.sayHi()); // 존 인사를 합니다.

// sayHi는 Dancer prototype의 프로토타입 => 같음
console.log(mia.sayHi === john.sayHi); // true

// sayHi는 Dancer prototype의 프로토타입으로 상속 프로퍼티
console.log(mia.hasOwnProperty("sayHi")); // false
```

---

## <mark color="#fbc956">프로토타입 프로퍼티</mark>

### 1. 함수의 prototype 프로퍼티

```jsx
function Professor(name, year) {
  this.name = name;
  this.year = year;
}

const mia = new Professor("미아", 2023);

console.log(mia.__proto__); // {}
console.log(mia.__proto__ === Professor.prototype); // true

console.log(Professor.__proto__ === Function.prototype); // true
console.log(Function.prototype.__proto__ === Object.prototype); // true

// 상속 체인의 가장 최상위는 Object.prototype이 있음
console.log(Professor.prototype.__proto__ === Object.prototype); // true
```

### 2. 함수의 디폴트 프로퍼티 prototype과 constrctor 프로퍼티

- 모든 함수는 기본적으로 `prototype` 프로퍼티를 가짐
- 디폴트 프로퍼티 `prototype`은 `constructor` 프로퍼티 하나만 있는 객체를 가리킴
- `constructor` 프로퍼티는 함수 자신을 가리킴

```jsx
function Professor(name, year) {
  this.name = name;
  this.year = year;
}

console.log(Professor.prototype); // {}
// showHidden: true => 숨겨진 값 보여줌
// 디폴트 프로퍼티 prototype은
// 함수 자신(Professor)을 가르키는
// constructor 프로퍼티를 가지고 있음
console.dir(Professor.prototype, {
  showHidden: true,
});
/**
<ref *1> {
  [constructor]: [Function: Professor] {
    [length]: 2,
    [name]: 'Professor',
    [arguments]: null,
    [caller]: null,
    [prototype]: [Circular *1]
  }
}
*/

console.log(Professor.prototype.constructor === Professor); // true
console.log(Professor.prototype.constructor.prototype === Professor.prototype); // true
```

### 3. 내장 객체의 프로토타입

```jsx
const testObj = {};

console.log(testObj); // {}
console.log(testObj.toString()); // [object Object]
console.log(Object.prototype.toString()); // [object Object]

console.log(testObj.__proto__ === Object.prototype); // true

// testObj.toString()을 호출하면
// Object.prototype에서 해당하는 메서드 가져옴
console.log(testObj.toString === testObj.__proto__.toString); // true
console.log(testObj.toString === Object.prototype.toString); // true
```

### 4. static 프로퍼티 추가

```jsx
function Professor(name, year) {
  this.name = name;
  this.year = year;
}

Professor.sayStaticHi = function () {
  return `static method 입니다.`;
};

console.log(Professor.sayStaticHi()); // static method 입니다.
```

### 5. 프로토타입 프로퍼티에서의 Overriding

```jsx
function Professor(name, year) {
  this.name = name;
  this.year = year;

  // 후에 추가
  this.sayHi = function () {
    return "인스턴스 메서드입니다.";
  };
}

// 먼저 추가
Professor.prototype.sayHi = function () {
  return "프로토타입 메서드입니다.";
};

const mia = new Professor("미아", 2023);
// 프로퍼티 셰도잉 - class에서 override
console.log(mia.sayHi()); // 인스턴스 메서드입니다.
```

### 6. getPrototypeOf 와 setPrototypeOf

- **`Object.getPrototypeOf(obj)` :** `obj`의 `[[Prototype]]` 반환
- **`Object.setPrototpyeOf(obj)` :** `obj`의 `[[Prototype]]`이 `proto`가 되도록 설정

1. **인스턴스 **proto**변경**

   ```jsx
   function Professor(name, year) {
     this.name = name;
     this.year = year;
   }

   Professor.prototype.sayHi = function () {
     return `${this.name} 인사를 합니다.`;
   };

   function MathProfessor(name, year) {
     this.name = name;
     this.year = year;

     this.teach = function () {
       return `${this.name} 수학을 가르칩니다.`;
     };
   }

   const mia = new Professor("미아", 20203);
   const john = new MathProfessor("존", 2022);

   console.log(mia.__proto__); // { sayHi: [Function (anonymous)] }
   console.log(mia.__proto__ === Professor.prototype); // true

   // 인스턴스 __proto__변경
   // getPrototypeOf으로 mia의 [[Prototype]] 반환
   console.log(Object.getPrototypeOf(mia) === Professor.prototype); // true

   console.log(mia.sayHi()); // 미아 인사를 합니다.
   console.log(john.teach()); // 존 수학을 가르칩니다.

   // getPrototypeOf으로 john의 [[Prototype]] 반환
   console.log(Object.getPrototypeOf(john) === MathProfessor.prototype); // true

   // setPrototypeOf으로 [[Prototype]] 변경
   Object.setPrototypeOf(john, Professor.prototype);
   console.log(john.sayHi()); // 존 인사를 합니다.

   // john의 프로토타입 __proto__가 Professor prototype으로 변경되어서 false나옴
   console.log(john.constructor === MathProfessor); // false
   console.log(john.constructor === Professor); // true
   console.log(mia.constructor === Professor); // true
   console.log(Object.getPrototypeOf(john) === MathProfessor.prototype); // false
   console.log(Object.getPrototypeOf(john) === Professor.prototype); // true

   // john의 프로토타입 __proto__가 변경되었다고 해서 MathProfessor까지 변경되지 않음
   console.log(MathProfessor.prototype === Professor.prototype); // false
   ```

2. **함수의 prototype 변경**

   ```jsx
   // 위의 이어서 진행

   // 함수의 prototype 변경
   MathProfessor.prototype = Professor.prototype;

   const micky = new MathProfessor("미키", 2021);
   // MathProfessor prototype을 Professor prototype으로 변경해서 true 나옴
   console.log(Object.getPrototypeOf(micky) === MathProfessor.prototype); // true
   console.log(MathProfessor.prototype === Professor.prototype); // true
   ```
