#!/usr/bin/env bash

WORK_DIR=$(pwd)
BIN_DIR=$WORK_DIR/bin


# TODO: for now we will assume that if the dir exits then the executables are included
if [ ! -d "$BIN_DIR" ]; then
  source ./dep-installer.sh
else
  echo "Dependencies already exist. Skipping installation"
fi

# Update path
PATH=$BIN_DIR:$PATH


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
