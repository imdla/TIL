## <mark color="#fbc956">All about objects</mark>

### 1. 객체 선언 방법

- **`object`를 생성해서 객체 생성 - 기본기`{}`**
  ```jsx
  const person = {
    name: "mia",
    year: 2023,
  };

  console.log(person); // { name: 'mia', year: 2023 }
  ```
- **`class`를 인스턴스화해서 생성 - `class`와 `OOP`**
  ```jsx
  class Professor {
    name;
    year;

    constructor(name, year) {
      this.name = name;
      this.year = year;
    }
  }

  console.log(new Professor("mia", 2023)); // Professor { name: 'mia', year: 2023 }
  ```
- **`function`을 사용해서 객체 생성 - 생성자 함수**
  ```jsx
  function PersonFunction(name, year) {
    this.name = name;
    this.year = year;
  }

  const mia = new PersonFunction("미아", 2023);
  console.log(mia); // PersonFunction { name: '미아', year: 2023 }
  ```
