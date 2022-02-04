## Microgrid Agent
This repository holds the code to one of the Agents in the microgrid. Much like a smartmeter 
this agent is responsible for reading consumption/production, and requesting the purchase/sale of energy as needed. 

### Technologies used 

#### Web3j
This Agent will mainly use Ethereum as a gateway for its transactions with other agents on the 
network and therefore makes use of [``web3j``](https://docs.web3j.io/4.8.7/). <br>

#### Micronaut
[``Micronaut``](https://micronaut.io) is a light weight JVM based framework intended to aid in the creation of microservices. We use it specifically for 
its dependency injection, configuration files. 

***More to come when we introduce kubernetes, helm, docker, etc***


