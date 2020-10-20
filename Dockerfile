FROM openjdk:11.0.8-jre-slim
COPY build/SpringMVCGestor.jar .
EXPOSE 8080
CMD java -jar SpringMVCGestor.jar