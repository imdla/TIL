# 멀티 스테이지 빌드를 활용하여 이미지 크기를 최소화하는 Dockerfile

# 빌드 단계 ------------------------------------
# JDK 21 슬림 버전을 사용하여 빌드 환경 구성
# 별칭(AS) : build를 통해 다음 스테이지에서 참조 가능
FROM openjdk:21-jdk-slim AS build

# Windows에서 작성된 gradlew 파일을 Linux 환경에서 실행하기 위한 패키지 설치
RUN apt-get update && apt-get install -y dos2unix

# 모든 작업이 이루어질 컨테이너 내부 디렉토리 지정
WORKDIR /app

# 빌드에 필요한 Gradle 관련 파일들을 먼저 복사
# 소스코드가 변경되어도 의존성은 캐시된 레이어를 재사용
COPY gradle gradle
COPY build.gradle settings.gradle gradlew ./

# gradlew 파일의 줄바꿈 문자를 변환하고 실행 권한 부여
RUN dos2unix gradlew && \
    chmod +x gradlew

# 의존성을 미리 다운로드하여 캐시 레이어 생성
# --no-daemon 옵션으로 데몬 프로세스 없이 한 번만 실행
RUN ./gradlew dependencies --no-daemon

# 애플리케이션 소스 코드 복사
COPY src src

# Spring Boot 애플리케이션을 실행 가능한 단일 JAR 파일로 패키징
RUN ./gradlew bootJar --no-daemon

# ---------------------------------------------
# 실행 단계 ------------------------------------
# JRE 환경만 포함된 알파인 리눅스 기반의 가벼운 이미지 사용
FROM eclipse-temurin:21-jre-alpine

# 애플리케이션 실행을 위한 디렉토리 설정
WORKDIR /app

# 빌드 스테이지에서 생성된 JAR 파일만 복사
# 빌드 환경의 불필요한 파일들은 제외하여 이미지 크기 최소화
COPY --from=build /app/build/libs/*.jar app.jar

# 컨테이너 실행 시 접근 가능한 포트 정보 제공
EXPOSE 8080

# 컨테이너 실행 시 JAR 파일을 실행하는 명령어
CMD ["java", "-jar", "app.jar"]