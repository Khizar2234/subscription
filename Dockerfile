FROM openjdk:11.0.3-jre-slim-stretch
VOLUME /config
ADD target/SubscriptionService-0.0.1-SNAPSHOT.jar /SubscriptionService.jar
EXPOSE 8097
RUN bash -c 'touch /SubscriptionService.jar'
ENTRYPOINT ["java","-jar","/SubscriptionService.jar"]