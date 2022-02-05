#!/bin/bash

WORK_DIR=$(pwd)

./gradlew clean
# TODO: check if generate contract has a dependency on any other gradle task
./gradlew generateContractWrappers
./gradlew build

# Build the docker image
docker build -t agent .

cd docker || exit

# Build geth CLI container
docker build -t geth .

docker compose up

cd WORK_DIR