FROM maven:3.8.7-openjdk-18-slim AS mavenBuild

MAINTAINER Software Shinobi "the.software.shinobi@gmail.com"

WORKDIR /

COPY . .

RUN mvn install -DskipTests

FROM eclipse-temurin:18-jre-alpine

COPY --from=mavenBuild /target/big-boss-lifestyle-1.0.jar /big-boss-lifestyle.jar

COPY --from=mavenBuild /src/main/resources/application.properties /application.properties

CMD ["java", "-jar", "/big-boss-lifestyle.jar"] 
