FROM maven:3.9.11-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY pom.xml .
RUN mvn -B dependency:go-offline
COPY src src
RUN mvn -B package -DskipTests

FROM eclipse-temurin:21-jre-alpine
RUN addgroup -S futagend && adduser -S futagend -G futagend
WORKDIR /app
COPY --from=build /workspace/target/futagend-api-*.jar app.jar
USER futagend
EXPOSE 8080
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75", "-jar", "/app/app.jar"]

