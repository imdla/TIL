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
    ![image.png](/Sesac/assets/day_70_2.png)

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

![image.png](/Sesac/assets/day_70_3.png)
