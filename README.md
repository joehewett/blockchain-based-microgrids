## Blockchain-Based Energy Microgrid

This project demonstrates the following:

* Blockchain and smart contracts aid in the creation of microgrids
* The choice of _consensus algorithm_ has an impact on both efficiency and energy consumption

This repository holds all projects required to run a local decentralised microgrid and services to monitor its activity:
* **Agent**: Smart agent which is connected to a (mock) smart meter and communicates with Ethereum nodes to buy/sell via a smart contract
* **Contract Producer**: Core component of the network infrastructure which publishes the initial smart contract and terminates
* **Smart Contract lib**: Library which includes the smart contract in both Solidity and a Java wrapper (semi-auto generated)
* **Monitoring services for docker**: Combines cAdvisor, Prometheus and Grafana to showcase results
* **Environment**: All configurations to spin up a private Ethereum network

## Guide to running the grid
The microgrid is simulated by running all components containerised and connected via ``docker-compose``. 

> This system can be run on _Mac_ and _Linux_ (not tested on Windows). The following guidance and scripts are aimed at **Mac users**.

#### Required dependencies
You will need the following dependencies:
* Docker/Docker Compose:
  * __Mac__: Install [Docker Desktop](https://docs.docker.com/desktop/mac/install/)
  * __Linux__: Install [Docker Engine](https://docs.docker.com/engine/install/) and [Docker Compose](https://docs.docker.com/compose/install/)
* JDK (Java 8+)

> The ``dependency-checker.sh`` will check that _docker_, _docker-compose_ and an appropriate JDK is present on your machine.
> This script will run automatically when using the main script.

***Docker machine requirements:*** This system has been tested with the following parameters: 4 cores, 7GB of RAM allocated to Docker, and 50GB space allocation. To allow for a similar simulation please set these parameters in Docker Desktop (Mac) ``Preferences -> Resources -> Advanced -> Apply``.

## Running microgrid and monitoring services
From the project root directory run the following:

```bash
./run-grid.sh
```

> If not executable you may need to run ``chmod +x ./run-grid.sh``


***What the script does:***

* Runs ``dependency-checker.sh`` and ``jdk-installer.sh`` which checks that all the dependencies for the project are present and that docker is running. If the required JDK is not present then it will install is to ``$PROJ/bin`` (***MacOS only***)
* Runs ``local-build.sh`` which builds and publishes artefacts (required for docker images)
* Starts monitoring services. Definitions can be found at [monitoring docker](https://github.com/joehewett/cs347-coursework/blob/master/monitoring/docker-compose.monitoring.yml)
* Starts a grid running _Proof of Authority_ (builds and runs images and network)
* Starts a grid running _Proof of Work_ (builds and runs images and network)
* Opens the URL for the dashboard

### Monitoring progress
A number of dashboards to monitor both the _Docker Engine_ and _Ethereum Nodes_ can be accessed at:

* Docker engine dashboard: http://localhost:3000/d/H1L77tL7k/docker-monitoring?orgId=1&refresh=5m 
* PoA ethereum network: http://localhost:3001
* PoW ethereum network: http://localhost:3002

> These ports are set in the configuration files found in ``env/``

***For more detailed docs on monitoring please refer to [this README](https://github.com/joehewett/cs347-coursework/tree/master/monitoring)***

### Architecture

#### Grid architecture 
![architecture-Page-2 drawio](https://user-images.githubusercontent.com/44194617/157416364-52dc22eb-bd69-462b-bd83-9dba978ed2c7.png)

#### Monitoring architecture
![Untitled Diagram drawio](https://user-images.githubusercontent.com/44194617/157416391-865f4896-5667-46f6-ba62-fe926bf9e59b.png)

### Documentation
Each subproject has its own _README_:

* **Agent**
* **Contract Producer**
* **Environment**

### Technologies used 

#### Web3j
This Agent will mainly use Ethereum as a gateway for its transactions with other agents on the 
network and therefore makes use of [``web3j``](https://docs.web3j.io/4.8.7/). <br>

#### Micronaut
[``Micronaut``](https://micronaut.io) is a light-weight JVM-based framework intended to aid in the creation of microservices. We use it specifically for 
its dependency injection and configuration files. 
