FROM ethereum/client-go:v1.10.1

COPY genesis.json /tmp

RUN geth init /tmp/genesis.json \
    && rm -f ~/.ethereum/geth/nodekey

ENTRYPOINT ["geth", "--mine", "--miner.threads=1"]
