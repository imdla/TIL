## <mark color="#fbc956">Function (함수)</mark>

### 1. Function

- 특정 작업 수행 위한 재사용 가능한 코드 블록
- 입력 받아 → 출력 반환
- 프로그램을 구조적이고 관리 용이하게 만듦, 코드의 중복 줄이고 유지보수성 높임
- **정의(define)** : 함수 내부 코드 작성
- **호출(call)** : 정의한 함수 실행

### 2. 함수 구조

- **`function` 키워드**
- **함수 이름**
- **매개변수(Parameter)**
  - **함수가 필요로 하는 입력값**
  - 매개변수에 기본값 설정 가능
  - 여러 개의 인수를 배열로 받을 수 있음
- **함수 몸체(Body)**
- `return` 문 : 함수의 결과 반환
- **인자(Argument)** : 함수 호출 시 실제 전달 값

```jsx
// 정의
function 함수이름(매개변수) {
  // 실행할 코드
  return 반환값;
}

// 호출
함수이름(인자);
```

### 3. 함수 대 메소드

> 💡 **메서드(method)?**
>
> 객체(object)의 내부에 정의된 함수
> 메서드는 객체의 동작을 추가하는 역할

### 4. 함수 스코프와 충돌

> 💡 **스코프 (scope)?**
>
> 함수를 생성할 때, 변수 및 함수 내 정의된 코드들은 그들만의 분리된 스코프 안에 자리함

- **전역 스코프(global scope)** : 함수 바깥에 선언된 가장 상위 레벨의 스코프, 전역 스코프 내에 정의된 값들은 어느 코드든 접근이 가능
- **함수 내부의 함수** : 함수를 어디에서나 호출할 수 있음, 크고 복잡한 함수를 가지고 있다면 몇몇의 하위 함수(sub-functions)로 나눔

### 5. 함수 반환

> 💡 **반환 값(return value)?**
>
> 함수가 완료되었을 때 함수가 반환하는 값

```jsx
function multiply(x, y) {
  return x * y;
}
const result1 = multiply(2, 4);
console.log(result1); // 8

const multiplyTwo = function (x, y) {
  return x * y;
};
console.log(multiplyTwo(4, 5)); // 20

const multiplyThree = function (x, y, z) {
  console.log(arguments); // [Arguments] { '0': 4, '1': 5, '2': 6 }
  return x * y * z;
};
console.log(multiplyThree(4, 5, 6)); // 120
```

---

## <mark color="#fbc956">함수 종류</mark>

### 1. 함수 선언식(Function Delcarations)

- `function` 키워드 사용해 함수 선언하는 방식
- 호이스팅의 영향 받아 선언 이전 호출 가능

```jsx
function multiply(x, y) {
  return x * y;
}

console.log(multiply(1, 2));
```

### 2. 함수 표현식(Function Expression)

- 함수를 변수에 할당하는 방식
- 함수의 이름은 선택적, 익명 함수로 사용 가능
- 호이스팅 되지 않으며, 선언 이후에만 호출 가능

```jsx
const multiply = function (x, y) {
  return x * y;
};

console.log(multiply(1, 2));
```

- **익명 함수**
  ```jsx
  function() {
    alert('hello');
  }
  ```

### 3. 화살표 함수 (Arrow Function)

- `function` 키워드 대신 화살표 (`⇒`) 사용

```jsx
// arrow 함수
const multiply2 = (x, y) => {
	return x * y;
}

console.log(multiply2(3, 4)); // 12

// 함수 내용 한 줄일 때 => 중괄호 없애기
const multiply3 = (x, y) => x * y;

console.log(multiply3(4, 5)); // 20

// 매개변수 한 개일 때 => 괄호 없애기
const multiply4 = x => x * 2;

console.log(multiply4(2)); // 4

// arrow 함수 응용 (multiply5 = multiply6)
const multiply5 = x => y => z => `x:${x} y:${y} z:${z}`;

console.log(multiply5(2)(5)(7)); // x:2 y:5 z:7

const multiply6(x){
	return function(y){
		return function(z){
			return `x:${x} y:${y} z:${z}`;
		}
	}
}

console.log(multiply6(3)(4)(5)); // x:3 y:4 z:5
```

### 4. 즉시 실행 함수 (Immediately Invoked Function)

```jsx
(function (x, y) {
  console.log(x * y);
})(4, 5);
// 20
```

### 5. 함수의 타입

```jsx
console.log(typeof multiply); // function

// 비교할 값 instanceof 비교 대상
console.log(multiply instanceof Object); // true
```
