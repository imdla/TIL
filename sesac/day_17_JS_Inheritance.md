## <mark color="#fbc956">클래스 상속 (Inheritance)</mark>

### 1. 클래스 상속

- 객체들 간의 관계를 구축하는 방법
- 슈퍼클래스(부모클래스) 등의 기존의 클래스로부터 속성과 동작을 상속 받을 수 있음

### 2. class 문법으로 상속

- **하위 클래스 생성** : `extends` 키워드를 통해 상속 받을 클래스를 명시

```jsx
class Professor {
  name;
  year;

  constructor(name, year) {
    this.name = name;
    this.year = year;
  }
}

// Professor을 상속 받아 FemaleProfessor 클래스를 생성
class FemaleProfessor extends Professor {
  sayName() {
    return `교수님이 출석을 부릅니다.`;
  }
}

// Professor을 상속 받아 MaleProfessor 클래스를 생성
class MaleProfessor extends Professor {
  teach() {
    return `교수님이 가르치십니다.`;
  }
}
```

- 클래스의 인스턴스 생성

```jsx
// FemaleProfessor의 인스턴스 생성
const mia = new FemaleProfessor("미아", 2023);
console.log(mia); // FemaleProfessor { name: '미아', year: 2023 }

// MaleProfessor의 인스턴스 생성
const john = new MaleProfessor("존", 2022);
console.log(john); // MaleProfessor { name: '존', year: 2022 }

console.log(mia.sayName()); // 교수님이 출석을 부릅니다.
console.log(mia.name); // 미아

console.log(john.teach()); // 교수님이 가르치십니다.
console.log(john.year); // 2022

// Professor의 인스턴스 생성
const james = new Professor("제임스", 2021);
console.log(james); // Professor { name: '제임스', year: 2021 }
```

### 3. 클래스 타입 비교

- **`instanceof`**
  - 객체가 특정 클래스에 속하는지 아닌지 환인 가능
  - 상속 관계도 확인 가능
  - `obj` 가 `Class` 에 속하거나 `Class` 에 상속받는 클래스에 속할 경우 → `true` 반환
  - 생성자 함수, `array` 와 같은 내장 클래스에도 사용 가

```jsx
obj instanceof Class;
```

```jsx
// 상위 내용을 이어서 진행

// mia는 FemaleProfessor의 인스턴스이므로
// FemaleProfessor과 Professor 양 쪽의 메소드와 속성 사용 가능
console.log(mia instanceof Professor); // true
console.log(mia instanceof FemaleProfessor); // true
console.log(mia instanceof MaleProfessor); // false

// john은 MaleProfessor의 인스턴스이므로
// MaleProfessor과 Professor 양 쪽의 메소드와 속성 사용 가능
console.log(john instanceof Professor); // true
console.log(john instanceof FemaleProfessor); // false
console.log(john instanceof MaleProfessor); // true

// james는 Professor의 인스턴스이므로
// Professor의 메소드와 속성만 사용 가능
console.log(james instanceof Professor); // true
console.log(james instanceof FemaleProfessor); // false
console.log(james instanceof MaleProfessor); //false
```
