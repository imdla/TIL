> 💡 **한 줄 요약**
>
> `npm install` 과 `npm ci` 모두 의존성 목록을 설치하는 커맨드이지만, `npm ci` 는 `npm install` 에 비해 의존성의 버전을 엄격히 유지한다.

### 1. 🤔 왜 사용하는가

- **`npm install` 과 `npm ci`(clean-install)의 공통점**

  - 모두 의존성 목록을 설치하는 커맨드

- **`npm install` 과 `npm ci`(clean-install)의 차이점**

  - `npm ci` 는 `npm install`에 비해 의존성의 버전을 엄격히 유지함

  1.  `npm install` : `package.json`에 명시된 version range 내에서 다른 버전을 설치할 가능성 있음
      `npm ci` : 오직 `package-lock.json`에 정확하게 표기된 특정 버전 따름 - 이로인해 예기치 않게 다른 버전의 의존성을 설치하는 일 방지 - 정확히 명시된 버전을 설치해 버전을 결정하기 위한 연산을 수행할 필요 없음
      → 설치 속도에 유리

  2.  `npm install` : `package-lock.json`을 변경할 가능성 있음
      `npm ci` : 절대 변경하지 않음 - `npm ci`는 의존성 목록의 버전을 변경없이 일관되게 유지 가능
  3.  `npm ci` : 매번 `node_modules`을 삭제한 후 설치함 - 이전에 설치된 의존성과의 충돌로 인한 문제 방지 - 오로지 `package-lock.json`에 따라 매번 동일한 의존성을 설치할 것을 보장함
      ⇒ `npm ci`는 CI/CD 환경에서 빌드 과정의 일관성 보장 목적으로 사용 되는 경우가 많음

### 2. 💡 무엇인지 아는가(특징)

> **npm ci를 로컬 개발 환경에서도 사용해도 되는가 ?**

- 가능하지만, `npm ci`는 `node_modules`를 매번 모두 삭제 후 다시 설치해 불필요한 시간 소요 될 수 있음
  - 로컬에서는 일반적으로 `npm install` 사용
  - CI/CD 환경에서는 `npm ci`사용하는 경우 많음
