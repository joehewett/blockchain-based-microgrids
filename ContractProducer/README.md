## Contract Producer

Simple ``Web3J`` java program, which publishes the energy contract and then finalises. 


### Required Dedendencies

#### Environment
This application can only interact with the Ethereum network through ``JSON-RPC`` exposed by a node on the network. Therefore, it will require a node to connect to (settings described below). A existing wallet is expected as the contract must be published by an account holder.
For now this is set up in the ``docker-compose.yml`` located in the ``/env`` directory. 

#### Software
* Java JVM at least version ``1.8``
* Gradle wrapper provided as part of the project 
* Docker: for building the image (currently no public image repository to publish so will have to be build locally)



### Environment
TODO add table with configuration environment variables

### Building
This project is managed by ``gradle v6.7`` . Therefore, to build the project you can run:
```bash 
./gradlew build 
```
If you would just like to generate the Wrapper classes for the Solarity contract then you can run:
```bash 
./gradlew generateContractWrappers
```


### Containerising
TODO
