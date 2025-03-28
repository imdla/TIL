### 1. 🤔 왜 사용하는가

- **컨테이너 생성 및 실행 명령어**

  - 생성
    - `docker run [옵션] [이미지 이름]:[태그]` : 이미지 기반 컨테이너 생성 및 실행
  - 이름 지정
    - `docker run --name [컨테이너 이름] [이미지 이름]` : 컨테이너에 이름 부여
  - 포트 매핑
    - `docker run -p [호스트 Port]:[컨테이너 Port] [이미지 이름]` : 로스트와 컨테이너 간 포트 매핑
  - 백그라운드 실행
    - `docker run -d [이미지 이름]` : 컨테이너를 백그라운드에서 실행
    - `docker run -d [이미지 이름] sleep infinity` : 컨테이너 백그라운드 실행 및 실행 중인 프로세스 없어도 종료되지 않음
  - 가상 터미널 할당
    - `docker run -it [이미지 이름] bash` : 컨테이너에 가상 터미널 할당, 사용자의 입력과 상호작용 가능
    - `docker run -it -d [이미지 이름]` : 컨테이너 백그라운드 실행, 실행 중인 프로세스 없어도 종료되지 않음
  - 환경 변수 설정
    - `docker run -e key=value [이미지 이름]` : 컨테이너 내부에서 실행될 서비스에 값을 전달하기 위해 환경 변수 설정
    - `docker run --env-file [환견 변수 파일 이름] [이미지 이름]` : 컨테이너 생성 시 환경 변수 파일을 읽어 환경 변수 설정
  - 삭제
    - `docker run -rm [이미지 이름]` : 컨테이너 종료 시 자동으로 컨테이너 삭제

- **컨테이너 관리 명령어**

  - 실행
    - `docker start [컨테이너 이름|ID]` : 컨테이너 실행
  - 정지
    - `docker stop [컨테이너 이름|ID]` : 컨테이너 정지
  - 삭제
    - `docker rm [컨테이너 이름|ID]` : 정지 상태의 컨테이너 삭제
    - `docker rm -f [컨테이너 이름|ID]` : 실행 상태의 컨테이너를 강제 종료 후 삭제
    - `docker container prune` : 정지 상태의 모든 컨테이너 삭제
  - 목록 출력
    - `docker ps` : 실행중인 컨테이너 목록 출력
    - `docker ps -a` : 정지 및 실행 중인 모든 컨테이너 목록 출력
  - 로그 출력
    - `docker logs [컨테이너 이름|ID]` : 컨테이너 로그 출력
    - `docker logs -f [컨테이너 이름|ID]` : 실시간 컨테이너 로그 출력

- **컨테이너에 접속해 호스트 파일 공유**
  - 접속
    - `docker exec -it [컨테이너 이름|ID] /bin/bash` : 컨테이너 내부 터미널에 접속
      - `exit` : 접속 종료
  - 호스트 파일 복사
    - `docker cp [호스트 파일|폴더 경로] [컨테이너 이름]:[컨테이너 내부 경로]` : 호스트 파일 또는 폴더를 내부 경로로 복사
  - 이미지로 저장
    - `docker commit [컨테이너 이름|ID] [이미지 이름]:[태그]` : 컨테이너 현재 상태를 이미지로 저장
  - 출력
    - `docker top [컨테이너 이름|ID]` : 컨테이너 내부에서 현재 실행 중인 서비스 출력
    - `docker inspect [컨테이너 이름|ID]` : 컨테이너의 현재 상태 출력
