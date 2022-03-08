#!/usr/bin/env bash

# Helper methods for formatting
function print_error() {
  printf "\e[1;31m\n%s \e[0m\n" "$1"
}

function print_success() {
  printf "\e[1;32m\n%s \e[0m\n" "$1"
}

# TODO set env variables

printf "Checking that all dependencies are installed ... "
chmod +x ./dependency-checker.sh

# Exceptions will be propagated!
source ./dependency-checker.sh

# ADD IF ELSE MSG


# Building software
printf "Building and publishing software to MavenLocal"
source ./local-build.sh


# TODO should I remove all images
printf "Running docker monitoring services "
docker-compose -f monitoring/docker-compose.monitoring.yml up -d

print_success "Monitoring started. Opening UI "

printf "Running Proof of Authority grid"
docker-compose --env-file ./env/.poa-env up -d

printf "Running Proof of Work grid"
docker-compose --env-file ./env/.pow-env up -d

print_success "Started all components"
open "http://localhost:3000/d/H1L77tL7k/docker-monitoring?orgId=1&refresh=5m"