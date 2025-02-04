## <mark color="#fbc956">Array</mark>

> 💡 **배열?**
>
> 보통 리스트에 저장된 다수의 값들을 포함하고 있는 하나의 객체

### 1. 배열 생성

- 배열은 대괄호(`[]`)로 구성되며 쉼표(`,`)로 구분 된 항목들 포함

```jsx
var shopping = ["bread", "milk", "cheese", "hummus", "noodles"];
var sequence = [1, 1, 2, 3, 5, 8, 13];
var random = ["tree", 795, [0, 1, 2]];
```

### 2. 배열 항목 접근과 수정

- **괄호 표기법 사용**

1. **배열의 개별 항목 접근 : `변수명[인덱스]`**

   ```jsx
   shopping[0];
   // returns "bread"
   ```

2. **배열의 항목 수정 : 변수 : `변수명[인덱스] = "수정값"`**

   ```jsx
   shopping[0] = "tahini";
   shopping;
   // shopping will now return [ "tahini", "milk", "cheese", "hummus", "noodles" ]
   ```

3. **다중배열(multidimensional array)** : **`변수명[인덱스][인덱스]`**

   - 배열 내부의 배열

   ```jsx
   random[2][2];
   ```

### 3. 배열의 갯수 조회

- `length` : 배열의 길이 반환
  ```jsx
  sequence.length;
  // should return 7
  ```

---

## <mark color="#fbc956">배열 메서드 (Array Functions)</mark>

### 1. 직접 변경

- **`push()`**
  - 맨 끝에 아이템 추가 (직접 변경)
  - 반환값 : 추가 후 길이 반환
  ```jsx
  let colorGroup = ["red", "orange", "yellow", "green", "blue", "purple"];

  console.log(colorGroup.push("black")); // 7
  console.log(colorGroup); // ['red', 'orange', 'yellow', 'green', 'blue', 'purple', 'black']
  ```
- **`pop()`**
  - 맨 끝에 아이템 삭제 (직접 변경)
  - 반환값 : 삭제한 값 반환
  ```jsx
  console.log(colorGroup.pop()); // black
  console.log(colorGroup); // ['red', 'orange', 'yellow', 'green', 'blue', 'purple']
  ```
- **`shift()`**
  - 첫번째 아이템 삭제 (직접 변경)
  - 반환값 : 삭제한 값 반환
  ```jsx
  console.log(colorGroup.shift()); // red
  console.log(colorGroup); // ['orange', 'yellow', 'green', 'blue', 'purple']
  ```
- **`unshift()`**
  - 첫번째 아이템 추가 (직접 변경)
  - 반환값 : 추가한 다음 길이 반환
  ```jsx
  console.log(colorGroup.unshift("red")); // 6
  console.log(colorGroup); // ['red', 'orange', 'yellow', 'green', 'blue', 'purple']
  ```
- **`splice(시작할 인덱스, 삭제할 갯수)`**
  - 선택한 아이템 삭제 (직접 변경)
  - 반환값 : 삭제한 값 반환
  ```jsx
  console.log(colorGroup.splice(0, 3)); // ['red', 'orange', 'yellow']
  console.log(colorGroup); // ['green', 'blue', 'purple']
  ```

### 2. 간접 변경

- **`concat()`**
  - 맨 끝에 아이템 추가 (새로운 Array 만들어서 반환)
  - 반환값 : 추가한 새로운 Array 반환
  ```jsx
  let colorGroup = [red, orange, yellow, green, blue, purple];

  console.log(colorGroup.concat("black")); // ['red', 'orange', 'yellow', 'green', 'blue', 'purple', 'black']
  console.log(colorGroup); // ['red', 'orange', 'yellow', 'green', 'blue', 'purple']
  ```
- **`slice(시작 인덱스, 끝 번호 인덱스 + 1)`**
  - 선택한 아이템 삭제 (새로운 Array 만들어서 반환)
  - 반환값 : 삭제한 새로운 Array 반환
  ```jsx
  console.log(colorGroup.slice(0, 3)); // ['red', 'orange', 'yellow']
  console.log(colorGroup); // ['red', 'orange', 'yellow', 'green', 'blue', 'purple']
  ```

### 3. 변환 및 정렬

- **`join()`**
  - 모든 값들을 `string`으로 변환
  ```jsx
  console.log(colorGroup.join()); // red,orange,yellow,green,blue,purple
  console.log(colorGroup.join("/")); // red/orange/yellow/green/blue/purple
  console.log(colorGroup.join(", ")); // red, orange, yellow, green, blue, purple
  ```
- **`split()`**
  - 모든 값들을 `array`로 변환
  ```jsx
  console.log(colorGroup.split()); // ['red', 'orange', 'yellow', 'green', 'blue', 'purple']
  ```
- **`sort()`**
  - 오름차순 정렬 (직접 변경)
  ```jsx
  console.log(colorGroup.sort()); // ['blue', 'green', 'orange', 'purple', 'red', 'yellow']
  ```
  - **sort() 지정하기 - a, b 를 비교했을 때**
    - a를 b보다 나중에 정렬(뒤에 두려면) → 0보다 큰 숫자 반환
    - a를 b보다 먼저 정렬(앞에 두려면) → 0보다 작은 숫자 반환
    - 원래 순서를 그대로 → 0 반환
    ```jsx
    let numbers = [1, 9, 7, 5, 3];

    numbers.sort((a, b) => {
      return a > b ? 1 : -1;
    });
    console.log(numbers); // [1, 3, 5 ,7, 9]

    numbers.sort((a, b) => (a > b ? -1 : 1));
    console.log(numbers); // [9, 7, 5, 3, 1]
    ```
- **`reverse()`**
  - 반대로 정렬 (직접 변경)
  ```jsx
  console.log(colorGroup.reverse()); // ['yellow', 'red', 'purple', 'orange', 'green', 'blue']
  ```
- **`map()`**
  - `Array`에 있는 값들을 순회하며 각각의 값들을 변형
  - 반환값 ⇒ 새로운 Array 반환
  ```jsx
  console.log(colorGroup.map((x) => x));
  // ['yellow', 'red', 'purple', 'orange', 'green', 'blue']
  console.log(colorGroup.map((x) => `색상: ${x}`));
  // ['색상: yellow', '색상: red', '색상: purple', '색상: orange', '색상: green', '색상: blue']

  // 응용
  console.log(
    colorGroup.map((x) => {
      if (x === "red") {
        return `붉은색: ${x}`;
      } else {
        return x;
      }
    })
  );
  // [ 'yellow', '붉은색: red', 'purple', 'orange', 'green', 'blue' ]
  console.log(colorGroup);
  // [ 'yellow', 'red', 'purple', 'orange', 'green', 'blue' ]
  ```

### 4. 조회

- **`indexOf(찾을 값, 시작할 인덱스)`**
  - 시작할 인덱스부터 값을 찾음
    - 요소 발견 : 해당 요소의 인덱스 반환
    - 요소 미발견 : -1 반환
  ```jsx
  console.log(colorGroup.indexOf("red")); // 1
  console.log(colorGroup.indexOf("white")); // -1
  ```
- **`lastIndexOf(찾을 값, 시작할 인덱스)`**
  - 시작할 인덱스부터 값을 찾음, 검색을 끝에서부터 시작함
    - 요소 발견 : 해당 요소의 인덱스 반환
    - 요소 미발견 : -1 반환
  ```jsx
  console.log(colorGroup.lastIndexOf("red")); // 1
  console.log(colorGroup.lastIndexOf("white")); // -1
  ```
- **`inclues(찾을 값, 시작할 인덱스)`**
  - 시작할 인덱스부터 값을 찾음, 검색을 끝에서부터 시작함
    - 요소 발견 : `true` 반환
    - 요소 미발견 : `fasle` 반환
  ```jsx
  console.log(colorGroup.includes("red")); // true
  ```
- **`filter()`**
  - `Array`의 모든 값들을 순회하며 `true`면 반환값 속함 / `false`면 반환값에 속하지 않음
  - 반환값 : 새로운 `Array` 반환
  ```jsx
  numbers = [1, 8, 7, 6, 3];
  console.log(numbers.filter((x) => x % 2 === 0)); // [8, 6]
  ```
- **`find()`**
  - `Array`의 모든 값들을 순회하며 `true`인 첫번째 값만 반환
  - 반환값 : 삭제한 새로운 `Array` 반환
  ```jsx
  console.log(numbers.find((x) => x % 2 === 0)); // 8
  ```
- **`findIndex()`**
  - `Array`의 모든 값들을 순회하며 `true`인 인덱스 값 반환
  - 반환값 : 삭제한 새로운 `Array` 반환
  ```jsx
  console.log(numbers.findIndex((x) => x % 2 === 0)); // 1
  ```
- **`reduce(콜백 함수, 초기값)`**
  - `Array`의 모든 값들을 순회하며 함수를 실행
  - 반환값 : 삭제한 새로운 `Array` 반환
  ```jsx
  console.log(numbers.reduce((p, n) => p + n, 0)); // 25
  ```

### 5. array 비교

- **spread operator**
  - ‘`…`’ 통해 리스트를 펼쳐 넣을 수 있음
  ```jsx
  let colorGroup2 = [...colorGroup];
  console.log(colorGroup2); // ['red', 'orange', 'yellow', 'green', 'blue', 'purple']
  ```
  - `‘…’ 없을때`, 배열 안에 배열 넣음
  ```jsx
  let colorGroup3 = [colorGroup];
  console.log(colorGroup3); // [ ['red', 'orange', 'yellow', 'green', 'blue', 'purple'] ]
  ```
  - 바로 할당
  ```jsx
  let colorGroup4 = colorGroup;

  console.log(colorGroup4); // ['red', 'orange', 'yellow', 'green', 'blue', 'purple']
  console.log(colorGroup4 === colorGroup); // true

  console.log([...colorGroup] === colorGroup); // false
  ```

### 6. 배열 여부 판단

- **`Array.isArray(arr)` :** `arr`이 배열인지 여부 판단
  ```jsx
  // typeOf 로는 일반 객체와 배열 구분 불가
  console.log(Array.isArray({})); // false
  console.log(Array.isArray([])); // true
  ```
