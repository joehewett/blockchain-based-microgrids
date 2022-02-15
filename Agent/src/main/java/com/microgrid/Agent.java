package com.microgrid;

import com.contract.generated.contracts.EnergyContract;
import com.microgrid.config.Configuration;
import com.microgrid.energy.Consumer;
import com.microgrid.energy.EnergySaleListener;
import com.microgrid.energy.EnergySaleTask;
import com.microgrid.energy.Producer;
import com.microgrid.ethereum.ContractFetcher;
import io.reactivex.disposables.Disposable;
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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Singleton
@Slf4j
public class Agent {

    @Inject
    private Configuration config;
    @Inject
    private Credentials credentials;
    @Inject
    private Web3jService httpService;
    @Inject
    private ContractFetcher contractFetcher;

    // Should probably move this out later
    @Inject
    private Consumer consumer;
    @Inject
    private Producer producer;

    public void start_agent() throws Exception {
        Web3j web3j = Web3j.build(httpService);
        log.info("Successfully connected at {}", config.getUrl());

        RawTransactionManager transactionManager =
                new RawTransactionManager(web3j, credentials, config.getChainId());

        String contractAddress = contractFetcher.blockingWaitForContract();
        log.info("Found contract {}", contractAddress);

        EnergyContract contract =
                EnergyContract.load(contractAddress, web3j, transactionManager, new DefaultGasProvider());

        // Spans a separate thread for consumption
        EthFilter filter = new EthFilter(DefaultBlockParameterName.LATEST, DefaultBlockParameterName.LATEST, contractAddress);
        Disposable subscribe = contract.announceSaleIntentionEventFlowable(filter)
                .subscribe(new EnergySaleListener(producer, consumer, contract));


        // Spawns a thread that will execute a sale attempt every n seconds
        ScheduledExecutorService executorService = Executors
                .newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(new EnergySaleTask(producer, consumer, contract), 5, 5, TimeUnit.SECONDS);

        // We ignore the thread cleanup, ideally it would be linked to a container shutdown event
    }
}
