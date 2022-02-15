#!/usr/bin/env bash

WORK_DIR=$(pwd)

source ./dep-installer.sh


# Rather than rely on external repositories we will build all code locally
# Hopefully, we can do this in docker but for now this is the best way forward
# The only risk is compatibility issues JDK version, etc


cd ./ContractLib
./gradlew clean build publishMavenJavaPublicationToMavenLocal
cd $WORK_DIR

cd ContractProducer
./gradlew clean build
cd $WORK_DIR

cd Agent
./gradlew clean build
cd $WORK_DIR
