## <mark color="#fbc956">Spring Boot 빌드 & 배포</mark>

> Spring Boot 프로젝트를 Gradle 빌드 도구를 사용해 JAR/WAR 파일로 패키징, 실행 및 배포

### 1. 프로젝트 빌드

> Spring Boot 애플리케이션의 빌드와 배포의 시작은 빌드 도구(Gradle, Maven)를 사용해 프로젝트를 JAR(Java ARchive) 또는 WAR(Web Application Archive) 파일로 패키징하는 것

### 2. Gradle vs Maven

> **자바 기반 빌드 도구**
>
> - 코드의 컴파일, 의존성 관리, 테스트 실행, 배포위해 사용
> - 대표적 예 : Gradle, Maven

- **Gradle**

  - Groovy 또는 Kotiln으로 작성된 스크립트를 기반으로 빌드 설정 정의
  - 증분 빌드(Incremental Build)와 빌드 캐싱(Build Cache) 지원해 빌드 속도 빠름
    - **증분 빌드(Incremental Build)**
      - 빌드 과정에서 변경 사항이 있는 코드만 부분적으로 빌드 수행
  - 멀티 쓰레드 환경에서 병렬 빌드 지원

- **Maven**
  - XML 파일로 빌드 설정 정의
  - 전체 빌드를 수행하기 때문에 Gradle과 비교했을 때 빌드 속도 느림

### 3. Gradle 프로젝트 설정

- 프로젝트 생성할 때 Gradle 도구 설정 후 Gradle로 빌드 가능

### 4. Gradle 빌드 설정

> `gradle.properties` 파일 작성해 빌드 시 여러가지 설정 조정 가능

- **`org.gradle.daemon`**

  - 백그라운드로 실행해 빌드 정보를 메모리에 유지
  - 연속적인 빌드 시 시간을 크게 단축 가능하지만 메모리 사용량 증가

- **`org.gradler.parallel`**

  - 태스크들을 병렬로 실행
  - 대규모 프로젝트에 적합, 그에 맞는 CPU 성능 필요

- **`org.gradle.workers.max`**

  - 병렬 작업에 사용할 워커 수 지정

- **`org.gradle.caching`**

  - 이전 빌드 결과물을 재사용해 빌드 시간 단축

- **`org.gradle.jvmargs`**

  - Gradle이 활용하는 JVM 메모리를 설정

- **`org.gradle.vfs.watch`**
  - 파일 변경을 감시해 변경된 파일만 빌드

### 5. 빌드와 실행

> **빌드 명령어**

- 기본 빌드
  ```bash
  ./gradlew build
  ```
- 클린 빌드 (build 폴더 삭제 후 빌드)
  ```bash
  ./gradlew clean build
  ```
- 테스트 없이 빌드
  ```bash
  ./gradlew build -x test
  ```
- 실행 파일만 생성
  ```bash
  ./gradlew bootJar
  ```
- 빌드 결과 폴더와 파일
  - `/build/libs/[폴더명]-0.0.1-SNAPSHOT.jar`

> **jar 파일 실행**

```bash
java -jar build/libs/backend-0.0.1-SNAPSHOT.jar
```

---

### ☀️ 오늘의 배움

> **Spring Boot 빌드와 배포**

- **빌드 도구**

  1. Maven
     - 러닝 커브가 상대적으로 괜찮음
  2. Gradle
     - 변경 사항만 부분적으로 빌드하는 증분 빌드가 가능해 빌드 속도가 빠름

- **빌드 설정**
  ![image.png](/Sesac/assets/day65_2.png)
  - `jar` : 서버를 구동시키는 파일
  - WAS (웹 애플리케이션 서버) : 동적인 데이터를 사용자에게 제공하는 서버
  - Tomcat : 내장되어 있는 웹 어플리케이션
