FROM openjdk:11.0.3-jre-slim-stretch
VOLUME /config
ADD target/AdministrationService-0.0.1-SNAPSHOT.jar /AdministrationService.jar
EXPOSE 8095
RUN bash -c 'touch /AdministrationService.jar'
ENTRYPOINT ["java","-jar","/AdministrationService.jar"]