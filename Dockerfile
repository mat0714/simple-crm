FROM maven:3.8.7-eclipse-temurin-17 AS builder

COPY pom.xml /app/
COPY src /app/src
RUN --mount=type=cache,target=/root/.m2 mvn -f /app/pom.xml clean package -DskipTests -Pprod

#Run
FROM eclipse-temurin:17.0.6_10-jre-jammy
COPY --from=builder /app/target/simple-crm-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]