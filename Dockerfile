FROM adoptopenjdk:11-jre-hotspot

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} paloupload-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/paloupload-0.0.1-SNAPSHOT.jar"]