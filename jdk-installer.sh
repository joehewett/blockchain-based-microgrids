#!/usr/bin/env bash

WORK_DIR=$(pwd)
BIN_DIR=$WORK_DIR/bin
BUILD_DIR=$WORK_DIR/dep_build

# Helper formatting methods
function print_error() {
  printf "\e[1;31m\n%s \e[0m\n" "$1"
}

function print_success() {
  printf "\e[1;32m\n%s \e[0m\n" "$1"
}

# Checks that the correct Java version is installed

# Script adapted version of helpful response from
# https://stackoverflow.com/questions/7334754/correct-way-to-check-java-version-from-bash-script
if [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]]; then
  _java="$JAVA_HOME/bin/java"
elif type -p java; then
  _java=java
fi

if [[ "$_java" ]]; then
  version=$("$_java" -version 2>&1 | awk -F '"' '/version/ {print $2}')
  if [[ "$version" > "1.8" ]]; then
    print_success "Found appropriate java version $version"
  else
    # Install as it either has not been found or is the wrong version
    print_error "Could not find Java. Will install to $BIN_DIR"
    # if [[ $REPLY =~ ^[Yy]$ ]]; then
      printf "\n%s" "Installing ... "
      rm -rf "$BUILD_DIR"
      mkdir "$BUILD_DIR"
      cd "$BUILD_DIR"
      if [[ $OSTYPE == 'darwin'* ]]; then
        if [[ $(uname -m) == "x86_64"  ]]; then
          curl -LO  https://download.java.net/java/GA/jdk17.0.2/dfd4a8d0985749f896bed50d7138ee7f/8/GPL/openjdk-17.0.2_macos-x64_bin.tar.gz
                tar -zxf openjdk-17.0.2_macos-x64_bin.tar.gz
                mv -fv ./jdk-17.0.2.jdk/* "$BIN_DIR"
                export JAVA_HOME="$BIN_DIR/Contents/Home"
                print_success "JDK 17 installed at $JAVA_HOME"
        else
          # Here we will assume that it is AArch64
          printf "Do nothing for now"
        fi
      fi
    #fi
  fi
fi


cd $WORK_DIR
