## <mark color="#fbc956">Scope (스코프)</mark>

### 1. Scope

> 💡 **Scope ?**
>
> 현재 실행되는 컨텍스트

- **컨텍스트**
  - 값과 표현식이 표현되거나 참조될 수 있음
  - 만약 변수 또는 표현식이 해당 스코프 내에 있지 않다면 사용 불가
- **스코프**
  - 계층적인 구조를 가지고 있어 하위 스코프→ 상위 스코프에 접근 가능, 반대는 불가
  - 값이나 함수가 접근할 수 있는 범위, 변수의 생명 주기 결정
  - **스코프 종류**
    - 전역 스코프
    - 지역 스코프
      - 블록 스코프
      - 함수 스코프

---

## <mark color="#fbc956">스코프 종류</mark>

### 1. 전역 스코프 (Global Scope)

- 다른 모든 범위를 포함하고, 모든 범위에 접근 가능한 스코프
- 함수나 블록 내부에 선언되지 않은 변수, 프로그램 종료될 때까지 메모리에 유지

### 2. 지역 스코프 (Local Scope)

- 변수를 로컬로 만드는 변수의 특성, 변수 이름은 지역 범위가 아닌 범위 안에서 해당 값만 바인딩 됨

### **3. 블록 스코프(Block Scope)**

- 중괄호 `{}` 로 감싸진 영역
- `let` 과 `const` 로 선언된 변수는 블록 스코프 가짐
- 코드블록 `{…}` 안에서 선언한 변수는 블록 안에서만 사용 가능

  ```jsx
  {
    let sayHi = "안녕하세요";

    console.log(sayHi); // 안녕하세요
  }

  console.log(sayHi); // ReferenceError: sayHi is not defined
  ```

- `if`, `for`, `while` 등에서도 `{…}`안에서 선언한 변수는 오직 블록 안에서만 접근 가능

  ```jsx
  if (true) {
    let sayHi = "안녕하세요";

    console.log(sayHi); // 안녕하세요
  }

  console.log(sayHi); // ReferenceError: sayHi is not defined
  ```

> 💡 **var, let, const에서 코드 블록 ?**
>
> ⇒ \*\*\*\*`var` 키워드를 사용 할 때 새로운 스코프를 만들어 내지 않음
>
> (`var` 는 함수 스코프 가짐)
>
> ⇒ `let`, `const` 키워드를 사용할 때 블록레벨 스코프를 만들 수 있음

- **`var` 키워드 사용할 경우**

  ```jsx
  var i = 0;

  for (var i = 0; i < 10; i++) {
    console.log(i);
  }
  /**
  0
  1
  2
  3
  4
  5
  6
  7
  8
  9
  */
  console.log(`i in global scope : ${i}`); // i in global scope : 10
  ```

- **`let`, `const` 키워드 사용할 경우**

  ```jsx
  var i = 999;

  for (let i = 0; i < 10; i++) {
    console.log(i);
  }
  /**
  0
  1
  2
  3
  4
  5
  6
  7
  8
  9
  */
  console.log(`i in global scope : ${i}`); // i in global scope : 999
  ```

### 4. 함수 스코프(Function Scope)

- 함수 내부에서 선언된 변수는 함수 내부에서만 유효함

```jsx
function sayHi() {
  let name = "이름";
  console.log(`안녕하세요 ${name}`);
}

sayHi();

console.log(name); // ReferenceError
```

### 5. 스코프 체인 (Scope Chain)

- 스코프가 중첩된 경우 내부 스코프에서 외부 스코프로 범위 넓혀가며 변수 검색하는 구조
- 모든 선언은 가장 가까운 스코프에 있는 선언부터 활용

  ```jsx
  var number1 = 20;

  function level1() {
    console.log(number1);
  }

  level1(); // 20
  ```

  - 함수 외부와 내부 둘 다 변수 선언이 있을 때

    ```jsx
    var number1 = 20;
    function level1() {
      var number1 = 40;

      // 함수 내의 number1 값 사용
      console.log(number1);
    }

    // level1에서는 함수 내 nember1 값 사용
    level1(); // 40

    // number1은 함수 밖에서 실행되어 함수 밖 number1 값 사용
    console.log(number1); // 20
    ```

  - 중첩된 함수에서 선언이 있을 때

    ```jsx
    var number1 = 20;

    function level1() {
      var number1 = 40;

      function level2() {
        var number2 = 99;

        // 함수 level1의 number1 값 사용
        console.log(`level2 number1 : ${number1}`);
        // 함수 내의 number2 값 사용
        console.log(`level2 number2 : ${number2}`);
      }

      level2();
      // 함수 내의 number1 값 사용
      console.log(`level1 number1 : ${number1}`);
    }

    level1();
    /**
    level2 number1 : 40
    level2 number2 : 99
    level1 number1 : 40
    */

    // number1은 함수 밖에서 실행되어 함수 밖 number1 값 사용
    console.log(number1); // 20

    // number2는 함수 밖에서 실행되지만, 함수 밖 number2는 선언되어 있지 않음
    console.log(number2); // ReferenceError: number2 is not defined
    ```

### 6. Lexical Scope

> 💡 **Lexical Scope ?**
>
> JS에서 사용, 선언된 위치가 상위 스코프를 정함

> 💡 **Dynamic Scope?**
>
> Lexical Scope와 반대로, 실행한 위치가 상위 스코프를 정함

```jsx
var number1 = 3;

function function1() {
  var number1 = 3;

  function2();
}

// function2는 글로벌 스코프에서 값을 가져옴
function function2() {
  console.log(number1);
}

function1(); // 3
```
