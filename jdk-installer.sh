#!/usr/bin/env bash

WORK_DIR=$(pwd)
BIN_DIR=$WORK_DIR/bin
BUILD_DIR=$WORK_DIR/dep_build

# Checks that the correct Java version is installed

# Script adapted version of helpful response from
# https://stackoverflow.com/questions/7334754/correct-way-to-check-java-version-from-bash-script
if type -p java; then
    _java=java
elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]];  then
    _java="$JAVA_HOME/bin/java"
fi

if [[ "$_java" ]]; then
    version=$("$_java" -version 2>&1 | awk -F '"' '/version/ {print $2}')
    if [[ "$version" > "1.8" ]]; then
        echo "Found appropriate java version $version"
        exit 0
    fi
fi

# Install as it either has not been found or is the wrong version
echo "Could not find Java. Will install to $BUILD_DIR"
read -p "Press [yY] to continue " -n 1 -r
if [[ $REPLY =~ ^[Yy]$ ]]
then
  echo "Installing ... "
  echo "TODO need to add installation script"
else
  exit 1
fi
