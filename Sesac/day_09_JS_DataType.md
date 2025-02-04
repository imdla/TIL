## <mark color="#fbc956">Data Type (자료형)</mark>

### 1. Data Type (자료형)

- 데이터가 저장할 수 있는 데이터의 종류
- 생성된 데이터 유형에 따라 각각 다른 특징 가지며, 수행 동작 달라짐

### 2. 자료형 종류

- **자료형 종류 :** 변수에 저장할 수 있는 데이터 유형
  - **Primitive Type (원시 자료형) - 7가지**
    - 값 자체가 저장되는 변경(수정) 불가능한 데이터 타입
    - `Number`, `String`, `Boolean`, `undefined`, `null`, `Symbol`
  - **Reference Types (참조 자료형)**
    - 메모리의 주소를 참조해 해당 데이터 관리하는 자료형
    - 변경 가능(Mutable, 가변성)하며, 메모리 주소를 통해 값 접근
    - `Function`, `Array`, `Object`

### 3. 지정되지 않은 타입

- 다른 언어와 달리 변수에 포함 할 데이터의 유형을 지정할 필요가 없음
- **static typing**
  ⇒ 변수를 선언 시 어떤 타입의 변수를 선언할지 명시 (ex. C)
- **dynamic typing**
  ⇒ 변수의 타입을 명시적으로 선언하지 않고 값에 의해 타입을 추론 (ex. JS)

---

## <mark color="#fbc956">**Primitive Type (원시 자료형)**</mark>

### 1. Number (숫자)

- **`number`**
  - 정수와 실수를 포함하는 숫자 데이터
  - 정수, 소수, 지수 표기법 지원
  - 특수 값
    - `NaN` (Not a Number)
    - `Infinity` (무한대)
  ```jsx
  const myAge = 20;
  console.log(typeof myAge); // 17
  ```

### 2. String (문자열)

- **`string`**
  - 텍스트 데이터
  - 변수에 문자열 값을 대입 시, 작은따옴표(`'`)나 큰따옴표(`"`)로 묶음
    - 문자열 선언의 시작과 끝에 동일한 형식 사용
  ```jsx
  var dolphinGoodbye = "So long and thanks for all the fish";
  ```
- **Template Literal** ⇒ 백틱(```) 문자 사용
  ```jsx
  const myColor = `빨강,
  노랑
  초록`;

  console.log(myColor);
  /*
   * 빨강
   * 노랑
   * 초록
   */
  ```

### 3. Booleans (불리언)

- **`booleans`**
  - `true` (참) / `false` (거짓) 라는 값 표현하는 데이터 유형
  - 조건문에서 논리 연산 수행 시 사용
  ```jsx
  var iAmAlive = true;
  console.log(iAmAlive); // boolean

  var test = 6 < 3;
  console.log(test); // false
  ```

### 4. undefined (언디파인드)

- **`undefined`**
  - 값이 할당되지 않은 변수에 자동 부여
  - 사용자가 직접 값을 초기화하지 않았을 때 지정되는 값
  ```jsx
  let noInit;
  console.log(noInit); // undefined
  console.log(typeof noInit); // undefined
  ```

### 5. null (널)

- **`null`**
  - 명시적으로 값이 없음을 표현
  - `undefined`와 다르게 개발자가 명시적으로 없는 값으로 초기화 할 때 사용
  ```jsx
  let init = null;
  console.log(init); // null
  console.log(typeof init); // object
  ```

### 6. Bigint (빅인트)

- `bigint`
  - 매우 큰 정수 나타내는 자료형, 정수의 크기에 제한이 없음
  - 숫자 끝에 `n` 붙여 생성
  ```jsx
  let bigNum = 123456789123456789123456789123456789n;
  ```

### 7. Symbol (심볼)

- **`symbol`**
  - 유일무이한 값을 생성할 때 사용
  - 주로 객체 키(Key)로 사용되며, 충돌 없이 고유한 식별자 생성
  - 다른 primitive 값들과 다르게 `Symbol()` 함수를 호출해서 사용
  ```jsx
  const test1 = "1";
  const test2 = "1";
  console.log(test1 === test2); // true

  const symbol1 = Symbol("1");
  const symbol2 = Symbol("1");
  console.log(symbol1 === symbol2); // false
  ```

---

## <mark color="#fbc956">**Reference Types (참조 자료형)**</mark>

### 1. Object (객체)

- **`object`**
  - 객체(Objects)는 실제 사물(real life object)을 모델링 하는 코드 구조
  - `key : value` 의 쌍을가진 속성(property)으로 이루어진 자료형
  ```jsx
  var color = { red: "빨간색", orange: "주황색", yellow: "노란색" };
  ```
- **객체 데이터 검색 : `object.key` / `object[’key’]`**
  - 키(key) 통해 접근 가능
  ```jsx
  console.log(color);
  // {red: "빨간색", orange: "주황색", yellow: "노란색"}
  console.log(color["red"]); // 빨간색
  console.log(color.red); // 빨간색
  console.log(typeof color); // object
  ```

### 2. Array (배열)

- **`array`**
  - 0개 이상의 값을 순차적으로 저장, 추가와 제거 자유로움
  - 대괄호(`[]`)로 묶고 쉼표로 구분 된 여러 값(요소)을 포함하는 단일 객체
  - 자료형 구분없이 값 저장 가능
  ```jsx
  var myNameArray = ["Chris", "Bob", "Jim"];
  console.log(myNameArray); // ["Chris", "Bob", "Jim"]

  var myNumberArray = [10, 15, 40];
  console.log(myNumberArray); // [10, 15, 40]
  ```
- **개별 값 접근 : `array[index]`**
  - 각 원소의 위치 나타내는 인덱스(index) 활용
  - 인덱스는 0부터 시작, 대괄호 사용해 원소에 접근 가능
  ```jsx
  myNameArray[0]; // Chris
  myNumberArray[2]; // 40
  ```
- **개별 값 변경**
  ```jsx
  myNameArray[0] = "Jhon";
  console.log(myNameArray); // ["Jhon", "Bob", "Jim"]
  console.log(typeof myNameArray); // object
  ```

### 3. Set (집합)

- 중복되지 않는 값들의 모음을 저장하는 자료형
- 중복 값은 제서, 값은 순서는 보장되지 않음

```jsx
let uniqueVal = new Set([1, 2, 3, 4, 5]);
console.log(uniqueVal); // Set {1, 2, 3, 4, 5}
```
