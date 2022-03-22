
# Monitoring

The aim of this project is two part:
 * Demonstrate that blockchain and smart contracts aid in the creation of microgrids
 * Show that the choice of concensus algorithms has a performance impact
 
 Therefore, a monitoring system has been put in place in order to plot the progress of each component in our system. 
 
 
 > The monitoring system is technically not part of the microgrid, however it does provides a global overview of the docker environemnt running the microgrid. It connects 
 > to the host machine rather than the running docker environments
 
 This is achieved by using the following technologies: 
 
 * [cAdvisor](https://github.com/google/cadvisor): a Google project which collects statisticts from containers on a machine
 * [Prometheus](https://github.com/prometheus/prometheus): Metrics management project which collects and stores metrics via peridic straping of configurable http endpoints
 * [Grafana](https://github.com/grafana/grafana): Visualisation tool for metrics and log management
 
 
 ## Docker container monitoring
   ### Requirements
 * Docker: All components are run and linked together via docker and docker compose, respectively
 * Ports: The UI for grafana will be exposed on ___port 3000___
 
 > If this port is occupied for some reason you can change this by editing the following TODO add once file is finalised
 
### Starting services
 All the components are containerised and can be run in an isolated environment. Run by executing:
 > From the projects root directory
 
 ```bash
 docker-compose -f monitoring/docker-compose.monitoring.yml up
 
 ```
 
 ### Visualising results
 Once all the services are running you should be able to monitor them by navigating to ``http://localhost:3000``:
 
 ```bash
 open http://localhost:3000
 ```
 
You should be able to see something like: TODO need to take a screenshot
 
 ### Architecture monitoring

![Untitled Diagram drawio](https://user-images.githubusercontent.com/44194617/157083124-504b0053-e9cb-4e6c-89ba-7e6fde733468.png)

 
  ## Ethereum activity monitoring
  All metrics and statistics for each node in the network are collected and exposed at ``http://localhost:<exposed_port>``. The interace is provided by [``eth-netstat``](https://github.com/cubedro/eth-netstats) who offer a simple UI to expose node metrics. The project is licensed under GNU General Public License v3.0. 

> The aim of this dashboard is to easily export statistics which will later on help in the comparison of different _consensus algorithms_ (PoA vs PoW)

If successfully set up, the metrics should look like:

<img width="1436" alt="Screenshot 2022-03-01 at 19 37 59" src="https://user-images.githubusercontent.com/44194617/156236719-d166d2f8-28a7-4394-a1ac-e19304b40ab6.png">
