> 💡 **한 줄 요약**
>
> 기본적으로 독립되어 있는 컨테이너지만, 도커 네트워크를 통해 컨테이너 간 직접 통신이 가능하다. 도커 네트워크 드라이버의 종류는 Bridge 네트워크, Host 네트워크, None 네트워크가 있다. 주로 Bridge 네트워크를 많이 사용한다. Bridge 네트워크는 컨테이너 생성 시 설정되는 기본 네트워크 드라이버이다. 각 컨테이너는 호스트 네트워크와 독립적으로 컨테이너만의 IP와 포트를 할당받는데, 동일 Bridge 네트워크에 속한 컨테이너끼리는 컨테이너 이름을 도메인 이름으로 사용해 통신할 수 있다.

### 1. 🤔 왜 사용하는가

- **Docker Network**

  - 컨테이너는 기본적으로 독립되어 있지만, 도커 네트워크 통해 컨테이너 간 직접 통신 가능
  - 필요에 따라 외부 네트워크(호스트 네트워크 등)과 격리 가능

- **Docker 네트워크 드라이버**

  - **Bridge 네트워크**
    - 컨테이너 생성 시 네트워크 미지정 시 설정되는 기본 네트워크 드라이버
    - 각 컨테이너는 호스트 네트워크와 독립적으로 컨테이너만의 IP와 포트 할당 받음
    - 컨테이너는 동일한 Bridge 네트워크에 속한 컨테이너와 통신 가능
    - 사용자가 정의한 Bridge 네트워크에 속한 컨테이너 간에 컨테이너 이름을 도메인 이름으로 사용해 통신 가능
  - **Host 네트워크**
    - 컨테이너 자체 네트워크 사용하지 않고, 호스트 네트워크(IP, 포트)를 그대로 사용
    - 호스트 네트워크를 공유해 포트 바인딩 `-p` 가 필요하지 않음
  - **None 네트워크**
    - 컨테이너의 네트워크를 완전히 격리
    - 외부와의 통신 불가능

- **Bridge 네트워크에서 컨테이너 간 통신 과정**
  1. Bridge 네트워크 생성

     ```bash
     docker network create user-network
     ```

  2. BusyBox 컨테이너 2개 생성

     ```bash
     docker run -it -d --name container1 --network user-network busybox
     docker run -it -d --name container2 --network user-network busybox
     ```

  3. 통신 테스트

     ```bash
     # container1 -> container2 통신 테스트
     docker exec container1 ping container2

     # container2 -> container1 통신 테스트
     docker exec container2 ping container1
     ```
