FROM openjdk:11-ea-9-jdk
MAINTAINER Aleksandr Grishin

COPY ./ /java-activemq-1c-integration-demo
WORKDIR /java-activemq-1c-integration-demo

RUN bash ./mvnw -DskipTests clean package

ENV WEB_SERVER_PORT 8080
ENV JVM_OPTS "-Xms512m -Xmx1024m"

EXPOSE $WEB_SERVER_PORT

ENTRYPOINT exec java $JVM_OPTS -jar ./target/java-activemq-1c-integration-demo-0.0.1-SNAPSHOT.jar
