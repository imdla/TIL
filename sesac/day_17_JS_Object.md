## <mark color="#fbc956">Object (객체)</mark>

### 1. Object (객체)

- 관련된 데이터와 함수의 집합, **`key : value pair`**
- 문자열, 숫자, 배열, 함수를 가질 수 있음
- **구성**
  - **`property` (속성)** - 문자열, 숫자, 배열 해당
  - **`method`** - 함수 해당
  - **`object literal` (객체 리터럴)** - 프로퍼티와 메소드를 포함한 객체, 객체를 생성할 때 컨텐츠를 그대로 대입

### 2. 점 표기법

- **`Object.property`**
- **`Object.method()`**
- 객체의 프로퍼티와 메소드에 접근할 때 점 표기법을 사용
  ```jsx
  let red = {
    name: ["빨강", "붉은색"],
    group: "색상",
    motion: function () {
      return `${this.name}은 ${this.group}입니다.`;
    },
  };

  console.log(red); // { name: ['빨강', '붉은색'], group: '색상', motion: [function: motion]}
  console.log(red.name); // ['빨강', '붉은색']
  console.log(red.group); // 색상
  console.log(red.motion()); // 빨강,붉은색은 색상입니다.
  ```
- **하위 namespaces**
  ```jsx
  name: ['빨강', '붉은색'],
  ```
  위의 name 멤버를 아래와 같이 변경 후
  ```jsx
  name : {
    first: '빨강',
    last: '붉은색'
  }
  ```
  아래와 같이 하위 namespaces로 만들 수 있음
  ```jsx
  console.log(red.name.first); // 빨강
  console.log(red.name.last); // 붉은색
  ```

### 3. 괄호 표기법

- **`Object['property']`**
- 객체의 프로퍼티에 접근하는 방법

```jsx
console.log(red["group"]); // 색상
```

### 4. 객체 멤버 설정

- 설정할 멤버를 간단히 명시(점이나 대괄호 표기법을 사용)해 객체 멤버의 값을 설정(갱신) 가능
- **변경**
  ```jsx
  let red = {
    name: ["빨강", "붉은색"],
    group: "색상",
    motion: function () {
      return `${this.name}은 ${this.group}입니다.`;
    },
  };

  // 괄호 표기법의 중첩
  red["name"]["last"] = "빨간색";
  console.log(red.name.last); // 빨간색

  // 점 표기법 - 프로퍼티
  red.group = "color";
  console.log(red.group); // color

  // 점 표기법 - 메서드
  red.motion = function () {
    return `빨강은 색상입니다.`;
  };
  console.log(red.motion()); // 빨강은 색상입니다.
  ```
- **추가**
  ```jsx
  // 괄호 표기법
  red["nextColor"] = "orange";
  console.log(red);
  /** {
    name: [ '빨강', '붉은색' ],
    group: '색상',
    motion: [Function: motion],
    nextColor: 'orange'
  } */
  ```
- **삭제**
  ```jsx
  // 괄호 표기법
  delete red["nextColor"];
  console.log(red); // { name: [ '빨강', '붉은색' ], group: '색상', motion: [Function: motion] }
  ```

### 5. **표기법 비교**

- **대괄호 표현**
  - 멤버의 값을 동적으로 변경할 수 있고, 멤버의 이름까지도 동적으로 사용할 수 있음
  ```jsx
  var myDataName = nameInput.value;
  var myDataValue = nameValue.value;

  person[myDataName] = myDataValue;
  ```
- **점 표기법**
  - 괄호 표기법처럼 이름을 동적으로 사용할 수 없고, 상수 값만을 사용해야 함
  ```jsx
  const nameKey = "name";
  const nameValue = "빨강";

  const groupKey = "group";
  const groupValue = "색상";

  const red2 = {
    [nameKey]: nameValue,
    [groupKey]: groupValue,
    motion: function () {
      return `${this.name}은 ${this.group}입니다.`;
    },
  };
  ```

### 6. this 키워드

- 현재 동작하고 있는 코드를 가지고 있는 개체를 가르킴
  ⇒ 객체 멤버의 컨텍스트가 바뀌는 경우에도 언제나 정확한 값을 사용하게 해줌

### 7. 객체의 특징

- `const` 로 선언할 경우 객체 자체를 변경 불가능
- 객체 안의 프로퍼티나 메서드 변경 가능

### 8. 객체 값 가져오기

- **`Object.keys(object name)`** : 모든 `key` 값 가져오기
  ```jsx
  let black = {
    name: "검정",
    group: "색상",
  };

  console.log(Object.keys(black)); // [ 'name', 'group' ]
  ```
- **`Object.values(object name)`** : 모든 **value** 값 가져오기
  ```jsx
  console.log(Object.values(black)); // [ '검정', '색상' ]
  ```

### 9. 간단 표기

```jsx
const name = "검정";

const black2 = {
  name: name,
};

console.log(black2); // { name: '검정' }
```

```jsx
const name = "검정";

// name: name의 표기 => name으로 가능
const black2 = {
  name,
};

console.log(black2); // { name: '검정' }
```
