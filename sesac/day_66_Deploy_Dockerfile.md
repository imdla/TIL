## <mark color="#fbc956">도커 파일 (Dockerfile)</mark>

### 1. 이미지의 설계도

- 도커는 `Dockerfile` 을 읽어 이미지 빌드 가능
- 특정 목적을 수행하는 이미지를 생성하기 위한 명령어 모음이 작성됨

### 2. Dockerfile 문법

- **`FROM [이미지 이름]:[태그]`**

  - 이미지를 빌드할 때 사용할 베이스 이미지를 지정

  ```docker
  FROM ubuntu:latest
  ```

- **`RUN [명령어]`**

  - 이미지를 빌드하는 동안 실행할 명령어 정의
  - 명령어 실행 결과들은 이미지에 적용됨
  - 패키지 업데이트, 설치와 같은 작업들 수행에 사용

  ```docker
  RUN apt-get update
  ```

- **`WORDIR [컨테이너 내부 경로]`**

  - 컨테이너 내부에서 명령어가 실행될 작업 폴더 설정
  - `WORKDIR` 명령어 이후 실행하는 명령어는 해당 경로에서 모두 실행
  - 만약, 지정한 폴더가 컨테이너에 없다면 해당 폴더를 자동으로 생성

  ```docker
  WORKDIR /app
  ```

- **`COPY [호스트 경로] [컨테이너 내부 경로]`**

  - 호스트 경로의 파일 또는 폴더를 컨테이너 내부 경로로 복사
  - 동일한 이름의 파일이 있을 때 덮어씌움
  - 동일한 이름의 폴더가 있을 때 컨테이너 폴더 안으로 파일과 폴더를 복사

  ```docker
  # 호스트의 index.html 파일을 컨테이너의 /usr/share/nginx/html 폴더로 복사
  COPY ./index.html /usr/share/nginx/html
  ```

  ```docker
  # 호스트이 현재 폴더에 있는 모든 파일과 폴더를 컨테이너 현재 작업 폴더(WORKDIR)로 복사
  # 호스트의 현재 폴더는 Dockerfile이 존재하는 폴더를 의미
  COPY . .
  ```

- **`ENV 변수=값`**

  - 컨테이너 내부에서 사용할 환경 변수 설정
  - `docker run --env-file` 명령어처럼 파일을 직접적으로 불러올 수 없음
    - 컨테이너 생성 시 환경 변수 파일을 불러오거나 추후에 학습할 docker-compose 활용

  ```docker
  ENV API_PORT=8080
  ```

- **`ARG 변수=값`**

  - 이미지 빌드에 사용할 변수와 값을 설정
  - 환경 변수와 차이점 : ARG 변수는 이미지 빌드 중에만 사용하는 변수

  ```docker
  ARG Version=1.0
  RUN echo "Version :$Version"
  ```

- **`EXPOSE [포트]`**

  - 컨테이너 내부에서 사용할 포트를 명시만 함
  - 문서화를 목적으로 포트를 명시만 하는 역할
    → 컨테이너 생성 시 `-p` 옵션으로 포트 설정 필요

  ```docker
  EXPOSE 80
  ```

- **`CMD [명령어]`**
  - 컨테이너를 실행할 때 실행할 명령어를 정의함
  - 명령어와 인수를 배열에 저장한 형태로 작성
  - CMD 명령어가 여러 개 있으면 마지막 CMD만 실행됨
  - 스크립트, 서비스 서버 시작 등과 같은 작업 수행에 사용
  ```docker
  CMD ["node", "app.js"]
  ```

### 3. Dockerfile 명령어

- **`docker build -t [이미지 이름]:[태그] .`**

  - 현재 폴더의 `Dockerfile` 을 기반으로 이미지 빌드

  ```docker
  docker build -t myimage .
  ```

- **`docker build -f [Dockerfile 이름] -t [이미지 이름]:[태그]`**
  - Dockerfile을 지정해 이미지를 빌드
  ```docker
  docker build -f Dockerfile.test -t myimage-test .
  ```

---

## <mark color="#fbc956">Dockerfile 예시</mark>

### 1. Node.js Dockerfile

1. 파일 구조

   > 📁 node_Dockerfile
   >
   > - Dockerfile
   > - app.js

2. `app.js`

   ```jsx
   console.log("Hello Docker");
   ```

3. Dockerfile 정보
   - 베이스 이미지 : `node:lts`
   - 작업 폴더 설정 : `/app`
   - 파일 복사 : [호스트] `app.js` → [컨테이너] `/app`
   - 컨테이너 실행 명령어 : `["node", "app.js"]`
4. 이미지 빌드
   - 이미지 이름 : my-node
5. 컨테이너 생성 및 실행
   - 컨테이너 종료와 함께 컨테이너가 삭제되는 옵션 설정

- **`node.js Dockerfile`**

  - **1️⃣ 컨테이너 내부에 파일 복사**

    ```docker
    # 베이스 이미지 지정
    FROM node:lts

    # [호스트] 파일 또는 폴더를 [컨테이너]로 복사
    # [호스트 파일] [컨테이너 경로]
    COPY ./app.js /app.js
    ```

    ```bash
    # Dockerfile 폴더 경로에서 진행
    # /dockerfile/node/app.js Dockerfile
    docker build -t my-node .

    # my-node가 있는지 images 확인
    docker images

    docker run --name my-node-container -itd my-node

    docker exec -it my-node-container bash

    # 컨테이너 내부에 app을 복사했는지 확인
    ls
    ```

  - **2️⃣ 컨테이너 내부에 복사 및 CMD 실행**

    ```docker
    # 베이스 이미지 지정
    FROM node:lts

    # 이미지를 생성할 때 실행되는 문법
    # [호스트] 파일 또는 폴더를 [컨테이너]로 복사
    # [호스트 파일] [컨테이너 경로]
    COPY ./app.js /app.js

    # 컨테이너 실행할 때 실행되는 문법
    CMD ["node", "app.js"]
    ```

    ```bash
    docker build -t my-node .
    docker rm -f my-node-container
    docker run --name my-node-container -itd my-node
    docker exec -it my-node-container bash
    ```

  - **3️⃣ 컨테이너 내부 작업 폴더 생성 및 파일 복사 및 명령 실행**

    ```docker
    # 베이스 이미지 지정
    FROM node:lts

    # 컨테이너 내부 작업 폴더를 설정
    # 없으면 알아서 생성함
    WORKDIR /app

    # 이미지를 생성할 때 실행되는 문법
    # [호스트] 파일 또는 폴더를 [컨테이너]로 복사
    # [호스트 파일] [컨테이너 경로]
    COPY ./package.json ./

    # 명령 실행
    # 이미지가 생성할 때 실행
    RUN npm install
    ```

    ```bash
    # package.json 생성됨
    npm init
    npm install axiox

    # 이미지 빌드
    docker build -t my-node .

    # 백그라운드 컨테이너 실행
    docker run -itd --name node-container my-node

    # 터미널 진입
    docker exec -it node-container bash
    ```

### 2. Nginx Dockerfile

1. 파일 구조

   > 📁 nginx_Dockerfile
   >
   > - Dockerfile
   > - index.html

2. `index.html`

   ```html
   <!DOCTYPE html>
   <html lang="en">
     <head>
       <meta charset="UTF-8" />
       <meta name="viewport" content="width=device-width, initial-scale=1.0" />
       <title>Document</title>
     </head>
     <body>
       <h1>Hello Dockerfile</h1>
     </body>
   </html>
   ```

3. Dockerfile 정보
   - 베이스 이미지 : `nginx:stable`
   - 포트 명시 : `80`
   - 파일 복사 : [호스트] `index.html` → [컨테이너] `/usr/share/nginx/html/`
   - 컨테이너 실행 명령어 : `["nginx", "-g", "daemon off;"]`
4. 이미지 빌드
   - 이미지 이름 : my-nginx
5. 컨테이너 생성 및 실행
   - 컨테이너 이름 : my-nginx-container
   - 백그라운드 실행
   - 포트 매핑 : `80:80`

- **`Nginx Dockerfile`**
  ```docker
  FROM nginx:stable
  EXPOSE 80
  COPY index.html /usr/share/nginx/html/
  CMD ["nginx", "-g", "daemon off;"]
  ```
  ```bash
  docker build -t my-nginx .
  docker run -p 80:80 --name my-nginx-container -d my-nginx
  ```

---

## <mark color="#fbc956">멀티 스테이지 빌드 (Multi-stage build)</mark>

### 1. 멀티 스테이지 빌드

- 하나의 Dockerfile에서 빌드 단계를 여러 단계로 나눠 작성
- 애플리케이션 배포 위한 프로젝트의 빌드 과정과 실행 환경을 분리 가능
  - 빌드 과정은 모든 코드를 다루기 때문에 이미지 무거움
  - 그래서 빌드 결과물만 실행 환경에 전달
  - 실행 환경은 최소한의 데이터와 가벼운 베이스 이미지를 사용해 최적화된 환경에서 프로젝트 실행

### 2. 멀티 스테이지 빌드 필요성

- 애플리케이션을 실행만 하는 이미지와 개발 & 실행 이미지를 비교
  - 실행만 하는 이미지가 가벼움
- 멀티 스테이지 빌드 활용 시 이미지와 컨테이너를 경량화할 수 있음
- 멀티 스테이지 빌드 활용 시 서비스 실행과 상관없는 파일들을 제외하고 서비스 운영 가능

### 3. Spring Boot 멀티 스테이지 빌드 비교

1. 서버 연결용 MySQL 컨테이너 생성

   ```bash
   docker run --name my-mysql -e MYSQL_ROOT_PASSWORD=1q2w3e4r! -e MYSQL_DATABASE=db -p 3307:3306 -d mysql:8.0
   ```

2. 환경 변수 파일 `.env` 작성

   ```bash
   DATABASE_HOST=host.docker.internal
   DATABASE_PORT=3307
   DATABASE_NAME=db
   DATABASE_USERNAME=root
   DATABASE_PASSWORD=1q2w3e4r!
   ```

   - **`host.docker.internal`**
     - 도커 컨테이너 내부에서 호스트 네트워크와 통신하기 위한 DNS
     - Spring Boot 컨테이너에서 호스트를 거쳐 mysql 컨테이너에 접근하기 위해 사용
     - Spring Boot 컨테이너 ↔ 호스트 PC ↔ mysql 컨테이너

3. 이미지 & 컨테이너 생성

   - 멀티 스테이지 빌드

     - `Dorckerfile.multistage`

       ```docker
       # 멀티 스테이지 빌드 활용 dockerfile

       # 빌드 단계 ------------------------------------
       # 별칭(AS) : build
       FROM openjdk:21-jdk-slim AS build

       # 필요한 기본 패키지 설치
       RUN apt-get update && apt-get install -y dos2unix

       # 컨테이너 내부 작업 디렉토리 설정
       WORKDIR /app

       # Gradle 관련 파일만 먼저 복사
       COPY gradle gradle
       COPY build.gradle settings.gradle gradlew ./

       # gradlew 파일 변환 및 권한 설정
       RUN dos2unix gradlew && \
           chmod +x gradlew

       # 의존성 다운로드 (캐시 활용을 위해 소스 코드 복사 전에 실행)
       RUN ./gradlew dependencies --no-daemon

       # 소스 코드 복사
       COPY src src

       # 프로젝트 빌드
       RUN ./gradlew bootJar --no-daemon

       # ---------------------------------------------
       # 실행 단계 ------------------------------------
       FROM eclipse-temurin:21-jre-alpine

       # 컨테이너 내부 작업 디렉토리 설정
       WORKDIR /app

       # 빌드 단계에서 생성된 JAR 파일 복사
       COPY --from=build /app/build/libs/*.jar app.jar

       # Spring Boot 프로젝트 포트 노출
       EXPOSE 8080

       # 프로젝트 실행 시 명령어
       CMD ["java", "-jar", "app.jar"]
       ```

     - 이미지 빌드 명령어
       ```docker
       docker build -f Dockerfile.multistage -t backend-image .
       ```
     - 컨테이너 생성 & 실행 명령어
       ```docker
       docker run -p 8080:8080 --name multistage-container --env-file .env -d backend-image
       ```

   - no 멀티 스테이지 빌드

     - `Dockerfile.no-multistage`

       ```docker
       FROM eclipse-temurin:21-jdk-alpine

       WORKDIR /app

       COPY build.gradle settings.gradle gradlew ./
       COPY gradle ./gradle

       RUN chmod +x gradlew

       RUN ./gradlew dependencies --no-daemon
       COPY . ./

       RUN ./gradlew bootJar --no-daemon

       EXPOSE 8080

       CMD ["java", "-jar", "/app/build/libs/app.jar"]
       ```

     - 이미지 빌드 명령어
       ```docker
       docker build -f Dockerfile.no-multistage -t no-multistage-image .
       ```
     - 컨테이너 생성 & 실행 명령어
       ```docker
       docker run \
         -p 8082:8080 \
         --name no-multistage-container \
         --env-file .env \
         -d no-multistage-image
       ```

   - **이미지 크기가 성능에 주는 영향**
     - 이미지의 크기는 컨테이너 성능에 직접적인 영향 주지 않음
     - 결과적으로 컨테이너에 의해 실행되는 애플리케이션은 달라지지 않기 때문
     - 하지만, 이미지의 크기는 간접적인 영향을 줌
       - 빌드 속도 : 이미지가 클수혹 더 많은 시간 필요
       - 배포 속도 : 이미지를 네트워크로 전송해야할 필요 있을 때 더 많은 시간 소요
       - 저장 공간 사용량 : 호스트의 저장 공간을 더 많이 사용하게 되며 네트워크 서비스에서 더 많은 리소스는 더 많은 비용 유발

### 4. React 빌드 & Nginx 실행 멀티 스테이지

1. 환경 변수 파일 `.env` 작성

   ```docker
   VITE_API_URL=/api
   ```

2. 이미지 빌드

   ```docker
   docker build -t frontend-image .
   ```

3. 컨테이너 생성 & 실행

   ```docker
   docker run -p 80:80 --name frontend-container -env-file .env -d feontend-image
   ```

---

### ☀️ 오늘의 배움

- **도커 파일**

  - 이미지의 설계도
  - `Dockerfile`
    - Docker Hub에 존재하는 이미지만 베이스 이미지로 사용 가능

- **멀티 스테이지 빌드**
  - 빌드하는 과정과 실행하는 과정을 나눔
    - 빌드하는 과정에서 진행된 spring code는 남아있을 필요가 없음
    - 실행하는 과정에서 결과만 남김
- **멀티 스테이지 빌드**

  ```bash
  # 멀티 스테이지 빌드 활용 dockerfile

  # 빌드 단계 ------------------------------------
  # 별칭(AS) : build
  FROM openjdk:21-jdk-slim AS build

  # 필요한 기본 패키지 설치
  RUN apt-get update && apt-get install -y dos2unix

  # 컨테이너 내부 작업 디렉토리 설정
  WORKDIR /app

  # 프로젝트 전체 파일 복사
  COPY . ./

  RUN dos2unix gradlew && \
      chmod +x gradlew

  # 프로젝트 빌드
  RUN ./gradlew build --no-daemon

  # ---------------------------------------------
  # 실행 단계 ------------------------------------
  FROM eclipse-temurin:21-jre-alpine

  # 컨테이너 내부 작업 디렉토리 설정
  WORKDIR /app

  # 빌드 단계에서 생성된 JAR 파일 복사
  COPY --from=build /app/build/libs/*.jar app.jar

  # Spring Boot 프로젝트 포트 노출
  EXPOSE 8080

  # 프로젝트 실행 시 명령어
  CMD ["java", "-jar", "app.jar"]
  ```
