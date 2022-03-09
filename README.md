## Decentralised Energy Microgrid

This projects aims to demonstrate the following:

* Demonstrate that blockchain and smart contracts aid in the creation of microgrids
* The choice of _concensus algorithm_ chosen can have a large impact on both efficiency and energy consumption

This repository holds all the projects required to run a local decetralised microgrid and services to monitor its activity. Namely:
* [Agent](): Smart agent which is connected to a smartmeter (mocked) and communicates with Ethereum nodes to buy/sell via a smart contract
* [Contract Producer](): Core component of the network infrastructure which publishes the initial smart contract and terminates
* [Smart Contract lib](): Library which includes the smart contract in both Solidity and a Java wrapper (semi-auto generated)
* [Monitoring services for docker](): combines cAvisor, prometheus and grafana to showcase the results of our project
* [Environment](): all configurations to spin up a private ethereum network (genesis files, crypto wallets) 

## Guide to running the grid
Currently, the microgrid is simmulated by running all components contanierised and connected via ``docker-compose``. 

> This system can easily be run on _Mac_ and _Linux_ (not tested on Windows). However all the below guidance and scripts are aimed at **Mac users** (although probably Linux compatible with some small modifications (flags and such))

#### Required dependencies
The only dependency you will need is ``docker`` and ``docker compose``:
* __Mac__: Install [Docker Desktop](https://docs.docker.com/desktop/mac/install/)
* __Linux__: Install [Docker Engine](https://docs.docker.com/engine/install/) and [Docker Compose](https://docs.docker.com/compose/install/)
* JDK( Java 8+) (taken care of by dependency installer script)

> The ``dependency-checker.sh`` will check that _docker_, _docker-compose_ and an appropriate JDK is present on your machine.
> This script will run automatically when using the main script

***Docker machine requirements:*** this system has been tested with the following parameters: 7GB of RAM allocated to Docker and 50GB space allocation (probably overkill). To allow for a similar simulation please set these parameters in Docker Desktop (Mac) ``Preferences -> Resources -> Advanced->Apply``

## Running microgrid and monitoring services
From the project root dir run the following:

```bash
chmod +x ./run-grid.sh
./run-grid
```
***What is the script doing:***

* Runs ``dependency-checker.sh`` and ``jdk-installer.sh`` which checks that all the dependencies for the project are present and that docker is running. If the required JDK is not present then it will install is to ``$PROJ/bin``
* Runs ``local-build.sh`` which builds and publishes artifacts (required for docker images)
* Starts monitoring services. Definitions can be found at [monitoring docker](https://github.com/joehewett/cs347-coursework/blob/master/monitoring/docker-compose.monitoring.yml)
* Starts a grid running _Proof of Authority_ (builds and runs images and network)
* Starts a grid running _Proof of Work_ (builds and runs images and network)
* Will finally open the URL for the dashboard

### Monitoring progress
To monitor the progress/usage of the networks please navigate to: http://localhost:3000/d/H1L77tL7k/docker-monitoring?orgId=1&refresh=5m 

***For more detailed docs on monitoring please refer to [this README](https://github.com/joehewett/cs347-coursework/tree/master/monitoring)***



### TODO CLEAN THIS

```
chmod +x ./local-build.sh
source ./local-build.sh
```
> Source is used as the required dependencies are installed locally to ``./bin`` and added to the PATH

> The script does the following:
> * Build and publish the solidity contract to ``MavenLocal`` (default: ``~/.m2``)
> * Build ``ContractPublisher`` (dependent on contract from ``MavenLocal``)
> * Build ``Agent`` (dependent on contract from ``MavenLocal``)

### Running microgrid
Two docker compose files hold all the necessary configurations to connect the grid:

* ``docker-compose.yml``: Holds the code for grid infrastructure such as bootnodes and contracts
* ``docker-compose.agents.yml``: specifies the containers including ethereum nodes and smart agents 

Run the network infrastructure by executing:
```
docker compose up
```
Next the agents can be run by running:
```
docker compose -f docker-compose.agents.yml up
```

> TODO: here we could potentially add the ethereum nodes

## Monitoring
All metrics and statistics for each node in the network are collected and exposed at ``http://localhost:3000``. The interace is provided by [``eth-netstat``](https://github.com/cubedro/eth-netstats) who offer a simple UI to expose node metrics. The project is licensed under GNU General Public License v3.0. 

> The aim of this dashboard is to easily export statistics which will later on help in the comparison of different _consensus algorithms_ (PoA vs PoW)

If successfully set up, the metrics should look like:

<img width="1436" alt="Screenshot 2022-03-01 at 19 37 59" src="https://user-images.githubusercontent.com/44194617/156236719-d166d2f8-28a7-4394-a1ac-e19304b40ab6.png">

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
├── ContractLib
│   ├── build.gradle
│   ├── README.md
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

This project contains source code for and Agent and the initial energy contract producer.

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


