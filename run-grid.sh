#!/usr/bin/env bash

# TODO set env variables

printf "Checking that dependencies are installed ... "
chmod +x ./dependency-checker.sh
source ./dependency-checker.sh # TODO check for failure

# ADD IF ELSE MSG


# Building software
#printf "Building and publishing software to MavenLocal"
#source ./local-build.sh


# TODO should I remove all images
#printf "Running docker monitoring services "
#docker-compose -f -d monitoring/docker-compose.monitoring.yml up



# Make sure all dependencies are installed
