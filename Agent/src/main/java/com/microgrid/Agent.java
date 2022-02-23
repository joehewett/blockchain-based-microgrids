package com.microgrid;

import com.contract.generated.contracts.EnergyContract;
import com.microgrid.config.Configuration;
import com.microgrid.energy.EnergySystem;
import com.microgrid.energy.SmartMeter;
import com.microgrid.ethereum.ContractFetcher;
import com.microgrid.ethereum.contract.EnergyOfferListener;
import com.microgrid.ethereum.contract.EnergySaleListener;
import com.microgrid.ethereum.contract.EnergySaleTask;
import lombok.extern.slf4j.Slf4j;
import org.web3j.abi.EventEncoder;
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
    @Inject
    private EnergySystem energySystem;
    @Inject
    private SmartMeter smartMeter;

    // Worth remembering
    // https://github.com/ethereum/go-ethereum/issues/14945

    public void start_agent() throws Exception {
        Web3j web3j = Web3j.build(httpService);
        log.info("Successfully connected at {}", config.getUrl());

        RawTransactionManager transactionManager =
                new RawTransactionManager(web3j, credentials, config.getChainId());

        String contractAddress = contractFetcher.blockingWaitForContract();
        log.info("Found contract {}", contractAddress);

        EnergyContract contract =
                EnergyContract.load(contractAddress, web3j, transactionManager, new DefaultGasProvider());

        // Power up appliances
        energySystem.powerUpAppliances();

        // Subscribes to contract events to check for available purchasing options
        activateSaleListener(contract);
        // Subscribes to completed sale events
        subscribeToCompletedSales(contract);

        // Spans thread to periodically sell energy if needed
         startPeriodicEnergySale(contract, 15);

//        if (config.getConsumptionRate() < config.getProductionRate()) {
//            log.info("Selling energy");
//            contract.sellEnergy(BigInteger.valueOf(500)).send();
//            log.info("Sold energy");
//        }
    }

    public void subscribeToCompletedSales(final EnergyContract contract) {
        String contractAddress = contract.getContractAddress();
        // Spans a separate thread for consumption
        EthFilter filter = new EthFilter(DefaultBlockParameterName.LATEST, DefaultBlockParameterName.LATEST, contractAddress);
        filter.addSingleTopic(EventEncoder.encode(EnergyContract.SALEGONETHROUGH_EVENT));
        contract.saleGoneThroughEventFlowable(filter)
                .subscribe(new EnergySaleListener(credentials, smartMeter));
    }

    public void activateSaleListener(final EnergyContract contract) {
        String contractAddress = contract.getContractAddress();
        // Spans a separate thread for consumption
        EthFilter filter = new EthFilter(DefaultBlockParameterName.LATEST, DefaultBlockParameterName.LATEST, contractAddress);
        filter.addSingleTopic(EventEncoder.encode(EnergyContract.ANNOUNCESALEINTENTION_EVENT));
        contract.announceSaleIntentionEventFlowable(filter)
                .subscribe(new EnergyOfferListener(smartMeter, contract, credentials));
    }

    public void startPeriodicEnergySale(final EnergyContract contract, int delay) {
        // Spawns a thread that will execute a sale attempt every n seconds
        ScheduledExecutorService executorService = Executors
                .newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(new EnergySaleTask(smartMeter, contract), 5, delay, TimeUnit.SECONDS);

        // We ignore the thread cleanup, ideally it would be linked to a container shutdown event
    }
}
