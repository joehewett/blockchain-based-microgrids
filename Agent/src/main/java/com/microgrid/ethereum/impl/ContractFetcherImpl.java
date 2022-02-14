package com.microgrid.ethereum.impl;

import com.microgrid.ethereum.ContractFetcher;
import lombok.extern.slf4j.Slf4j;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;

@Slf4j
@Singleton
public class ContractFetcherImpl implements ContractFetcher {

    public static final int ATTEMPTS = 10000000;
    public static final long SLEEP_DURATION = 10000L;

    @Inject
    private Web3jService httpService;

    @Override
    public String blockingWaitForContract() throws TransactionException, IOException {
        try {
            Web3j web3j = Web3j.build(httpService);
            Transaction contractTransaction = web3j.replayPastAndFutureTransactionsFlowable(new DefaultBlockParameterNumber(0))
                    .filter(trans -> trans.getTo() == null)
                    .blockingFirst();

            TransactionReceipt transactionReceipt =
                    new PollingTransactionReceiptProcessor(web3j, SLEEP_DURATION, ATTEMPTS)
                            .waitForTransactionReceipt(contractTransaction.getHash());

            return transactionReceipt.getContractAddress();

        } catch (IOException | TransactionException e) {
            log.error("Error which waiting for contract transaction receipt");
            throw e;
        }
    }
}
