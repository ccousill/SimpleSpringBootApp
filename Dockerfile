FROM alpine
RUN apk update
RUN apk add --no-cache openjdk11 maven
COPY . /app
WORKDIR /app
RUN mvn package -DskipTests
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/springboot-backend-0.0.1-SNAPSHOT.jar"]
