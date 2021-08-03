FROM adoptopenjdk/openjdk11:ubi AS Builder
MAINTAINER Taehyun Kim <sskl600@naver.com>

# Build ENV (스프링 파일이 생성되는 곳)
COPY . .
RUN ./gradlew clean
RUN ./gradlew build

FROM adoptopenjdk/openjdk11:ubi
COPY --from=builder build/libs/*.jar/ app.jar
#COPY ./build/libs/*.jar /usr/src/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
