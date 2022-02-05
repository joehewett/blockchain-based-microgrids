## Decentralised Energy Microgrid
This repository holds all the projects required to run a local decetralised microgrid backed by ethereum

### Project Structure
<pre>├── Agent
│   ├── Dockerfile
│   ├── README.md
│   ├── ...
│   └── src
├── ContractProducer
│   ├── Dockerfile
│   ├── README.md
│   ├── ...
│   └── src
├── README.md
└── env
    ├── agent1
    ├── agent2
    ├── bin
    ├── bootnode
    ├── contract-node
    ├── dep-installer.sh
    ├── docker
    └── run-env.sh
</pre>

This project contains source code for and Agent and the initial energy contract producer. TODO add tree structure

### Guide to running grid
First, you will have to install the required dependencies by running:
```bash 
cd env
chmod +x dep-installer.sh
source dep-installer.sh
```
> Hopefully, this wont be required later on as docker should take care of all dependencies

### Documentation
Each subproject has its own Readme. TODO add links

### Technologies used 

#### Web3j
This Agent will mainly use Ethereum as a gateway for its transactions with other agents on the 
network and therefore makes use of [``web3j``](https://docs.web3j.io/4.8.7/). <br>

#### Micronaut
[``Micronaut``](https://micronaut.io) is a light weight JVM based framework intended to aid in the creation of microservices. We use it specifically for 
its dependency injection, configuration files. 

***More to come when we introduce kubernetes, helm, docker, etc***


