## Grid Agent

The role of the grid agent is to act as a smart element which decides when a prosumer has an surplus/shortage of energy and will buy/sell accordingly. 
This component is a DApp meaning that it communicates with other agents through the ethereum network specifically through smart contracts. 

> Its worth remebering this project aims to prove certain hypothesis therefore this agent only serves as an MVP to this end. This means that the smart meter 
>  is mocked, the consumption and production of energy is arbitrary and controlled via environment variables. 


### Logic overview
This application is multithreaded. Each thread does the following:

* ___Main thread___: Blocking waits for the the appearance of the smart contract in the network, then idly serves a HTTP server exposed on port ``8080``
* ___Thread 1___: Simulates the production/consumption of energy. The parameters for which are set as environment variables
* ___Thread 2___: Listens out for sale events from the smart contract
* ___Thread 3___: Listens out for confirmed purchase events

All threads other than main, require to keep a consistent state of the current energy of the system. Therefore, they all use the thead safe Singleton ``SmartMeter``.
This class keeps track of the energy sold, bought, consumed and produced. 





### Environment

Environment Variable | Default | Description 
--- | --- | --- 
URL_SCHEME | http | http or https url scheme for protocol to connect to Ethereum Node 
URL_HOST | localhost | Host address of Ethereum Node
URL_PORT | 8085 | Port exposed by Ethereum Node
WALLET_PATH | /root/ | Path to crypto wallet
WALLET_PASSWORD | password | Password to crypto wallet
CHAIN_ID | 1 | Ethereum Network chain ID (1 is the public network)
