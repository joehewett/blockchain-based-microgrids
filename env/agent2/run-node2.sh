#!/bin/bash

WORK_DIR=$(pwd)
GENESIS_F=/Users/University/Desktop/Git/year3/private_bc/genesis.json

rm -rf keystore

# Create account
geth --datadir "$WORK_DIR" account new --password "password"

# Initialise node
geth --datadir "$WORK_DIR" init $GENESIS_F

# Start node
geth --datadir "$WORK_DIR" \
     --port 30312 \
     --networkid 123 \
     --http.corsdomain="*" \
     --http.port 8086







