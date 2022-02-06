package com.contract;

import com.contract.config.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import com.contract.generated.contracts.EnergyContract;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

@Slf4j
public class ContractProducer {

    private static final Configuration config = Configuration.getInstance();

    public static void main(String[] args) throws Exception {
        log.info("Running with config \n {}", config.toString());
        Credentials credentials = WalletUtils.loadCredentials(config.getWalletPassword(), config.getWalletPath());

        Web3j web3j = Web3j.build(new HttpService(config.getUrl()));

        log.info("Deploying contract");
        RawTransactionManager transactionManager = new RawTransactionManager(web3j, credentials, config.getChainId());
        EnergyContract contract = EnergyContract.deploy(web3j, transactionManager, new DefaultGasProvider())
                .send();
        log.info("Contract created with address {}", contract.getContractAddress());
    }
}

