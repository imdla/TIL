# 베이스 이미지 지정
FROM node:lts

# 컨테이너 내부 작업 폴더를 설정
# 없으면 알아서 생성함
WORKDIR /app

# 이미지를 생성할 때 실행되는 문법
# [호스트] 파일 또는 폴더를 [컨테이너]로 복사
# [호스트 파일] [컨테이너 경로]
COPY ./package.json ./

# 명령 실행
# 이미지가 생성할 때 실행
RUN npm install
RUN npm run build

# 컨테이너 실행할 때 실행되는 문법
# CMD ["node", "app.js"]