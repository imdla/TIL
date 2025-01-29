## <mark color="#fbc956">Docker Compose</mark>

### 1. 멀티 컨테이너 관리 도구

- 여러 개의 애플리케이션에 대한 컨테이너를 정의 및 실행하기 위한 도구
- 서비스, 네트워크, 볼륨, 환경 변수 등을 `docker-compose.yml` 파일에서 관리
- **리눅스 docker-compose 설치**

  - 윈도우와 맥에서는 Docker와 함께 설치됨
  - 리눅스에서는 docker-compose 설치 필요

  ```bash
  sudo curl -SL https://github.com/docker/compose/releases/download/v2.20.2/docker-compose-linux-x86_64 -o /usr/local/bin/docker-compose
  sudo chmod +x /usr/local/bin/docker-compose

  docker-compose --version
  ```

### 2. docker-compose.yml

- 여러 개의 docker 컨테이너(서비스)를 관리하는 파일
- Docker는 `docker-compose.yml` 파일을 기반으로 이미지 빌드하고, 컨테이너 생성 및 실행, 관리 가능

### 3. docker-compose.yml 문법

- **이미지 기반 컨테이너 생성 - nginx 컨테이너**

  - 1️⃣ `nginx/docker-compose.yml`

    ```yaml
    # nginx/docker-compose.yml
    # 필수 아님
    # docker-compose 문법 버전
    version: "3.8"

    # 관리할 것들을 명시
    # 컨테이너(서비스) 관리 블록들의 최상위 블록
    services:
      # docker-compose의 서비스 이름
      nginx:
        # 어떤 이미지 사용할 것인지
        image: nginx:stable
        # 포트 명시
        # 컨테이너 실행 시 네트워크, 환경변수들을 같이 명시
        ports:
          - "80:80"
    # 위의 명령어들은
    # docker run -p 80:80 nginx:stable 과 동일한 상태
    ```

    ```bash
    # 컨테이너 생성 및 시작
    docker-compose up

    # 컨테이너 중지 및 삭제
    docker-compose down
    ```

    - `version`
      - docker-compose.yml 파일의 버전을 명시
    - `services`
      - 서비스(컨테이너) 그룹 블록
    - `image`
      - Docker Hub에 저장된 이미지 이름
    - `ports`
      - 호스트와 컨테이너의 포트 매핑

- **이미지 기반 컨테이너 생성 - mysql 컨테이너**

  - 2️⃣ `mysql/docker-compose.yml` - `.env` 활용

    ```yaml
    # mysql/docker-compose.yml
    version: "3.8"

    services:
      db:
        image: mysql:8.0
        ports:
          - "3307:3306"
        # [호스트 포트] : [컨테이너 포트]
        environment:
          MYSQL_ROOT_PASSWORD: 1q2w3e4r!
          MYSQL_DATABASE: demo
        # 위의 명령어는
        # docker run -e MYSQL_ROOT_PASSWORD=1q2w3e4r! 와 동일
    ```

    ```bash
    # 컨테이너 생성 및 시작
    docker-compose up

    # 컨테이너 중지 및 삭제
    docker-compose down
    ```

    - `env_file`

      - 이미지를 빌드할 때 읽어올 환경 변수 파일 경로 정의

    - `.env` 생성

      - 환경변수의 값이 그대로 드러나는 것은 좋지 않음
      - 환경 변수 파일 생성

      ```
      MYSQL_ROOT_PASSWORD: 1q2w3e4r!
      MYSQL_DATABASE: demo
      ```

      ```yaml
      # 환경 변수를 사용하는 방법 2가지

      # 1. 직접 변수를 불러오기
      environment:
            MYSQL_ROOT_PASSWORD: ${MYSQL_ROOT_PASSWORD}
            MYSQL_DATABASE: ${MYSQL_DATABASE}

      # 2. 환경 변수 파일 불러오기
      env_file:
      			- .env
      ```

      ```bash
      # 컨테이너 생성 및 시작
      docker-compose up

      # 컨테이너 중지 및 삭제
      docker-compose down

      # 이미지를 새롭게 빌드해줌
      # down 진행 후 build 진행
      docker-compose up --build
      ```

  - 3️⃣ `mysql/docker-compose.yml` - Network, Volume 활용

    ```yaml
    # mysql/docker-compose.yml
    version: "3.8"

    services:
      db:
        image: mysql:8.0
        ports:
          - "3307:3306"
        env_file:
          - .env
        # db와 연결하고 싶을 때 사용하는 네트워크 드라이버
        networks:
          - db-connect
        volumes:
          -  # Named Vloume:[컨테이너 경로]
          - db-volume:/var/lib/mysql

    # 컨테이너에 적용할 네트워크를 관리하는 블록
    networks:
      db-connect:

    volumes:
      db-volume:
    ```

    ```bash
    # 컨테이너 생성 및 시작
    docker-compose up

    # 컨테이너 중지 및 삭제
    docker-compose down

    # volume과 network 생성 확인
    docker volume ls
    docker network ls
    ```

    - `networks`
      - 컨테이너가 연결된 네트워크 작성
      - 최하단 networks 블록에서 네트워크 설정 가능
      - 추가 설정이 없을 경우 bridge 네트워크로 생성
    - `volume`
      - 컨테이너가 연결될 볼륨 정의
      - 최하단 volumes 블록에서 볼륨 설정 가능

- **Dockerfile 기반 컨테이너 생성**

  - 4️⃣ `tic-tac-toe` - Dockerfile 활용

    ```docker
    # 빌드 스테이지
    FROM node:23-alpine AS build
    WORKDIR /app
    COPY package*.json ./
    RUN npm install
    COPY . .
    RUN npm run build

    # 실행 스테이지
    FROM nginx:mainline-alpine-slim
    COPY --from=build /app/dist /usr/share/nginx/html
    COPY nginx.conf /etc/nginx/nginx.conf
    EXPOSE 80
    CMD ["nginx", "-g", "daemon off;"]
    ```

    ```yaml
    version: "3.8"

    services:
      # 서비스 이름
      tic-tac-toe:
        # 컨테이너 이름 지정
        container_name: tic-tac-toe-container
        # docker run --name tic-tac-toe-container와 동일

        # 이미지를 Dockerfile 기반으로 build할 것임
        build:
          # Dockerfile이 있는 위치 지정
          context: ./
          # Dockerfile의 이름
          dockerfile: Dockerfile

        # 빌드(Dockerfile) 없이 사용 시 Docker Hub에서 가져옴
        # 빌드(Dockerfile)와 함께 사용시 이미지의 이름을 의미
        image: tic-tac-toe-image:latest
        ports:
          - "80:80"
    ```

    ```bash
    # 컨테이너 생성 및 시작
    docker-compose up

    # 생성된 이미지 확인
    docker images
    ```

    - `container_name`
      - 생성되는 컨테이너의 이름 정의
    - `build`
      - Dockerfile을 사용해 이미지를 빌드하기 위한 블록
      - `context` : Dockerfile 경로
      - `dockerfile` : Dockerfile 파일 이름 (기본값 : Dockerfile)
    - `image`
      - build 블록 다음 image 블록은 빌드로 생성되는 이미지의 이름 정의

### 4. docker-compose 명령어

- **`docker-compose up`**

  - 컨테이너 생성 및 시작
  - `--build` : 컨테이너 시작 전 이미지를 다시 빌드
  - `-d` : 백그라운드에서 컨테이너를 실행

- **`docker-compose down`**

  - 컨테이너 및 네트워크를 중지 및 삭제
  - `--volumes` : 연관된 볼륨도 삭제
  - `-rmi all` : 연관된 이미지도 삭제

- **`docker-compose logs`**

  - 컨테이너들의 로그 출력
  - `-f` : 실시간 로그 출력

- **`docker-compose build`**

  - 이미지를 빌드
  - `--no-cache` : 캐시를 사용하지 않고 빌드

- **`docker-compose push`**

  - Docker Hub에 Dockerfile로 빌드한 이미지를 업로드
  - 이미지의 이름을 `사용자명/이미지명:태그` 로 작성

- **`docker-compose pull`**

  - docker-compose.yml에 정의된 이미지를 Docker Hub에서 다운로드함

- **`docker-compose start`**

  - 중지된 컨테아너를 다시 시작

- **`docker-compose stop`**

  - 실행 중인 컨테이너를 중지

- **`docker-compose restart`**
  - 컨테이너를 다시 시작

### 5. 서비스 배포

- Database, Spring Boot, React 를 빌드 및 실행

  - 5️⃣ `project` - frontend + backend (healthycheck 활용)

    ```yaml
    services:
      db:
        container_name: db-container
        image: mysql:8.0
        volumes:
          - db-volume:/var/lib/mysql
        env_file:
          - .env
        networks:
          - db-connect
        # db 컨테이너 실행 시 프로그램이 생성 및 최초 실행 시 초기화되는 시간 필요
        # compose 최초 실행 시 db 컨테이너 내부의 MySQL 서버 설정이 안되어있는 경우가 있어
        # backend 입장에서 Spring Boot가 실행되면 db 서버가 설정이 시간이 필요하기에 못찾음
        # (db 서버가 실행이 안된 상태)
        # 최초 1회 서버 설정이 완료되었는지 옵션을 통해 확인
        healthcheck:
          test: ["CMD", "mysqladmin", "ping", "-h", "localhost"]
          interval: 10s
          timeout: 5s
          retries: 3
          start_period: 30s

      backend:
        container_name: backend-container
        build:
          context: ./backend
        image: backend-image:latest
        env_file:
          - .env
        networks:
          - db-connect
          - backend-connect
        # 컨테이너 생성 순서 지정, 의존성 설정
        # db 실행 후 backend 실행
        depends_on:
          db:
            condition: service_healthy

      frontend:
        container_name: frontend-container
        build:
          context: ./frontend
        image: frontend-image:latest
        ports:
          - "80:80"
        networks:
          - backend-connect
        depends_on:
          - backend
          - db

    volumes:
      db-volume:

    networks:
      db-connect:
      backend-connect:
    ```

    - `services`
      - 서비스(컨테이너) 모음을 정의하는 최상단 요소
    - `container_name`
      - 생성되는 컨테이너 이름을 정의
    - `build`
      - dockerfile을 기반으로 이미지 빌드 시 정의
      - `context` : dockerfile 경로 지정
      - `args` : dockerfile 빌드 시 `ARG` 속성에 값을 전달
        - `${VITE_API_URL}` : `.env` 파일을 읽어 `VITE_API_URL` 값을 불러옴
    - `image`
      - 기존 이미지를 기반으로 컨테이너를 생성
      - 만약 `build` 블록 다음에 정의된 후 사용할 경우 빌드된 이미지의 이름이 됨
    - `ports`
      - 호스트와 컨테이너 간 포트 매핑
      - 호스트 포트를 명시하지 않을 경우, 임의의 호스트 포트와 매핑됨
      - 포트를 매핑하지 않을 경우 동일한 네트워크를 공유하는 컨테이너와만 통신 가능
    - `env_file`
      - `.env` 파일을 읽어 환경 변수로 설정
    - `volumes`
      - 컨테이너의 볼륨을 설정
    - `networks`
      - 컨테이너에 연결할 네트워크를 정의
      - 동일한 네트워크에 연결될 컨테이너 간에 네트워크 공유하며 서로 직접 통신 가능
        - 다른 컨테이너와 통신할 때 컨테이너의 서비스 이름(DNS)로 접근 가능
        - Spring Boot에서 Database와 통신할 때 db:3306 으로 접근 가능
      - Bridge 네트워크에 연결하였을 때 다른 Bridge 네트워크에 연결된 컨테이너는 서로 통신 불가능
    - `depends_on`
      - 컨테이너 사이의 의존 관계 설정
      - 특정 컨테이너 실행 후 현재 컨테이너 실행함
    - `healthcheck`

      - 컨테이너 실행 상태 확인
      - `test` : 컨테이너 실행 상태를 확인하기 위한 명령어 정의
      - `interval` : 상태 확인 실행 간격
      - `timeout` : 상태 확인 명령어가 완료될 때까지 대기하는 시간
      - `retries` : 시도 횟수

    - 환경 변수 파일 `.env` 작성
      - `docker-compose.yml` 과 동일 폴더에 작성

    ```yaml
    # 백엔드 환경 변수
    DATABASE_HOST=db-container
    DATABASE_PORT=3306
    DATABASE_NAME=demo
    DATABASE_USERNAME=root
    DATABASE_PASSWORD=1q2w3e4r!

    # 데이터베이스 환경 변수
    MYSQL_ROOT_PASSWORD=1q2w3e4r!
    MYSQL_DATABASE=demo
    ```

    - 컨테이너 생성 및 실행

    ```bash
    # 컨테이너 생성 및 시작
    docker-compose up --build
    ```

  - backend/Dockerfile

    ```docker
    # 멀티 스테이지 빌드를 활용하여 이미지 크기를 최소화하는 Dockerfile

    # 빌드 단계 ------------------------------------
    # JDK 21 슬림 버전을 사용하여 빌드 환경 구성
    # 별칭(AS) : build를 통해 다음 스테이지에서 참조 가능
    FROM openjdk:21-jdk-slim AS build

    # Windows에서 작성된 gradlew 파일을 Linux 환경에서 실행하기 위한 패키지 설치
    RUN apt-get update && apt-get install -y dos2unix

    # 모든 작업이 이루어질 컨테이너 내부 디렉토리 지정
    WORKDIR /app

    # 빌드에 필요한 Gradle 관련 파일들을 먼저 복사
    # 소스코드가 변경되어도 의존성은 캐시된 레이어를 재사용
    COPY gradle gradle
    COPY build.gradle settings.gradle gradlew ./

    # gradlew 파일의 줄바꿈 문자를 변환하고 실행 권한 부여
    RUN dos2unix gradlew && \
        chmod +x gradlew

    # 의존성을 미리 다운로드하여 캐시 레이어 생성
    # --no-daemon 옵션으로 데몬 프로세스 없이 한 번만 실행
    RUN ./gradlew dependencies --no-daemon

    # 애플리케이션 소스 코드 복사
    COPY src src

    # Spring Boot 애플리케이션을 실행 가능한 단일 JAR 파일로 패키징
    RUN ./gradlew bootJar --no-daemon

    # ---------------------------------------------
    # 실행 단계 ------------------------------------
    # JRE 환경만 포함된 알파인 리눅스 기반의 가벼운 이미지 사용
    FROM eclipse-temurin:21-jre-alpine

    # 애플리케이션 실행을 위한 디렉토리 설정
    WORKDIR /app

    # 빌드 스테이지에서 생성된 JAR 파일만 복사
    # 빌드 환경의 불필요한 파일들은 제외하여 이미지 크기 최소화
    COPY --from=build /app/build/libs/*.jar app.jar

    # 컨테이너 실행 시 접근 가능한 포트 정보 제공
    EXPOSE 8080

    # 컨테이너 실행 시 JAR 파일을 실행하는 명령어
    CMD ["java", "-jar", "app.jar"]
    ```

  - frontend/Dockerfile

    ```docker
    # React 빌드 단계 ------------------------------------
    # Node.js 알파인 리눅스 기반의 가벼운 이미지 사용
    # 별칭(AS) : build를 통해 다음 스테이지에서 참조 가능
    FROM node:23-alpine AS build

    # 모든 작업이 이루어질 컨테이너 내부 디렉토리 지정
    WORKDIR /app

    # 빌드에 필요한 package.json 파일들을 먼저 복사
    # 소스코드가 변경되어도 의존성은 캐시된 레이어를 재사용
    COPY package*.json ./

    # npm을 통해 프로젝트 의존성 설치
    RUN npm install

    # 애플리케이션 소스 코드 복사
    COPY . .

    # React 애플리케이션을 정적 파일로 빌드
    RUN npm run build

    # ---------------------------------------------
    # Nginx 실행 단계 ------------------------------------
    # Nginx 알파인 리눅스 기반의 가벼운 이미지 사용
    FROM nginx:mainline-alpine-slim

    # 빌드 스테이지에서 생성된 정적 파일들을 Nginx의 서비스 디렉토리로 복사
    COPY --from=build /app/dist /usr/share/nginx/html

    # Nginx 설정 파일 복사
    COPY nginx.conf /etc/nginx/nginx.conf

    # 컨테이너 실행 시 접근 가능한 포트 정보 제공
    EXPOSE 80

    # Nginx를 포그라운드에서 실행하는 명령어
    CMD ["nginx", "-g", "daemon off;"]
    ```

    ```jsx
    worker_processes 1;

    events {
        worker_connections 1024;
    }

    http {
        include       mime.types;
        default_type  application/json;

        log_format main '$remote_addr - $remote_user [$time_local] "$request" '
                        '$status $body_bytes_sent "$http_referer" '
                        '"$http_user_agent" "$http_x_forwarded_for" "$request_time" '
                        '"$upstream_response_time" "$upstream_addr"';

        access_log /var/log/nginx/access.log main;
        error_log /var/log/nginx/error.log;

        keepalive_timeout 60;

        gzip_static on;

        gzip_vary on;

        server {
            listen 80;

            server_name localhost;

            root /usr/share/nginx/html;

            index index.html;

            location / {
                try_files $uri /index.html;
            }

    				# backend-container : IP 주소 없이 컨테이너 이름으로 연결
            location /api/ {
                proxy_pass http://backend-container:8080;
                proxy_set_header Host $host;
            }
        }
    }
    ```

- **DB 서비스 healthcheck 필요성**

  - 최초 DB 컨테이너 생성 시 데이터베이스 초기화를 위한 시간 필요
  - 초기화 중 backend 컨테이너 생성 및 실행되면 JDBC의 데이터베이스 연결이 실패함
  - 이를 방지위해 데이터베이스의 초기화가 완료되었는지 healthcheck 해야 함

  - **healthcheck 없을 경우**
    - DB 컨테이너 생성 및 실행
      → 데이터베이스 초기화 시작
      → backend 컨테이너 생성 및 실행 (오류)
      → 데이터베이스 초기화 완료
  - **healthcheck 있을 경우**
    - DB 컨테이너 생성 및 실행
      → 데이터베이스 초기화 시작
      → 데이터베이스 초기화 완료
      → backend 컨테이너 생성 및 실행 (정상)

---

### ☀️ 오늘의 배움

- **Docker Compose**

  - 이미지 및 컨테이너를 컨트롤하는 역할
  - `docker-compose.yml` 파일을 기반으로 이미지 및 컨테이너를 관리 감독함

- **도커 네트워크**

  - `None`
    - 격리됨
    - 네트워크가 없음
  - `Host`
    - 컨테이너 네트워크를 생성하지 않고 호스트 네트워크를 그대로 공유
  - `Bridge`
    - 격리된 네트워크를 사용
    - 컨테이너 이름으로 네트워크 연결 가능
    - mysql과 같은 소중한 데이터를 포트 노출없이 숨겨서 사용 가능

- **도커 Volume**

  - `Bind Mount`
  - `Named Volume`

- **NAT**

  - Network Address Transfor
  - 외부에 노출될 IP : 공인 IP (Public IP)
  - 내부에 숨겨진 IP : 사설 IP (Private IP)
  - `ipconfig`
  - public ip로 통신함

- **클라우드 서비스**

  - 온프레미스
  - 온디멘드

- 인스턴스 : 실행 가능한 형태
- **Amazon Machine Image**
- 인스턴스 유형
- 키 페어

- **공개키**
  - 원격 서버에 보관
  - 자물쇠 역할
- **개인키**
  - 열쇠 역할
- HTTP의 SSH 인증서도 비슷한 역할
