## <mark color="#fbc956">리눅스 (Linux)</mark>

### 1. 리눅스 (Linux)

- 서버 OS로 많이 활용되는 오픈 소스 무료 OS
- 윈도우, 맥에 비래 상대적으로 적은 양의 리소스를 요구하는 경량화 OS
- 장점 : 사용자의 필요에 따라 커스터마이징 가능
- 단점 : 시작 난이도 어려움

### 2. 리눅스 배포판

- 기본 리눅스 OS를 용도에 맞춰 재가공한 OS들
- 일반 사용자 용도, 서버 용도, 임베디드 용도 등 다양한 형태 있음
- 대표적 리눅스 배포판 : Ubuntu, Debian 계열, Red Hat 계열

### 3. 리눅스 기본 폴더(폴더) 구조

- `/` : 시스템의 루트 폴더 (최상위 경로)
- `~` : 현재 사용자의 홈 폴더 (`/home/username`)
- `/root` : 관리자 계정의 홈 폴더
- `/home` : 모든 사용자의 홈 폴더(`~`) 의 상위 폴더
- `/bin` : 기본 명령어 실행 파일 저장 폴더
- `/var` : 로그, 캐시 등 변동성 데이터 저장 폴더
- `/tmp` : 임시 파일 저장 폴더
- `/etc` : 설정 파일 저장 폴더
- `/opt` : 추가 패키지 설치 폴더

---

## <mark color="#fbc956">파일 및 폴더 명령어</mark>

- **`/cd [폴더 경로]`**

  - 터미널상 폴더 경로 이동

  ```bash
  cd /
  ```

- **`/toch [파일 이름]`**

  - 파일 생성

  ```bash
  touch file.txt
  ```

- **`/mkdir [폴더 이름]`**

  - 폴더 생성

  ```bash
  mkdir folder
  ```

- **`cat [파일 이름]`**

  - 파일 내용 출력

  ```bash
  cat file.txt
  ```

- **`cp [원본 파일] [복사 파일]`**

  - 파일 복사

  ```bash
  cp file.txt copy_file.txt
  ```

- **`cp -r [원본 폴더] [복사 폴더]`**

  - 폴더 복사

  ```bash
  cp -r folder copy_folder
  ```

- **`rm [파일 이름]`**

  - 파일 삭제

  ```bash
  rm copy_file.txt
  ```

- **`rm -r [파일 이름]`**

  - 폴더 삭제

  ```bash
  rm -r copy_folder
  ```

- **`ls [옵션]`**

  - 현재 폴더 내 파일 및 폴더 목록 출력
  - 다양한 옵션이 있으며 한 번에 옵션을 여러 개 지정 가능

  ```bash
  # 상세 정보 출력
  ls -l

  # 숨김 파일 및 폴더 출력
  ls -a

  # 폴더 내부를 재귀적으로 출력
  ls -R

  # 최신 파일 기준 정렬 출력
  ls -lt

  # 숨김 파일 및 폴더 상세 정보 출력
  ls -al
  ```

- **`pwd`**

  - 현재 폴더의 경로 출력

  ```bash
  pwd
  ```

- **`ln -s [원본 파일|폴더 경로] [심볼릭 링크 경로]`**
  - 심볼릭 링크(바로가기) 생성

---

## <mark color="#fbc956">패키지 관리 명령어</mark>

> **`apt` vs `yum`**
>
> - Debian 계열 리눅스 배포판은 apt 명령어 사용
> - Red Hat 계열 리눅스 배포판은 yum 명령어 사용

> **`sudo` (superuser do)**
>
> - 일반 사용자가 관리자 권한으로 명령 실행 시 사용하는 명령어

- **`apt update`**

  - 패키지 목록만 업데이트

  ```bash
  sudo apt update
  ```

- **`apt upgrade`**

  - 설치된 패키지를 최신 버전으로 업데이트

  ```bash
  sudo apt upgrade
  ```

- **`apt install [패키지 이름]`**

  - 패키지 설치

  ```bash
  sudo apt install vim
  ```

- **`apt remove [패키지 이름]`**
  - 패키지 삭제
  ```bash
  sudo apt remove vim
  ```

---

## <mark color="#fbc956">사용자 & 권한 명령어</mark>

- **`whoami`**

  - 현재 로그인한 사용자 확인

- **`id [사용자 이름]`**

  - 사용자 ID와 그룹 정보 확인

  ```bash
  id username
  ```

- **`adduser [사용자 이름]`**

  - 새 사용자 추가

  ```bash
  sudo adduser newuser

  # 명령어 오류 발생 시 패키지 설치
  # sudo apt update
  # sudo apt install adduser
  ```

- **`deluser [사용자 이름]`**

  - 사용자 삭제

  ```bash
  sudo deluser username
  ```

- **`passwd [사용자 이름]`**

  - 사용자 비밀번호 설정 또는 변경

  ```bash
  sudo passwd username
  ```

- **`chmod [권한] [파일|폴더명]`**

  - 파일 또는 폴더 권한 변경

  ```bash
  chmod 755 file.txt
  ```

- **`usermod -aG [그룹 이름] [사용자 이름]`**

  - 사용자를 그룹에 추가

  ```bash
  sudo usermod -aG docker ubuntu
  ```

- **`group [사용자 이름]`**

  - 사용자가 속한 그룹 목록 출력

- **`cat /etc/passwd`**

  - 사용자 목록 출력

- **`cat /etc/group`**
  - 그룹 목록 출력

---

## <mark color="#fbc956">프로세스 제어 명령어</mark>

- 현재 실행 중인 프로세스 확인

  ```bash
  ps aux

  # 트리 형태
  pstree

  # 포트를 포함한 실행 중인 프로세스 확인
  sudo netstat -tulnp
  ```

- **`sudo lsof -i:[포트 번호]`**

  - 특정 포트를 사용하고 있는 프로세스 확인

  ```bash
  sudo lsof -i:80
  ```

- **`sudo kill -9 [PID]`**

  - 특정 프로세스 종료

  ```bash
  sudo kill -9 1234
  ```

- **`systemctl start [서비스 이름]`**

  - 특정 서비스 시작

  ```bash
  sudo systemctl start nginx
  ```

- **`systemctl stop [서비스 이름]`**

  - 특정 서비스 중지

  ```bash
  sudo systemctl stop nginx
  ```

- **`systemctl restart [서비스 이름]`**

  - 특정 서비스를 부팅 후 자동 시작하도록 설정

  ```bash
  sudo systemctl enable nginx
  ```

- **`systemctl disable [서비스 이름]`**
  - 특정 서비스를 부팅 후 자동 시작하지 않도록 설정
  ```bash
  sudo systemctl disable nginx
  ```

---

## <mark color="#fbc956">네트워크 명령어</mark>

- **`ping [URL 또는 IP]`**

  - 네트워크 연결 상태 확인

  ```bash
  ping google.com
  ```

- **`wget [URL]`**

  - 파일 다운로드

  ```bash
  wget http://example.com/file.zip
  ```

- **`ss -tuln`**

  - 네트워크 상태 확인

  ```bash
  ss -tuln
  ```

- **`ip addr show`**
  - 네트워크 정보 확인
  ```bash
  ip addr show
  ```

> **iproute2 패키지**
>
> - `ss` 명령어와 `ip` 명령어 실행 위해 필요한 패키지
> - 위 명령어 실행되지 않을 경우 iproute2 패키지 설치함
>   ```bash
>   sudo apt update
>   sudo apt install iproute2
>   ```

---

## <mark color="#fbc956">리눅스 권한 시스템</mark>

### 1. 파일과 폴더 권한

- 파일 및 폴더 권한 확인 명령어와 출력 예시

  ```bash
  ls -l
  ```

  ```bash
  -rwxr-xr--
  ```

- **권한 요소**
  - 첫 번째 문자
    - 파일의 유형 나타냄
      - `-` : 일반 파일
      - `d` : 폴더
      - `l` : 심볼릭 링크
      - `c` : 블록 장치
  - 나머지 9개 문자
    - 파일의 권한을 나타내며 3개씩 묶어서 읽고, 3개의 주체가 있음
      - `r` : 읽기 (read, 4)
      - `w` : 쓰기 (write, 2)
      - `x` : 실행 (execute, 1)
      - `-` : 권한 없음 (0)
    - 3개의 주체
      - Owner : 파일과 폴더를 생성한 소유자(u)
      - Group : 파일과 폴더를 공유하는 그룹(g)
      - Others : 소유자와 그룹에 속하지 않는 모든 사용자(o)
  - 권한 해석
    - `-` : 일반 파일
    - `rwx` : Owner - 읽기 / 쓰기 / 실행 권한
    - `r-x` : Group - 읽기 / 실행 권한
    - `r--` : Others - 읽기 권한

### 2. 권한 숫자 표기법

- 파일 / 폴더 권한을 숫자로 표기하는 방법
  - `r` : 읽기 (read, 4)
  - `w` : 쓰기 (write, 2)
  - `x` : 실행 (execute, 1)
  - `-` : 권한 없음 (0)
- 숫자의 합을 통해 권한 표기
- 예시
  - 7(`rwx`) = 4 + 2 + 1
  - 5(`r-x`) = 4 + 1
  - 0(`---`) = 0

### 3. 권한 변경

- 숫자 표기법

  ```bash
  chmod 755 [파일 이름]
  ```

  - 소유자 : `rwx`
  - 그룹 : `r-x`
  - 기타 사용자 : `r-x`

- 문자 표기법
  - 소유자 / 그룹 / 기타 사용자 실행 권한 추가
    ```bash
    chmod +x [파일 이름]
    ```
  - 소유자 실행(x) 권한 추가
    ```bash
    chmod u+x [파일 이름]
    ```
  - 그룹 / 기타 사용자 읽기, 쓰기, 실행 권한 추가
    ```bash
    chmod go+rwx [파일 이름]
    ```

---

## <mark color="#fbc956">성능 관련 명령어</mark>

- 네트워크 속도 측정

  - 패키지 설치
    ```bash
    sudo apt update
    sudo apt install -y speedtest-cil
    ```
  - 속도 측정
    ```bash
    speedtest-cli
    ```

- 성능 모니터링

  - 패키지 설치

    ```bash
    # 필요 시 설치
    sudo apt install htop
    ```

  - CPU와 메모리 사용량 모니터링
    ```bash
    htop
    ```
    - **htop 모니터링 해석**
      1. 왼쪽 그래프 - 각 성능 요소의 사용량
         - CPU (0, 1)
         - RAM
         - Swap 메모리
      2. 오른쪽 정보
         - Tasks : 실행 중인 프로세스 수 / 쓰레드 수 / 커널 쓰레드 수 / 프로세스 수
         - Load average : 1분, 5분, 15분 간격의 CPU 평균 부하, 값이 코어 수 이상일 경우 부하가 높다는 것을 의미
  - 디스크 사용량
    ```bash
    df -h
    ```
  - Docker가 사용 중인 용량 확인
    ```bash
    docker system df
    ```

- swap 메모리 설정

  ```bash
  # 1. swap 파일 생성
  sudo fallocate -l 2G /swapfile

  # 2. swap 파일 권한 설정
  sudo chmod 600 -swapfile

  # 3. swap 영역으로 포맷
  sudo mkswap /swapfile

  # 4. swap 활성화
  sudo swapon /swapfile

  # 5. 부팅 시 자동으로 swap이 마운트되도록 /etc/fstab에 추가
  echo '/swapfile none swap sw 0 0' | sudo tee -a /etc/fstab

  # 6. swap 설정 확인
  sudo swapon --show
  ```

  - **Swap 메모리**
    - RAM이 부족할 때 추가로 사용할 수 있는 가상 메모리 영역
    - RAM이 부족할 때 디스크 용량을 일부 가지고 와 임시로 RAM처럼 사용
    - 다만, RAM보다 속도 느림

---

## <mark color="#fbc956">방화벽 명령어</mark>

### 1. 방화벽(UFW) 설치

```bash
sudo apt update
sudo apt install ufw -y
```

### 2. 방화벽 명령어

- 방화벽 상태 확인

  ```bash
  sudo ufw status
  ```

- 방화벽 기본 정책 설정

  ```bash
  sudo ufw default deny incoming
  sudo ufw default allow outgoing
  ```

- **`sudo ufw allow [포트 번호]`**

  - 특정 포트 허용

  ```bash
  sudo ufw allow 22
  ```

  ```bash
  sudo ufw allow 2222
  ```

- **`sudo ufw deny [포트 번호]`**

  - 특정 포트 차단

  ```bash
  sudo ufw deny 22
  ```

- 방화벽 활성화

  - 현재 연결 상태의 SSH은 접속 유지되지만, 새로운 연결은 불가능
  - 방화벽 활성화 이전, SSH 포트 번호는 꼭 허용하고 활성화 해야 함

  ```bash
  sudo ufw enable
  ```

- 규칙 확인

  ```bash
  sudo ufw status numbered
  ```

- 방화벽 비활성화
  ```bash
  sudo ufw disable
  ```

---

### ☀️ 오늘의 배움

- `htop`
  - 성능 모니터링
- `ufw` 기능
  - 특정 IP에 대해서만 허용
  - 특정 포트에 대해 인바운드, 아웃바운드 설정
