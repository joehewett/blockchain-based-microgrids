package com.microgrid;

import com.contract.generated.contracts.EnergyContract;
import com.microgrid.config.Configuration;
import com.microgrid.ethereum.ContractFetcher;
import com.microgrid.ethereum.GethApi;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.math.BigInteger;

@Singleton
@Slf4j
public class Agent {

    @Inject
    private Configuration config;
    @Inject
    private GethApi gethApi;
    @Inject
    private Credentials credentials;
    @Inject
    private Web3jService httpService;
    @Inject
    private ContractFetcher contractFetcher;

    public void start_agent() throws Exception {
        Web3j web3j = Web3j.build(httpService);
        log.info("Successfully connected at {}", config.getUrl());

        RawTransactionManager transactionManager =
                new RawTransactionManager(web3j, credentials, config.getChainId());

        String contractAddress = contractFetcher.blockingWaitForContract();
        log.info("Found contract {}", contractAddress);

        EnergyContract contract =
                EnergyContract.load(contractAddress, web3j, transactionManager, new DefaultGasProvider());

        contract.sellEnergy(BigInteger.TEN).sendAsync();

        EthFilter filter = new EthFilter(DefaultBlockParameterName.LATEST, DefaultBlockParameterName.LATEST, contractAddress);

        Disposable subscribe = contract.announceSaleIntentionEventFlowable(filter)
                .subscribe(announceSaleIntentionEventResponse -> log.info("Somebody is selling!!!!!!!!!!"));


    }
}
