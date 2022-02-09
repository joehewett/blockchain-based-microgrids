# Docker file intended to be run from docker-compose which means it cannot be
# run individually from this directory as the paths will not match !!

FROM ethereum/client-go:v1.10.1

COPY genesis.json /tmp

RUN geth init /tmp/genesis.json \
    && rm -f ~/.ethereum/geth/nodekey

ENTRYPOINT ["geth", "--nodiscover", "--ipcdisable", "--nodekey=/tmp/bootnode.key"]
