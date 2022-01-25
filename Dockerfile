#Containerises the agent

FROM openjdk:11
RUN mkdir /root/app
COPY ./build/libs/Agent-*-all.jar /root/app
WORKDIR /root/app
CMD  ["java", "-jar", "Agent-0.1.0-all.jar"]
