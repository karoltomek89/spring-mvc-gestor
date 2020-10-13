FROM openjdk:11.0.8-jre-slim
COPY build/gestor-0.0.1.jar .
EXPOSE 8080
CMD java -jar gestor-0.0.1.jar