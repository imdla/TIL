## <mark color="#fbc956">Destructuring Assignment (구조 분해 할당)</mark>

### 1. Destructuring Assignment (구조 분해 할당)

- 객체나 배열의 값을 변수로 분해해 할당 해줌
- 변수 선언 시 구조에 맞춰 배열이나 객체의 값을 자동으로 분리
- 함수의 매개변수가 많거나 매개변수 기본값이 필요한 경우 사용

### 2. 배열 구조 분해 할당

- 배열의 원소 → 변수에 할당

  ```jsx
  let arr = ["John", "Smith"];

  // 구조 분해 할당
  let [firstName, surName] = arr;

  console.log(firstName); // John
  console.log(surName); // Smith
  ```

- **`split` 같은 반환 값이 배열인 메서드 함께 활용 가능**
  ```jsx
  let [firstName, surName] = "John Smith".split(" ");
  ```
- **쉼표 사용 ⇒ 요소 무시**

  - 필요한 원소만 할당
  - 두번째 요소 생략, 세번째 요소는 `job` 이라는 변수에 할당됨

  ```jsx
  let [firstName, , job] = ["John", "Smith", "Professor", "Math", 2023];

  console.log(job); // Professor
  ```

- **할당 연산자 우측** ⇒ 모든 이터러블 가능
  ```jsx
  let [a, b, c] = "abc";
  let [one, two, three] = new Set([1, 2, 3]);
  ```
- **할당 연산자 좌측** ⇒ 모든 할당할 수 있는 것

  ```jsx
  let user = {};
  [user.fristName, user.surName] = "John Smith".split(" ");

  console.log(user.fristName); // John
  ```

- **`Object.entries(obj)`** - 객체의 키와 값을 순회해 변수로 분해 할당

  ```jsx
  let user = {
    name: "John",
    year: 2022,
  };

  for (let [key, value] of Object.entries(user)) {
    console.log(`${key}: ${value}`); // name: John, year: 2022
  }
  ```

- **변수 교환**

  ```jsx
  let guest = "John";
  let admin = "Mia";

  // 변수에 저장된 값 교환
  [guest, admin] = [admin, guest];

  console.log(`${guest} ${admin}`); // Mia John
  ```

### 3. 펼침 연산자로 나머지 요소 가져오기

- 펼침 연산자 사용해 할당하고 남은 원소를 하나의 변수에 모두 할당
- **`… 변수이름`** - 나머지 배열 요소들이 저장된 새로운 배열
  (가장 마지막에 위치해야함)

```jsx
let [name1, name2, ...rest] = [
  "John",
  "Mia",
  "Michel",
  "Anddy",
  "Lisa",
  "Betty",
];

console.log(name1); // John
console.log(name2); // Mia

// 'rest'는 배열임
console.log(rest[0]); // Michel
console.log(rest[1]); // Anddy
console.log(rest.lenght); // 4
```

### 4. 배열 분해 기본값

- **할당할 값이 없으면 `undefined`로 취급**

  ```jsx
  let [firstName, surName] = [];

  console.log(firstName); // undefined
  console.log(surName); // undefined
  ```

- **`=` 이용 - 기본값(default value) 설정**

  ```jsx
  let [firstName = "Mia", surName = "Smith"] = ["John"];

  // 배열에서 받아온 값
  console.log(firstName); // John
  // 기본값
  console.log(surName); // Smith
  ```

- **복잡한 표현식과 함수 호출도 기본값 지정 가능**

  ```jsx
  let [
    firstName = prompt("이름을 입력하세요"),
    surName = prompt("성을 입력하세요"),
  ] = ["John"];

  // 배열에서 받아 온 값
  console.log(firstName); // John
  // prompt에서 받아온 값
  console.log(surName);
  ```

### 5. 객체 구조 분해 할당

- 객체의 속성을 변수로 할당
  - 객체의 `key` 와 동일한 변수에 `value` 할당
- `let {상응하는 객체 프로퍼티 패턴} = {분해하고자하는 객체}`
  ```jsx
  let {var1, var2} = {var1:..., var2:...}
  ```
- **객체 프로퍼티의 키 목록을 패턴으로 사용하는 경우**

  ```jsx
  let professor = {
    name: "Mia",
    year: 2023,
    subJect: "Math",
  };

  let { name, year, subject } = professor;

  console.log(name); // Mia
  console.log(year); // 2023
  console.log(subject); // Math
  ```

- **할당 연산자 좌측 ⇒ 복잡한 패턴 가능
  (분해 객체의 프로퍼티와 변수의 연결 조정 가능)** - 객체 프로퍼티를 프로퍼티의 키와 다른 이름을 가진 변수에 저장
  ⇒ 좌측 패턴에 `콜론(:)` 사용
  ```jsx
  let professor = {
  name: 'Mia',
  year: 2023,
  subJect: 'Math'
  };

      let {name: n, year: y, subject} = professor;

      console.log(n); // Mia
      console.log(y); // 2023
      console.log(subject); // Math
      ```

- **할당 연산자 좌측 ⇒ 기본값 설정**

  - 프로퍼티가 없는 경우, `=` 사용해 기본값 설정

  ```jsx
  let professor = {
    name: "Mia",
  };

  let { name, year = 2023, subject = "All" } = professor;

  console.log(n); // Mia
  console.log(y); // 2023
  console.log(subject); // All
  ```

- **할당 연산자 좌측 ⇒ 표현식이나 함수 호출 기본값 지정 가능**

  - 프로퍼티가 없는 경우, 표현식이나 함수 실행

  ```jsx
  let professor = {
    name: "Mia",
  };

  let {
    name,
    year = prompt("year?"),
    subject = prompt("subject?"),
  } = professor;

  // 객체의 값
  console.log(name); // Mia
  // prompt에서 받아온 값
  console.log(year);
  console.log(subject);
  ```

- **할당 연산자 좌측 ⇒ 콜론과 할당 연산자 동시 사용 가능**

  ```jsx
  let professor = {
    name: "Mia",
  };

  let { name, year: y = 2023, subject: s = "All" } = professor;

  console.log(name); // Mia
  console.log(year); // 2023
  console.log(subject); // All
  ```

- **객체에서 원하는 정보만 가져오기**

  ```jsx
  let professor = {
    name: "Mia",
    year: 2023,
    subject: "Math",
  };

  let { name } = professor;

  console.log(name); // Mia
  ```

### 6. 펼침 연산자로 나머지 요소 가져오기

- 객체 앞쪽에 위치한 프로퍼티 몇 개만 필요, 이후 나머지 값들 모아서 저장할 때 사용
- **`… 변수이름`** - 나머지 프로퍼티 할당 (가장 마지막 위치)

```jsx
let professor = {
  name: "Mia",
  year: 2023,
  subject: "Math",
};

let [name, ...rest] = professor;

// rest에는 {year: 2023, subject: 'Math'} 할당
console.log(rest.year); // 2023
console.log(rest.subject); // Math
```

### 7. 변수 선언 없이 사용

- 할당문을 `괄호(…)`로 감싸 `{…}`를 코드 블록이 아닌 표현식으로 해석하게함

```jsx
let name, year, subject;

({ name, year, subject } = { name: "Mia", year: 2023, subject: "Math" });

console.log(name); // Mia
```

### 8. 중첩 구조 분해(Nested Destructuring)

- 객체나 배열이 다른 객체나 배열의 포함하는 경우, 복잡한 패턴을 사용하면 중첩 배열이나 객체의 정보를 추출 할 수 있음

```jsx
let options = {
  size: {
    width: 100,
    height: 200,
  },
  items: ["Cake", "Donut"],
  extra: true,
};

let {
  // size 할당
  size: { width, height },
  // items 할당
  items: [item1, item2],
  // title 기본값 지정
  title = "Menu",
} = options;

console.log(width); // 100
console.log(height); // 200
console.log(item1); // Cake
console.log(item2); // Donut
console.log(title); // Menu
```

### 9. 함수 매개변수

- 함수 매개 변수의 문법

```jsx
function({
	매개변수로 전달된 객체 프로퍼티 : 변수 할당 = 기본값
	...
}) {}
```

- 기본값을 사용해도 괜찮은 경우 undefined으로 지정할 경우 가독성이 떨어짐

  ```jsx
  // 메뉴 생성 함수
  function showMenu(title = "Untitled", width = 100, height = 200, items = []) {
    //...
  }

  // 기본값을 사용한 함수
  showMenu("My Menu", undefined, undefined, ["item1", "item2"]);
  ```

- 매개변수 모두를 개체에 모아 함수에 전달하면 함수가 전달받은 객체를 분해하여 변수에 할당

  ```jsx
  let options = {
    title: "My Menu",
    items: ["item1", "item2"],
  };

  function showMenu({
    title = "Untitled",
    width = 100,
    height = 200,
    items = [],
  }) {
    console.log(`${title} ${width} ${height}`); // My Menu 100 200
    console.log(items); // item1, item2
  }

  showMenu(options);
  ```

  ```jsx
  let options = {
    title: "My Menu",
    items: ["Cake", "Donut"],
  };

  function showMenu({
    title = "Untitled",
    width: w = 100,
    height: h = 200,
    items: [item1, item2],
  }) {
    console.log(`${title} ${w} ${h}`); // My Menu 100 200
    console.log(item1); // Cake
    console.log(item2); // Donut
  }

  showMenu(options);
  ```
