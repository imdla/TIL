## <mark color="#fbc956">Module</mark>

### 1. 모듈 (Module)

- 코드를 여러 파일로 나누고, 코드를 파일 단위로 관리하는 시스템
- 연관성이 높은 코드들을 파일 단위로 관리해 유지보수 쉬워짐
- **종류**
  - ES 모듈 시스템
  - Common JS 모듈 시스템

### 2. 라이브러리 (Library)

- 관련된 모듈들의 집합
- 특정 기능 수행 위한 도구들의 모임

### 3. 패키지 (Package)

- 배포 가능한 가장 작은 소프트웨어 단위
- 하나 이상의 모듈/라이브러리 포함
- `package.json` 파일로 관리됨 (의존성, 메타데이터 등)

---

## <mark color="#fbc956">ES 모듈</mark>

### 1. ES 모듈 (ESM, ECMAScript Modules)

- ECMAScript 6 (ES6)에서 도입된 모듈 시스템
- 웹 브라우저와 Node.js에서 사용 가능
- 모듈 파일 확장자 : `.js` , `.mjs` (Node.js 환경)
- 트리 쉐이킹 가능

### 2. 사용법

- **모듈 내보내기 : `export`**
- **모듈 불러오기 : `import ... from 파일`**
- **모듈 설정 파일 : `package.json`**
  ```json
  // package.json
  {
    "type": "module"
  }
  ```

### 3. export 방식

- 여러 값을 내보낼 수 있음
- 불러올 때 반드시 원본 이름 사용하거나 `as` 로 별칭 지정
- 중괄호 `{}` 사용 필수

1. 모듈 내보내는 파일

   ```jsx
   // export.js
   export const name = "홍길동";

   export function greet() {
     console.log(`안녕하세요. ${name}입니다.`);
   }
   ```

2. 모듈 불러오는 파일

   ```jsx
   import { name, greet } from "./export.js";

   console.log(name);
   greet();
   ```

   - `as` 별칭 지정

   ```jsx
   import { name as nickname, greet as sayHi } from "./export.js";

   console.log(nickname);
   sayHi();
   ```

### 4. export default 방식

- 모듈 달 하나만 가능
- 불러올 때 아무 이름이나 사용 가능
- 중괄호 `{}` 없이 사용

1. 모듈 내보내는 파일

   ```jsx
   // export.js
   const obj = {
     name: "홍길동",
     greet() {
       console.log(`안녕하세요, ${this.name}입니다.`);
     },
   };

   export default obj;
   ```

2. 모듈 불러오는 파일

   ```jsx
   import obj from "./export.js";

   console.log(obj.name);
   obj.greet();
   ```

---

## <mark color="#fbc956">CommonJS 모듈</mark>

### 1. CommonJS 모듈

- ES 모듈 이전 Node.js에서 사용된 모듈 시스템
- 모듈 파일 확장자 : `.js`

### 2. 사용법

- **모듈 내보내기 : `module.exports`**
- **모듈 불러오기 : `... = require(모듈)`**

- Node.js 환경

1. 모듈 내보내는 파일

   ```jsx
   // export.js
   const name = "홍길동";

   function greet() {
     console.log(`안녕하세요. ${name}입니다.`);
   }

   module.exports = { name, greet };
   ```

2. 모듈 불러오는 파일

   ```jsx
   const { name, greet } = require("./export.js");

   console.log(name);
   greet();
   ```

---

## <mark color="#fbc956">패키지 (외부모듈)</mark>

### 1. 패키지

- 여러 모듈의 집합
- NPM(Node Package Manager)을 활용해 프로젝트에 패키지 설치, 삭제, 관리 가능
- 프로젝트에 설치된 패키지는 `package.json` 파일에 작성됨
- `package.json` 파일에 작성된 패키지 목록 통해 다른 환경에서도 동일한 패키지 설치 가능

### 2. NPM 기본 명령어

- **`npm install {패키지명}`**
  - 현재 프로젝트에 해당 패키지 설치
  1. **`npm install -g {패키지명}`**
     - 전역 환경 설치
     - 하나의 프로젝트가 아닌 PC 환경 전체에서 사용할 수 있게 패키지 설치
  2. **`npm install {패키지명} --save-dev`**
     - 개발용 패키지 설치
     - 배포 환경에 사용되지 않고, 오직 개발 환경에서만 사용할 수 있게 패키지 설치
     - 배포, 운영과 관련없는 개발 과정에만 사용되는 배키지 설치 시 사용
- **`npm uninstall {패키지명}`**
  - 현재 프로젝트에서 해당 패키지 삭제
- **`npm install`**
  - `package.json` 에 작성된 모든 패키지 읽어 설치
- **`npm update`**
  - 패키지를 최신 버전으로 업데이트
- **`npm list`**
  - 현재 프로젝트에 설치된 패키지 목록 출력
