FROM openjdk:16
VOLUME /config
ADD target/AdministrationService-0.0.1-SNAPSHOT.jar /AdministrationService.jar
EXPOSE 8097
RUN bash -c 'touch /AdministrationService.jar'
ENTRYPOINT ["java","-jar","/AdministrationService.jar"]
