#!/usr/bin/env bash

WORK_DIR=$(pwd)

source ./dep-installer.sh

cd bootnode || exit

IP="127.0.0.1"
PORT="30301"

KEY=$(bootnode -writeaddress -nodekey bootnode.key)
ENODE=enode://"$KEY"@"$IP":0?discport="$PORT"

echo "$ENODE"

./run-bootnode.sh&


cd $WORK_DIR