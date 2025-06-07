# Build stage
FROM eclipse-temurin:21-jdk-alpine AS build
RUN mkdir -p /build
WORKDIR /build
ADD . /build
RUN --mount=type=cache,target=/root/.m2 ./mvnw -f /build/pom.xml clean package

# Package stage
FROM eclipse-temurin:21-jre-alpine
COPY --from=build /build/target/*-full.jar /app/server.jar
EXPOSE 15164
CMD [ "java", "-cp", "/app/server.jar:/app/lib/*", "com.rug.tno.App" ]