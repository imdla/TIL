## <mark color="#fbc956">Number (숫자)</mark>

### 1. 숫자의 종류

- **정수(int)** : 분수가 없는 부동 소수점 숫자
- **부동 소수점 숫자(floats)** : 소수점과 소수 자릿수
- **doubles** : 부동 소수점 숫자보다 정밀도 높은 특정 유형의 부동 소수점 숫자

### 2. 숫자 체계

- **2진수** : 10진수를 0과 1을 이용해 나타내는 데이터 타입
- **8진수** : 10진수를 0부터 7까지의 수를 이용해 나타내는 데이터 타입
- **16진수** : 10진수를 0부터 15까지의 수(1~10, A~F)를 이용해 나타내는 데이터 타입

### 3. 숫자 타입으로 변환

- **`number()`**
  : 문자열을 숫자 타입으로 변환

---

## <mark color="#fbc956">Operators (연산자)</mark>

### 1. 산술 연산자

: 수학적 계산 처리

- `+` : 덧셈
- `-` :뺄셈
- `*` : 곱셈
- `/` : 나눗셈
- `%` : 나머지
- `**` : 거듭제곱

```jsx
console.log(10 + 10); // 20
console.log(10 - 10); // 0
console.log(10 * 10); // 100
console.log(10 / 10); // 1
console.log(10 % 10); // 0
console.log(10 ** 3); // 1000
```

### 2. 증감 연산자

- **`++`** : 증가 연산자
- **`--`** : 감소 연산자
- 반복해서 숫자 변수의 값을 1만큼 더하거나 뺌

```jsx
let number = 1;

number++;
console.log(number); // 2

number--;
console.log(number); // 1
```

### 3. 연산자의 위치

- 변수 앞 연산자 사용 → 브라우저가 변수를 먼저 증가/감소 시키고 값 반환
  ```jsx
  let result = 1;
  console.log(result); // 1

  result = number++;
  console.log(result, number); // 1, 2

  result = number--;
  console.log(result, number); // 2, 1

  result = ++number;
  console.log(result, number); // 2, 2

  result = --number;
  console.log(result, number); // 1, 1
  ```
- 숫자가 아닌 타입에 +, - 사용할 경우?
  ```jsx
  let sample = "99";

  console.log(+sample); // 99
  console.log(typeof +sample); // number

  console.log(sample); // 99
  console.log(typeof sample); // string

  sample = true;
  console.log(+sample); // 1
  console.log(typeof +sample); // number

  sample = false;
  console.log(+sample); // 0
  console.log(typeof +sample); // number

  sample = "빨강";
  console.log(+sample); // NaN -> Not a number

  sample = "99";
  console.log(-sample); // -99
  console.log(typeof -sample); // number
  ```

### 4. 할당 연산자 (assignment operator)

: 값을 변수에 할당

- **`=`** : 변수 오른쪽에 있는 값을 할당
- **복합 할당 연산자**
  - **`+=`** : 오른쪽 값을 왼쪽 변수 값에 `더하고` 새 변수 값 반환
  - **`-=`** : 오른쪽 값을 왼쪽 변수 값에 `빼고` 새 변수 값 반환
  - **`*=`** : 오른쪽의 값을 왼쪽 변수 값에 `곱하고` 새 변수 값 반환
  - **`/=`** : 오른쪽의 값을 왼쪽 변수 값에 `나누고` 새 변수 값 반환
  - **`%=`** : 오른쪽의 값을 왼쪽 변수 값에 `나머지`를 새 변수 값 반환

```jsx
let num = 100;
console.log(num); // 100

let num += 10;
console.log(num); // 110

let num -= 10;
console.log(num); // 100

let num *= 10;
console.log(num); // 1000

let num /= 10;
console.log(num); // 100

let num %= 10;
console.log(num); // 0
```

### 5. 비교 연산자

: 두 값의 크기나 동등성 비교

- **`==`** : 값이 동일한지
- **`!=`** : 값이 동일하지 않은지
- **`===`** : 일치 연산자 (값과 값의 데이터 유형이 동일한지)
- **`!==`** : 불일치 연산자
- **`<`** : ~보다 작음
- **`>`** : ~보다 큼
- **`<=`** : ~보다 작거나 같음
- **`>=`** : ~보다 크거나 같음

```jsx
console.log(5 == 5); // true
console.log(5 == "5"); // true
console.log(0 == ""); // true
console.log(true == 1); // true
console.log(false == 0); // true
console.log(true == "1"); // true

console.log(5 != 5); // false
console.log(5 != "5"); // false
console.log(0 != ""); // false
console.log(true != 1); // false
console.log(false != 0); // false
console.log(true != "1"); // false
```

```jsx
console.log(100 > 1); // true
console.log(100 < 1); // false
console.log(100 <= 1); // false
console.log(100 >= 1); // true
```

- 값과 타입의 비교
  ```jsx
  console.log(5 === 5); // true
  console.log(5 === "5"); // false
  console.log(0 === ""); // false
  console.log(true === 1); // false
  console.log(false === 0); // false
  console.log(true === "1"); // false

  console.log(5 !== 5); // false
  console.log(5 !== "5"); // true
  console.log(0 !== ""); // true
  console.log(true !== 1); // true
  console.log(false !== 0); // true
  console.log(true !== "1"); // true
  ```

### 6. 삼항 조건 연산자 (ternary operator)

- **`{condition} ? {true value} : {false value}`**
  ```jsx
  console.log(10 > 0 ? "10이 0보다 크다" : "10이 0보다 작다");
  // 10이 0보다 크다
  ```

### 7. 논리 연산자

: 논리적인 참/거짓 계산

- `&&` (and) : 모두 `true`여야 `true` 반환
  ```jsx
  console.log(true && true); // true
  console.log(true && false); // false
  console.log(false && true); // false
  console.log(false && false); // false
  ```
- `||` (or) : 하나만 `true`여도 `true` 반환
  ```jsx
  console.log(true || true); // true
  console.log(true || false); // true
  console.log(false || true); // true
  console.log(false || false); // false
  ```
- **단축 평가 (short circuit evaluation)**
  - **`&&`를 사용**
    - 좌측 true → 우측 값 반환
    - 좌측 false → 좌측 값 반환
  - **`||`를 사용**
    - 좌측 true → 좌측 값 반환
    - 좌측 false → 우측 값 반환
  ```jsx
  console.log(true || "빨강"); // true
  console.log(false || "빨강"); // 빨강
  console.log(false && "빨강"); // false
  console.log(true && "빨강"); // 빨강

  console.log(true && true && "빨강"); // 빨강
  console.log(true && false && "빨강"); // false
  ```

### 8. null 연산자

- `{변수} **??** {변수가 지정되지 않은 값}` 일 때 반환 값
  ```jsx
  let name;
  console.log(name); // undefined

  name = name ?? "이름";
  console.log(name); // 이름

  name = name ?? "검정";
  console.log(name); // 이름

  let name2;
  name2 ??= "이름";
  console.log(name); // 이름
  ```
