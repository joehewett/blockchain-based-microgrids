## Blockchain-Based Energy Microgrid

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
You will need the following dependencies:
* Docker/Docker compose:
  * __Mac__: Install [Docker Desktop](https://docs.docker.com/desktop/mac/install/)
  * __Linux__: Install [Docker Engine](https://docs.docker.com/engine/install/) and [Docker Compose](https://docs.docker.com/compose/install/)
* JDK (Java 8+)

> The ``dependency-checker.sh`` will check that _docker_, _docker-compose_ and an appropriate JDK is present on your machine.
> This script will run automatically when using the main script

***Docker machine requirements:*** this system has been tested with the following parameters: 7GB of RAM allocated to Docker and 50GB space allocation (probably overkill). To allow for a similar simulation please set these parameters in Docker Desktop (Mac) ``Preferences -> Resources -> Advanced -> Apply``

## Running microgrid and monitoring services
From the project root directory run the following:

```bash
./run-grid.sh
```

> If not executable you may need to run ``chmod +x ./run-grid.sh``


***What is the script doing:***

* Runs ``dependency-checker.sh`` and ``jdk-installer.sh`` which checks that all the dependencies for the project are present and that docker is running. If the required JDK is not present then it will install is to ``$PROJ/bin`` (***MacOS only***)
* Runs ``local-build.sh`` which builds and publishes artifacts (required for docker images)
* Starts monitoring services. Definitions can be found at [monitoring docker](https://github.com/joehewett/cs347-coursework/blob/master/monitoring/docker-compose.monitoring.yml)
* Starts a grid running _Proof of Authority_ (builds and runs images and network)
* Starts a grid running _Proof of Work_ (builds and runs images and network)
* Will finally open the URL for the dashboard

### Monitoring progress
A number of dashboards to monitor both the _Docker Engine_ and _Ethereum Nodes_ have been added. 

They can be accessed at:

* Docker engine dashboard: http://localhost:3000/d/H1L77tL7k/docker-monitoring?orgId=1&refresh=5m 
* PoA ethereum network: http://localhost:3001
* PoA ethereum network: http://localhost:3002

> These ports are set in the configuration files found in ``env/``

***For more detailed docs on monitoring please refer to [this README](https://github.com/joehewett/cs347-coursework/tree/master/monitoring)***

### Architecture

#### Grid architecture 
![architecture-Page-2 drawio](https://user-images.githubusercontent.com/44194617/157416364-52dc22eb-bd69-462b-bd83-9dba978ed2c7.png)

#### Monitoring architecture
![Untitled Diagram drawio](https://user-images.githubusercontent.com/44194617/157416391-865f4896-5667-46f6-ba62-fe926bf9e59b.png)


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


