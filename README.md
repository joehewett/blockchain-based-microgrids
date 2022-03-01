## Decentralised Energy Microgrid
This repository holds all the projects required to run a local decetralised microgrid backed by ethereum

### Project Structure
<pre>├── Agent
│   ├── build.gradle
│   ├── Dockerfile
│   ├── README.md
│   └── src
├── ContractProducer
│   ├── build.gradle
│   ├── Dockerfile
│   ├── Dockerfile.local
│   │   ├── README.md
│   └── src
├── dep-installer.sh
├── docker-compose.yml
├── eth_network
│   ├── agent1
│   ├── bootnode
│   ├── contract-producer
│   ├── genesis.json
│   └── miner
├── README.md
└── run-env.sh

</pre>

This project contains source code for and Agent and the initial energy contract producer. TODO add tree structure

### Guide to running grid
Currently, the microgrid is simmulated by running all components contanierised and connected via ``docker-compose``. 

#### Required dependencies
The only dependency you will need is ``docker`` and ``docker compose``:
* __Mac__: Install [Docker Desktop](https://docs.docker.com/desktop/mac/install/)
* __Linux__: Install [Docker Engine](https://docs.docker.com/engine/install/) and [Docker Compose](https://docs.docker.com/compose/install/)

Docker is the only required dependency as all operations running/building is performed in containers. While this does mean it takes a while to build it also allows us to be consistent amoung different development machines
```
cd PROJECT_DIR
docker compose up
```

### Monitoring
All metrics and statistics for each node in the network are collected and exposed at ``http://localhost:3000``. The interace is provided by [``eth-netstat``](https://github.com/cubedro/eth-netstats) who offer a simple UI to expose node metrics. The project is licensed under GNU General Public License v3.0. 

> The aim of this dashboard is to easily export statistics which will later on help in the comparison of different _consensus algorithms_ (PoA vs PoW)

If successfully set up, the metrics should look like:

<img width="1436" alt="Screenshot 2022-03-01 at 19 37 59" src="https://user-images.githubusercontent.com/44194617/156236719-d166d2f8-28a7-4394-a1ac-e19304b40ab6.png">


### Documentation
Each subproject has its own _README_:

* [Agent]()
* [Contract Producer]()
* [Environment]()

### Technologies used 

#### Web3j
This Agent will mainly use Ethereum as a gateway for its transactions with other agents on the 
network and therefore makes use of [``web3j``](https://docs.web3j.io/4.8.7/). <br>

#### Micronaut
[``Micronaut``](https://micronaut.io) is a light weight JVM based framework intended to aid in the creation of microservices. We use it specifically for 
its dependency injection, configuration files. 

***More to come when we introduce kubernetes, helm, docker, etc***


