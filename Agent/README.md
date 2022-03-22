## Grid Agent

The role of the grid agent is to act as a smart element which decides when a prosumer has an surplus/shortage of energy and will buy/sell accordingly. 
This component is a DApp meaning that it communicates with other agents through the ethereum network specifically through smart contracts. 

> Its worth remebering this project aims to prove certain hypothesis therefore this agent only serves as an MVP to this end. This means that the smart meter 
>  is mocked, the consumption and production of energy is arbitrary and controlled via environment variables. 


### Logic overview
This application is multithreaded. Each thread does the following:

* ___Main thread___: Blocking waits for the the appearance of the smart contract in the network, then idly serves a HTTP server exposed on port ``8080``
* ___Thread 1___: Simulates the production/consumption of energy. The parameters for which are set as environment variables (sleep time). 
* ___Thread 2___: Listens out for sale events from the smart contract
* ___Thread 3___: Listens out for confirmed purchase events

All threads other than main, require to keep a consistent state of the current energy of the system. Therefore, they all use the thead safe Singleton ``SmartMeter``.
This class keeps track of the energy sold, bought, consumed and produced. 


## Building and running the Agent

***The ``run-grid.sh`` script will take care of all this*** 

This is just a guide to building and running this project as a standalone should the need arise

### Required Dependencies
In order to build the project you will need Java JDK version 8+. The project is build using gradle but a wrapper is provided to avoid version incompatibilities. 

At build time this project imports the solidity contract code from ``~/.m2`` and therefore the ``ContractLib`` must be build and published before building this project to avoid buildtime errors. 

> Tradidionally we would host such a library on an online repo however ``MavenLocal`` will do for a project of this nature

## Building
In order to build the project you should run:
```bash 
./gradlew build
```

## Running the agent:
To run the agent on your machine, you should run:

```bash 
./gradlew run -e <any_required_env_variables>
```

## Containerising the Agent
A dockerfile is provided with the project as it is intended to run as part of a larger scale network. The image can be build as such:

```bash 
docker build -t grid-agent .
```


### Environment

Environment Variable | Default | Description 
--- | --- | --- 
NET_SCHEME | http | http or https url scheme for protocol to connect to Ethereum Node 
NET_HOST | localhost | Host address of Ethereum Node
NET_PORT | 8085 | Port exposed by Ethereum Node
WEB3J_WALLET_PATH | /root/ | Path to crypto wallet
WEB3J_WALLET_PASSWORD | password | Password to crypto wallet
NET_CHAIN_ID | 31575 | Ethereum Network chain ID (1 is the public network)
PRODUCTION_RATE | 50 | Percentage of total production rate (1000)
CONSUMPTION_RATE | 75 | Percentage of consumption rate. 100 percent would be 1000 W every time the thread executes

