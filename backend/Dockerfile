FROM adoptopenjdk/openjdk11:ubi
MAINTAINER Taehyun Kim <sskl600@naver.com>

# Build ENV (스프링 파일이 생성되는 곳)
COPY . .
RUN ./gradlew clean
RUN ./gradlew build
COPY ./build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
