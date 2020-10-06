FROM openjdk:11-jdk-slim
ADD target/gestor-0.0.1-SNAPSHOT.jar .
EXPOSE 8000
CMD java -jar gestor-0.0.1-SNAPSHOT.jar