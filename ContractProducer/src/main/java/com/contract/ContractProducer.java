package com.contract;

import com.contract.config.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import com.contract.generated.contracts.EnergyContract;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

@Slf4j
public class ContractProducer {

    private static final Configuration config = Configuration.getInstance();

    public static void main(String[] args) throws Exception {
            Credentials credentials = WalletUtils.loadCredentials(config.getWalletPassword(), config.getWalletPath());

            Web3j web3j = Web3j.build(new HttpService(config.getUrl()));

            log.info("Deploying contract");
            EnergyContract contract = EnergyContract.deploy(web3j, credentials, new DefaultGasProvider(), "This is a basic energy contract!")
                    .send();
            log.info("Contract created with address {}", contract.getContractAddress());
    }
}

