# FROM node:latest
# WORKDIR /app
# COPY package.json .
# RUN npm install
# # RUN npm install -g typescript
# # RUN npm install -g esbuild
# COPY . .
# EXPOSE 5174

# CMD ["npm", "run", "dev"]


FROM node:latest

# 작업 폴더를 만들고 npm 설치
WORKDIR /usr/src/app
COPY package.json /usr/src/app/package.json

RUN npm install --force

# 소스를 작업폴더로 복사하고 빌드
COPY . /usr/src/app
RUN npm run build

