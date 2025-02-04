## <mark color="#fbc956">this 키워드</mark>

### 1. this 키워드

- 함수에서 현재 실행 중인 코드의 컨텍스트를 참조하는 객체에 접근
- 함수의 호출 방식에 따라 `this` 가 가리키는 값 달라짐
  ⇒ 실행 중에는 할당으로 설정할 수 없고 함수를 호출할 때 마다 다를 수 있음
- 전역(Global) 컨텍스트에서는 전역개체 가리킴

> 💡 일반 함수 호출 → `this`가 최상위 객체 (global 또는 window)를 가르킴
>
> 메서드로 호출 → 호출된 객체 가르킴
> `new` 키워드 사용해서 객체를 생성 → 객체 가르킴

> 💡 **컨텍스트 (context) ?**
>
> 코드가 실행되는 환경(위치)

### 2. 함수 내부의 this

- 일반 함수에서는 전역 개체 가리킴

```jsx
const testFunc = function () {
  return this;
};

console.log(testFunc() === global); // true
```

### 3. 메서드 내부의 this

- 메서드가 어디서 정의되었는지 상관없이 `this`는 점 앞의 객체(실행된 객체)가 무엇인가에 따라 결정

```jsx
function Professor(name, year) {
  this.name = name;
  this.year = year;

  this.sayHi = function () {
    return `안녕하세요 저는 ${this.name}입니다.`;
  };
}

const mia = new Professor("미아", 2023);

// 생성자 함수(Professor) 내 this.sayHi 선언 후
// new 키워드로 Professor를 이용해 mia 객체를 만들어
// this는 mia를 가르킴
console.log(mia.sayHi()); // 안녕하세요 저는 미아입니다.

Professor.prototype.teach = function () {
  return `${this.name}가 학생들을 가르칩니다.`;
};

console.log(mia.teach()); // 미아가 학생들을 가르칩니다.
```

### 4. 화살표 함수 내부의 this

- 상위 컨텍스트의 `this` 를 상속 받음
- `this` 가 binding 되지 않음

```jsx
const cafe = {
  menu: "coffee",
  order: () => {
    console.log(`메뉴 ${this.menu}를 주문 완료했습니다.`);
  },
};

cafe.order(); // 메뉴 undefined를 주문 완료했습니다.
```

### **5. 메서드에서 한 번에 비교하기**

(메서드의 `this` / 메서드 내 함수 선언문의 `this` / 메서드 내 화살표 함수의 `this`)

1. **메서드의 `this`**

   ⇒ 컨텍스트는 `Professor` → `Professor` 의 `name` 존재

   ```jsx
   function Professor(name, year) {
     this.name = name;
     this.year = year;
   }

   const mia = new Professor("미아", 2023);

   Professor.prototype.teach = function () {
     return `${this.name}가 학생들을 가르칩니다.`;
   };

   console.log(mia.teach()); // 미아가 학생들을 가르칩니다.
   ```

2. **메서드 내 함수 선언문의 `this`**

   ⇒ 컨텍스트는 `teach` → `teach` 에는 `name` 없음

   ```jsx
   function Professor(name, year) {
     this.name = name;
     this.year = year;
   }

   const mia = new Professor("미아", 2023);

   Professor.prototype.teach = function () {
     function teach2() {
       return `${this.name}가 학생들을 가르칩니다.`;
     }

     return teach2();
   };

   // 객체의 메서드로 함수 선언 시 this가 자동으로 실행하는 대상 객체를 가르킴
   // 그 외의 위치에서 함수 선언 시 함수의 this는 무조건 global object를 가르킴
   console.log(mia.teach()); // undefined가 학생들을 가르칩니다.
   ```

3. **메서드 내 화살표 함수의 `this`**

   ⇒ 컨텍스트는 `teach` → 화살표 함수의 `this` 는 상위 컨텍스트 가리킴 (`Professor`) → `Professor` 의 `name` 존재

   ```jsx
   function Professor(name, year) {
     this.name = name;
     this.year = year;
   }

   const mia = new Professor("미아", 2023);

   Professor.prototype.teach = function () {
     const teach2 = () => {
       return `${this.name}가 학생들을 가르칩니다.`;
     };

     return teach2();
   };

   console.log(mia.teach()); // 미아가 학생들을 가르칩니다.
   ```

### 6. 함수 바인딩

- 함수를 어떻게 호출했는지 상관하지 않고 `this` 값을 설정 가능
- `call()` ⇒ 컴마를 기반으로 `arguments`를 순서대로 넘겨줌
- `apply()` ⇒ `arguments`를 리스트로 입력함

```jsx
function returnName() {
  return this.name;
}

console.log(returnName()); // undefined

const mia = {
  name: "미아",
};

// call(), apply() 사용해 mia에 this 키워드 바인딩
console.log(returnName.call(mia)); // 미아
console.log(returnName.apply(mia)); // 미아

// call(), apply() 차이점 비교
function multiply(x, y, z) {
  return `${this.name} / 결과값 : ${x * y * z}`;
}

console.log(multiply.call(mia, 1, 2, 3)); // 미아 / 결과값 : 6
console.log(multiply.apply(mia, [1, 2, 3])); // 미아 / 결과값 : 6
```

- `bind()` ⇒ `this`를 바인딩 해놓고 나중에 실행 가능

```jsx
// 위의 예제 이어서 진행

const lateFunc = multiply.bind(mia, 1, 2, 3);
console.log(lateFunc); // [Function: bound multiply]
console.log(lateFunc()); // 미아 / 결과값 : 6
```
