## <mark color="#fbc956">Super and Override</mark>

### 1. Overriding

- 부모-자식 상속 관계에 있는 클래스에서 상위 클래스의 메서드를 하위 클래스에서 재정의 하는 것

### 2. super 키워드

- **`super` 키워드**
  - 객체 리터럴 또는 클래스의 Prototype 속성에 접근
  - 슈퍼 클래스의 생성자를 호출하는 데 사용
  - **`super.method(...)`** : 부모 클래스에 정의된 `method` 호출
  - **`super(...)`** : 부모 생성자 호출, 자식 생성자 내부에서만 사용 가능

```jsx
class Professor {
  name;
  year;

  constructor(name, year) {
    this.name = name;
    this.year = year;
  }

  sayHello() {
    return `안녕하세요 ${this.name}입니다.`;
  }
}

class FemaleProfessor extends Professor {
  subject;

  constructor(name, year, subject) {
    // 기존 프로퍼티를 받을 경우, 슈퍼키워드 사용
    // 생성자 오버라이딩
    super(name, year);
    this.subject = subject;
  }

  sayHello() {
    // 메서드 오버라이딩
    return `${super.sayHello()} ${this.subject}를 맡고 있습니다.`;
  }
}

// 인스턴스 생성
const mia = new FemaleProfessor("미아", 2023, "math");
console.log(mia); // FemaleProfessor { name: '미아', year: 2023, subject: 'math' }
console.log(mia.sayHello()); // 안녕하세요 미아입니다. math를 맡고 있습니다.

const amelia = new Professor("아멜리아", 2022, "art");
console.log(amelia); // Professor { name: '아멜리아', year: 2022 }
console.log(amelia.sayHello()); // 안녕하세요 아멜리아입니다.
```
