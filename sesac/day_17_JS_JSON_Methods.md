## <mark color="#fbc956">JSON and Methods</mark>

### 1. JSON(JavaScript Object Notation)

- JavaScript 객체 표기법을 기반으로 한 데이터 형식
- 인간이 읽기 쉬운 구조로 데이터 표현, 서버와 클라이언트 간 데이터 교환에 많이 사용

### 2. JSON 구조

- `키(Key) - 값(Value)` 쌍으로 데이터 표현
- 중괄호 `{}` 는 객체 나타냄, 대괄호 `[]` 는 배열 나타냄
- 문자열, 숫자, 배열, 객체, 불리언, null 값 사용 가능
  - 문자열 (String) : 큰 따옴표(`”`)로 묶인 문자열 데이터
  - 숫자 (Number) : 정수와 실수를 따옴표 없이 표현
  - 불리언 (Boolean) : `True`(참) / `False`(거짓) 나타냄
  - 배열 (Array) : 여러 개의 값을 대괄호 `[]` 안 나열한 구조, 값은 쉼표로 구분
  - 객체 (Object) : 중괄호 `{}` 로 묶인 `key:value` 쌍
  - null : 값이 없음

> **JSON 메서드**
>
> - `JSON.stringify` : 객체 → JSON으로 바꿔줌
> - `JSON.parse` : JSON → 객체로 바꿔줌

### 3. JSON.stringify

1. **객체 → JSON으로 바꿔줌**

   ```jsx
   let student = {
     name: "John",
     age: 20,
     isAdmin: false,
     course: ["html", "css", "js"],
     wife: null,
   };

   let json = JSON.stringify(student);

   console.log(typeof json); // string
   // JSON으로 인코딩된 객체
   console.log(json);
   // {"name":"John","age":20,"isAdmin":false,"course":["html","css","js"],"wife":null}
   ```

2. **JSON으로 인코딩된 객체의 특징**
   - 문자열은 큰 따옴표(`”`)로 감싼다. (작은 따옴표나 백틱 사용 불가)
   - 객체 프로퍼티 이름은 큰 따옴표(`”`)로 감싼다.
3. **`JSON.stringfy` 는 객체뿐 아니라 원시값에 적용 가능**

   - 객체 `{ ... }`
   - 배열 `[ ... ]`
   - 원시형 : 문자형, 숫자형, 불린형, null

   ```jsx
   // 숫자를 JSON으로 인코딩 => 숫자
   console.log(JSON.stringify(1)); // 1
   // 문자열 JSON으로 인코딩 => 문자열
   console.log(JSON.stringify("hello")); // "hello"
   console.log(JSON.stringify(true)); // true
   console.log(JSON.stringify([1, 2, 3])); // [1,2,3]
   ```

4. **`JSON.stringfy` 호출 시 무시되는 프로퍼티**

   - 함수 프로퍼티 (메서드)
   - 심볼형 프로퍼티 (`key`가 심볼인 프로퍼티)
   - 값이 `undefied`인 프로퍼티

   ```jsx
   let user = {
     sayHi() {
       console.log("Hello");
     },
     [Symbol("id")]: 123,
     something: undefined,
   };

   console.log(JSON.stringify(user)); // {}
   ```

5. **`JSON.stringfy` 장점**

   - 중첩 객체도 알아서 문자열로 바꿔줌

   ```jsx
   let meetup = {
     title: "Conference",
     room: {
       number: 23,
       participants: ["John", "Mia"],
     },
   };

   // 객체 전체가 문자열로 변환됨
   console.log(JSON.stringify(meetup));
   // {"title":"Conference","room":{"number":23,"participants":["John","Mia"]}}
   ```

6. **`JSON.stringfy` 주의**

   - 순환 참조가 있을 경우, 원하는 대로 객체를 문자열로 바꾸는 것 불가능

   ```jsx
   let room = {
     number: 23,
   };

   let meetup = {
     title: "Conference",
     participants: ["John", "Mia"],
   };

   meetup.place = room; // meetup은 room 참조
   room.occupiedBy = meetup; // room은 meetup 참조

   JSON.stringify(meetup); // Error: Converting circular structure to JSON
   ```

### 4. replacer로 원하는 프로퍼티만 직렬화

> 💡 **replacer 함수 ?**
>
> - replacer에 전달되는 함수는 프로퍼티 `(key, value)`쌍 전체를 대상으로 호출
> - 반드시 기존 프로퍼티 값을 대신하여 사용할 값 반환해야 함
> - 특정 프로퍼티를 직렬화에서 누락시키려면 반환 값을 `undefined` 로 만듦

- **`JSON.stringify` 의 전체 문법**

  ```jsx
  let json = JOSN.stringify(value, [replacer, space]);
  ```

  - **`value`** : 인코딩 하려는 값
  - **`replacer`** : JSON으로 인코딩하길 원하는 프로퍼티가 담긴 배열, 매핑 함수 `function(key, value)`
  - **`space`** : 서식 변경 목적으로 사용할 공백 문자 수

  ```jsx
  let room = {
    number: 23,
  };

  let meetup = {
    title: "Conference",
    participants: [{ name: "John" }, { name: "Mia" }],
    place: room, // meetup은 room 참조
  };

  room.occupiedBy = meetup; // room은 meetup 참조

  console.log(JSON.stringify(meetup, ["title", "participants"]));
  // {"title":"Conference","participants":[{},{}]}
  ```

- 위의 배열에 `name`을 넣지 않아 출력된 문자열의 `participants`가 비었음

  - 순환 참조를 발생시키는 프로퍼티 `room.occupieBy` 만 제외하고 모든 프로퍼티 배열에 넣음

  ```jsx
  let room = {
    number: 23,
  };

  let meetup = {
    title: "Conference",
    participants: [{ name: "John" }, { name: "Mia" }],
    place: room, // meetup은 room을 참조
  };

  room.occupiedBy = meetup; // room은 meetup을 참조

  console.log(
    JSON.stringify(meetup, ["title", "participants", "place", "name", "number"])
  );
  // {"title":"Conference","participants":[{"name":"John"},{"name":"Mia"}],"place":{"number":23}}
  ```

- `occupieBy` 제외한 모든 프로퍼티 직렬화 됨, 그러나 배열이 길다는 느낌이 있음
  - `replacer` 자리에 배열 대신 **함수 전달**
- `occupiedBy` 를 `undefined` 으로 반환하여 직렬화에 포함되지 않은 것 확인

  ```jsx
  let room = {
    number: 23,
  };

  let meetup = {
    title: "Conference",
    participants: [{ name: "John" }, { name: "Mia" }],
    place: room, // meetup은 room을 참조
  };

  room.occupiedBy = meetup; // room은 meetup 참조

  console.log(
    JSON.stringify(meetup, function replacer(key, value) {
      console.log(`${key}: ${value}`);
      return key == "occupiedBy" ? undefined : value;
    })
  );
  /* replacer 함수에서 처리하는 키:값 쌍 목록
  : [object Object]
  title: Conference
  participants: [object Object],[object Object]
  0: [object Object]
  name: John
  1: [object Object]
  name: Mia
  place: [object Object]
  number: 23
  occupiedBy: [object Object]
  {"title":"Conference","participants":[{"name":"John"},{"name":"Mia"}],"place":{"number":23}}
  */
  ```

  - `replacer` 함수 사용하면 중첩 객체 등을 포함한 객체 전체에서 원하는 프로퍼티만 선택해 직렬화 가능

### 5. space로 가독성 높이기

> 💡 **space ?**
>
> - `JSON.stringify(value, replacer, space)` 의 `space`
> - 가독성을 높이기 위해 중간에 삽입해 줄 공백 문자 수를 나타냄

```jsx
let user = {
  name: "John",
  age: 20,
  roles: {
    isAdmin: false,
    isEditor: true,
  },
};

console.log(JSON.stringify(user, null, 2));
/* 공백 문자 두 개를 사용해 들여쓰기 함
{
  "name": "John",
  "age": 20,
  "roles": {
    "isAdmin": false,
    "isEditor": true
  }
}
*/
```

### 6. custom “toJSON”

- **`toJSON`** : 객체를 JSON으로 바꿀 수 있음
- `JSON.stringify` 는 이런 경우를 감지하고 `toJSON`을 자동으로 호출해줌

```jsx
let room = {
  number: 23,
};

let meetup = {
  title: "Conference",
  date: new Date(Date.UTC(2017, 0, 1)),
  room,
};

console.log(JSON.stringify(meetup));
/*
	{
		"title":"Conference",
		"date":"2017-01-01T00:00:00.000Z", // Date 객체 내장 메서드 toJSON이 호출되며 date의 값이 문자열로 변환됨
		"room":{"number":23}
	}
*/
```

- `room` 에 직접 커스텀 메서드 `toJSON` 추가하여 어떻게 변경되는지 아래에서 확인

```jsx
let room = {
  number: 23,
  toJSON() {
    return this.number;
  },
};

let meetup = {
  title: "Conference",
  room,
};

console.log(JSON.stringify(room)); // 23
console.log(JSON.stringify(meetup)); // {"title":"Conference","room":23}
```

### 7. JSON.parse

- **`JSON.parse` 의 전체 문법**

  ```jsx
  let value = JSON.parse(str, [reviver]);
  ```

  - **`str`** : JSON 형식의 문자열
  - **`reviver`** : 모든 `(key, value)` 쌍을 대상으로 호출되는 `function(key, value)` 형태의 함수로 값 변경 가능

  ```jsx
  let numbers = "[0, 1, 2, 3]";

  numbers.JSON.parse(numbers);

  console.log(numbers[1]); // 1
  ```

- `JSON.parse` 는 **중첩 객체에 사용 가능**

  ```jsx
  let userData =
    '{ "name": "John", "age": 20, "isAdmin": false, "friends": [0,1,2,3] }';

  let user = JSON.parse(userData);

  console.log(user.friends[1]); // 1
  ```

### 8. reviver 사용하기

- JSON으로 바꾼 `date` 는 `Date`객체가 아니고 문자열임
  → `JSON.parse` 의 두번째 인수 `reviver` 이용해 `date` 를 `Date` 객체를 반환하도록 함수를 구현함

  ```jsx
  let str = '{"title":"Conference","date":"2017-11-30T12:00:00.000Z"}';

  let meetup = JSON.parse(str, function (key, value) {
    if (key === "date") return new Date(value);
    return value;
  });

  console.log(meetup.date.getDate()); // 30
  ```

- 중첩 객체에 적용

  ```jsx
  let schedule = `{
    "meetups": [
      {"title":"Conference","date":"2017-11-30T12:00:00.000Z"},
      {"title":"Birthday","date":"2017-04-18T12:00:00.000Z"}
    ]
  }`;

  schedule = JSON.parse(schedule, function (key, value) {
    if (key == "date") return new Date(value);
    return value;
  });

  console.log(schedule.meetups[1].date.getDate()); // 18
  ```
