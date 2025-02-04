## <mark color="#fbc956">생성자 함수 (Constructor Function)</mark>

### **1. 생성자 함수 규칙**

- 함수 이름의 첫 글자는 대문자로 시작
- 반드시 ‘`new`’연산자를 붙여 실행

### 2. new 키워드

- `new` 키워드를 사용해서 함수를 실행할 경우 동작
  - 빈 객체를 만들어 `this`에 할당
  - 함수 본문을 실행, `this`에 새로운 프로퍼티 추가해 `this` 수정
  - `this` 반환

```jsx
function Professor(name, year) {
  // this = {}; (빈 객체가 암시적으로 만들어짐)

  // 새로운 프로퍼티를 this에 추가함
  this.name = name;
  this.year = year;

  // return this; (this가 암시적으로 반환됨)
}

// new 키워드 사용
const mia = new Professor("미아", 2023);
console.log(mia); // Professor { name: '미아', year: 2023 }
```

### 3. new 키워드 미사용

- 함수에서 반환해주는 것이 없음 → `undefined` 반환
- 프로퍼티는 `global` 객체로 이동

```jsx
function Professor(name, year) {
  this.name = name;
  this.year = year;

  // 생성자 함수 내 메소드 가능
  this.sayHi = function () {
    return `안녕하세요. ${this.name}입니다.`;
  };
}

// new 키워드 미사용 (함수형으로 실행했을 때)
const mia = Professor("미아", 2023);
console.log(mia); // undefined
console.log(global.name); // 미아
```

### 4. new.target과 생성자 함수

- **`new.target`** : `new`와 함께 호출되었는지 알 수 있음
  - `new` 사용 함수 호출 → 함수 자체 반환
  - `new` 미사용 함수 호출 → undefined 반환

```jsx
function Professor(name, year) {
  console.log(new.target);

  this.name = name;
  this.year = year;
}

// new 키워드 사용
const mia = new Professor("미아", 2023); // [Function: Professor]

// new 키워드 미사용
const john = Professor("존", 2023); // undefined
```

- **`new.target` 활용**
  - 함수 본문에서 해당 함수가 `new`와 함께 호출 되도록 만듦

```jsx
function Professor(name, year) {
  if (!new.target) {
    return new Professor(name, year);
  }

  this.name = name;
  this.year = year;
}

// new 키워드 사용
const mia = new Professor("미아", 2023);
console.log(mia); // Professor { name: '미아', year: 2023 }

// new 키워드 미사용
const john = Professor("존", 2023);
console.log(john); // Professor { name: '존', year: 2023 }
```

### 5. 생성자와 return

- 생성자 함수엔 보통 `return`문이 없음
- 반환해야 할 것은 모두 `this`에 저장되고, `this`는 자동으로 반환되기 때문에 반환문을 명시할 필요 없음
- **`return`문이 있을 경우**

  1. **객체를 `return`한다면 `this`대신 객체 반환**

     ```jsx
     function Professor(name, year) {
       this.name = name;
       this.year = year;
       return {};
     }

     const mia = new Professor("미아", 2023);
     console.log(mia); // {}
     ```

  2. **원시형을 `return` 한다면 `return` 문이 무시됨**

     ```jsx
     function Professor(name, year) {
       this.name = name;
       this.year = year;

       return "안녕하세요";
     }

     const mia = new Professor("미아", 2023);
     console.log(mia); // Professor { name: '미아', year: 2023 }
     ```

### 6. 생성자 내 메서드

- 생성자 함수는 메서드 가능

```jsx
function Professor(name, year) {
  this.name = name;
  this.year = year;

  // 생성자 함수 내 메소드 가능
  this.sayHi = function () {
    return `안녕하세요. ${this.name}입니다.`;
  };
}

// new 키워드 사용
const mia = new Professor("미아", 2023);
console.log(mia); // Professor { name: '미아', year: 2023, sayHi: [Function (anonymous)] }
console.log(mia.sayHi()); // 안녕하세요. 미아입니다.
```

### 7. Arrow 함수와 new

- `Arrow Function`으로 생성자 함수 만드는 것은 불가능

```jsx
const Professor = (name, year) => {
  this.name = name;
  this.year = year;
};

// 불가능
// const mia = new Professor('미아', 2023);
```
