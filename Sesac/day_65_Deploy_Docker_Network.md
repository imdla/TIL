## <mark color="#fbc956">Docker Network</mark>

### 1. 컨테이너 내부의 네트워크

- 컨테이너는 기본적으로 독립되어 있지만 도커 네트워크를 통해 컨테이너 간에 직접 통신 가능
- 필요에 따라 외부 네트워크(호스트 네트워크 등)과 격리 가능

### 2. 네트워크 드라이버

- **Bridge 네트워크**

  - 컨테이너 생성 시 네트워크를 지정하지 않을 경우 설정되는 기본 네트워크 드라이버
  - 각 컨테이너는 호스트 네트워크와 독립적으로 컨테이너만의 IP와 포트 할당 받음
  - 컨테이너는 동일한 Bridge 네트워크에 속한 컨테이너와 통신 가능
  - 사용자가 정의한 Bridge 네트워크에 속한 컨테이너 간에는 컨테이너 이름을 도메인 이름으로 사용해 통신 가능

- **Host 네트워크**

  - 컨테이너 자체 네트워크를 사용하지 않고, 호스트 네트워크(IP, 포트)를 그대로 사용
  - 호스트 네트워크를 공유하기 때문에 바인딩 `-p` 이 필요하지 않음
  - `docker run -d --network host [이미지]`

- **None 네트워크**
  - 컨테이너의 네트워크를 완전히 격리
  - 외부와 통신이 불가능
  - `docker run -d --network none [이미지]`

### 3. Network 명령어

- **`docker network create [Network 이름]`**

  - 새로운 Bridge 네트워크 드라이브 생성

- **`docker network ls`**

  - 도커 네트워크 목록 출력

- **`docker network rm [Network 이름]`**

  - 도커 네트워크를 삭제
  - 연결된 컨테이너가 없어야 삭제됨

- **`docker network connect [Network 이름] [컨테이너 이름]`**

  - 특정 컨테이너를 지정된 네트워크에 연결

- **`docker network disconnect [Network 이름] [컨테이너 이름]`**

  - 컨테이너를 네트워크에서 분리

- **`docker network inspect [Network 이름]`**
  - 네트워크의 상세 정보를 출력

### 4. 컨테이너 생성 시 네트워크 연결

- **`docker run --network [Network 이름] [이미지 이름]`**

  - 지정된 네트워크에 연결한 컨테이너 생성

- **`docker run --network none [이미지 이름]`**

  - none 네트워크에 연결한 컨테이너를 생성

- **`docker run --network host [이미지 이름]`**
  - host 네트워크에 연결한 컨테이너를 생성

### 5. Bridge 네트워크 통신 예시

1. **Bridge 네트워크 생성**

   ```bash
   docker network create user-network
   ```

   ```bash
   # 네트워크 목록 출력
   docker network ls
   ```

1. **BusyBox 컨테이너 2개 생성**

   ```bash
   docker run -it -d --name container1 --network user-network busybox
   ```

   ```bash
   docker run -it -d --name container2 --network user-network busybox
   ```

1. **`container1` → `container2` 통신 테스트**

   ```bash
   docker exec container1 ping container2
   ```

1. **`container2` → `container1` 통신 테스트**

   ```bash
   docker exec container2 ping container1
   ```

---

### ☀️ 오늘의 배움

- **컨테이너 데이터를 외부에 보관 및 공유 방법**

  - `Named Volume` : 도커 시스템이 관리하는 공간에 데이터를 보관
  - `Bind Mount` : 호스트의 파일 시스템을 공유하는 방법

- **Network**

  - 통신을 하기 위한 공간

- **Docker의 Network Mode**

  - **`None`**
    - 외부와 완전히 격리된 상태
    - 네트워크 상으로 통신이 불가
  - **`Host Network`**
    - 내 PC의 네트워크를 공유
    - 호스트 네트워크를 컨테이너 네트워크가 그대로 사용
    - 컨테이너의 포트를 호스트의 포트를 매핑시키지 않음
  - **`Bridge Network`**
    ![image.png](/Sesac/assets/day65.png)
    - Docker가 Bridge 네트워크를 생성함
    - 호스트와 격리된 컨테이너만의 네트워크
    - 컨테이너 포트와 호스트 포트를 매핑시킴
    - **하나의 브릿지 네트워크에 여러 개의 컨테이너가 포함될 수 있음**
      - 브릿지 네트워크 내 컨테이너끼리 통신이 자유로움
      - 컨테이너의 포트를 외부와 매핑하지 않아도 서로 통신 가능
      - IP 주소를 몰라도 **컨테이너 이름**이 도메인의 역할을 함
        (서로 다른 컨테이너가 한 브릿지 네트워크에 속해있을 때)
        - 컨테이너 이름 → 도커 → 컨테이너 IP
    - 하나의 컨테이너가 여러 개의 브릿지 네트워크에 포함될 수 있음

- **`ping`**

  - 서로 통신이 되는지 문자를 날리는 것
  - 통신이 잘되는지 확인

- **DNS**
  - Domain Name Server
  - 도메인 이름 → IP로 변환하는 기술
