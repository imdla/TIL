## <mark color="#fbc956">Optional Chaining (옵셔널 체이닝)</mark>

### 1. Optional Chaining (옵셔널 체이닝)

- 중첩된 객체의 깊은 속성에 접근할 때 안전하게 존재 여부 확인하는 문법
- 존재하지 않는 속성에 접근할 때 발생하는 오류 방지 가능
- 존재하지 않는 속성에 접근 시 `undefined` 반환

### 2. 옵셔널 체이닝의 사용

- **`?.`**

```jsx
object?.property;
object?.method();
```

### 3. 객체의 옵셔널 체이닝

- 중첩된 객체의 속성에 접근 시 안전하게 값 확인 가능

```jsx
const shop = {
  title: "My Cafe",
  menu: {
    cake: "딸기",
  },
};

console.log(shop?.title); // My Cafe
console.log(shop?.menu?.cake); // 딸기
console.log(shop.address?.locate); // undefined
```

### 4. 배열에서 옵셔널 체이닝

- 배열의 원소가 존재하는지 확인 가능

```jsx
const dessert = [{ menu: "cake" }, { menu: "coffee" }];

console.log(dessert?.[0]?.menu); // cake
console.log(dessert?.[1]?.menu); // coffee
console.log(dessert?.[2]?.menu); // undefined
```

### 5. 메서드에서의 옵셔널 체이닝

- 메서드 존재시에만 호출 가능

```jsx
const cafe = {
  greet() {
    return "어서오세요";
  },
};

console.log(cafe.greet?.()); // 어서오세요
console.log(cafe.makeCoffee?.()); // undefined
```
