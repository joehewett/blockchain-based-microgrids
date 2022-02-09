FROM openjdk:11
RUN mkdir /root/app && mkdir /root/cryptodir/
COPY ./build/libs/ContractProducer-*-all.jar /root/app
WORKDIR /root/app
CMD  ["java", "-jar", "ContractProducer-0.1.0-all.jar"]