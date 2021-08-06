# Build Setting
FROM node:14.17.4-alpine as build-stage
MAINTAINER Taehyun Kim <sskl600@naver.com>

WORKDIR /app
ADD . .
RUN yarn install
RUN yarn run build

# production stage
FROM nginx:stable-alpine as production-stage
COPY  ./nginx/nginx.conf /etc/nginx/conf.d/default.conf

COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 8080
CMD ["nginx", "-g", "daemon off;"]

# Avoiding user interaction with tzdata
# ENV DEBIAN_FRONTEND=noninteractive

# RUN npm install -g http-server

# WORKDIR /app
# WORKDIR /usr/src/app
# # COPY package*.json ./
# COPY . .
# RUN npm install
# # COPY . .
# RUN npm run build

# EXPOSE 8080
# # CMD ["yarn", "serve", "--port", "3000"]
# CMD [ "npm", "run", "serve" ]

# Usage
# Move to folder included Dockerfile

# docker build -t "name" .

# docker run -p "serverport":"exposeport" --name "name" -d "imageName"


