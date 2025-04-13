# 필요프로그램 설치
FROM openjdk:17-jdk-alpine
# 파일 복사
WORKDIR /app
COPY gradle gradle
COPY src src
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
# 빌드
RUN chmod +x gradlew
RUN ./gradlew bootJar


ENTRYPOINT ["java", "-jar", "build/libs/demo-0.0.1-SNAPSHOT.jar"]

# docker build -t demospring:v1.0 .
# docker run -d --name my-spring -p 8080:8080 demospring:v1.0