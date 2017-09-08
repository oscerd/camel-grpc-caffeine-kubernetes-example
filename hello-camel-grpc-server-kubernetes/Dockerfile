FROM openjdk:8

COPY target/camel-example-hello-grpc-server-kubernetes-0.0.1-SNAPSHOT-jar-with-dependencies.jar /app/grpc-server.jar

EXPOSE 8080 50051
ENTRYPOINT ["java", "-jar", "/app/grpc-server.jar"]
