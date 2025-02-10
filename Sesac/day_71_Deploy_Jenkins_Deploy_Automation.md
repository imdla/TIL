## <mark color="#fbc956">Jenkins 배포 자동화 프로세스</mark>

### 1. GitHub 저장소와 Jenkins 파이프라인 연결

- 저장소와 파이프라인을 생성하고, 연결함
- 푸시 이벤트에 의해 작업이 실행되는지 테스트

### 2. 환경 변수 파일 생성

- `docker-compose.yml` 파일을 활용해 배포를 진행하기 때문에 환경 변수 파일이 필요함
- Jenkins Credentials을 활용해 배포에 활용할 환경 변수 파일을 원격 서버에 생성

### 3. 컨테이너 생성 & 실행

- `docker-compose.yml` 파일을 활용해 컨테이너를 생성하고 실행함

---

## <mark color="#fbc956">Jenkins 환경 변수</mark>

### 1. 환경 변수 파일

- Jenkins Credentials을 활용해 환경 변수 파일 업로드함
- Jenkins 관리 → Credentials → Domain(global) → Add Credentials
- Kind : `Secret file`
- File : 환경 변수 파일

  ```bash
  # .env
  # 백엔드 환경 변수
  DATABASE_PORT=3306 # 데이터베이스 포트
  DATABASE_NAME=demo # 데이터베이스 이름
  DATABASE_USERNAME=root # 데이터베이스 접속 Username
  DATABASE_PASSWORD=1q2w3e4r! # 데이터베이스 root 계정 비밀번호

  # 데이터베이스 환경 변수
  MYSQL_DATABASE=demo # 데이터베이서 이름
  MYSQL_ROOT_PASSWORD=1q2w3e4r! # 데이터베이스 접속 비밀번호

  # 프론트엔드 환경 변수
  VITE_API_URL=/api
  ```

- ID : `env-file`
  - 파이프라인(jenkinsfile) 내부에서 식별자로 사용

---

## <mark color="#fbc956">파이프라인</mark>

### 1. 전체 파이프라인

- **전체 파이프라인**

  ```groovy
  pipeline {
    agent any

    stages {
      stage('Build Start') {
        steps {
          script {
            withCredentials([string(credentialsId: 'discord-webhook', variable: 'discord_webhook')]) {
              discordSend description: """
              Jenkins Build Start
              """,
              link: env.BUILD_URL,
              title: "${env.JOB_NAME} : ${currentBuild.displayName} 시작",
              webhookURL: "$discord_webhook"
              }
          }
        }
      }
      stage('Load Environment Variables') {
        steps {
          script {
            withCredentials([file(credentialsId: 'env-file', variable: 'env_file')]) {
              sh 'cp $env_file .env'
              sh 'chmod 644 .env'
            }
          }
        }
      }
      stage('Run Docker Compose') {
        steps {
          script {
            sh 'docker-compose down'
            sh 'DOCKER_BUILDKIT=1 COMPOSE_DOCKER_CLI_BUILD=1 docker-compose up -d --build'
          }
        }
      }
      stage('Clean Docker Image') {
        steps {
          script {
            // 빌드 후 사용하지 않는 Docker 이미지 삭제
            sh 'docker image prune -f'
          }
        }
      }
    }
    post {
          success {
              withCredentials([string(credentialsId: 'discord-webhook', variable: 'discord_webhook')]) {
                          discordSend description: """
                          제목 : ${currentBuild.displayName}
                          결과 : ${currentBuild.currentResult}
                          실행 시간 : ${currentBuild.duration / 1000}s
                          """,
                          link: env.BUILD_URL, result: currentBuild.currentResult,
                          title: "${env.JOB_NAME} : ${currentBuild.displayName} 성공",
                          webhookURL: "$discord_webhook"
              }
          }
          failure {
              withCredentials([string(credentialsId: 'discord-webhook', variable: 'discord_webhook')]) {
                          discordSend description: """
                          제목 : ${currentBuild.displayName}
                          결과 : ${currentBuild.currentResult}
                          실행 시간 : ${currentBuild.duration / 1000}s
                          """,
                          link: env.BUILD_URL, result: currentBuild.currentResult,
                          title: "${env.JOB_NAME} : ${currentBuild.displayName} 실패",
                          webhookURL: "$discord_webhook"
              }
          }
      }
  }
  ```

### 2. 환경 변수 파일 생성

- **환경 변수 파일**

  ```groovy
  pipeline {
    agent any

    stages {
      stage('Load Environment Variables') {
        steps {
          script {
            // Jenkins Credentials에서 Secret File 가져오기
            // credentialsId : credentials 생성 당시 작성한 ID
            // variable : 스크립트 내부에서 사용할 변수 이름
            // 환경 변수 파일 로드
            withCredentials([file(credentialsId: 'env-file', variable: 'env_file')]) {
              // 환경 변수 파일을 작업 폴더로 복사
              sh 'cp $env_file .env'

              // 환경 변수 파일 권한 설정 (읽기/쓰기)
              sh 'chmod 644 .env'

            }
          }
        }
      }
  }
  ```

  - **환경 변수 파일 불러옴**

    ```groovy
    withCredentials([file(credentialsId: ..., variable: ...)]) {
    	// ...
    }
    ```

  - **환경 변수 파일을 작업 폴더로 복사함**

    ```groovy
    withCredentials([file(credentialsId: ..., variable: ...)]) {
    	sh 'cp $env_file .env'
    }
    ```

  - **환경 변수 파일의 권한을 변경**
    - 소유자는 읽기(4) / 쓰기(2)
    - 그룹 및 기타 사용자는 읽기(4)
    ```groovy
    sh 'chmod 644 .env'
    ```

### 3. Docker 컨테이너 실행

```groovy
pipeline {
  agent any

  stages {
    // 도커 컴포즈 실행
    stage('Run Docker Compose') {
      steps {
        script {
	        // Docker 이미지 빌드
	        sh 'docker compose build'

          // 기존 Docker 컨테이너 중지
          sh 'docker compose down'

          // Docker 컨테이너 실행
          sh 'docker compose up -d'
        }
      }
    }
  }
}
```

---

## <mark color="#fbc956">Discord 알림 메시지</mark>

- 빌드 시작 전 후 알림 메시지를 전송해 빌드 시작과 결과를 알림

1. `Discord Notifier` 플러그인 설치
2. 디스코드 Webhook 생성
3. Jenkins에 Webhook 환경 변수 등록
   - Jenkins 관리 → Credentials → Domains(global) → Add Credentials
   - Kind : `Secret text`
   - Secret : 디스코드 Webhook
   - ID : `discord-webhook` , 파이프라인 내부 식별자

- 추가 스크립트 작성

  ```groovy
  pipeline {
    agent any

    stages {
      stage('Build Start') {
        steps {
          script {
            // Jenkins Credentials에서 Secret Text 가져오기
            // credentialsId : credentials 생성 당시 작성한
            // variable : 스크립트 내부에서 사용할 변수 이름
            withCredentials([string(credentialsId: 'discord-webhook', variable: 'discord_webhook')]) {
                  // 디스코드 알림 메세지 작성
                  // description : 메세지 설명문
                  // link : Jenkins BUILD 주소
                  // title : 메세지 제목
                  // webhookURL : 메세지 전송 URL
                  discordSend description: """
                  Jenkins Build Start
                  """,
                  link: env.BUILD_URL,
                  title: "${env.JOB_NAME} : ${currentBuild.displayName} 시작",
                  webhookURL: "$discord_webhook"
              }
          }
        }
      }
      // ... 생략
    }

    post {
          success {
              withCredentials([string(credentialsId: 'discord-webhook', variable: 'discord_webhook')]) {
                          discordSend description: """
                          제목 : ${currentBuild.displayName}
                          결과 : ${currentBuild.currentResult}
                          실행 시간 : ${currentBuild.duration / 1000}s
                          """,
                          link: env.BUILD_URL, result: currentBuild.currentResult,
                          title: "${env.JOB_NAME} : ${currentBuild.displayName} 성공",
                          webhookURL: "$discord_webhook"
              }
          }
          failure {
              withCredentials([string(credentialsId: 'discord-webhook', variable: 'discord_webhook')]) {
                          discordSend description: """
                          제목 : ${currentBuild.displayName}
                          결과 : ${currentBuild.currentResult}
                          실행 시간 : ${currentBuild.duration / 1000}s
                          """,
                          link: env.BUILD_URL, result: currentBuild.currentResult,
                          title: "${env.JOB_NAME} : ${currentBuild.displayName} 실패",
                          webhookURL: "$discord_webhook"
              }
          }
      }
  }
  ```
