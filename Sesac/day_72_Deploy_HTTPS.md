## <mark color="#fbc956">HTTPS (HTTP Secure)</mark>

### 1. 보안이 강화된 HTTP

- 기존 **HTTP에 SSL / TLS 암호화**를 추가한 통신 프로토콜
- 서버와 브라우저 간의 통신을 암호화해 보안을 강화함
  - **SSL / TLS 암호화**
    - SSL은 최초의 보안 HTTP 프로토콜이였지만 보안 취약점이 발견되어 TLS로 대체됨
    - TLS는 SSL을 개선한 프로토콜, 현재도 지속적으로 업데이트 중임
- **443 포트**를 통해 통신하며, HTTPS 통신을 위해서 서버는 **CA(Certificate Authority, 신뢰할 수 있는 기관)**에서 발급받은 `SSL(Secure Sockets Layer) 인증서` 를 보유해야 함
  - SSL 인증서에는 도메인 이름, 발급자, 조직 정보, 유효 기간, 서버 공개 키 등과 같은 서버의 신원 정보가 포함되어 있음
  - SSL 인증서를 발급받기 위해서는 서버는 **도메인 이름**이 필수임

### 2. 대칭키 / 비대칭키 암호화

- SSL / TLS 동작 과정 이해를 위해서는 대칭키 암호화와 비대칭키 암호화에 대해 알고 있어야 함

> **비대칭키 암호화**

- 암호화와 복호화는 서로 다른 키(공개 키 / 개인 키)를 활용해서 하는 암호화 방식
- 암호화는 공개 키로, 복호화는 개인 키로 함
  - ex. SSH 프로토콜에서 공개 키와 개인 키를 활용해 연결함
- 서버의 신뢰성을 검증할 때 활용되며, 신뢰 검증 과정을 SSL / TLS 핸드셰이크(handshake)라고 함

> **대칭키 암호화**

- 동일한 키를 활용해 데이터를 암호화 및 복호화하는 암호화 방식
- 비대칭키에 비해 높은 성능을 보여주지만, 동일한 키로 암호화 / 복호화를 하기 때문에 **대칭키 관리에 주의**해야 함
- 핸드셰이크 이후 서버와 브라우저 간 데이터를 전달할 때 활용함

### 3. SSL / TLS 동작 순서

> 서버 동작
>
> 브라우저 동작

1. **브라우저가 서버에 접속하면 정보를 보냄**
   - TLS 버전
   - 암호화 방식
   - 브라우저가 생성한 무작위 난수
2. **서버가 브라우저에 정보를 보냄**
   - CA의 개인 키로 서명된 `SSL 인증서`
   - 서버의 공개 키(Public Key) - 비대칭키 암호화에 활용
   - 서버가 생성한 무작위 난수
3. **서버의 SSL 인증서의 신뢰성을 검증**
   - CA의 공개키로 서버가 보낸 SSL 인증서를 검증
   - CA의 정보와 공개 키는 브라우저에 저장되어 있음
4. **Pre-Master Secret을 생성해 서버에 전송**
   - 브라우저와 서버가 생성한 난수를 활용해 Pre-Master Secret을 생성
   - 서버의 공개키로 Pre-Master Secret을 암호화해서 전송
5. **Pre-Master Secret을 복호화**
   - 브라우저가 서버의 공개 키로 암호화한 Pre-Master Secret을 서버의 개인키로 복호화
   - 브라우저와 서버는 각각 생성한 난수 2개와 Pre-Master Secret을 공유하게 됨
6. **대칭키를 생성 / 대칭키를 생성**
   - 난수 2개와 Pre-Master Secret로 대칭키를 생성
   - 이후 모든 통신은 대칭키를 활용한 **대칭키 암호화 방식**으로 진행

> **정리**

- 브라우저와 서버 간 신뢰성 검증(Handshake)을 할 때는 비대칭키 암호화 방식을 활용
- 신뢰성 검증 후 통신은 대칭키 암호화 방식을 활용, 활용되는 대칭키 암호화는 핸드셰이크(신뢰 검증) 과정에서 생성됨
- 핸드셰이크 과정에서는 두 세트의 공개키 / 개인키가 활용됨
  - CA의 공개키 / 개인키
    - 공개키 : SSL 인증서의 검증
    - 개인키 : SSL 인증서의 디지털 서명
  - 서버의 공개키 / 개인키
    - 공개키 : 브라우저의 Pre-Master Secret 암호화
    - 개인키 : 서버의 Pre-Master Secret 복호화

---

## <mark color="#fbc956">인증서 발급과 Nginx 인증서 설정</mark>

> **인증서 발급은 도메인 이름 필요**

- CA에서 인증서 발급을 위해 도메인 이름이 필요

### 1. 도메인 발급

- 도메인 발급은 생략, `sslip.io` 서비스 활용

> **sslip.io**

- 무료 공유 와일드카드 DNS 서비스
- 도메인 구입 없이 Public IPv4를 도메인으로 생성해줌
- `[Public IPv4].sslip.io` 주소를 통해 임시로 일반적인 도메인처럼 사용 가능
- SSL 인증서 발급에는 도메인 이름이 필수인대 대신 활용 가능

### 2. 인증서 발급

- 무료 SSL 인증서 발급 기관인 **Let’s Encrypt**을 활용

1. 원격 서버 - 패키지 설치

   - certbot - Let’s Encrypt 인증서 발급 도구

   ```bash
   sudo apt update
   sudo apt install -y certbot
   ```

2. 원격 서버 - 개인 키와 인증서 생성

   ```bash
   sudo certbot certonly --standalone -d [Public IPv4].sslip.io
   ```

3. 원격 서버 - Let’s Encrypt 인증서 발급 과정

   1. Enter email address - `이메일 작성`
   2. Please read the Terms of Service at - 서비스 약관 동의 여부 → `Y`
   3. Would you be willing, … - 뉴스레터 구독 여부 → `N`
   4. 인증서 생성 확인

      ```bash
      sudo ls /etc/letsencrypt/live/도메인/
      ```

      - 아래 파일 확인
        - /etc/letsencrypt/live/`도메인`/privkey.pem
        - /etc/letsencrypt/live/`도메인`/fullchain.pem

4. 원격 서버 - 인증서 파일 권한 변경

   ```bash
   sudo chmod 644 /etc/letsencrypt/live/도메인/privkey.pem
   sudo chmod 644 /etc/letsencrypt/live/도메인/fullchain.pem
   ```

### 3. Nginx 인증서 설정

- `docker-compose.yml` 와 `nginx.conf` 파일을 수장하여 생성한 인증서를 Nginx 서비스에 제공
- `docker-compose.yml`

  - 수정 전

    ```yaml
    services:
      db:
        container_name: db-container
        image: mysql:8.0
        volumes:
          - db-volume:/var/lib/mysql
        env_file:
          - .env
        networks:
          - db-connect
        healthcheck:
          test: ["CMD", "mysqladmin", "ping", "-h", "localhost"]
          interval: 10s
          timeout: 5s
          retries: 3
          start_period: 30s

      backend:
        container_name: backend-container
        build:
          context: ./backend
        image: nodecrewbeemo/backend:latest
        env_file:
          - .env
        environment:
          DATABASE_HOST: db
        networks:
          - db-connect
          - backend-connect
        depends_on:
          db:
            condition: service_healthy

      frontend:
        container_name: frontend-container
        build:
          context: ./frontend
          args:
            VITE_API_URL: ${VITE_API_URL}
        image: nodecrewbeemo/frontend:latest
        ports:
          - "80:80"
        networks:
          - backend-connect
        depends_on:
          - backend
          - db

    volumes:
      db-volume:

    networks:
      db-connect:
      backend-connect:
    ```

  - 수정 후

    - `environment` 블록 활용해 도메인 이름 관리
    - HTTPS 통신을 위한 `443` 포트를 매핑
    - `volumes` 블록으로 SSL 인증서를 컨테이너와 공유

    ```yaml
    services:
      db:
        container_name: db-container
        image: mysql:8.0
        volumes:
          - db-volume:/var/lib/mysql
        env_file:
          - .env
        networks:
          - db-connect
        healthcheck:
          test: ["CMD", "mysqladmin", "ping", "-h", "localhost"]
          interval: 10s
          timeout: 5s
          retries: 3
          start_period: 30s

      backend:
        container_name: backend-container
        build:
          context: ./backend
        image: nodecrewbeemo/backend:latest
        env_file:
          - .env
        environment:
          DATABASE_HOST: db
        networks:
          - db-connect
          - backend-connect
        depends_on:
          db:
            condition: service_healthy

      frontend:
        container_name: frontend-container
        build:
          context: ./frontend
          args:
            VITE_API_URL: ${VITE_API_URL}
        image: nodecrewbeemo/frontend:latest
        environment:
          - DOMAIN=도메인
        ports:
          - "80:80"
          - "443:443"
        networks:
          - backend-connect
    		volumes:
    		  - /etc/letsencrypt/live/${DOMAIN}/fullchain.pem:/etc/letsencrypt/live/${DOMAIN}/fullchain.pem:ro
    		  - /etc/letsencrypt/live/${DOMAIN}/privkey.pem:/etc/letsencrypt/live/${DOMAIN}/privkey.pem:ro
        depends_on:
          - backend
          - db

    volumes:
      db-volume:

    networks:
      db-connect:
      backend-connect:
    ```

- `nginx.conf`

  - 수정 전

    ```groovy
    worker_processes 1;

    events {
    worker_connections 1024;
    }

    http {
    include       mime.types;
    default_type  application/json;

    log_format main '$remote_addr - $remote_user [$time_local] "$request" '
    '$status $body_bytes_sent "$http_referer" '
    '"$http_user_agent" "$http_x_forwarded_for" '
    'to "$upstream_addr"';

    access_log /var/log/nginx/access.log main;
    error_log /var/log/nginx/error.log debug;

    keepalive_timeout 60;

    gzip_static on;
    gzip_vary on;

    server {
    listen 80;
    server_name localhost;

    root /usr/share/nginx/html;
    index index.html;

    location / {
    index index.html;
    try_files $uri /index.html;
    }

    location /api/ {
    proxy_pass http://backend-container:8080;
    proxy_http_version 1.1;
    proxy_set_header Host $host;
    proxy_set_header X-Real-IP $remote_addr;
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
    }
    }
    ```

  - 수정 후

    ```groovy
    worker_processes 1;

    events {
        worker_connections 1024;
    }

    http {
        include       mime.types;
        default_type  application/json;

        log_format main '$remote_addr - $remote_user [$time_local] "$request" '
                    '$status $body_bytes_sent "$http_referer" '
                    '"$http_user_agent" "$http_x_forwarded_for" '
                    'to "$upstream_addr"';

        access_log /var/log/nginx/access.log main;
        error_log /var/log/nginx/error.log debug;

        gzip_static on;
        gzip_vary on;

        # HTTPS 서버 설정
        server {
            listen 443 ssl http2;
            server_name 도메인;

            # SSL 설정
            ssl_certificate /etc/letsencrypt/live/도메인/fullchain.pem;
            ssl_certificate_key /etc/letsencrypt/live/도메인/privkey.pem;

            # SSL 보안 설정
            ssl_protocols TLSv1.2 TLSv1.3;
            ssl_ciphers ECDHE-ECDSA-AES128-GCM-SHA256:ECDHE-RSA-AES128-GCM-SHA256:ECDHE-ECDSA-AES256-GCM-SHA384:ECDHE-RSA-AES256-GCM-SHA384;
            ssl_prefer_server_ciphers on;

            root /usr/share/nginx/html;
            index index.html;

            location / {
                try_files $uri /index.html;
            }

            location /api/ {
                proxy_pass http://backend-container:8080;
                proxy_set_header Host $host;
            }
        }

        # HTTP를 HTTPS로 리다이렉트
        server {
            listen 80;
            server_name 도메인;

            location / {
                return 301 https://$host$request_uri;
            }
        }
    }
    ```

---

## <mark color="#fbc956">Nginx 설정 파일</mark>

### 1. 설정 파일(/\*.conf) 모듈화

- Nginx는 기본적으로 2개의 설정 파일을 가진 상태로 설치됨

  - **`*/etc/nginx/nginx.conf*`**

    - Nginx 메인 설정 파일, 글로벌 설정을 관리
    - `include` 구문으로 여러 개의 서버(server) 블록 설정 파일을 관리
    - `conf.d` 폴더에 존재하는 서버 블록 설정 파일(`default.conf`)을 읽어 서버를 관리
    - 원본 파일

      ```groovy
      user  nginx;
      worker_processes  auto;

      error_log  /var/log/nginx/error.log notice;
      pid        /var/run/nginx.pid;

      events {
          worker_connections  1024;
      }

      http {
          include       /etc/nginx/mime.types;
          default_type  application/octet-stream;

          log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                            '$status $body_bytes_sent "$http_referer" '
                            '"$http_user_agent" "$http_x_forwarded_for"';

          access_log  /var/log/nginx/access.log  main;

          sendfile        on;
          #tcp_nopush     on;

          keepalive_timeout  65;

          #gzip  on;

          include /etc/nginx/conf.d/*.conf;
      }
      ```

  - **`*/etc/nginx/conf.d/default.conf*`**

    - 기본적으로 작성된 모듈 서버 설정 파일
    - 80 포트에 대한 처리가 작성되어 있음
    - 원본 파일

      ```groovy
      server {
          listen       80;
          listen  [::]:80;
          server_name  localhost;

          #access_log  /var/log/nginx/host.access.log  main;

          location / {
              root   /usr/share/nginx/html;
              index  index.html index.htm;
          }

          #error_page  404              /404.html;

          # redirect server error pages to the static page /50x.html
          #
          error_page   500 502 503 504  /50x.html;
          location = /50x.html {
              root   /usr/share/nginx/html;
          }

          # proxy the PHP scripts to Apache listening on 127.0.0.1:80
          #
          #location ~ \.php$ {
          #    proxy_pass   http://127.0.0.1;
          #}

          # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
          #
          #location ~ \.php$ {
          #    root           html;
          #    fastcgi_pass   127.0.0.1:9000;
          #    fastcgi_index  index.php;
          #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
          #    include        fastcgi_params;
          #}

          # deny access to .htaccess files, if Apache's document root
          # concurs with nginx's one
          #
          #location ~ /\.ht {
          #    deny  all;
          #}
      }
      ```

- **`*conf.d`\* 폴더**
  - Nginx 설정 파일 `모듈화` 의 핵심 폴더
  - 하나의 Nginx로 여러 개의 서버 설정이 필요할 때, 파일을 분리해 작성한 후 저장하는 폴더
  - 메인 설정 파일 `*nginx.conf*` 은 이 폴더에서 모듈화된 서버 설정 파일 `*.conf*` 을 불러와 설정을 적용함

### 2. Nginx 환경 변수 주입 - /\*.template 파일

- **기본적으로 Nginx는 환경 변수를 지원하지 않음**
- 하지만 Docker Nginx 버전 1.19 이미지부터 환경 변수를 추출(`extract environment variables before nginx starts`) 하는 기능이 존재함
- Docker Nginx는 `/etc/nginx/templates` 폴더의 `.template` 파일들을 읽어서 환경 변수를 주입한 후, `/etc/nginx/conf.d` 폴더에 `.conf` 파일로 생성

  - 단, 메인 설정 파일 `nginx.conf` 파일에는 적용할 수 없는 기능임

- 예시
  - 환경 변수
    ```groovy
    DOMAIN=naver.com
    ```
  - `/etc/nginx/templates/default.conf.template`
    ```groovy
    server {
    listen 443;
    server_name ${DOMAIN};
    }
    ```
  - 환경 변수가 주입된 `/etc/nginx/conf.d/default.conf`
    - 아래 서버 설정이 Nginx에 적용됨
    ```groovy
    server {
    listen 443;
    server_name naver.com;
    }
    ```

### 3. HTTP / HTTPS 서버 설정 파일 분리

- 기존 _`/etc/nginx/nginx.conf`_ 에서 HTTP 서버 설정을 분리해서 2개의 파일로 나누고, 추가로 HTTPS 서버 설정 파일을 작성

  - **`*frontend/nginx.conf*`**

    - HTTP(80) 서버 설정을 삭제
    - `*include /etc/nginx/conf.d/*.conf;*` 구문 통해 모듈 설정 파일 불러옴

    ```groovy
    worker_processes 1;

    events {
    worker_connections 1024;
    }

    http {
    include       mime.types;
    default_type  application/json;

    log_format main '$remote_addr - $remote_user [$time_local] "$request" '
    '$status $body_bytes_sent "$http_referer" '
    '"$http_user_agent" "$http_x_forwarded_for" '
    'to "$upstream_addr"';

    access_log /var/log/nginx/access.log main;
    error_log /var/log/nginx/error.log debug;

    keepalive_timeout 60;

    gzip_static on;
    gzip_vary on;

    include /etc/nginx/conf.d/*.conf;
    }
    ```

- **`*frontend/templates/default.conf.template*`**

  - 기존 HTTP(80) 서버 설정을 작성
  - 도메인을 환경 변수로 처리하기 위해 `templates` 폴더 활용

  ```groovy
  # HTTP를 HTTPS로 리다이렉트
  server {
  listen 80;
  server_name ${DOMAIN};

  location / {
  return 301 https://$host$request_uri;
  }
  }
  ```

- **`*frontend/templates/https.conf.template*`**

  - 기존 HTTPS(443) 서버 설정을 작성
  - 도메인을 환경 변수로 처리하기 위해 `templates` 폴더 활용

  ```groovy
  # HTTPS 서버 설정
  server {
  listen 443 ssl http2;
  server_name ${DOMAIN};

  ssl_certificate /etc/letsencrypt/live/${DOMAIN}/fullchain.pem;
  ssl_certificate_key /etc/letsencrypt/live/${DOMAIN}/privkey.pem;

  ssl_protocols TLSv1.2 TLSv1.3;
  ssl_prefer_server_ciphers on;
  ssl_ciphers ECDHE-ECDSA-AES128-GCM-SHA256:ECDHE-RSA-AES128-GCM-SHA256:ECDHE-ECDSA-AES256-GCM-SHA384:ECDHE-RSA-AES256-GCM-SHA384;

  root /usr/share/nginx/html;
  index index.html;

  location / {
  try_files $uri /index.html;
  }

  location /api/ {
  proxy_pass ${API_URL};
  proxy_http_version 1.1;
  proxy_set_header Host $host;
  proxy_set_header X-Real-IP $remote_addr;
  proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
  proxy_set_header X-Forwarded-Proto $scheme;
  }
  }
  ```

- **`*frontend/Dockerfile*`**

  - `templates` 폴더 파일 복사(COPY) 구문 추가함

  ```jsx
  FROM node:23-alpine AS build
  ARG VITE_API_URL
  ENV VITE_API_URL=$VITE_API_URL
  WORKDIR /app
  COPY package*.json ./
  RUN npm install
  COPY . .
  RUN npm run build

  FROM nginx:mainline-alpine-slim
  COPY --from=build /app/dist /usr/share/nginx/html
  COPY nginx.conf /etc/nginx/nginx.conf
  COPY ./templates /etc/nginx/templates
  EXPOSE 80 443
  CMD ["nginx", "-g", "daemon off;"]
  ```

- `docker-compose.yml`

  - 환경 변수 파일 불러옴

  ```groovy
  services:
  	frontend:
  		# ...
  		env_file:
  		- .env
  ```

- 환경 변수 파일
  - DOMAIN / API_URL 값을 추가
  ```yaml
  DOMAIN=도메인명
  API_URL=http://backend-container:8080
  ```
  - GitHub Actions을 사용한다면 워크플로우에 환경 변수 작성 스크립트를 추가함
  ```yaml
  echo "DOMAIN=${{ secrets.DOMAIN }}" >> .env
  echo "API_URL=${{ secrets.API_URL }}" >> .env
  ```

---

### ☀️ 오늘의 배움

> **HTTP와 HTTPS**

- **HTTP**
  - 평문 데이터 전달
    ![image.png](/Sesac/assets/day72_1.png)
- **HTTPS**

  - 암호문 데이터 전달

  ![image.png](/Sesac/assets/day72_2.png)

- **SSL / TLS**

  - **SSL**
    - 인증서 필요
    - 인증서는 CA(신뢰할 수 있는 기관)에서 발급

- **대칭키 / 비대칭키 암호화**

  - **비대칭키 암호화**
    ![image.png](/Sesac/assets/day72_3.png)
  - **대칭키 암호화**
    - 비대칭키 암호화보다 성능 좋음 (위험성이 높지만)
    - 속도가 비교적 빠름
      ![image.png](/Sesac/assets/day72_4.png)

- **SSH 연결**

  ![image.png](/Sesac/assets/day72_5.png)

- **SSL / TLS 핸드셰이크**
  - **활용되는 비대칭키 키 세트**
    - CA 비대칭키 (공개키 / 개인키)
    - 서버 비대칭키 (공개키 / 개인키)
- 비대칭키 암호화 통해 만들어진 대칭키는 보다 안전함

> **인증서 발급과 Nginx 인증서 설정**

- **도메인 발급**

  - **`[Public IPv4].sslip.io`**

- **인증서 발급**

  ```bash
  # 원격 서버 - 패키지 설치
  sudo apt update
  sudo apt install -y certbot

  # 원격 서버 - 개인 키와 인증서 생성
  sudo certbot certonly --standalone -d [Public IPv4].sslip.io

  sudo ls /etc/letsencrypt/live/

  # 인증서 생성 확인
  sudo chmod 644 /etc/letsencrypt/live/[Public IPv4].sslip.io/privkey.pem
  sudo chmod 644 /etc/letsencrypt/live/[Public IPv4].sslip.io/fullchain.pem

  sudo ls -al /etc/letsencrypt/live/[Public IPv4].sslip.io/
  ```

- **Nginx에 인증서 설정**

  - `docker-compose.yml` 수정

    ```yaml
    frontend:
      container_name: frontend-container
      build:
        context: ./frontend
        cache_to:
          - type=local,dest=/tmp/.buildx-cache/frontend,mode=max
        cache_from:
          - type=local,src=/tmp/.buildx-cache/frontend
        args:
          VITE_API_URL: ${VITE_API_URL}
      image: imdla/frontend:latest
      ports:
        - "80:80"
        - "443:443"
      networks:
        - backend-connect
      volumes:
        - /etc/letsencrypt/live/[Public IPv4].sslip.io/fullchain.pem:/etc/letsencrypt/live/13.124.186.21.sslip.io/fullchain.pem
        - /etc/letsencrypt/live/[Public IPv4].sslip.io/privkey.pem:/etc/letsencrypt/live/13.124.186.21.sslip.io/privkey.pem
      depends_on:
        - backend
        - db
    ```

  - Host의 인증서 파일을 Docker Container 공간에 연결

    ⇒ Nginx에서 Host의 인증서에 접근 가능해짐

  - 연결 후, 원격 서버에서 인증서 확인하기

    ```bash
    # 도커 컨테이너 접속
    docker exec -it frontend-container sh

    cd etc/letsencrypt/live/[Public IPv4].sslip.io/

    # 원격 서버에 인증서 확인할 수 있음
    ls
    ```

  - `nginx.conf` 수정

    ```groovy
    server {
            listen 443 ssl;
            http2 on;
            server_name 13.124.186.21.sslip.io;

            # SSL 인증서 설정
            # 인증서와 공개키 파일
            ssl_certificate /etc/letsencrypt/live/[Public IPv4].sslip.io/fullchain.pem;
            # 개인키 파일
            ssl_certificate_key /etc/letsencrypt/live/[Public IPv4].sslip.io/privkey.pem;

            # SSL 보안 설정
            ssl_protocols TLSv1.2 TLSv1.3;
            ssl_ciphers ECDHE-ECDSA-AES128-GCM-SHA256:ECDHE-RSA-AES128-GCM-SHA256:ECDHE-ECDSA-AES256-GCM-SHA384:ECDHE-RSA-AES256-GCM-SHA384;
            ssl_prefer_server_ciphers on;

            root /usr/share/nginx/html;
            index index.html;

            location / {
              index index.html;
              try_files $uri /index.html;
            }

            location /api/ {
                proxy_pass http://backend-container:8080;
                proxy_http_version 1.1;
                proxy_set_header Host $host;
                proxy_set_header X-Real-IP $remote_addr;
                proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            }
        }

        server {
            listen 80;
            server_name 13.124.186.21.sslip.io;

            # 리다이렉트
            location / {
                return 301 https://$host$request_uri;
            }
        }
    ```

> **Nginx 설정 파일**

- server 블록을 파일로 분리해야 함
- 모듈화 시킨 파일을 nginx.conf로 불러와 사용해야 함
  > 📁 conf.d
  >
  > - `default.conf` → HTTP(80) 설정
  > - `https.conf` → HTTP(443) 설정
  - nginx.conf
    ```groovy
    # 모듈화 시킨 서버 설정 파일을 메인 설정 파일로 불러오기(포함시키기)
    include /etc/nginx/conf.d/*.conf;
    ```
  - `Dockerfile`
    ```yaml
    # 모듈화된 서버 설정 파일들 컨테이너로 복사
    COPY ./conf.d /etc/nginx/conf.d
    ```
- `.template`
  ![image.png](/Sesac/assets/day72_6.png)
