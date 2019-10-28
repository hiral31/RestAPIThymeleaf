FROM openjdk:8
ADD target/restapi.jar restapi.jar
EXPOSE 8089
ENTRYPOINT ["java","-jar","restapi.jar"]