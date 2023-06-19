FROM openjdk:17-alpine
EXPOSE 8090
COPY ./build/libs/elastic-search-service-0.0.1.jar /tmp/
WORKDIR /tmp
ENTRYPOINT ["java","-jar", "elastic-search-service-0.0.1.jar"]