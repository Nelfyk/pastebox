FROM openjdk:17-jdk-alpine
MAINTAINER Burduzhan Ruslan
COPY target/pastebox-0.0.1-SNAPSHOT.jar pastebox.jar
# start
ENTRYPOINT ["java","-jar","/pastebox.jar"]

#docker build --tag=pastebox:latest .
#docker run -p 7777:8080 pastebox:latest

#docker ps