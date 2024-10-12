## <mark color="#fbc956">GUI vs CLI</mark>

### **GUI와 CLI**

- **GUI (Graphic User Interface)** : 그래픽을 통해 사용자와 컴퓨터가 상호 작용하는 방식
- **CLI (Command Line Interface)** : 터미널을 통해 사용자와 컴퓨터가 상호 작용하는 방식

> 💡**Interface(인터페이스)**
>
> 원래 서로 다른 개체끼리 맞닿아 있는 면
> 사용자와 컴퓨터가 서로 소통하는 접점

### CLI 장점

- 효율성
- 원격 액세스
- 문제 해결

---

## <mark color="#fbc956">경로</mark>

### 루트, 홈 디렉토리

- **루트 디렉토리 (Root Directory, `/`)**
  - 모든 파일과 폴더를 담고 있는 최상위 폴더
- **홈 디렉토리 (Home Directory, `~`)**
  - `Tilde(틸드)` 라 부르며, 현재 로그인 된 사용자의 홈 폴더

> 💡 **폴더 vs 디렉토리**
>
> 폴더와 디렉토리는 거의 같은 의미로 사용
> 세부적으로, 윈도우 탐색기에서 특수 폴더들(ex. 네트워크 환경, 내 컴퓨터 등)은 폴더이지만 디렉토리는 아님, 폴더가 디렉토리보다 넓은 개념

### 절대 경로와 상대 경로

- **절대 경로** : /(root) 디렉토리부터 경로 추적
  - 윈도우 바탕 화면의 절대 경로 `C:/Users/kyle/Desktop`
- **상대 경로** : 현재 작업하고 있는 디렉토리 기준으로 상대적 위치 작성
  - 이중점(..) 표기법 이용해 상위 디렉토리 표시
  - `./` : 현재 작업 중인 디렉토리
  - `../` : 현재 작업 중인 폴더의 상위 디렉토리

---

## <mark color="#fbc956">터미널 명령어</mark>

- **파일 생성 : `touch {파일명}`**
  - 띄어쓰기로 구분해 여러 파일을 한꺼번에 생성 가능
  - 숨김 파일 생성 : `.` 을 파일 명 앞에 붙임
  ```bash
  $ touch text.txt
  ```
- **폴더 생성 (make directory) : `mkdir {폴더명}`**
  - 띄어쓰기로 구분해 여러 폴더 한꺼번에 생성 가능
  - 폴더 이름 사이에 공백을 넣고 싶다면 따옴표로 묶어 입력
  ```bash
  $ mkdir folder
  $ mkdir 'make folder'
  ```
- **현재 디렉토리의 폴더/파일 목록 조회(list segments) : `ls`**

  - **옵션**
  - `-a` : all 옵션, 숨김 파일까지 모두 보여줌
  - `-l` : long 옵션, 용량 및 수정 날짜 등 파일 정보를 자세히 보여줌

  ```bash
  # 기본 사용
  $ ls

  # all 옵션
  $ ls -a

  # all, long 옵션 함께 적용
  $ ls -a -l

  # 여러 옵션 축약 가능
  $ ls -al
  ```

- **이동, 이름 변경(move) : `mv`**

  - 다른 폴더로 이동 시 작성한 폴더 있어야 함, 없을 경우 이름 변경

  ```bash
  # 폴더 이동
  $ mv text.txt folder

  # 파일명 변경
  $ mv text1.txt text2.txt
  ```

- **디렉토리 이동(change directory) : `cd {폴더명}`**
  - `cd ~` : 홈 디렉토리로 이동 (= `cd`)
  - `cd ..` : 부모 디렉토리로 이동 (위로 가기)
  - `cd -` : 바로 전 디렉토리로 이동 (뒤로 가기)
  ```bash
  # 현재 작업중인 디렉토리의 folder 폴더로 이동
  $ cd folder
  ```
- **파일 삭제(remove) : `rm {파일명}`**

  - GUI와 달리 휴지통 이동 X, 바로 완전 삭제
  - `rm -r` : recursive 옵션, 폴더 지울 때 사용
  - `*(asteriskm wildcard)` 사용해 `rm *.txt` 입력 시 txt 파일 전체 지움

  ```bash
  # 파일 삭제
  $ rm test.txt

  # 폴더 삭제
  $ rm -r folder
  ```

- **폴더/파일 여는 기능 : `start/open {폴더/파일명}`**

  - Windows에서 start, Mac에서는 open 사용

  ```bash
  # Windows
  $ start test.txt
  # 현재 디렉토리 오픈
  $ start .

  # Mac
  $ open test.txt
  ```

- **단축키**
  - `위, 아래 방향키` : 과거 작성 명령어 조회
  - `tab` : 폴더/파일 이름 자동 완성
  - `ctrl + a` : 커서 맨 앞으로 이동
  - `ctrl + e` : 커서 맨 뒤로 이동
  - `ctrl + w` : 커서 맨 앞 단어 삭제
  - `ctrl + l` : 터미널 화면 청소 (스크롤 올리면 과거 내역 조회 가능)
  - `ctrl + insert` : 복사
  - `shift + insert` : 붙여넣기
