FROM openjdk:17-alpine
EXPOSE 8090
COPY ./build/libs/elastic-search-service-0.0.1.jar /tmp/
WORKDIR /tmp
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=docker", "elastic-search-service-0.0.1.jar"]