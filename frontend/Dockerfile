#FROM ubuntu:18.04
FROM node:lts-alpine
MAINTAINER Taehyun Kim <sskl600@naver.com>

# Avoiding user interaction with tzdata
ENV DEBIAN_FRONTEND=noninteractive

# RUN apt-get update
# RUN apt-get install -y node.js
# RUN apt-get install -y npm
RUN npm install -g http-server

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build

EXPOSE 9000
CMD [ "http-server", "dist" ]

