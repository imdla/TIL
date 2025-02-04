# React 빌드 단계 ------------------------------------
# Node.js 알파인 리눅스 기반의 가벼운 이미지 사용
# 별칭(AS) : build를 통해 다음 스테이지에서 참조 가능
FROM node:23-alpine AS build

# 모든 작업이 이루어질 컨테이너 내부 디렉토리 지정
WORKDIR /app

# 빌드에 필요한 package.json 파일들을 먼저 복사
# 소스코드가 변경되어도 의존성은 캐시된 레이어를 재사용
COPY package*.json ./

# npm을 통해 프로젝트 의존성 설치
RUN npm install

# 애플리케이션 소스 코드 복사
COPY . .

# React 애플리케이션을 정적 파일로 빌드
RUN npm run build

# ---------------------------------------------
# Nginx 실행 단계 ------------------------------------
# Nginx 알파인 리눅스 기반의 가벼운 이미지 사용
FROM nginx:mainline-alpine-slim

# 빌드 스테이지에서 생성된 정적 파일들을 Nginx의 서비스 디렉토리로 복사
COPY --from=build /app/dist /usr/share/nginx/html

# Nginx 설정 파일 복사
COPY nginx.conf /etc/nginx/nginx.conf

# 컨테이너 실행 시 접근 가능한 포트 정보 제공
EXPOSE 80

# Nginx를 포그라운드에서 실행하는 명령어
CMD ["nginx", "-g", "daemon off;"]