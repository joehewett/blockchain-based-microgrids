#!/usr/bin/env bash

WORK_DIR=$(pwd)
OS_DIS=$(uname -s)

BIN_DIR=$WORK_DIR/bin

BUILD_DIR=$WORK_DIR/dep_build

mkdir -p $WORK_DIR/bin

# shellcheck disable=SC2164
rm -rf dep_build && mkdir "$BUILD_DIR" && cd "$BUILD_DIR"



if [[ $OS_DIS == 'Darwin'* ]]; then
  echo 'Installing Geth and Tools for Mac ...'
  curl -sLO https://gethstore.blob.core.windows.net/builds/geth-alltools-darwin-amd64-1.10.15-8be800ff.tar.gz

  tar -zxf geth-alltools-darwin-amd64-1.10.15-8be800ff.tar.gz

  \cp -a geth-alltools-darwin-amd64-1.10.15-8be800ff/. "$BIN_DIR"
  rm -rf geth-alltools-darwin-amd64-1.10.15-8be800ff

elif [[ $OS_DIS == 'Linux'* ]]; then
    echo 'Linux'
fi

rm -rf "$BUILD_DIR"
PATH=$BIN_DIR:$PATH


cd $WORK_DIR
