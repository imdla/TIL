## <mark color="#fbc956">Map (맵)</mark>

### 1. Map

- 키가 있는 데이터를 저장 (객체와 유사), 키에 다양한 자료형을 허용

### 2. Map의 메서드와 프로퍼티

- **`new Map()`** : 맵 생성
- **`map.set(key, value)`** : `key` 를 이용해 `value` 를 저장
- **`map.get(key)`** : `key` 에 해당하는 값 반환, `key` 존재X → `undefined` 반환
- **`map.has(key)`** : `key` 존재O → `true` 반환 / `key` 존재X → `false` 반환
- **`map.delete(key)`** : `key`에 해당하는 값 제거
- **`map.clear()`** : 맵 안의 모든 요소 제거
- **`map.size`** : 요소의 개수 반환

```jsx
let map = new Map();

map.set("1", "str1");
map.set(1, "num1");
map.set(ture, "bool1");

// 맵은 키의 타입을 변환시키지 않고 그대로 유지함
console.log(map.get(1)); // num1
console.log(map.get("1")); // str1

console.log(map.size); // 3
```

### 3. 맵은 키를 객체로 허용

- 맵은 객체를 키로 사용할 수 있음

  ```jsx
  let john = { name: "John" };

  let users = new Map();
  users.set(john, 2023);

  console.log(users.get(john)); // 2023
  ```

- 객체에는 문자열 키를 사용할 수 있지만, 객체 키는 사용할 수 없음

  ```jsx
  let john = { name: "John" };

  let usersObj = {};
  usersObj[john] = 2023;

  // userObj는 객체로 모든 키를 문자형으로 변환시킴
  // => john은 문자형으로 변환됨 => "[object Object]"가 됨
  console.log(usersObj["[object Object]"]); // 2023
  ```

### 4. 맵의 요소에 반복 작업

- **`map.keys()`** : 각 요소의 키를 모은 반복 가능(interable)한 객체 반환
- **`map.values()`** : 각 요소의 값을 모은 이터러블 객체 반환
- **`map.entries()`** : 요소의 `[key, value]` 을 한 쌍으로 하는 이터러블 객체 반환

  ```jsx
  let professor = new Map([
    ["Mia", 2023],
    ["John", 2022],
    ["Michle", 2021],
  ]);

  for (let name of professor.keys()) {
    console.log(name); // Mia, John, Michle
  }

  for (let year of professor.values()) {
    console.log(year); // 2023, 2022, 2021
  }

  for (let entry of professor) {
    console.log(entry); // Mia,2023 ...
  }
  ```

- **맵의 `forEach`**
  ```jsx
  professor.forEach((value, key, map) => {
    console.log(`${key}: ${value}`); // Mia: 2023 ...
  });
  ```

> **맵은 삽입 순서 기억함**
>
> 맵은 값이 삽입된 순서대로 순회 실시 (↔ 객체는 프로퍼티 순서 기억하지 못함)

### 5. 객체를 맵으로 바꾸기

- 각 요소가 키와 값이 쌍인 배열이나 이터러블 객체를 초기화 용도로 `Map`에 전달해 새로운 `Map` 생성

  ```jsx
  // 각 요소가 [key, value] 쌍인 배열
  let map = new Map([
    ["1", "str1"],
    [1, "num1"],
    [true, "bool1"],
  ]);

  console.log(map.get(1)); // str1
  ```

- **`Object.entries(obj)`**

  - 객체를 맵으로 만들어 객체의 키-값 쌍을 요소 `[key, value]`로 가지는 배열 반환

  ```jsx
  let obj = {
    name: "John",
    year: 2023,
  };

  let map = new Map(Object.entries(obj));

  console.log(map.get("name")); // John
  ```

### 6. 맵을 객체로 바꾸기

- `Map`을 각 요소가 키와 값이 쌍인 배열이나 이터러블 객체로 만들 수 있음

  ```jsx
  let professor = Object.fromEntries([
    ["Mia", 2023],
    ["John", 2022],
    ["Michle", 2021],
  ]);

  console.log(professor); // { Mia: 2023, John: 2022, Michle: 2021 }
  console.log(professor.Mia); // 2023
  ```

- **`Object.fromEntries(map)`**

  - 맵을 객체로 바꿈, 각 요소가 `[key, value]` 쌍인 배열을 객체로 바꿈

  ```jsx
  let map = new Map();
  map.set("Mia", 2023);
  map.set("John", 2022);
  map.set("Michle", 2021);

  let obj = Object.fromEntries(map);
  console.log(obj); // { Mia: 2023, John: 2022, Michle: 2021 }
  ```

---

## <mark color="#fbc956">Set (집합)</mark>

### 1. Set

- 중복을 허용하지 않는 값을 모아놓은 특별한 컬렉션
- 셋에 키가 없는 값이 저장됨

### 2. 셋의 메서드

- **`new Set(iterable)`** : 셋 생성, interable 객체를 전달받으면 그 안의 값을 복사해 셋에 넣음
- **`set.add(value)`** : 값 추가하고 셋 자신 반환
- **`set.delete(value)`** : 값 제거
  - 호출 시점에 셋 내에 값이 있을 경우 제거 성공 → `true`, 아니면 → `false` 반환
- **`set.has(value)`** : 셋 내에 값이 존재할 경우 → `true`, 아니면 → `false` 반환
- **`set.clear()`** : 셋을 비움
- **`set.size`** : 셋에 몇 개의 값이 있는지 반환

```jsx
let set = new Set();

let john = { name: "John" };
let mia = { name: "Mia" };
let michle = { name: "Michle" };

set.add(john);
set.add(mia);
set.add(michle);
set.add(john);
set.add(mia);

console.log(set.size); // 3

for (let user of set) {
  console.log(user.name); // John, Mia, Michle
}
```

### 3. 셋의 값에 반복 작업

- `for..of` 나 `forEach` 통해 셋의 값을 대상으로 반복 작업 수행 가능

```jsx
let set = new Set(["John", "Mia", "Michle"]);

for (let name of set) console.log(name); // John, Mia, Michle

set.forEach((value, valueAgain, set) => {
  console.log(value); // John, Mia, Michle
});
```

- **셋의 반복 잡업위한 메서드**
  - **`set.keys()`** : 셋 내의 모든 값을 포함하는 이터러블 객체 반환
  - **`set.values()`** : `set.keys` 와 동일한 작업
  - **`set.entries()`** : 셋 내의 각 값을 이용해 만든 `[value, value]` 배열을 포함하는 이터러블 객체 반환
