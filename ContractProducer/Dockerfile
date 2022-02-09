FROM gradle:6.9-jdk11 AS build
ADD --chown=gradle:gradle . /local/gradle
WORKDIR /local/gradle
RUN gradle build

FROM openjdk:11
RUN mkdir /root/app
COPY --from=build /local/gradle/build/libs/*.jar /root/app
WORKDIR /root/app
CMD  ["java", "-jar", "ContractProducer-0.1.0-all.jar"]
