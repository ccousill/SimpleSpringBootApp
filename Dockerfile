FROM alpine
RUN apk update
RUN apk add openjdk11 maven
COPY . /app
WORKDIR /app
RUN mvn package -DskipTests
RUN java -jar target/springboot-backend-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java", "-jar", "target/springboot-backend-0.0.1-SNAPSHOT.jar"]
