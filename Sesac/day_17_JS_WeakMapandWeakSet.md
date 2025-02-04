## <mark color="#fbc956">WeakMap (위크 맵)</mark>

### 1. WeakMap

- 키-값 쌍의 모음
- 키 : 반드시 객체이거나 등록되지 않은 심볼
- 값 : 임의의 JavaScript 타입

### 2. WeakMap 구조

- `WeakMap` 의 키는 반드시 객체여야함

  ```jsx
  let weakMap = new WeakMap();

  let obj = {};

  // Correct
  weakMap.set(obj, "ok");

  // Wrong
  weakMap.set("test", "Hello");
  ```

- `WeakMap` 의 키로 사용된 객체를 참조하는 것이 아무것도 없다면 해당 객체는 메모리와 `WeakMap` 에서 자동으로 삭제됨

  ```jsx
  let john = { name: "John" };

  let weakMap = new WeakMap();
  weakMap.set(john, "...");

  john = null;
  ```

### 3. WeakMap의 메서드

- **`weakMap.get(key)`**
- **`weakMap.set(key, value)`**
- **`weakMap.delete(key)`**
- **`weakMap.has(key)`**

```jsx
const wm1 = new WeakMap();
const wm2 = new WeakMap();
const wm3 = new WeakMap();
const o1 = {};
const o2 = function () {};
const o3 = window;

// set
wm1.set(o1, 23);
wm1.set(o2, "Hello");
wm2.set(o1, o2);
wm2.set(o3, undefined);
wm2.set(wm1, wm2);

// get
wm1.get(o2); // 'Hello'
wm2.get(o2); // undefined
wm2.get(o3); // undefined

//has
wm1.has(o2); // true
wm2.has(o2); // false
wm2.has(o3); // true

wm3.set(o1, 23);
wm3.get(o1); // 23

// delete
wm1.has(o1); // true
wm1.delete(o1);
wm1.has(o1); // false
```

### 4. WeakMap의 사용 : 추가 데이터

- `WeakMap` : 부차적인 데이터 저장할 곳이 필요할 때 사용

  ```jsx
  let users = new Map();

  function countUsers(user) {
    let count = users.get(user) || 0;
    users.set(user, count + 1);
  }

  let john = { name: "John" };

  // users에 john 추가
  countUsers(john);

  // 필요없을 시 null로 덮음
  john = null;
  ```

  - 위의 예시는 필요없는 정보를 손수 지워야하며 `users`가 차지하는 메모리 공간이 커짐

- `WeakMap` 이용해 지정하면 수동으로 지워줄 필요 없음

  ```jsx
  let users = new WeakMap();

  function countUsers(user) {
    let count = users.get(user) || 0;
    users.set(user, count + 1);
  }
  ```

### 5. WeakMap의 사용 : 캐싱

- **캐싱(caching)**

  - 시간이 오래 걸리는 작업의 결과를 저장해서 연산 시간과 비용을 절약해주는 기법

  ```jsx
  let cache = new Map();

  function process(obj) {
    if (!cache.has(obj)) {
      let result = /* 오래 걸리는 작업 */ obj;

      cache.set(obj, result);
    }

    return cache.get(obj);
  }

  let obj = {
    /* 객체 */
  };

  let result1 = process(obj); // 함수 process 호출
  let result2 = process(obj); // 맵에 저장된 결과 가져옴

  obj = null; // 필요없을 시 null로 덮음

  console.log(cache.size); // 1
  ```

  - 위의 예시는 객체가 필요없어져도 cache를 직접 지워야 함

- `WeakMap` 이용해 지정하면 객체가 메모리에서 삭제되면, 캐시에 저장된 함수 결과 역시 메모리에서 자동으로 삭제됨

  ```jsx
  let cache = new WeakMap();

  function process(obj) {
    if (!cache.has(obj)) {
      let result = /* 오래 걸리는 작업 */ obj;

      cache.set(obj, result);
    }

    return cache.get(obj);
  }

  let obj = {
    /* 객체 */
  };

  let result1 = process(obj);
  let result2 = process(obj);

  obj = null; // 필요없을 시 null로 덮음
  ```

---

## <mark color="#fbc956">WeakSet (위크 셋)</mark>

### 1. WeakSet

- 객체만의 컬렉션
- 객체만 저장 가능
- 셋 안의 객체는 도달 가능할 때만 메모리에서 유지됨

### 2. WeakMap의 메서드

- **`weakSet.add(key)`**
- **`weakSet.has(key)`**
- **`weakSet.delete(key)`**

```jsx
let ws = new WeakSet();
let obj = {};
let obj2 = {};

// add
console.log(ws.add(obj));
console.log(ws.add(obj2));

// has
console.log(ws.has(obj)); // true
console.log(ws.has(obj2)); // true

// delete
console.log(ws.delete(obj)); // true
console.log(ws.has(obj)); // false
ws.has(window); // false
```

> `weakMap` 과 `weakSet` 은 반복 작업이 불가능함
>
> → 저장된 자료를 한 번에 얻는 것이 불가능
> → 객체와 함께 추가 데이터를 저장하는 용도로 사용 가능
