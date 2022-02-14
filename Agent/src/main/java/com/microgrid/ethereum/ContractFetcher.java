package com.microgrid.ethereum;

import org.web3j.protocol.exceptions.TransactionException;

import java.io.IOException;

public interface ContractFetcher {
    String blockingWaitForContract() throws TransactionException, IOException;
}
