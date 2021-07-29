# Build Setting
FROM node:lts-alpine as build-stage
MAINTAINER Taehyun Kim <sskl600@naver.com>

# Avoiding user interaction with tzdata
ENV DEBIAN_FRONTEND=noninteractive

WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# production stage
FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]

# Usage
# Move to folder included Dockerfile

# docker build -t "name" .

# docker run -p "serverport":"exposeport" --name "name" -d "imageName"

