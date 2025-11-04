# Build
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -e -U -B dependency:resolve
COPY src ./src
RUN mvn -q -e -B package

# Run
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/java-metric-1.0.0-jar-with-dependencies.jar app.jar
ENV PGW_URL=pushgateway:9091 JOB_NAME=java-demo INSTANCE=java-1
ENTRYPOINT ["java","-jar","/app/app.jar"]
