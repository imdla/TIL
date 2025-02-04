![image.png](/Sesac/assets/day69.png)

## <mark color="#fbc956">CI (Continuous Integration)</mark>

### 1. 지속적 통합

- 코드의 변경 사항을 공유 저장소(Github)에 정기적으로 자주 병합하는 방식
- 병합과 함께 자동으로 애플리케이션 빌드와 테스트까지 이루어질 때 CI라 함
- 코드 변경 사항이 자주 병합되므로, 배포 프로세스가 더 신속하고 안정적으로 진행될 수 있음
  - CI를 통해 코드가 지속적으로 검증되고 자동화된 테스트 거쳐, 배포 전 단계에서 발생 가능한 오류를 조기에 감지해 수정 가능

### 2. 장점

- 자동 테스트 통해 테스트 빠르게 발견 가능
- 개발자간 코드 병합 시 충돌 최소화 가능
- 자동 테스트와 빌드 통해 테스크 줄임
- 코드 품질을 지속적으로 유지 및 개선 가능
- 일관된 개발 환경 제공해 팀 생산성 높일 수 있음

## <mark color="#fbc956">CD</mark>

### 1. 지속적 제공(Continuous Delivery)와 지속적 배포(Coninuous Deployment)

- **지속적 제공**

  - 자동화 배포 파이프라인을 통해 테스트를 통과한 코드를 언제든 배포할 준비를 하는 프로세스
  - 최종 배포는 사람의 승인을 통해 이루어짐
  - 코드가 배포 가능한 상태를 항상 유지해, 배포 과정에서 예기치 않은 문제가 발생할 가능성이 줄어듦
  - 개발팀과 운영팀 간 협업을 원활하게 만들어줌

- **지속적 배포**
  - 자동화 배포 파이프라인을 통해 테스트를 통과한 코드를 자동으로 배포하는 프로세스
  - 승인 없이 프로세스에 의해 자동으로 배포 이루어짐

---

## <mark color="#fbc956">CI / CD 파이프라인 구성 요소</mark>

- 소스 코드 관리(SCM, Source Code Management) : GitHub 등을 사용해 코드 버전을 관리하고 코드를 업데이트
- 테스트(Test) : 단위 테스트 및 통합 테스트 수행
- 빌드(Build) : 코드 컴파일, 의존성 설치, 번들링 수행
- 배포(Deploy) : 코드를 개발 또는 운영 서버 환경에 배포

---

### ☀️ 오늘의 배움

- **SSH 포트 번호 변경**

  1. **AWS 보안 → 인바운드 규칙 2222 포트 추가**
  2. **SSH 포트 번호 변경 (22 → 2222)**

     1. `/etc/ssh/sshd_config` 파일 수정

        ```bash
        sudo vim /etc/ssh/sshd_config
        ```

     2. Port 설정 변경 후 저장

        ```bash
        # Port 22
        Port 2222
        ```

     3. SSH 재실행

        ```bash
        sudo systemctl daemon-reload
        sudo systemctl restart sshd
        ```

     4. SSH 서비스 상태 확인

        ```bash
        sudo systemctl status ssh
        ```

  3. **Linux 환경에서 Docker 설치**

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

     2. docker 설치

        ```bash
        sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin -y
        ```

     3. Docker 사용 권한 위한 현재 사용자를 Docker 그룹에 추가

        - `-aG` : docker 그룹에 현재 사용자 추가

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

  4. **nginx 컨테이너 설치 및 실행**

     ```bash
     docker run --name nginx -p 80:80 nginx
     ```

  5. **mysql 컨테이너 설치 및 실행**

     ```bash
     docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1q2w3e4r! -e MYSQL_DATABASE=demo mysql:8.0
     ```

- **CI / CD**
  - CI
    - Github 정기적 병합
    - build, test 까지 과정
  - CD
    - 자동 배포 과정
