FROM ubuntu:latest
RUN apt-get update && apt-get install -y dos2unix
COPY docker-entrypoint.sh /entrypoint.sh
RUN dos2unix /entrypoint.sh && apt-get --purge remove -y dos2unix && rm -rf /var/lib/apt/lists/*
ENTRYPOINT ["/entrypoint.sh"]

# Build Setting
FROM adoptopenjdk:8-jdk-hotspot AS builder
MAINTAINER Taehyun Kim <sskl600@naver.com>

# gradle Build
COPY gradlew  .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
# Web Application src copy
COPY src src
# grant privilege
RUN chmod +x ./gradlew

# create jar file
RUN ./gradlew bootJars

FROM adoptopenjdk:8-jdk-hotspot
# move builder image's file to app.jar
COPY --from=builder build/libs/*.jar app.jar

# expose container port
EXPOSE 8080
# run jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Usage
# Move to folder included Dockerfile

# docker build -t "name" .

# docker run -p "serverport":"exposeport" --name "name" -d "imageName"