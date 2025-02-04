> React 애플리케이션을 빌드래 정적 파일(HTML, CSS, JavaScript)로 변환,
> 이를 Nginx를 사용해 배포

## <mark color="#fbc956">React 빌드</mark>

### 1. 프로덕션 빌드

- `npm run dev`

  - 개발 서버
  - React 프로젝트를 실행하는 방법
  - 실제 서비스 운영은 불가능

- **특징**

  - 개발 서버는 개발에 초점을 맞춘 서버로 운영에 적합한 성능 제공하지 않음
  - 개발 서버는 보안을 고려한 서버가 아님
  - 개발 서버는 동시에 많은 요청을 처리할 수 없음
  - 개발 서버는 컴퓨터 리소스를 비효율적으로 사용함

- **빌드**

  - 서비스 운영 위해 프로젝트를 묶어(번들링) 정적 파일(HTML, CSS, JavaScript)로 변환
  - 정적 파일을 Nginx와 같은 웹 서버로 제공 해야함
    - 또는 Next.js를 사용해 자체 서버를 함께 실행 필요
  - 빌드 결과 폴더와 파일 : `/dist/.`

- **번들러(Bundler)**
  - 여러 개의 HTML, CSS, JS 파일을 하나 또는 더 적은 파일로 묶는 도구
    - 사용하지 않는 코드를 제거해 파일 크기 줄임
    - 필요한 파일이 줄어들어 HTTP 요청 수가 줄어듦
    - 일부 최신 문법을 구 브라우저에서도 작동할 수 있는 문법으로 변환

### 2. Gzip 빌드

- 기존 정적 파일을 Gzip 압축 알고리즘으로 압축하는 빌드 방식
  - **Gzip 압축 알고리즘**
    - HTTP 표준 프로토콜에서 정의된 압축 형식
    - 대부분의 서버와 클라이언트가 Gzip 파일을 지원함
    - 다른 압축 파일은 응답으로 반환할 수 없지만 Gzip 압축 파일은 응답으로 반환 가능
- 파일을 압축해 용량 작음, 그 결과 서버에 파일을 응답할 때 더 적은 리소스 소모
- 크기가 너무 작은 프로젝트의 경우 오히려 Gzip 압축이 비효울적임

> **Gzip 빌드 준비 & 명령어**

- Gzip 플러그인 설치
  ```bash
  npm install vite-plugin-compression --save-dev
  ```
- Vite 설정 파일 `vite.config.js` 수정

  - 이전

  ```jsx
  import { defineConfig } from "vite";
  import react from "@vitejs/plugin-react";

  export default defineConfig({
    plugins: [react()],
  });
  ```

  - 이후

  ```jsx
  import { defineConfig } from "vite";
  import react from "@vite/plugin-compression";

  // Gzip 모듈 불러오기
  import compression from "vite-plugin-compression";

  export default defineConfig({
  	plugins: [
  		react(),
  		// compression() 추가
  		compression({
  			algorithm: "gzip", // Gzip 알고리즘 사용
  			ext: ".gz", // 생성되는 파일의 확장자
  			threshold: 10240, // 압축 최소 크기(10KB 이상만 압축)
  		});
  	]
  });
  ```

---

## <mark color="#fbc956">Nginx</mark>

### 1. Nginx

- HTTP 요청 처리, 정적 파일 서빙, 리버스 프록시, 로드 밸런서를 수행하는 고성능 경량 웹 서버 소프트웨어

- 컨테이너 생성과 실행
  ```jsx
  docker run -p 80:80 --name nginx-container -d nginx
  ```
- 컨테이너 내부 터미널 접속
  ```jsx
  docker exec -it nginx-container bash
  ```

### 2. 웹 서버(Web Server)

- HTTP 요청에 대해 HTML, CSS, JS 이미지, 비디오 Gzip 압축 파일과 같은 정적 콘텐츠를 클라이언트(웹 브라우저)에게 응답으로 반환할 수 있는 웹 서버 역할 함

### 3. 리버스 프록시 서버 (Reverse Proxy Server)

- 클라이언트 요청을 서버로 대신 전달, 응답을 클라이언트에게 반환하는 역할
- 서버의 IP를 숨기고, 로드 밸런싱 통해 트래픽 부하 줄임

### 4. 설정 파일 `nginx.conf`

- 웹 서버(Nginx)가 클라이언트의 요청을 어떻게 처리할지에 대해 정의하는 파일
- 설정 파일은 여러 개의 블록이 계층 구조로 구성됨

```coffeescript
worker_processes 1;

events {
	worker_connections 1024;
}

http {
	# MIME 유형(Multipurposr Internet Mail Extensions Type)
	# MIME 유형 : 서버와 클라이언트 간에 데이터가 전송될 때 데이터의 유형을 명확하게 정의
	# 데이터 확장자 html, htm, shml -> MIME 유형 text/html
	# 데이터 확장자 css -> MIME 유형 test/css

	# 로그 포맷 및 파일 경로 설정
	log_format main '$remote_addr - $remote_user [$time_local] "$request" '
                   '$status $body_bytes_sent "$http_referer" '
                   '"$http_user_agent" "$http_x_forwarded_for"';

  access_log /var/log/nginx/access.log main;
  error_log/var/log/nginx/error.log;

  # 서버 - 클라이언트 TCP 연결 유지 시간
  keepalive_timeout 60;

  # Gzip 설정
  # 압축된 Gzip 파일(.gz)을 서빙
  gzip_static on;

  # Accept-Encoding 헤더를 응답에 추가
  gzip_vary on;

  server {
	  # HTTP 요청 수신 포트
		listen 80;

		# 처리할 도메인 이름
		server_name localhost;

		# 정적 파일을 서빙할 폴더 경로
		root /usr/share/nginx/html;

		# 기본 경로(/) 요청에 대한 서빙 파일
		index index.html;

		# 기본 경로(/) 요청 처리
		location / {
			# 클라이언트가 요청한 파일이 있으면 해당 파일 서빙
			# 클라이언트가 요청한 파일이 없으면 index.html 서빙
			# SPA의 라우팅 문제를 해결하기 위해 필수 설정
			try_files $uri /index.html;
		}
  }
}
```

- **주요 블록**
  - **global**
    - Nginx 전체에 적용되는 전역 설정
    - 워커 프로세스 수(worker_processes) 등을 정의
  - **http**
    - HTTP 요청과 관련된 설정
    - MIME 유형, Gzip 등을 정의
    - server 블록을 포함
  - **server**
    - http 블록 안에서 특정 도메인 또는 IP에 대한 요청 처리 설정
    - 포트, 도메인 이름, 정적 파일 경로 등을 정의
    - location 블록을 포함
  - **location**
    - server 블록 안에서 특정 경로(URL)에 대한 요청 처리 설정
    - 정적 파일 서빙, 리버스 프록시 서버, 리다이렉트, 응답 헤더 등을 정의

### 5. nginx 명령어

- `nginx`

  - Nginx 서버 시작

- `nginx -s stop`

  - Nginx 서버 중지

- `nginx -s reload`

  - Nginx 설정 파일 다시 불러오기

- `nginx -t`
  - Nginx 설정 파일 테스트

---

## <mark color="#fbc956">React 빌드 & Nginx 실행</mark>

### 1. 프로젝트 빌드

1. 실습 프로젝트 파일
2. 패키지 설치
3. Gzip 빌드 설정 `vite.config.js`

   ```jsx
   import { defineConfig } from "vite";
   import react from "@vitejs/plugin-react";
   import compression from "vite-plugin-compression";

   export default defineConfig({
   	plugins: [
   		react(),
   		compression({
   			algorithm: "gzip",
   			ext: ".gz",
   			threshold: 10240,
   		});
   	]
   });
   ```

4. 프로젝트 빌드

   ```bash
   npm run build
   ```

### 2. Nginx 실행

1. nginx 도커 컨테이너 생성 & 실행

   ```bash
   docker run -p 80:80 --name nginx-container -d nginx
   ```

2. 빌드 결과 `dist` 폴더를 컨테이너 내부로 복사

   - nginx는 정적 콘텐츠를 자동으로 리로드

   ```bash
   # docker cp [호스트 경로] [컨테이너 이름]:[컨테이너 경로]
   docker cp dist/. nginx-container"/usr/share/nginx/html
   ```

3. nginx 설정 파일 `nginx.conf` 작성

   ```bash
   # nginx.conf
   worker_processes 1;

   events {
   	worker_connextions 1024;
   }

   http {
   	include         mim.types;
   	default_type    application/json;
   	log_format main '$remote_addr - $remote_user [$time_local] "$request" '
                       '$status $body_bytes_sent "$http_referer" '
                       '"$http_user_agent" "$http_x_forwarded_for"';

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
     }
   }
   ```

4. nginx 설정 파일 컨테이너 내부로 복사

   ```bash
   # docker cp [호스트 경로] [컨테이너 이름]:[컨테이너 경로]
   docker cp nginx.cng nginx-container:/etc/nginx/nginx.conf
   ```

5. nginx 설정 파일 리로드

   ```bash
   docker exec -it nginx-container nginx -s reload
   ```
