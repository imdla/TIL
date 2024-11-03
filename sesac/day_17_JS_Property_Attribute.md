## <mark color="#fbc956">Object Property (객체 프로퍼티)</mark>

### 1. 프로퍼티 플래그 (property flag)

- **`value`** : 실제 프로퍼티 값
- **`writable`**
  - `true`이면 값 수정 가능
  - `false`면 읽기만 가능
- **`enumerable`**
  - `true`이면 반복문을 사용해 나열 가능
  - `false`면 반복문을 사용해 나열 불가능
- **`configurable`**
  - `true`이면 프로퍼티 삭제나 플래그 수정 가능
  - `false`면 프로퍼티 삭제와 플래그 수정 불가능
    - 단, `false`일 때 `writable`이 `true`인 경우 → `value` 및 `writable` 변경 가능

### 2. 프로퍼티 설명자(descriptor) 반환

- **`Object.getOwnPropertyDescriptor`** 메서드 : 프로퍼티 값과 세 플래그에 대한 정보 나옴
- **`getOwnPropertyDesciptor**( property attribute 알고 싶은 객체, property key 값 )`
- **`getOwnPropertyDescriptors**( property attribute 알고 싶은 객체 )`: 모든`property`의 값 나옴

```jsx
const user = {
  name: "John",
};

let descriptor = Object.getOwnPropertyDescriptor(user, "name");
console.log(descriptor); // { value: 'John', writable: true, enumerable: true, configurable: true }
```

### 3. 플래그 변경

- **`Object.defineProperty`** 메서드
- **`defineProperty**( 프로퍼티 적용할 객체, 적용할 객체 프로퍼티, 프로퍼티 어트리뷰트 정의 )`

```jsx
const user2 = {
	name: 'John';
};

Object.defineProperty(user2, 'subject', {
	value: 'math',
	writable: true,
	enumerable: true,
	configurable: true,
});
console.log(user2); // { name: 'John',subject: 'math' }
console.log(Object.getOwnPropertyDescriptor(user2, 'subject')); // { value: 'math', writable: true, enumerable: true, configurable: true }
```

### 4. 프로퍼티 종류

1. **데이터 프로퍼티 (data property)**

   - 키와 값으로 형성된 실질적 값을 갖고 있는 프로퍼티
   - **데이터 프로퍼티의 설명자**
     - **`value` , `writable` , `enumerable` , `configurable`**

   ```jsx
   const mia = {
     name: "미아",
     year: 2023,
   };

   // Object => 대문자로 시작하면 생성자 함수 or class
   // 생성자 함수 or class에 '.'으로 직접적으로 붙어있는 것 => static 함수
   console.log(Object.getOwnPropertyDescriptor(mia, "name")); // { value: '미아', writable: true, enumerable: true, configurable: true }
   console.log(Object.getOwnPropertyDescriptor(mia, "year")); // { value: 2023, writable: true, enumerable: true, configurable: true }
   ```

2. **접근자 프로퍼티 (accessor property)**

   - 자체적으로 값을 갖고 있지 않지만 다른 값을 가져오거나 설정할 때 호출되는 함수로 구성된 프로퍼티 → **`getter` , `setter`**
   - **접근자 프로퍼티의 설명자**
     - **`get`** : 인수가 없는 함수, 프로퍼티 읽을 때 동작
     - **`set`** : 인수가 하나인 함수, 프로퍼티에 값을 쓸 때 동작
     - **`enumerable`** : 데이터 프로퍼티와 동일
     - **`configurable`** : 데이터 프로퍼티와 동일

   ```jsx
   const mia2 = {
     name: "미아",
     year: 2000,

     get age() {
       return new Date().getFullYear() - this.year;
     },

     set age(age) {
       this.year = new Date().getFullYear() - age;
     },
   };

   console.log(mia2); // { name: '미아', year: 2000, age: [Getter/Setter] }
   console.log(mia2.age); // 23

   // age 값 변경
   mia2.age = 33;
   console.log(mia2.age); // 33
   console.log(mia2.year); // 1991

   console.log(Object.getOwnPropertyDescriptor(mia2, "age"));
   /**
   {
     get: [Function: get age],
     set: [Function: set age],
     enumerable: true,
     configurable: true
   }
   */
   ```

### 5. Writable 플래그

```jsx
const mia3 = {
  name: "미아",
  year: 2023,
  subject: "math",
};

// subject 프로퍼티의 writable => false 변경
Object.defineProperty(mia3, "subject", {
  writable: false,
});

// subject 프로퍼티의 writable false => 값 변경 불가
mia3.subject = "art";
console.log(mia3); // { name: '미아', year: 2023, subject: 'math' }
```

### 6. Enumerable 플래그

```jsx
// 위의 mia3 객체 이어서 진행

// 반복문을 사용해 나열 가능
console.log(Object.keys(mia3)); // [ 'name', 'year', 'subject' ]
for (let key in mia3) {
  console.log(key);
}
/**
name
year
subject
*/

// name 프로퍼티의 enumerable => false로 변경
Object.defineProperty(mia3, "name", {
  enumerable: false,
});
console.log(Object.getOwnPropertyDescriptor(mia3, "name")); // { value: '미아', writable: true, enumerable: false, configurable: true }

// name 프로퍼티의 enumerable false => name 나열 불가
console.log(Object.keys(mia3)); // [ 'year', 'subject' ]
for (let key in mia3) {
  console.log(key);
}
/**
year
subject
*/
console.log(mia3); // { year: 2023, subject: 'math' }

// 나열은 불가능 하지만 값은 있음
console.log(mia3.name); // 미아
```

### 7. Configurable 플래그

```jsx
// 위의 mia3 객체 이어서 진행

// subject 프로퍼티의 configurable => false로 변경
Object.defineProperty(mia3, "subject", {
  writable: true,
  configurable: false,
});
console.log(Object.getOwnPropertyDescriptor(mia3, "subject"));
/**
{
  value: 'math',
  writable: true,
  enumerable: true,
  configurable: false
}
*/

// subject 프로퍼티의 configurable false => 플래그 변경 불가
Object.defineProperty(mia3, "subject", {
  enumerable: false,
});

// subject의 프로퍼티 configurable false이지만 writable true라서 => 값 변경 가능
Object.defineProperty(mia3, "subject", {
  value: "art",
});
console.log(Object.getOwnPropertyDescriptor(mia3, "subject")); // { value: 'art', writable: true, enumerable: true, configurable: false }

// subject의 프로퍼티 configurable false이지만 writable true라서
// => writable 플래그 변경 가능
Object.defineProperty(mia3, "subject", {
  wriable: false,
});
console.log(Object.getOwnProptertyDescriptor(mia3, "subject"));
/**
{
  value: 'art',
  writable: false,
  enumerable: true,
  configurable: false
}
*/
```
