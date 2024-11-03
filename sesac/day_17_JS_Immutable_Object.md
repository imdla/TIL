## <mark color="#fbc956">객체 수정을 막아주는 다양한 메서드</mark>

### 1. Extensible

- **`Object.isExtensible(obj)`**
  - 프로퍼티 추가 가능한 경우 → `true` 반환
  - 불가능한 경우 → `false` 반환
  ```jsx
  const mia = {
    name: "미아",
    year: 2000,

    get age() {
      return new Date().getFullYear() - this.year();
    },

    set age(age) {
      this.year = new Date().getFullYear() - age;
    },
  };

  // extensible 반환 값 확인
  console.log(Object.isExtensible(mia)); // true

  // 새로운 프로퍼티 추가 => 가능
  mia["subject"] = "math";
  console.log(mia); // { name: '미아', year: 2000, age: [Getter/Setter], subject: 'math' }
  ```
- **`Object.preventExtensions(obj)`** : 프로퍼티 추가 불가능 (`isExtensible`을 `false`로 변경)
  ```jsx
  // isExtensible을 false로 변경
  Object.preventExtensions(mia);
  console.log(Object.isExtensible(mia)); // false

  // 새로운 프로퍼티 추가 => 불가능
  mia["job"] = "교수";
  console.log(mia); // { name: '미아', year: 2000, age: [Getter/Setter], subject: 'math' }

  // 프로퍼티 삭제 => 가능
  delete mia["subject"];
  console.log(mia); // { name: '미아', year: 2000, age: [Getter/Setter] }
  ```

### 2. Seal

- **`Object.isSealed(obj)`**
  - 프로퍼티 추가 및 삭제 가능한 경우 → `false` 반환
  - 불가능한 경우 → `true` 반환
  - `configurable: flase` 를 설정하는 것과 동일
  ```jsx
  const mia2 = {
    name: "미아",
    year: 2000,

    get age() {
      return new Date().getFullYear() - this.year();
    },

    set age(age) {
      this.year = new Date().getFullYear() - age;
    },
  };

  // seal 반환 값 확인
  console.log(Object.isSealed(mia2)); // flase
  ```
- **`Object.seal(obj)`** : 프로퍼티 추가와 삭제 불가능 (`isSealed`를 `true`로 변경)
  ```jsx
  // isSealed를 true로 변경
  Object.seal(mia2);
  console.log(Object.isSealed(mia2)); // true

  // 프로퍼티 추가 => 불가능
  mia2["subject"] = "math";
  console.log(mia2); // { name: '미아', year: 2000, age: [Getter/Setter] }

  // 프로퍼티 삭제 => 불가능
  delete mia2["name"];
  console.log(mia2); // { name: '미아', year: 2000, age: [Getter/Setter] }

  // 프로퍼티 플래그 변경 => 가능
  // configurable: false 설정과 동일
  Object.defineProperty(mia2, "name", {
    witable: false,
  });
  console.log(Object.getOwnPropertyDescriptor(mia2, "name")); // { value: '미아', writable: true, enumerable: true, configurable: false }
  ```

### 3. Freezed

- **`Object.isFrozen(obj)`**
  - 가능한 경우 → `false`
  - 프로퍼티 추가, 삭제, 변경 불가능하고,
    `configurablr: false` , `writable: false` 인경우 → `true`
  ```jsx
  const mia3 = {
    name: "미아",
    year: 2000,

    get age() {
      return new Date().getFullYear() - this.year();
    },

    set age(age) {
      this.year = new Date().getFullYear() - age;
    },
  };

  // freezed 반환 값 확인
  console.log(Object.isFrozen(mia3)); // flase
  ```
- **`Object.freeze(obj)`** : 프로퍼티 추가, 삭제, 변경 불가능 (`isFrozen`을 `true`로 변경)
  ```jsx
  // isFrozen을 true로 변경
  Object.freeze(mia3);
  console.log(Object.isFrozen(mia3)); // true

  // 프로퍼티 추가 => 불가능
  mia3["subject"] = "math";
  console.log(mia3); // { name: '미아', year: 2000, age: [Getter/Setter] }

  // 프로퍼티 삭제 => 불가능
  delete mia3["name"];
  console.log(mia3); // { name: '미아', year: 2000, age: [Getter/Setter] }

  // 프로퍼티 플래그 변경 => 불가능
  // Object.defineProperty(mia3, 'name', {
  // 	vlaue: John,
  // });
  console.log(Object.getOwnPropertyDescriptor(mia3, "name")); // { value: '미아', writable: false, enumerable: true, configurable: false }
  ```

### 4. 상위 객체 내 하위 객체 수정 여부

- 상위 객체 동결 시 하위 객체는 동결되지 않음

```jsx
const mia4 = {
  name: "미아",
  year: 2000,
  john: {
    name: "존",
    year: 2001,
  },
};

// 상위 객체 동결
Object.freeze(mia4);

// 상위 객체 동결 시 하위 객체는 동결되지 않음
console.log(Object.isFrozen(mia4)); // true
console.log(Object.isFrozen(mia4["john"])); // false
```
