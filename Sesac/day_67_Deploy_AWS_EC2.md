## <mark color="#fbc956">AWS EC2 (Elastic Compute Cloud)</mark>

### 1. 클라우드 기반 가상 서버

- AWS 서비스 중 가장 유명한 서비스 중 하나
- 사용자가 필요한 자원을 필요한 시간만큼 사용 가능한 가상 컴퓨팅 서비스
- 개인을 위한 소규모 서버, 기업 서비스 운영을 위한 중규모 서버, 인공지능을 위한 고성능 서버 등 많은 유형의 서버를 제공하며 이렇게 설계된 서버를 인스턴스(Instance)라 지칭함
- 필요에 따라 인스턴스를 생성, 시작, 중지, 재시작을 할 수 있게 서버의 성능을 유연하게 조절 가능
- 사용자는 각 인스턴스의 시간 당 사용량만큼 비용 지불함

### 2. T 시리즈 인스턴스

- 인스턴스의 종류는 수백 개가 있음, 범용 인스턴스인 T2와 T3가 있음
- T3 인스턴스
  - T2 인스턴스의 개선 버전
  - 동일한 성능을 더 낮은 비용으로 사용 가능
  - t2.micro만 프리티어가 가능
- 동일한 시리즈라도 nano ~ 2xlarge까지 등급 나누어져 있음
  - 등급 높을수록 사양 높으며 높은 비용 요구함
- **등급별 용도**
  - nana, micro : 경량 웹 서비스 또는 웹 사이트
  - small : 시작 단계의 웹 서비스
  - medium, large : 소규모 트래픽이 발생하는 웹 서비스
  - xlarge, 2xlarge : 고성능 웹 서비스 또는 데이터베이스

### 3. 인스턴스 생성

1. AWS EC2 콘솔 진입 후 인스턴스 시작 버튼 클릭
   - **EC2 콘솔**
     - AWS에서 콘솔이라는 단어는 각 서비스를 관리할 수 있는 웹 인터페이스
     - EC2 콘솔은 EC2 관리 웹 페이지
2. 인스턴스 이름
3. AMI : `ubuntu`
   - **AMI (Amazon Machine Image)**
     - 인스턴스의 기반이 되는 OS 이미지
     - AWS에 적합한 Amazon Linux, 범용적인 리눅스 배포판 Ubuntu 등 다양한 이미지를 제공해 이미지에 따라 비용이 달라질 수 있음
4. 인스턴스 유형 : `t2.micro`
5. 키 페어 생성
   - 키 페어 유형 : `RSA`
   - 프라이빗 키 파일 형식 : `.pem(Private-Enhanced Mail)`
   - **키 페어**
     - 공개 키(Public Key)와 개인 키(Private Key)로 구성된 인스턴스 접근 인증 수단
     - Public Key : 인스턴스(또는 서버 PC)에 저장되는 공개 자물쇠 역할
     - Private Key : 사용자가 가지는 개인 열쇠 역할
6. 네트워크 설정
7. 스토리지 구성
8. 고급 세부 정보 - 사용자 데이터 : 스크립트 작성

   ```bash
   #!/bin/bash
   sudo apt update -y
   sudo apt install -y nginx

   echo "<h1>Hello World</h1>" > /var/www/html/index.html

   sudo systemctl restart nginx
   ```

9. 인스턴스 시작

### 4. EBS (Elastic Block Store)

- 인스턴스와 연결된 네트워크 저장 장치
  - 볼륨 : 하나의 저장 장치
- 인스턴스 종료된 후에도 데이터를 저장하고 있음
  - 볼륨은 생성할 때 용량 결정해야 함
- EBS 볼륨은 특정 가용 영역에 고정되어 있음
  - 일반적으로 하나의 인스턴스와만 연결 가능
  - ap-northeast-2a 가용 영역 볼륨은 ap-northeast-2b에서 연결할 수 없음
- 인스턴스와 꼭 연결되어 있을 필요 없음
  - 인스턴스 삭제해도 EBS 볼륨은 보존 가능

---

## <mark color="#fbc956">보안 그룹 (Security Group)</mark>

### 1. 보안 그룹

- EC2 인스턴스의 네트워크 보안을 담당하는 가상 방화벽
- 인스턴스로 들어가거나(인바운드) 인스턴스에서 나오는(아웃바운드) 트래픽 제어 가능

- **규칙 편집 방법**
  1. 보안 그룹 콘솔 접속 후 수정 할 보안 그룹 클릭
  2. 인바운드 또는 아웃바운드 규칙을 편집
     - 인바운드 : 외부에서 인스턴스로 접근
     - 아웃바운드 : 인스턴스에서 외부로 접근

### 2. 인바운드 규칙 예시

1. **0.0.0.0/0**
   - 모든 IP 주소
2. **SSH**
   - 프로토콜 : TCP
   - 포트 : 22
   - 용도 : 인스턴스 서버에 원격 접속을 위해 사용
   - 주의사항 : IP 범위를 0.0.0.0/0으로 설정 시 모든 IP가 접근 가능하지만 매우 위험하므로 특정 IP만 허용하는 것이 좋음
3. **HTTP/HTTPS**
   - 프로토콜 : TCP
   - 포트 : 80 / 443
   - 용도 : 사용자의 HTTP 요청을 받기 위해 사용
4. **데이터베이스 포트**
   - 프로토콜 : TCP
   - 포트 : 3306(MySQL, MariaDB), 27017(MongoDB) 등
   - 용도 : 데이터베이스 서버에 연결하기 위해 사용
   - 기타 : 필요에 따라 인스턴스에 설치된 데이터베이스 서버의 포트 변경 후 규칙에서 해당 포트 사용 가능

- **인스턴스 기본 Public IP vs Elastic IP**
  - EC2 인스턴스의 기본 Public IP는 인스턴스가 중지되고, 다시 시작되면 변경됨
  - Elastic IP는 IP가 고정되지만 경우에 따라 요금이 부과됨, 고정 IP가 필요한 경우 Elastic IP 활용
    - Elastic IP가 생성되고 사용되지 않는 경우
    - Elastic IP를 6개 이상 샹성한 경우

---

## <mark color="#fbc956">SSH (Secure Shell)</mark>

### 1. 원격 접속 프로토콜

- 네트워크를 통해 A컴퓨터에서 B컴퓨터에 원격으로 제어 위해 사용되는 프로토콜
- 기본적으로 22번 포트 사용
- 물리적으로 접근할 수 없는 서버 PC에 원격으로 접속해 해당 PC에 명령어 입력 가능
- username과 password를 활용하는 방법, 공개 키와 개인 키를 활용하는 방법이 있지만 보안을 위해 공개 키와 개인 키 활용을 권장함
- SSH 연결을 위해 서버의 Public IP(외부 IP) 또는 DNS와 사용자명(username)이 필요함

  - AWS EC2 인스턴스의 Public IP와 DNS는 인스턴스 목록에서 확인 가능
  - username은 생성함 AMI에 따라 달라짐
    - Amazon Linux : ec2-user
    - Ubuntu : ubuntu
    - CentOS : centos

- **SSH 접속 명령어**

  - username / password 활용

    ```bash
    ssh [username]@[hostIP]

    # 일반적으로 IP를 hostname으로 표현하지만 편의를 위해 hostIP로 표현
    # ssh username@hostname
    ```

  - 개인 키 활용
    ```bash
    ssh -i [개인 키 경로] [username]@[hostIP]
    ```
  - EC2 인스턴스 접속
    ```bash
    ssh -i [pem 파일 경로] ubuntu@[퍼블릭 IPv4]
    ```

### 2. 공개 키와 개인 키

- 사용자는 공개 키와 개인 키를 생성해 SSH 연결에 활용
- **공개 키 (Public Key)** : 서버에 저장될 키, 자물쇠 역할
- **개인 키 (Private Key)** : 사용자가 보유한 키, 열쇠 역할

- **공개 키와 개인 키 생성 방법**

  1. 명령어 입력

     ```bash
     ssh-keygen -t rsa -b 2048
     ```

  2. 파일 확인

     ```bash
     cd ~/.ssh
     ls

     # id_rsa.pub : Public Key (공개 키)
     # id_rsa : Private Key (개인 키)
     ```

- **서버에 공개 키 등록 방법**

  1. 서버 PC SSH 연결
  2. `~/ .ssh/authorized_keys` 파일에 클라이언트의 공개 키를 복사해 붙여넣음

     - 여러 개의 공개 키를 등록해야 할 경우 줄바꿈을 통해 여러 개의 공개 키 작성

     ```bash
     # 주석을 통해 공개 키를 구분하고, 설명할 수 있다.
     # 윈도우 PC 공개 키
     ssh-rsa AAA...

     # 맥북 공개 키
     ssh-rsa AAA...
     ```

  3. 비밀 키로 SSH 연결

     ```bash
     ssh -i [개인 키 경로] [username]@[hostIP]
     ```

- **known_hosts**
  - SSH 연결을 하면 known_hosts 파일이 생성되는데 해당 파일은 서버의 지문(fingerprint)를 저장함
  - fingerprint는 서버의 정보와 서버의 공개 키를 표현하는데 클라이언트는 서버에 연결할 때 서버가 제시하는 서버의 공개 키와 fingerprint를 비교함
  - 이를 통해 서버가 변조되거나 대체되는 것을 감지하는 보안 매커니즘

### 3. SSH 클라이언트

- SSH 연결을 도와주는 클라이언트 프로그램
- Terminus 사용법
  1. Keychain 생성
  2. Host 생성
     - Address : 인스턴스 Public IP
     - Username : 인스턴스 Username
     - Key, Certificate, FIDO2 클릭 후 `Key` 선택 후 생성한 `Keychain` 설정
  3. 생성한 Host 더블 클릭으로 연결
  4. 첫 연결 시 안내문 동의

### 4. SSH 포트 번호 변경

- 포트 번호를 변경해 무차별 공격 대상에서 벗어나 보안 강화 가능

1. `/etc/ssh/sshd_config` 파일 수정

   ```bash
   sudo vim /etc/ssh/sshd_config
   ```

2. 포트 변경 후 저장
   - 수정 전
     ```bash
     #Port 22
     ```
   - 수정 후
     ```bash
     Port 2222
     ```
3. SSH 재실행

   ```bash
   sudo systemctl daemon-reload
   sudo systemctl restart sshd

   # 또는
   sudo systemctl daemon-reload
   sudo systemctl restart ssh
   ```

4. SSH 서비스 상태 확인

   ```bash
   sudo systemctl status ssh
   ```

---

## <mark color="#fbc956">원격 서버 초기 설정</mark>

### 1. 초기 설정

- **Docker 설치**

  1. GPG 키 및 Apt 저장소 설정

     ```bash
     # Add Docker's official GPG key:
     sudo apt-get update
     sudo apt-get install ca-certificates curl
     sudo install -m 0755 -d /etc/apt/keyrings
     sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
     sudo chmod a+r /etc/apt/keyrings/docker.asc

     # Add the repository to Apt sources:
     echo \
       "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
       $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
       sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

     sudo apt-get update
     ```

  2. Docker 설치

     ```bash
     sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin -y
     ```

  3. Docker 사용 권한을 위한 현재 사용자를 Docker 그룹에 추가

     - `-aG` : Docekr 그룹에 현재 사용자 추가

     ```bash
     sudo usermod -aG docker $USER
     ```

  4. 재실행

     ```bash
     sudo reboot
     ```

  5. 설치 확인

     ```bash
     docker -v
     docker run hello-world
     ```

  6. Docker Compose 설치

     ```bash
     sudo curl -SL https://github.com/docker/compose/releases/download/v2.20.2/docker-compose-linux-x86_64 -o /usr/local/bin/docker-compose
     sudo chmod +x /usr/local/bin/docker-compose

     # 설치 확인
     docker-compose --version
     ```

- Git pull 옵션
  - git pull rebase 옵션값을 설정
  ```bash
  git config --global pull.rebase true
  ```
