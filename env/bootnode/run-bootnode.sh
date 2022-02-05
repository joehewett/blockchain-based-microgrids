#!/usr/bin/env bash

WORK_DIR=$(pwd)
GENESIS_F=/Users/University/Desktop/Git/year3/private_bc/genesis.json

# Generate private key
if [[ ! -f bootnode.key ]]; then
    bootnode -genkey bootnode.key
fi

bootnode -nodekey bootnode.key
