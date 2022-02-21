FROM ethereum/client-go:v1.10.1

COPY genesis.json /tmp

RUN geth init /tmp/genesis.json \
    && touch password.txt \
    && echo "password" > password.txt \
    && rm -f ~/.ethereum/geth/nodekey

ENTRYPOINT ["geth", "--allow-insecure-unlock"]
