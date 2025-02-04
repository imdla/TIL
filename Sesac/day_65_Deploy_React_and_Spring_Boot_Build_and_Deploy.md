## <mark color="#fbc956">프록시 서버</mark>

### 1. 포워드 프록시 (Forward Proxy)

- 클라이언트 앞에 위치해 클라이언트 대신 외부 서버에 요청을 대신 보내주는 서버
- 외부 서버는 클라이언트의 정보를 알 수 없음
- 프록시 서버가 정적 컨텐츠를 캐싱할 수 있음

### 2. 리버스 프록시 (Reverse Proxy)

- 내부 서버 앞에 위치해 내부 서버 대신 클라이언트의 요청을 받아주는 서버
- 프록시 서버가 클라이언트의 요청을 필요에 따라 여러 개의 내부 서버로 분산할 수 있음
- 클라이언트는 내부 서버 정보를 알 수 없음
- 프록시 서버가 정적 컨텐츠를 캐싱할 수 있음

### 3. 포워드 vs 리버스

- 두 서버 모두 클라이언트와 서버 중간에서 중개 역할을 하는 것은 동일
- 클라이언트와 서버 중 무엇이 숨겨지냐로 두 서버 구분할 수 있음

---

## <mark color="#fbc956">Nginx 리버스 프록시 서버</mark>

> Nginx는 클라이언트의 특정 패턴의 URL 요청을 서버 대신 받아 서버로 전달

### 1. Nginx 설정 파일

- `nginx.conf`

  ```bash
  worker_processes 1;

  events {
      worker_connections 1024;
  }

  http {
      include       mime.types;
      default_type  application/json;

      log_format main '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for" "$request_time" '
                      '"$upstream_response_time" "$upstream_addr"';

      access_log /var/log/nginx/access.log main;
      error_log /var/log/nginx/error.log;

      keepalive_timeout 60;

      gzip_static on;

      gzip_vary on;

      server {
          listen 80;

          server_name localhost;

          root /usr/share/nginx/html;

          index index.html;

          location / {
              try_files $uri /index.html;
          }

  				# localhost:80/api/~ 패턴으로 요청하면
  				# 프록시 서버가
  				# localhost:8080/api/~ 로 요청을 재전달
          location /api/ {
              proxy_pass http://localhost:8080;
              proxy_set_header Host $host;
          }
      }
  }
  ```

  - **`location /api/`**
    - 리버스 프록시 처리할 URL 패턴
    - `api/` 로 시작하는 모든 URL에 매칭
  - **`proxy_pass http://localhost:8000;`**
    - 특정 URL 패턴 요청을 해당 주소로 전달
    - ex. http://localhost:80/api/posts → http://localhost:8080/api/posts
  - **`proxy_set_header Host $host;`**
    - 서버에 요청을 전달할 때 요청 Host 헤더를 설정

> **localhost를 사용하지 않는 이유**

- 현재 Nginx를 도커 컨테이너로 실행한 상황일 경우 localhost에 접근 시 컨테이너 네트워크의 localhost로 접근함
- 호스트 PC 네트워크에 접근하기 위한 주소 `host.docker.internal` 로 통신함

### 2. 배포하기

- **mysql 컨테이너 생성**

  ```bash
  docker run -p 3307:3306 -e MYSQL_ROOT_PASSWORD=1q2w3e4r! -e MYSQL_DATABASE=demo --name mysql -d mysql:8.0
  ```

- **Spring Boot 빌드 & 배포**

  1. gradle 빌드

     ```bash
     ./gradlew build -x test
     ```

  2. `.jar` 파일 실행

     ```bash
     java -jar build/libs/backend-0.0.1-SNAPSHOT.jar
     ```

- **Vite 빌드**

  1. 패키지 설치

     ```bash
     npm install
     ```

  2. 프로젝트 빌드

     ```bash
     npm run build
     ```

- **Nginx 실행 및 설정**

  1. Nginx 실행

     ```bash
     docker run -p 80:80 --name nginx-container -d nginx
     ```

  2. `dist` 폴더 복사

     ```bash
     docker cp dist/. nginx-container:/usr/share/nginx/html
     ```

  3. `nginx.conf` 설정 파일 작성 및 복사

     ```bash
     worker_processes 1;

     events {
     	worker_connections 1024;
     }

     http {
     	include       mime.types;
     	default_type  application.json;
     	log_format main '$remote_addr - $remote_user [$time_local] "$request" '
     									'$status $body_bytes_sent "$http_referer" '
     									'"$http_user_agent" "$http_x_forwarded_for" "$request_time" '
     									'"$upstream_response_time" "$upstram_addr"';
     	access_log /var/log/nginx/access.log main;
     	error_log /var/log/nginx/error.log;
     	keepalive_timeout 60;
     	gzip_static on;
     	gzip_vary on;

     	server {
     		listen 80;

     		server_name localhost;

     		root /usr/share/nginx/html;

     		index index.html;

     		location / {
     			try_files $uri /index.html;
     		}

     		location /api/ {
     			proxy_pass http://host.docker.internal:8080;
     			proxy_set_header Host $host;
     		}
     	}
     }

     ```

     ```bash
     docker cp nginx.conf nginx-container:etc/nginx/nginx.conf
     ```

  4. Nginx 설정 다시 불러오기

     ```bash
     docker exec -it nginx-container nginx -s reload
     ```

---

### ☀️ 오늘의 배움

> **React + Spring Boot 빌드와 배포**

- **프록시 서버**

  ![image.png](/Sesac/assets/day65_4.png.png)

  - WAS는 사용자에게 노출 안되는게 좋음
  - WAS를 숨길 서버를 만듦 → 프록시 서버
  - 클라이언트의 요청을 대신 받아 WAS에 전달
    - WAS의 응답을 받아 클라이언트에 전달
  - Nginx는 프록시 서버 역할도 함

- **프록시 서버의 중개**

  ```bash
  # 로컬 호스트로 접속하게 되면
  location / {
  	try_files $uri /index.html;
  }

  # 사용자가 api로 접속하게 되면
  # localhost:80/api 요청이 들어오면
  location /api/ {
  	# 특정 URL 패턴 요청을 해당 주소로 전달
  	# localhost:8080/api 주소로 요청을 대신 전달해라 (프록시 서버)
  	proxy_pass http://host.docker.internal:8080;
  	# 서버에 요청 전달 시 요청 Host 헤더를 설정
  	proxy_set_header Host $host;
  }
  ```

- **배포하기**

  ```bash
  # mysql 컨테이너 생성 - cmd
  docker run -p 3307:3306 -e MYSQL_ROOT_PASSWORD=1q2w3e4r! -e MYSQL_DATABASE=demo --name mysql -d mysql:8.0

  # gradle 빌드 - cmd
  ./gradlew build -x test

  # jar 파일 실행 - cmd
  java -jar build/libs/backend-0.0.1-SNAPSHOT.jar

  # vite 빌드 패키지 설치- frontend
  npm install

  # 프로젝트 빌드 - frontend
  npm run build

  # Nginx 실행 - cmd
  docker run -p 80:80 --name nginx-container -d nginx

  # dist 폴더 복사 - frontend
  docker cp dist/. nginx-container:/usr/share/nginx/html

  # nginx.conf 설정 파일 작성 및 복사 - frontend
  docker cp nginx.conf nginx-container:/etc/nginx/nginx.conf

  # Nginx 설정 다시 불러오기
  docker exec -it nginx-container nginx -s reload
  ```

  - `nginx.conf` 설정 파일

  ```bash
  worker_processes 1;

  events {
      worker_connections 1024;
  }

  http {
      include       mime.types;
      default_type  application/json;
      log_format main '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for" "$request_time" '
                      '"$upstream_response_time" "$upstream_addr"';
      access_log /var/log/nginx/access.log main;
      error_log /var/log/nginx/error.log;
      keepalive_timeout 60;
      gzip_static on;
      gzip_vary on;

      server {
  		    # 포트가 80번 요청일 때
          listen 80;

          server_name localhost;

  				# 정적 파일 제공
          root /usr/share/nginx/html;

          index index.html;

          location / {
              try_files $uri /index.html;
          }

  				# 프록시 서버 역할을 하기 위한 설정
          location /api/ {
  		        # localhost:80/api/일 경우 localhost:8080/api/주소로 대체해 전달
              proxy_pass http://host.docker.internal:8080;
              proxy_set_header Host $host;
          }
      }
  }
  ```

- `host.docker.internal`
  - 프록시 서버는 도커 컨테이너 내부에 있음
  - 클라이언트와 WAS는 host에 있음

![image.png](/Sesac/assets/day65_3.png.png)
