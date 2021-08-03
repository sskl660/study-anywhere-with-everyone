FROM adoptopenjdk/openjdk11:ubi
MAINTAINER Taehyun Kim <sskl600@naver.com>

# Build ENV (스프링 파일이 생성되는 곳)
COPY . .
RUN ./gradlew build
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]


