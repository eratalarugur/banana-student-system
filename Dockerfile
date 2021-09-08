#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#ARG JAR_FILE
#ADD build/libs/banana-student-system-0.0.1.jar /app.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM java:8-jdk-alpine
COPY ./target/banana-student-system-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch banana-student-system-0.0.1-SNAPSHOT.jar'
ARG JAR_FILE=target/banana-student-system-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","banana-student-system-0.0.1-SNAPSHOT.jar"]