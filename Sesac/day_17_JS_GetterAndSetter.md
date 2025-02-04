## <mark color="#fbc956">Getter and Setter</mark>

### 1. getter (획득자)

- 데이터를 가공해서 새로운 데이터를 반환할 때

```jsx
class Person {
  name;
  year;

  constructor(name, year) {
    this.name = name;
    this.year = year;
  }

  get nameAndYear() {
    return `${this.name}-${this.year}`;
  }
}

const john = new Person("존", 2023);
console.log(john); // Person { name: '존', year: 2023 }
console.log(john.nameAndYear); // 존-2023
```

- private한 값을 반환할 때

```jsx
class Person2 {
  #name;
  year;

  constructor(name, year) {
    // private한 값 => # 이용
    this.#name = name;
    this.year = year;
  }

  get name() {
    return this.#name;
  }

  set name(name) {
    this.#name = name;
  }
}

const john2 = new Person2("존", 2023);

console.log(john2); // Person { year: 2023} => name의 값 나오지 않음
console.log(john2.name); // 존 => 지정해야 name의 값 나옴

john2.name = "마이클";
console.log(john2.name); // 마이클
```

### 2. setter(설정자)

- `setter`는 하나의 `parameter` 필수

```jsx
class Person {
  name;
  year;

  constructor(name, year) {
    this.name = name;
    this.year = year;
  }

  get nameAndYear() {
    return `${this.name}-${this.year}`;
  }

  set setName(name) {
    this.name = name;
  }
}

const john = new Person("존", 2023);
john.setName = "마이클";
console.log(john); // Person { name: '마이클', year: 2023 }
```
