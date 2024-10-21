## <mark color="#fbc956">Type Conversion (형 변환)</mark>

### 1. Type Conversion (형 변환)

- 함수와 연산자에 전달되는 값이 적절한 자료형으로 자동 변환되는 과정

### 1. String type으로 변환

- **명시적 : `toString(value)`**
  ```jsx
  let age = 20;

  let stringAge = age.toString();
  console.log(typeof stringAge, stringAge); // string, 20

  console.log(typeof (99).toString(), (99).toString()); // string 99
  console.log(typeof true.toString(), true.toString()); // string true
  console.log(typeof Infinity.toString(), Infinity.toString()); // string Infinity
  ```
- **암묵적**
  ```jsx
  let test = age + "";
  console.log(typeof test); // string

  console.log("98" + 2); // 982
  console.log("98" * 2); // 196
  console.log("98" - 2); // 96
  ```

### 2. Number type으로 변환

- `Number(value)`
- 수학과 관련된 함수와 표현식에서 자동 발생
- 숫자 이외 글자가 있는 문자열을 숫자형으로 변환 시 `NaN`
  ```jsx
  console.log(typeof +"1", "1"); // number 1
  ```
- **정수로 변환 : `parseInt()`**
  ```jsx
  console.log(typeof parseInt("0.99"), paseInt("0.99")); // number 0
  ```
- **실수로 변환 : `parseFloat()`**
  ```jsx
  console.log(typeof parseFloat("0.99"), parseFloat("0.99")); // number 0.99
  ```
- **변환 규칙**
  - `undefined` → `NaN`
  - `null` → `0`
  - `true` / `false` → `1` / `0`
  - `string` → 문자열의 처음/끝 공백 제거
    - 공백 제거 후 남은 문자열 없는 경우 : `0`
    - 공백 제거 후 남은 문자열 있는 경우 문자열에서 숫자 읽음
    - 변환 실패 시 `NaN`

### 3. Boolean type으로 변환

- `Boolean(value)`
- `false` : `0` , 빈 문자열, `null` , `undefined` , `NaN`
- `true` : 위 이외의 값

```jsx
console.log(!"x"); // false
console.log(!!"x"); // true
console.log(!""); // true
console.log(!!""); // false

console.log(!!0); // false
console.log(!!"0"); // true

console.log(!!"false"); // true
console.log(!!false); // false

console.log(!!undefined); // false
console.log(!!null); // false

console.log(!![]); // true
console.log(!!{}); // true
```
