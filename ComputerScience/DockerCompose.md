> 💡 **한 줄 요약**
>
> 도커 컴포즈는 여러 개의 애플리케이션에 대한 컨테이너를 정의 및 실행하기 위한 도구로, docker-compose.yml 파일에서 관리할 수 있다.

### 1. 🤔 왜 사용하는가

- **Docker Compose**

  - 멀티 컨테이너 관리 도구
  - 여러 개의 애플리케이션에 대한 컨테이너를 정의 및 실행위한 도구
  - 서비스, 네트워크, 볼륨, 환경 변수 등을 `docker-compose.yml` 파일에서 관리 가능

- **`docker-compose.yml` 문법**

  ```yaml
  **# version : docker-compose.yml 파일의 버전 명시**
  version : "3.8"

  **# services : 서비스(컨테이너) 그룹 목록**
  services:
    db:
  	  **# container_name : 생성되는 컨테이너의 이름**
      container_name: db-container
      **# image : Docker Hub에 저장된 이미지의 이름**
      image: mysql:8.0
      **# volumes : 컨테이너가 연결될 볼륨 정의**
      volumes:
        - db-volume:/var/lib/mysql
      **# 이미지를 빌드할 때 읽어올 환경 변수 파일 경로 정의**
      env_file:
        - .env
      **# networks : 컨테이너가 연결된 네트워크 생성**
      networks:
        - db-connect
      **# healthcheck : 컨테이너의 실행 상태 확인**
      healthcheck:
  			**# test : 컨테이너 실행 상태 확인 위한 명령어 정의**
        test: ["CMD", "mysqladmin", "ping", "-h", "localhost"]
        **# interval : 상태 확인 실행 간격**
        interval: 10s
        **# timeout : 상태 확인 명령어가 완료될 때까지 대기하는 시간**
        timeout: 5s
        **# retries : 시도 횟수**
        retries: 3
        start_period: 30s

    backend:
      container_name: backend-container
  	  **# build : Dockerfile을 사용해 이미지를 빌드하기 위한 블록**
      build:
  	    **# context : Dockerfile 경로**
        context: ./backend
        dockerfile: Dockerfile
      **# build 블록 다음 image 블록 : 빌드로 생성되는 이미지의 이름 정의**
      image: backend-image:latest
      env_file:
        - .env
      networks:
        - db-connect
        - backend-connect
      **# depends_on : 컨테이너 사아의 의존 관계 정의**
      depends_on:
        db:
          condition: service_healthy

    frontend:
      container_name: frontend-container
      build:
        context: ./frontend
      image: frontend-image:latest
  	  **# ports : 호스트와 컨테이너의 포트 매핑**
      ports:
        - "80:80"
      networks:
        - backend-connect
      depends_on:
        - backend
        - db

  **# volumes 블록으로, 볼륨 설정**
  volumes:
    db-volume:

  **# networks 블록으로, 네트워크 설정**
  networks:
    db-connect:
    backend-connect:

  ```

- **Docker Compose 명령어**

  - `docker-compose up` : 컨테이너 생성 및 시작
    - 옵션
      - `-build` : 컨테이너 시작 전 이미지 다시 빌드
      - `-d` : 컨테이너를 백그라운드에서 실행
  - `docker-compose down` : 컨테이너 및 네트워크 중지 및 삭제
    - 옵션
      - `--volumes` : 연관된 볼륨도 삭제
      - `-rmi all` : 연관된 이미지도 삭제
  - `docker-compose logs` : 컨테이너들의 로그 출력
    - 옵션
      - `-f` : 실시간 로그 출력
  - `docker-compose push` : Docker Hub네 Dockerfile로 빌드한 이미지 업로드
  - `docker-compose pull` : docker-compose.yml에 정의된 이미지를 Docker Hub에서 다운로드
  - `docker-compose start` : 중지된 컨테이너를 다시 시작
  - `docker-compose stop` : 실행 중인 `컨테이너를 중지`
  - `docker-compose restart` : 컨테이너 다시 시작

- **Docker Compose를 사용하여 데이터베이스, 백엔드 서버, 프론트엔드 애플리케이션으로 구성된 멀티 컨테이너 애플리케이션을 구축 방법**

  ![image.png](/ComputerScience/assets/dockerCompose.png)

  1. docker-compose.yml
     - 위의 docker-compose.yml 파일 사용
  2. 환경 변수 파일 `.env`

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

  3. 컨테이너 생성 및 실행

     ```bash
     docker-compose up --build
     ```

  4. backend와 frontend의 Dockerfile 작성

- **`depends_on` 옵션**

  - 컨테이너 간의 실행 순서 제어 옵션
  - 특정 컨테이너가 다른 컨테이너의 의존하는 관계 설정하는 Docker Compose 옵션

  - **역할**

    - A 컨테이너가 B 컨테이너를 `depends_on` 하면 B 컨테이너가 먼저 시작됨
    - 컨테이너의 실행 순서만 보장
      - 컨테이너 내부 서비스가 완전히 준비되었는지는 확인하지 않음

  - **`condition` 사용**

    - `depends_on` 만으로 실행 순서는 제어할 수 있지만, 의존하는 서비스가 완전히 준비되었는지 확인하지 않아 문제 발생 가능
    - `condition` 옵션을 사용해 서비스가 완전히 준비될 때까지 기다릴 수 있음

  - **필요성 (컨테이너 간 의존성 관리 중요성)**

    1. 데이터베이스가 준비되기 전 웹 애플리케이션이 실행되면 오류 발생
       - 웹 서버가 DB에 연결 시도하지만, DB가 준비되지 않았다면 접속 실패
       - `depends_on` 사용 시 DB가 먼저 실행됨
    2. 메시지 큐, 캐시, API 서버 등 다른 서비스와의 연결 문제 예방
       - API 서버가 실행되기 전에 클라이언트가 요청 시 오류 발생
    3. 컨테이너 실행 순서가 보장되지 않으면 비정상적인 동작 가능
       - 컨테이너가 랜덤한 순서로 실행될 경우, 의존성이 있는 서비스가 예측 불가능한 방식으로 동작할 수 있음

  - **`restart` 사용**
    - `depends_on` 은 실행 순서만 제어해, 컨테이너 중지되었을 때 다시 실행하는 것까지 관리하지 않음
    - `restart` 사용해 컨테이너가 비정상 종료되더라도 자동으로 재시작 가능

- **`healthcheck` 옵션**
  - 최초 DB 컨테이너 생성 시 데이터베이스 초기화를 위해 시간이 필요한데, 초기화 중 backend 컨테이너가 생성 및 실행되어 JDBC의 데이터베이스 연결 작업이 실패함
  - 이를 방지하기 위해 데이터베이스 초기화가 완료되었는지 healthcheck 해야 함
  - **healthcheck 없을 경우**
    1. DB 컨테이너 생성 및 실행
    2. 데이터베이스 초기화 시작
    3. backend 컨테이너 생성 및 실행 (오류)
    4. 데이터베이스 초기화 완료
  - **healthcheck 있을 경우**
    1. DB 컨테이너 생성 및 실행
    2. 데이터베이스 초기화 시작
    3. 데이터베이스 초기화 완료
    4. backend 컨테이너 생성 및 실행 (정상)
