## <mark color="#fbc956">Loop (반복문)</mark>

### 1. 반복문

- 특정 코드 블록을 반복적으로 실행하는 구문
- 조건이나 컬렉션이 따라 반복 제어 가능
- 코드의 재사용성 높이고 가독성 향상

### 2. 반복문 유형

- **조건의해 반복 결정되는 반복문**
  - `for` 반복문
  - `while` 반복문
  - `do...while` 반복문
- **반복 가능 자료형(Enumerable, Iterable) 대한 반복문**
  - `for...in` 반복문
  - `for...of` 반복문

### 3. 반복의 기능

- **초기화**
  - 어떤 값으로 초기화, 반복문의 시작점
  - 반복문 실행 시 한 번 실행
- **조건식**
  - 반복 조건 작성
  - 반복문이 계속 실행될지, 혹은 멈출지를 결정하는 `true`, `false` 테스트
- **증감식**
  - 코드 블록 반복 끝날 때 마다 실행
  - 조건이 더 이상 `true`가 아닐 때까지 각각의 연이은 반복에서 조금씩 카운터를 변화시킴

### 4. for 반복문

- 초기화, 조건식, 증감식으로 구성
- 반복문의해 실행될 코드 블록은 중괄호 `{}` 로 구분
  ```jsx
  for (초기화; 조건식; 증감식) {
    // 실행할 코드
  }
  ```
  ```jsx
  for (let i = 0; i < 5; i++) {
    console.log(i);
  }
  /**
   * 0
   * 1
   * 2
   * 3
   * 4
   */
  ```
- 중첩된 for 반복문
  ```jsx
  for (let i = 0; i < 3; i++) {
    for (let j = 3; j > 0; j--) {
      console.log(i, j);
    }
  }
  /**
   * 0, 3
   * 0, 2
   * 0, 1
   * 1, 3
   * 1, 2
   * 1, 1
   * 2, 3
   * 2, 2
   * 2, 3
   */
  ```

### 5. for…in 반복문

- 열거 가능한 자료형(Enumerable)인 객체(`Object`) 순회 시 사용
- 객체의 키(`Key`) 순회

```jsx
const dog = {
	name: 'doggy',
	age: 5,
	color: 'black';
}

for (let key in dog) {
	console.log(key);
}

/**
* name
* age
* color
*/
```

```jsx
const color = ["빨강", "주황", "노랑", "초록", "파랑"];

for (let key in color) {
  console.log(key);
}

/**
 * 0
 * 1
 * 2
 * 3
 * 4
 */

for (let key in color) {
  console.log(`${key}:${color[key]}`);
}

/**
 * 0:빨강
 * 1:주황
 * 2:노랑
 * 3:초록
 * 4:파랑
 */
```

### 6. for…of 반복문

- 반복 가능한 자료형(Iterable)의 값(`Value`)을 순회 시 사용
- 배열, 문자열, 집합에서 사용 가능

```jsx
const color = ["빨강", "주황", "노랑", "초록", "파랑"];

for (let value of color) {
  console.log(value);
}

/**
 * 빨강
 * 주황
 * 노랑
 * 초록
 * 파랑
 */
```

### 7. while 반복문

- 반복 횟수가 확실하지 않을 때 사용
- 조건식이 참인 동안 코드 블록 반복 실행

```jsx
초기화;
while (조건식) {
  // 실행할 코드

  증감식;
}
```

```jsx
let number = 0;

while (number < 10) {
  number++;
}

console.log(number); // 10
```

### 8. do … while 반복문

- 코드 블록을 최소 한 번 실행 후 조건식 평가
- 조건 상관없이 한 번은 실행되어야 할 때 사용

```jsx
초기화;
do {
  // 실행할 코드

  증감식;
} while (조건식);
```

```jsx
let number = 0;

do {
  number++;
} while (number < 10);

console.log(number); // 10
```

### 9. 반복문 종료 - break

- 반복문을 즉시 종료

```jsx
for (let i = 0; i < 10; i++) {
  if (i === 5) {
    break;
  }
  console.log(i);
}

/**
 * 0
 * 1
 * 2
 * 3
 * 4
 */
```

```jsx
let number = 0;
while (number < 10) {
  if (number === 5) {
    break;
  }

  number++;
  console.log(number);
}

/**
 * 1
 * 2
 * 3
 * 4
 * 5
 */
```

### 10. 반복 건너뛰기 - continue

- 현재 반복 건너뛰고 다음 반복으로 넘어감

```jsx
for (let i = 0; i < 10; i++) {
  if (i === 5) {
    continue;
  }
  console.log(i);
}

/**
 * 0
 * 1
 * 2
 * 3
 * 4
 * 6
 * 7
 * 8
 * 9
 */
```

```jsx
let number = 0;
while (number < 10) {
  number++;

  if (number === 5) {
    break;
  }

  console.log(number);
}

/**
 * 1
 * 2
 * 3
 * 4
 * 6
 * 7
 * 8
 * 9
 * 10
 */
```
