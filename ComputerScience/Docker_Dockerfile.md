> 💡 **한 줄 요약**
>
> 도커 파일은 이미지의 설계도로 특정 목적을 수행하는 이미지 생성을 위한 명령어들을 작성한다. 도커는 이 도커 파일을 읽어 이미지를 빌드할 수 있다. 또한, 멀티 스테이지 빌드를 활용해 애플리케이션을 빌드하는 과정에서 필요하지만 최종 실행에 불필요한 파일 포함하지 않도록 설정할 수 있다.

### 1. 🤔 왜 사용하는가

- **Dockerfile**

  - 이미지의 설계도
  - 특정 목적을 수행하는 이미지 생성위한 명령어 모음
  - Docker는 Dockerfile을 읽어 이미지를 빌드할 수 있음

- **Dockerfile 문법**

  ```docker
  **# FROM [이미지 이름] : [태그]**
  # 이미지 빌드 시 사용할 베이스 이미지 지정
  FROM ubuntu:latest

  **# RUN [명령어]**
  # 이미지 빌드하는 동안 실행할 명령어 정의
  # 명령어 실행 결과들은 이미지에 적용됨
  # 주로 패키지 업데이트, 설치의 작업 등
  RUN apt-get update

  **# WORKDIR [컨테이너 내부 경로]**
  # 컨테이너 내부에서 명령어가 실행될 작업 폴더 설정
  # WORKDIR 명령어 이후 실행하는 명령어는 해당 경로에서 모두 실행
  WORKDIR /app

  **# COPY [호스트 경로] [컨테이너 내부 경로]**
  # 호스트 경로의 파일 또는 폴더를 컨테이너 내부 경로로 복사
  COPY ./index.html /usr/share/nginx/html

  **# ENV 변수=값**
  # 컨테이너 내부에서 사용할 환경 변수 설정
  ENV API_PORT=8080

  **# ARG 변수=값**
  # 이미지 빌드에 사용할 변수와 값 설정
  ARG Version=1.0

  **# EXPOSE [포트]**
  # 컨테이너 내부에서 사용할 포트 명시
  EXPOSE 80

  **# CMD [명령어]**
  # 컨테이너 실행 시 실행할 명령어 정의
  # 주로 스크립트, 서비스 서버 시작 등 작업 수행
  CMD ['node', 'app.js']
  ```

- **Dockerfile 명령어**

  - `docker build -t [이미지 이름]:[태그] .` : 현재 폴더의 Dockerfile 기반 이미지 빌드
  - `docker build -f [Dockerfile 이름] -t [이미지 이름]:[태그]` : Dockerfile 지정해 이미지 빌드

- **Node.js 애플리케이션을 위한 Dockerfile 작성 방법**

  1. 폴더 구성

     > 📁 node_Dockerfile
     >
     > - Dockerfile
     > - app.js

  2. app.js

     ```jsx
     console.log("Hello Dockerfile");
     ```

  3. Dockerfile

     ```docker
     # 베이스 이미지 지정
     FROM node:lts

     # 컨테이너 내부 작업 폴더를 설정
     # 없으면 알아서 생성함
     WORKDIR /app

     # 이미지를 생성할 때 실행되는 문법
     # [호스트] 파일 또는 폴더를 [컨테이너]로 복사
     # [호스트 파일] [컨테이너 경로]
     COPY ./app.js /app.js

     # 컨테이너 실행할 때 실행되는 문법
     CMD ["node", "app.js"]

     # 이미지를 생성할 때 실행되는 문법
     # [호스트] 파일 또는 폴더를 [컨테이너]로 복사
     # [호스트 파일] [컨테이너 경로]
     COPY ./package.json ./

     # 명령 실행
     # 이미지가 생성할 때 실행
     RUN npm install
     ```

  4. 이미지 빌드

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

- **Nginx 웹 서버를 위한 Dockerfile을 작성 방법**

  1. 폴더 구조

     > 📁 nginx_Dockerfile
     >
     > - Dockerfile
     > - index.html

  2. index.html

     ```html
     <!DOCTYPE html>
     <html lang="en">
       <head>
         <meta charset="UTF-8" />
         <meta
           name="viewport"
           content="width=device-width, initial-scale=1.0"
         />
         <title>Document</title>
       </head>
       <body>
         <h1>Hello Dockerfile</h1>
       </body>
     </html>
     ```

  3. Dockerfile

     ```docker
     FROM nginx:stable
     EXPOSE 80
     COPY index.html /usr/share/nginx/html/
     CMD ["nginx", "-g", "daemon off;"]
     ```

  4. 빌드

     ```bash
     docker build -t my-nginx .
     docker run -p 80:80 --name my-nginx-container -d my-nginx
     ```

- **멀티 스테이지 빌드**

  - 멀티 스테이지 빌드를 통해 이미지와 컨테이너 경량화
  - 서비스 실행과 상관없는 파일들을 제외하고 서비스 운영 가능

- **멀티 스테이지 빌드를 사용한 Spring Boot 애플리케이션을 위한 Dockerfile을 작성 방법**

  1. 서버 연결용 MySQL 컨테이너 생성

     ```bash
     docker run --name my-mysql -e MYSQL_ROOT_PASSWORD=1q2w3e4r! -e MYSQL_DATABASE=db -p 3307:3306 -d mysql:8.0
     ```

  2. 환경 변수 파일 `.env` 생성

     ```
     DATABASE_HOST=host.docker.internal
     DATABASE_PORT=3307
     DATABASE_NAME=db
     DATABASE_USERNAME=root
     DATABASE_PASSWORD=1q2w3e4r!
     ```

     - `host.docker.internal`
       - 도커 컨테이너 내부에서 호스트 네트워크와 통신하기 위한 DNS
       - Spring Boot 컨테이너에서 ↔ 호스트 거쳐 ↔ MySQL 컨테이너에 접근위해 사용

  3. Dockerfile.multistage

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

  4. 이미지 빌드

     ```bash
     docker build -f Dockerfile.multistage -t backend-image .
     ```

  5. 컨테이너 생성 및 실행

     ```bash
     docker run -p 8080:8080 --name multistage-container --env-file .env -d backend-image
     ```

- **멀티 스테이지 빌드를 사용하지 않는 Spring Boot 애플리케이션을 위한 Dockerfile을 작성 방법**

  1. 위와 동일
  2. 위와 동일
  3. Dockerfile

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

  4. 이미지 빌드

     ```bash
     docker build -f Dockerfile.no-multistage -t no-multistage-image .
     ```

  5. 컨테이너 생성 및 실행

     ```bash
     docker run -p 8082:8080 --name no-multistage-container --env-file .env -d no-multistage-image
     ```

- **이미지 크기에 따른 성능 차이**

  - 이미지의 크기는 컨테이너 성능에 직접적인 영향을 주지 않지만, 간접적인 영향 줌
    - 빌드 속도 → 이미지 클수록 빌드에 더 많은 시간 소요
    - 배포 속도 → 이미지를 네트워크로 전송할 경우 더 많은 시간 소요
    - 저장 공간 사용량 → 호스트의 저장 공간 더 많이 사용, 네트워크 서비스에서 더 많은 리소스는 더 많은 비용 필요

- **React 애플리케이션 빌드 & Nginx를 사용, 실행하는 멀티 스테이지 Dockerfile을 작성 방법**
  1. 환경 변수 파일 `.env`

     ```
     VITE_API_URL=/api
     ```

  2. 이미지 빌드

     ```bash
     docker build -t frontend-image .
     ```

  3. 컨테이너 생성 및 실행

     ```bash
     docker run -p 80:80 --name frontend-container --env-file .env -d frontend-image
     ```

### 2. ⚠️ 단점 및 🔄 개선 방법

- **Dockerfile을 작성할 때 주의 사항 및 개선**
  1. **불필요한 레이어 최소화**
     - Docker는 레이어 개념 사용해 이미지 구성
     - 불필요한 `RUN` 명령을 여러개 사용할 경우, 레이어 많아져 이미지 크기 커짐
     - `&&` 으로 명령을 하나로 합침
     - `rm -rf /var/lib/apt/lists/*` 을 통해 캐시 제거
  2. **가능한 작은 베이스 이미지 사용**
     - `ubuntu` 나 `debian` 같은 전체 OS 기반 이미지보다 가벼운 `Alpine Linux` 기반 이미지 사용
     - 작은 이미지일수록 빌드 속도 빨라짐, 보안 취약점 줄어듦
  3. **캐싱을 활용한 빌드 속도 최적화**
     - Docker는 위에서 아래로 명령어 실행 결과 캐싱함
     - 변경이 적은 명령어를 위쪽으로 배치할 경우, 캐시 활용해 빌드 속도 빨라짐
  4. **불필요한 파일 복사 방지 (`.dockerignore` 사용)**
     - `node_modules` ,`venv` , `logs` 같은 불필요한 파일이 컨테이너에 포함되지 않도록 `.dockerignore` 파일 설정
  5. **root 사용자 대신 비루트 사용자 실행**

     - Docker 컨테이너는 `root` 권한으로 실행
     - 보안 강화 위해 별도의 사용자 만들어 실행하는 것이 좋음

     ```docker
     FROM node:16
     WORKDIR /app
     COPY package.json package-lock.json /app/
     RUN npm install

     # 새로운 사용자 생성
     RUN useradd -m myuser
     USER myuser

     COPY . /app
     CMD ["node", "server.js"]
     ```

  6. **멀티 스테이지 빌드 활용 (Multi-stage Build)**
     - 애플리케이션을 빌드하는 과정에서 필요하지만, 최종 실행에는 불필요한 파일 포함하지 않도록 멀티 스테이지 빌드 사용
