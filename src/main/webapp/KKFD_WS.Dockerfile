FROM node:16-alpine3.12

MAINTAINER Tae ho kim <sjabber91@gmail.com>

RUN mkdir -p html

WORKDIR ./html

COPY ./resource .

RUN npm install

CMD ["node", "server.js"]
