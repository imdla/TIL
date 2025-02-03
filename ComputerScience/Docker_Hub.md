> 💡**한 줄 요약**
>
> 도커 허브는 도커 공식 원격 이미지 저장소로 이미지를 다운 받고, 개인 이미지도 업로드할 수 있다.

### 1. 🤔 왜 사용하는가

- **Docker Hub**

  - 도커 공식 원격 이미지 저장소
  - 도커 개발사가 생성한 공식 이미지 및 사용자 개인 이미지 업로드 가능

- **Docker Image 명령어**

  - `docker pull [이미지 이름]:[태그]` : Docker Hub에서 이미지 다운로드
  - `docker images` : 로컬 이미지 목록 출력
  - `docker rmi [이미지 이름]:[태그]` : 이미지 삭제
  - `docker rmi -f [이미지 이름]:[태그]` : 이미지 기반 컨테이너가 있어도 이미지를 강제 삭제
  - `docker image prune` : 태그 없고, 이미지 기반 컨테이너 없는 이미지 모두 삭제
  - `docker image prune -a` : 이미지 기반 컨테이너 없는 이미지 모두 삭제
  - `docker inspect [이미지 이름]:[태그]` : 이미지의 상세 정보 출력
  - `docker tag [이미지 이름]:[태그] [새 이미지 이름]:[새 태그]` : 이미지의 이름과 태그 추가

- **Docker 이미지 Docker Hub에 업로드 및 공유**
  1. `docker login`
     - Docker Hub에 로그인
     - `docker login -u [사용자명]` : Docker Hub에 사용자명 / 패스워드 통해 로그인
     - `docker logout` : 로그아웃
  2. `docker push [사용자명]/[이미지 이름]:[태그]`
     - 로그인한 Docker Hub 계정으로 이미지 업로드
     - Repositories 페이지에서 업로드한 도커 이미지 확인 가능
