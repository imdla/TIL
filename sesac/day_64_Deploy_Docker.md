## <mark color="#fbc956">도커 (Docker)</mark>

### 1. 도커

- 애플리케이션을 개발, 배포, 실행하기 위한 오픈 플랫폼
- 애플리케이션과 인프라를 분리할 수 있어 소프트웨어를 신속하게 제공할 수 있음
- Docker 통해 애플리케이션을 관리하는 방식과 동일한 방식으로 인프라 관리 가능
- 코드 배포, 테스트, 배포를 빠르게 처리하는 방법론 활용 시 코드 작성과 프로덕션 환경에서 실행 사이의 지연을 크게 줄일 수 있음

### 2. 도커 구조

- **Client**
  - 사용자가 입력한 명령어를 Docker Host로 전송
- **Docker daemon**
  - Client의 요청을 처리
  - 이미지, 컨테이너, 네트워크 볼륨 등의 도커 객체 관리
- **Registry**
  - Docker 이미지가 저장되는 네트워크 공간 (저장소)
  - Github처럼 Docker Hub가 존재함
- **Images**
  - Docker 컨테이너를 생성하는 읽기 전용의 불변 템플릿
- **Containers**
  - 이미지를 기반으로 생성한 인스턴스

### 3. 가상 머신 vs 도커

- **가상 머신 (Virtual Machine, VM)**

  - 전통적인 가상화 기술
  - 하이퍼바이저(Hypervisor) 위에서 동작
    - **하이퍼바이저(Hypervisor)**
      - 하나의 PC에 여러 개의 OS를 동시에 실행하기 위한 플랫폼
      - 하나의 물리적 PC를 여러 개의 PC로 가상화하고, 가상화한 PC에 VM을 작동시킴
  - 호스트 OS와 별도로 각 VM은 OS를 포함
  - 각 VM은 OS를 포함하기 때문에 리소스 사용량이 많음

- **도커 (Docker)**
  - 컨테이너 기반 가상화 기술
  - 호스트 OS 위에서 동작하며, 호스트 OS 커널을 공유함
  - 호스트 OS 커널을 공유해 각 컨테이너는 리소스 사용량이 적음
  - 애플리케이션을 실행하기 위한 최소한의 패키지만 설치하기 때문에 가벼움

> **커널 (Kernel)**

- OS의 핵심 부분
- 하드웨어와 소프트웨어 간의 통신을 관리하고 시스템 자원을 제어하는 프로그램
- 메모리 관리, 프로세스 스케줄링, 파일 시스템 관리 등 OS의 가장 기본적이고 중요한 기능 담당

> **윈도우 OS / 맥 OS와 리눅스 OS 컨테이너의 커널 공유**

- 도커 컨테이너는 대부분 리눅스 OS 기반 → 호스트 OS도 리눅스여야 함
- **🤔 윈도우와 맥이 어떻게 리눅스 기반 컨테이너를 실행할 수 있을까?**
  - 윈도우는 WSL2라는 가상화 기술, 맥은 Hypervisor.framework 라는 가상 머신을 지원함
  - 각 가상화 기술이 리눅스 커널을 제공
  - 리눅스 커널을 통해 도커 컨테이너를 실행함

## <mark color="#fbc956">이미지</mark>

### 1. 컨테이너의 템플릿

- 소프트웨어를 실행하기 위한 OS, 코드, 프로그램, 라이브러리 등을 모두 포함하는 독립적인 실행 패키지
- 컨테이너를 생성하기 위한 템플릿
  - 하나의 이미지로 여러 개의 똑같은 컨테이너 생성 가능
- 이미지는 생성 불가, 수정 필요 시 새로운 이미지 생성 필요 (불변성)
- 이미지는 여러 개의 계층(Layers)으로 구성
  - 각 계층은 캐싱됨

### 2. 이미지 명령어

- **`docker pull [이미지 이름]:[태그]`**

  - Docker Hub에서 이미지를 다운 받음
  - 태그 미작성 시 `[이미지 이름]:latest` 로 인식

  ```bash
  docker pull hello-world
  ```

- **`docker images`**

  - 로컬 이미지 목록 출력

- **`docker rmi [이미지 이름]:[태그]`**

  - 이미지 삭제
  - 삭제하려는 이미지 기반 컨테이너가 있으면 이미지 삭제 불가

  ```bash
  docker rmi hello-world
  ```

- **`docker rmi -f [이미지 이름]:[태그]`**

  - 이미지 기반 컨테이너가 있어도 이미지를 강제 삭제

- **`docker image prune`**

  - 태그 없고, 이미지 기반 컨테이너가 없는 이미지를 모두 삭제

- **`docker image prune -a`**

  - 이미지 기반 컨테이너가 없는 이미지 모두 삭제

- **`docker inspect [이미지 이름]:[태그]`**

  - 이미지의 상세 정보 출력

  ```bash
  docker inspect hello-world
  ```

- **`docker tag [이미지 이름]:[태그] [새 이미지 이름]:[태그]`**
  - 이미지의 이름과 태그 추가
  - 기존 이미지는 수정하지 않고, 동일한 이미지 데이터를 참조하는 새로운 이미지 생성
  - 기존 이미지와 새 이미지의 참조하는 IMAGE ID가 동일
    - 두 개는 이름과 태그만 다르고 동일한 이미지임
  ```bash
  docker pull hello-world
  docker tag hello-world my/hello-world:my
  docker images
  ```

### 3. Docker Hub

- 도커 공식 원격 이미지 저장소
- 도커 개발사와 각 생성한 공식 이미지 및 사용자 개인 이미지도 올릴 수 있음

### 4. Docker Hub 관련 명령어

- **`docker login`**

  - Docker Hub에 로그인
  - 명령어 입력 시 메세지 나올 경우 https://login.docker.com/activate 에 접속해 코드 입력함
  - 코드 입력 후 로그인 페이지가 나오며 로그인함

  ```bash
  USING WEB-BASED LOGIN
  To sign in with credentials on the command line, use 'docker login -u <username>'

  Your one-time device confirmation code is: 코드
  Press ENTER to open your browser or submit your device code here: https://login.docker.com/activate

  Waiting for authentication in the browser…
  ```

- **`docker login -u [사용자명]`**

  - Docker Hub에 사용자명, 패스워드를 통해 로그인
  - 명령어 입력 시 패스워드 입력을 요청함

- **`docker logout`**

  - 로그아웃

- **`docker push [사용자명]/[이미지 이름]:[태그]`**

  - 로그인한 Docker Hub 계정으로 이미지 업로드
  - 업로드한 도커 이미지는 Docker Hub - Repositories 페이지에서 확인 가능

- **`docker info`**
  - 현재 로그인 정보

---

## <mark color="#fbc956">컨테이너</mark>

### 1. 이미지의 인스턴스

- 이미지를 기반으로 생성하고 실행한 독립적인 인스턴스
- 여러 개의 컨테이너를 생성 및 실행 가능
- 각 컨테이너는 기본적으로 독립적인 환경을 가짐
- 컨테이너 내부에서 실행한 프로세스 종료될 경우, 컨테이너도 함께 종료됨

### 2. 컨테이너 명령어

- **`docker run [옵션] [이미지 이름]:[태그]`**

  - 이미지를 기반으로 컨테이너 생성 및 실행
  - 태그 미작성 시 `[이미지 이름]:latest` 으로 인식
  - 로컬 이미지 목록에 존재하지 않을 경우 Docker Hub에서 해당 이미지 검색해 다운로드 받음
  - 컨테이너 생성 시 다양한 옵션 설정 가능

  ```bash
  docker run hello-world
  ```

- **`docker ps`**

  - 실행 중인 컨테이너 목록 출력

- **`docker ps -a`**

  - 정지 및 실행 중인 모든 컨테이너 목록 출력

- **`docker start [컨테이너 이름/ID]`**

  - 컨테이너 실행

- **`docker stop [컨테이너 이름/ID]`**

  - 컨테이너 정지

- **`docker rm [컨테이너 이름/ID]`**

  - 정지 상태의 컨테이너 삭제

- **`docker rm -f [컨테이너 이름/ID]`**

  - 실행 상태의 컨테이너 강제 종료 후 삭제

- **`docker container prune`**

  - 정지 상태의 모든 컨테이너 삭제

- **`docker logs [컨테이너 이름/ID]`**

  - 컨테이너 로그 출력

- **`docker logs -f [컨테이너 이름/ID]`**

  - 실시간으로 컨테이너 로그 출력

- **`docker exec -it [컨테이너 이름/ID] /bin/bash`**

  - 컨테이너 내부 터미널에 접속
  - 컨테이너에 bash가 없어 bash로 접속할 수 없을 경우
    - **`docker exec -it [컨테이너 이름/ID] /bin/sh`** 로 접속
  - `exit` : 접속 종료

- **`docker cp [호스트 파일 / 폴더 경로] [컨테이너 이름]:[컨테이너 내부 경로]`**

  - 호스트 파일 또는 폴더를 컨테이너 내부 경로로 복사

- **`docker commit [컨테이너 이름/ID] [이미지 이름]:[태그]`**

  - 컨테이너의 현재 상태를 이미지로 저장

- **`docker top [컨테이너 이름/ID]`**

  - 컨테이너 내부에서 현재 실행 중인 서비스 출력

- **`docker inspect [컨테이너 이름/ID]`**
  - 컨테이너의 현재 상태 출력

### 3. 컨테이너 `run` 옵션

- **`docker run --name [컨테이너 이름] [이미지 이름]`**

  - 컨테이너에 이름 부여
  - 이름 미지정 시 임의로 이름 지정함

  ```bash
  docker run --name hello-world hello-world
  ```

- **`docker run -p [호스트 Port]:[컨테이너 Port] [이미지 이름]`**

  - `--publish` 의 약자
  - 호스트와 컨테이너 간 포트 매핑
  - 호스트에서 특정 포트 사용 중일 경우, 해당 포트와 매핑된 컨테이너 생성 불가
  - ex. `docker run -p 80:80`
    - 호스트의 80포트로 접속 시 도커 컨테이너의 80 포트로 연결됨
    ```bash
    docker run -p 80:80 nginx
    ```
  - ex. `docker run -p 3000:8080`
    - 호스트의 3000 포트로 접속 시 도커 컨테이너의 8080 포트로 연결

- **`docker run -d [이미지 이름]`**

  - `--detach` 의 약자
  - 컨테이너를 백그라운드에서 실행

  ```bash
  docker run -d -p 80:80 nginx
  ```

  - **`docker run -d [이미지 이름] sleep infinity`**
    - 컨테이너가 백그라운드에서 실행
    - 실행 중인 프로세스가 없어도 종료되지 않음

- **`docker run -it [이미지 이름] bash`**

  - 컨테이너에 가상 터미널을 할당
  - 사용자의 입력과 상호작용 가능하게 함

  ```bash
  docker run -it ubuntu bash
  ```

  - **`docker run -it -d [이미지 이름]`**
    - 컨테이너가 백그라운드에서 실행
    - 실행 중인 프로세스가 없어도 종료되지 않음

- **`docker run -e key=value [이미지 이름]`**

  - `--env` 의 약자
  - 컨테이너 내부에서 실행될 서비스에 값을 전달하기 위해 환경 변수 설정
  - 특정 이미지는 초기 설정을 위한 환경 변수가 필수일 수 있음
    - ex. MySQL 이미지는 Root 계정 비밀번호 설정이 필수임
    ```bash
    docker run -e MYSQL_ROOT_PASSWORD=1q2w3e4r! -e MYSQL_DATABASE=demo -p 3307:3306 -d mysql:8.0
    ```

- **`docker run --env-file [환경 변수 파일 이름] [이미지 이름]`**

  - 컨테이너 생성 시 환경 변수 파일을 읽어 환경 변수를 설정함

  ```bash
  docker run --env-file .env -d mysql
  ```

- **`docker run -rm [이미지 이름]`**
  - 컨테이너 종료 시 자동으로 컨테이너 삭제됨
  - 일회성 컨테이너에 활용

---

### ☀️ 오늘의 배움

- WSL

  - Linux용 Windows 하위 시스템
  - 별도의 가상 머신 또는 이중 부팅 없이 Windows 컴퓨터에서 Linux 환경을 실행할 수 있는 Windows 기능

- **이미지**

  - 자바의 클래스와 같음
  - 여러 개의 계층으로 이루어짐
    - 계층은 캐싱됨 → 결과를 저장 후 요청할 경우 저장된 곳에서 결과를 가지고 옴
  - 여러 개의 이미지를 R/W Layer에 올리면 컨테이너가 완성됨
  - **`docker pull [이미지 이름]:[태그]`**
    - Docker Hub에서 이미지를 다운받음
    - 태그 : 이미지 구분 역할, 버전임

- lts

  - 안정화된 버전

- **컨테이너**

  - 실행가능한 이미지
  - **`docker run [옵션] [이미지 이름]:[태그]`**
    - 도커를 생성 및 실행함
    - 이미지가 없을 경우 hub에서 찾아 자동으로 pull해서 다운받음
  - **`docker ps`**
    - 실행 중인 컨테이너 목록 출력
    - 🤔 `docker run hello-world` 실행 후 `ps`에서 왜 아무것도 뜨지 않나 ?
      - `run` 한 뒤, 실행 중인 프로세스가 없으면 자동으로 종료되서 `ps`에서 뜨지 않음
  - **`docker start[컨테이너 ID]`**
    - 🤔 `docker start[컨테이너 ID]` 실행 후 아무것도 실행되지 않은 이유 ?
      - hello-world는 한번 실행 후 실행되는게 없어서
  - **`docker logs [컨테이너 ID]`**
    - 외부(내 컴퓨터)에서 컨테이너 내부에 일어난 상황을 확인하고 싶을 때 사용

- `-itd`

  - `-d` : 백그라운드에서 실행
  - `-it` : 컨테이너를 끄지않고 계속 켜둔 상태로 유지하기 위함

- **`exec`**

  - 컨테이너 내부에서 제어해야할 경우, 터미널에 접속

- 포트 (port)

  - 하나의 pc와 여러 통신을 하는 프로그램이 존재할 때
  - 식별 번호

- `-e`

  - 환경 변수 관련

- 호스트 볼륨

  ```java
  docker run -itd -v [호스트 PC 경로]:/app node

  docker exec -it 51 sh
  ```

- **`@ModelAttribute`**
