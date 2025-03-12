FROM gradle:jdk21-jammy AS build

WORKDIR /app
COPY . .

RUN gradle bootJar

FROM eclipse-temurin:21-jdk-jammy AS runtime

WORKDIR /app
COPY --from=build /app/build/libs/user-service-0.0.1-SNAPSHOT.jar user-service.jar

ENTRYPOINT ["java", "-jar", "user-service.jar"]