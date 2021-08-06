# Build Setting
FROM node:14.17.4-alpine as build-stage
MAINTAINER Taehyun Kim <sskl600@naver.com>

# build by yarn
WORKDIR /app
ADD . .
RUN yarn install
RUN yarn run build

# production stage by nginx
FROM nginx:stable-alpine as production-stage
COPY  ./nginx/nginx.conf /etc/nginx/conf.d/default.conf

COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 8080
CMD ["nginx", "-g", "daemon off;"]

# Usage
# Move to folder included Dockerfile

# docker build -t "name" .

# docker run -p "serverport":"exposeport" --name "name" -d "imageName"


