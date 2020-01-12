FROM node:alpine as build

WORKDIR /usr/src/app
COPY . /usr/src/app

RUN apt-get update && \
    apt-get install -y make ant openjdk-8-jdk graphviz && \
    apt-get clean

RUN ls | grep "READ"

RUN npm install -g vuepress serve

RUN mkdir vuepress-starter && cd vuepress-starter && echo '# Hello VuePress' > README.md && vuepress build

RUN mvn javadoc:javadoc
Generates javadoc under target/side/apidocs

RUN  mvn package

EXPOSE 5000

ENTRYPOINT ["serve"]
