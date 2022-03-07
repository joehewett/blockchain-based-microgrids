#!/usr/bin/env bash


# THIS SCRIPT WILL CHECK THAT YOU HAVE ALL THE REQUIRED DEPENDENCIES INSTALLED ON YOUR MACHINE
# UNFORTUNATELY WE CANNOT INSTALL DOCKER AND DOCKER COMPOSE WITHOUT ROOT PRIVILEGES THEREFORE
# WE WILL ASSUME IT HAS BEEN INSTALLED BY THE USER
# THIS SCRIPT WILL JUST CHECK IT EXITS AND RUNNING

if ! command -v docker &> /dev/null; then
    printf "\nFailed to find docker on your PATH. Is it installed and on the PATH?"
#    exit 1
fi

if ! type "docker-compose" > /dev/null; then
  printf "\nFailed to find docker compose on your PATH. Is it installed and on the PATH?"
fi

if ! docker version > /dev/null 2>&1; then
  printf "Docker engine is not running. Starting daemon ..."

  if [[ $OSTYPE == 'darwin'* ]]; then
    open -a Docker
    while ! docker version > /dev/null 2>&1; do
      printf ".";
      sleep 1
    done
  # TODO what if it is run on Linux
  fi

fi


# Finally we check that Java is installed and the correct version
#source ./jdk-installer.sh  # TODO check what happens if this fails
