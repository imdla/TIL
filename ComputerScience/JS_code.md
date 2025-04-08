### 1. 🤔 문제

```jsx
function change(a, b, c) {
  a = "a changed";
  b = { b: "changed" };
  c.c = "changed";
}

let a = "a unchanged";
let b = { b: "unchanged" };
let c = { c: "unchanged" };

change(a, b, c);

console.log(a, b, c);
```

### 2. 💡 결과

`a unchanged {b: ‘unchanged’} {c: ‘changed’}`

- 자바스크립트는 Call by Value 방식으로 매개변수 전달
  - 함수 배개변수에 값의 복사본이 전달됨

1. **`a` 문자열**

   - `a` 는 문자열
   - 문자열 값의 복사본이 파라미터에 전잘
   - 함수 내에서 값이 변경되어도 호출한 곳의 변수에는 영향을 미치지 않음

   ⇒ `a` 는 여전히 `a unchanged` 를 유지

2. **`b` 객체**
   - `b` 는 객체
   - 원본 객체의 참조 값(주소)의 복사본이 파라미터에 전달
   - `b = {b : ‘changed’}` 와 같이 객체를 새롭게 할당할 경우
     - 해당 복사본이 가리키는 참조 값이 새로운 객체의 참조 값으로 변경
   - 함수 내의 복사본 `b` 는 `b = {b: ‘changed’}` 의 참조 값을 가리키게 됨
   - 한수 외부의 `b` 는 여전히 `b = {b: ‘unchanged’}` 로 유지
3. **`c` 객체**
   - `c` 는 객체
   - 원본 객체의 참조 값이 복사본이 파라미터에 전달됨
   - 함수 내부와 외부의 변수가 모두 동일한 참조 값을 가리키고 있음
     - 함수 내부에서 객체의 속성을 변경하면 호출한 곳의 객체에도 영향 미침
   - `c.c = ‘changed’` 는 `c` 객체의 속성을 변경한 것
   - 호출한 곳의 `c` 객체는 `{c: ‘changed’}` 로 변경됨
