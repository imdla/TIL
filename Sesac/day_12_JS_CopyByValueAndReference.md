## <mark color="#fbc956">Copy by Value and Reference</mark>

### 1. Copy by Value and Reference

> 💡 **copy by value ?** 값에 의한 전달
> 💡 **copy by reference ?** 참조에 의한 전달

- `primitive value` → copy by value
- `object` → copy by reference

### 2. copy by value

```jsx
let original = "안녕하세요";
let clone = original;

console.log(original); // 안녕하세요
console.log(clone); // 안녕하세요

clone += " 예시입니다.";
console.log(original); // 안녕하세요
console.log(clone); // 안녕하세요 예시입니다.

console.log(original === clone); // false
// => 값 전달로 original과 clone은 같지 않음
```

### 3. copy by reference

```jsx
let originalObj = {
  name: "빨강",
  group: "색상",
};
let cloneObj = originalObj;

console.log(originalObj); // { name: '빨강', group: '색상' }
console.log(cloneObj); // { name: '빨강', group: '색상' }

originalObj["group"] = "color";
console.log(originalObj); // { name: '빨강', group: 'color' }
console.log(cloneObj); // { name: '빨강', group: 'color' }

console.log(originalObj === cloneObj); // true
// => 참조 전달로 originalObj와 cloneObj는 같음

// 다른 예시
let originalObj2 = {
  name: "black",
  group: "color",
};
let cloneObj2 = {
  name: "black",
  group: "color",
};

console.log(originalObj2 === cloneObj2); // false
// => 참조 전달로 originalObj2와 cloneObj2는 각각 다른 참조 가짐 => 다름
```

### 4. Spread Operator (펼침 연산자)

- `…`
- 배열이나 객체의 내용을 펼쳐 새로운 배열, 객체 생성 시 사용
- **copy by value**
  ```jsx
  const blue = {
    name: "파랑",
    group: "색상",
  };
  const blue2 = {
    ...blue,
  };
  console.log(blue2); // { name: '파랑', group: '색상' }
  console.log(blue2 === blue); // false
  // => blue2는 blue의 spread operator 값을 가짐 => 다름
  ```
- **값 추가**
  ```jsx
  const blue3 = {
    number: 1,
    ...blue,
  };
  console.log(blue3); // { number: 1, name: '파랑', group: '색상' }
  // spread operator에 다른 값 추가 가능
  ```
- **위치 중요**
  ```jsx
  const blue4 = {
    name: "파란색",
    ...blue,
  };
  console.log(blue4); // { name: '파랑', group: '색상' }

  const blue5 = {
    ...blue,
    name: "파란색",
  };
  console.log(blue5); // { name: '파란색', group: '색상' }
  // blue에 이미 'name' key가 있기에 spread operator 전에 name 변경 시 적용 안됨,
  // spread operator 이후 name 변경 시 적용됨

  // 다른 예시
  const numbers = [1, 3, 5];
  const numbers2 = [10, ...numbers];
  console.log(numbers2); // [ 10, 1, 3, 5 ]

  const numbers3 = [...numbers, 10];
  console.log(numbers3); // [ 1, 3, 5, 10 ]
  ```
- **중첩된 배열의 copy by value**
  - 일반 배열의 펼침 연산자 사용 → 값에 의한 전달 (얕은 복사)
  ```jsx
  let num_1 = [1, 2, 3];
  let num_2 = num_1;
  let num_3 = [...num_1];

  num_1[0] = 10;

  console.log(num_1); // [10, 2, 3]
  console.log(num_2); // [10, 2, 3]
  console.log(num_3); // [1, 2, 3]
  ```
  ```jsx
  let num_1 = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9],
  ];
  let num_2 = num_1;
  let num_3 = [...num_1];

  num_1[0] = 10;

  console.log(num_1); // [ 10, [ 4, 5, 6 ], [ 7, 8, 9 ] ]
  console.log(num_2); // [ 10, [ 4, 5, 6 ], [ 7, 8, 9 ] ]
  console.log(num_3); // [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ]
  ```
  - 중첩 배열의 펼침 연산자 사용 → 참조에 의한 전달 (깊은 복사)
  ```jsx
  let num_1 = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9],
  ];
  let num_2 = num_1;
  let num_3 = [...num_1];

  num_1[0][0] = 10;

  console.log(num_1); // [ [ 10, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ]
  console.log(num_2); // [ [ 10, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ]
  console.log(num_3); // [ [ 10, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ]
  ```
