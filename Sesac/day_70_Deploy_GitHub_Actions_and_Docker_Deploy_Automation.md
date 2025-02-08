## <mark color="#fbc956">배포 프로세스</mark>

### 0. 프로젝트 준비

- 각 컨테이너 이미지 빌드 위한 Dockerfile과 컨테이너 관리 위한 Docker Compose가 필요

### 1. Secrets 준비

- Docker 이미지 빌드에 필요한 환경 변수를 저장소의 Secrets에 생성
- 생성한 Secrets는 환경 변수 파일로 생성
  - 이미지 빌드 및 컨테이너 실행에 활용
- **필요한 Secrets**

  - 개인 키(Private Key) - `SSH_PRIVATE_KEY`
  - SSH 연결 호스트 Username - `SSH_USERNAME`
  - SSH 연결 호스트 Public IP - `SSH_HOST`
  - SSH 연결 포트 - `SSH_PORT`
  - DcokerHub 사용자명 - `DOCKER_USERNMAE`
  - DockerHub 비밀번호 - `DOCKER_PASSWORD`
  - Docker 이미지 빌드 및 컨테이너 실행 위한 여러 환경 변수

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

    # 프론트엔드 환경 변수
    VITE_API_URL=/api
    ```

- 모든 Docker 컨테이너 삭제
  ```bash
  docker rm -f $(docker ps -aq)
  ```

### 2. SSH 연결 준비

- 원격 서버를 제어하기 위해 SSH 연결 활용
- SSH 연결 위해 SSH Agent 준비, 원격 서버를 신뢰할 수 있는 서버로 등록

### 3. Docker Hub 로그인

- `docker-compose.yml` 을 활용해 빌드한 이미지를 저장소에 푸시하기 위해 Docker Hub에 로그인

### 4. 이미지 빌드 & 푸시

- 이미지 빌드하고, Docker Hub에 푸시함
- 이미지 빌드할 때, GitHub Actions cache를 활용하기 위해 `docker-compose.yml` 파일 수정 필요

  - 수정 전 `docker-compose.yml`

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
        image: [도커허브 Username]/backend:latest
        env_file:
          - .env
        environment:
          DATABASE_HOST: db
        networks:
          - db-connect
          - backend-connect
        depends_on:
          db:
            condition: service_healthy

      frontend:
        container_name: frontend-container
        build:
          context: ./frontend
          args:
            VITE_API_URL: ${VITE_API_URL}
        image: [도커허브 Username]/frontend:latest
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

  - 수정 후`docker-compose.yml`

    ```yaml
    # docker-compose-actions.yml
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
          # BuildKit 캐시 설정
          # 캐시 불러올 폴더
          cache_from:
            - type=local,src=/tmp/.buildx-cache/backend
          # 캐시 저장할 폴더
          cache_to:
            - type=local,dest=/tmp/.buildx-cache/backend,mode=max
        image: [도커허브 Username]/backend:latest
        env_file:
          - .env
        networks:
          - db-connect
          - backend-connect
        depends_on:
          db:
            condition: service_healthy

      frontend:
        container_name: frontend-container
        build:
          context: ./frontend
          # BuildKit 캐시 설정
          cache_from:
            - type=local,src=/tmp/.buildx-cache/frontend
          cache_to:
            - type=local,dest=/tmp/.buildx-cache/frontend,mode=max
          args:
            VITE_API_URL: ${VITE_API_URL}
        image: [도커허브 Username]/frontend:latest
        ports:
          - "80:80"
        networks:
          - backend-connect
        depends_on:
          - backend

    volumes:
      db-volume:

    networks:
      db-connect:
      backend-connect:

    ```

### 5. 원격 서버로 파일 복사

- Docker 이미지 Pull 및 컨테이너 생성 위한 Docker Compose 파일과 환경 변수 파일을 원격 서버로 복사

### 6. 이미지 풀 & 컨테이너 실행

- Docker Hub의 이미지를 Pull 하고, 컨테이너를 생성

---

## <mark color="#fbc956">워크플로우</mark>

### 1. 전체 워크플로우

- **전체 워크플로우**

  ```yaml
  name: Deploy Service

  on:
    push:
      branches:
        - main
      paths:
        - ".github/workflows/workflow.yml"
        - "docker-compose-actions.yml"
        - "Dockerfile"
        - "frontend/**"
        - "backend/**"

  jobs:
    docker-build-push:
      runs-on: ubuntu-24.04

      steps:
        # 소스 코드를 워크스페이스로 체크아웃
        - name: Checkout code
          uses: actions/checkout@v4.2.2

        # GitHub Actions Cache 설정
        - name: Cache Docker layers
          uses: actions/cache@v4.2.0
          with:
            path: /tmp/.buildx-cache
            key: ${{ runner.os }}-docker-${{ github.sha }}
            restore-keys: |
              ${{ runner.os }}-docker-

        # SSH 에이전트 설정 - 원격 서버 접속을 위한 SSH 키 설정
        - name: SSH Agent
          uses: webfactory/ssh-agent@v0.9.0
          with:
            ssh-private-key: ${{ secrets.SSH_PRIVATE_KEY }}

        # 원격 서버의 호스트 키를 known_hosts에 추가하여 SSH 연결 신뢰 설정
        - name: Add Host Key to Known Hosts
          run: ssh-keyscan -H ${{ secrets.SSH_HOST }} >> ~/.ssh/known_hosts

        # Docker Buildx 설정
        - name: Set up Docker Buildx
          uses: docker/setup-buildx-action@v3

        # DockerHub 로그인 - 이미지 푸시를 위한 인증
        - name: DockerHub Login
          run: echo '${{ secrets.DOCKER_PASSWORD }}' | docker login -u '${{ secrets.DOCKER_USERNAME }}' --password-stdin

        # 환경 변수 파일 생성
        - name: Create .env file
          run: |
            echo "DATABASE_HOST=${{ secrets.DATABASE_HOST }}" >> .env
            echo "DATABASE_NAME=${{ secrets.DATABASE_NAME }}" >> .env
            echo "DATABASE_PASSWORD=${{ secrets.DATABASE_PASSWORD }}" >> .env
            echo "DATABASE_PORT=${{ secrets.DATABASE_PORT }}" >> .env
            echo "DATABASE_USERNAME=${{ secrets.DATABASE_USERNAME }}" >> .env
            echo "MYSQL_DATABASE=${{ secrets.MYSQL_DATABASE }}" >> .env
            echo "MYSQL_ROOT_PASSWORD=${{ secrets.MYSQL_ROOT_PASSWORD }}" >> .env
            echo "VITE_API_URL=${{ secrets.VITE_API_URL }}" >> .env

        # BuildKit 빌더 생성
        - name: Create BuildKit Builder
          run: |
            docker buildx create --use --name buildkit
            docker buildx use buildkit

        # Docker 이미지 빌드
        - name: Docker Image Build
          run: |
            docker compose -f docker-compose-actions.yml build --build-arg BUILDKIT_INLINE_CACHE=1

        # Docker 이미지 푸시
        - name: Docker Image Push
          run: |
            docker compose -f docker-compose-actions.yml push

        # docker-compose와 환경 변수 파일을 원격 서버로 복사
        - name: Copy deployment files
          uses: appleboy/scp-action@v0.1.7
          with:
            host: ${{ secrets.SSH_HOST }}
            username: ${{ secrets.SSH_USERNAME }}
            key: ${{ secrets.SSH_PRIVATE_KEY }}
            port: ${{ secrets.SSH_PORT }}
            source: "docker-compose-actions.yml,.env"
            target: "~/work-directory"

        # 원격 서버에서 Docker 컨테이너 실행
        - name: Deploy
          uses: appleboy/ssh-action@v1.0.3
          with:
            host: ${{ secrets.SSH_HOST }}
            username: ${{ secrets.SSH_USERNAME }}
            key: ${{ secrets.SSH_PRIVATE_KEY }}
            port: ${{ secrets.SSH_PORT }}
            script: |
              cd ~/work-directory
              docker compose -f docker-compose-actions.yml down
              docker compose -f docker-compose-actions.yml up --pull always -d
              docker image prune -f
  ```

### 2. 배포 환경 설정

- **소스 코드 Checkout**

  ```yaml
  - name: Checkout code
  	uses: actions/checkout@v4.2.2
  ```

- **GitHub Actions Cache 설정**

  - 워크플로우를 시작할 때 Docker 이미지 캐시를 불러오고, 종료할 때 저장
  - `path` : 캐싱할 파일 또는 폴더 경로
  - `key` : 캐시 key (식별자)
  - `restore-kwys` : 캐시 key를 못찾았을 때 복원하기 위한 key 목록

  ```yaml
  - name: Cache Docker layers
  	uses: actions/cache@v4.2.0
  	with:
  		path: /tmp/.buildx-cache
  		key: ${{ runner.os }}-docker-${{ github.sha }}
  		restore-keys: |
  			${{ runner.os }}-docker-
  ```

- **SSH Agent 설정**

  ```yaml
  - name: SSH Agent
  	uses: webfactory/ssh-agent@v0.9.0
  	with:
  		ssh-private-key: ${{ secrets.SSH_PRIVATE_KEY }}
  ```

- **원격 서버 신뢰 설정**

  ```yaml
  - name: Add Host Key to Known Hosts
  	run: ssh-keyscan -H ${{ secrets.SSH_HOST }} >> ~/.ssh/known_hosts
  ```

- **Docker Buildx Action**

  - Docker BuildKit 엔진 활용을 위해 필요한 action
  - BuildKit 엔진은 Docker 이미지 캐시 설정을 위해 필요

  ```yaml
  - name: Set up Docker Buildx
  	uses: docker/setup-buildx-action@v3
  ```

- **Docker Hub 로그인**

  ```yaml
  - name: DockerHub Login
  	run: echo "${{ secrets.DOCKER_PASSWORD }}" | login -u "${{ secrets.DOCKER_USERNAME }}" --password-stdin
  ```

- **환경 변수 파일 생성**
  ```yaml
  - name: Create .env file
    run: |
      echo "DATABASE_HOST=${{ secrets.DATABASE_HOST }}" >> .env
      echo "DATABASE_NAME=${{ secrets.DATABASE_NAME }}" >> .env
      echo "DATABASE_PASSWORD=${{ secrets.DATABASE_PASSWORD }}" >> .env
      echo "DATABASE_PORT=${{ secrets.DATABASE_PORT }}" >> .env
      echo "DATABASE_USERNAME=${{ secrets.DATABASE_USERNAME }}" >> .env
      echo "MYSQL_DATABASE=${{ secrets.MYSQL_DATABASE }}" >> .env
      echo "MYSQL_ROOT_PASSWORD=${{ secrets.MYSQL_ROOT_PASSWORD }}" >> .env
      echo "VITE_API_URL=${{ secrets.VITE_API_URL }}" >> .env
  ```

### 3. Docker 이미지 빌드 & 푸시

- **BuildKit 빌더 생성**

  - `docker buildx create --use --name buildkit` : buildkit 이름의 BuildKit 엔진 빌더 생성
  - `docker buildx use buildkit` : 현재 실행 환경의 빌더를 buildkit 으로 설정

  ```yaml
  - name: Create BuildKit Builder
  	run: |
  		docker buildx create --use --name buildkit
  		docker buildx use buildkit
  ```

  - **Docker BuildKit**

    - Docker의 새로운 빌드 엔진
      - 기존 빌드 엔진보다 더 빠른 빌드 제공
    - 현재 워크플로우에서 이미지 캐시 활용위해 BuildKit 사용

  - **Docker buildx**
    - BuildKit 엔진을 실행하는 플러그인

- **Docker 이미지 캐시 활용 빌드**

  - `--build-arg BUILDKIT_INLINE_CACHE=1` : 캐시 사용 활성화

  ```yaml
  - name: Docker Image Build
    run: |
      docker compose -f docker-compose-actions.yml build --build-arg BUILDKIT_INLINE_CACHE=1
  ```

- **Docker 이미지 푸시**
  ```yaml
  - name: Docker Image Push
    run: |
      docker compose -f docker-compose-actions.yml push
  ```

### 4. 원격 서버 제어

- 원격 서버에서 필요한 파일 복사

  - `docker-compose-actions.yml` : Docker 이미지를 가져오기위해 필요
  - `.env` : Docker 컨테이너 생성하기 위해 필요

  1. 작업 폴더(work-directory)가 존재하면 `git pull`
  2. 작업 폴더(work-directory)가 존재하지 않으면 `git clone`

  ```yaml
  - name: Copy deployment files
    uses: appleboy/scp-action@v0.1.7
    with:
      host: ${{ secrets.SSH_HOST }}
      username: ${{ secrets.SSH_USERNAME }}
      key: ${{ secrets.SSH_PRIVATE_KEY }}
      port: ${{ secrets.SSH_PORT }}
      source: "docker-compose-actions.yml,.env"
      target: "~/work-directory"
  ```

- **원격 서버에서 이미지 풀 & 컨테이너 실행 & 이미지 정리**
  ```yaml
  - name: Deploy
    uses: appleboy/ssh-action@v1.0.3
    with:
      host: ${{ secrets.SSH_HOST }}
      username: ${{ secrets.SSH_USERNAME }}
      key: ${{ secrets.SSH_PRIVATE_KEY }}
      port: ${{ secrets.SSH_PORT }}
      script: |
        cd ~/work-directory
        docker compose -f docker-compose-actions.yml up --pull always -d
        docker image prune -f
  ```

> **별도의 Docker 이미지 캐시 설정이 필요한 이유**

- 기존 Docker 캐시는 로컬 환경에 계속 저장되고, 갱신되지만 GitHub Actions은 실행 때마다 가상환경이 새롭게 생성됨
- 캐시가 없는 환경이 만들어지기 때문에, GitHub Actions Cache를 활용해 Docker 이미지 캐시를 유지하기 위한 별도의 설정이 필요

> **GitHub Actions Cache 제한**

- 저장 공간 : 저장소 당 10GB
- 개별 캐시 : 개별 캐시 당 최대 5GB
- 캐시 기한 : 7일간 사용되지 않은 캐시 삭제
- 자동 삭제 : 총 캐시 용량이 10GB 넘으면 오래 사용하지 않은 캐시 자동 삭제

---

## <mark color="#fbc956">Discord 알림 메시지</mark>

### 1. Webhook

- 2개의 다른 서비스(또는 애플리케이션) A, B가 있을 때 A 서비스에서 특정 이벤트가 발생하면 B 서비스에 알림을 보내는 기능
  - GitHub 원격 저장소(A)에서 PUSH 이벤트가 발생하면 디스코드(B) 채널에 알림을 보낼 수 있음
- Webhook을 사용하기 위해 알림을 받는 서비스의 B의 Webhook URL이 필요함
  - HTTP 통신을 통해 알림 보냄

### 2. Discord Webhook Actions

- 빌드 시작 전 후 알림 메시지 전송해 빌드 시작과 결과 알림

1. 저장소 Secrests 준비
   - DISCORD_WEBHOOK - 디스코드 Webhook URL
2. 워크플로우에 Discord Alram 워크플로우 작성

   - Actions 시작 알림 메세지
     ```yaml
     - name: Discord Notification - Start
       uses: sarisia/actions-status-discord@v1
       with:
         webhook: ${{ secrets.DISCORD_WEBHOOK }}
         title: "🚀 배포 시작"
         description: |
           Repository: ${{ github.repository }}
           Branch: ${{ github.ref_name }}
           Commit: ${{ github.event.head_commit.message }}
         color: 0x0000ff
     ```
   - Actions 종료 알림 메시지

     ```yaml
     # Discord 알림 - 배포 성공
     - name: Discord Notification - Success
       if: success()
       uses: sarisia/actions-status-discord@v1
       with:
         webhook: ${{ secrets.DISCORD_WEBHOOK }}
         title: "✅ 배포 성공"
         description: |
           Repository: ${{ github.repository }}
           Branch: ${{ github.ref_name }}
           Commit: ${{ github.event.head_commit.message }}
         color: 0x00ff00

     # Discord 알림 - 배포 실패
     - name: Discord Notification - Failure
       if: failure()
       uses: sarisia/actions-status-discord@v1
       with:
         webhook: ${{ secrets.DISCORD_WEBHOOK }}
         title: "❌ 배포 실패"
         description: |
           Repository: ${{ github.repository }}
           Branch: ${{ github.ref_name }}
           Commit: ${{ github.event.head_commit.message }}
         color: 0xff0000
     ```

3. 최종 워크플로우

   - `deploy.yml`

     ```yaml
     name: Deploy Service

     on:
       push:
         branches:
           - main
         paths:
           - ".github/workflows/workflow.yml"
           - "docker-compose-actions.yml"
           - "Dockerfile"
           - "frontend/**"
           - "backend/**"

     jobs:
       docker-build-push:
         runs-on: ubuntu-24.04

         steps:
           # Discord 알림 - 배포 시작
           - name: Discord Notification - Start
             uses: sarisia/actions-status-discord@v1
             with:
               webhook: ${{ secrets.DISCORD_WEBHOOK }}
               title: "🚀 배포 시작"
               description: |
                 Repository: ${{ github.repository }}
                 Branch: ${{ github.ref_name }}
                 Commit: ${{ github.event.head_commit.message }}
               color: 0x0000ff

           # 소스 코드를 워크스페이스로 체크아웃
           - name: Checkout code
             uses: actions/checkout@v4.2.2

           # GitHub Actions Cache 설정
           - name: Cache Docker layers
             uses: actions/cache@v4.2.0
             with:
               path: /tmp/.buildx-cache
               key: ${{ runner.os }}-docker-${{ github.sha }}
               restore-keys: |
                 ${{ runner.os }}-docker-

           # SSH 에이전트 설정 - 원격 서버 접속을 위한 SSH 키 설정
           - name: SSH Agent
             uses: webfactory/ssh-agent@v0.9.0
             with:
               ssh-private-key: ${{ secrets.SSH_PRIVATE_KEY }}

           # 원격 서버의 호스트 키를 known_hosts에 추가하여 SSH 연결 신뢰 설정
           - name: Add Host Key to Known Hosts
             run: ssh-keyscan -H ${{ secrets.SSH_HOST }} >> ~/.ssh/known_hosts

           # Docker Buildx 설정
           - name: Set up Docker Buildx
             uses: docker/setup-buildx-action@v3

           # DockerHub 로그인 - 이미지 푸시를 위한 인증
           - name: DockerHub Login
             run: echo '${{ secrets.DOCKER_PASSWORD }}' | docker login -u '${{ secrets.DOCKER_USERNAME }}' --password-stdin

           # 환경 변수 파일 생성
           - name: Create .env file
             run: |
               echo "DATABASE_HOST=${{ secrets.DATABASE_HOST }}" >> .env
               echo "DATABASE_NAME=${{ secrets.DATABASE_NAME }}" >> .env
               echo "DATABASE_PASSWORD=${{ secrets.DATABASE_PASSWORD }}" >> .env
               echo "DATABASE_PORT=${{ secrets.DATABASE_PORT }}" >> .env
               echo "DATABASE_USERNAME=${{ secrets.DATABASE_USERNAME }}" >> .env
               echo "MYSQL_DATABASE=${{ secrets.MYSQL_DATABASE }}" >> .env
               echo "MYSQL_ROOT_PASSWORD=${{ secrets.MYSQL_ROOT_PASSWORD }}" >> .env
               echo "VITE_API_URL=${{ secrets.VITE_API_URL }}" >> .env

           # BuildKit 빌더 생성
           - name: Create BuildKit Builder
             run: |
               docker buildx create --use --name buildkit
               docker buildx use buildkit

           # Docker 이미지 빌드
           - name: Docker Image Build
             run: |
               docker compose -f docker-compose-actions.yml build --build-arg BUILDKIT_INLINE_CACHE=1

           # Docker 이미지 푸시
           - name: Docker Image Push
             run: |
               docker compose -f docker-compose-actions.yml push

           # docker-compose와 환경 변수 파일을 원격 서버로 복사
           - name: Copy deployment files
             uses: appleboy/scp-action@v0.1.7
             with:
               host: ${{ secrets.SSH_HOST }}
               username: ${{ secrets.SSH_USERNAME }}
               key: ${{ secrets.SSH_PRIVATE_KEY }}
               port: ${{ secrets.SSH_PORT }}
               source: "docker-compose-actions.yml,.env"
               target: "~/work-directory"

           # 원격 서버에서 Docker 컨테이너 실행
           - name: Deploy
             uses: appleboy/ssh-action@v1.0.3
             with:
               host: ${{ secrets.SSH_HOST }}
               username: ${{ secrets.SSH_USERNAME }}
               key: ${{ secrets.SSH_PRIVATE_KEY }}
               port: ${{ secrets.SSH_PORT }}
               script: |
                 cd ~/work-directory
                 docker compose -f docker-compose-actions.yml down
                 docker compose -f docker-compose-actions.yml up --pull always -d
                 docker image prune -f

           # Discord 알림 - 배포 성공
           - name: Discord Notification - Success
             if: success()
             uses: sarisia/actions-status-discord@v1
             with:
               webhook: ${{ secrets.DISCORD_WEBHOOK }}
               title: "✅ 배포 성공"
               description: |
                 Repository: ${{ github.repository }}
                 Branch: ${{ github.ref_name }}
                 Commit: ${{ github.event.head_commit.message }}
               color: 0x00ff00

           # Discord 알림 - 배포 실패
           - name: Discord Notification - Failure
             if: failure()
             uses: sarisia/actions-status-discord@v1
             with:
               webhook: ${{ secrets.DISCORD_WEBHOOK }}
               title: "❌ 배포 실패"
               description: |
                 Repository: ${{ github.repository }}
                 Branch: ${{ github.ref_name }}
                 Commit: ${{ github.event.head_commit.message }}
               color: 0xff0000
     ```

---

### ☀️ 오늘의 배움

- **GitHub Actions & Docekr 배포**

  - **GitHub Actions & Docekr 직접 배포**
    ![image.png](/Sesac/assets/day70_1.png)

    1. 이미지 Build 및 Push

       - checkout : 프로젝트가 workspace로 복사됨

       ```bash
       # 도커 컨테이너 삭제 명령어
       docker rm -f $(docker ps -aq)

       # 이미지 빌드
       # 컨테이너 생성
       docker-compose up --build

       # 도커 로그인
       docker login -u imdla

       # 이미지 push
       docker-compose push
       ```

    2. termius의 SFTP를 통해 서버에 `.env` , `docker-compose.yml` 파일을 전송
       - scp : 파일 복사
       - sftp : 파일 전송
    3. 리눅스 docker-compose 설치 후 pull & up

       ```bash
       docker-compose pull

       docker-compose up

       # Port 80 열기
       # 퍼블릭 IP로 접속
       ```

  - **GitHub Actions & Docekr 배포**
    ![image.png](/Sesac/assets/day70_2.png)

    1.  secrets 지정
        - 개인 키
        - SSH 연결 호스트 username
        - SSH 연결 호스트 public ip
        - SSH 연결 포트
        - Docker Hub 사용자명
        - Docker Hub 비밀번호
        - 백엔드 환경 변수
        - 데이터베이스 환경 변수
        - 프론트엔드 환경 변수
    2.  `deploy.yml` 작성 - `.github/workflows/deploy.yml` 1. 환경 변수 파일 생성 2. 이미지 빌드 3. Docker 로그인 4. Image push 5. workspace에서 원격 서버로 docker-compose.yml와 .env 복사 6. SSH 통해 Pull Image & Up Container

        ````yaml
        name: Deploy Service

               on:
                 push: # 워크플로우 실행 조건 이벤트
                   branches: # 워크플로우 실행 조건 브랜치
                     - main
               jobs:
                 ssh-agent: # Job 이름
                   runs-on: ubuntu-24.04 # Github 워크스페이스 환경

                   steps: # 실행할 작업(step)
                     - name: Checkout code
                       uses: actions/checkout@v4.2.2

                     - name: run ssh-agent
                       uses: webfactory/ssh-agent@v0.9.0
                       with:
                         ssh-private-key: ${{ secrets.SSH_PRIVATE_KEY }}

                     # 원격 서버를 신뢰할 수 있는 서버로 등록하는 과정
                     # known_hosts : 원격 서버들의 지문이 저장된 파일
                     - name: ADD Remote Server Fingerprint to Known Hosts
                       run: ssh-keyscan -H -p ${{ secrets.SSH_PORT }} ${{ secrets.SSH_HOST }} >> ~/.ssh/known_hosts || true

                     # 1. 환경 변수 파일 생성
                     - name: Create .env file
                       run: |
                         echo "DATABASE_HOST=${{ secrets.DATABASE_HOST }}" >> .env
                         echo "DATABASE_NAME=${{ secrets.DATABASE_NAME }}" >> .env
                         echo "DATABASE_PASSWORD=${{ secrets.DATABASE_PASSWORD }}" >> .env
                         echo "DATABASE_PORT=${{ secrets.DATABASE_PORT }}" >> .env
                         echo "DATABASE_USERNAME=${{ secrets.DATABASE_USERNAME }}" >> .env
                         echo "MYSQL_DATABASE=${{ secrets.MYSQL_DATABASE }}" >> .env
                         echo "MYSQL_ROOT_PASSWORD=${{ secrets.MYSQL_ROOT_PASSWORD }}" >> .env
                         echo "VITE_API_URL=${{ secrets.VITE_API_URL }}" >> .env

                     # 2. 이미지 빌드
                     - name: Docker Image Build
                       run: docker compose build

                     # 3. Docker 로그인
                     - name: Login DockerHub
                       run: echo '${{ secrets.DOCKER_PASSWORD }}' | docker login -u '${{ secrets.DOCKER_USERNAME }}' --password-stdin

                     # 4. 이미지 push
                     - name: Docker Image Push
                       run: docker compose push

                     # 5. workspace에서 원격 서버로 compose와 .env 복사
                     - name: Copy .env / docker-compose.yml
                       uses: appleboy/scp-action@v0.1.7
                       with:
                         host: ${{ secrets.SSH_HOST }}
                         username: ${{ secrets.SSH_USERNAME }}
                         key: ${{ secrets.SSH_PRIVATE_KEY }}
                         port: ${{ secrets.SSH_PORT }}
                         source: "docker-compose.yml, .env"
                         target: "~/work-directory"

                     # 6. 이미지 pull & 컨테이너 up
                     - name: Pull Image & Up Container
                       uses: appleboy/ssh-action@v1.0.3
                       with:
                         host: ${{ secrets.SSH_HOST }}
                         username: ${{ secrets.SSH_USERNAME }}
                         key: ${{ secrets.SSH_PRIVATE_KEY }}
                         port: ${{ secrets.SSH_PORT }}
                         script: |
                           cd ~/work-directory
                           docker compose pull
                           docker compose down
                           docker compose up -d
               ```
        ````

![image.png](/Sesac/assets/day70_3.png)

- **Webhook**

  ![image.png](/Sesac/assets/day70_4.png)

  ![image.png](/Sesac/assets/day70_5.png)

  1. A의 Webhook URL 제공
  2. PUSH 이벤트 발생
  3. HTTP 통신
  4. 메시지 출력

- **GitHub Actions Cache 설정**
  ![image.png](/Sesac/assets/day70_6.png)
  - 캐시 파일 저장 공간 특징
    1. 일정 기간 캐시 파일을 보관
    2. Actions Workspace가 공유하는 공간
       - Docker Named Volume처럼 공유하는 공간
    - 캐시 파일의 저장 경로 & 캐시 파일을 불어올 경로 지정 필요
  - `docker-compose-actions-cache.yml`
    ```yaml
    backend:
      container_name: backend-container
      build: # build 블록 : 이미지 build 관련 설정
        context: ./backend
        # 캐시 저장 경로
        cache_to:
          # type : 캐시의 종류
          # src : 캐시를 저장할할 경로
          # mode : 캐시 저장 방식(max: 최대한 많은 캐시 데이터를 저장장)
          - type=local,dest=/tmp/.buildx-cache/backend,mode=max
        # 캐시를 불러올 경로
        cache_from:
          - type=local,src=/tmp/.buildx-cache/backend

      image: imdla/backend:latest
      env_file:
        - .env
      environment:
        DATABASE_HOST: db
      networks:
        - db-connect
        - backend-connect
      depends_on:
        db:
          condition: service_healthy
    ```
    ```yaml
    # 캐시 저장 설정을 위한 actions(라이브러리)
    - name: Cache Docker Image Layer
      # acrions/cache : 깃허브 캐시 저장소 활용을 위한 라이브러리
      uses: actions/cache@v4.2.0
      with:
        path: /tmp/.buildx-cache
        key: docker-image-layer-cache

    # 3. 도커 Buildx 엔진 설정 action(라이브러리)
    - name: Set up Docker BuildKit
      # docker/setup-buildx-action
      # 워크스페이스에 BuildKit 엔진을 설치하는 action(라이브러리)
      # build = BuildKit
      uses: docker/setup-buildx-action@v3

    # BuildKit 엔진 빌더(이미지 빌드를 도와주는 도구) 생성
    # 기존 Docker 빌더 : Cache 파일 저장 & 불러오기 경로 제어 불가
    - name: Creat Buildkit 빌더
      run: |
        docker buildx create --use --name buildkit
        docker buildx use buildkit
    ```
    - `deploy.yml` 전체
      ```yaml
      name: Deploy Service

      on:
        push: # 워크플로우 실행 조건 이벤트
          branches: # 워크플로우 실행 조건 브랜치
            - main
      jobs:
        ssh-agent: # Job 이름
          runs-on: ubuntu-24.04 # Github 워크스페이스 환경

          steps: # 실행할 작업(step)
            - name: Checkout code
              uses: actions/checkout@v4.2.2

            # 캐시 저장 설정을 위한 actions(라이브러리)
            - name: Cache Docker Image Layer
              # acrions/cache : 깃허브 캐시 저장소 활용을 위한 라이브러리
              uses: actions/cache@v4.2.0
              with:
                path: /tmp/.buildx-cache
                key: docker-image-layer-cache-${{ github.sha }}
                restore-keys: docker-image-layer-cache

            - name: run ssh-agent
              uses: webfactory/ssh-agent@v0.9.0
              with:
                ssh-private-key: ${{ secrets.SSH_PRIVATE_KEY }}

            # 원격 서버를 신뢰할 수 있는 서버로 등록하는 과정
            # known_hosts : 원격 서버들의 지문이 저장된 파일
            - name: ADD Remote Server Fingerprint to Known Hosts
              run: ssh-keyscan -H -p ${{ secrets.SSH_PORT }} ${{ secrets.SSH_HOST }} >> ~/.ssh/known_hosts || true

            # 1. 환경 변수 파일 생성
            - name: Create .env file
              run: |
                echo "DATABASE_HOST=${{ secrets.DATABASE_HOST }}" >> .env
                echo "DATABASE_NAME=${{ secrets.DATABASE_NAME }}" >> .env
                echo "DATABASE_PASSWORD=${{ secrets.DATABASE_PASSWORD }}" >> .env
                echo "DATABASE_PORT=${{ secrets.DATABASE_PORT }}" >> .env
                echo "DATABASE_USERNAME=${{ secrets.DATABASE_USERNAME }}" >> .env
                echo "MYSQL_DATABASE=${{ secrets.MYSQL_DATABASE }}" >> .env
                echo "MYSQL_ROOT_PASSWORD=${{ secrets.MYSQL_ROOT_PASSWORD }}" >> .env
                echo "VITE_API_URL=${{ secrets.VITE_API_URL }}" >> .env

            # 3. 도커 Buildx 엔진 설정 action(라이브러리)
            - name: Set up Docker BuildKit
              # docker/setup-buildx-action
              # 워크스페이스에 BuildKit 엔진을 설치하는 action(라이브러리)
              # build = BuildKit
              uses: docker/setup-buildx-action@v3

            # BuildKit 엔진 빌더(이미지 빌드를 도와주는 도구) 생성
            # 기존 Docker 빌더 : Cache 파일 저장 & 불러오기 경로 제어 불가
            - name: Creat Buildkit 빌더
              run: |
                docker buildx create --use --name buildkit-builder
                docker buildx use buildkit-builder

            # 2. 이미지 빌드
            - name: Docker Image Build
              run: |
                docker compose -f docker-compose-actions-cache.yml build --build-arg BUILDKIT_INLINE_CACHE=1

            # 3. Docker 로그인
            - name: Login DockerHub
              run: echo '${{ secrets.DOCKER_PASSWORD }}' | docker login -u '${{ secrets.DOCKER_USERNAME }}' --password-stdin

            # 4. 이미지 push
            - name: Docker Image Push
              run: docker compose -f docker-compose-actions-cache.yml push

            # 5. workspace에서 원격 서버로 compose와 .env 복사
            - name: Copy .env / docker-compose-actions-cache.yml
              uses: appleboy/scp-action@v0.1.7
              with:
                host: ${{ secrets.SSH_HOST }}
                username: ${{ secrets.SSH_USERNAME }}
                key: ${{ secrets.SSH_PRIVATE_KEY }}
                port: ${{ secrets.SSH_PORT }}
                source: "docker-compose-actions-cache.yml,.env"
                target: "~/work-directory"

            # 6. 이미지 pull & 컨테이너 up
            - name: Pull Image & Up Container
              uses: appleboy/ssh-action@v1.0.3
              with:
                host: ${{ secrets.SSH_HOST }}
                username: ${{ secrets.SSH_USERNAME }}
                key: ${{ secrets.SSH_PRIVATE_KEY }}
                port: ${{ secrets.SSH_PORT }}
                script: |
                  cd ~/work-directory
                  docker compose -f docker-compose-actions-cache.yml pull
                  docker compose -f docker-compose-actions-cache.yml down
                  docker compose -f docker-compose-actions-cache.yml up -d

            # Discord 알림 - Webhook
            - name: Discord Notificaton - Start
              uses: sarisia/actions-status-discord@v1
              with:
                webhook: ${{ secrets.DISCORD_WEBHOOK }}
                title: "🚀 배포 시작"
                description: |
                  Repository: ${{ github.repository }}
                  Branch: ${{ github.ref_name }}
                  Commit: ${{ github.event.head_commit.message }}
                color: 0xBAFF1A

            # Discord 알림 - 배포 성공
            - name: Discord Notificaton - Success
              if: success()
              uses: sarisia/actions-status-discord@v1
              with:
                webhook: ${{ secrets.DISCORD_WEBHOOK }}
                title: "✅ 배포 종료 - 성공"
                description: |
                  Repository: ${{ github.repository }}
                  Branch: ${{ github.ref_name }}
                  Commit: ${{ github.event.head_commit.message }}
                color: 0xFF9BFD

            # Discord 알림 - 배포 실패
            - name: Discord Notificaton - Failure
              if: failure()
              uses: sarisia/actions-status-discord@v1
              with:
                webhook: ${{ secrets.DISCORD_WEBHOOK }}
                title: "❌ 배포 종료 - 실패"
                description: |
                  Repository: ${{ github.repository }}
                  Branch: ${{ github.ref_name }}
                  Commit: ${{ github.event.head_commit.message }}
                color: 0xBBBBBB
      ```
