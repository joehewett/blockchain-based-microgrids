## Contract Producer

Simple ``Web3j`` Java program, which publishes the energy contract and then terminates. 


### Required Dependencies

#### Environment
This application can only interact with the Ethereum network through ``JSON-RPC`` exposed by a node on the network. Therefore, it will require a node to connect to (settings described below). An existing wallet is expected as the contract must be published by an account holder. This is set up in the ``docker-compose.yml`` located in the ``/env`` directory. 

#### Software
* Java JVM; at least version ``1.8``
* Gradle wrapper provided as part of the project 
* Docker: for building the image (currently no public image repository to publish so will have to be build locally)

### Environment

Environment Variable | Default | Description 
--- | --- | --- 
URL_SCHEME | http | http or https url scheme for protocol to connect to Ethereum Node 
URL_HOST | localhost | Host address of Ethereum Node
URL_PORT | 8085 | Port exposed by Ethereum Node
WALLET_PATH | /root/ | Path to crypto wallet
WALLET_PASSWORD | password | Password to crypto wallet
CHAIN_ID | 1 | Ethereum Network chain ID (1 is the public network)


### Building
***The ``run-grid.sh`` script handles this*** 

This is just a guide to building and running this project as a standalone, should the need arise.

***

This project is managed by ``gradle v6.7``. To build the project, run:
```bash 
./gradlew build 
```
To solely generate the wrapper classes for the Solarity contract then you can run:
```bash 
./gradlew generateContractWrappers
```

### Containerising
To containerise the producer, run:

```bash 
docker build -f Dockerfile.local -t contract-producer .
```

> ***This requires the Contract library to be published first***
