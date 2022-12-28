FROM adoptopenjdk/openjdk11

COPY build/libs/*.jar /opt/app/
COPY build/resources/main/* /opt/app/config/
WORKDIR /opt/app
ENTRYPOINT ["java", "-jar", "CrossBook-1.0.0.jar"]