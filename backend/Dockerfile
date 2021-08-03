FROM adoptopenjdk/openjdk11:ubi AS Builder
MAINTAINER Taehyun Kim <sskl600@naver.com>

COPY --from=builder ./build/libs/SSAZIPtest-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
