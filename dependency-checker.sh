#!/usr/bin/env bash

# THIS SCRIPT WILL CHECK THAT YOU HAVE ALL THE REQUIRED DEPENDENCIES INSTALLED ON YOUR MACHINE
# UNFORTUNATELY WE CANNOT INSTALL DOCKER AND DOCKER COMPOSE WITHOUT ROOT PRIVILEGES THEREFORE
# WE WILL ASSUME IT HAS BEEN INSTALLED BY THE USER
# THIS SCRIPT WILL JUST CHECK IT EXITS AND RUNNING

# Color formatting for errors
function print_error() {
  printf "\e[1;31m\n%s \e[0m\n" "$1"
}

function print_success() {
  printf "\e[1;32m\n%s \e[0m\n" "$1"
}

## Check that docker is installed
if ! command -v docker &>/dev/null; then
  print_error "Failed to find docker on your PATH. Is it installed and on the PATH?"
  exit 1
fi

## Check that docker compose is installed (usually by default on mac)
if ! type "docker-compose" >/dev/null; then
  print_error "Failed to find docker compose on your PATH. Is it installed and on the PATH?"
  exit 1
fi

## If docker not running then we turn on the engine and wait for it to start
if ! docker version >/dev/null 2>&1; then
  printf "\n%s" "Docker engine is not running. Starting daemon ..."

  if [[ $OSTYPE == 'darwin'* ]]; then
    open -a Docker -g
    COUNTER=0
    while ! docker version >/dev/null 2>&1; do
      printf "."
      COUNTER=$((COUNTER +1))
      if [ "$COUNTER" == 45 ]; then
        print_error "Failed to programmatically start docker daemon. Please do so manually by opening 'Docker' on your Mac"
        exit 1
      fi
      sleep 1
    done
  # TODO what if it is run on Linux
  fi

fi

print_success "Found all required dependencies!"

# Finally we check that Java is installed and the correct version
#source ./jdk-installer.sh  # TODO check what happens if this fails
