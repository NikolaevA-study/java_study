FROM openjdk:22
ARG JAR_FILE=build/libs/save-user-info-service-0.0.1-SNAPSHOT.jar
LABEL maintainer ="save-user-info-service"
WORKDIR /save-user-info-service
VOLUME /main-app
COPY ${JAR_FILE} save-user-info-service.jar
ENTRYPOINT ["java","-jar","save-user-info-service.jar"]