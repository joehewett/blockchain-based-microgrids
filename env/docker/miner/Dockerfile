FROM ethereum/client-go:v1.10.1

COPY genesis.json /tmp

RUN geth init /tmp/genesis.json

# All nodes are standard so we set all configurations here
ENTRYPOINT ["geth", "--networkid=31575", "--http.api=eth,admin,miner", \
            "--http", "--http.vhosts=*", "--http.corsdomain=*", "--http.addr=0.0.0.0", "--http.port=8085", \
            "--nodiscover", "--mine"]
