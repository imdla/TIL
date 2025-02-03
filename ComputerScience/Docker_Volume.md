> 💡 **한 줄 요약**
>
> 도커 볼륨은 호스트 PC와 컨테이너 사이 데이터를 영구적으로 저장 및 공유하기 위한 기능으로, Named Volume과 Bind Mount이 있다. Named Volume은 도커 시스템이 관리하는 볼륨으로, 하나의 볼륨을 여러 개의 컨테이너가 공유할 수 있다. Bind Mount는 호스트 PC의 특정 폴더를 공유하는 볼륨으로, 지정한 호스트 PC 경로를 컨테이너에서 공유해 사용할 수 있다.

### 1. 🤔 왜 사용하는가

- **Docker Volume**

  - 데이터 공유 저장소
  - 호스트 PC와 컨테이너 사이에 데이터를 영구적으로 저장 및 공유하기위한 기능
  - 컨테이너 삭제 시 그 안의 데이터 사라지지만, 볼륨 사용 시 데이터 재사용 가능

- **Named Volume vs Bind Mount**

  - **Named Volume**
    - 도커 시스템이 관리하는 볼륨
    - 하나의 볼륨을 여러 개의 컨테이너가 공유할 수 있음
    - 주로 데이터베이스 파일 등을 공유할 때 사용
  - **Bind Mount**
    - 호스트 PC의 특정 폴더를 공유하는 볼륨
    - 지정한 호스트 PC 경로를 컨테이너에서 공유해 사용 가능
      - 파일 수정 및 활용 가능
    - 주로 개발 환경에서 소스 코드 공유 시 사용

- **MySQL 데이터베이스를 Docker 컨테이너에서 Named Volume 사용하는 방법**
  1. 환경 변수 `.env`

     ```
     MYSQL_ROOT_PASSWORD=1q2w3e4r!
     MYSQL_DATABASE=demo
     ```

  2. MySQL 데이터 저장용 볼륨 생성 : `mysql_volume`

     ```bash
     docker volume create mysql_volume
     ```

  3. 컨테이너 생성

     ```bash
     docker run --name mysql -p 3307:3306 -v mysql_volume:/var/lib/mysql --env-file .env -d mysql:8.0
     ```
