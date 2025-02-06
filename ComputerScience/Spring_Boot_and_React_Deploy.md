![image.png](/ComputerScience/assets/deploy.png)

### 1. 🤔 왜 사용하는가

- **Sping Boot 배포**

  1. 빌드 도구(gradle)를 통해 build해 `jar` 파일 생성
     - `jar` 파일 : 실행 가능한 파일, 프로젝트를 압축한 파일
     - gradle 장점 : 증분 빌드 (변경 사항 코드만 부분적 빌드)
  2. `jar` 파일 실행해 WAS 실행
     - `gradlew` : gradle을 실행하는 명령어 모음 파일

  ```bash
  # 빌드
  ./gradlew build

  # jar 파일 실행
  java -jar build/libs/backend-0.0.1-SNAPSHOT.jar
  ```

- **React 배포**

  1. 번들링 도구(Vite)를 통해 build해 `dist` 파일(정적 파일) 생성
     - Gzip 빌드 - Gzip 압축 알고리즘을 통해 작은 용량으로 작은 리소스를 소모 가능
  2. Nginx에 대한 설정 파일인 `nginx.conf` 생성
     - Nginx(웹 서버) : 클라이언트이 요청에 정적 파일 응답하는 서버
  3. Nginx 생성 및 실행 `nginx.conf` 파일과 dist 파일 Nginx로 복사
  4. Nginx 리로드

  ```bash
  # 패키지 설치
  npm install

  # 프로젝트 빌드
  npm run build

  # Nginx 생성 및 실행
  docker run -p 80:80 --name nginx-container -d nginx

  # dist 폴더 컨테이너 내부 복사
  docker cp dist/. nginx-container:/usr/share/nginx/html
  # nginx 설정 파일 컨테이버 내부 복사
  docker cp nginx.conf nginx-container:/etc/nginx/nginx.conf

  # 리로드
  docker exec -it nginx-container nginx -s reload
  ```
