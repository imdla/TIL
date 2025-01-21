## <mark color="#fbc956">볼륨 (Volume)</mark>

### 1. 데이터 공유 저장소

- 호스트 PC와 컨테이너 사이에 데이터를 영구적으로 저장 및 공유위한 기능
- 일반적으로 컨테이너 삭제 시, 내부 데이터도 사라지지만 볼륨 사용할 경우 데이터를 재사용 가능

### 2. 도커 관리 Volume (Named Volume)

- 도커 시스템이 관리하는 볼륨
- 하나의 볼륨은 여러 개의 컨테이너가 공유 가능
- 일반적으로 데이터베이스 파일 등을 공유할 때 활용

### 3. 호스트 PC 공유 Volume (Bind Mount)

- 호스트 PC의 특정 폴더를 공유하는 볼륨
- 지정한 호스트 PC 경로를 컨테이너에서 공유해 사용가능
  - 파일 수정 및 활용 가능
- 일반적으로 개발 환경에서 소스 코드 공유할 때 활용

### 4. Volume 명령어

- **`docker volume create [Named Volume 이름]`**

  - 새로운 볼륨 생성

- **`docker volume ls`**

  - 볼륨 목록 출력

- **`docker volume rm [Named Volume 이름]`**

  - 볼륨 삭제

- **`docker run -v [Named Volume]:[컨테이너 경로] [이미지]`**

  - 컨테이너 생성 시 Named Volume에 연결

- **`docker run -v [호스트 PC 경로]:[컨테이너 경로] [이미지]`**
  - 컨테이너 생성 시 호스트 PC 경로에 Bind Mount 함

### 5. Named Volume 활용

1. 환경 변수 파일 `.env` 작성

   ```bash
   	MYSQL_ROOT_PASSWORD=1q2w3e4r!
   	MYSQL_DATABASE=demo
   ```

2. MySQL 데이터 저장용 볼륨 `mysql_volume` 생성

   ```bash
   docker volume create mysql_volume
   ```

3. 컨테이너 생성

   - 도커 볼륨 `mysql_volume` 과 컨테이너 경로 `/var/lib/mysql` 을 연결

   ```bash
   docker run --name mysql -p 3307:3306 -v mysql_volume:/var/lib/sql --env-file .env -d mysql:8.0
   ```

   - 볼륨이 있는 경우

   ```bash
   docker run -p 3307:3306 -e  MYSQL_ROOT_PASSWORD=1q2w3e4r! -e MYSQL_DATABASE=demo -v mysql-volume:/var/lib/mysql -d --name mysql mysql:8.0
   ```

   - 볼륨이 없는 경우

   ```bash
   docker run -p 3307:3306 -e  MYSQL_ROOT_PASSWORD=1q2w3e4r! -e MYSQL_DATABASE=demo -d --name mysql mysql:8.0
   ```
