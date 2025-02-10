## <mark color="#fbc956">Jenkins</mark>

### 1. Jenkins

- 오픈 소스 기반 자동화 서버
- 많은 플러그인 지원, 애플리케이션의 CI/CD 구축을 위해 활용
- Jenkins에서 일련의 작업 과정(워크플로우)을 Job이라 표현

### 2. Jenkins 설치 & 실행 & 접속

- **네트워크 속도 주의**

  - Jenkins 초기 설정 중 플러그인 설치의 경우 네트워크를 통해 플러그인을 다운로드 받음
  - 이때, 네트워크 속도가 느리면 설치 중 오류 발생 가능
  - 특히, t2.micro, t2.small 인스턴스의 경우 네트워크 속도가 느려 오류 발생 확률이 높음

- **디스크 용량 주의**

  - Jenkins 및 기초 플러그인 설치 위한 많은 디스크 용량 필요로 함
  - Jenkins를 실습할 때 EC2 인스턴스 생성 때 스토리지 구성에서 용량을 20GiB로 설정

- **수업 위한 설치 방법**

  - **플러그인 설치 오류**
    - AWS EC2 인스턴스에서 Jenkins 기초 플러그인(확장 프로그램) 설치에 문제 있어
    - 기초 플러그인이 설치된 Docker 이미지를 활용해 Jenkins Docker 컨테이너 형태로 실행

  1. Swap 메모리 설정

     ```bash
     # Swap Memory 설정 파일
     sudo fallocate -l 2G /swapfile

     # 설정 파일 권한 변경
     sudo chmod 600 /swapfile

     # Swap Memory 설정 파일 초기화
     sudo mkswap /swapfile

     # Swap Memory 활성화
     sudo swapon /swapfile

     # 상태 확인 1
     sudo swapon --show

     # 상태 확인 2
     free -m

     # Swap Memory 영구 적용
     echo '/swapfile none swap sw 0 0' | sudo tee -a /etc/fstab
     ```

  2. `docker-compose.yml` 작성

     ```bash
     mkdir ~/jenkins-compose
     vim docker-compose.yml
     ```

     ```yaml
     # docker-compose.yml
     services:
       jenkins:
         container_name: jenkins-container
         image: nodecrewbeemo/jenkins:latest
         restart: unless-stopped # 컨테이너 재시작 옵션
         environment:
           - TZ=Asia/Seoul
         ports:
           - "8080:8080" # Jenkins 웹페이지 포트
         group_add:
           # 컨테이너 실행 시 컨테이너 내부 사용자에게 그룹을 추가
           # 해당 설정을 통해 컨테이너 내부 사용자가 docker 명령어를 사용할 수 있도록 함
           - ${DOCKER_GROUP_ID} # 호스트의 docker 그룹 ID 환경 변수
         volumes:
           - jenkins_home_volume:/var/jenkins_home # Jenkins 데이터 영구 저장
           - /var/run/docker.sock:/var/run/docker.sock # 호스트의 Docker 소켓 마운트, Jenkins에서 Docker 명령어를 사용하기 위해 필요

     volumes:
       jenkins_home_volume: # Jenkins 데이터 저장 볼륨
     ```

  3. 호스트의 docker 그룹 ID를 환경변수로 추가

     ```yaml
     export DOCKER_GROUP_ID=$(getent group docker | cut -d: -f3)
     ```

  4. 컨테이너 생성

     ```bash
     docker compose up -d
     ```

  5. Jenkins 웹 UI 접속

     - **`http://[인스턴스 Public IP]:8080/`**

  6. 초기 비밀번호 확인 및 입력

     ```bash
     docker exec -it jenkins-container bash -c  "cat /var/jenkins_home/secrets/initialAdminPassword"
     ```

  7. `Install suggexted plugins`
  8. 계정 생성 (Create First Admin User)
  9. `Save and Finish`
  10. `Start using Jenkins`

- **정상적인 설치 방법**

  1. Swap 메모리 설정

     - Jenkins의 경우 RAM 16GB 이상 Swap Memory 1GB 이상을 권장
     - 가장 낮은 인스턴스의 경우 메모리 부족으로 실행이 안되기 때문에 Swap 메모리를 설정해 문제를 해결할 수 있음

     ```bash
     # Swap Memory 설정 파일
     sudo fallocate -l 2G /swapfile

     # 설정 파일 권한 변경
     sudo chmod 600 /swapfile

     # Swap Memory 설정 파일 초기화
     sudo mkswap /swapfile

     # Swap Memory 활성화
     sudo swapon /swapfile

     # 상태 확인 1
     sudo swapon --show

     # 상태 확인 2
     free -m

     # Swap Memory 영구 적용
     echo '/swapfile none swap sw 0 0' | sudo tee -a /etc/fstab
     ```

  2. 호스트 PC 직접 설치

     ```bash
     sudo wget -O /usr/share/keyrings/jenkins-keyring.asc \
       https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key
     echo "deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc]" \
       https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
       /etc/apt/sources.list.d/jenkins.list > /dev/null

     sudo apt update

     # Jenkins 설치
     sudo apt install -y fontconfig openjdk-17-jre
     sudo apt install -y jenkins

     # docker 그룹에 jenkins 추가
     sudo usermod -aG docker jenkins

     # 그룹 변경 적용을 위해 jenkins 재시작
     sudo systemctl restart jenkins
     ```

  3. 실행

     ```bash
     # 자동 실행 설정
     sudo systemctl enable jenkins

     # Jenkins 실행
     sudo systemctl start jenkins

     # 실행 상태 확인
     sudo systemctl status jenkins
     ```

     - **Jenkins 실행 안될 때 제거 후 재설치 방법**

       ```bash
       # Jenkins 제거
       sudo apt purge --auto-remove -y jenkins
       ```

       ```bash
       # Jenkins 설치
       sudo apt update
       sudo apt install -y jenkins

       # docker 그룹에 jenkins 추가
       sudo usermod -aG docker jenkins
       ```

       ```bash
       # Jenkins 재실행 및 상태 확인
       sudo systemctl restart jenkins
       sudo systemctl status jenkins
       ```

  4. Jenkins 웹 UI 접속

     - `http://[인스턴스 Public IP]:8080/`
     - 보안 그룹 및 UFW 규칙 확인
       - 8080 연결을 허용한 상태인지 확인

  5. 초기 비밀번호 확인 및 입력

     ```bash
     sudo cat /var/lib/jenkins/secrets/initialAdminPassword
     ```

  6. `Install suggested plugins`

     - **초기 플러그인 설치가 제대로 안됐을 때 플러그인 수동 설치 방법**

       1. 플러그인 설치를 재시고하지 않고, 다음 단계로 넘어감
       2. 메인 화면 - Jenkins 관리 → Plugins → Advanced settings → 업데이트 사이트 경로 수정 : `http://updates.jenkins.io/update-coneter.json`
       3. Avaliable plugins → `skip-certificate-check` 검색 후 설치
       4. Instaleed plugins → 설치가 안된 플러그인을 Avaliable plugins에서 검색해서 수동 설치

  7. 계정 생성 (Create First Admin User)
  8. `Save and Finish`
  9. `Start using Jenkins`

### 3. Jenkins 웹 UI 구성 요소

- **Dashboard**

  1. 새로운 Job 생성
  2. 모든 Job의 빌드 기록 출력
  3. Jenkins 서버의 설정 관리
     - 서버 설정, 플러그인(확장 프로그램), 자격 증명, 사용자 등을 관리함
  4. 생성된 모든 워크플로우의 상태 출력함

- **Jenkins 관리 (manage)**

  1. Jenkins 서버의 기본 설정을 관리
  2. 플러그인 설치, 삭제 및 목록을 관리
  3. Jenkins 자격 증명(인증 정보)를 관리함
  4. Jenkins 서버의 로그 확인 가능
     - Jenkins 사용 중 문제 생겼을 경우, 로그를 확인 해 디버그함

- **Job Dashboard**
  1. Job을 수동으로 실행(빌드)함
  2. Job의 설정을 수정
  3. Job 빌드 내역을 출력
     - 각 빌드 내역을 클릭하면 빌드 상세 결과를 출력함
       1. 빌드 중 전체 콘솔 출력을 출력
       2. 빌드 실행 시간을 출력
       3. Pileline Job의 경우 각 작업 단계의 빌드 결과를 출력

### 4. Freestyle project vs Pipeline

- Jenkins의 두 개의 기본적인 워크플로우 정의 방식

- **Freestyle**

  - GUI를 통한 워크플로우 정의 방식
  - 초보자가 접근하기에 간단하지만, 복잡한 과정을 처리하기에는 불편함
  - 작업 실행 중 Jenkins가 재시작되면 작업이 멈춤 (durability X)

- **Pipeline**

  - GUI 또는 Jenkins을 통한 워크플로우 정의 방식
  - 복잡한 코드 작성을 요구하지만, 파일을 통해 관리하므로 버전 관리가 가능
  - 작업 실행 중 Jenkins가 재시작되면 중단된 부분부터 다시 시작함 (durability O)

- **실습**

  1. 동일한 워크플로우의 Freestyle과 Pileline을 생성

     - Freestyle
       - Build Steps → Add build step → Execute shell 3개 생성 및 명령어 작성
     - Pipeline

       - Pipeline → Definition : Pipeline script → Script 작성

       ```groovy
       pipeline {
           agent any

           stages {
               stage('Hello') {
                   steps {
                       echo 'Hello World'
                   }
               }
               stage('Sleep') {
                   steps{
                       sleep 30
                   }
               }
               stage('End'){
                   steps{
                       echo "end"
                   }
               }
           }
       }
       ```

  2. 두 Job을 동시에 빌드, Jenkins 재시작

     ```bash
     # jenkins 재실행 명령어
     sudo systemctl restart jenkins
     ```

     - Jenkins를 Docker 컨테이너로 실행했을 경우
       ```bash
       docker restart jenkins-container
       ```

  3. Pipeline Job은 중단된 부분부터 다시 시작, Freestyle은 다시 시작하지 않음

---

## <mark color="#fbc956">Git SCM(Source Cod Management)</mark>

### 1. Git SCM

- Git 저장소와의 통합을 지원하는 Jenkins 기초 플러그인
- Git에 대한 여러 형태(아이디/비밀번호, Token 등)의 인증 및 저장 기능을 지원함
- GitHub 원격 저장소의 이벤트에 반응해 워크플로우를 트리거할 수 있는 기능을 지원함

### 2. GitHub 자격 증명서(Credentials)

- Jenkins 서버에서 GitHub 원격 저장소와의 통신을 위한 인증 증명서

1. **GitHub Personal access tokens 생성**

   - GitHub → 프로필 사진 → Settings → Developer settings → Personal access tokens → Fine-grained tokens → Generate new token

   1. Token name (토큰 별칭)
   2. Expiration (만료 기간) : 필요한 만큼 설정
   3. Generate Token

   - **토큰 보관**
     - 토큰은 잃어버리면 다시 생성해야하기 때문에 파일 형태로 저장 해놓음
     - 민감한 데이터이기 때문에 외부로 노출하지 않도록 함

   GitHub → 프로필 사진 클릭 → Settings → Developer settings → Personal access tokens → Fine-grained tokens → Generate new token

2. **Jenkins 자격 증명 생성**

   - Jenkins 관리 → Credentials → Domains(global) → Add Credentials

   1. username : GitHub 사용자 이름
   2. Password : `GitHub 인증 토큰`
   3. ID : 자격 증명 식별자 (별칭)
   4. Description : 자격 증명 설명

### 3. Pipeline script from SCM

- GitHub 원격 저장소와 Jenkins Job을 연동해 Push 이벤트에 반응하여 워크플로우가 실행되게 함

1. **저장소 Webhook 설정**

   - GitHub 원격 저장소 → Settings → Webhooks → Add webhook

   1. Payload URL : `http://{인스턴스 Public IP}:8080/github-webhook/`
   2. Content type : `application/json`
   3. Add webhook

2. **파이프라인 생성**

   - Enter an item name : 파이프라인 이름
   - Select an Item type : `Pipeline`
   - `OK`

3. **파이프라인 설정**

   - **아래 항목 체크 및 작성**

     - ✅ **`GitHub project`**
       - GitHub 저장소 주소 작성
       - 자동화와 연관은 없지만, Jenkins 웹 페이지와 GitHub 원격 저장소를 연결하는 역할 함
     - ✅ **`GitHub hook trigger for GITScm polling`**
       - 위 설정에 의해 GtiHub Webhook을 통해 Jenkins Job이 트리거(실행) 됨

   - Pipeline Definition : ✅`Pipeline script from SCM`
   - 아래 항목 선택 및 작성
     - SCM : ✅ Git
     - Repository URL : GitHub 저장소 주소 작성
     - Credentials : `목차 2-2` 에서 생성한 GitHub 자격 증명서
     - Branch Specifier : 트리거 브랜치
       - `*` : 모든 브랜치
       - `main` : main 브랜치
       - `deploy/*` : 특정 패턴(deploy) 브랜치

### 4. Pipeline script vs Pipeline script from SCM

- **Pipeline script**

  - Jenkins 웹 페이지에서 직접 파이프라인을 정의하는 방식
  - 장점 : 웹 페이지에서 직접 바로 수정할 수 있음
  - 단점 : 스크립트 변경 이력(버전)을 관리하기 어려움
  - 초기에 간단한 CI / CD 스크립트를 테스트하는데 용이

- **Pipeline script from SCM**
  - 파이프라인 스크립트를 파일(Jenkinsfile)로 정의하는 방식
  - 장점 : Git(SCM)을 통해 버전 관리 가능, 공유 쉬움
    - 연결된 저장소의 이벤트에 반응해 트리거 됨
  - 단점 : Jenkins와 SCM의 통합 설정이 필요함
  - 초기 테스트에서 벗어나 애플리케이션 CI / CD 구축에 용이

---

## <mark color="#fbc956">Jenkinsfile</mark>

### 1. Jenkinsfile

- Jenkins 파이프라인을 정의하는 스크립트 파일
- 파일 형태이기 때문에 SCM(git)을 통해 버전 관리 가능
- 선언형(Declarative)과 명령형(Scripted) 두 가지 작성 방식이 있음

### 2. Declarative vs Scripted

- **Declarative**

  - 코드의 구조를 강제하지만 간단함
  - 직관적이라 초보자가 접근하기 쉬움
  - 일반적인 CI / CD 과정을 충분히 구현할 수 있음

- **Scripted**
  - 코드의 구조가 유연하지만 코드가 복잡해 초보자가 접근하기 어려움
  - 분기 처리 등 더 복잡한 로직을 구현할 때 활용

### 3. Jenkinsfile 구조

- `Jenkinsfile`

  ```groovy
  pipeline {
      agent any

      stages {
          stage('Build') {
              steps {
  	              echo '애플리케이션 빌드'
              }
          }

          stage('Test'){
  		        steps{
  				        echo '애플리케이션 테스트'
  		        }
          }
      }

      post {
  		    always {
  				    echo '항상 실행된다.'
  		    }

  		    success {
  				    echo '성공 시 실행된다.'
  		    }

  		    failure {
  				    echo '실패 시 실행된다.'
  		    }
      }
  }
  ```

  - `Pipeline`
    - 스크립트가 declarative 방식이란 것을 나타내는 최상위 블록
  - `agent`
    - 파이프라인이 실행될 환경(Node)을 정의
    - `any` : 작업이 가능한 Node를 찾아 작업을 실행
    - `label '이름'` : 특정 Node에서 작업을 실행
    - `docker { image ' 이미지 이름' }` : 특정 Docker 이미지의 컨테이너에서 작업을 실행
  - `environment`
    - 파이프라인 내에서 사용할 변수 정의
    - 정적 값을 정의하거나 `key = 'value'`
    - Jenkins Credentials에서 값을 참조해올 수 있음
      - `API_KEY = credentials('api_key')`
  - `stages & stage`
    - 파이프라인의 단계를 구분
    - 하나의 stages 블록에 여러 개의 stage 블록 정의 가능
  - `step`
    - 각 단계에서 실행할 작업을 정의
    - 실질적으로 실행될 명령어들을 정의하는 블록
    - 하나의 stage 블록에 하나의 steps 블록만 정의 가능
      - 하지만, 하나의 steps 블록 내에서 여러 개의 작업 저으의 가능
  - `post`
    - 파이프라인 실행 후 후속 작업 정의, 아래와 같은 블록이 있음
    - 파이프라인 실행 결과를 협업 툴에 알림을 전송할 때 활용
    - `always` : 항상 실행
    - `success` : 파이프리인 성공 시 실행
    - `failure` : 파이프라인 실패 시 실행
    - `aborted` : 사용자가 파이프라인을 중단 시 실행
    - `changed` : 파이프라인 상태가 이전 상태와 달라졌을 때 실행

---

## <mark color="#fbc956">실습</mark>

### 1. git 프로젝트

1. 로컬 git 프로젝트를 생성하고, 원격 저장소를 생성 및 연결함
2. 프로젝트 최상위 폴더에 `jenkinsfile` 생성 및 내용 작성 → Commit & Push 함

   ```groovy
   pipeline {
       agent any
       stages {
           stage('Practice') {
               steps {
                   echo 'Jenkins 연습하기'
               }
           }
       }
   }
   ```

### 2. Jenkins Credentials

1. GitHub Personal access token(Fine-grained tokens)을 생성
2. Jenkins 서버에서 GitHub 인증 자격 증명서를 생성

### 3. Jenkins Pipeline

1. Push 이벤트에 대한 GitHub 원격 저장소 Webhook을 설정
2. `Pipeline script from SCM` 기반 파이프라인을 구성
3. 파이프라인이 정상적으로 빌드되는지 확인함

### 4. Push 테스트

1. `jenkinsfile` 을 수정, 다시 Commit & Push 함

   ```bash
   pipeline {
       agent any
       stages {
           stage('Practice') {
               steps {
                   echo 'Push 테스트'
               }
           }
       }
   }
   ```

2. 파이프라인이 Push 이벤트 Webhook에 의해 트리거 되는지 확인

---

### ☀️ 오늘의 배움

- **Jenkins**
  - CI/CD 도구
  - 서버 설치 및 관리까지 다 할 수 있음 (온 프레미스)
- **GitHub Action**

  - GitHub에 종속됨

- **Pipeline**

  - **pipeline script** : 웹 UI 활용해 관리
    - groovy 사용
  - **pipeline script from SCM** : 파일 활용해 소스 코드 관리, 깃허브를 통해 우리 소스 코드를 관리
    - 깃허브의 소스 코드 가져옴 (checkout)
    - 깃허브와 젠킨스의 통합 지원
    - 깃허브의 인증 정보를 저장하는 기능 지원
    - GitHub 원격 저장소의 이벤트(push)에 의해 pipeline을 실행

- **GitHub 자격 증명서(Credentials)**
  - `Fine-grained tokens` : 권한의 종류가 아래와 다름
  - `Tokens(classic)`

> **Jenkins 배포 자동화**
>
> ![image.png](/Sesac/assets/day71_1.png)
>
> ![image.png](/Sesac/assets/day71_2.png)

1. 준비
   - **GitHub 자격 증명서**
     - Jenkins 서버에서 GitHub 원격 저장소와 통신 위한 인증서 등록
     1. GitHub Personal access token 생성
     2. Jenkins 자격 증명 생성
   - **GitHub Webhook 설정**
     - GitHub 원격 저장소와 Jenkins Job을 연동해 Push 이벤트에 반응해 워크플로우 실행
     1. 저장소 Webhook 설정
     2. 파이프라인 생성 및 설정
2. **checkout**
   - pipeline과 깃허브 레포지토리를 연결했기 때문에 자동으로 소스코드를 가져옴 (checkout)
3. 프로젝트 내 **`Jenkinsfile`** 연결

   - Pipeline의 Configuration → Script path에서 파일명 변경 가능
   - `Jenkinsfile`

     ```groovy
     // 스크립트가 declarative 방식임을 나타내는 최상위 블록
     pipeline {
     		// 파이프라인이 실행될 환경 정의
     		// any : 작업이 가능한 Node를 찾아 작업 실행
         agent any

     		// 파이프라인의 단계를 구분
         stages {
             stage('Build') {
     		        // 각 단계에서 실행할 작업 정의
                 steps {
     	              echo '애플리케이션 빌드'
                 }
             }

             stage('Test'){
     		        steps{
     				        echo '애플리케이션 테스트'
     		        }
             }
         }

         // 파이프라인 실행 후 후속 작업 정의
         post {
     		    always {
     				    echo '항상 실행된다.'
     		    }

     		    success {
     				    echo '성공 시 실행된다.'
     		    }

     		    failure {
     				    echo '실패 시 실행된다.'
     		    }
         }
     }
     ```

   - **원격 서버 파이프라인의 프로젝트 폴더 확인 가능**

     ```bash
     # 원격 서버에서 Jenkins 서버로 진입
     docker exec -it jenkins-container bash

     # 파이프라인 내 진입
     cd /var/jenkins_home/workspace

     # 프로젝트 폴더 확인 가능
     ls
     ```

4. **환경 변수 파일 생성 및 업로드**
   - Jenkins Credentials 활용해 환경 변수 파일을 업로
5. **파이프라인 내부로 환경 변수 파일 복사**

   - `Jenkinsfile` 수정 → push

     ```groovy
     pipeline {
         agent any

         stages {
             stage('Copy Environment Variable File') {
                 steps {
     	              script {
                       // withCredentials : Credentials 서비스를 활용하겠다
                       // file : secret file을 불러오겠다
                       // credentialsId : 불러올 file의 식별 ID
                       // variable : 블록 내부에서 사용할 변수명
                       withCredentials([file(credentialsId: 'env-file', variable: 'env_file')]) {
                         // Jenkins 서비스 내 .env 파일을 파이프라인 프로젝트 내부로 복사
                         sh 'cp $env_file .env'

                         // 파일 권한 설정
                         // 소유자 : 읽기 + 쓰기
                         // 그 외 : 읽기 권한
                         // 권한) 읽기(4) 쓰기(2) 실행(1)
                         sh 'chmod 644 .env'
                       }
                     }
                 }
             }
         }

         post {
     		    always {
     				    echo '항상 실행된다.'
     		    }

     		    success {
     				    echo '성공 시 실행된다.'
     		    }

     		    failure {
     				    echo '실패 시 실행된다.'
     		    }
         }
     }
     ```

     - 원격 서버의 파이프라인에 `.env` 파일 복사되었는지 확인
       ```bash
       ls -al
       ```
     - 컨테이너 내리기
       ```bash
       docker compose -f docker-compose-actions-cache.yml down
       ```

6. **이미지 빌드 및 컨테이너 실행**

   - `Jenkinsfile` 수정 → push

     ```groovy
     pipeline {
         agent any

         stages {
             stage('Copy Environment Variable File') {
                 steps {
     	              script {
                       // withCredentials : Credentials 서비스를 활용하겠다
                       // file : secret file을 불러오겠다
                       // credentialsId : 불러올 file의 식별 ID
                       // variable : 블록 내부에서 사용할 변수명
                       withCredentials([file(credentialsId: 'env-file', variable: 'env_file')]) {
                         // Jenkins 서비스 내 .env 파일을 파이프라인 프로젝트 내부로 복사
                         sh 'cp $env_file .env'

                         // 파일 권한 설정
                         // 소유자 : 읽기 + 쓰기
                         // 그 외 : 읽기 권한
                         // 권한) 읽기(4) 쓰기(2) 실행(1)
                         sh 'chmod 644 .env'
                       }
                     }
                 }
             }

             stage('Docker Image Build & Container Run') {
               steps {
                 script {
                   sh 'docker compose build'
                   sh 'docker compose down'
                   sh 'docker compose up -d'
                 }
               }
             }
         }

         post {
     		    always {
     				    echo '항상 실행된다.'
     		    }

     		    success {
     				    echo '성공 시 실행된다.'
     		    }

     		    failure {
     				    echo '실패 시 실행된다.'
     		    }
         }
     }
     ```

- Jenkins는 GitHub Actions과 달리 캐시가 삭제되지 않아 따로 지정해두지 않아도 된다

> **GitHub Actions와 Jenkins 비교**

- **GitHub Actions**
  - Checkout Action 필수
  - 인스턴스와 별개의 서비스 (환경)
  - 이미지 전달을 위해 Docker Hub 활용
  - SSH 연결을 통해 인스턴스 제어
  - 깃허브에 종속된 서비스 → 자격 증명 과정 X
  - 깃허브에 종속된 서비스 → 별도의 Webhook을 통한 이벤트 트리거 불필요
- **Jenkins**
  - SCM 플러그인 처리
  - 인스턴스에 설치된 서비스
  - 직접 이미지 빌드 및 컨테이너 실행
  - 직접 인스턴스 제어 (Docker 명령어 실행)
  - 깃허브와 별개의 서비스 → 자격 증명 필요 O
  - 깃허브와 별개의 서비스 → 별도의 Webhook을 통해 이벤트 트리거 필요
