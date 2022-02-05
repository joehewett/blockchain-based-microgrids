#!/bin/bash

WORK_DIR=$(pwd)
GENESIS_F=/Users/University/Desktop/Git/year3/private_bc/genesis.json

rm -rf keystore
rm -f password.txt

touch password.txt

echo "password" >> password.txt

# Create account
geth --datadir "$WORK_DIR" account new --password password.txt

# Initialise node
geth --datadir "$WORK_DIR" init $GENESIS_F

# Start node
geth --datadir "$WORK_DIR" \
     --port 30311 \
     --networkid 123 \
     --http.api="miner,eth,admin" \
     --http \
     --http.corsdomain="*" \
     --http.addr="localhost" \
     --http.port=8085







