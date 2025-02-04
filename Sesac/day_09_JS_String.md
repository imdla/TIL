## <mark color="#fbc956">String</mark>

### 1. 문자 선언

- 큰 따옴표(`"`) 또는 작은 따옴표(`'`)를 사용해 문자열 선언
- 단일 문자열 선언의 시작과 끝에 동일한 형식을 사용
- **Template Literal :** 백틱(```) 문자 사용
  - 템플릿 리터럴 내 javascript 변수나 표현식을 `${}` 로 감싸면 결과가 문자열에 포함됨

```jsx
const name = "Mahalia";

const greeting = `Hello ${name}`;
```

### 2. 컨텍스트에서 연결

1. **“`+`”를 이용한 연결**

   ```jsx
   const group = "컬러";
   console.log(group + " 검정"); // 컬러 검정
   ```

2. **문자열에 표현식 포함** - 템플릿 리터럴에만 `${}`를 사용 가능

   ```jsx
   console.log(`${group} + 검정`); // 컬러 검정
   ```

### 3. 여러줄 문자열

1. **템플릿 리터럴(```) 이용**

   ```jsx
   const newline = `One day you finally knew
   what you had to do, and began,`;
   console.log(newline);
   ```

2. **Escaping Character**

   - **`\n`** : newline
   - **`\t`** : tab
   - **`\\`** : \

   ```jsx
   const myColor = "빨강\n노랑";
   console.log(myColor);
   /** 빨강
    * 노랑
    */

   const myColor2 = "노랑\t초록";
   console.log(myColor2); // 노랑	초록

   const backSlash = "파랑\\보라";
   console.log(backSlash); // 파랑\보라
   ```

### 4. 문자열에 따옴표 포함

1. **다른 문자 중 하나를 사용한 문자열 선언**

   ```jsx
   const goodQuotes1 = 'She said "I think so!"';
   const goodQuotes2 = `She said "I'm not going in there!"`;
   ```

2. **이스케이프 처리 (`\`)**

   : 문자가 코드의 일부가 아닌 텍스트로 인식하도록 함

   ```jsx
   const bigmouth = "I've got no right to take my place…";
   console.log(bigmouth);
   ```

### 5. 숫자와 문자열 연결

- **`Number()`** : 전달된 모든 것 → 숫자로 변환할 수 있는 경우 숫자로 변환
- **`String()`** : 인수 → 문자열로 변환

```jsx
const name = "Front ";
const number = 242;
console.log(`${name}${number}`); // "Front 242"
```

### 6. 문자열의 메서드

- **`length`** : **문자열의 길이 찾기**
  ```jsx
  var browserType = "mozilla";
  browserType.length;
  ```
- **`변수명[index]`** : **특정 문자열 찾기**
  ```jsx
  browserType[0];
  ```
- **문자열 내부의 하위 문자열 찾기 및 추출**
  - **`indexOf()`** : 하위 문자열 찾기
    ```jsx
    browserType.indexOf("zilla");
    ```
  - **`slice()`** : 문자열 내 부분 문자열이 어디에서 시작되고 어떤 문자로 끝나는지 찾기
    ```jsx
    browserType.slice(0, 3);
    ```
    - 특정 문자 뒤에 문자열의 나머지 문자를 모두 추출하려는 경우
      → 문자열에서 나머지 문자를 추출할 문자 위치만 포함
    ```jsx
    browserType.slice(2);
    ```
- **대소문자 변경**
  - **`toUpperCase()`** : 대문자로 변경
  - **`toLowerCase()`** : 소문자로 변경
  ```jsx
  var radData = "My NaMe Is MuD";
  radData.toLowerCase();
  radData.toUpperCase();
  ```
- **`replace( 변경 문자, 변경할 문자)` : 문자열의 일부 변경**
  ```jsx
  browserType.replace("moz", "van");
  ```
