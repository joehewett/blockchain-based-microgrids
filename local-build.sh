#!/usr/bin/env bash

WORK_DIR=$(pwd)

# Rather than rely on external repositories we will build all code locally
# Hopefully, we can do this in docker but for now this is the best way forward
# The only risk is compatibility issues JDK version, etc

# Not running tests as there arent any unit/component tests in place
# This project accompanies a research paper therefore does not adhere fully to standard QA procedures (automated test suites)

cd ./ContractLib
./gradlew printJavaHome build publishMavenJavaPublicationToMavenLocal -x test
cd $WORK_DIR

cd ContractProducer
./gradlew printJavaHome build -x test
cd $WORK_DIR

cd Agent
./gradlew printJavaHome build -x test
cd $WORK_DIR
