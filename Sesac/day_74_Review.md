## <mark color="#fbc956">0️⃣ 명령어</mark>

- **도커 명령어**

  - 모든 실행 중인 컨테이너 포함 삭제
    ```bash
    docker rm -f $(docker ps -aq)
    ```
  - 모든 사용 중인 이미지 포함 삭제
    ```bash
    docker rmi -f $(docker images -q)
    ```
  - 모든 이미지, 컨테이너, 네트워크, 볼륨 삭제
    ```bash
    docker system prune -a -f
    ```

- **도커 컴포즈 명령어**
  - 이미지 빌드와 컨테이너 실행
    ```bash
    docker compose -up --build
    ```
  - 컨테이너 삭제
    ```bash
    docker compose down
    ```

---

## <mark color="#fbc956">1️⃣ 준비</mark>

> **풀더 경로**
>
> - 📁 api
>   - `*.env*`
>   - `*Dockerfile*`
> - 📁 client
>   - `*Dockerfile*`
>   - `*nginx.conf*`
> - `*.env*`
> - `*docker-compose.yml*`

### **1. DB 서버 실행**

```bash
docker run -p 호스트포트:3306 -e MYSQL_ROOT_PASSWORD=1q2w3e4r! -e MYSQL_DATABASE=api mysql:8.0
```

### **2. 패키지 설치**

- Node 패키지 설치
- 패키지 `node_module` 동기화 작업

### **3. 프로젝트 실행**

- 프로젝트 실행 및 기능 테스트

---

## <mark color="#fbc956">2️⃣ 배포 파일 준비</mark>

### **1. 환경 변수 생성**

1. **Spring Boot 환경 변수 설정**
   - `properties` 파일 데이터베이스 설정을 환경 변수로 전환
     ```
     - 데이터베이스 서버 호스트
     - 데이터베이스 서버 포트
     - 데이터베이스 이름
     - 계정 이름
     - 계정 비밀번호
     ```
   - `src/main/resources/application.properties`
     ```yaml
     # DB 설정 환경 변수로 전환
     spring.datasource.url=jdbc:mysql://${DATABASE_HOST}:${DATABASE_PORT}/${DATABASE_NAME}
     spring.datasource.username=${DATABASE_USERNAME}
     spring.datasource.password=${DATABASE_PASSWORD}
     ```
2. **api 환경 변수**

   ```yaml
   DATABASE_HOST=db-container
   DATABASE_PORT=3306
   DATABASE_NAME=api
   DATABASE_USERNAME=root
   DATABASE_PASSWORD=1q2w3e4r!
   ```

3. **db 환경 변수**

   ```yaml
   MYSQL_DATABASE=api
   MYSQL_ROOT_PASSWORD=1q2w3e4r!
   ```

### **2. Ngin 설정**

1. **nginx 설정 파일 생성**

   ```
   - 포트 설정(listen)
   - 호스트 이름(server_name)
   - 정적 파일 위치(root)
   - 정적 파일 이름(index)
   - / 경로 처리 -> 정적 파일 서빙
   - /api 경로 처리 -> api 서버 프록시
   ```

- `nginx.conf`

  ```
  server {
      listen 80;
      server_name localhost;

      root   /usr/share/nginx/html;
      index  index.html;

      location / {
          try_files $uri /index.html;
      }

      location /api {
          # API 서버로 요청을 프록시
          proxy_pass http://api-container:8080;

          # 요청 헤더 설정
          proxy_http_version 1.1;                    # HTTP/1.1 사용 (Keep-Alive가 기본값, 지속적 연결 지원, 파이프라이닝 가능)
          proxy_set_header Connection "keep-alive";  # Keep-Alive 활성화 (여러 요청에 대해 단일 TCP 연결 재사용)
          proxy_set_header Host $host;                # 원본 호스트 정보 ex) localhost:80ㅁ
          proxy_set_header X-Real-IP $remote_addr;    # 실제 클라이언트 IP ex) 127.0.0.1
          proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;  # 프록시 서버를 거친 클라이언트의 IP 목록
      }
  }
  ```

### **3. Dockerfile 생성**

1. **Client (Frontend)**

   ```
   ### 빌드 스테이지 ###
   - 패키지 목록 복사
   - 패키지 설치
   - 소스코드 복사
   - 빌드

   ### 실행 스테이지 ###
   - nginx 설정 파일 복사
   - 빌드 결과물 복사
   - nginx 실행
   ```

   - `Dockerfile`

     ```docker
     # 베이스 이미지
     # 폴더 및 파일 복사
     # 프로젝트 빌드 명령어 실행
     # 서비스 실행 명령어 -> 컨테이너가 실행될 떄 CMD
     FROM node AS build
     WORKDIR /app

     # 패키지 설치를 위한 패키지 목록 파일 복사
     COPY package.json package-lock.json ./

     # 패키지 설치
     RUN npm install

     # 소스 코드 복사
     COPY . .

     # 프로젝트 빌드
     RUN npm run build

     # Run Stage
     FROM nginx

     COPY nginx.conf /etc/nginx/conf.d/default.conf

     # Build Stage에서 빌드 결과물(정적 파일) 복사
     COPY --from=build /app/dist /usr/share/nginx/html

     # 포트 명시
     # 문서화 목적
     EXPOSE 80

     CMD ["nginx", "-g", "daemon off;"]
     ```

2. **API (Backend)**

   ```
   ### 빌드 스테이지 ###
   - 패키지 목록 복사
   - 패키지 설정
   - 소스코드 복사
   - 빌드

   ### 실행 스테이지 ###
   - jar 파일 복사
   - jar 파일 실행
   ```

   - `Dockerfile`

     ```docker
     # 빌드 스테이지
     # 베이스 이미지
     # 의존성 설치
     # 소스 코드 복사 & 빌드

     # 실행 스테이지
     # jar 파일 복사
     # jar 파일 실행

     # Build stage
     FROM openjdk:21-jdk-slim AS build
     WORKDIR /app

     # 의존성 캐싱 레이어
     COPY build.gradle settings.gradle ./
     COPY gradle ./gradle
     COPY gradlew ./gradlew
     RUN chmod +x gradlew
     RUN ./gradlew dependencies --no-daemon

     # 소스 코드 복사 및 빌드
     COPY src ./src
     RUN ./gradlew build --no-daemon -x test

     # Run stage
     FROM eclipse-temurin:21-jre-alpine
     WORKDIR /app

     # 빌드된 jar 파일 복사
     COPY --from=build /app/build/libs/*.jar app.jar

     # 포트 설정
     EXPOSE 8080

     # 실행 명령
     ENTRYPOINT ["java", "-jar", "app.jar"]

     ```

### **4. Docker compose**

- `docker-compose` 작성 블록

  ```
  - services
  	- db
  		: 컨테이너명, 베이스 이미지, 볼륨, 네트워크, 헬스체크,
  			환경 변수 파일, 포트(선택)
  	- api
  		: 컨테이너명, Dockerfile 경로, 이미지명, 포트(선택), 네트워크,
  			환경 변수 파일, 의존 관계
  	- client
  		: 컨테이너명, Dockerfile 경로, 이미지명, 포트(선택), 네트워크,
  			환경 변수 파일, 의존 관계

  - networks
  	- 개별 볼륨
  - volumes
  	- 개별 네트워크
  ```

- `docker-compose.yml`

  ```yaml
  # 문법 버전(선택)

  # services 블록
  services:
    # 개별 컨테이너(서비스) 블록
    # # db
    # # # 컨테이너명, 베이스 이미지, 볼륨, 네트워크, 헬스체크, 환경변수파일, 포트(선택)
    db:
      container_name: db-container
      image: mysql:8.0
      volumes:
        # /var/lib/mysql - mysql 데이터 경로
        - db-volume:/var/lib/mysql
      networks:
        - db-network
      env_file:
        - .env
      healthcheck:
        test: ["CMD", "mysqladmin", "ping", "-h", "localhost"]
        interval: 10s
        timeout: 5s
        retries: 3
        start_period: 30s
    # # backend(api)
    # # # 컨테이너명, Dockerfile 경로, 이미지명, 포트(선택), 네트워크, 환경변수파일, 의존관계
    api:
      container_name: api-container
      build:
        # Dockerfile 경로
        context: ./api
        dockerfile: Dockerfile
      image: nodecrewbeemo/api-image:latest
      ports:
        - "8080:8080"
      networks:
        - db-network
        - api-network
      env_file:
        - .env
      depends_on:
        db:
          condition: service_healthy
    # # frontned(client)
    # # # 컨테이너명, Dockerfile 경로, 이미지명, 포트, 네트워크, 환경변수파일, 의존관계
    client:
      container_name: client-container
      build:
        context: ./client
        dockerfile: Dockerfile
      image: nodecrewbeemo/client:latest
      ports:
        - "80:80"
        - "443:443"
      networks:
        - api-network
      env_file:
        - .env
      depends_on:
        - db
        - api

  # volumes와 networks 블록
  volumes:
    db-volume:

  networks:
    db-network:
    api-network:
  ```

### 5. 배포 관련 기타 파일

- `api.gradle.properties`

  - gradle 빌드 옵션 설정 파일
  - GitHub Actions 환경 최적화 파일 설정

  ```yaml
  # CI/CD 환경 최적화 설정
  org.gradle.parallel=true             # 병렬 실행
  org.gradle.daemon=false              # 데몬 비활성화

  # GitHub Actions의 Ubuntu runner 스펙 (2코어, 7GB RAM)
  org.gradle.jvmargs=
      -Xmx5g                           # JVM 최대 힙 메모리를 5GB로 설정
      -XX:MaxMetaspaceSize=512m        # 메타스페이스(클래스 메타데이터) 최대 크기 512MB
      -XX:+HeapDumpOnOutOfMemoryError  # OOM 발생 시 힙 덤프 파일 생성

  org.gradle.workers.max=2             # 일하는 CPU의 최대 수
  ```

- `client/vite.config.js`

  - gzip 설정
  - gzip 설정 패키지 설치

  ```bash
  # client
  npm install -D vite-plugin-compression
  ```

  ```jsx
  import { defineConfig } from "vite";
  import react from "@vitejs/plugin-react";
  import compression from "vite-plugin-compression";

  // https://vite.dev/config/
  export default defineConfig({
    plugins: [
      react(),
      compression({
        algorithm: "gzip",
        ext: ".gz",
        threshold: 1024,
        deleteOriginFile: false,
      }),
    ],
    server: {
      proxy: {
        "/api": {
          target: "http://localhost:8080",
          changeOrigin: true,
          secure: false,
        },
      },
    },
  });
  ```

---

## <mark color="#fbc956">3️⃣ 운영 서버 배포 테스트</mark>

### 1. EC2 인스턴스 준비

- **인스턴스 준비**

  1. EC2 접속
  2. 인스턴스 시작
  3. 이름
  4. AMI : ubuntu
  5. 인스턴스 유형 : t3.small
  6. 키페어
  7. 보안그룹
  8. 스토리지 설정
  9. 인스턴스 생성

- **EC2 인스턴스 접속**
  - termius 통해 접속

### 2. SSH 연결 테스트 및 초기 세팅

1. **`sudo apt update`**
2. **원격 서버 초기 설정**

   - 도커 설치

     1. GPG 키 및 Apt 저장소 설정

        ```bash
        # Add Docker's official GPG key:
        sudo apt-get update
        sudo apt-get install ca-certificates curl
        sudo install -m 0755 -d /etc/apt/keyrings
        sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
        sudo chmod a+r /etc/apt/keyrings/docker.asc

        # Add the repository to Apt sources:
        echo \
          "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
          $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
          sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

        sudo apt-get update
        ```

     2. Docker 설치

        ```bash
        sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin -y
        ```

     3. Docker 사용 권한위해 사용자 추가

        ```bash
        sudo usermod -aG docker $USER
        ```

     4. 재실행

        ```bash
        sudo reboot
        ```

     5. 설치 확인

        ```bash
        docker -v
        docker run hello-world
        ```

     6. Docker compose 설치

        ```bash
        sudo curl -SL https://github.com/docker/compose/releases/download/v2.20.2/docker-compose-linux-x86_64 -o /usr/local/bin/docker-compose
        sudo chmod +x /usr/local/bin/docker-compose

        # 설치 확인
        docker-compose --version
        ```

### 3. 수동 배포

1. **로컬(개발 환경)에서 도커 이미지를 DockerHub로 `push`**

   > `image` 이름 확인
   >
   > - `도커허브계정명 / 이미지 : 태그`

   - `*docker login -u 도커허브계정*`
     - 성공 메시지 `Login Succeeded`
   - `docker compose push`
     - 현재 터미널 경로가 `docker-compose.yml` 이 있는 경로에서 진행

2. **배포 파일 복사**

   - `.env`
   - `docker-compose.yml`
   - 파일 확인 명령어 : `ls -al`

3. **DockerHub에서 원격 서버로 이미지 `pull`**

   - `docker compose pull`
     - 현재 터미널 경로가 `docker-compose.yml` 이 있는 경로에서 진행

4. **컨테이너 실행**

   - `docker compose up`

5. 웹 사이트 접속 테스트
   - `EC2 인스턴스 Public IPv4` 접속

---

## <mark color="#fbc956">4️⃣ 운영 서버 배포 자동화</mark>

### 1. git 준비

1. **git 프로젝트 초기화**
   - 주의 : **`.gitignore`** 작성 여부 확인
2. github 저장소 생성 및 push

### 2. 환경 변수 등록

- GitHub Actions Secrets 활용

  - settingd → secrets and variables → actions → `new repository secret`
  - `.env` 작성된 환경 변수

    ```bash
    # 백엔드 환경 변수
    DATABASE_HOST=db-container
    DATABASE_PORT=3306
    DATABASE_NAME=demo
    DATABASE_USERNAME=root
    DATABASE_PASSWORD=1q2w3e4r!

    # 데이터베이스 환경 변수
    MYSQL_ROOT_PASSWORD=1q2w3e4r!
    MYSQL_DATABASE=demo

    # 프론트엔드 환경 변수
    VITE_API_URL=/api
    ```

  - SSH 환경 변수
    ```bash
    - SSH_HOST
    - SSH_PORT
    - SSH_USERNAME
    - SSH_PRIVATE_KEY
    ```
  - Docker Hub 계정 정보
    ```bash
    # 도커 이미지 Push를 위한 로그인 계정 정보
    - DOCKER_USERNAME
    - DOCKER_PASSWORD
    ```

### 3. 워크플로우 작성

- `.github/workflows/[파일명].yml`

  ```yaml
  name: deploy service

  on:
    push:
      branches:
        - main

  jobs:
    ssh-agent: # Job 이름
      runs-on: ubuntu-24.04 # GitHub 워크스페이스 환경

      steps: # 실행할 작업(step)
        - name: Checkout code
          uses: actions/checkout@v4

        - name: Create .env file
          run: |
            echo "DATABASE_HOST=${{ secrets.DATABASE_HOST }}" >> .env
            echo "DATABASE_NAME=${{ secrets.DATABASE_NAME }}" >> .env
            echo "DATABASE_PASSWORD=${{ secrets.DATABASE_PASSWORD }}" >> .env
            echo "DATABASE_PORT=${{ secrets.DATABASE_PORT }}" >> .env
            echo "DATABASE_USERNAME=${{ secrets.DATABASE_USERNAME }}" >> .env
            echo "MYSQL_DATABASE=${{ secrets.MYSQL_DATABASE }}" >> .env
            echo "MYSQL_ROOT_PASSWORD=${{ secrets.MYSQL_ROOT_PASSWORD }}" >> .env

        - name: Add Remote Server Fingerprint to Known Hosts
          run: ssh-keyscan -H -p ${{ secrets.SSH_PORT }} ${{ secrets.SSH_HOST }} >> ~/.ssh/known_hosts || true

        - name: Login DockerHub
          run: echo '${{ secrets.DOCKER_PASSWORD}}' | docker login -u '${{ secrets.DOCKER_USERNAME }}' --password-stdin

        - name: Docker Image Build
          run: docker compose -f docker-compose.yml build

        - name: Docker Image Push
          run: docker compose -f docker-compose.yml push

        - name: Copy .env / docker-compose.yml
          uses: appleboy/scp-action@v0.1.7
          with:
            host: ${{ secrets.SSH_HOST }}
            username: ${{ secrets.SSH_USERNAME }}
            key: ${{ secrets.SSH_PRIVATE_KEY }}
            port: ${{ secrets.SSH_PORT }}
            source: "docker-compose.yml,.env"
            target: "~/github-actions-work-directory"

        - name: Pull Image & Up Container
          uses: appleboy/ssh-action@v1.0.3
          with:
            host: ${{ secrets.SSH_HOST }}
            username: ${{ secrets.SSH_USERNAME }}
            key: ${{ secrets.SSH_PRIVATE_KEY }}
            port: ${{ secrets.SSH_PORT }}
            script: |
              cd ~/github-actions-work-directory
              docker compose -f docker-compose.yml pull
              docker compose -f docker-compose.yml down
              docker compose -f docker-compose.yml up -d
  ```
