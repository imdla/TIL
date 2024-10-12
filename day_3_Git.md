## <mark color="#fbc956">Git</mark>

- **Git**
  - 변경사항 및 그의 메타 데이터 저장
- **Git Repository**
  - 프로젝트의 가상 스토리지
  - 필요 시 액세스 가능한 코드 버전 저장
- **Local Repository**
  - **Working Directory** : 사용자 일반 작업 공간
  - **Staging Area** : commit 위한 파일 및 폴더 추가 장소
  - **Repository** : Staging Area에 있던 변경 사항 저장 장소
  - Git의 버전 관리
    - Working Directory → Staging Area → Repository

---

## <mark color="#fbc956">git 명령</mark>

### 1. Repository 설정

> 로컬로 생성된 레포지토리와 원격 레포지토리에서 복제된 레포지토리에 대한 workflow

- **새 레포지토리 생성**
  **: `git init`**
  - 새 Git 레포지토리 생성해 지정되지 않은 기존 프로젝트를 Git 레포지토리로 변환 (일회성 명령)
  - `.git` 하위 디렉토리 및 `main` 브랜치 생성
- **구성 및 설정**
  **: `git config --global user.email {user email}` ,**
  **`git config --global user.name {user name}`**
  - Git 설치 위한 구성 옵션 설정
  - 누가 커밋 기록 남겼는지 확인하기위해 이름과 이메일 설정
  - `git config --global -l` : 설정 확인

### 2. 변경 사항 저장

- **레포지토리 변경사항 저장**
  **: `git add {파일/폴더명}` , `git commit -m {message}`**
  - **`git add`** : 변경사항을 Working Directory → Staging Area 이동
    - add 통해 공식 기록에 commit하기 전 준비
    - `git add .` : 변경사항 전체 스테이징
  - **`git commit`** : Staging Area의 작업을 프로젝트 기록에 하나의 commit으로 저장
  - 변경 사항 발생
    → `git add {파일명}` (Staging Area에 추가)
    → `git commit -m {message}` (commit 생성)
- **변경사항 조회**
  **: `git diff`**
  - commit되지 않은 변경 사항 출력
- **Git 무시**
  **: `.gitignore`**
  - Git이 명시적으로 무시하도록 지시한 파일
  - `.gitignore` 라는 특수 파일에 입력해 Git이 관리하지 않도록 무시함
    (민감한 개인 파일, 운영체제 활용 파일, IDE나 Text Editor에서 활용 되는 파일 등)
    → 무시할 파일은 `git add` 전 `.gitignore` 에 작성
  - `.git` 폴더와 동일한 위치에 생성

### 3. 레포지토리 검사

- **Working Directory와 Staging Area 상태 조회**
  **: `git status`**
  - **상태**
    - **Untracked** : Git이 관리하지 않는 파일
    - **Tracked** : Git이 관리하는 파일
      - **Unmodified** : 최신 상태
      - **Modified** : 변경 있지만 Staging Area에 반영하지 않은 상태
      - **Staged** : Staging Area에 올라간 상태
- **프로젝트 commit 기록 조회**
  **: `git log`**
  - 프로젝트 저장한 커밋 내역(ID, 작성자, 시간, 메시지 등) 조회
  - **옵션**
    - `--oneline` : 각 commit 한 줄로 압축
    - `--graph` : 커밋 기반 그래프 표시
    - `-all` : 모든 브랜치의 내역 조회
    - `--reverse` : 커밋 내역 순서 반대로 조회
    - `-p` : 파일 변경 내용 포함
    - `-n` : n개의 내역 조회

### 4. 변경 내용 실행 취소

- **파일 수정 취소**
  **: `git restore {파일명}`**
  - Git이 버전 관리하고 있는 Working Directory 파일만 가능
- **Staging Area → Undtage로 되돌리기**
  - **로컬에 commit 없는 경우 : `git rm --cached {파일명}`**
  - **로컬에 commit 있는 경우 : `git restore --staged {파일명}`**
- **직전 커밋 수정**
  **: `git commit --amend`**
  - 수정 시 커밋 해시 값도 변경
- **특정 커밋 상태로 수정**
  **: `git reset {옵션} {커밋 ID}`**
  - `--soft` : 해당 커밋으로 돌아가 이후 commit 파일들 Staging Area로 수정 (commit 가능 상태)
  - `--mixed` (default) : 해당 커밋으로 돌아가 이후 commit 파일들 Working Directory로 수정 (Unsatge 상태)
  - `--hard` : 해당 커밋으로 돌아가 이후 commit들 Working Directory에서 삭제 (Untracked 파일은 그대로 남음)
- **커밋 취소 후 새 커밋 생성**
  **: `git revert {커밋 ID}`**
  - commit 취소하는 commit 생성

### 5. 기록 수정

- **삭제한 커밋으로 수정**
  **: `git reflog`**
  - 이전 커밋 목록 모두 출력

### 6. 공동작업 워크플로

- **로컬에 원격 레포지토리 설정**
  - **등록 : `git remote add {remote 이름} {url}`**
    - 로컬에서 Git으로 관리되는 폴더 및 하나 이상 commit 존재
    - 원격 레포지토리 url → 로컬에 추가해 로컬 브랜치에 대한 업스트림 브랜치 설정
  - **조회 : `git remote -v`**
    - 로컬 저장소에 연결된 원격 저장소 조회
  - **삭제 : `git remote rm(remove) {remote 이름}`**
    - 로컬과 원격 레포지토리의 연결을 끊음

### 7. 동기화

- **로컬 레포지토리 업로드**
  **: `git push {remote 이름} {브랜치 이름}`**
  - 로컬 레포지토리 → 원격 레포지토리로 커밋 게시
  - **옵션**
    - `-u` : 이후 `push`부터 `{remote 이름}`과 `{브랜치 이름}` 생략 가능
- **로컬 레포리지 업데이트**
  **: `git pull {remote 이름}`**
  - 원격 레포지토리의 콘텐츠 다운 → 로컬 레포지토리 업데이트해 병합
- **원격 레포지토리의 복사본 생성**
  **: `git clone {url}`**
  - 중앙 레포지토리에서 프로젝트 이미 설정된 경우
  - 원격 레포지토리 → 로컬에 복제
    - `git init` 과 `git remote add` 이미 수행됨
  - `git clone {url} {폴더명}` : 로컬에 복제하는 폴더명 변경해 저장

### 8. 브랜치

: 단일 레포지토리 내 분리된 작업 공간 만들어 독립적 작업 가능

- **브랜치 조회, 생성, 삭제**
  - 브랜치 조회 : `git branch`
  - 원격 레포지토리 브랜치 조회 : `git branch -r`
  - 새 브랜치 생성 : `git branch {브랜치명}`
  - 특정 커밋 기준 브랜치 생성 : `git branch {브랜치명} {커밋 ID}`
  - 브랜치 삭제
    - 병합된 브랜치 삭제 : `git branch -d {브랜치명}`
    - 브랜치 강제 삭제 : `git branch -D {브랜치명}`
- **브랜치 이동**
  **: `git switch {브랜치명}`**
  - 현재 브랜치 → 다른 브랜치로 HEAD 이동
  - **옵션**
    - 브랜치 생성 및 이동 : `git switch -c {브랜치명}`
    - 특정 커밋 기준 브랜치 생성 및 이동 : `git switch -c {브랜치명} {커밋 ID}`

### 9. 브랜치 병합

- **브랜치 병합**
  **: `git merge {병합할 브랜치명}`**
  - 두 브랜치를 하나로 결합, 메인 브랜치에서 진행
- **merge 종류**
  - **Fast-forward**
    - 현재 브랜치의 가리키는 커밋을 앞으로 이동시킴 (빨리감기)
  - **3-Way Merge**
    - 두 브랜치에서 다른 파일이나 같은 파일의 다른 부분 변경 시
      → 각 브랜치 커밋 두 개를 공통 조상 하나 사용해 Merge commit
  - **Merge Confilct**
    - 두 브랜치에서 같은 파일의 같은 부분 변경 시
      → Git이 어느 내용으로 사용할지 판단 X 충돌 발생
      → 사용자가 직접 내용 선택해 수동으로 해결
      → `git aad` 통해 충돌 해결 알림
      → `git commit` 실행해 병합 커밋 생성
    - **충돌 표시 방식**
      - `<<<<<<<` : 수신 브랜치
      - `=======` : 구분선
      - `>>>>>>>` : 병합 브랜치
- **vim 에디터**
  - `i` : 입력 모드
  - `esc` : 명령 모드
    - `:wq` : 저장 및 종료
      ( `w` : 저장, `quit` : 종료 )
    - `dd` : 해당 줄 삭제
    - `:q!` : 강제 종료
      ( `!` : 강제 )
