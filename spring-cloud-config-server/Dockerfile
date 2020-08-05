FROM openjdk:8-jdk-alpine
ADD target/spring-cloud-config-server-1.0.jar spring-cloud-config-server-1.0.jar
ENTRYPOINT ["sh","-c","java -jar /spring-cloud-config-server-1.0.jar"]