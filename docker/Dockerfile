FROM openjdk:8-jdk

ADD clientbase-edu.jar clientbase-edu.jar
RUN sh -c 'touch /clientbase-edu.jar'
ENTRYPOINT ["java","-jar", "-Dspring.data.mongodb.uri=mongodb://clientbase-edu-mongo/client", "/clientbase-edu.jar"]