## <mark color="#fbc956">Date 객체와 날짜</mark>

### 1. Date

- 날짜 저장 및 날짜와 관련 메서드 제공해주는 내장 객체
- 생성 및 수정 시간을 저장하거나 시간을 측정하고 날짜를 출력할 수 있음

### 2. 객체 생성

- **`new Date()`** : 현재 날짜와 시간 저장된 `Date` 객체 반환
  ```jsx
  let now = new Date();
  console.log(now); // 현재 날짜 및 시간 출력
  ```
- **`new Date(milliseconds)`** : UTC 기준(UTC+0) 1970년 1월 1일 0시 0분 0초에서 `milliseconds` 밀리초(1/1000 초) 후의 시점이 저장된 `Date` 객체가 반환
  ```jsx
  let jan01_1970 = new Date(0);
  console.log(jan01_1970); // 1970년 1월 1일 0시 0분 0초 나타냄
  ```
- **`new Date(datestring)`** : 인수가 하나에 문자열이라면 자동으로 구문 분석됨
  ```jsx
  let date = new Date("2017-01-26");
  console.log(date); // 2017-01-26T00:00:00.000Z
  ```
- **`new Date(year, month, date, hours, minutes, seconds, ms)`** : 주어진 인수 조합해 만들 수 있는 날짜가 저장된 객체 반환 (첫번째와 두번째 인수만 필수 값)
  - **`year`** : 반드시 네자리 숫자
  - **`month`** : `0` (1월) ~ `11` (12월) 사이 숫자
  - **`date`** : 값이 없는 경우 1일로 처리
  - **`hour`**, **`minutes`**, **`seconds`**, **`ms`** : 값이 없는 경우 `0` 으로 처리
  ```jsx
  console.log(new Date(2010, 0, 1, 0, 0, 0, 0)); // 2009-12-31T15:00:00.000Z
  console.log(new Date(2010, 0, 1)); // 2009-12-31T15:00:00.000Z
  ```

### 3. 날짜 구성요소 얻기

- **`getFullYear()`** : 연도(네 자릿수) 반환
- **`getMonth()`** : 월(0 ~ 11) 반환
- **`getDate()`** : 일(0 ~ 31) 반환
- **`getHours()`** , **`getMinutes()`** , **`getSeconds()`** , **`getMilliseconds()`** : 시, 분, 초, 밀리초 반환
- **`getDay()`** : 요일(0 ~ 6) 반환 (0 = 일요일)
- **`getTime()`** : 주어진 일시와 1970년 1월 1일 00시 00분 00초 사이의 간격(밀리초 단위) 반환
- **`getTimezoneOffset()`** : 현지 시간과 표준 시간의 차이 반환(분 단위)

```jsx
// 현재 일시
let date = new Date();

// 현지 시간 기준 시
console.log(date.getHours());

// 표준시간대 기준 시
console.log(date.getUTCHours());
```

### 4. 날짜 구성요소 설정

- **`setFullYear(year, [month], [date])`**
- **`setMonth(month, [date])`**
- **`setDate(date)`**
- **`setHours(hour, [min], [sec], [ms])`**
- **`setMinutes(min, [sec], [ms])`**
- **`setSeconds(sec, [ms])`**
- **`setMilliseconds(ms)`**
- **`setTime(milliseconds)`** (1970년 1월 1일 00:00:00 UTC부터 밀리초 이후를 나타내는 날짜를 설정)

```jsx
let today = new Date();

today.setHours(0);
console.log(today); // '시'만 0으로 변경

today.setHours(0, 0, 0, 0);
console.log(today); // 날짜는 변경되지 않고 시, 분, 초가 0으로 변경
```

### 5. 자동 고침 **(autocorrection)**

- 범위를 벗어나는 값을 설정하려고 하면 자동 고침 기능이 활성화되면서 값이 자동으로 수정
- 입력받은 날짜 구성 요소가 범위를 벗어나면 초과분은 자동으로 다른 날짜 구성요소에 배분
  ```jsx
  let date = new Date(2013, 0, 32);
  console.log(date); // 2013년 2월 1일 출력
  ```
- **일정 시간이 지난 후의 날짜**

  ```jsx
  let date = new Date();
  date.setSeconds(date.getSeconds() + 70);

  console.log(date); // 70초 후의 날짜 출력
  ```

- **0이나 음수를 날짜 구성 요소에 설정**

  ```jsx
  let date = new Date(2016, 0, 2);

  date.setDate(1);
  console.log(date); // 01 Jan 2016

  // 일의 최솟값은 1이므로 0 입력 시 전달의 마지막 날 설정한 것과 같음
  date.setDate(0);
  console.log(date); //31 Dec 2015
  ```

### 6. 시간 차 측정

- **`Date`** 객체를 숫자형으로 변경하면 타임스탬프 (**`date.getTime()`** 호출 시 반환되는 값)가 됨

```jsx
let date = new Date();
console.log(+date); // 타임 스탬프를 호출한 것과 동일한 값
```

### 7. Date.now()

- `Date.now()` : 현재 타임스탬프를 반환하는 메서드, `Date` 객체를 만들지 않고도 시차 측정 가능

```jsx
let start = Date.now(); // 1970년 1월 1일부터 현재까지 밀리초

for (let i = 0; i < 10000; i++) {
  let doSomething = i * i * i;
}

let end = Date.now();
console.log(`반복문을 모두 도는데 ${end - start} 밀리초 소요됨`);
```

### 8. Date.parse와 문자열

- **`Date.parse(str)`** : 문자열에서 날짜를 읽어올 수 있음
- **문자열의 형식 :** `YYYY-MM-DDTHH:mm:ss.sssZ`
  - **`YYYY-MM-DD`** : 날짜 (연-월-일)
  - **`"T"`** : 구분 기호로 쓰임
  - **`HH:mm:ss.sss`** : 시:분:초.밀리초
  - **`'Z'`** : (옵션) `+-hh:mm` 형식의 시간대를 나타냄
    - `Z` 한 글자인 경우 UTC+0을 나타냄
- **문자열의 형식의 만족**
  - 만족하는 문자열 → 대응하는 날짜의 타임스탬프 반환
  - 문자열 형식이 맞지 않은 경우 → `NaN` 반환

```jsx
let ms = Date.parse("2012-01-26");

console.log(ms); // 1327536000000 (타임스탬프)
```

- `Date.parse(str)` 이용한 새로운 `Date` 객체 만들기

```jsx
let date = new Date(Date.parse("2012-01-26"));

console.log(date); // 2012-01-26T00:00:00.000Z
```
