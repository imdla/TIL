## <mark color="#fbc956">GitHub Actions</mark>

### 1. GitHub CI/CD

- GitHub에서 제공하는 CI/CD 도구
- Push, Pull Request 등의 이벤트에 반응해 저장소에서 작업(테스트, 실행, 빌드 등)을 실행할 수 있음
- 일련의 작업(Job) 모음을 워크플로우(Workflow)라고 지칭

### 2. 주요 이벤트

- 워크플로우를 실행하는 조건(트리거)을 이벤트(Event)라 지칭
- Push : 특정 브랜치에 Push가 발생할 경우
- Pull Request : PR 생성, 병합이 발생할 경우
- Tag : 태그를 생성 및 삭제할 경우
- Schedule : 주기적으로 실행하는 경우, corn 표현식을 활용

### 3. 주요 동작

- 코드 테스트 및 빙드 : 코드가 푸쉬될 때 자동으로 테스트 및 빌드
- 배포 자동화 : 서버, AWS, Azure, GCP 등으로 배포 파이프라인 구축
- 코드 품질 체크 : 린트, 코드 분석 도구를 통해 품질 관리
- 정기 작업 : 데이터 백업, 리포지토리 관리 등 주기적으로 실행해야 할 작업 스케줄링

---

## <mark color="#fbc956">워크플로우(Workflow)</mark>

### 1. 워크 플로우

- GitHub Actions가 실행할 자동화 작업을 정의하는 설정 파일
- git 프로젝트의 최상위 폴더에서 `.github/workflows/workflow.yml` 파일로 정의
- `workflow` 폴더 내부 여러 개의 워크플로우 파일을 정의 가능

### 2. 간단한 워크플로우

> `.github/workflows/01_hello-world.yml`

```yaml
name: Terminal Output Workflow

on:
	push:
		paths:
			- ".github/workflows/01_hello-world.yml"
		branches:
			- main

jobs:
	terminal-output:
		runs-on: ubuntu-24.04

		steps:
			- name: Checkout code
				uses: actions/checkout@v4.2.2

			- name: Print Hello World
				uses: echo "Hello World!"
```

- `name:` : 워크플로우 이름 정의
- `on:` : 워크플로우를 자동으로 실행할 이벤트를 정의
  - `paths:`
    - 특정 파일이 변경되었을 때만 워크플로우를 실행
    - 워크플로우 실습 위해 작성한 설정으로, 일반적으로는 필요하지 않음
  - `push:` : push 이벤트에 의해 트리거됨
    - `branches:`
      - 실행 조건 브랜치를 설정
      - 여러 개를 설정할 때는 아래와 같이 작성
        ```yaml
        branches:
        	- main
        	- develop
        	- feature/*
        ```
- `jobs:`
  - 실행될 작업을 정의
  - job은 워크플로우의 작업 단위 의미
  - terminal-output: 작업의 이름
  - `run-on:` : 실행 환경 정의 (도커의 이미지와 유사)
  - `steps:`
    - 실제 실행될 작업 내부 단계
    - name : 단계의 이름
    - `uses:`
      - 외부 Action 또는 정의되어 있는 코드를 호출 (GitHub Actions의 함수)
      - actions/checkout@v4.2.2 : 저장소의 코드를 체크아웃(다운로드) 함

---

## <mark color="#fbc956">GitHub Secrets</mark>

### 1. GitHub의 환경 변수

- GitHub Actions 워크플로우에서 민감한 데이터의 환경 변수가 필요할 때 사용하는 서비스
- 저장소 단위로 Secrets을 관리하며 조직 단위로도 관리 가능
- GitHub에는 별도로 Environments 서비스가 존재하는데, Secrets와 다른 역할 수행

- `Secrets`
  - 일반적으로 생각라는 환경 변수 의미
  - 민감한 데이터를 저장
  - 워크플로우 내에서 안전하게 사용하기 위해 활용
- `Environments`
  - 환경 별로 Secrets을 관리하고 싶을 때 활용하는 서비스
  - 동일한 Secret Key에 대해 환경별로 다른 value가 필요할 때 활용

### 2. Secrets 설정과 활용

- 저장소 Secrets
  1. GitHub 저장소 이동
  2. Settings 탭 클릭
  3. Secrets and variables > Actions 선택
  4. New repository secret 버튼 클릭
  5. Name과 Value 입력 후 Add secret 클릭
- 조직 Secrets

  1. GitHub 조직 페이지 이동
  2. Settings > Secrets and variables > Actions 클릭
  3. New organization secret 클릭 후 설정

- 워크플로우 내부 Secrets 활용 `${{ secrets.Name }}`
  ```yaml
  run: echo ${{ secrets.MY_SECRET }}
  ```

### 3. Secrets 활용 워크플로우

- 저장소에 등록한 Secrets을 읽어 출력
- 워크플로우에서 사용할 저장소 secrets 생성
  ```yaml
  MESSAGE=hello world
  ```

> `.github/workflows/02_hello-world.yml`

```yaml
name: Terminal Ouput Secrets Workflow

on:
	push:
		paths:
			- ".github/workflow/02_secrets.yml"
		branches:
			- main
jobs:
	secret-output:
		runs-on: ubuntu-24.04

		steps:
			- name: Checkout code
				uses: actions/checkout@v4.2.2

			- name: Print MESSAGE
				run: echo "my secret message is ${{ secrets.MESSAGE }}"
```

- 워크플로우 실행 결과
  - secret의 value는 마스킹(\*) 처리되어 출력
  ```yaml
  echo "my secret message is ***"
  shell: /usr/bin/bash -e {0}
  my scret message is ***
  ```
