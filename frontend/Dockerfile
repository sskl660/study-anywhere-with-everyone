# Build Setting
FROM node:14.17.4-alpine as build-stage
MAINTAINER Taehyun Kim <sskl600@naver.com>

ENV DEBIAN_FRONTEND=noninteractive

WORKDIR /usr/src/app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

EXPOSE 8080
CMD ["npm", "run", "serve"]

# Usage
# Move to folder included Dockerfile

# docker build -t "name" .

# docker run -p "serverport":"exposeport" --name "name" -d "imageName"


